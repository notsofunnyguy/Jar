package jar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalCurrencyConverterDto implements Serializable {
    private String base;
    private HashMap<String, Double> rates;
}
