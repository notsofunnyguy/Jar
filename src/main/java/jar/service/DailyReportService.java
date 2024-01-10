package jar.service;

import jar.dto.DailyReportRequestDto;
import jar.dto.DailyReportResponseDto;

public interface DailyReportService {
    DailyReportResponseDto getDailyReportByDate(DailyReportRequestDto dailyReportRequestDto);

}
