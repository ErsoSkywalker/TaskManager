/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BD.cDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author conti
 */
public class GimmeProyect extends HttpServlet {

    cDatos bd = new cDatos();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GimmeProyect</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GimmeProyect at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();

        try {
            bd.conectar();
            ResultSet res = bd.consulta("call spDesplegarProyectoByID(" + request.getParameter("idProject") + ");");
            while (res.next()) {
                out.println("<div class=\"uk-background-secondary uk-light uk-padding uk-panel\">\n"
                        + "                <div align=\"center\">\n"
                        + "                    <h1 id=\"TituloProyecto\">\n"
                        + "                        " + res.getString("NombreProyecto") + "\n"
                        + "                    </h1>\n"
                        + "                </div>\n"
                        + "                <h4>Responsable del Proyecto</h4>\n"
                        + "                <p id=\"Responsable\">\n"
                        + "                    " + res.getString("ResponsableProyecto") + "\n"
                        + "                </p>\n"
                        + "                <h4>Descripción de la Proyecto</h4>\n"
                        + "                <p id=\"DescripcionProyecto\">\n"
                        + "                    " + res.getString("Descripcion") + "\n"
                        + "                </p>\n"
                        + "\n"
                        + "                <h4>Tareas</h4>\n"
                        + "\n"
                        + " <div id=\"tabla\">               <table class=\"uk-table uk-table-middle uk-table-divider\" id=\"Tareas\">\n"
                        + "                    <thead>\n"
                        + "                        <tr>\n"
                        + "                            <th class=\"uk-width-small\">Numero de tarea</th>\n"
                        + "                            <th>Nombre Tarea</th>\n"
                        + "                            <th>Descripción tarea</th>\n"
                        + "                            <th>Fecha Inicio</th>\n"
                        + "                            <th>Fecha Final</th>\n"
                        + "                            <th>Estatus</th>\n"
                        + "                        </tr>\n"
                        + "                    </thead>\n"
                        + "                    <tbody>\n");

                ResultSet res2 = bd.consulta("call spDesplegarTareasProyecto(" + request.getParameter("idProject") + ");");
                while (res2.next()) {
                    out.println(
                            "                        <tr>\n"
                            + "                            <td>" + res2.getString("idTarea") + "</td>\n"
                            + "                            <td>" + res2.getString("NombreTarea") + "</td>\n"
                            + "                            <td>" + res2.getString("DescripcionTarea") + "</td>\n"
                            + "                            <td>" + res2.getString("FechaInicio") + "</td>\n"
                            + "                            <td>" + res2.getString("FechaFinal") + "</td>\n"
                            + "                            <td>" + res2.getString("Estatus") + "</td>\n");
                            if(res2.getString("Estatus").equalsIgnoreCase("Activa"))
                                out.println("<td><button class=\"uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom\" id=\"botonCompletar\">Completar</button></td>\n");
                            out.println("                        </tr>\n");
                }

                out.println("                    </tbody>\n"
                        + "                </table> </div>\n"
                        + "\n"
                        + "                <h4>\n"
                        + "                    Progreso\n"
                        + "                </h4>\n"
                        + "                <progress id=\"js-progressbar\" class=\"uk-progress\" value=\"55\" max=\"100\"></progress>\n"
                        + "\n"
                        + "            </div>\n"
                        + "\n"
                        + "            <div class=\"uk-container\">\n"
                        + "\n"
                        + "\n"
                        + "                <ul uk-accordion>\n"
                        + "                    <li>\n"
                        + "                        <a class=\"uk-accordion-title\" href=\"#\" style=\"color: #ffffff\">Agregar Tarea</a>\n"
                        + "                        <div class=\"uk-accordion-content\">\n"
                        + "                            <div class=\"uk-background-secondary uk-light uk-padding uk-panel\">\n"
                        + "                                <div align=\"center\">\n"
                        + "                                    <h1>\n"
                        + "                                        Crear Tarea\n"
                        + "                                    </h1>\n"
                        + "\n"
                        + "\n"
                        + "                                    <div class=\"uk-margin\">\n"
                        + "                                        <h4>Nombre Tarea: </h4><input class=\"uk-input uk-form-width-large\" type=\"text\" name=\"nombre\" id=\"nombre\" placeholder=\"Inserta tu texto\">\n"
                        + "                                    </div>\n"
                        + "\n"
                        + "                                    <div class=\"uk-margin\">\n"
                        + "                                        <h4>Fecha Inicio: </h4><input class=\"uk-input uk-form-width-large\" type=\"date\" name=\"fechaInicio\" id=\"fechaInicio\" placeholder=\"Inserta tu fecha\">\n"
                        + "                                    </div>\n"
                        + "\n"
                        + "                                    <div class=\"uk-margin\">\n"
                        + "                                        <h4>Fecha Final: </h4><input class=\"uk-input uk-form-width-large\" name=\"fechaFinal\" id=\"fechaFinal\" type=\"date\" placeholder=\"Inserta tu fecha\">\n"
                        + "                                    </div>\n"
                        + "\n"
                        + "                                    <div class=\"uk-margin\">\n"
                        + "                                        <h4>Descripción: </h4><textarea class=\"uk-input uk-form-width-large\" name=\"descripcion\" id=\"descripcion\" placeholder=\"Inserta tu texto\"></textarea>\n"
                        + "                                    </div>\n"
                        + "                                    <button class=\"uk-button uk-button-primary uk-button-large\" id=\"agregar\" onclick=\"agregarTarea("+request.getParameter("idProject")+")\">Agregar</button>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                    </li>\n"
                        + "                </ul>\n"
                        + "\n"
                        + "\n"
                        + "            </div>");
            }
            bd.cierraConexion();
        } catch (SQLException ex) {
            Logger.getLogger(GimmeProyect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
