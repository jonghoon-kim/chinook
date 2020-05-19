package exceptions;

import java.time.LocalDateTime;

public class WrongUpdateException extends RuntimeException {
    private int trackId;
    private LocalDateTime at;

    public int getTrackId() {
        return trackId;
    }

    public LocalDateTime getAt() {
        return at;
    }

    public WrongUpdateException(int trackId, LocalDateTime at) {
        super();
        this.trackId = trackId;
        this.at = at;
    }
}
