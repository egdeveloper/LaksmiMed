package org.egdeveloper.utils;

import java.util.HashMap;
import java.util.Map;

public class AIMap<K, V> extends HashMap<K, V>{

    private static Map<Class, IInstanceFactory> valueFactories_ = new HashMap<>();
    private Class<V> valueClazz_;

    /**
     * Создание объекта класса с регистрацией типа значения словаря
     * @param valueClazz Класс, хранящий информацию в режиме runtime о значение словаря
     */
    public AIMap(Class<V> valueClazz){
        super();
        this.valueClazz_ = valueClazz;
    }

    /**
     * Создание объекта класса с регистрацией типа значения словаря и назначением
     * по умолчанию вместимости словаря
     * @param valueClazz Класс, хранящий информацию в режиме runtime о значение словаря
     */
    public AIMap(Class<V> valueClazz, int capacity){
        super(capacity);
        this.valueClazz_ = valueClazz;
    }

    public AIMap(Class<V> valueClazz, int capacity, float loadFactor){
        super(capacity, loadFactor);
        this.valueClazz_ = valueClazz;
    }

    public AIMap(Class<V> valueClazz, Map<K, V> other){
        super(other);
        this.valueClazz_ = valueClazz;
    }

    /**
     * Зарегестрировать фабрику для автоматической инициализации значения
     * @param clazz Тип значения
     */
    public static void registerInstanceFactory(Class clazz, IInstanceFactory builder){
        valueFactories_.put(clazz, builder);
    }

    /**
     * Удалить фабрику для автоматической инициализации значения
     * @param clazz Тип значения
     */
    public static void removeInstanceFactory(Class clazz){
        valueFactories_.remove(clazz);
    }

    /**
     * Получить значение по ключу
     * @param key Ключ
     * @return Значение
     */
    @Override
    public V get(Object key) {
        if(!containsKey(key)){
            super.put((K) key, (V) valueFactories_.get(valueClazz_).produce());
        }
        return super.get(key);
    }
}
