package com.example.banco.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error en la inicialización.  " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSessionFactory() {
        return sessionFactory.getCurrentSession()   ;
    }
}