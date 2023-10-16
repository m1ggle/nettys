package org.yahve.chat.protocol;

/**
 * @author m1ggle
 * @project nettys
 * @describe
 * @date 2023/10/16
 */
public interface Serializable {
    /**
     * 反序列化
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserializable(Class<T> clazz, byte[] bytes);

    /**
     * 序列化
     * @param object
     * @param <T>
     * @return
     */
    <T> byte[] serializable(T object);
}
