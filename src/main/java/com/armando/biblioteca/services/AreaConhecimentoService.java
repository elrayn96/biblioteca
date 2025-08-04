package com.armando.biblioteca.services;

import com.armando.biblioteca.Validacao;
import com.armando.biblioteca.model.AreaConhecimento;
import com.armando.biblioteca.repository.AreaConhecimentoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class AreaConhecimentoService {

    private final AreaConhecimentoRepository areaRepo;
    private List<AreaConhecimento> areas;
    private Validacao validar = new Validacao();


    public AreaConhecimentoService(AreaConhecimentoRepository areaRepo) {
        this.areaRepo = areaRepo;
        areas = this.areaRepo.findAll();
    }

    // 1. Cadastro de uma AreaConhecimneto
    public void cadastrarAreaConhecimento(AreaConhecimento area){
        if(!areaRepo.existsById(area.getId())) {
            if(areas.size() == 0) {
                area.setId(validar.validarID("ARE00000"));
            } else {
                area.setId(areas.get(areas.size()-1).getId());
            }
        }
        areaRepo.save(area);
    }

    // 2. Listar AreaConhecimento (Ordem de ID's)
    public List<AreaConhecimento> listarAreas() {
        return areaRepo.findAll();
    }

    // 3. Listar AreaConhecimento (Ordem Alfabetica)
    public List<AreaConhecimento> ordenarAreas() {
        return areaRepo.findAll().stream()
        .sorted(Comparator.comparing(AreaConhecimento::getNome))
        .collect(Collectors.toList());
    }

    // 2. Buscar AreaConhecimento Por ID
    public AreaConhecimento dadosArea(String id) {
        return areaRepo.findById(id).get();
    }
    
}
