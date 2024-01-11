package jar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DailyReportRequestDto {
    @NotBlank
    private String date;
}
