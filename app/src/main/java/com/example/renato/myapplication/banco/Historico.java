package com.example.renato.myapplication.banco;

/**
 * Created by Renato on 20/05/2018.
 */

 public class Historico {
    private int id;
    private String solicitante;
    private String horario;
    private int numeroCaixa;

    Historico(int id, String solicitante, String horario, int numeroCaixa) {
        this.id = id;
        this.solicitante = solicitante;
        this.horario = horario;
        this.numeroCaixa = numeroCaixa;

    }

    public int getId(){ return this.id; }
    public String getSolicitante(){ return this.solicitante; }
    public String getHorario(){ return this.horario; }
    public int getNumeroCaixa(){ return this.numeroCaixa; }


    @Override
    public boolean equals(Object o){
        return this.id == ((Historico)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }


}
