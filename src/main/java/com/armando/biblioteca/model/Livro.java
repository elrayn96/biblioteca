package com.armando.biblioteca.model;
import jakarta.persistence.*;

@Entity
@Table(name ="livro")
public class Livro {

    @Id
    @Column(name = "codLivro", length = 8)
    String id;

    @Column(name = "nome", nullable = false)
    String nome;

    @ManyToOne
    @JoinColumn(name = "codArea", nullable = false)
    AreaConhecimento area;

    @ManyToOne
    @JoinColumn(name = "codEditora", nullable = false)
    Editora editora;

    @ManyToMany
    @JoinTable(
        name = "autorlivro",
        joinColumns = @JoinColumn(name = "codLivro"),
        inverseJoinColumns = @JoinColumn(name = "codAutor")
    )
    Autor autor[];

    public Livro() {
        
    }

    public Livro(String id, String nome, AreaConhecimento area, Editora editora, Autor autor[]) {
        this.id = id;
        this.nome = nome;  
        this.area = area;
        this.editora = editora;
        this.autor = autor;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public AreaConhecimento getArea() {
        return this.area;
    }

    public Editora getEditora() {
        return this.editora;
    }

    public Autor[] getAutor() {
        return this.autor;
    }

    public void setID(String id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setArea(AreaConhecimento area) {
        this.area = area;
    } 
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    public void setAutor(Autor autor[]) {
        this.autor = autor;
    }

    public void getDetalhes() {
        System.out.println("============================================================================");
        System.out.println("||                             Detalhes do Livro                          ||");
        System.out.println("============================================================================");
        System.out.printf("|| ID - Livro:          %s%n", this.getId());
        System.out.printf("|| Nome do Livro:       %s%n", this.getNome());
        System.out.printf("|| Area de Conhecimento:%s%n", this.area.getNome());
        System.out.printf("|| Editora:             %s%n", this.editora.getNome());
        System.out.println("============================================================================");
        System.out.println("||                                 Autor(es)                              ||");
        System.out.println("============================================================================");
        for (int i = 0; i < this.autor.length; i++) {
            System.out.println("|| " + (i+1) + ". " + this.autor[i].getNome() + " - " + this.autor[i].getId());
        }
        System.out.println("============================================================================");
        
    }
}
