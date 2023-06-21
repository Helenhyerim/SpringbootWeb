package org.zerock.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.entity.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;


@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;



    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title...." + i)
                    .content("Content..." + i)
                    .writer("user" + (i%10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest(){
        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()){
            Guestbook guestbook = result.get();
            guestbook.changeTitle("Changed Title...");
            guestbook.changeContent("Changed Content...");

            guestbookRepository.save(guestbook);
        }

    }


    @Test
    public void testQuery1(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook; //1. 동적으로 처리하기 위한 Q도메인 클래스 얻어오기. 엔티티클래스의 필드를 변수로 활용가능

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder(); //2. BooleanBuilder 는 where 문에 들어가는 조건들을 넣어주는 컨테이너

        BooleanExpression expression = qGuestbook.title.contains(keyword); // 3. 원하는 조건을 필드값과 결합해서 사용

        builder.and(expression); // 4. 만들어진 조건을 where 문에 and/or 키워드로 결합

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        // 5. QuerydslPredicateExecutor 인터페이스의 findAll() 사용

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });


    }


    @Test
    public void testQuery2(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
        BooleanExpression exContent = qGuestbook.content.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll); // title or content 에 keyword 가 있고,
        builder.and(qGuestbook.gno.gt(0L)); // gno > 0 인 조건

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });


    }

}
