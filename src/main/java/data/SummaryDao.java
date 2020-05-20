package data;

import data.base.DateTimeKeyEntityDao;
import entities.Summary;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SummaryDao extends DateTimeKeyEntityDao<Summary> {
    //region singleton
    private SummaryDao() {
    }

    private static SummaryDao _instance;

    public static SummaryDao getInstance() {
        if (_instance == null)
            _instance = new SummaryDao();

        return _instance;
    }
    //endregion

    @SneakyThrows
    @Override
    protected Summary readEntity(ResultSet result) {
        var entity = new Summary();

        entity.setAt(result.getTimestamp(1).toLocalDateTime());
        entity.setCount(result.getInt(2));

        return entity;
    }

    @Override
    protected String getByKeyQuery() {
        //language=TSQL
        return "select * from Summary where [At] = ?";
    }

    @Override
    protected String deleteByKeyQuery() {
        //language=TSQL
        return "delete Summary where [At] = ?";
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from Summary";
    }

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from Summary";
    }

    @Override
    public boolean insert(Summary entity) {
        //language=TSQL
        var query = "insert into Summary (At, Count) values (?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setTimestamp(1, Timestamp.valueOf(entity.getAt()));
                statement.setInt(2, entity.getCount());
            }
        });
    }

    @Override
    public boolean update(Summary entity) {
        //language=TSQL
        var query = "update Summary set Count = ? where At = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, entity.getCount());
                statement.setTimestamp(2, Timestamp.valueOf(entity.getAt()));
            }
        });
    }

    public LocalDateTime getMaxAt(){
        //language=TSQL
        String query = "select top 1 At from Summary order by At desc ";

        return getDateTime(query, null);
    }
}
