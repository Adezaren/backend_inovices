package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatisticsDTO {
    private long invoicesCount;
    private double allTimeSum;
    private double currentYearSum;
}
