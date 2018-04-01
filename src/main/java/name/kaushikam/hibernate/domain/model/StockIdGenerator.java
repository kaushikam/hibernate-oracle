package name.kaushikam.hibernate.domain.model;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

@Slf4j
public class StockIdGenerator implements IdentifierGenerator, Configurable {

    private static final String STOCK_SEQUENCE = "stock_sequence";

    private String sequenceCallSyntax;

    @Override
    public void configure(Type type, Properties params,
                          ServiceRegistry serviceRegistry)
            throws MappingException {

        final JdbcEnvironment jdbcEnvironment = serviceRegistry
                .getService(JdbcEnvironment.class);
        final Dialect dialect = jdbcEnvironment.getDialect();

        sequenceCallSyntax = dialect.getSequenceNextValString(params.getProperty("sequenceName"));
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
       Long seqValue = ((Number) Session.class.cast(session)
               .createNativeQuery(sequenceCallSyntax)
               .uniqueResult()).longValue();
       return new StockId(seqValue);
    }
}
