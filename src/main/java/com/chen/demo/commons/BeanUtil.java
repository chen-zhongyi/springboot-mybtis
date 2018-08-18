package com.chen.demo.commons;

import com.chen.demo.enums.UserType;
import com.chen.demo.models.User;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil {

    public static Map<String, Object> objectToMap(Object obj) throws Exception{
        Map<String, Object> map = new HashMap<>();
        if (obj == null) return map;
        Class c = obj.getClass();
        List<Class> clazzs = new ArrayList<>();
        while (true) {
            clazzs.add(c);
            c = c.getSuperclass();
            if (c == null || c == Object.class) break;
        }
        for (Class clazz : clazzs) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isFinal(mod) || Modifier.isStatic(mod)) continue;
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        }
        return map;
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> clazz) throws Exception{
        if(map == null || map.size() <= 0)  return null;
        Object obj = clazz.newInstance();
        List<Class> clazzs = new ArrayList<>();
        while(true){
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
            if(clazz == null || clazz == Object.class)  break;
        }
        for (Class c : clazzs) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isFinal(mod) || Modifier.isStatic(mod)) continue;
                field.setAccessible(true);
                if(field != null) {
                    field.setAccessible(true);
                    field.set(obj, map.get(field.getName()));
                }
            }
        }
        return obj;
    }

    public static void main(String[] args) throws Exception{
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPhone("18758324519");
        user.setType(UserType.MANAGER);
        Map<String, Object> map = objectToMap(user);
        for(Map.Entry<String, Object> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
            System.out.println(entry.getValue() == null ? "null" : entry.getValue().getClass().getName());
            System.out.println();
        }
        User u = (User) mapToObject(map, User.class);
        System.out.println(new Gson().toJson(u));
    }
}
