/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.filmes.repository;

import com.api.filmes.model.Analise;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
import org.springframework.data.jpa.repository.Query; 
import java.util.List; 

@Repository 
public interface AnaliseRepository extends JpaRepository<Analise, Integer> { 
} 

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

   

