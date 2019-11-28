package com.diploma.knowledgebase.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token", schema = "public")
public class ConfirmationToken {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "confirmation_token_token_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "token_id", nullable = false)
    private long token_id;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "created_date")
    private Date createdDate;

    @Column
    private Long user_id;


    public ConfirmationToken(User user) {
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString() + user.getReg_id();
        this.user_id = user.getId();
    }

    public ConfirmationToken() {
    }

    public long getTokenid() {
        return token_id;
    }

    public void setTokenid(long tokenid) {
        this.token_id = tokenid;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

}
