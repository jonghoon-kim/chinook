package data;

import data.base.EntityDao;
import data.base.StringEntityDao;
import entities.Celebrity;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CelebrityDao extends StringEntityDao<Celebrity> {
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

    @Override
    protected String getByKeyQuery() {
        //language=TSQL
        return "select * from Celebrity where Name = ?";
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

    @Override
    protected String deleteByKeyQuery() {
        //language=TSQL
        return "delete Celebrity where Name = ?";
    }
}
