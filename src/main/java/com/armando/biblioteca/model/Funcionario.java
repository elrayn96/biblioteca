package com.armando.biblioteca.model;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("FUNCIONARIO")
@Table(name = "")
public class Funcionario extends Usuario {

    @Column(name = "estado")
    private String estado;

    public Funcionario(){

    }
    
    public Funcionario(String id, String nome, int idade, String telefone, String endereco, String estado) {
        super(id, nome, idade, telefone, endereco);
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void getDetalhes() {
        System.out.println("============================================================");
        System.out.println("||                 Detalhes do Funcionario                ||");
        System.out.println("============================================================");
        super.getDetalhes();
        System.out.printf("|| Estado:              %s%n", this.estado);
        System.out.println("============================================================");
    }
}
