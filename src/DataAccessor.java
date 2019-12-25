import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;

public class DataAccessor {
    public DataAccessor(EntityStore store) throws DatabaseException {

        pIdx = store.getPrimaryIndex(
                Integer.class, Order.class);

        pIdxW = store.getPrimaryIndex(
                Integer.class, Worker.class);

        pIdxT = store.getPrimaryIndex(
                Integer.class, Tariff.class);

        pIdxC = store.getPrimaryIndex(
                Integer.class, Comment.class);

        sIdxW = store.getSecondaryIndex(
                pIdx, Integer.class, "worker_id");

        sIdxT = store.getSecondaryIndex(
                pIdx, Integer.class, "tariff_id");

        sIdxC = store.getSecondaryIndex(
                pIdx, Integer.class, "comment_id");
    }

    PrimaryIndex<Integer, Order> pIdx;
    PrimaryIndex<Integer, Worker> pIdxW;
    PrimaryIndex<Integer, Tariff> pIdxT;
    PrimaryIndex<Integer, Comment> pIdxC;
    SecondaryIndex<Integer, Integer, Order> sIdxW;
    SecondaryIndex<Integer, Integer, Order> sIdxT;
    SecondaryIndex<Integer, Integer, Order> sIdxC;
}