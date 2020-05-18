package data;

import lombok.SneakyThrows;

import java.sql.PreparedStatement;

public interface ParameterSetter {
    void setValue(PreparedStatement statement);
}
