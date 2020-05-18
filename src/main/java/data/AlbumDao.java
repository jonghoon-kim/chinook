package data;

import entities.Album;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AlbumDao {
    //region singleton
    private AlbumDao() {
    }

    private static AlbumDao _instance;

    public static AlbumDao getInstance(){
        if (_instance == null)
            _instance = new AlbumDao();

        return _instance;
    }

    //endregion

    //region helper methods
    @SneakyThrows
    private Album readAlbum(ResultSet result) {
        Album album = new Album();

        album.setAlbumId(result.getInt(1));
        album.setTitle(result.getString(2));
        album.setArtistId(result.getInt(3));

        return album;
    }

    @SneakyThrows
    private Connection getConnection() {
        String connString =
                "jdbc:sqlserver://192.168.1.5;database=Chinook;user=sa;password=3512";
        return DriverManager.getConnection(connString);
    }

    @SneakyThrows
    private int getInt(String query, ParameterSetter parameterSetter){
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(query);

        if (parameterSetter != null)
            parameterSetter.setValue(statement);

        ResultSet result = statement.executeQuery();

        int count = 0;
        while (result.next()){
            count = result.getInt(1);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return count;
    }

    @SneakyThrows
    private Album getOne(String query, ParameterSetter parameterSetter){
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(query);

        if (parameterSetter != null)
            parameterSetter.setValue(statement);

        ResultSet result = statement.executeQuery();

        ArrayList<Album> albums = new ArrayList<>();
        while (result.next()){
            Album album = readAlbum(result);
            albums.add(album);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return albums.size() == 0 ? null : albums.get(0);
    }

    @SneakyThrows
    private ArrayList<Album> getMany(String query, ParameterSetter parameterSetter) {
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(query);
        if (parameterSetter != null)
            parameterSetter.setValue(statement);

        ResultSet result = statement.executeQuery();

        ArrayList<Album> albums = new ArrayList<>();
        while (result.next()){
            Album album = readAlbum(result);
            albums.add(album);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return albums;
    }

    @SneakyThrows
    private boolean execute(String query, ParameterSetter parameterSetter){
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(query);
        if (parameterSetter != null)
            parameterSetter.setValue(statement);

        int rowCount = statement.executeUpdate();

        statement.getConnection().close();
        statement.close();

        return rowCount == 1;
    }
    //endregion

    @SneakyThrows
    public int getCount(){
        //language=TSQL
        String query = "select count(*) from Album";

        return getInt(query, null);
    }

    @SneakyThrows
    public Album getByKey(int key){
        //language=TSQL
        String query = "select * from Album where AlbumId = ?";

        return getOne(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, key);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Album> getByArtistId(int artistId) {
        //language=TSQL
        String query = "select * from Album where ArtistId = ?";

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, artistId);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Album> getAll() {
        //language=TSQL
        String query = "select * from Album";

        return getMany(query, null);
    }

    @SneakyThrows
    public int getMaxAlbumId() {
        //language=TSQL
        String query = "select top 1 AlbumId from Album order by AlbumId desc ";

        return getInt(query, null);
    }

    @SneakyThrows
    public boolean insert(Album album){
        //language=TSQL
        String query = "insert into Album values (?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, album.getTitle());
                statement.setInt(2, album.getArtistId());
            }
        });
    }

    @SneakyThrows
    public boolean update(Album album){
        //language=TSQL
        String query = "update Album set Title = ?, ArtistId = ? where AlbumId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, album.getTitle());
                statement.setInt(2, album.getArtistId());
                statement.setInt(3, album.getAlbumId());
            }
        });
    }

    @SneakyThrows
    public boolean deleteByKey(int key){
        //language=TSQL
        String query = "delete Album where AlbumId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, key);
            }
        });
    }
}
