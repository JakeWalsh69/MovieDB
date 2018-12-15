package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class ProductionCountries {

    private String iso_3166_1;
    @Column(insertable=false, updatable = false)
    private String name;

    public ProductionCountries() {
    }

    public ProductionCountries(String iso_3166_1, String name) {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }
}
