import lombok.Data;

import java.util.Objects;

@Data
public class Album {
    private int albumId;
    private String title;
    private int artistId;
}
