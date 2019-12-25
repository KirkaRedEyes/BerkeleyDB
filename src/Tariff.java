import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class Tariff {
    @PrimaryKey(sequence="ID")
    private int id;
    private String name;
    private int price;

    private Tariff() {};
    public Tariff(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
}
