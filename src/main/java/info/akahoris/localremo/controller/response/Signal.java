package info.akahoris.localremo.controller.response;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Signal {
    private int freq;

    private List<Integer> data;

    private String format;
}
