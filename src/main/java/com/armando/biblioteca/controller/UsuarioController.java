package com.armando.biblioteca.controller;

import com.armando.biblioteca.model.*;
import com.armando.biblioteca.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuariosService uService;

    @GetMapping("/estudante")
    public String novoEstudante() {
        return "usuarios/estudante";
    }

    @GetMapping("/docente")
    public String novoDocente() {
        return "usuarios/docente";
    }

    @GetMapping("/funcionario")
    public String novoFuncionario() {
        return "usuarios/funcionario";
    }


    //Cadastro
    @PostMapping("/cadastrar/docente")
    public String salvarDocente(@ModelAttribute Docente usuario) {
        uService.cadastrarDocente(usuario);
        return "redirect:/usuarios/lista/id";
    }

    @PostMapping("/cadastrar/estudante")
    public String salvarEstudante(@ModelAttribute Estudante usuario) {
        usuario.getDetalhes();
        uService.cadastrarEstudante(usuario);
        return "redirect:/usuarios/lista/id";
    }

    @PostMapping("/cadastrar/funcionario")
    public String salvarFuncionario(@ModelAttribute Funcionario usuario) {
        uService.cadastrarFuncionario(usuario);
        return "redirect:/usuarios/lista/id";
    }



    @GetMapping("/lista/id")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = uService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/lista";
    }

    @GetMapping("/lista/nome")
    public String listarOrdenar(Model model) {
        List<Usuario> usuarios = uService.ordenarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/lista";
    }

    @GetMapping("/ver")
    public String verUsuarioForm() {
        return "usuarios/ver"; // apenas retorna o HTML
    }

    @GetMapping("/ver/{id}")
    @ResponseBody
    public Optional<Usuario> verUsuario(@PathVariable String id) {
        return uService.dadosUsuario(id); 
    }


}

/*
    @PostMapping("/cadastrar/funcionario")
    public String salvarFuncionario(@ModelAttribute Funcionario usuario, RedirectAttributes redi) {
        uService.cadastrarFuncionario(usuario);
        redi.addFlashAttibute("")
        return "Registado com Sucesso";
    }
 */
