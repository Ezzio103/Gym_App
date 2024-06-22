package nhom9.gym.service.statistic.revenue;

import nhom9.gym.entity.RevenueStatistic;

import java.util.List;

public interface RevenueStatisticsService {
    List<RevenueStatistic> getMonthlyRevenueStats(int months);
    List<RevenueStatistic> getYearlyRevenueStats(int year);
}

