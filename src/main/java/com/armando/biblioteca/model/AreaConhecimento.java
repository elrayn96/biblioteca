package com.armando.biblioteca.model;
import jakarta.persistence.*;

@Entity
@Table(name = "areaconhecimento")
public class AreaConhecimento {

    @Id
    @Column(length = 8, name = "codArea")
    String id;

    @Column(name = "nome", nullable = false)
    String nome;
    @Column(name = "descricao")
    String descricao;

    public AreaConhecimento() {
        
    }


    public AreaConhecimento(String id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void getDetalhes() {
        System.out.println("=========================================================================================================");
        System.out.printf("ID - Area de Conhecimento:       %s%n", this.id);
        System.out.printf("Nome da Area de Conhecimento:    %s%n", this.nome);
        System.out.println("Descricao: \n  " + this.descricao);
        System.out.println("==========================================================================================================");
    }

}
