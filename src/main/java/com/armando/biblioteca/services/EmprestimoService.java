package com.armando.biblioteca.services;

import com.armando.biblioteca.Validacao;
import com.armando.biblioteca.model.Emprestimo;
import com.armando.biblioteca.model.Usuario;
import com.armando.biblioteca.model.Exemplar;
import com.armando.biblioteca.repository.EmprestimoRepository;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class EmprestimoService {
    private final EmprestimoRepository empRepo;
    
    private ExemplarService eService;
    private Validacao validar = new Validacao();

    public EmprestimoService(EmprestimoRepository empRepo, ExemplarService eService) {
        this.empRepo = empRepo;
        this.eService = eService;
    }

    // 1. Registar Emprestimo
    public void registarEmprestimo(Emprestimo emp, List<String> exemplarIds){
        List<Emprestimo> emprestimos = empRepo.findAll();
        if(emprestimos.size() == 0) {
            emp.setId("EMP00001");
        } else {
            emp.setId(validar.validarID(emprestimos.get(emprestimos.size()-1).getId()));
        }
        List<Exemplar> exemplares = eService.listaExemplarId(exemplarIds);
        if(exemplarIds.size() == 0) {
            //return;
            System.out.println(exemplares.toString());
            for(String id: exemplarIds) {
                System.out.println(id);
            }
        } else {
            System.out.println(exemplares.size() + " - " + exemplarIds.getFirst());
            for (Exemplar exe : exemplares) {
                System.out.println(exe.getId());
                exe.setEstado("Emprestado");
                eService.cadastrarExemplar(exe);
            }
        }
        emp.setExemplar(exemplares.toArray(new Exemplar[exemplares.size()]));
        emp.setDataEmprestimo(new Date());
        emp.setDataDevolucaoPrevista();
        emp.setEstado("Pendente");
        empRepo.save(emp);    
        
    }

    // 2. Ver Legibilidade Usuario
    public boolean verLegibilidadeUsuario(Usuario usu) {
        return !empRepo.existsByUsuarioAndEstado(usu, "Pendente");
    }

    // 3. Registar Devolucao
    public void registarDevolucao(Emprestimo emp) {
        emp.setDataDevolucaoReal();
        emp.setEstado("Devolvido");
        for (Exemplar exe : emp.getExemplar()) {
            exe.setEstado("Disponivel");
            eService.cadastrarExemplar(exe);
        }
        empRepo.save(emp);
    } 
 
    public void registarDevolucao(Usuario usuario) {
        Emprestimo emp = empRepo.findByUsuarioAndEstadoIgnoreCase(usuario, "Pendente").get();
        registarDevolucao(emp);
    } 

    // 4. Listar Emprestimos
    public List<Emprestimo> listarEmprestimos() {
        return empRepo.findAll();
    }

    public Optional<Emprestimo> empPendente(Usuario usuario) {
        return empRepo.findByUsuarioAndEstadoIgnoreCase(usuario, "Pendente");
    }

    

    // Livros unicos
    public boolean livrosUnicos(List<Exemplar> exemplares) {
        return exemplares.stream().map(e -> e.getLivro().getId()).distinct().count() == exemplares.size();
    }
    
    
}
