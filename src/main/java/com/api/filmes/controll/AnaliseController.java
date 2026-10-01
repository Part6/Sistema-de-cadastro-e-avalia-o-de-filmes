/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.filmes.controll;

import org.springframework.web.bind.annotation.*; 
import com.api.filmes.model.Analise;
import java.util.ArrayList;
import java.util.List;


@RestController 
@CrossOrigin(origins = "*") 
@RequestMapping("/analise") 
public class AnaliseController { 
    private List<Analise> analiseList = new ArrayList<>(); 
    private int proximoId = 1; 

    @PostMapping("") 
    public Analise criarAnalise(@RequestBody Analise filme) { 
        filme.setId(proximoId++); 
        analiseList.add(filme); 
        return filme; 
    }

    @GetMapping("") 
    public List buscarAnalises() { 
        return analiseList; 
    }

    @GetMapping("/{id}") 
    public Analise buscarAnalise(@PathVariable int id) { 
        for (Analise filme : analiseList) { 
            if (filme.getId() == id) { 
            return filme; 
            } 
        } 
        return null; 
    } 
    @PutMapping("/{id}") 
    public Analise atualizarAnalise(@PathVariable int id, @RequestBody Analise analise) { 
        for (int i = 0; i < analiseList.size(); i++) { 
        Analise t = analiseList.get(i); 
        if (t.getId() == id) { 
         
            t.setId(analise.getId());
            t.setFilme(analise.getFilme());
            t.setNota(analise.getNota());
            t.setAnalise(analise.getAnalise());
            
            return t; 
            } 
        } 
        return null; 
    } 

    @DeleteMapping("/{id}") 
    public boolean deletarAnalise(@PathVariable int id) { 
        for (int i = 0; i < analiseList.size(); i++) { 
        Analise filme = analiseList.get(i); 
        if (filme.getId() == id) { 
            analiseList.remove(i); 
            return true; 
    } 
    } 
        return false; 
    } 
} 

