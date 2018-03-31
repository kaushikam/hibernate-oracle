package name.kaushikam.hibernate.domain.model;

public interface StockRepository {
    Stock save(Stock stock);
    void delete(Stock deleted);
}
