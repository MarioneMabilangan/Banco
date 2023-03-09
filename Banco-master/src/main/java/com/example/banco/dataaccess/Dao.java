package com.example.banco.dataaccess;

import com.example.banco.modelo.Clients;

import java.util.List;

public interface Dao {
    Clients getById(int id);
    List<Clients> getAll();
    void save(Clients clients);
    void update(Clients clients);
    void delete(Clients clients);
}
