package com.example.banco.dataaccess;

import com.example.banco.modelo.Clients;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ClientsDao implements Dao{
    private final SessionFactory sessionFactory;

    public ClientsDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Clients getById(int id) {
        try (Session session = sessionFactory.openSession()){
            return session.get(Clients.class, id);
        }
    }

    @Override
    public List<Clients> getAll() {
        try (Session session = sessionFactory.openSession()){
            List<Clients> clients = session.createQuery("SELECT c from Clients c", Clients.class).list();
            session.close();
            return clients;
        }
    }

    @Override
    public void save(Clients clients) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(clients);
            tx.commit();
        }
    }

    @Override
    public void update(Clients clients) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(clients);
            tx.commit();
        }
    }

    @Override
    public void delete(Clients clients) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(clients);
            tx.commit();
        }
    }
}
