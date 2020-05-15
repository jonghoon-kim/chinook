import data.AlbumDao;
import entities.Album;
import lombok.SneakyThrows;

import java.util.ArrayList;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Album album = AlbumDao.getInstance().getByKey(2);
        ArrayList<Album> albums = AlbumDao.getInstance().getAll();
        ArrayList<Album> albums2 = AlbumDao.getInstance().getByArtistId(3);
        int albumCount = AlbumDao.getInstance().getCount();

        Album newAlbum = new Album();
        newAlbum.setTitle("new album");
        newAlbum.setArtistId(3);
        boolean inserted = AlbumDao.getInstance().insert(newAlbum);
        System.out.println(inserted);
        int maxAlbumId = AlbumDao.getInstance().getMaxAlbumId();
        System.out.println(maxAlbumId); // 새로 추가된 albumId
    }


}
