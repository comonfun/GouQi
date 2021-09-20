package tools;

import com.google.gson.Gson;
import datatools.ConnectTool;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Beans {

    static Gson gson = new Gson();
    private static ResultSet resultSet;

    static class PostIP {
        private String ip;

        private PostIP(String ip) {
            this.ip = ip;
        }
    }


    public static List<Weather.DataBean.ForecastBean> getForecastBeans(int id) throws IOException {


        String weather = PostTool.getWeathers(id);
        Weather weatherObj = gson.fromJson(weather, Weather.class);

        List<Weather.DataBean.ForecastBean> forecast = weatherObj.getData().getForecast();

        return forecast;
    }


    public static List initPos() throws SQLException, IOException, URISyntaxException {
        String ip = GetTool.getIP("https://www.ip.cn/api/index?ip=&type=0");

        Connection conn=null;
        Statement statement=null;
        int id = 0;
        try {
            conn = ConnectTool.getConnection();
            statement = conn.createStatement();
            if(ip==null)return null;
            //通过IP获取地址
            String pos = GetTool.getPos(ip);

            Position position = gson.fromJson(pos, Position.class);
            Position.ResultBean result = position.getResult();
            String sheng=result.getProvince();
            String shi=result.getCity();
            String qu=result.getDistrict();

            resultSet = statement.executeQuery("select * from cities where sheng='" + sheng + "' and shi='" + shi + "' and qu='" + qu+"'");
            ArrayList arrayList=null;
            if(resultSet.next()){
                arrayList = new ArrayList(2);
                id=resultSet.getInt(1);
                arrayList.add(sheng+shi+qu);
                arrayList.add(id);
            }
            return arrayList;
        } finally {
            if (resultSet != null){
                resultSet.close();
            }

            if (conn != null){
                conn.close();
            }

            if(statement != null){
                statement.close();
            }
        }
    }
}