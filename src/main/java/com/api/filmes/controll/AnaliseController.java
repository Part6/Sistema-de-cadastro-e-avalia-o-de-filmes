/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.filmes.controll;

import com.api.filmes.repository.AnaliseRepository;
import java.util.List; 
import com.api.filmes.model.Analise;

import org.springframework.web.bind.annotation.*; 

@RestController 
@CrossOrigin(origins = "*") 
@RequestMapping("/analiseRest") // http://localhost:8080/analiseRest/criar
public class AnaliseController { 
     private final AnaliseRepository ar;
    
    public AnaliseController(AnaliseRepository ar) {
        this.ar = ar;
    }

    @PostMapping("/criar") 
    public Analise criarAnalise(@RequestBody Analise analise) { 
        return ar.save(analise);
    }

    @GetMapping("/buscar") 
    public List buscarAnalises() { 
         return ar.findAll();
    }

    @GetMapping("/{id}") 
    public Analise buscarAnalise(@PathVariable int id) { 
        return ar.findById(id).orElse(null);
    } 
    
    
    @PutMapping("/{id}") 
    public Analise atualizarAnalise(@PathVariable int id, @RequestBody Analise analise) { 
        
        Analise analiseExistente = ar.findById(id).orElse(null);
        
        if (analiseExistente != null) {
        analiseExistente.setFilme(analise.getFilme());
        analiseExistente.setAnalise(analise.getAnalise());
        analiseExistente.setNota(analise.getNota());
        ar.save(analiseExistente);
        }
        return analiseExistente;
        
    } 

    @DeleteMapping("/{id}") 
    public boolean deletarAnalise(@PathVariable int id) { 
        if (ar.existsById(id)) {

            ar.deleteById(id);

            return true;
        }

        return false;
    } 
} 


