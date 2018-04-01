package name.kaushikam.hibernate.infrastructure.hibernate;

import lombok.extern.slf4j.Slf4j;
import name.kaushikam.hibernate.Application;
import name.kaushikam.hibernate.domain.model.*;
import org.dbunit.IDatabaseTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

import java.util.Date;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestPersistenceContext.class, Application.class})
public class HibernateStockRepositoryITCase extends AbstractITCase {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private IDatabaseTester databaseTester;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void configure() {
        String schema = environment.getRequiredProperty("spring.datasource.username");
        super.configure(databaseTester, schema);
    }

    @Test
    public void testStockRepoCreatesStockObjectProperly() throws Exception {
        Stock stock = new Stock();
        stock.setStockCode("7052");
        stock.setStockName("PADINI");

        StockDetail stockDetail = new StockDetail();
        stockDetail.setCompName("PADINI Holding Malaysia");
        stockDetail.setCompDesc("one stop shopping");
        stockDetail.setRemark("vinci vinci");
        stockDetail.setListedDate(new Date());

        stock.setStockDetail(stockDetail);
        stockDetail.setStock(stock);

        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.2"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("10.0"));
        stockDailyRecords.setVolume(3000000L);
        stockDailyRecords.setDate(new Date());

        stockDailyRecords.setStock(stock);
        stock.getStockDailyRecords().add(stockDailyRecords);


        stock = stockRepository.save(stock);

        assertNotNull(stock.getStockId());

        stockRepository.delete(stock);
    }

}