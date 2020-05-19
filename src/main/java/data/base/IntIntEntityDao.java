package data.base;

import data.ParameterSetter;
import entities.PlaylistTrack;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;

public abstract class IntIntEntityDao<E> extends DoubleKeyEntityDao<E, Integer, Integer> {
    @SneakyThrows
    public final E getByKey(int key1, int key2){
        //language=TSQL
        String query = getByKeyQuery();

        return getOne(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, key1);
                statement.setInt(2, key2);
            }
        });
    }


    @SneakyThrows
    public final boolean deleteByKey(int key1, int key2){
        //language=TSQL
        String query = deleteByKeyQuery();

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, key1);
                statement.setInt(2, key2);
            }
        });
    }
}
