import data.AlbumDao;
import data.CelebrityDao;
import data.PlaylistTrackDao;
import data.TrackDao;
import entities.Album;
import entities.PlaylistTrack;
import exceptions.WrongUpdateException;
import helpers.ConnectionString;
import lombok.SneakyThrows;

import java.util.ArrayList;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        ConnectionString.getInstance().initialize("jdbc:sqlserver://192.168.1.5;database=Chinook;user=sa;password=3512");

        System.out.println(AlbumDao.getInstance().getCount());
    }
}
