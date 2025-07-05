package com.armando.biblioteca.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name ="emprestimo")
public class Emprestimo {

    @Id
    @Column(length = 8, name = "codEmprestimo")
    private String id;

    @ManyToOne
    @JoinColumn(name = "codUsuario", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "emprestimoexemplar",
        joinColumns = @JoinColumn(name = "codEmprestimo"),
        inverseJoinColumns = @JoinColumn(name = "codExemplar")
    )
    private Exemplar exemplar[];

    @Temporal(TemporalType.DATE)
    @Column(name = "dataEmprestimo")
    private Date dataEmprestimo;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataDevolucaoPrevista")
    private Date dataDevolucaoPrevista;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataDevolucaoReal")
    private Date dataDevolucaoReal;

    @Column(name = "estado")
    private String estado;
    
    //Construtores

    public Emprestimo() {

    }

    public Emprestimo(String id, Usuario usuario, Exemplar exemplar[]) {
        this.id = id;
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = new Date();
        this.dataDevolucaoPrevista = new Date(this.dataEmprestimo.getTime() + 7L * 24 * 60 * 60 * 1000);
        this.estado = "Pendente";
    }

    public Emprestimo(String id, Usuario usuario, Exemplar exemplar[], Date dataEmprestimo, Date dataDevolucaoReal, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = new Date(this.dataEmprestimo.getTime() + 7L * 24 * 60 * 60 * 1000);
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.estado = estado;
    }
  
    
    
    public String getId(){
        return this.id;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public Exemplar[]  getExemplar(){
        return this.exemplar;
    }
    
    public Date getDataEmprestimo(){
        return this.dataEmprestimo;
    }
    
    public Date getDataDevolucaoPrevista(){
        return this.dataDevolucaoPrevista;
    }
    
    public Date getDataDevolucaoReal(){
        return this.dataDevolucaoReal;
    }
    
    public String getEstadoEmprestimo(){
        return this.estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setExemplar(Exemplar exemplar[]) {
        this.exemplar = exemplar;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista() {
        this.dataDevolucaoPrevista = new Date(this.dataEmprestimo.getTime() + 7L * 24 * 60 * 60 * 1000);
    }

    public void setDataDevolucaoReal(Date dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
    
    public void setDataDevolucaoReal(){
        this.dataDevolucaoReal = new Date();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setEstadoEmprestimo(){
        this.estado="Devolvido";
    }
    
    public void getDetalhes() {
        System.out.println("==========================================================================================");
        System.out.println("||                                  Detalhes do Emprestimo                              ||");
        System.out.println("==========================================================================================");
        System.out.printf("|| ID - Emprestimo:           %s%n", this.getId());
        System.out.printf("|| ID - Usuario:              %s%n", this.getUsuario().getId());
        System.out.printf("|| Nome - Usuario:            %s%n", this.usuario.getNome());
        for (Exemplar exe : this.exemplar) {
            System.out.printf("|| Exemplar:                  %s%n", exe.getId() + " - " + exe.getLivro().getNome());
        }
        System.out.printf("|| Data de Emprestimo:        %s%n", this.dataEmprestimo);
        System.out.printf("|| Data de Devolucao Prevista:%s%n", this.dataDevolucaoPrevista);
        System.out.printf("|| Data de Devolucao Real:    %s%n", (this.dataDevolucaoReal != null ? this.dataDevolucaoReal : "Nao devolvido"));
        System.out.println("==========================================================================================");
    }
}