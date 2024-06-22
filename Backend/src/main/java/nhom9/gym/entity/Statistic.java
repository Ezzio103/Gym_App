package nhom9.gym.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Statistic {
    private String label;
    private double value;


}

