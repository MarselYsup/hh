package com.technokratos.util;

import com.technokratos.exceptions.file.FileUnableProcessException;
import com.technokratos.models.MongoFileEntity;
import com.technokratos.models.ResumeEntity;
import com.technokratos.models.SkillEntity;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

@UtilityClass
public class DocxGenerator {

    private final String DEFAULT_FONT_FAMILY = "Times New Roman";

    private final Integer DEFAULT_FONT_SIZE = 12;

    private final String DATE_PATTERN_FORMAT = "dd.MM.yyyy";

    private final String DEFAULT_EMPTY_TEXT = "(not added)";

    public void writeResumeToResponse(ResumeEntity resume, Optional<MongoFileEntity> avatar, HttpServletResponse response) {

        String filename = String.format("%s.docx", resume.getTitle());

        XWPFDocument document = getDocument(resume, avatar);

        try {
            document.write(response.getOutputStream());
        } catch (IOException e) {
            throw new FileUnableProcessException(filename);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-Disposition", String.format("filename=\"%s\"", filename));
    }

    private XWPFDocument getDocument(ResumeEntity resume, Optional<MongoFileEntity> avatar) {
        XWPFDocument document = new XWPFDocument();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_FORMAT).withZone(ZoneId.systemDefault());

        addParagraphInfo(document, resume.getTitle(), 24);

        String names = String.format(" %s %s", resume.getEmployee().getFirstName(), resume.getEmployee().getLastName());

        if(avatar.isPresent()) {
            addParagraphWithPicture(document, new ByteArrayInputStream(avatar.get().getContent().getData()), names);
        } else {
            addParagraphInfo(document, names, 16);
        }

        if(Objects.nonNull(resume.getEmployee().getDateOfBirth())) {
            addParagraphProperty(document, "Date of birth", formatter.format(resume.getEmployee().getDateOfBirth()));
        }

        addParagraphProperty(document, "Phone number", resume.getEmployee().getPhoneNumber());
        addParagraphProperty(document, "City", resume.getEmployee().getCity());
        addParagraphProperty(document, "E-mail", resume.getEmployee().getUserAccount().getEmail());

        addParagraphInfo(document, resume.getActivity().getActivity().toUpperCase(), 16);
        addParagraphProperty(document, "Employment", resume.getEmployment().toString());

        if(Objects.nonNull(resume.getSalary())) {
            addParagraphProperty(document, "Salary", resume.getSalary().toString());
        }

        if(Objects.nonNull(resume.getEducation())) {
            addParagraphInfo(document, "Education", 16);
            addParagraphProperty(document, "University name", resume.getEducation().getUniversityName());
            addParagraphProperty(document, "Institute name", resume.getEducation().getInstituteName());
            addParagraphProperty(document, "Major", resume.getEducation().getMajor());
            addParagraphProperty(document, "Starting date",
                    formatter.format(resume.getEducation().getStartingDate()));
            addParagraphProperty(document, "Completion date",
                    formatter.format(resume.getEducation().getCompletionDate()));
            addParagraphProperty(document, "Education level", resume.getEducation().getEducationLevel().toString());
        }

        addParagraphInfo(document, "Information", 16);
        addParagraphProperty(document, "Experience", resume.getExperience());

        if(Objects.nonNull(resume.getSkillSet())) {
            addParagraphProperty(document, "Skills",
                    resume.getSkillSet().stream().map(SkillEntity::getSkill).collect(Collectors.joining(", ")));
        }

        if(Objects.nonNull(resume.getInformation())) {
            addParagraphProperty(document, "Addition information", resume.getInformation());
        }

        return document;
    }

    private void addParagraphWithPicture(XWPFDocument document, InputStream image, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        correctSpacing(paragraph);

        XWPFRun imageRun = paragraph.createRun();
        try {
            imageRun.addPicture(image, XWPFDocument.PICTURE_TYPE_JPEG, "avatar",
                    Units.toEMU(100), Units.toEMU(100)
            );
        } catch (InvalidFormatException | IOException e) {
            throw new FileUnableProcessException("avatar");
        }
        XWPFRun textRun = paragraph.createRun();
        configure(textRun, text, 16, false);
        textRun.addBreak();
    }

    private void addParagraphInfo(XWPFDocument document, String text, int size) {
        XWPFParagraph paragraph = document.createParagraph();
        correctSpacing(paragraph);
        XWPFRun textRun = paragraph.createRun();
        configure(textRun, text, size, true);
        textRun.addBreak();
    }

    private void addParagraphProperty(XWPFDocument document, String title, String text) {
        XWPFParagraph paragraph = document.createParagraph();
        correctSpacing(paragraph);

        XWPFRun titleRun = paragraph.createRun();
        configure(titleRun, title.concat(": "), DEFAULT_FONT_SIZE, true);

        XWPFRun textRun = paragraph.createRun();
        configure(textRun, text, DEFAULT_FONT_SIZE, false);
        textRun.addBreak();
    }

    private void configure(XWPFRun run, String text, int size, boolean bold) {

        if(Objects.isNull(text)) {
            text = DEFAULT_EMPTY_TEXT;
        }

        run.setBold(bold);
        run.setFontSize(size);
        run.setFontFamily(DEFAULT_FONT_FAMILY);
        run.setText(text);
    }

    private void correctSpacing(XWPFParagraph paragraph) {
        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(0);
    }

}
