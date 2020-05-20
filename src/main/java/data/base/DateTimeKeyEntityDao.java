package data.base;

import data.ParameterSetter;
import entities.base.Entity;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class DateTimeKeyEntityDao<E extends Entity> extends SingleKeyEntityDao<E, LocalDateTime> {
    @SneakyThrows
    public final E getByKey(LocalDateTime key){
        //language=TSQL
        String query = getByKeyQuery();

        return getOne(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setTimestamp(1, Timestamp.valueOf(key));
            }
        });
    }

    @SneakyThrows
    public final boolean deleteByKey(LocalDateTime key){
        //language=TSQL
        String query = deleteByKeyQuery();

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setTimestamp(1, Timestamp.valueOf(key));
            }
        });
    }
}
