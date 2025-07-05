package com.armando.biblioteca.controller;



import java.util.List;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;



import org.springframework.stereotype.Controller;



import com.armando.biblioteca.model.Autor;

import com.armando.biblioteca.services.AutorService;



@Controller

@RequestMapping("/autor")

public class AutorController {

 

    @Autowired

    private AutorService autorService;





    @GetMapping("/cadastrar")

    public String cadastrarAutor() {

        return "autor/cadastrar";

    }





    @PostMapping("/cadastrar")

    public String salvarAutor(@ModelAttribute Autor autor) {

        autorService.cadastrarAutor(autor);

        System.out.println("Autor Cadastrada com sucesso");

        return "redirect:/autor/lista/id";

    }



    @GetMapping("/lista/id")

    public String listar(Model model) {

        List<Autor> autores = autorService.listarAutores();

        model.addAttribute("autores", autores);

        return("autor/lista");

    }



    @GetMapping("/lista/nome")

    public String ordenar(Model model) {

        List<Autor> autores = autorService.ordenarAutores();

        model.addAttribute("autores", autores);

        return("autor/lista");

    } 

    

    @GetMapping("/lista/json")

    @ResponseBody

    public List<Autor> listaJSON(Model model) {

        return autorService.ordenarAutores();

    } 





    @GetMapping("/ver")

    public String verUsuarioForm() {

        return "autor/ver";

    }



    @GetMapping("/ver/{id}")

    @ResponseBody

    public Optional<Autor> verAutor(@PathVariable String id) {

        return autorService.dadosAutor(id); 

    }

}
