package name.kaushikam.hibernate.infrastructure.hibernate;

import name.kaushikam.hibernate.domain.model.Stock;
import name.kaushikam.hibernate.domain.model.StockId;
import name.kaushikam.hibernate.domain.model.StockRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface HibernateStockRepository extends StockRepository, Repository<Stock, StockId> {
}
