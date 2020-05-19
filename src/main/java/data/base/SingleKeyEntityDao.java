package data.base;

import entities.base.Entity;

import java.sql.ResultSet;

public abstract class SingleKeyEntityDao<E extends Entity, K> extends EntityDao<E> {
    protected abstract String getByKeyQuery();

    protected abstract String deleteByKeyQuery();
}
