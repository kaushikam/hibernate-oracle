package name.kaushikam.hibernate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_daily_record", uniqueConstraints = @UniqueConstraint(columnNames = "record_date"))
public class StockDailyRecord implements Serializable {

    @Id
    @Column(name = "record_id")
    @GeneratedValue(generator = "dailyRecordSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "dailyRecordSequence", initialValue = 1, allocationSize = 100,
            sequenceName = "daily_record_sequence")
    private Integer recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(name = "price_open", precision = 6)
    private Float priceOpen;

    @Column(name = "price_close", precision = 6)
    private Float priceClose;

    @Column(name = "price_change", precision = 6)
    private Float priceChange;

    @Column(name = "volume")
    private Long volume;

    @Temporal(TemporalType.DATE)
    @Column(name = "record_date", unique = true, nullable = false)
    private Date date;


}
