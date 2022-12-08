package com.mustache.bbs.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass @Getter
public class BaseEntity {

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
}


