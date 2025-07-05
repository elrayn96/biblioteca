package com.armando.biblioteca.controller;

import com.armando.biblioteca.model.*;
import com.armando.biblioteca.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaConhecimentoController {

    @Autowired
    private AreaConhecimentoService areaService;


    @GetMapping("/cadastrar")
    public String cadastrarArea() {
        return "area/cadastrar";
    }


    @PostMapping("/cadastrar")
    public String salvarArea(@ModelAttribute AreaConhecimento area) {
        areaService.cadastrarAreaConhecimento(area);
        System.out.println("Area Cadastrada com sucesso");
        return "redirect:/area/lista/id";
    }

    @GetMapping("/lista/id")
    public String listar(Model model) {
        List<AreaConhecimento> areaList = areaService.listarAreas();
        model.addAttribute("area", areaList);
        return "area/lista";
    }

    @GetMapping("/lista/nome")
    public String ordenar(Model model) {
        List<AreaConhecimento> areaList = areaService.ordenarAreas();
        model.addAttribute("area", areaList);
        return "area/lista";
    }

    @GetMapping("/lista/json")
    @ResponseBody
    public List<AreaConhecimento> listaJSON(Model model) {
        List<AreaConhecimento> areaList = areaService.listarAreas();
        return areaList;
    }
}


