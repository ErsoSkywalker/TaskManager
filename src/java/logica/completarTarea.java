/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BD.cDatos;
/**
 *
 * @author conti
 */
public class completarTarea extends HttpServlet {
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
            out.println("<title>Servlet completarTarea</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet completarTarea at " + request.getContextPath() + "</h1>");
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
            out.println("<table class=\"uk-table uk-table-middle uk-table-divider\" id=\"Tareas\">\n"
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
                        
                ResultSet res2 = bd.consulta("call spCompletarTarea("+request.getParameter("idTarea")+", "+request.getParameter("idProject")+");");
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
                                out.println("<td><button class=\"uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom\" id=\"botonCompletar\" onclick=\"completarTarea("+res2.getString("idTarea")+")\">Completar</button></td>\n");
                            out.println("                        </tr>\n");
                }

                out.println("                    </tbody>\n"
                        + "                </table>");
                bd.cierraConexion();
        } catch (SQLException ex) {
            Logger.getLogger(agregarTarea.class.getName()).log(Level.SEVERE, null, ex);
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
