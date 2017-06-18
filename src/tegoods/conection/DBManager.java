package tegoods.conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author igor
 * pattern Singleton
 */
public class DBManager {
    private final String PATH_TO_CONFIG_FILE = "config/config.dat";
    private static DBManager instance = null;
    private final Connection con;
    private Statement cmd;
    private String user;
    private String pass;
    private String server;
    private String port;
    private String database;
    public static DBManager getInstance() throws ClassNotFoundException, SQLException, IOException{
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }
    
    private DBManager() throws ClassNotFoundException, SQLException, IOException{
        readSettings(PATH_TO_CONFIG_FILE);
        String url = "jdbc:mysql://" + server +
          ":" + port + "/" + database;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
    }
    
    public ResultSet executeSelect(String sql) throws SQLException{
        cmd = con.prepareStatement(sql);
        System.out.println("SQL = " + sql);
        ResultSet rs = cmd.executeQuery(sql);
        return rs;
    }
    
    public ResultSet executeSelect(PreparedStatement sql) throws SQLException{
        System.out.println("SQL = " + sql.toString());
        ResultSet rs = sql.executeQuery();
        return rs;
    }
    
    public void update(String sql) throws SQLException{
        System.out.println("SQL = " + sql);
        cmd = con.prepareStatement(sql);
        cmd.executeUpdate(sql);
        disconect();
    }
    
    public void update(PreparedStatement sql) throws SQLException{
        System.out.println("SQL = " + sql.toString());
        sql.executeUpdate();
    }
    
    public void disconect() throws SQLException{
        cmd.close();
    }
    
    public Connection getConnection(){
        return con;
    }
    
    private enum LoadState{
        SERVER,
        PORT,
        USER,
        PASSWORD,
        DATABASE,
        STOP
    }
    
    private void readSettings(String path) throws UnsupportedEncodingException, IOException{
        InputStream is = getClass().getResourceAsStream(path);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String str;
            LoadState ls;
            while((str = br.readLine()) != null){
                if(str.equalsIgnoreCase("[Server]")){
                    ls = LoadState.SERVER;
                } else if(str.equalsIgnoreCase("[Port]")){
                    ls = LoadState.PORT;
                } else if(str.equalsIgnoreCase("[User]")){
                    ls = LoadState.USER;
                } else if(str.equalsIgnoreCase("[Password]")){
                    ls = LoadState.PASSWORD;
                } else if(str.equalsIgnoreCase("[Database]")){
                    ls = LoadState.DATABASE;
                } else {
                    ls = LoadState.STOP;
                }
                str = br.readLine();
                switch(ls){
                    case SERVER :
                        server = str;
                        break;
                    case PORT :
                        port = str;
                        break;
                    case USER :
                        user = str;
                        break;
                    case PASSWORD :
                        pass = str;
                        break;
                    case DATABASE :
                        database = str;
                        break;
                    case STOP :
                        break;
                }
            }
        } 
    }
    
//    public static void main(String[] args) {
//        try {
//            DBManager manager = DBManager.getInstance();
//            ResultSet rs = manager.executeSelect("select * from goods");
//            while(rs.next()){
//                System.out.println("id = " + rs.getInt(1));
//                System.out.println("name = " + rs.getString(2));
//                System.out.println("price = " + rs.getFloat(3));
//                System.out.println("date = " + rs.getDate(4).toString());
//            }
//            manager.disconect();
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
