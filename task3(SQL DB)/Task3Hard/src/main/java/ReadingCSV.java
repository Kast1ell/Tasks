import POJOS.Logins;
import POJOS.Postings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadingCSV {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost:1433;database=task3;user=maxim;" +
            "password=24122002;TrustServerCertificate=True";
    public Statement state;
    public String name;
    private static Statement stmt;
    private static ResultSet rs;
    static String pathLogins = "C:\\Users\\User\\Desktop\\Tasks\\Task3Hard\\src\\main\\resources\\logins.csv";
    static String pathPostings = "C:\\Users\\User\\Desktop\\Tasks\\Task3Hard\\src\\main\\resources\\postings.csv";

    public static ArrayList<Logins> logins = new ArrayList<Logins>();
    public static ArrayList<Postings> postings = new ArrayList<Postings>();
    public static ArrayList<Postings> postingsCorrect = new ArrayList<Postings>();

    public void Connecting(ArrayList<Postings> pstss) throws ClassNotFoundException {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dburl);
            System.out.println("Connected");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM postings");
            stmt.executeUpdate("DELETE FROM logins");
            for (int i = 1; i < pstss.size(); i++) {
                Postings p = pstss.get(i);
                String q1 = "insert into postings values ('" + p.getMatDoc() + "', '" + p.getItem() + "', '" + p.getDocDate() + "', '" + p.getPstngDate() +
                        "', '" + p.getMaterialDescription() + "', '" + p.getQuantity() + "', '" + p.getBUn() + "', '" +
                       p.getAmountLC() + "', '" + p.getCrcy() + "', '" + p.getUserName() + "', '" + String.valueOf(p.getAutorized()) + "')";
                stmt.executeUpdate(q1);
            }
            for (int i = 1; i < logins.size(); i++) {
                Logins p = logins.get(i);
                String q1 = "insert into logins values ('" + p.getApplications() + "', '" + p.getAppAccountName() + "', '" + p.getIsActive() + "', '" + p.getJobTitle()
                         + "', '" + p.getDepartment() + "')";
                stmt.executeUpdate(q1);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Logins> getLg() {
        logins = readLogins(pathLogins);
        return logins;
    }

    public ArrayList<Postings> getPs() {
        postings = readPostings(pathPostings);
        return postings;
    }

    public ArrayList<Postings> startAlg() throws ClassNotFoundException {

        postingsCorrect = check(getLg(), getPs());
        Connecting(postingsCorrect);
        return postingsCorrect;
    }
    public ArrayList<Logins> readLogins(String path) {

        ArrayList<Logins> lst1 = new ArrayList<>();
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 20) {
                    String[] values = new String[]{};
                    if (line.split(";").length<3) {
                        values = line.split(",");
                    }
                    else {
                        values = line.split(";");
                    }
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                    }
                    if ((values[2].equals("True"))||(values[2].equals("true")))
                        lst1.add(new Logins(values[0], values[1], true, values[3], values[4]));
                    else
                        lst1.add(new Logins(values[0], values[1], false, values[3], values[4]));
                    String buf = "";
                    for (int i = 0; i < values.length; i++) {
                        buf += " " + values[i];
                    }
                    System.out.println(buf);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst1;
    }

    public ArrayList<Postings> readPostings(String path) {
        ArrayList<Postings> lst2 = new ArrayList<>();
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 20) {
                    String[] values = new String[]{};
                    if (line.split(";").length<3) {
                        values = line.split(",");
                    }
                    else {
                        values = line.split(";");
                    }
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                    }
                    lst2.add(new Postings(values[0], values[1], values[2], values[3], values[4],values[5], values[6], values[7], values[8], values[9]));
                    String buf = "";
                    for (int i = 0; i < values.length; i++) {
                        buf += " " + values[i];
                    }
                    System.out.println(buf);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst2;
    }

    public ArrayList<Postings> check(List<Logins> logs, List<Postings> posts) {
        ArrayList<Postings> postsCor = new ArrayList<>();
        System.out.println("Started");
        System.out.println(logs.size());
        System.out.println(posts.size());
        postsCor.add(posts.get(0));
        for (int i = 1; i < posts.size(); i++) {
            for (int j = 1; j < logs.size(); j++) {
                // System.out.println(posts.get(j).getUserName());
                // System.out.println(logs.get(i).getAppAccountName());
                // System.out.println(logs.get(i).getIsActive());
                if (logs.get(j).getAppAccountName().equals(posts.get(i).getUserName()) && logs.get(j).getIsActive()) {
                    posts.get(i).setAutorized(true);
                    postsCor.add(posts.get(i));
                    System.out.println(posts.get(i).getString());
                }
            }
            if (!posts.get(i).getAutorized()) {
                posts.get(i).setAutorized(false);
                postsCor.add(posts.get(i));
                System.out.println(posts.get(i).getString());
            }

        }
        return postsCor;
    }

    /*public static void main(String[] args) {
        startAlg();
    }*/
}
