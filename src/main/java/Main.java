import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Album album = AlbumDao.getInstance().getByKey(2);
        ArrayList<Album> albums = AlbumDao.getInstance().getAll();
        ArrayList<Album> albums2 = AlbumDao.getInstance().getByArtistId(3);
        int albumCount = AlbumDao.getInstance().getCount();
    }


}
