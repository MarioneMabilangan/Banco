package com.example.banco.servlet;

import com.example.banco.dataaccess.ClientsDao;
import com.example.banco.dataaccess.Dao;
import com.example.banco.hibernate.HibernateUtil;
import com.example.banco.modelo.Clients;
import com.example.banco.modelo.Comptes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "CreateCustomer", value = "/create.do")
public class CreateCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("submit_action");
        if (action.equals("crearcliente")){
            int condicion = 0;
            String dni = request.getParameter("id_fiscal");
            String nombre = request.getParameter("nombre_cliente");
            String email = request.getParameter("email_cliente");
            String pais = request.getParameter("pais");
            String cuenta = request.getParameter("cuenta");
            String ingresoString = request.getParameter("ingreso_inicial");
            int ingreso = Integer.parseInt(ingresoString);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager em = emf.createEntityManager();

            Clients clienteExistente = null;
            EntityTransaction transaction = null;

            try {
                transaction = em.getTransaction();
                transaction.begin();

                clienteExistente = em.find(Clients.class, dni);

                if (clienteExistente == null){
                    clienteExistente = new Clients(dni, nombre, email, pais);
                    condicion++;
                }

                Comptes comptes = new Comptes(cuenta, ingreso, clienteExistente);
                clienteExistente.getCompteByIdCompte().add(comptes);
                em.persist(clienteExistente);
                transaction.commit();
                System.out.println(clienteExistente.toString());
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                em.close();
            }

            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            switch (condicion){
                case 0:
                    out.println("Añadido cuenta bancaria a usuario " + nombre + " con éxito");
                    System.out.println("se ha creado");
                    break;
                case 1:
                    out.println("Creación usuario " + nombre + " con éxito");
                    System.out.println("se ha creado");
                    break;
            }
            out.close();
        }
        else if (action.equals("mostrarcliente")){
            List<Clients> clients;
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            Dao dao = new ClientsDao(HibernateUtil.getSessionFactory().getSessionFactory());

            clients = dao.getAll();

            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Clientes y sus cuentas</h1>");
            out.println("<table>");
            out.println("<tr><th>Nombre</th><th>DNI</th><th>Cuenta</th><th>Saldo</th></tr>");

            for(Clients client : clients) {
                out.println("<tr>");
                out.println("<td>" + client.getNombre() + "</td>");
                out.println("<td>" + client.getDni() + "</td>");

                List<Comptes> comptes = client.getCompteByIdCompte();

                for (Comptes compte : comptes) {
                    out.println("<td>" + compte.getCuenta() + "</td>");
                    out.println("<td>" + compte.getIngresoInicial() + "</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</html>");
            out.println("</body>");
        }
    }
}