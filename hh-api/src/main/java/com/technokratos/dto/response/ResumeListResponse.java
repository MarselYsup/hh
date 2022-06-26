package com.technokratos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResumeListResponse {

    private List<ResumeResponse> resumeList;

    private Integer totalPages;

    private Integer currentPage;
}
