package nhom9.gym.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class RevenueStatistic extends Statistic {
    public RevenueStatistic(String label, double value) {
        super(label, value);
    }
}
