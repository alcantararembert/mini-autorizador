package br.com.rembert.miniautorizador.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Data
@Entity
@Table(name="card")
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AbstractAuditableEntity implements Serializable {

    @Id
    @Column(name = "card_number", unique = true)
    private String cardNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "balance")
    private BigDecimal balance;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Transaction> transactions;

}
