package com.armando.biblioteca.model;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("DOCENTE")
@Table(name = "docente")
public class Docente extends Usuario {

    @Column(name = "departamento")
    private String departamento;

    public Docente() {

    }

    public Docente(String id, String nome, int idade, String telefone, String endereco, String departamento) {
        super(id, nome, idade, telefone, endereco);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public void getDetalhes() {
        System.out.println("==================================================");
        System.out.println("                 Detalhes do Docente              ");
        System.out.println("==================================================");
        super.getDetalhes();
        System.out.printf("Departamento:        %s%n", this.departamento);
        System.out.println("==================================================");
    }
}


