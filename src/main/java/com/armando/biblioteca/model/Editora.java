package com.armando.biblioteca.model;
import jakarta.persistence.*;

@Entity
@Table(name ="editora")
public class Editora {

    @Id
    @Column(length = 8, name = "codEditora")
    private String id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "endereco")
    private String endereco;

    public Editora() {
        
    }
        
    

    public Editora(String id, String nome, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco; 
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setID(String id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }   

    public void getDetalhes() {
        System.out.println("====================================================================");
        System.out.println("||                         Detalhes da Editora                    ||");
        System.out.println("====================================================================");
        System.out.printf("|| ID - Editora:        %s%n", this.id);
        System.out.printf("|| Nome:                %s%n", this.nome);
        System.out.printf("|| Telefone:            %s%n", this.telefone);
        System.out.printf("|| Endereco:            %s%n", this.endereco);
        System.out.println("====================================================================");
    }
}
