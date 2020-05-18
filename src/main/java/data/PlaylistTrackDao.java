package data;

import entities.PlaylistTrack;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlaylistTrackDao extends EntityDao {
    //region singleton
    private PlaylistTrackDao() {
    }
    
    private static PlaylistTrackDao _instance;
    
    public static PlaylistTrackDao getInstance(){
        if (_instance == null)
            _instance = new PlaylistTrackDao();
        
        return _instance;
    }
    //endregion

    @SneakyThrows
    public boolean insert(PlaylistTrack playListTrack){
        //language=TSQL
        String query = "insert into PlaylistTrack values (?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, playListTrack.getPlaylistId());
                statement.setInt(2, playListTrack.getTrackId());
            }
        });
    }

    @SneakyThrows
    public boolean deleteByKey(int playlistId, int trackId){
        //language=TSQL
        String query = "delete PlaylistTrack where playlistId = ? and trackId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, playlistId);
                statement.setInt(2, trackId);
            }
        });
    }

    @SneakyThrows
    protected ArrayList<PlaylistTrack> getMany(String query, ParameterSetter parameterSetter) {
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(query);
        if (parameterSetter != null)
            parameterSetter.setValue(statement);

        ResultSet result = statement.executeQuery();

        ArrayList<PlaylistTrack> playListTracks = new ArrayList<>();
        while (result.next()){
            PlaylistTrack playListTrack = readPlaylistTrack(result);
            playListTracks.add(playListTrack);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return playListTracks;
    }

    @SneakyThrows
    protected PlaylistTrack getOne(String query, ParameterSetter parameterSetter){
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(query);

        if (parameterSetter != null)
            parameterSetter.setValue(statement);

        ResultSet result = statement.executeQuery();

        ArrayList<PlaylistTrack> playListTracks = new ArrayList<>();
        while (result.next()){
            PlaylistTrack playListTrack = readPlaylistTrack(result);
            playListTracks.add(playListTrack);
        }

        result.close();
        statement.getConnection().close();
        statement.close();

        return playListTracks.size() == 0 ? null : playListTracks.get(0);
    }

    @SneakyThrows
    private PlaylistTrack readPlaylistTrack(ResultSet result) {
        PlaylistTrack playListTrack = new PlaylistTrack();

        playListTrack.setPlaylistId(result.getInt(1));
        playListTrack.setTrackId(result.getInt(2));

        return playListTrack;
    }

    @SneakyThrows
    public ArrayList<PlaylistTrack> getAll() {
        //language=TSQL
        String query = "select * from PlaylistTrack";

        return getMany(query, null);
    }

    @SneakyThrows
    public int getCount(){
        //language=TSQL
        String query = "select count(*) from PlaylistTrack";

        return getInt(query, null);
    }

    @SneakyThrows
    public PlaylistTrack getByKey(int playlistId, int trackId){
        //language=TSQL
        String query = "select * from PlaylistTrack where playlistId = ? and trackId = ?";

        return getOne(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, playlistId);
                statement.setInt(2, trackId);
            }
        });
    }

    @SneakyThrows
    public ArrayList<PlaylistTrack> getByPlaylistId(int playlistId) {
        //language=TSQL
        String query = "select * from PlaylistTrack where PlaylistId = ?";

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, playlistId);
            }
        });
    }

    @SneakyThrows
    public ArrayList<PlaylistTrack> getByTrackId(int trackId) {
        //language=TSQL
        String query = "select * from PlaylistTrack where trackId = ?";

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, trackId);
            }
        });
    }
}
