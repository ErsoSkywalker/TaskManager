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
public class ProyectosMain extends HttpServlet {

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
            out.println("<title>Servlet ProyectosMain</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProyectosMain at " + request.getContextPath() + "</h1>");
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
        out.println("<table class=\"uk-table uk-table-middle uk-table-divider\">\n"
                + "                            <thead>\n"
                + "                                <tr>\n"
                + "                                    <th class=\"uk-width-small\">Numero de Proyecto</th>\n"
                + "                                    <th>Descripción Proyecto</th>\n"
                + "                                    <th>Responsable</th>\n"
                + "                                    <th>Ver</th>\n"
                + "                                    <th>Progreso</th>\n"
                + "                                </tr>\n"
                + "                            </thead>\n"
                + "                            <tbody>");

        try {
            bd.conectar();

            ResultSet res = bd.consulta("call spDesplegarProyecto();");
            while (res.next()) {
                out.println("<tr>\n"
                        + "                                    <td>"+res.getString("idProyecto")+"</td>\n"
                        + "                                    <td>"+res.getString("NombreProyecto")+"</td>\n"
                        + "                                    <th>"+res.getString("ResponsableProyecto")+"</th>\n"
                        + "\n"
                        + "                                    <td>\n"
                        + "                                        <div class=\"uk-animation-toggle\" tabindex=\"0\">\n"
                        + "                                            <div class=\"uk-animation-shake\">\n"
                        + "                                                <form action=\"Proyecto.html\" method=\"Get\">\n"
                        + "                                                    <input type=\"hidden\" value=\""+res.getString("idProyecto")+"\" name=\"proyecto\">\n"
                        + "                                                    <button class=\"uk-button uk-button-default\" type=\"submit\" >Ver</button>\n"
                        + "                                                </form>\n"
                        + "\n"
                        + "                                            </div>\n"
                        + "                                        </div>\n"
                        + "                                    </td>\n"
                        + "                                    <td><progress id=\"js-progressbar\" class=\"uk-progress\" value=\"55\" max=\"100\"></progress></td>\n"
                        + "                                </tr>");
            }
            bd.cierraConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ProyectosMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.println("</tbody>\n"
                + "                        </table>");

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
