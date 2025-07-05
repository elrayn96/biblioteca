package com.armando.biblioteca.model;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ESTUDANTE")
@Table(name = "estudante")
public class Estudante extends Usuario {

    @Column(name = "curso")
    private String curso;

    public Estudante() {

    }

    public Estudante(String id, String nome, int idade, String telefone, String endereco, String curso) {
        super(id, nome, idade, telefone, endereco);
        this.curso = curso;
    }


    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public void getDetalhes() {
        System.out.println("============================================================");
        System.out.println("||                    Detalhes do Estudante               ||");
        System.out.println("============================================================");
        super.getDetalhes();
        System.out.printf("|| Curso:               %s%n", this.getCurso());
        System.out.println("============================================================");
    }

}
