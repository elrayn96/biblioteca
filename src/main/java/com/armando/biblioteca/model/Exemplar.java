package com.armando.biblioteca.model;
import jakarta.persistence.*;

@Entity
@Table(name ="exemplar")
public class Exemplar {

    @Id
    @Column(name = "codExemplar", length = 8)
    String id;

    @Column(name = "estado", length = 12,nullable = false)
    String estado;

    @ManyToOne
    @JoinColumn(name = "codLivro", nullable = false)
    Livro livro;

    //Contrutores
    public Exemplar() {

    }

    public Exemplar(String id, Livro livro) {
        this.id = id;
        this.estado = "Disponivel";
        this.livro = livro;
    }
    
    public Exemplar(String id, Livro livro, String estado) {
        this.id = id;
        this.estado = estado;
        this.livro = livro;
    }
 
    
    public String getId() {
        return this.id;
    }

    public String getEstado() {
        return this.estado;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    

    public void getDetalhes() {
        System.out.println("============================================================");
        System.out.println("||                  Detalhes do Exemplar                  ||");
        System.out.println("============================================================");
        System.out.printf("|| ID - Exemplar:       %s%n", this.id);
        System.out.printf("|| ID - Livro:          %s%n", this.livro.getId());
        System.out.printf("|| Nome do Livro:       %s%n", this.livro.getNome());
        System.out.printf("|| Estado:              %s%n", this.estado);
        System.out.println("============================================================");
    }
}
