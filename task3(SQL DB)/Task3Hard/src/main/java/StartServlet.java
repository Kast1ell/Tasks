import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import POJOS.*;

@WebServlet("/tableWFilter")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf8");
        String filter =req.getParameter("autorized");

        String dc_day = req.getParameter("dc_day");
        String dc_month = req.getParameter("dc_month");
        String dc_year = req.getParameter("dc_year");

        String ps_day = req.getParameter("ps_day");
        String ps_month = req.getParameter("ps_month");
        String ps_year = req.getParameter("ps_year");

        System.out.println(dc_day);
        System.out.println(dc_month);
        System.out.println(dc_year);
        System.out.println(ps_day);
        System.out.println(ps_month);
        System.out.println(ps_year);

        Boolean fltr=null;
        System.out.println(filter);

        if (filter.equals("True") || filter.equals("true") || filter.equals("T") || filter.equals("Tr")) {
            fltr = true;
        }
        else if (filter.equals("False") || filter.equals("false") || filter.equals("F") || filter.equals("f")){
            fltr = false;
        }
        System.out.println(fltr);

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        ReadingCSV cs = new ReadingCSV();
        ArrayList<Postings> psts = null;
        try {
            psts = cs.startAlg();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Postings la = psts.get(0);
        psts.remove(0);
        printWriter.write("<table style=\"border: 1px solid grey;\">");
        printWriter.write("<tr style=\"border: 1px solid grey;\"><th>" + la.getMatDoc() + "</th><th align=\"center\" style=\"border: 1px solid grey;\">" + la.getItem()
                + "</th><th align=\"center\" style=\"border: 1px solid grey;\">" + la.getDocDate() + "</th><th align=\"center\" style=\"border: 1px solid grey;\">"
                + la.getPstngDate() + "</th><th align=\"center\" style=\"border: 1px solid grey;\">" + la.getMaterialDescription() + "</th>" + "<th align=\"center\" style=\"border: 1px solid grey;\">"
                + la.getQuantity() + "</th><th align=\"center\" style=\"border: 1px solid grey;\">" + la.getBUn() + "</th><th align=\"center\" style=\"border: 1px solid grey;\">" + la.getAmountLC() +
                "</th><th align=\"center\" style=\"border: 1px solid grey;\">" + la.getCrcy() + "</th><th align=\"center\" style=\"border: 1px solid grey;\">" + la.getUserName() + "</th><th align=\"center\" style=\"border: 1px solid grey;\">" + "Autorized" + "</th></tr>");
        for (Postings ps:psts) {
            String[] real_ps = ps.getPstngDate().split("\\.");
            String[] real_dc = ps.getDocDate().split("\\.");

            Boolean flDate1 = false;
            Boolean flDate2 = false;

            if (!dc_day.equals("") && !dc_month.equals("") && !dc_year.equals("") && (dc_day.equals(real_dc[0]) && dc_month.equals(real_dc[1]) && dc_year.equals(real_dc[2])))  {
                flDate1=true;
            } else if (!dc_day.equals("") && !dc_month.equals("") && dc_year.equals("") && (dc_day.equals(real_dc[0]) && dc_month.equals(real_dc[1])))  {
                flDate1=true;
            } else if (!dc_day.equals("") && dc_month.equals("") && dc_year.equals("") && (dc_day.equals(real_dc[0])))  {
                flDate1=true;
            } else if (!dc_day.equals("") && dc_month.equals("") && !dc_year.equals("") && (dc_day.equals(real_dc[0]) && dc_year.equals(real_dc[2])))  {
                flDate1=true;
            } else if (dc_day.equals("") && dc_month.equals("") && !dc_year.equals("") && (dc_year.equals(real_dc[2])))  {
                flDate1=true;
            } else if (dc_day.equals("") && !dc_month.equals("") && !dc_year.equals("") && (dc_month.equals(real_dc[1]) && dc_year.equals(real_dc[2])))  {
                flDate1=true;
            } else if (dc_day.equals("") && !dc_month.equals("") && dc_year.equals("") && (dc_month.equals(real_dc[1]))) {
                flDate1=true;
            } else if (dc_day.equals("") && dc_month.equals("") && dc_year.equals("")) {
                flDate1=null;
            }

            if (!ps_day.equals("") && !ps_month.equals("") && !ps_year.equals("") && (ps_day.equals(real_ps[0]) && ps_month.equals(real_ps[1]) && ps_year.equals(real_ps[2])))  {
                flDate2=true;
            } else if (!ps_day.equals("") && !ps_month.equals("") && ps_year.equals("") && (ps_day.equals(real_ps[0]) && ps_month.equals(real_ps[1])))  {
                flDate2=true;
            } else if (!ps_day.equals("") && ps_month.equals("") && ps_year.equals("") && (ps_day.equals(real_ps[0])))  {
                flDate2=true;
            } else if (!ps_day.equals("") && ps_month.equals("") && !ps_year.equals("") && (ps_day.equals(real_ps[0]) && ps_year.equals(real_ps[2])))  {
                flDate2=true;
            } else if (ps_day.equals("") && ps_month.equals("") && !ps_year.equals("") && (ps_year.equals(real_ps[2])))  {
                flDate2=true;
            } else if (ps_day.equals("") && !ps_month.equals("") && !ps_year.equals("") && (ps_month.equals(real_ps[1]) && ps_year.equals(real_ps[2])))  {
                flDate2=true;
            } else if (ps_day.equals("") && !ps_month.equals("") && ps_year.equals("") && (ps_month.equals(real_ps[1]))) {
                flDate2=true;
            } else if (ps_day.equals("") && ps_month.equals("") && ps_year.equals("")) {
                flDate2=null;
            }

            if ((flDate1==null || flDate1) && (flDate2==null || flDate2))
                if (fltr==null) {
                    printWriter.write("<tr><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getMatDoc() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getItem() +
                            "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getDocDate() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getPstngDate() +
                            "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getMaterialDescription() + "</td>" + "<td align=\"center\" style=\"border: 1px solid grey;\">" +
                            ps.getQuantity() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getBUn() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" +
                            ps.getAmountLC() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getCrcy() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getUserName() + "</td>" +
                            "<td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getAutorized() + "</td></tr>");
                }
                else {
                        if (ps.getAutorized()==fltr) {
                            System.out.println(ps.getUserName());
                            printWriter.write("<tr><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getMatDoc() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getItem() +
                                    "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getDocDate() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getPstngDate() +
                                    "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getMaterialDescription() + "</td>" + "<td align=\"center\" style=\"border: 1px solid grey;\">" +
                                    ps.getQuantity() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getBUn() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" +
                                    ps.getAmountLC() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getCrcy() + "</td><td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getUserName() + "</td>" +
                                    "<td align=\"center\" style=\"border: 1px solid grey;\">" + ps.getAutorized() + "</td></tr>");
                        }
                }
        }
        printWriter.write("</table>");
        printWriter.close();
    }
}
