package com.example.banco;

import com.example.banco.hibernate.HibernateUtil;
import com.example.banco.modelo.Clients;
import com.example.banco.modelo.Comptes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "CreateCustomer", value = "/create")
public class CreateCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros de la petición
        String dni = request.getParameter("id_fiscal");
        String nombre = request.getParameter("nombre_cliente");
        String correo = request.getParameter("email_cliente");
        String pais = request.getParameter("pais");
        int ingresoInicial = Integer.parseInt(request.getParameter("ingreso_inicial"));

        // Crear una instancia de EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        // Crear una instancia de EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            // Comenzar una transacción
            em.getTransaction().begin();

            // Crear una instancia de Clientes
            Clients cliente = new Clients(dni, nombre, correo, pais);

            // Crear una instancia de Comptes
            Comptes cuenta = new Comptes(ingresoInicial, cliente);

            // Persistir la cuenta
            em.persist(cuenta);

            // Agregar la cuenta al cliente
            cliente.getCompteByIdCompte().add(cuenta);

            // Persistir el cliente
            em.persist(cliente);

            // Confirmar la transacción
            em.getTransaction().commit();

            // Enviar una respuesta al cliente
            PrintWriter out = response.getWriter();
            out.println("La creación ha ido bien");
        } catch (Exception e) {
            // Si ocurre un error, hacer un rollback de la transacción
            em.getTransaction().rollback();

            // Enviar una respuesta al cliente con el mensaje de error y la traza de la excepción
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.println("Error en la creación: " + e.getMessage());
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}