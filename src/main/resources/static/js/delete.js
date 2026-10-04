$(document).ready(function () {

    $(".btnExcluir").on("click", function () {

        let id = $(this).data("id");

        console.log("ID do filme:", id);

        if (!confirm("Deseja realmente excluir este filme?")) {
            return;
        }

        $.ajax({
            url: "/filmesRest/" + id,
            type: "DELETE",

            success: function (resultado) {

                console.log("Resposta:", resultado);

                if (resultado === true) {

                    alert("Filme excluído com sucesso!");

                    location.reload();

                } else {

                    alert("Filme não encontrado.");

                }
            },

            error: function (xhr) {

                console.log("Erro:", xhr);

                alert("Erro ao excluir o filme.");
            }
        });

    });
    
    $(".btnExcluirAnalise").on("click", function () {

        let id = $(this).data("id");

        console.log("ID da análise:", id);

        if (!confirm("Deseja realmente excluir esta análise?")) {
            return;
        }

        $.ajax({
            url: "/analiseRest/" + id,
            type: "DELETE",

            success: function (resultado) {

                console.log("Resposta:", resultado);

                if (resultado === true) {

                    alert("Análise excluída com sucesso!");

                    location.reload();

                } else {

                    alert("Análise não encontrada.");

                }
            },

            error: function (xhr) {

                console.log("Erro:", xhr);

                alert("Erro ao excluir a análise.");
            }
        });

    });
});