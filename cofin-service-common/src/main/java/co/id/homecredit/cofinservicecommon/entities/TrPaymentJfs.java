package co.id.homecredit.cofinservicecommon.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fujiatma.napitupulu
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TrPaymentJfs {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TR_PAY_JFS")
    @SequenceGenerator(name = "SEQ_TR_PAY_JFS", sequenceName = "SEQ_TR_PAY_JFS", allocationSize = 0)
    private Long id;
    private String contractNumber;
    private Long paymentBslId;
    private String exchangePaymentId;
    private Date paymentDate;
    private BigDecimal totalAmtPayment;
    private BigDecimal totalAmtPaymentJfs;
    private BigDecimal pmtInstallment;
    private String paymentTypeJfs;
    private String pairingType;
    private Date pairingDate;
    private String createdBy;
    private Date dtimeCreated;
    private String updatedBy;
    private Date dtimeUpdated;
    private Date exportDate;
    private String status;
    private Integer isActive;
    private String reason;
    private Long jfsParentId;
    private BigDecimal pairedAmount;
    private Date bankProcessDate;
    private Date adjPaymentDate;
}
