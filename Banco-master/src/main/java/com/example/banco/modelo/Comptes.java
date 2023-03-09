package com.example.banco.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Comptes")
public class Comptes {
    @Id
    @Column(name = "cuenta")
    private String cuenta;

    @Column(name = "ingresoInicial")
    private int ingresoInicial;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "dni")
    private Clients clientsByIdClients;

    public Comptes(String cuenta, int ingresoInicial, Clients clientsByIdClients) {
        this.cuenta = cuenta;
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
