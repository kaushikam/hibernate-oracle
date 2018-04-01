package name.kaushikam.hibernate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {

    @Id
    @Embedded
    @GenericGenerator(name = "stock_id_generator",
            parameters = @Parameter(name = "sequenceName", value = "stock_sequence"),
            strategy = "name.kaushikam.hibernate.domain.model.StockIdGenerator")
    @GeneratedValue(generator = "stock_id_generator")
    private StockId stockId;

    @Column(name = "stock_code", nullable = false, unique = true, length = 10)
    private String stockCode;

    @Column(name = "stock_name", nullable = false, unique = true, length = 20)
    private String stockName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
    private StockDetail stockDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock", cascade = CascadeType.ALL)
    private Set<StockDailyRecord> stockDailyRecords = new HashSet<>(0);
}
