package org.yahve.chat.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

public enum Algotithm implements Serializable {
    Java {
        @Override
        public <T> T deserializable(Class<T> clazz, byte[] bytes) {
           try {
               ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
               return  (T) ois.readObject();
           }catch (Exception e){
               throw new RuntimeException("反序列化失败", e);
           }
        }

        @Override
        public <T> byte[] serializable(T object) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream(); // 访问数组
                ObjectOutputStream oos = new ObjectOutputStream(bos);    // 用对象流 包装
                oos.writeObject(object);

                return bos.toByteArray();
            }catch (Exception e){
                throw new RuntimeException("序列化失败", e);
            }

        }
    },
    JSON {
        @Override
        public <T> T deserializable(Class<T> clazz, byte[] bytes) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodecAdapter()).create();
            String fromString = new String(bytes, StandardCharsets.UTF_8);
            return gson.fromJson(fromString,clazz);
        }

        @Override
        public <T> byte[] serializable(T obj) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodecAdapter()).create();
            String toJson = gson.toJson(obj);
            return toJson.getBytes(StandardCharsets.UTF_8);
        }
    }
}
