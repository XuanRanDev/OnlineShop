package dev.lyt.onlineshop.model;

import dev.lyt.onlineshop.dao.OrdersDao;

import java.util.List;

public class Orders {
    /**
     * 显示购物车列表
     *
     * @return
     */
    OrdersDao dao = new OrdersDao();
    private Integer o_id;   //订单id
    private User user;   //用户id
    private Clothes clothes;   //食物id
    private Integer o_num;  //订购的数量
    private String markup;      //备注
    private String state;       //状态
    private String loginId;   //用户ID
    private String f_name;   // 服装名称
    private Integer num;   //总价格
    private String pzstate;
    private String mobile;
    private String pd;
    private StringBuffer showBuyList;        //显示购物车列表
    private int total;                        //购物车总数量
    private int totalPrice;                    //所有商品总价格
    private StringBuffer showNoPayList;        //显示未支付列表
    private StringBuffer showPayList;        //显示已支付列表

    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPzstate() {
        return pzstate;
    }

    public void setPzstate(String pzstate) {
        this.pzstate = pzstate;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes menu) {
        this.clothes = menu;
    }

    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(Integer o_id) {
        this.o_id = o_id;
    }

    public Integer getO_num() {
        return o_num;
    }

    public void setO_num(Integer o_num) {
        this.o_num = o_num;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public StringBuffer getShowBuyList() {
        showBuyList = new StringBuffer();
        List<Orders> list = dao.searchBuy(user);
        for (Orders orders : list) {
            showBuyList.append("<tr class=\"tr\"><td class=\"td1\"><span class=\"no\">");
            //编号
            showBuyList.append(orders.getO_id());
            showBuyList.append("</span></td><td class=\"td1\"><span class=\"food\">");
            //菜名
            showBuyList.append(orders.getClothes().getF_name());
            showBuyList.append("</span></td><td class=\"td1\"><span class=\"number\">");
            //数量
            showBuyList.append(orders.getO_num());
            showBuyList.append("</span></td><td class=\"td1\"><span class=\"per_price\">");
            //单价
            showBuyList.append(orders.getClothes().getPrice());
            showBuyList.append("</span></td><td class=\"td1\"><span class=\"price\">");
            //总价
            showBuyList.append(orders.getO_num() * orders.getClothes().getPrice());
            showBuyList.append("</span></td><td class=\"td1\"><span class=\"information\">");
            //备注：口味要求+其他要求
            showBuyList.append(orders.getMarkup());
            showBuyList.append("</span></td><td class=\"td1\"><span class=\"delete\"><a href=\"deleBuy?id=" + orders.getO_id() + "\">" +
                    "<span class = \"glyphicon glyphicon-trash\"></span></a></span></td></tr>");
        }
        return showBuyList;
    }

    /**
     * 返回总数量
     *
     * @return
     */
    public int getTotal() {
        total = 0;
        List<Orders> list = dao.searchBuy(user);
        for (Orders orders : list) {
            total += orders.getO_num();
        }
        return total;
    }

    /**
     * 所有商品总价格
     *
     * @return
     */
    public int getTotalPrice() {
        totalPrice = 0;
        List<Orders> list = dao.searchBuy(user);
        for (Orders orders : list) {
            totalPrice += orders.getO_num() * orders.getClothes().getPrice();
            System.out.println(totalPrice);
        }
        return totalPrice;
    }

    /**
     * 显示未配送列表
     *
     * @return
     */
    public StringBuffer getShowNoPayList() {
        showNoPayList = new StringBuffer();
        List<Orders> list = dao.searchBuyPd(user);
        for (Orders orders : list) {
            showNoPayList.append("<tr class=\"tr\"><td class=\"td1\"><span class=\"no\">");
            showNoPayList.append(orders.getClothes().getF_name());
            showNoPayList.append("</span></td><td class=\"td1\"><span class=\"food\">");
            showNoPayList.append(orders.getO_num());
            showNoPayList.append("</span></td><td class=\"td1\"><span class=\"number\">");
            showNoPayList.append(orders.getClothes().getPrice());
            showNoPayList.append("</span></td><td class=\"td1\"><span class=\"per_price\">");
            showNoPayList.append(orders.getClothes().getPrice() * orders.getO_num());
            showNoPayList.append("</span></td><td class=\"td1\"><span class=\"price\">");
            showNoPayList.append(orders.getMarkup());
            showNoPayList.append("</span></td><td class=\"td1\"><span class=\"information\" style=\"color: red;font-weight: bold;\">");
            showNoPayList.append(orders.getPzstate());

        }
        return showNoPayList;
//		<tr class="tr">
//        <td class="td1"><span class="no">1</span></td>
//        <td class="td1"><span class="food">服装</span></td>
//        <td class="td1"><span class="number">1</span></td>
//        <td class="td1"><span class="per_price">19.0</span></td>
//        <td class="td1"><span class="price">19.0</span></td>
//        <td class="td1"><span class="information">服装信息</span></td>
//    	</tr>
    }

    /**
     * 显示已支付列表
     *
     * @return
     */
    public StringBuffer getShowPayList() {
        showPayList = new StringBuffer();
        List<Orders> list = dao.searchPaidList(user);
        for (Orders orders : list) {
            System.out.println(orders);
        }
        for (Orders orders : list) {
            showPayList.append("<tr class=\"tr\"><td class=\"td1\"><span class=\"no\">");
            showPayList.append(orders.getClothes().getF_name());
            showPayList.append("</span></td><td class=\"td1\"><span class=\"food\">");
            showPayList.append(orders.getO_num());
            showPayList.append("</span></td><td class=\"td1\"><span class=\"number\">");
            showPayList.append(orders.getClothes().getPrice());
            showPayList.append("</span></td><td class=\"td1\"><span class=\"per_price\">");
            showPayList.append(orders.getO_num() * orders.getClothes().getPrice());
            showPayList.append("</span></td><td class=\"td1\"><span class=\"price\">");
            showPayList.append(orders.getMarkup());
            showPayList.append("</span></td><td class=\"td1\"><span class=\"information\" style=\"color: red;font-weight: bold;\">");
            showPayList.append(orders.getPzstate());
            showPayList.append("</span></td></tr>");
        }
        return showPayList;

    }

    @Override
    public String toString() {
        return "Orders{" +
                "o_id=" + o_id +
                ", user=" + user +
                ", clothes=" + clothes +
                ", o_num=" + o_num +
                ", markup='" + markup + '\'' +
                ", state='" + state + '\'' +
                ", loginId='" + loginId + '\'' +
                ", f_name='" + f_name + '\'' +
                ", num=" + num +
                ", pzstate='" + pzstate + '\'' +
                ", mobile='" + mobile + '\'' +
                ", pd='" + pd + '\'' +
                ", showBuyList=" + showBuyList +
                ", total=" + total +
                ", totalPrice=" + totalPrice +
                ", showNoPayList=" + showNoPayList +
                ", showPayList=" + showPayList +
                ", dao=" + dao +
                '}';
    }
}

