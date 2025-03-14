package com.core.accountservice.shared.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(name="created_by", length=45, updatable = false)
    protected String createdBy;

    @CreatedDate
    @Column(name="created_date", updatable = false)
    protected Date createdDate;

    @LastModifiedBy
    @Column(name="last_modified_by", length=45)
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name="last_modified_date")
    protected Date lastModifiedDate;
}