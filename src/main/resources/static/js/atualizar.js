/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


$(document).ready(function () {

    $("#formEditar").submit(function (event) {

        event.preventDefault();

        let id = $("#id").val();

        let filme = {

            nome: $("#nome").val(),

            genero: $("#genero").val(),

            sinopse: $("#sinopse").val(),

            dataLancamento: $("#data").val()

        };
        
        console.log("teste1");

        $.ajax({

            url: "/filmesRest/" + id,

            type: "PUT",

            contentType: "application/json",

            data: JSON.stringify(filme),

            success: function () {
                
                alert("Filme atualizado com sucesso!");

                window.location.href = "/lista-filme";

            },

            error: function (xhr) {

                console.log(xhr);

                alert("Erro ao atualizar o filme.");

            }

        });

    });
    
    $("#formEditarAnalise").submit(function (event) {

        event.preventDefault();

        let id = $("#id").val();

        let analise = {

            nome: $("#nome").val(),

            analise: $("#analise").val(),

            nota: $("#nota").val()

        };
        
        console.log("teste2");

        $.ajax({

            url: "/analiseRest/" + id,

            type: "PUT",

            contentType: "application/json",

            data: JSON.stringify(analise),

            success: function () {
                
                alert("Analise atualizada com sucesso!");

                window.location.href = "/lista-filme";

            },

            error: function (xhr) {

                console.log(xhr);

                alert("Erro ao atualizar a Analise.");

            }

        });

    });
});