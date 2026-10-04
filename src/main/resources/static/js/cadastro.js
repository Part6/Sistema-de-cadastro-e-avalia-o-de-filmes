

$(document).ready(function () {
    
    console.log("funciona");
    $("#formCadastro").submit(function (event) {

        event.preventDefault();


        let filme = {

            id: $("#id").val(),

            nome: $("#nome").val(),

            sinopse: $("#sinopse").val(),

            genero: $("#genero").val(),

            data: $("#data").val()

        };
        
        console.log("test1");

        $.ajax({

            url: "/filmesRest/criar",

            type: "POST",

            contentType: "application/json",

            data: JSON.stringify(filme),

            success: function (filmeCriado) {

                alert("Filme cadastrado! ID: " + filmeCriado.id);
                
                console.log("test2");
            },

            error: function () {

                alert("Erro ao cadastrar filme.");
                console.log("test3");
            }

        });

    });
    
    $("#formCadastroAnalise").submit(function (event) {

        event.preventDefault();

        let analise = {
            
            id: $("#id").val(),

            filme: $("#nome").val(),

            analise: $("#analise").val(),

            nota: $("#nota").val()

        };
        console.log("test1");

        $.ajax({

            url: "/analiseRest/criar",

            type: "POST",

            contentType: "application/json",

            data: JSON.stringify(analise),

            success: function (analiseCriada) {

                alert("Análise cadastrada! ID: " + analiseCriada.id);
                console.log("test2");
            },

            error: function () {

                alert("Erro ao cadastrar análise.");
                console.log("test3");

            }

        });

    });
});
    
