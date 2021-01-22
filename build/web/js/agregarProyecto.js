$(document).ready(function () {


    $('#agregar').on('click', function () {

        $.ajax({
            method: "POST",
            url: "agregarProyecto",
            data: {
                nombre: $('#nombre').val(),
                responsable: $('#responsable').val(),
                fechaInicio: $('#fechaInicio').val(),
                fechaFinal: $('#fechaFinal').val(),
                descripcion: $('#descripcion').val()
            }
        })
                .done(function (msg) {
                    Swal.fire({
                        title: '<strong><u>Hey!</u></strong>',
                        icon: 'info',
                        html: msg,
                        showCloseButton: true
                    });
                });

        $('#nombre').val('');
        $('#responsable').val('');
        $('#fechaInicio').val('');
        $('#fechaFinal').val('');
        $('#descripcion').val('');

    });


});


