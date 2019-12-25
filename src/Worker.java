import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class Worker {
    @PrimaryKey(sequence = "id")
    private int id;
    private String name;
    private String surname;
    private String patronymic;


    Worker() {
    }

    public Worker(int id, String name, String surname, String patronymic) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }
}
