
package com.armando.biblioteca.model;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    
    @Id
    @Column(name = "codUsuario", length = 8)
    protected String id;
     
    @Column(name = "nome", nullable = false)
    protected String nome;

    @Column(name = "idade")
    protected int idade;

    @Column(name = "telelfone")
    protected String telefone;

    @Column(name = "endereco")
    protected String endereco;

    public Usuario() {

    }

    public Usuario(String id, String nome, int idade, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
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

    public int getIdade() {
        return this.idade;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdade(int idade) {
         this.idade = idade;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void getDetalhes() {
        System.out.printf("|| ID:                  %s%n", this.getId());
        System.out.printf("|| Nome:                %s%n", this.getNome());
        System.out.printf("|| Telefone:            %s%n", this.getTelefone());
        System.out.printf("|| Endereço:            %s%n", this.getEndereco());
    }
}

