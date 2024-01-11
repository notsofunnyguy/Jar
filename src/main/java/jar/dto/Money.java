package jar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Money {
    @NonNull
    private double amount;
    @NotBlank
    private String currency;
}
