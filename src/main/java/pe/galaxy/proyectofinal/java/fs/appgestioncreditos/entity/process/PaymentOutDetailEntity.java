package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.process;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TBL_DESEMBOLSO_DETALLE")
@Entity(name = "PaymentOutDetailEntity")

public class PaymentOutDetailEntity {

    @Id
    @Column(name = "DESEMBOLSO_DETALLE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDesembolsoDetalle")
    @SequenceGenerator(sequenceName = "SEQ_DESEMBOLSO_DETALLE", allocationSize = 1, name = "seqDesembolsoDetalle")
    private Long idPaymentOutDetail;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "DESEMBOLSO_ID", nullable = false)
    private PaymentOutEntity paymentOut;

    @NotNull
    @Column(name = "NUMERO_CUOTA")
    private Integer count;

    @NotNull
    @Column(name = "FECHA_PAGO")
    private LocalDate datePayment;

    @NotNull
    @Column(name = "CAPITAL_CUOTA")
    private Double capitalMoney;

    @NotNull
    @Column(name = "INTERES_CUOTA")
    private Double taxRate;

    @NotNull
    @Column(name = "PAGO_CUOTA")
    private Double monthlyPay;



}
