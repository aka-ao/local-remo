package info.akahoris.localremo.controller;

import info.akahoris.localremo.component.ErrorResponse.NotFoundSignal;
import info.akahoris.localremo.controller.request.SendSignal;
import info.akahoris.localremo.controller.response.SimpleResponse;
import info.akahoris.localremo.controller.response.Signal;
import info.akahoris.localremo.service.cassandra.CassandraService;
import info.akahoris.localremo.service.cassandra.table.SignalTable;
import info.akahoris.localremo.service.signal.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SignalController {
    @Autowired
    SignalService signalService;
    @Autowired
    CassandraService cassandraService;

    @GetMapping("/signal")
    Signal getSignal() {
        return signalService.fetchSignal();
    }

    @PostMapping("/signal")
    Signal sendSignal(@RequestBody SendSignal sendSignal) {
        Signal signal = cassandraService.select(sendSignal.getId()).getSignal();
        return signalService.sendSignal(signal);
    }

    @PostMapping("/signal/{name}")
    SignalTable  postSignal(@RequestBody Signal signal, @PathVariable String name) {return cassandraService.insert(signal, name);}

    @GetMapping("/all-signal")
    List<SignalTable> getAllSignal() { return cassandraService.selectAll();}

    @DeleteMapping("/signal/{id}")
    SimpleResponse deleteSignal(@PathVariable String id) throws NotFoundSignal {
        System.out.println(id);
        cassandraService.delete(id);
        return new SimpleResponse("削除成功");
    }
}
