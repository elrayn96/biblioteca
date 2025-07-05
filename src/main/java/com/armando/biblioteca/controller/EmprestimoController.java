package com.armando.biblioteca.controller;

import com.armando.biblioteca.model.*;
import com.armando.biblioteca.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService empService;

    @Autowired
    private UsuariosService uService;

    @Autowired
    private ExemplarService eService;

    @GetMapping("/cadastrar")
    private String cadastrarEmprestimo(Model model) {
        return "/emprestimo/cadastrar";
    }

    @GetMapping("/exemplares-disponiveis")
    @ResponseBody
    private List<Exemplar> buscarExemplarDisponiveis(@RequestParam("query") String query) {
        List<Exemplar> todos = eService.buscarExemplarLivroOuId(query), disponiveis = new ArrayList<>();

        for (Exemplar e : todos) {
            if (e.getEstado().equalsIgnoreCase("Disponivel")) {
                disponiveis.add(e);
            }
        }
        return disponiveis;
    }

    @GetMapping("/usuarios-disponiveis")
    @ResponseBody
    private List<Usuario> buscaUserDisponivel(@RequestParam("query") String query) {
        List<Usuario> todos = uService.buscarExemplarNomeOuId(query);
        List<Usuario> disponiveis = new ArrayList<>();
        for (Usuario u : todos) {
            if (empService.verLegibilidadeUsuario(u)) {
                disponiveis.add(u);
            }
        }
        return disponiveis;
    }

    @PostMapping("/cadastrar")
    public String cadastr(@RequestParam String usuarioId, @RequestParam List<String> exemplarIds,
            @ModelAttribute Emprestimo emprestimo) {
        usuarioId = usuarioId.substring(0, 8);
        System.out.println("Quantidade de exemplares: " + exemplarIds.size());
        for (String exem : exemplarIds) {
            System.out.println(exem);
        }
        if (empService.verLegibilidadeUsuario(uService.dadosUsuario(usuarioId.toUpperCase()).get())) {
            emprestimo.setUsuario(uService.dadosUsuario(usuarioId).get());
            if (exemplarIds.size() < 5 && empService.livrosUnicos(eService.listaExemplarId(exemplarIds))) {
                empService.registarEmprestimo(emprestimo, exemplarIds);
            }
        } else {
            System.out.println("Erro");
        }
        return "redirect:/";

    }

    @GetMapping("/devolucao")
    public String devol(Model model) {
        return "emprestimo/devolucao";
    }

    @PostMapping("/devolucao")
    public String devolver(@RequestParam String usuarioId) {
        empService.registarDevolucao(uService.dadosUsuario(usuarioId).get());
        return "redirect:/";
    }

    @GetMapping("/ver")
    @ResponseBody
    public Emprestimo pendentes(@RequestParam String usuarioId) {
        System.out.println("ID - Usuario: " + usuarioId);
        Optional<Usuario> usuOp = uService.dadosUsuario(usuarioId);
        if(usuOp.isPresent()){
            if(empService.empPendente(usuOp.get()).isPresent()){
                System.out.println(empService.empPendente(usuOp.get()).get().getEstadoEmprestimo());
                return empService.empPendente(usuOp.get()).get();
            }
            //return empService.empPendente(usuOp.get());
        }
        return null;
    }

}
