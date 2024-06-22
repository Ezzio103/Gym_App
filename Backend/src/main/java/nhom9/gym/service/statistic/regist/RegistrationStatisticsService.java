package nhom9.gym.service.statistic.regist;

import nhom9.gym.entity.RenewalRegistrationStatistic;

import java.util.List;

public interface RegistrationStatisticsService {
    List<RenewalRegistrationStatistic> getMonthlyRegistrationStats(int months);
    List<RenewalRegistrationStatistic> getYearlyRegistrationStats(int year);
}

