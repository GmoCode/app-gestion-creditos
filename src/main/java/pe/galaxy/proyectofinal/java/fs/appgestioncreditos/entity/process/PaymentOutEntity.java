package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.process;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ClientEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ProductEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TBL_DESEMBOLSO")
@Entity(name = "PaymentOutEntity")

public class PaymentOutEntity {

    @Id
    @Column(name = "DESEMBOLSO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDesembolso")
    @SequenceGenerator(sequenceName = "SEQ_DESEMBOLSO", allocationSize = 1, name = "seqDesembolso")
    private Long idPaymentOut;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false, foreignKey = @ForeignKey(name = "FK_Desembolso_Usuario"))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_Desembolso_Cliente"))
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_Desembolso_Producto"))
    private ProductEntity product;

    @NotNull
    @Size(min = 3 ,max = 20, message = "Caracteres permitidos entre {min} y {max}")
    @Column(name = "TIPO_CAMBIO")
    private String currencyType;

    @NotNull
    @Positive (message = "Solo números positivos")
    @Min(value = 300, message = "Monto minimo de crédito {value}")
    @Column(name = "MONTO_CREDITO")
    private Double loamAmount;

    @NotNull
    @Positive (message = "Solo números positivos")
    @Min(value = 6, message = "Plazo minimo {value} meses")
    @Max(value = 36, message = "Plazo maximo {value} meses")
    @Column(name = "TOTAL_CUOTAS")
    private Integer loamTerm;

    @Column(name = "FECHA_PRESTAMO")
    private LocalDate datePaymentOut = LocalDate.now();

    @NotNull
    @Column(name = "MONTO_TOTAL")
    private Double total;

    @NotNull
    @Column(name = "ESTADO")
    private String status;

    @OneToMany(mappedBy = "paymentOut" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<PaymentOutDetailEntity> details;

    public void calculateTotal(){

        double taxTemp = 0.0;

        taxTemp = (loamAmount * product.getTax());

        setTotal(loamAmount + taxTemp);
    }

    public void calculateMonthly(){

        Double capitalMoney = (loamAmount / loamTerm);

        Double monthPay = (total / loamTerm);

        Double monthTaxRate = monthPay - capitalMoney;

        PaymentOutDetailEntity details = new PaymentOutDetailEntity();



    }
}
