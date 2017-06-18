/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tegoods.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tegoods.model.Product;
import tegoods.conection.DBManager;

/**
 *
 * @author igor
 */
public class ProductDAO implements DAO{
    DBManager manager;
    String lastQuerySQL = "select * from goods;";
    public ProductDAO() throws ClassNotFoundException, SQLException, IOException {
        manager = DBManager.getInstance();
    }
    
    @Override
    public void insert(Product p)  throws  SQLException{
        Connection con = manager.getConnection();
        try (PreparedStatement cmd = con.prepareStatement("insert into goods() "
                + "values(default,?,?,?,?)")) {
            cmd.setString(1, p.getName());
            cmd.setDouble(2, p.getPrice());
            cmd.setString(3, p.getAddDate());
            ByteArrayInputStream is = new ByteArrayInputStream(p.getPicture());
            cmd.setBlob(4, is);
            cmd.executeUpdate();
        }
    }

    @Override
    public void update(Product p) throws SQLException {
        Connection con = manager.getConnection();
        try(PreparedStatement cmd = con.prepareStatement("update goods set name ="
                + "?, price = ?, add_date = ?, image = ? where id = ?")){
            cmd.setString(1, p.getName());
            cmd.setDouble(2, p.getPrice());
            cmd.setString(3, p.getAddDate());
            ByteArrayInputStream is = new ByteArrayInputStream(p.getPicture());
            cmd.setBlob(4, is);
            cmd.setInt(5, p.getId());
            cmd.executeUpdate();            
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection con = manager.getConnection();
        String sql = "delete from goods where id = " + id;
        manager.update(sql);
    }

    @Override
    public List<Product> search(String regExp, boolean considerPrice,
            double priceMin, double priceMax) throws SQLException {
        List<Product> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder("select * from goods where"
                + " name like '%" + regExp + "%'");
        if(considerPrice){
            sb.append(" and (price >= ").append(priceMin).append(" and price <= ").append(priceMax).append(");");
        } else {
            sb.append(";");
        }
        lastQuerySQL = sb.toString();
        ResultSet rs = manager.executeSelect(sb.toString());
        while(rs.next()){
            list.add(getProductFromResultSet(rs));
        }
        return list;
    }

    @Override
    public List<Product> getList() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "select * from goods;";
        ResultSet rs = manager.executeSelect(sql);
        while(rs.next()){
            list.add(getProductFromResultSet(rs));
        }
        return list;
    }

    @Override
    public List<Product> reloadLastSearchResults() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "select * from goods;";
        ResultSet rs = manager.executeSelect(lastQuerySQL);
        while (rs.next()) {
            list.add(getProductFromResultSet(rs));
        }
        return list;
    }
    
    private Product getProductFromResultSet(ResultSet rs) throws SQLException{
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setAddDate(rs.getString("add_date"));
        p.setPicture(rs.getBytes("image"));
        return p;
    }
}

