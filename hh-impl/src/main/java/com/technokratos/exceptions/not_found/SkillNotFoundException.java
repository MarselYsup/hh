package com.technokratos.exceptions.not_found;

public class SkillNotFoundException extends NotFoundHhException {
    public SkillNotFoundException() {
        super("Skill not found");
    }
}
