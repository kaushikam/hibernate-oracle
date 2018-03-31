package name.kaushikam.hibernate.infrastructure.dbunit;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CustomDataSourceDatabaseTester extends DataSourceDatabaseTester {

    public CustomDataSourceDatabaseTester(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public IDatabaseConnection getConnection() throws Exception {
        IDatabaseConnection connection = super.getConnection();
        DatabaseConfig databaseConfig = connection.getConfig();
        databaseConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new Oracle10DataTypeFactory());
        databaseConfig.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES,
                Boolean.TRUE);
        return connection;
    }
}
