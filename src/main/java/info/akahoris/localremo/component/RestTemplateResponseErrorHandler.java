package info.akahoris.localremo.component;

import info.akahoris.localremo.component.ErrorResponse.NotFoundSignal;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        switch(clientHttpResponse.getStatusCode()) {
            case NOT_FOUND:
                throw new NotFoundSignal("信号が見つかりませんでした");
            default:
                super.handleError(clientHttpResponse);
                break;
        }
    }
}
