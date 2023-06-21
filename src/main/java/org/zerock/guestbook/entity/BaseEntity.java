package org.zerock.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 테이블로 생성되지 않음, 실제 테이블은 BaseEntity 를 상속한 엔티티의 클래스로 테이블 생성됨
@EntityListeners(value={AuditingEntityListener.class}) // AuditingEntityListener : JPA 내부에서 엔티티객체 생성/변경 감지하는역할
@Getter
abstract class BaseEntity {

    @CreatedDate // JPA 에서 엔티티 생성시간 처리, updatable = false 은 해당 컬럼값은 변경되지 않도록 설정
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // JPA 에서 엔티티 최종수정시간 처리
    @Column(name="moddate")
    private LocalDateTime modDate;

}
