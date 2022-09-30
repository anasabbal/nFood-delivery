package com.example.fooddelivery.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
public class AbstractEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @CreatedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected Boolean deleted = false;

    protected void delete() {
        this.deleted = true;
    }
}
