package nhom9.gym.service.statistic.revenue;

import nhom9.gym.dao.RegistrationRepository;
import nhom9.gym.entity.RevenueStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RevenueStatisticsServiceImpl implements RevenueStatisticsService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RevenueStatisticsServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<RevenueStatistic> getMonthlyRevenueStats(int months) {
        LocalDate endDate = LocalDate.now();
        List<RevenueStatistic> stats = new ArrayList<>();

        for (int i = months - 1; i >= 0; i--) {
            LocalDate currentMonth = endDate.minusMonths(i).withDayOfMonth(1);
            LocalDate monthStart = currentMonth;
            LocalDate monthEnd = (i == 0) ? endDate : currentMonth.withDayOfMonth(currentMonth.lengthOfMonth());

            // Fetch revenue from the repository
            Double revenue = registrationRepository.calculateRevenueInPeriod(monthStart, monthEnd);
            if (revenue == null) {
                revenue = 0.0;
            }

            // Get the month name
            String monthName = monthStart.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

            stats.add(new RevenueStatistic(monthName, revenue));
        }

        return stats;
    }

    @Override
    public List<RevenueStatistic> getYearlyRevenueStats(int year) {
        List<RevenueStatistic> stats = new ArrayList<>();

        for (Month month : Month.values()) {
            LocalDate monthStart = LocalDate.of(year, month, 1);
            LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

            // Fetch revenue from the repository
            Double revenue = registrationRepository.calculateRevenueInPeriod(monthStart, monthEnd);
            if (revenue == null) {
                revenue = 0.0;
            }

            // Get the month name
            String monthName = month.getDisplayName(TextStyle.FULL, Locale.getDefault());

            stats.add(new RevenueStatistic(monthName, revenue));
        }

        return stats;
    }
}
