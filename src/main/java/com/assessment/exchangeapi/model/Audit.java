package com.assessment.exchangeapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name ="auditInfo")
public class Audit {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long requestId;

    public enum Status{
        REQUEST_SENT,
        RESPONSE_RECEIVED
    }

    @Column(name = "status")
    private Status status;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    @CreationTimestamp
    @Column(name = "create_ts")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "updated_ts")
    private Date updateTime;
}
