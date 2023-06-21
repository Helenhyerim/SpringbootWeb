package org.zerock.guestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.repository.GuestbookRepository;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동주입
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository repository; // 반드시 final 로 선언

    @Override
    public Long register(GuestbookDTO dto) {

        log.info("DTO--------------------------");
        log.info(dto);
        // GuestbookDTO(gno=null, title=Sample Title..., content=Sample Content, writer=user0, regDate=null, modDate=null)

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);
        // Guestbook(gno=null, title=Sample Title..., content=Sample Content, writer=user0)

        repository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable((Sort.by("gno").descending()));
        Page<Guestbook> result = repository.findAll(pageable);
        Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDTO(entity));
        // entityToDTO() 를 이용해 Function 생성 -> PageResultDTO 로 구성

        return new PageResultDTO<>(result, fn);
    }


}
