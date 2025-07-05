package com.armando.biblioteca.services;

import com.armando.biblioteca.model.Usuario;
import com.armando.biblioteca.Validacao;
import com.armando.biblioteca.model.Docente;
import com.armando.biblioteca.model.Estudante;
import com.armando.biblioteca.model.Funcionario;
import com.armando.biblioteca.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class UsuariosService {

    private final UsuarioRepository usuarioRepo;
    private List<Usuario> user;
    private Validacao validar = new Validacao();


    public UsuariosService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.user = this.usuarioRepo.findAll();
    }

    // 1. Cadastro de um usuário
    public void cadastrarUsuario(Usuario usuario){
        usuarioRepo.save(usuario);
    }

    // 1.1 Docente
    public void cadastrarDocente(Docente est) {
        List<Docente> user = filtrarDocentes(this.user);

        if(user.size() == 0) {
            est.setId(validar.validarID("DOC00000"));
        } else {
            est.setId(validar.validarID(user.get(user.size()-1).getId()));
        }
        cadastrarUsuario(est);

    }


    //1.2 Estudante
    public void cadastrarEstudante(Estudante est) {
        List<Estudante> user = filtrarEstudantes(this.user);

        if(user.size() == 0) {
            est.setId(validar.validarID("EST00000"));
        } else {
            est.setId(validar.validarID(user.get(user.size()-1).getId()));
        }
        cadastrarUsuario(est);
    }


    // 1.3 Funcionario
    public void cadastrarFuncionario(Funcionario est) {
        List<Funcionario> user = filtrarFuncionarios(this.user);
        if(user.size() == 0) {
            est.setId(validar.validarID("FUN00000"));
        } else {
            est.setId(validar.validarID(user.get(user.size()-1).getId()));
        }
        cadastrarUsuario(est);
    }




    // 2. Buscar Usuario Por ID
    public Optional<Usuario> dadosUsuario(String id) {
        
        return usuarioRepo.findById(id);
    }

    // 3. Listar Usarios( Ordem de ID's)
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    // 4. Listar Usarios( Ordem Alfabetica)
    public List<Usuario> ordenarUsuarios() {
        return usuarioRepo.findAll().stream()
        .sorted(Comparator.comparing(Usuario::getNome))
        .collect(Collectors.toList());
    }





    //Filtradores
    private static List<Docente> filtrarDocentes(List<Usuario> usuarios) {
        ArrayList<Docente> lista = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u instanceof Docente)
                lista.add((Docente) u);
        }
        return lista;
    }

    private static List<Estudante> filtrarEstudantes(List<Usuario> usuarios) {
        ArrayList<Estudante> lista = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u instanceof Estudante)
                lista.add((Estudante) u);
        }
        return lista;
    }

    private static List<Funcionario> filtrarFuncionarios(List<Usuario> usuarios) {
        ArrayList<Funcionario> lista = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u instanceof Funcionario)
                lista.add((Funcionario) u);
        }
        return lista;
    }


    public List<Usuario> buscarExemplarNomeOuId(String query) {
        String q = query.toLowerCase();
        return usuarioRepo.findAll().stream().filter(u -> u.getId().toLowerCase().contains(q) ||  u.getNome().toLowerCase().contains(q)).collect(Collectors.toList());
    }
    
}
