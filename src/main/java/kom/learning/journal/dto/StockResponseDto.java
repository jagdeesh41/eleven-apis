package kom.learning.journal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockResponseDto {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("OperatingMIC")
    private String operatingMIC;
    @JsonProperty("CountryISO2")
    private String countryISO2;
    @JsonProperty("CountryISO3")
    private String countryISO3;
}
