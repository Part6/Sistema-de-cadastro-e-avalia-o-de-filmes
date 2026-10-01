/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.filmes.model;

/**
 *
 * @author Desktop
 */
public class Filmes {
   private int id;
   private String nome;
   private String sinopse;
   private String genero;
   private String dataLancamento;
   
   public int getId() { return id; } 
   public void setId(int id) { this.id = id; } 
   
   public String getNome() { return nome; } 
   public void setNome(String nome) { this.nome = nome; } 
   
   public String getSinopse() { return sinopse; } 
   public void setSinopse(String sinopse) { this.sinopse = sinopse; } 
   
   public String getGenero() { return genero; } 
   public void setGenero(String genero) { this.genero = genero; } 
   
   public String getDataLancamento() { return dataLancamento; } 
   public void setDataLancamento(String dataLancamento) { this.dataLancamento = dataLancamento; } 
}
