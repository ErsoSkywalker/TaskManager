function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
}

$(document).ready(function () {

    $.ajax({
        method: "POST",
        url: "GimmeProyect",
        data: {idProject: getUrlParameter('proyecto')}
    })
            .done(function (msg) {
                $('#Proyectokekw').html(msg);
            });
});

function agregarTarea(idProyecto) {
    $.ajax({
        method: "POST",
        url: "agregarTarea",
        data: {
            idProject: idProyecto,
            NombreTarea: $('#nombre').val(),
            DescripcionTarea: $('#fechaInicio').val(),
            FechaInicio: $('#fechaFinal').val(),
            FechaFinal: $('#descripcion').val()
        }
    })
            .done(function (msg) {
                $('#tabla').html(msg);
            });
}


