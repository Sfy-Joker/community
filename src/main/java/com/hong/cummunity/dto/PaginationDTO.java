package com.hong.cummunity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private Integer currentPage;
    private Integer totalPage;
    private List<QuestionDTO> questions;
    private List<Integer> pages = new ArrayList<>();
    private Boolean showPreviousPage;
    private Boolean showNextPage;
    private Boolean showFirstPage;
    private Boolean showEndPage;

    public void handle(Integer currentPage, Integer size, Integer totalCount) {
        this.currentPage = currentPage;

        if (totalCount % size == 0) {
            this.totalPage = totalCount / size;
        } else {
            this.totalPage = totalCount / size + 1;
        }

        pages.add(currentPage);
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                pages.add(0, currentPage - i);
            }
            if (currentPage + i <= this.totalPage) {
                pages.add(currentPage + i);
            }
        }

        //是否展示上一页
        if (currentPage == 1) {
            this.showPreviousPage = false;
        } else {
            this.showPreviousPage = true;
        }
        //是否展示下一页
        if (currentPage == this.totalPage) {
            this.showNextPage = false;
        } else {
            this.showNextPage = true;
        }

        //是否展示去首页
        if (this.pages.contains(1)) {
            this.showFirstPage = false;
        } else {
            this.showFirstPage = true;
        }
        //是否展示去最后一页
        if (this.pages.contains(this.totalPage)) {
            this.showEndPage = false;
        } else {
            this.showEndPage = true;
        }


    }
}
