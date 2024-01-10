package jar.api;

import jar.dto.DailyReportRequestDto;
import jar.dto.DailyReportResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jar.service.DailyReportService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/daily-report")
public class DailyReportApi {

    private final DailyReportService dailyReportService;

    // this gets the Daily Report by Date
    @GetMapping("")
    public DailyReportResponseDto getDailyReport(@RequestBody DailyReportRequestDto dailyReportRequestDto){
        return dailyReportService.getDailyReportByDate(dailyReportRequestDto);
    }

}
