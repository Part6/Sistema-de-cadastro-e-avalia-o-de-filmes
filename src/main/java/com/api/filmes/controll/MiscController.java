/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.filmes.controll;

import com.api.filmes.model.*;
import com.api.filmes.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller 
public class MiscController { //// http://localhost:8080/lista-filme
                
               
      private final FilmesRepository filmesRepository;
      private final AnaliseRepository analiseRepository;

    public MiscController(FilmesRepository filmesRepository, AnaliseRepository analiseRepository) {
        this.filmesRepository = filmesRepository;
        this.analiseRepository = analiseRepository;
    }

    // =========================
    // CADASTRO
    // =========================

    @GetMapping("/cadastro-filme")
    public String exibirFilme(Model model) {

        model.addAttribute("filme", new Filmes());

        return "cadastro-filme";
    }

    @PostMapping("/cadastro-filme")
    public String processarFilme(
            @ModelAttribute("filme") Filmes filme) {

        filmesRepository.save(filme);

        return "redirect:/lista-filme";
    }

    
    @GetMapping("/cadastro-analise")
    public String exibirAnalise(Model model) {

        model.addAttribute("analise", new Analise());

        return "cadastro-analise";
    }
    
    @PostMapping("/cadastro-analise")
    public String processarAnalise( @ModelAttribute("analise") Analise analise) {

        analiseRepository.save(analise);

        return "redirect:/lista-filme";
    }

    // =========================
    // LISTA DE FILMES
    // =========================

    @GetMapping("/lista-filme")
    public String exibirFilmeLista(Model model) {

        model.addAttribute("filmes",filmesRepository.findAll());

        return "lista-filme";
    }


    // =========================
    // DETALHES DO FILME
    // =========================

    @GetMapping("/filmes/{id}")
    public String detalhesFilme(@PathVariable Integer id,Model model) {

        Filmes filme = filmesRepository.findById(id).orElse(null);

        model.addAttribute("filme", filme);
        model.addAttribute("analises",analiseRepository.findAll());

        return "detalhes-filme";
    }
    
    @GetMapping("/analise/{id}")
    public String detalhesAnalise(@PathVariable Integer id,Model model) {

        Analise analise = analiseRepository.findById(id).orElse(null);

        model.addAttribute("analise", analise);

        return "analise-view";
    }


    // =========================
    // EDITAR FILME
    // =========================

    @GetMapping("/filmes/{id}/editar")
    public String editarFilme(@PathVariable Integer id,Model model) {

        Filmes filme = filmesRepository
                .findById(id)
                .orElse(null);

        model.addAttribute("filme", filme);

        return "EditarFilme";
    }
    
    @GetMapping("/analise/{id}/editar")
    public String editarAnalise(@PathVariable Integer id,Model model) {

        Analise analise = analiseRepository.findById(id).orElse(null);

        model.addAttribute("analise", analise);

        return "EditarAnalise";
    }

    @PostMapping("/filmes/{id}/editar")
    public String atualizarFilme(@PathVariable Integer id,@ModelAttribute("filme") Filmes filme) {
        Filmes filmeExistente = filmesRepository.findById(id).orElse(null);

        if (filmeExistente != null) {

            filmeExistente.setNome(filme.getNome());
            filmeExistente.setSinopse(filme.getSinopse());
            filmeExistente.setGenero(filme.getGenero());
            filmeExistente.setDataLancamento(
                    filme.getDataLancamento()
            );

            filmesRepository.save(filmeExistente);
        }

        return "redirect:/lista-filme";
    }
    
    @PostMapping("/analise/{id}/editar")
    public String atualizarAnalise(@PathVariable Integer id,@ModelAttribute("analise") Analise analise) {
        Analise analiseExistente = analiseRepository.findById(id).orElse(null);

        if ( analiseExistente != null) {

            analiseExistente.setFilme(analise.getFilme());
            analiseExistente.setAnalise(analise.getAnalise());
            analiseExistente.setNota(analise.getNota());
            

            analiseRepository.save(analiseExistente);
        }

        return "redirect:/lista-filme";
    }


    // =========================
    // ANÁLISE DO FILME
    // =========================

    /* @PostMapping("/detalhes-filme")
    public String processarDetalhes(
            @ModelAttribute("filme") Filmes filme,
            Model model) {

        Analise analise = new Analise();

        analise.setFilme(filme.getNome());

        model.addAttribute("analise", analise);
        model.addAttribute("filme", filme);

        return "cadastro-analise";
    }


    @PostMapping("/cadastro-analise")
    public String processarAnalise(@ModelAttribute("analise") Analise analise, Model model) {

        model.addAttribute("analise", analise);

        return "analise-view";
    }    */            
                           
}
