package com.armando.biblioteca.controller;
import com.armando.biblioteca.model.*;
import com.armando.biblioteca.services.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    private EditoraService ediService;

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "/editora/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String salvar(@ModelAttribute Editora editora) {
        ediService.cadastrarEditora(editora);
        return "redirect:/editora/lista/id";
    }


    @GetMapping("/lista/id")
    public String listar(Model model) {
        List<Editora> edi = ediService.listarEditoras();
        model.addAttribute("edi", edi);
        return "editora/lista";
    } 

    @GetMapping("/lista/nome")
    public String ordenar(Model model) {
        List<Editora> edi = ediService.ordenarEditoras();
        model.addAttribute("edi", edi);
        return "editora/lista";
    }

    @GetMapping("/lista/json")
    @ResponseBody
    public List<Editora> listaJSON(Model model) {
        return ediService.ordenarEditoras();
    }

    @GetMapping("/ver")
    public String verUsuarioForm() {
        return "/editora/ver";
    }

    @GetMapping("/ver/{id}")
    @ResponseBody
    public Optional<Editora> verEditora(@PathVariable String id) {
        return ediService.dadosEditora(id); 
    }
    
}
