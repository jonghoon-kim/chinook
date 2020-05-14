import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Album a1 = AlbumDao.getInstance().getByKey(1);
        ArrayList<Album> allAlbums = AlbumDao.getInstance().getAll();
        ArrayList<Album> beatlesAlbums =
                AlbumDao.getInstance().getByArtistId(3);

        a1.setTitle(a1.getTitle() + "!");

        AlbumDao.getInstance().update(a1);

        Album newAlbum = new Album();
        newAlbum.setTitle("new awesome album");
        newAlbum.setArtistId(1);
        AlbumDao.getInstance().insert(newAlbum);

        AlbumDao.getInstance().deleteByKey(3);
    }
}
