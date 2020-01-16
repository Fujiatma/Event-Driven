package co.id.homecredit.cofinservicecommon.dto.permata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * @author fujiatma.napitupulu
 */

/*entity for consume Queue RABBIT*/
public class PaymentDto {

    private Long id;

    private String contractNumber;

    private Long paymentBslId;

    private String exchangePaymentId;

    private Date paymentDate;

    private BigDecimal totalAmtPayment = BigDecimal.ZERO;

    private BigDecimal totalAmtPaymentJfs = BigDecimal.ZERO;

    private String paymentTypeJfs;

    private String pairingType;

    private Date pairingDate;

    private BigDecimal pmtInstallment;

    private Date exportDate;

    private Integer isActive;

    private String status;

    private Long jfsParentId;

    private BigDecimal pairedAmount;

    private Long jmsGroupId;

    private String reason;

    private Date adjPaymentDate;

    private Date bankProcessDate;

    private String createdBy;

    private Date dtimeCreated;

    private String updatedBy;

    private Date dtimeUpdated;

    private Long idAgreement;

    private String bankContractNumber;

    private String partnerId;

}
