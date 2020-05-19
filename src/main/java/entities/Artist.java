package entities;

import entities.base.Entity;
import lombok.Data;


@Data
public class Artist extends Entity {
    private int artistId;
    private String name;

    @Override
    public String getKeyText() {
        return Integer.toString(artistId);
    }
}
