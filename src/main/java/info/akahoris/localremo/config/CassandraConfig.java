package info.akahoris.localremo.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(cassandraTemplateRef = "cassandraTemplate")
public class CassandraConfig extends AbstractCassandraConfiguration {
    @Value("${spring.data.cassandra.username}")
    private String username;

    @Value("${spring.data.cassandra.password}")
    private String password;

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;


    @Override
    @Primary
    @Bean
    public CassandraAdminTemplate cassandraTemplate() {
        CqlSession session = CqlSession.builder().withKeyspace(keyspaceName).build();
        return new CassandraAdminTemplate(session);
    }

    // ポート番号の設定
    @Override
    @Bean(name = "Port")
    public int getPort() {
        return port;  // デフォルトは9042
    }

    // キースペースの設定
    @Override
    @Bean(name = "KeySpace")
    protected String getKeyspaceName() {
        return keyspaceName;
    }



}
