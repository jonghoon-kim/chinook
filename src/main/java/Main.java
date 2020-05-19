import data.AlbumDao;
import data.CelebrityDao;
import data.PlaylistTrackDao;
import data.TrackDao;
import entities.Album;
import entities.PlaylistTrack;
import exceptions.WrongUpdateException;
import lombok.SneakyThrows;

import java.util.ArrayList;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(PlaylistTrackDao.getInstance().toBigString());
    }
}
