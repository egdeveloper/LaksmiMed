package org.egdeveloper.utils;

@FunctionalInterface
public interface IInstanceFactory<T> {
    T produce();
}
