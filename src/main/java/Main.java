import data.AlbumDao;
import data.CelebrityDao;
import data.PlaylistTrackDao;
import entities.Album;
import entities.PlaylistTrack;
import exceptions.WrongUpdateException;
import lombok.SneakyThrows;

import java.util.ArrayList;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        PlaylistTrack playlistTrack = new PlaylistTrack();
        playlistTrack.setTrackId(1);
        playlistTrack.setPlaylistId(1);

        try {
            PlaylistTrackDao.getInstance().update(playlistTrack);
        } catch (WrongUpdateException e) {
            System.out.printf("trackId is %d at %s", e.getTrackId(), e.getAt());
        }
//        CelebrityDao.getInstance().
    }
}
