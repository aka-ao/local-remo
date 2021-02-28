package info.akahoris.localremo.service.cassandra.table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.akahoris.localremo.controller.response.Signal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table("signal")
@AllArgsConstructor
@NoArgsConstructor
public class SignalTable {
    @PrimaryKey
    private UUID id;
    private String name;
    private String json;

    public SignalTable(UUID id, String name, Signal signal) {
        setId(id);
        setName(name);
        setJson(signal);
    }

    public void setJson(Signal signal) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.json = mapper.writeValueAsString(signal);
        } catch(Exception e) {
            this.json = "";
        }
    }

    @JsonIgnore
    public Signal getSignal() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(this.json, Signal.class);
        } catch(Exception e) {
            return new Signal();
        }
    }
}
