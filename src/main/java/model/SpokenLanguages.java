package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class SpokenLanguages {

    private String iso_639_1;
    @Column(insertable=false, updatable = false)
    private String name;

    public SpokenLanguages() {
    }

    public SpokenLanguages(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }
}
