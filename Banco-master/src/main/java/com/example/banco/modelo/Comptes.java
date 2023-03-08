package com.example.banco.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Comptes")
public class Comptes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta")
    private String cuenta;

    @Column(name = "ingresoInicial")
    private int ingresoInicial;

    @ManyToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Clients clientsByIdClients;

    public Comptes(int ingresoInicial, Clients clientsByIdClients) {
        this.ingresoInicial = ingresoInicial;
        this.clientsByIdClients = clientsByIdClients;
    }

    public Comptes() {

    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public int getIngresoInicial() {
        return ingresoInicial;
    }

    public void setIngresoInicial(int ingresoInicial) {
        this.ingresoInicial = ingresoInicial;
    }

    public Clients getClientsByIdClients() {
        return clientsByIdClients;
    }

    public void setClientsByIdClients(Clients clientsByIdClients) {
        this.clientsByIdClients = clientsByIdClients;
    }
}
