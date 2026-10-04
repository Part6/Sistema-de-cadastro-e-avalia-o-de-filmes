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
 
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import com.api.filmes.model.Preferencia;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.CookieValue;



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
    public String exibirFilme(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            Model model) {
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema);
        
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
    public String exibirAnalise(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            Model model) {
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema);
        
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
    public String exibirFilmeLista(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            Model model) {
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema);
        
        model.addAttribute("filmes",filmesRepository.findAll());

        return "lista-filme";
    }


    // =========================
    // DETALHES DO FILME
    // =========================

    @GetMapping("/filmes/{id}")
    public String detalhesFilme(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            @PathVariable Integer id,Model model) {
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema);
        
        Filmes filme = filmesRepository.findById(id).orElse(null);

        model.addAttribute("filme", filme);
        model.addAttribute("analises",analiseRepository.findAll());

        return "detalhes-filme";
    }
    
    @GetMapping("/analise/{id}")
    public String detalhesAnalise(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            @PathVariable Integer id,Model model) {
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema);
        
        Analise analise = analiseRepository.findById(id).orElse(null);

        model.addAttribute("analise", analise);

        return "analise-view";
    }


    // =========================
    // EDITAR FILME
    // =========================

    @GetMapping("/filmes/{id}/editar")
    public String editarFilme(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            @PathVariable Integer id,Model model) {
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema);
        
        Filmes filme = filmesRepository
                .findById(id)
                .orElse(null);

        model.addAttribute("filme", filme);

        return "EditarFilme";
    }
    
    @GetMapping("/analise/{id}/editar")
    public String editarAnalise(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            @PathVariable Integer id,Model model) {
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema);
        
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
    
    @RequestMapping("/preferencias") 
    public String preferencias(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema,Model model){
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema);
        return "preferencias"; 
    }
    
    @PostMapping("/preferencias") 
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response){ 

        Cookie cookiePrefNome = new Cookie("pref-nome", pref.getNome()); 
        cookiePrefNome.setDomain("localhost"); //disponível apenas no domínio "localhost" 
        cookiePrefNome.setHttpOnly(true); //acessível apenas por HTTP, JS não 
        cookiePrefNome.setMaxAge(86400); //1 dia 
        

        response.addCookie(cookiePrefNome); 

        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo()); 
        cookiePrefEstilo.setDomain("localhost"); //disponível apenas no domínio "localhost" 
        cookiePrefEstilo.setHttpOnly(true); //acessível apenas por HTTP, JS não 
        cookiePrefEstilo.setMaxAge(86400); //1 dia 

        response.addCookie(cookiePrefEstilo); 

        return new ModelAndView("redirect:/"); //"index"; 
    }
    @RequestMapping("/") 
    public String index(@CookieValue(name="pref-nome", defaultValue="")String nome, @CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model){ 
        model.addAttribute("nome", nome); 
        model.addAttribute("css", tema); 
        return "index"; 

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
