package data;

import entities.PlaylistTrack;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlaylistTrackDao extends EntityDao<PlaylistTrack> {
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
    @Override
    protected PlaylistTrack readEntity(ResultSet result) {
        PlaylistTrack playListTrack = new PlaylistTrack();

        playListTrack.setPlaylistId(result.getInt(1));
        playListTrack.setTrackId(result.getInt(2));

        return playListTrack;
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from PlaylistTrack";
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

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from PlaylistTrack";
    }

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
