package dev.lyt.onlineshop.dao;

import dev.lyt.onlineshop.model.Clothes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XuanRan on 2022年5月24日
 * 服装DAO
 */
public class ClothesDao {


    /**
     * 根据ID查询服装
     */
    public static Clothes searchById(int id) {
        String sql = "SELECT * FROM clothes WHERE f_id=?";
        try {
            Connection conn = SqlHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Clothes clothes = new Clothes();
                clothes.setF_id(rs.getInt("f_id"));
                clothes.setF_name(rs.getString("f_name"));
                clothes.setF_content(rs.getString("f_content"));
                clothes.setPrice(rs.getInt("price"));
                clothes.setF_image(rs.getString("f_image"));
                return clothes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加新的服装
     *
     * @param clothes bean
     * @return 结果
     */
    public boolean insertClothes(Clothes clothes) {
        String sql = "insert into clothes(f_image,f_name,price,f_content) values(?,?,?,?)";
        Object[] ps = new Object[4];
        ps[0] = clothes.getF_image();
        ps[1] = clothes.getF_name();
        ps[2] = clothes.getPrice();
        ps[3] = clothes.getF_content();
        SqlHelper.executeUpdate(sql, ps);
        return true;
    }

    /**
     * 修改服装信息
     *
     * @param clothes 对象
     * @return 结果
     */
    public boolean updateClothes(Clothes clothes) {
        String sql = "update clothes set f_image=?,f_name=?,price=?,f_content=? where f_id=?";
        Object[] ps = new Object[5];
        ps[0] = clothes.getF_image();
        ps[1] = clothes.getF_name();
        ps[2] = clothes.getPrice();
        ps[3] = clothes.getF_content();
        ps[4] = clothes.getF_id();
        SqlHelper.executeUpdate(sql, ps);
        return true;
    }

    /*
     * 删除服装
     * */
    public boolean delByFid(Integer id) {
        String sql = "delete from clothes where f_id = ?";
        SqlHelper.executeUpdate(sql, new Object[]{id});
        return true;
    }

    /**
     * 通过key模糊查询
     *
     * @param key 关键字
     * @return 结果
     */
    public List<Clothes> findMeu(String key) {
        String sql = "select * from clothes where f_name like ?";
        String[] ps = new String[1];
        ps[0] = "%" + key + "%";
        ResultSet resultSet = SqlHelper.executeQuery(sql, ps);
        List<Clothes> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Clothes clothes = new Clothes();
                clothes.setF_id(resultSet.getInt("f_id"));
                clothes.setF_name(resultSet.getString("f_name"));
                clothes.setF_content(resultSet.getString("f_content"));
                clothes.setPrice(resultSet.getInt("price"));
                clothes.setF_image(resultSet.getString("f_image"));
                list.add(clothes);
            }

        } catch (Exception er) {
            er.printStackTrace();
        }
        return list;
    }

    /**
     * 查询所有的服装信息
     *
     * @return 结果
     */
    public List<Clothes> findAll() {
        String sql = "select * from clothes";
        ResultSet resultSet = SqlHelper.executeQuery(sql, null);
        List<Clothes> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Clothes clothes = new Clothes();
                clothes.setF_id(resultSet.getInt("f_id"));
                clothes.setF_name(resultSet.getString("f_name"));
                clothes.setF_content(resultSet.getString("f_content"));
                clothes.setPrice(resultSet.getInt("price"));
                clothes.setF_image(resultSet.getString("f_image"));

                list.add(clothes);
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return list;
    }
}
