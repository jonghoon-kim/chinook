package entities;

import entities.base.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Summary extends Entity {
    @Override
    public String getKeyText() {
        return at.toString();
    }

    private LocalDateTime at;
    private int count;
}
