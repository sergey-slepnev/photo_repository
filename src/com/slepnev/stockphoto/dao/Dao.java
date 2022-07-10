package com.slepnev.stockphoto.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    E save(E photo);

    List<E> findAll();

    Optional<E> findById(K id);

    void update(E photo);

    boolean delete(K id);

}
