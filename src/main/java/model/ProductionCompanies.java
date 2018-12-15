package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class ProductionCompanies {

    @Column(insertable=false, updatable = false)
    private Long id;
    @Column(insertable=false, updatable = false)
    private String name;
    private String origin_country;

    public ProductionCompanies() {
    }

    public ProductionCompanies(Long id, String name, String origin_country) {
        this.id = id;
        this.name = name;
        this.origin_country = origin_country;
    }
}
