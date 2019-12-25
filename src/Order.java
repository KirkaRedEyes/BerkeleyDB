import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class Order {
    @PrimaryKey(sequence = "ID")
    private int id;
    @SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = Worker.class, name = "worker_id")
    private int worker_id;
    @SecondaryKey(relate = Relationship.MANY_TO_MANY, relatedEntity = Worker.class, name = "tariff_id")
    private int tariff_id;
    @SecondaryKey(relate = Relationship.ONE_TO_ONE, relatedEntity = Worker.class, name = "comment_id")
    private int comment_id;

    private Order() {
    }

    public Order(int id, int worker_id, int tariff_id, int comment_id) {
        this.id = id;
        this.worker_id = worker_id;
        this.tariff_id = tariff_id;
        this.comment_id = comment_id;
    }

    public int getId() {
        return id;
    }

    public int getWorkerId() {
        return worker_id;
    }

    public int getTariffId() {
        return tariff_id;
    }

    public int getCommentId() {
        return comment_id;
    }

    public void setTariff(Tariff tariffObject) {
        Tariff tariff = tariffObject;
    }

    public void setTariffId(int tariff_id) {
        this.tariff_id = tariff_id;
    }
}
