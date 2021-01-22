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
            DescripcionTarea: $('#descripcion').val(),
            FechaInicio: $('#fechaFinal').val(),
            FechaFinal: $('#fechaInicio').val()
        }
    })
            .done(function (msg) {
                $('#tabla').html(msg);
            });

    $('#nombre').val('');
    $('#descripcion').val('');
    $('#fechaFinal').val('');
    $('#fechaInicio').val('');
}

function completarTarea(idTarea) {
    $.ajax({
        method: "POST",
        url: "completarTarea",
        data: {
            idTarea: idTarea,
            idProject: getUrlParameter('proyecto')
        }
    })
            .done(function (msg) {
                $('#tabla').html(msg);
            });
}

function borrarProyecto() {
    $.ajax({
        method: "POST",
        url: "borrarProyecto",
        data: {
            idProject: getUrlParameter('proyecto')
        }
    })
            .done(function (msg) {
                Swal.fire(
                        'Hey!',
                        'Eliminaste un proyecto correctamente',
                        'success'
                        );
                window.location = 'index.html';
            });
}


