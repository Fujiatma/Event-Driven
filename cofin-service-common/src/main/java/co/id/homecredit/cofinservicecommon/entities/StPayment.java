package co.id.homecredit.cofinservicecommon.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ST_PAYMENT")
    @SequenceGenerator(sequenceName = "SEQ_ST_PAYMENT", allocationSize = 0, name = "SEQ_ST_PAYMENT")
    private Long id;
    private String contractNumber;
    private Date exportDate;
    private Date paymentDate;
    private String paymentTypeJfs;
    private BigDecimal bslTotalAmtPayment;
    private BigDecimal bslAmtPayment;
    private BigDecimal pmtInstallment;
    private BigDecimal pmtPrincipal;
    private BigDecimal pmtInterest;
    private BigDecimal pmtEtFee;
    private BigDecimal pmtPenalty;
    private BigDecimal pmtOverpayment;
    private BigDecimal pmtOther;
    private Date adjPaymentDate;
    private String createdBy;
    private Date dtimeCreated;
    private String updatedBy;
    private Date dtimeUpdated;
    private String bankContractNumber;
    private Long idAgreement;
    private String bankPaymentType;

}
