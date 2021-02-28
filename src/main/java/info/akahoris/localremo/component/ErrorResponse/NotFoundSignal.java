package info.akahoris.localremo.component.ErrorResponse;

import java.io.IOException;

public class NotFoundSignal extends IOException {
    public NotFoundSignal(String message) {
        super(message);
    }
}
