package data;


import data.base.EntityDao;
import data.base.IntEntityDao;
import entities.Artist;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArtistDao extends IntEntityDao<Artist> {
    //region singleton
    private ArtistDao() {
    }

    private static ArtistDao _instance;

    public static ArtistDao getInstance(){
        if (_instance == null)
            _instance = new ArtistDao();

        return _instance;
    }
    //endregion

    @SneakyThrows
    @Override
    protected Artist readEntity(ResultSet result) {
        Artist entity = new Artist();

        entity.setArtistId(result.getInt(1));
        entity.setName(result.getString(2));

        return entity;
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from Artist";
    }

    @Override
    protected String getByKeyQuery() {
        //language=TSQL
        return "select * from artist where artistId = ?";
    }

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from Artist";
    }

    @SneakyThrows
    public boolean insert(Artist artist){
        //language=TSQL
        String query = "insert into artist values (?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, artist.getName());
            }
        });
    }

    @SneakyThrows
    public boolean update(Artist artist){
        //language=TSQL
        String query = "update Artist set name = ? where ArtistId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, artist.getName());
                statement.setInt(2, artist.getArtistId());
            }
        });
    }

    @Override
    protected String deleteByKeyQuery() {
        //language=TSQL
        return "delete Artist where ArtistId = ?";
    }

    @SneakyThrows
    public int getMaxArtistId() {
        //language=TSQL
        String query = "select top 1 artistId from artist order by artistId desc ";

        return getInt(query, null);
    }
}
