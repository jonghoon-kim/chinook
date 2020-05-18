package data;

import entities.Track;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TrackDao extends EntityDao {
    //region singleton
    private TrackDao() {
    }

    private static TrackDao _instance;

    public static TrackDao getInstance() {
        if (_instance == null)
            _instance = new TrackDao();

        return _instance;
    }
    //endregion

    @SneakyThrows
    public boolean insert(Track track){
        //language=TSQL
        String query = "insert into Track values (?, ?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, track.getName());
                statement.setInt(2, track.getAlbumId());
                statement.setInt(3, track.getGenreId());
                statement.setDouble(4, track.getUnitPrice());
            }
        });
    }

    @SneakyThrows
    public boolean update(Track track){
        //language=TSQL
        String query = "update Track set name = ?, albumId = ?, genreId = ?, unitPrice = ? where trackId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, track.getName());
                statement.setInt(2, track.getAlbumId());
                statement.setInt(3, track.getGenreId());
                statement.setDouble(4, track.getUnitPrice());
                statement.setInt(5, track.getTrackId());
            }
        });
    }

    @SneakyThrows
    public boolean deleteByKey(int key){
        //language=TSQL
        String query = "delete track where trackId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, key);
            }
        });
    }

    @SneakyThrows
    protected ArrayList<Track> getMany(String query, ParameterSetter parameterSetter) {
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(query);
        if (parameterSetter != null)
            parameterSetter.setValue(statement);

        ResultSet result = statement.executeQuery();

        ArrayList<Track> tracks = new ArrayList<>();
        while (result.next()){
            Track track = readTrack(result);
            tracks.add(track);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return tracks;
    }

    @SneakyThrows
    protected Track getOne(String query, ParameterSetter parameterSetter){
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(query);

        if (parameterSetter != null)
            parameterSetter.setValue(statement);

        ResultSet result = statement.executeQuery();

        ArrayList<Track> tracks = new ArrayList<>();
        while (result.next()){
            Track track = readTrack(result);
            tracks.add(track);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return tracks.size() == 0 ? null : tracks.get(0);
    }

    @SneakyThrows
    private Track readTrack(ResultSet result) {
        Track Track = new Track();

        Track.setTrackId(result.getInt(1));
        Track.setName(result.getString(2));
        Track.setAlbumId(result.getInt(3));
        Track.setGenreId(result.getInt(4));
        Track.setUnitPrice(result.getFloat(5));

        return Track;
    }

    @SneakyThrows
    public ArrayList<Track> getAll() {
        //language=TSQL
        String query = "select * from Track";

        return getMany(query, null);
    }

    @SneakyThrows
    public int getCount(){
        //language=TSQL
        String query = "select count(*) from Track";

        return getInt(query, null);
    }

    @SneakyThrows
    public int getMaxTrackId() {
        //language=TSQL
        String query = "select top 1 trackId from track order by trackId desc ";

        return getInt(query, null);
    }

    @SneakyThrows
    public Track getByKey(int key){
        //language=TSQL
        String query = "select * from track where trackId = ?";

        return getOne(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, key);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Track> getByAlbumId(int albumId) {
        //language=TSQL
        String query = "select * from Track where albumId = ?";

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, albumId);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Track> getByGenreId(int genreId) {
        //language=TSQL
        String query = "select * from Track where genreId = ?";

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, genreId);
            }
        });
    }
}
