package dev.lyt.onlineshop.dao;

import dev.lyt.onlineshop.model.Orders;
import dev.lyt.onlineshop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao {
    /*
     * admin订单更新
     * */
    public boolean adminUpdate(Orders orders) {
        String sql = "update orders set pzstate=? where o_id=?";
        Object[] ps = new Object[2];
        ps[0] = orders.getPzstate();
        ps[1] = orders.getO_id();
        SqlHelper.executeUpdate(sql, ps);
        return true;
    }

    /**
     * 根据ID查询订单
     */
    public Orders searchById(Integer id) {
        String sql = "SELECT * FROM orders WHERE o_id=?";
        String[] ps = new String[1];
        ps[0] = id.toString();

        ResultSet resultSet = SqlHelper.executeQuery(sql, ps);
        try {
            while (resultSet.next()) {
                Orders orders = new Orders();
                orders.setO_id(resultSet.getInt("o_id"));
                orders.setO_num(resultSet.getInt("o_num"));
                orders.setMarkup(resultSet.getString("markup"));
                orders.setState(resultSet.getString("state"));
                orders.setPzstate(resultSet.getString("pzstate"));
                return orders;
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    /*
     * 管理员订单信息用户id模糊查询
     * */
    // 服装名的模糊查询
    public List<Orders> findOreders(String key) {
        String sql = "select orders.o_id,user.loginId,clothes.f_name,orders.o_num,clothes.price * orders.o_num as num,orders.markup,orders.state,orders.pzstate,user.mobile \n" +
                "from orders join user on orders.u_id = user.u_id join clothes on orders.f_id = clothes.f_id where loginId like ?;";
        String[] ps = new String[1];
        ps[0] = "%" + key + "%";
        ResultSet resultSet = SqlHelper.executeQuery(sql, ps);
        List<Orders> findOrdersList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Orders orders = new Orders();
                orders.setO_id(resultSet.getInt("o_id"));
                orders.setLoginId(resultSet.getString("loginId"));
                orders.setF_name(resultSet.getString("f_name"));
                orders.setO_num(resultSet.getInt("o_num"));
                orders.setNum(resultSet.getInt("num"));
                orders.setMarkup(resultSet.getString("markup"));
                orders.setState(resultSet.getString("state"));
                orders.setPzstate(resultSet.getString("pzstate"));
                orders.setMobile(resultSet.getString("mobile"));
                findOrdersList.add(orders);
            }

        } catch (Exception er) {
            er.printStackTrace();
        }
        return findOrdersList;
    }

    /*
     * admin删除订单
     * */
    public boolean delByOid(Integer id) {
        String sql = "delete from orders where o_id = ?";
        SqlHelper.executeUpdate(sql, new Object[]{id});
        return true;
    }

    /*
     * 管理员订单信息
     * */
    public List<Orders> findAllorders() {
        String sql = "select orders.o_id,user.loginId,clothes.f_name,orders.o_num,clothes.price * orders.o_num as num,orders.markup,orders.state,orders.pzstate,user.mobile \n" +
                "from orders join clothes on orders.f_id = clothes.f_id join user on orders.u_id = user.u_id;";
        ResultSet resultSet = SqlHelper.executeQuery(sql, null);
        List<Orders> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Orders orders = new Orders();
                orders.setO_id(resultSet.getInt("o_id"));
                orders.setLoginId(resultSet.getString("loginId"));
                orders.setF_name(resultSet.getString("f_name"));
                orders.setO_num(resultSet.getInt("o_num"));
                orders.setNum(resultSet.getInt("num"));
                orders.setMarkup(resultSet.getString("markup"));
                orders.setState(resultSet.getString("state"));
                orders.setPzstate(resultSet.getString("pzstate"));
                orders.setMobile(resultSet.getString("mobile"));
                list.add(orders);
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return list;
    }

    /**
     * 删除订单
     */
    public int delete(Orders orders) {
        String sql = "DELETE FROM orders WHERE o_id=?";
        int i = 0;
        try {
            Connection conn = SqlHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orders.getO_id());
            i = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 更新订单
     */
    public int update(Orders orders) {
        String sql = "UPDATE orders SET u_id=?, f_id=?, o_num=?, markup=?, state=?,pzstate=?,pd=? WHERE o_id=?";
        int i = 0;
        try {
            Connection conn = SqlHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orders.getUser().getU_id());
            ps.setInt(2, orders.getClothes().getF_id());
            ps.setInt(3, orders.getO_num());
            ps.setString(4, orders.getMarkup());
            ps.setString(5, orders.getState());
            ps.setString(6, orders.getPzstate());
            ps.setString(7, orders.getPd());
            ps.setInt(8, orders.getO_id());
            i = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 查询正在配送的订单
     */
    public List<Orders> searchPaidList(User user) {
        String sql = "SELECT * FROM orders WHERE u_id=? and pzstate=?";
        List<Orders> list = new ArrayList<>();
        ClothesDao dao = new ClothesDao();
        try {
            Connection conn = SqlHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getU_id());
            ps.setString(2, "正在配送");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setO_id(rs.getInt("o_id"));
                orders.setUser(UserDao.searchById(rs.getInt("u_id")));
                System.out.println("userId: " + orders.getUser().getU_id());
                System.out.println("clothesId: " + rs.getInt("f_id"));
                orders.setClothes(ClothesDao.searchById(rs.getInt("f_id")));
                orders.setO_num(rs.getInt("o_num"));
                orders.setMarkup(rs.getString("markup"));
                orders.setState(rs.getString("state"));
                orders.setPzstate(rs.getString("pzstate"));
                list.add(orders);
                System.out.println("menuId: " + orders.getClothes().getF_id());
                System.out.println("menuName: " + orders.getClothes().getF_name());
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询等待配送的订单
     */
    public List<Orders> searchBuyPd(User user) {
        ClothesDao dao = new ClothesDao();
        String sql = "SELECT * FROM orders WHERE u_id=? and pd=? and pzstate=?";
        List<Orders> list = new ArrayList<>();
        try {
            Connection conn = SqlHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getU_id());
            ps.setString(2, "是");
            ps.setString(3, "等待配送");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setO_id(rs.getInt("o_id"));
                orders.setUser(UserDao.searchById(rs.getInt("u_id")));
                orders.setClothes(dao.searchById(rs.getInt("f_id")));
                orders.setO_num(rs.getInt("o_num"));
                orders.setMarkup(rs.getString("markup"));
                orders.setState(rs.getString("state"));
                orders.setPzstate(rs.getString("pzstate"));
                orders.setPd(rs.getString("pd"));
                list.add(orders);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询购物车
     */
    public List<Orders> searchBuy(User user) {
        ClothesDao dao = new ClothesDao();
        String sql = "SELECT * FROM orders WHERE u_id=? and pd=?";
        List<Orders> list = new ArrayList<>();
        try {
            Connection conn = SqlHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getU_id());
            ps.setString(2, "否");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setO_id(rs.getInt("o_id"));
                orders.setUser(UserDao.searchById(rs.getInt("u_id")));
                orders.setClothes(dao.searchById(rs.getInt("f_id")));
                orders.setO_num(rs.getInt("o_num"));
                orders.setMarkup(rs.getString("markup"));
                orders.setState(rs.getString("state"));
                orders.setPzstate(rs.getString("pzstate"));
                orders.setPd(rs.getString("pd"));
                list.add(orders);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 保存订单
     */
    public void save(Orders orders) {
        String sql = "INSERT INTO orders(u_id, f_id, o_num, markup,state,pzstate,pd) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conn = SqlHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orders.getUser().getU_id());
            ps.setInt(2, orders.getClothes().getF_id());
            ps.setInt(3, orders.getO_num());
            ps.setString(4, orders.getMarkup());
            ps.setString(5, orders.getState());
            ps.setString(6, orders.getPzstate());
            ps.setString(7, orders.getPd());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
