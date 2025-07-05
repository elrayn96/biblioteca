package com.armando.biblioteca.controller;

import com.armando.biblioteca.model.*;
import com.armando.biblioteca.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@Controller
@RequestMapping("/exemplar")
public class ExemplarController {


    @Autowired
    private ExemplarService exeService;

    @GetMapping("/cadastrar")
    public String cadastrarExemplar() {
        return "/exemplar/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String salvar(@ModelAttribute Exemplar exemplar, @RequestParam String livroId) {
        exeService.cadastrarExemplar(exemplar, livroId);
        return "redirect:/";
    }

    @GetMapping("/danificar")
    public String danificar() {
        return "/exemplar/danificar";
    }

    @PutMapping("/danificar/{id}")
        public ResponseEntity<?> reportarDanificado(@PathVariable String id) {
        Exemplar ex = exeService.dadosExemplar(id).get();

        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exemplar não encontrado");
        }

        if ("emprestado".equalsIgnoreCase(ex.getEstado())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Exemplar emprestado");
        }

        ex.setEstado("Danificado");
        exeService.cadastrarExemplar(ex);
        return ResponseEntity.ok("Exemplar danificado com sucesso");
    }
    
}
