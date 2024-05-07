package com.pdi.hexago.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "Transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Timestamp timestamp;

    @OneToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private AccountEntity account;

    private BigDecimal previousAmount;

    private BigDecimal transactionValue;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
    }

    public BigDecimal getPreviousAmount() {
        return previousAmount;
    }

    public void setPreviousAmount(BigDecimal previousAmount) {
        this.previousAmount = previousAmount;
    }
}
