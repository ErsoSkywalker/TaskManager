drop database if exists dbTaskManager;
create database dbTaskManager;
use dbTaskManager;

create table tblProyectos(

	idProyecto int primary key not null,
    NombreProyecto nvarchar(60) not null,
    ResponsableProyecto nvarchar(60) not null,
    FechaInicio date not null,
    FechaFinal date not null,
    Descripcion text not null

);


Insert into tblProyectos values (1,'Proyecto1','Christian Abraham Ontiveros Valdelamar',now(),now(), 'DescripciónTest'), (2,'Proyecto2','Christian Abraham Ontiveros Valdelamar2',now(),now(), 'DescripciónTest2');
Select * from tblProyectos;
create table tblTareas(

	idTarea int primary key not null,
    NombreTarea nvarchar(60) not null,
    DescripcionTarea text not null,
    idProyecto int not null,
    FechaInicio date not null,
    FechaFinal date not null,
    Estatus enum('Activa','Terminada'),
    foreign key (idProyecto) references tblProyectos(idProyecto)

);

Insert into tblTareas values(1,'Tarea1Proyecto1','TareaTest',1,now(),now(),'Activa'), (2,'Tarea2Proyecto1','TareaTest2',1,now(),now(),'Terminada');
Select * from tblTareas;


delimiter //

	create procedure spDesplegarTareasProyecto(in idProyectoxd int)
    BEGIN
    
		Select * from tblTareas where idProyecto = idProyectoxd;
    
    END //
    
    create procedure spDesplegarProyecto()
    BEGIN
    
		Select * from tblProyectos;
    
    END //

	create procedure spAgregarProyecto(in NombreProyectoxd nvarchar(60), in Responsablexd nvarchar(60), in FechaInicioxd date, in FechaFinalxd date, in Descripcionxd text)
    BEGIN
    
		declare idSiguiente int;
        set idSiguiente = ifnull((Select MAX(idProyecto) from tblproyectos)+1,1);
		IF((Select idProyecto from tblProyectos where NombreProyecto = NombreProyectoxd) is null)
        THEN
			insert into tblProyectos values(idSiguiente,NombreProyectoxd, Responsablexd, FechaInicioxd, FechaFinalxd, Descripcionxd);
			Select 1 as Mensaje;
        ELSE
			Select 0 as Mensaje;
        END IF;
    
    END //

	create procedure spEliminarProyecto(in idProyectoxd int)
    BEGIN
    
		delete from tblTareas where idProyecto = idProyectoxd;
		delete from tblProyectos where idProyecto = idProyectoxd;
		Select 1 as Mensaje;
    END //

	create procedure spAgregarTarea(in idProyectoxd int, in NombreTareaxd nvarchar(60), in DescripcionTareaxd text, in FechaInicioxd date, in FechaFinalxd date)
    BEGIN
    
		declare idSiguiente int;
        set idSiguiente = ifnull((Select MAX(idTarea) from tblTareas)+1,1);
        
        Insert into tblTareas values(idSiguiente, NombreTareaxd, DescripcionTareaxd, idProyectoxd, FechaInicioxd, FechaFinalxd, 'Activa');
		Select * from tblTareas where idProyecto = idProyectoxd;
    
    END //

	create procedure spCompletarTarea(in idTareaxd int, in idProyectoxd int)
    BEGIN
    
		Update tblTareas set Estatus = 'Terminada' where idTarea = idTareaxd;
		Select * from tblTareas where idProyecto = idProyectoxd;
    END //

	create procedure spDesplegarProyectoByID(in idProyectoxd int)
    BEGIN
    
		Select * from tblProyectos where idProyecto = idProyectoxd;
    
    END //

delimiter ;
Select * from tblproyectos;
call spAgregarProyecto('Nombre','Christian',now(),now(),'Descripcion');
call spCompletarTarea(1);