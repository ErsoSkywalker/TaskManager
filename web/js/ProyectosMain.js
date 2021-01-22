$(document).ready(function () {
    $.ajax({
        method: "POST",
        url: "ProyectosMain"
    })
            .done(function (msg) {
                $('#tabla').html(msg);
            });
});

