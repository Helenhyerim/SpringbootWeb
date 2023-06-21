package org.zerock.guestbook.dto;

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


    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){ // Pageable 타입의 객체를 생성하는 목적
        return PageRequest.of(page - 1, size, sort); // jpa 이용중에는 페이지번호가 0부터 시작하므로 -1
    }


}
