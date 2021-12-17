/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Consulta;
import modelo.GestorDB;
import modelo.Producto;
import modelo.Reclamo;
import modelo.Venta;

/**
 *
 * @author arias
 */
@WebServlet(name = "ReclamoServlet", urlPatterns = {"/ReclamoServlet"})
public class ReclamoServlet extends HttpServlet {

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
        GestorDB g = new GestorDB();
        String id = (String) request.getParameter("id");
        
        Venta c = g.obtenerVenta(Integer.parseInt(id));
        request.setAttribute("compra", c);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/reclamo.jsp");
        rd.forward(request, response);
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
        GestorDB g = new GestorDB();
        String id = (String) request.getParameter("id");
        String reclamo = request.getParameter("reclamo");
        
        Venta compra = g.obtenerVenta(Integer.parseInt(id));
        Reclamo r = new Reclamo(0, reclamo, compra, compra.getComprador(), compra.getVendedor());
        g.insertarReclamo(r);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/MisComprasServlet");
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
