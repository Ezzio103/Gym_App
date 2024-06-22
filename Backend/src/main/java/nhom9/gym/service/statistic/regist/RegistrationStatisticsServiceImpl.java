package nhom9.gym.service.statistic.regist;

import nhom9.gym.dao.RegistrationRepository;
import nhom9.gym.entity.RenewalRegistrationStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RegistrationStatisticsServiceImpl implements RegistrationStatisticsService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationStatisticsServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<RenewalRegistrationStatistic> getMonthlyRegistrationStats(int months) {
        LocalDate endDate = LocalDate.now();
        List<RenewalRegistrationStatistic> stats = new ArrayList<>();

        for (int i = months - 1; i >= 0; i--) {
            LocalDate currentMonth = endDate.minusMonths(i).withDayOfMonth(1);
            LocalDate monthStart = currentMonth;
            LocalDate monthEnd = (i == 0) ? endDate : currentMonth.withDayOfMonth(currentMonth.lengthOfMonth());

//            System.out.println(monthStart + " " + monthEnd);

            // Fetch registration and renewal counts from the repository
            Integer registrationCount = registrationRepository.countRegistrationsInPeriod(monthStart, monthEnd) != null ?
                    registrationRepository.countRegistrationsInPeriod(monthStart, monthEnd) : 0;
            Integer renewalCount = registrationRepository.countRenewalsInPeriod(monthStart, monthEnd) != null ?
                    registrationRepository.countRenewalsInPeriod(monthStart, monthEnd) : 0;

            // Get the month name
            String monthName = monthStart.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());

            stats.add(new RenewalRegistrationStatistic(monthName, registrationCount, renewalCount));
        }

        return stats;
    }

    @Override
    public List<RenewalRegistrationStatistic> getYearlyRegistrationStats(int year) {
        List<RenewalRegistrationStatistic> stats = new ArrayList<>();

        for (Month month : Month.values()) {
            LocalDate monthStart = LocalDate.of(year, month, 1);
            LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

            // Fetch registration and renewal counts from the repository
            Integer registrationCount = registrationRepository.countRegistrationsInPeriod(monthStart, monthEnd) != null ?
                    registrationRepository.countRegistrationsInPeriod(monthStart, monthEnd) : 0;
            Integer renewalCount = registrationRepository.countRenewalsInPeriod(monthStart, monthEnd) != null ?
                    registrationRepository.countRenewalsInPeriod(monthStart, monthEnd) : 0;

            // Get the month name
            String monthName = month.getDisplayName(TextStyle.FULL, Locale.getDefault());

            stats.add(new RenewalRegistrationStatistic(monthName, registrationCount, renewalCount));
        }

        return stats;
    }
}
