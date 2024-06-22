package nhom9.gym.entity;

import lombok.*;

@Getter
@Setter
public class RenewalRegistrationStatistic extends Statistic {
    private double value2;

    public RenewalRegistrationStatistic(String label, double value, double value2) {
        super(label, value);
        this.value2 = value2;
    }


}

