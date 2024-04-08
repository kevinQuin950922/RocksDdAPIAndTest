package com.example.scheduled.repositorio;

public interface KeyVauleRepository <K, V> {
    void save (K key, V value);
    V find(K value);
    void delete (K key);
}
