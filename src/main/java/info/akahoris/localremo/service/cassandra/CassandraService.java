package info.akahoris.localremo.service.cassandra;


import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import info.akahoris.localremo.component.ErrorResponse.NotFoundSignal;
import info.akahoris.localremo.controller.response.Signal;
import info.akahoris.localremo.service.cassandra.table.SignalTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CassandraService {

    @Autowired
    CassandraAdminTemplate cassandraTemplate;

    public SignalTable insert(Signal signal, String name) {
        return cassandraTemplate.insert(new SignalTable(Uuids.timeBased(), name, signal));
    }

    public List<SignalTable> selectAll() {
        SimpleStatement select = QueryBuilder.selectFrom("signal").all().build();
        return cassandraTemplate.select(select, SignalTable.class);
    }

    public SignalTable select(String id) {
        UUID uuid = UUID.fromString(id);
        return cassandraTemplate.selectOneById(uuid, SignalTable.class);
    }

    public String delete(String id) throws NotFoundSignal {
        UUID uuid = UUID.fromString(id);
        Boolean res = cassandraTemplate.deleteById(uuid, SignalTable.class);
        if(!res) throw new NotFoundSignal("信号が見つかりませんでした");

        return id;
    }

}
