package br.com.rembert.miniautorizador.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@Entity
@Table(name="transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "value")
    private BigDecimal value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transactions", referencedColumnName = "card_number")
    private Card card;
}
