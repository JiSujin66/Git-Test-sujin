import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static final String DB_NAME = "kiosk.db";

    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\402-028\\desktop\\" + DB_NAME;

    public static final String TABLE_MEMBERS="members";
    public static final String TABLE_AMOUNTS="amounts";
    public static final String TABLE_MENU="menu";
    public static final String TABLE_OPTION="option_choice";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_AMOUNT="amount";
    public static final String COLUMN_TIME="time";
    public static final String COLUMN_PHONE_NUMBER="phone_number";
    public static final String COLUMN_TOTAL_AMOUNT="total_amount";
    public static final String COLUMN_COUNTS="counts";
    public static final String COLUMN_GRADE="grade";
    public static final String COLUMN_FIRST_CHOICE="first_choice";
    public static final String COLUMN_SECOND_CHOICE="second_choice";
    public static final String COLUMN_PRICE="price";
    public static final String COLUMN_CHOICE="choice";
    public static final String COLUMN_ADD_CHOICE="add_choice";
    public static final String COLUMN_ADD_PRICE="add_price";

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("It was connection");
            return true;

        } catch (SQLException e) {
            System.out.println("Couldn't connect database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection:" + e.getMessage());
        }
    }
    public List<members> members(Integer phone) {
        StringBuilder sc = new StringBuilder();



        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sc.toString())) {
            System.out.println(sc.toString() + "\n");

            List<members> M = new ArrayList<>();


            while (results.next()) {
                members m = new members();

                m.setPhone_number(results.getInt(COLUMN_PHONE));
                m.setCounts(results.getInt(COLUMN_COUNTS));
                m.setGrade(results.getString(COLUMN_GRADE));
                m.setTotal_amount(results.getInt(COLUMN_TOTAL_AMOUNT));


                M.add(m);

            }
            return M;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<amounts> amounts(String second_choice,String add_choice) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT "+ COLUMN_SECOND_CHOICE +", "+
                COLUMN_PRICE+"+"+COLUMN_ADD_PRICE+" AS "+"amount"+
                " FROM "+TABLE_MENU+" JOIN "+TABLE_OPTION +
                " ON "+TABLE_MENU+"."+COLUMN_FIRST_CHOICE+"="+TABLE_OPTION+"."+COLUMN_CHOICE +
                " WHERE "+TABLE_MENU+"."+COLUMN_SECOND_CHOICE+"="+"\'" + second_choice + "\'" +
                " AND "+TABLE_OPTION+"."+COLUMN_ADD_CHOICE+"="+"\'" + add_choice + "\'");


        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            System.out.println(sb.toString() + "\n");

            List<amounts> A = new ArrayList<>();


            while (results.next()) {
                amounts a = new amounts();

             //   a.setPhone(results.getInt(COLUMN_PHONE));
                a.setAmount(results.getInt(COLUMN_AMOUNT));
                //a.setTime(results.getInt(COLUMN_TIME));
                a.setSecond_choice(results.getString(COLUMN_SECOND_CHOICE));


                A.add(a);

            }
            return A;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<menu> menu() {
        StringBuilder sd = new StringBuilder();


        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sd.toString())) {
            System.out.println(sd.toString() + "\n");

            List<menu> E = new ArrayList<>();


            while (results.next()) {
                menu e = new menu();

                e.setFirst_choice(results.getString(COLUMN_FIRST_CHOICE));
                e.setSecond_choice(results.getString(COLUMN_SECOND_CHOICE));
                e.setPrice(results.getInt(COLUMN_PRICE));


                E.add(e);

            }
            return E;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<option_choice> option_choice() {   //int 로 받음
        StringBuilder se = new StringBuilder();


        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(se.toString())) {
            System.out.println(se.toString() + "\n");

            List<option_choice> O = new ArrayList<>();


            while (results.next()) {
                option_choice o =new option_choice();

                o.setChoice(results.getString(COLUMN_CHOICE));
                o.setAdd_choice(results.getString(COLUMN_ADD_CHOICE));
                o.setAdd_price(results.getInt(COLUMN_PRICE));



                O.add(o);

            }
            return O;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
