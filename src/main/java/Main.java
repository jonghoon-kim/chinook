import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
//        ArrayList<Album> albums = getAll();
        ArrayList<Album> albums = getByArtistId(3);

        for (Album album : albums)
            System.out.println(album);

//        Album a1 = AlbumDao.getInstance().getByKey(1);
//        ArrayList<Album> allAlbums = AlbumDao.getInstance().getAll();
//        ArrayList<Album> beatlesAlbums =
//                AlbumDao.getInstance().getByArtistId(3);
//
//        a1.setTitle(a1.getTitle() + "!");
//
//        AlbumDao.getInstance().update(a1);
//
//        Album newAlbum = new Album();
//        newAlbum.setTitle("new awesome album");
//        newAlbum.setArtistId(1);
//        AlbumDao.getInstance().insert(newAlbum);
//
//        AlbumDao.getInstance().deleteByKey(3);
    }

    @SneakyThrows
    private static ArrayList<Album> getByArtistId(int artistId) {
        String connString =
                "jdbc:sqlserver://192.168.1.33;database=Chinook;user=sa;password=3512";
        Connection connection = DriverManager.getConnection(connString);

        String query = "select * from Album where ArtistId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, artistId);

        ResultSet result = statement.executeQuery();

        ArrayList<Album> albums = new ArrayList<>();
        while (result.next()){
            Album album = new Album();
            album.setAlbumId(result.getInt(1));
            album.setTitle(result.getString(2));
            album.setArtistId(result.getInt(3));

            albums.add(album);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return albums;
    }

    @SneakyThrows
    private static ArrayList<Album> getAll() {
        String connString =
                "jdbc:sqlserver://192.168.1.33;database=Chinook;user=sa;password=3512";
        Connection connection = DriverManager.getConnection(connString);

        String query = "select * from Album";
        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet result = statement.executeQuery();

        ArrayList<Album> albums = new ArrayList<>();
        while (result.next()){
            Album album = new Album();
            album.setAlbumId(result.getInt(1));
            album.setTitle(result.getString(2));
            album.setArtistId(result.getInt(3));

            albums.add(album);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return albums;
    }
}
