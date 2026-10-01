/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.filmes.controll;

import com.api.filmes.model.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller 
public class MiscController {
                
                List<Filmes> filmes = new ArrayList();
                
                @GetMapping("/cadastro-filme") // 
                public String exibirFilme(Model model) { 
                    
                    model.addAttribute("filme", new Filmes()); 
                    return "cadastro-filme"; 
                } 
                
                @PostMapping("/cadastro-filme") 
                public String processarFilme(@ModelAttribute Filmes filme, Model model) { 
                
                    filmes.add(filme); 
                    model.addAttribute("filme", new Filmes()); 
                    
                    return "cadastro-filme"; 
                } 
                
                @GetMapping("/lista-filme") // http://localhost:8080/lista-filme
                public String exibirFilmeLista(Model model) { 
                    
                        Filmes filmeTeste = new Filmes();
                        filmeTeste.setId(0);
                        filmeTeste.setNome("Fodase");
                        filmeTeste.setSinopse("n importa");
                        filmeTeste.setGenero("terror");
                        filmeTeste.setDataLancamento("10/09/2001");
                        
                        filmes.add(filmeTeste);
                        
                    model.addAttribute("filmes", filmes);
                    return "lista-filme"; 
                } 
                
                @GetMapping("/filmes/{id}")
                public String detalhesFilme(@PathVariable int id, Model model) {

                    Filmes filmeSelecionado = null;

                    for (Filmes filme : filmes) {
                        if (filme.getId() == id) {
                            filmeSelecionado = filme;
                            break;
                        }
                    }

                    model.addAttribute("filme", filmeSelecionado);

                    return "detalhes-filme";
                }
                
                @PostMapping("/detalhes-filme")
                public String processarDetalhes(@ModelAttribute("filme") Filmes filme,Model model) {
                    
                    Analise analise = new Analise();

                    analise.setFilme(filme.getNome());

                    model.addAttribute("analise", analise);
                    model.addAttribute("filme", filme); 
                    return "analise-filme";
                }
                

                @PostMapping("/analise-filme")
                public String processarAnalise(@ModelAttribute("analise") Analise analise,Model model) {

                    model.addAttribute("analise", analise);
                    return "analise-view";
                }
                
                @PostMapping("/analise-view")
                public String exibirAnalise(@ModelAttribute("analise") Analise analise,Model model) {
                    
                    model.addAttribute("analise", analise);
                    return "analise-view";
                    
                }
               
                
               
                
                
                
}
