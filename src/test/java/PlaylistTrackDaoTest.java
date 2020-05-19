import data.PlaylistTrackDao;
import data.TrackDao;
import entities.PlaylistTrack;
import helpers.ConnectionString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlaylistPlaylistTrackDaoTest {
    @BeforeAll
    static void initializeConnectionString(){
        ConnectionString.getInstance().initialize("jdbc:sqlserver://192.168.1.5;database=Chinook;user=sa;password=3512");
    }

    @Test
    void insert() {
        final int playlistId = 17;
        int trackId = TrackDao.getInstance().getMaxTrackId();
        PlaylistTrackDao.getInstance().deleteByKey(playlistId, trackId);

        int oldCount = PlaylistTrackDao.getInstance().getCount();

        PlaylistTrack playlistTrack = new PlaylistTrack();
        playlistTrack.setPlaylistId(playlistId);
        playlistTrack.setTrackId(trackId);

        boolean inserted = PlaylistTrackDao.getInstance().insert(playlistTrack);

        assertTrue(inserted);

        int newCount = PlaylistTrackDao.getInstance().getCount();

        assertEquals(oldCount + 1, newCount);
    }

    @Test
    void deleteByKey() {
        final int playlistId = 17;
        int trackId = TrackDao.getInstance().getMaxTrackId();

        if (PlaylistTrackDao.getInstance().getByKey(playlistId, trackId) == null)
        {
            var entity = new PlaylistTrack();
            entity.setPlaylistId(playlistId);
            entity.setTrackId(trackId);
            PlaylistTrackDao.getInstance().insert(entity);
        }

        int oldCount = PlaylistTrackDao.getInstance().getCount();

        PlaylistTrackDao.getInstance().deleteByKey(playlistId, trackId);

        int newCount = PlaylistTrackDao.getInstance().getCount();

        assertEquals(oldCount - 1, newCount);
    }
}