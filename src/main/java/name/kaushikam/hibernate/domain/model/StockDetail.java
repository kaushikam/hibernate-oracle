package name.kaushikam.hibernate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_detail")
public class StockDetail {

    @Id
    @GenericGenerator(name = "generator", strategy = "foreign",
    parameters = @Parameter(name = "property", value = "stock"))
    @GeneratedValue(generator = "generator")
    private StockId stockId;

    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private Stock stock;

    @Column(name = "comp_name", nullable = false, length = 100)
    private String compName;

    @Column(name = "comp_desc", nullable = false)
    private String compDesc;

    @Column(name = "remark", nullable = false)
    private String remark;

    @Column(name = "listed_date", nullable = false)
    private Date listedDate;
}
