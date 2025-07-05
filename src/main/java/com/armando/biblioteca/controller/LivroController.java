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
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private EditoraService editoraService;

    @Autowired
    private AreaConhecimentoService areaService;



    @GetMapping("/cadastrar")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "/livro/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String salvarLivro(@ModelAttribute Livro livro,@RequestParam String editoraId, @RequestParam String areaId, @RequestParam(name = "autoresIds", required = false) List<String> autoresIds) {
        livro.setEditora(editoraService.dadosEditora(editoraId).get());
        livro.setArea(areaService.dadosArea(areaId));
        livroService.cadastrarLivro(livro, autoresIds);
        return "redirect:/livro/lista/id";
    }


    @GetMapping("/lista/id") 
    public String listar(Model model) {
        List<Livro> livro = livroService.listarLivros();
        model.addAttribute("livros", livro);
        return "/livro/lista";
    }

    @GetMapping("/lista/json") 
    @ResponseBody
    public List<Livro> listarJSON(Model model) {
        return livroService.ordenarLivros();
    }
    
    @GetMapping("/lista/nome") 
    public String ordenar(Model model) {
        List<Livro> livros = livroService.ordenarLivros();
        model.addAttribute("livros", livros);
        return "/livro/lista";
    }

    @GetMapping("/ver")
    public String ver() {
        return "/livro/ver";
    }

    @GetMapping("/ver/{id}")
    @ResponseBody
    public Optional<Livro> verID(@PathVariable String id) {
        return livroService.dadosLivro(id);
    }


    @GetMapping("/por-autor")
    public String porAutor() {
        return "/livro/por-autor";
    }

    @GetMapping("por-autor/{id}")
    @ResponseBody
    public List<Livro> porAutorID(@PathVariable String id) {
        return livroService.livrosPorAutor(id);
    }
}
