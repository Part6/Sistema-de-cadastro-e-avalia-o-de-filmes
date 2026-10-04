/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.filmes.controll;

import com.api.filmes.repository.FilmesRepository;
import java.util.List; 
import org.springframework.web.bind.annotation.*; 
import com.api.filmes.model.Filmes;

@RestController 
@CrossOrigin(origins = "*") 
@RequestMapping("/filmesRest") //localhost:8080/filmesRest
public class FilmeController { 
    
    private final FilmesRepository fr;
    
    public FilmeController(FilmesRepository fr) {
        this.fr = fr;
    }

    @PostMapping("/criar") 
    public Filmes criarFilmes(@RequestBody Filmes filme) { 
        return fr.save(filme);
    }

    @GetMapping("/buscar") 
    public List buscarFilmess() { 
         return fr.findAll();
    }

    @GetMapping("/{id}") 
    public Filmes buscarFilmes(@PathVariable int id) { 
        return fr.findById(id).orElse(null);
    } 
    
    
    @PutMapping("/{id}") 
    public Filmes atualizarFilmes(@PathVariable int id, @RequestBody Filmes filme) { 
        
        Filmes filmeExistente = fr.findById(id).orElse(null);
        
        if (filmeExistente != null) {
        filmeExistente.setNome(filme.getNome());
        filmeExistente.setGenero(filme.getGenero());
        filmeExistente.setSinopse(filme.getSinopse());
        filmeExistente.setDataLancamento(filme.getDataLancamento());
        fr.save(filmeExistente);
        }
        return filmeExistente;
        
    } 

    @DeleteMapping("/{id}") 
    public boolean deletarFilmes(@PathVariable int id) { 
        if (fr.existsById(id)) {

            fr.deleteById(id);

            return true;
        }

        return false;
    } 
} 
