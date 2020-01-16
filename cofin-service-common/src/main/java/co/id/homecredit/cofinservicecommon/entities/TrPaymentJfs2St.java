package co.id.homecredit.cofinservicecommon.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author fujiatma.napitupulu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TrPaymentJfs2St {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ST_PAYMENT")
    @SequenceGenerator(sequenceName = "SEQ_ST_PAYMENT", allocationSize = 0, name = "SEQ_ST_PAYMENT")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TR_PAYMENT_JFS_ID", nullable = false, foreignKey = @ForeignKey(name = "TR_PAYMENT_JFS2ST_FK1"))
    private TrPaymentJfs trPaymentJfs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ST_PAYMENT_ID", nullable = false, foreignKey = @ForeignKey(name = "TR_PAYMENT_JFS2ST_FK2"))
    private StPayment stPayment;
}
