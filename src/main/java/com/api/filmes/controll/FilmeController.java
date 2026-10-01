/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.filmes.controll;

import java.util.ArrayList; 
import java.util.List; 
import org.springframework.web.bind.annotation.*; 
import com.api.filmes.model.Filmes;

@RestController 
@CrossOrigin(origins = "*") 
@RequestMapping("/filmesRest") //localhost:8080/filmesRest
public class FilmeController { 
    private List<Filmes> filmesList = new ArrayList<>(); 
    private int proximoId = 1; 

    @PostMapping("/criar") 
    public Filmes criarFilmes(@RequestBody Filmes filme) { 
        filme.setId(proximoId++); 
        filmesList.add(filme); 
        return filme; 
    }

    @GetMapping("/buscar") 
    public List buscarFilmess() { 
        return filmesList; 
    }

    @GetMapping("/{id}") 
    public Filmes buscarFilmes(@PathVariable int id) { 
        for (Filmes filme : filmesList) { 
            if (filme.getId() == id) { 
            return filme; 
            } 
        } 
        return null; 
    } 
    @PutMapping("/{id}") 
    public Filmes atualizarFilmes(@PathVariable int id, @RequestBody Filmes filme) { 
        for (int i = 0; i < filmesList.size(); i++) { 
        Filmes t = filmesList.get(i); 
        if (t.getId() == id) { 
         
            t.setDataLancamento(filme.getDataLancamento());
            t.setGenero(filme.getGenero());
            t.setId(filme.getId());
            t.setNome(filme.getNome());
            t.setSinopse(filme.getSinopse());
            return t; 
            } 
        } 
        return null; 
    } 

    @DeleteMapping("/{id}") 
    public boolean deletarFilmes(@PathVariable int id) { 
        for (int i = 0; i < filmesList.size(); i++) { 
        Filmes filme = filmesList.get(i); 
        if (filme.getId() == id) { 
            filmesList.remove(i); 
            return true; 
    } 
    } 
        return false; 
    } 
} 
