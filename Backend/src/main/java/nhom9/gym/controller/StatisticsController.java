package nhom9.gym.controller;

import nhom9.gym.entity.RenewalRegistrationStatistic;
import nhom9.gym.entity.RevenueStatistic;
import nhom9.gym.service.statistic.regist.RegistrationStatisticsService;
import nhom9.gym.service.statistic.revenue.RevenueStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private  RevenueStatisticsService revenueStatisticsService;
    private  RegistrationStatisticsService registrationStatisticsService;

    @Autowired
    public StatisticsController(RevenueStatisticsService revenueStatisticsService, RegistrationStatisticsService registrationStatisticsService) {
        this.revenueStatisticsService = revenueStatisticsService;
        this.registrationStatisticsService = registrationStatisticsService;
    }

    @GetMapping("/revenue/monthly")
    public ResponseEntity<List<RevenueStatistic>> getMonthlyRevenueStats(@RequestParam("months") int months) {
        List<RevenueStatistic> stats = revenueStatisticsService.getMonthlyRevenueStats(months);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/revenue/yearly")
    public ResponseEntity<List<RevenueStatistic>> getYearlyRevenueStats(@RequestParam("year") int year) {
        List<RevenueStatistic> stats = revenueStatisticsService.getYearlyRevenueStats(year);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/registrations/monthly")
    public ResponseEntity<List<RenewalRegistrationStatistic>> getMonthlyRegistrationStats(@RequestParam("months") int months) {
        List<RenewalRegistrationStatistic> stats = registrationStatisticsService.getMonthlyRegistrationStats(months);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/registrations/yearly")
    public ResponseEntity<List<RenewalRegistrationStatistic>> getYearlyRegistrationStats(@RequestParam("year") int year) {
        List<RenewalRegistrationStatistic> stats = registrationStatisticsService.getYearlyRegistrationStats(year);
        return ResponseEntity.ok(stats);
    }
}

