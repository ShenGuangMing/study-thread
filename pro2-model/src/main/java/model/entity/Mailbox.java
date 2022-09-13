package model.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

@Slf4j
//理解成大的邮箱，收信的是邮箱的一个格子
public class Mailbox {
    private static int id = 0;
    private static Map<Integer, GuardedObject> map = new Hashtable<>();
    //生成id
    public synchronized static int generateId() {
        return id++;
    }

    //生成一个GuardedObject
    public static GuardedObject generateGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        //添加近map
        map.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds() {
        return map.keySet();
    }
    public static GuardedObject getById(Integer id) {
        return map.remove(id);
    }
}
