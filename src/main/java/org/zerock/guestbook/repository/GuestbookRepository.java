package org.zerock.guestbook.repository;

import com.querydsl.core.annotations.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.zerock.guestbook.entity.Guestbook;


public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QuerydslPredicateExecutor<Guestbook> {
}
