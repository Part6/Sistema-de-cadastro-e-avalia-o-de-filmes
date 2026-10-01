/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.filmes.model;
import org.springframework.stereotype.Component; 
/**
 *
 * @author Desktop
 */
@Component
public class Analise {
   private int id;
   private String filme;
   private String analise;
   private int nota;
   
   public int getId() { return id; } 
   public void setId(int id) { this.id = id; } 
   
   public String getFilme() { return filme; } 
   public void setFilme(String filme) { this.filme = filme; } 
   
   public String getAnalise() { return analise; } 
   public void setAnalise(String analise) { this.analise = analise; } 
   
   public int getNota() { return nota; } 
   public void setNota(int nota) { this.nota = nota; }
   
   
}
