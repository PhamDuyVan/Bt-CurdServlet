/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import my.common.Database;

/**
 *
 * @author mygam
 */
public class Editservlet extends HttpServlet {

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
            String method = request.getMethod();
            if (method.equalsIgnoreCase("get")) {
                
                String id = request.getParameter("id");
                java.sql.Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    conn = Database.getConnection();
                    ps = conn.prepareStatement("Select * from users where id=" + id);
                    //ps.setInt(1,Integer.parseInt(id));
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        String country=rs.getString(5);
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Update Users</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Update User</h1>");
                        out.println("<form action=\"EditServlet\" method=\"POST\">\n"
                                + "<input type=hidden name='id' value=" + id + ">"
                                + "            <table border=\"0\">\n"
                                + "                <tr>\n"
                                + "                    <td>Name</td>\n"
                                + "                    <td><input type=\"text\" name=\"uname\" value=" + rs.getString(2) + "></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td>Password</td>\n"
                                + "                    <td><input type=\"password\" name=\"upass\" value=" + rs.getString(3) + "required></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td>Email</td>\n"
                                + "                    <td><input type=\"email\" name=\"email\" value=" + rs.getString(4) + "></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td>Country</td>\n"
                                + "                    <td><select name=\"country\">\n"
                                + "                            <option value=\"VietNam\" " +(country.equals("VietNam")?"selected":"")+">VietNam</option>\n"
                                + "                            <option value=\"USA\" " +(country.equals("USA")?"selected":"")+">USA</option>\n"
                                + "                            <option value=\"UK\" " +(country.equals("UK")?"selected":"")+">UK</option>\n"
                                + "                            <option value=\"other\" " +(country.equals("other")?"selected":"")+">other</option>\n"
                                + "                        </select></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td colspan=\"2\"><input type=\"submit\" value=\"Edit & Save\" /></td>\n"
                                + "                </tr>\n"
                                + "            </table>\n"
                                + "        </form>\n"
                                + "");
                        out.println("</body>");
                        out.println("</html>");

                    }
                } catch (Exception e) {
                    System.out.println("Loi: " + e.toString());
                    out.println("<h2>Thao tác user thất bại</h2>");

                }
                //Chèn nội dung viewservlet vào kq phản hồi
            } else if (method.equalsIgnoreCase("post")) {

                String uname = request.getParameter("uname");
                String upass = request.getParameter("upass");
                String email = request.getParameter("email");
                String country = request.getParameter("country");
                String id = request.getParameter("id");
                //Xử lý yêu câu
                java.sql.Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = Database.getConnection();
                    ps = conn.prepareStatement("update users set name=?, password=?, email=?, country=? where id=?");
                    ps.setString(1, uname);
                    ps.setString(2, upass);
                    ps.setString(3, email);
                    ps.setString(4, country);
                    ps.setInt(5, Integer.parseInt(id));
                    int kq = ps.executeUpdate();
                    if (kq > 0) {
                        out.println("<h2>Cập nhật user thành công</h2>");
                    } else {
                        out.println("<h2>Cập nhật user thất bại</h2>");
                    }
                } catch (Exception e) {
                    System.out.println("Loi: " + e.toString());
                    out.println("<h2>Cập nhật user thất bại</h2>");
                }
                request.getRequestDispatcher("ViewServlet").include(request, response);
            }
            /* TODO output your page here. You may use following sample code. */
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
        //PrintWriter out=response.getWriter();
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
        processRequest(request, response);
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