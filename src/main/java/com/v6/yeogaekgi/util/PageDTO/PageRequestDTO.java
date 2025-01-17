package com.v6.yeogaekgi.util.PageDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page;
    private int size;
    private String type;
    private String keyword;


    public PageRequestDTO(){
        this.page = 0;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){

        return PageRequest.of(page , size, sort);

    }
}