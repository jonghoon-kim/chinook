import data.SummaryDao;
import entities.Summary;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SummaryDaoTest {

    @Test
    void getCount의_반환값은_0보다_커야_함() {
        int count = SummaryDao.getInstance().getCount();

        assertTrue(count > 0);
    }

    @Test
    void getByKey() {
        var at = LocalDateTime.parse("2009-01-01T01:00:00.000");
        Summary entity = SummaryDao.getInstance().getByKey(at);

        assertTrue(entity.getAt().equals(at));
    }

    @Test
    void getAllQuery() {
        ArrayList<Summary> entities = SummaryDao.getInstance().getAll();

        assertTrue(entities.size() > 0);
    }

    @Test
    void insert() {
        var now = LocalDateTime.now();

        // SummaryDao.getInstance().deleteByKey(now);

        int oldCount = SummaryDao.getInstance().getCount();

        Summary entity = new Summary();
        entity.setAt(now);
        entity.setCount(0);
        boolean inserted = SummaryDao.getInstance().insert(entity);

        assertTrue(inserted);

        int newCount = SummaryDao.getInstance().getCount();

        assertEquals(oldCount + 1, newCount);
    }

    @Test
    void update() {
        final LocalDateTime key = LocalDateTime.now();
        final int countForTest = new Random().nextInt();

        if (SummaryDao.getInstance().getByKey(key) == null) {
            Summary entity = new Summary();

            entity.setAt(key);
            entity.setCount(0);

            SummaryDao.getInstance().insert(entity);
        }

        Summary entity = SummaryDao.getInstance().getByKey(key);
        entity.setCount(countForTest);
        SummaryDao.getInstance().update(entity);

        entity = SummaryDao.getInstance().getByKey(key);
        assertEquals(countForTest, entity.getCount());
    }

    @Test
    void deleteByKey() {
        final LocalDateTime key = SummaryDao.getInstance().getMaxAt();

        if (SummaryDao.getInstance().getByKey(key) == null) {
            Summary entity = new Summary();

            entity.setAt(key);
            entity.setCount(0);

            SummaryDao.getInstance().insert(entity);
        }

        int oldCount = SummaryDao.getInstance().getCount();

        boolean deleted = SummaryDao.getInstance().deleteByKey(key);
        assertTrue(deleted);

        int newCount = SummaryDao.getInstance().getCount();

        assertEquals(oldCount - 1, newCount);
    }
}