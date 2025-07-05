package com.armando.biblioteca.model;

import jakarta.persistence.*;



@Entity

@Table(name ="autor")

public class Autor {



    @Id

    @Column(length = 8, name = "codAutor")

    private String id;



    @Column(name = "nome", nullable = false)

    private String nome;



    public Autor() {

        

    }



    public Autor(String id, String nome) {

        this.nome = nome;

        this.id = id;

    }



    //Getters

    public String getId() {

        return this.id;

    }

    public String getNome() {

        return this.nome;

    }



    //Setters

    public void setId(String id) {

        this.id = id;

    }

    public void setNome(String nome) {

        this.nome = nome;

    }





    

    public void getDetalhes() {

        System.out.println("========================================================");

        System.out.println("                    Detalhes do Autor                   ");

        System.out.println("========================================================");

        System.out.printf("Código do Autor:      %s%n", this.getId());

        System.out.printf("Nome:                 %s%n", this.getNome());

        System.out.println("========================================================");

    }

}
