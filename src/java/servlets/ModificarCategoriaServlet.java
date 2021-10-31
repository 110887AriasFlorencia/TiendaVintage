/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.GestorDB;
import modelo.Categoria;

/**
 *
 * @author arias
 */
@WebServlet(name = "ModificarCategoriaServlet", urlPatterns = {"/ModificarCategoriaServlet"})
public class ModificarCategoriaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
            GestorDB g = new GestorDB();
            String idModificar = (String) request.getParameter("id");

            Categoria categoria = g.obtenerCategoria(Integer.parseInt(idModificar));

            request.setAttribute("id", idModificar);
            request.setAttribute("nombre", categoria.getNombre());
            

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/modificarCategoria.jsp");
            rd.forward(request, response);
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

		GestorDB g = new GestorDB();

		int id = Integer.parseInt((String) request.getParameter("txtId"));
		String nombre = request.getParameter("txtNombre");
                
		g.modificarCategoria(new Categoria(id, nombre));

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListadoCategoriaServlet");
		rd.forward(request, response);
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
