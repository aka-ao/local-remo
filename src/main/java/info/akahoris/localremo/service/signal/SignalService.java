package info.akahoris.localremo.service.signal;

import info.akahoris.localremo.controller.response.Signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SignalService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${info.akahori-s.local-remo.signal.host}")
    private String host;

    private static final String localRemoURL = "http://192.168.11.2/messages";

    public Signal fetchSignal() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Requested-With", "local");

        RequestEntity<?> req = RequestEntity.get(localRemoURL).headers(headers).build();
        ResponseEntity<Signal> res = restTemplate.exchange(req, Signal.class);

        return res.getBody();
    }

    public Signal sendSignal(Signal signal) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Requested-With", "local");
        RequestEntity<?> req = RequestEntity.post(localRemoURL).headers(headers).body(signal);
        ResponseEntity<Signal> res = restTemplate.exchange(req, Signal.class);

        return res.getBody();
    }
}
