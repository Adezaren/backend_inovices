package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonStatisticsDTO {
    private long personId;
    private String personName;
    private double revenue;
}
