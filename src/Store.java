import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityIndex;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

public class Store {
    private static File envHome = new File("./JEDB");
    private Environment envmnt;
    private EntityStore store;
    private DataAccessor da;

    public void setup() {
        EnvironmentConfig envConfig = new EnvironmentConfig();
        StoreConfig storeConfig = new StoreConfig();

        envConfig.setAllowCreate(true);
        storeConfig.setAllowCreate(true);
        envmnt = new Environment(envHome, envConfig);
        store = new EntityStore(envmnt, "EntityStore", storeConfig);
    }

    public void shutdown() throws DatabaseException {

        store.close();
        envmnt.close();
    }

    public void run() throws DatabaseException {
        setup();

        da = new DataAccessor(store);
        Tariff tarif = new Tariff(1, "name", 12);
        Order order1 = new Order(1, 1, tarif.getId(), 1);
        Order order2 = new Order(2, 1, tarif.getId(), 2);
        Order order3 = new Order(3, 12, tarif.getId(), 4);

        da.pIdxT.put(tarif);
        da.pIdx.put(order1);
        da.pIdx.put(order2);
        da.pIdx.put(order3);

        Order getOrder = getOrder(1);

//        getRelationOrder(1);

        updateOrderById(1, order1);
        Order id = getOrderById(1);
        shutdown();
    }

    public Order getOrder(int id) {
        return da.pIdx.get(id);
    }

    public Order getRelationOrder(int id) {
        Order order = da.pIdx.get(id);
        int tariffId = order.getTariffId();
        Tariff tariff = da.pIdxT.get(tariffId);
        order.setTariff(tariff);

        return order;
    }

    public boolean deleteOrder(int id) {
        return da.pIdx.delete(id);
    }

    public HashMap getOrders() {
        HashMap<Integer, Order> orders = new HashMap<Integer, Order>();

        for (Map.Entry<Integer, Order> entry : da.pIdx.map().entrySet()) {
            orders.put(entry.getValue().getId(), entry.getValue());
        }

        return orders;
    }

    public Order getOrderById(int searchId) {
        setup();

        EntityIndex<Integer, Order> pIndex = da.pIdx;

        EntityCursor<Order> org_cursor = pIndex.entities();

        for (Order entity : org_cursor) {

            int id = org_cursor.current().getId();
            if (searchId == id) {
                return entity;
            }
        }

        return new Order(1, 1, 1, 1);
    }

    public Order updateOrderById(int searchId, Order record) {
        setup();

        EntityIndex<Integer, Order> pIndex = da.pIdx;

        EntityCursor<Order> org_cursor = pIndex.entities();

        for (Order entity : org_cursor) {

            int id = org_cursor.current().getId();
            if (searchId == id) {
                org_cursor.update(record);
            }
        }

        return new Order(1, 1, 1, 1);
    }
}
