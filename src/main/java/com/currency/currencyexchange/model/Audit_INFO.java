package com.currency.currencyexchange.model;


import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "audit Info")
public class Audit_INFO {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long request_Id;

    public enum APIRequestStatus {
        REQUEST_SENT,
        RESPONSE_READY;
    }
    @Column(name = "status")
    private APIRequestStatus status;

    @SerializedName("from")
    @Column(name = "Base Currency")
    private String request;

    @SerializedName("to")
    @Column(name = "Conversion Currency")
    private String response;

    @SerializedName("result")
    @Column(name = "Rate")
    private String Rate;

    @SerializedName("date")
    @Column(name="create_Ts")
    private Date create_Ts;

    @SerializedName("timestamp")
    @Column(name = "update_Ts")
    private String update_Ts;
}
