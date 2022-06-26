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
public class VacancyListResponse {

    private List<VacancyResponse> vacancyList;

    private Integer totalPages;

    private Integer currentPage;
}
