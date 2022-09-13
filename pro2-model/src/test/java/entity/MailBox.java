package entity;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

@Slf4j
public class MailBox {
    //小信箱集合
    private static Map<Integer, GuardedObject> map = new Hashtable<>();
    private static int id = 0;
    private synchronized static int generateId() {
        return id++;
    }
    public static GuardedObject generateGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        //添加到集合中
        map.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds() {
        return map.keySet();
    }

    public static GuardedObject getById(Integer id) {
        return map.get(id);
    }


}
