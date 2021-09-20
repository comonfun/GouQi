package tools;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;


public class GetTool {


    static String getIP(String url) {
        RequestConfig requestConfig = RequestConfig.custom()
                // 获取连接超时时间
                .setConnectionRequestTimeout(1000)
                // 请求超时时间
                .setConnectTimeout(2000)
                // 响应超时时间
                .setSocketTimeout(4000)
                .build();

        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        HttpGet httpGet = new HttpGet(url);

//        httpGet.addHeader("referer","https://www.ip.cn/");
        String header1="Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36";
        String header2="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.72 Safari/537.36";
        String header3="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36 Edg/89.0.774.77";
        httpGet.addHeader("user-agent",header3);
        HttpEntity entity=null;
        InputStream content=null;
        BufferedInputStream bc=null;
        CloseableHttpResponse response = null;
        String ip=null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            response=client.execute(httpGet);
            entity = response.getEntity();
            content = entity.getContent();
            byte[] json=new byte[16];

            int len;
            while((len=content.read(json))!=-1){
                stringBuilder.append(new String(json,0,len));
            }
            Gson gson = new Gson();
            IP ipAddress=gson.fromJson(stringBuilder.toString(),IP.class);
            ip = ipAddress.getIp();
            EntityUtils.consume(entity);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if(content != null) {
                try{
                    content.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(response != null) {
                try {
                    response.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            if(client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ip;
    }

    static String getPos(String ip) throws IOException,URISyntaxException {
        CloseableHttpClient client = HttpClients.createDefault();
        URI uri = null;
        HttpGet httpGet=null;
        HttpEntity entity=null;
        InputStream content=null;
        BufferedInputStream bc=null;
        CloseableHttpResponse response = null;
        InputStreamReader isr=null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("ips.market.alicloudapi.com")
                    .setPath("/iplocaltion")
                    .setParameter("ip", ip).build();
            httpGet = new HttpGet(uri);
            httpGet.setHeader("Authorization","APPCODE d4875ceb25fd45179ccc607867998bb9");
            httpGet.setHeader("Content-Type","application/json; charset=utf-8");
            response=client.execute(httpGet);
            entity = response.getEntity();
            content = entity.getContent();
            isr = new InputStreamReader(content,"utf-8");
            char[] ch = new char[16];
            int len;
            while((len=isr.read(ch))!=-1){
                stringBuilder.append(new String(ch,0,len));
            }

            EntityUtils.consume(entity);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if(isr != null){
                isr.close();
            }

            if(content != null) {
                try{
                    content.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(response != null) {
                try {
                    response.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            if(client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}