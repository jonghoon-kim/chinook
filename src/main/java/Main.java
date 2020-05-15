import data.AlbumDao;
import entities.Album;
import lombok.SneakyThrows;

import java.util.ArrayList;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        var album = AlbumDao.getInstance().getByKey(2);
        System.out.println(album);
//        ArrayList<Album> albums = AlbumDao.getInstance().getAll();
//        ArrayList<Album> albums2 = AlbumDao.getInstance().getByArtistId(3);
//        int albumCount = AlbumDao.getInstance().getCount();
    }
}
