package data;

import data.base.EntityDao;
import entities.Celebrity;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CelebrityDao extends EntityDao<Celebrity> {
    //region singleton
    private CelebrityDao() {
    }

    private static CelebrityDao _instance;

    public static CelebrityDao getInstance() {
        if (_instance == null)
            _instance = new CelebrityDao();

        return _instance;
    }
    //endregion

    @SneakyThrows
    @Override
    protected Celebrity readEntity(ResultSet result) {
        Celebrity entity = new Celebrity();

        entity.setName(result.getString(1));
        entity.setScore(result.getBigDecimal(2));

        return entity;
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from Celebrity";
    }

    @SneakyThrows
    public Celebrity getByKey(String key){
        //language=TSQL
        String query = "select * from Celebrity where Name = ?";

        return getOne(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, key);
            }
        });
    }

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from Celebrity";
    }

    @Override
    public boolean insert(Celebrity entity) {
        //language=TSQL
        String query = "insert into Celebrity values (?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, entity.getName());
                statement.setBigDecimal(2, entity.getScore());
            }
        });
    }

    @Override
    public boolean update(Celebrity entity) {
        //language=TSQL
        String query = "update Celebrity set Score = ? where Name = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setBigDecimal(1, entity.getScore());
                statement.setString(2, entity.getName());
            }
        });
    }

    @SneakyThrows
    public boolean deleteByKey(String key){
        //language=TSQL
        String query = "delete Celebrity where Name = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, key);
            }
        });
    }
}
