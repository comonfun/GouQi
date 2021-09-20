package tools;


import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PostTool {

        static String getWeathers(int id) throws IOException {
        CloseableHttpClient client=null;
        HttpEntity entity=null;
        StringBuilder sb=new StringBuilder();
        CloseableHttpResponse response=null;
        CloseableHttpResponse body=null;
        InputStream content=null;
        InputStreamReader isr=null;
        try {
            client = HttpClients.createDefault();

            List list = new ArrayList<BasicNameValuePair>();
            list.add(new BasicNameValuePair("cityId",String.valueOf(id)));
            list.add(new BasicNameValuePair("token","677282c2f1b3d718152c4e25ed434bc4"));
            UrlEncodedFormEntity urlEncdedFormEntity = new UrlEncodedFormEntity(list, Charset.forName("UTF-8"));
//            body.setContentType(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
            HttpPost post = new HttpPost("http://freecityid.market.alicloudapi.com/whapi/json/alicityweather/briefforecast3days");
            post.setEntity(urlEncdedFormEntity);
            post.addHeader("Authorization", "APPCODE d4875ceb25fd45179ccc607867998bb9");
            post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            response = client.execute(post);

            entity = response.getEntity();

            content = entity.getContent();

            isr = new InputStreamReader(content,"utf-8");
            char[] ch = new char[32];
            int len;
            while((len=isr.read(ch))!=-1){
                sb.append(new String(ch,0,len));
            }
        }
        finally {
            if(isr !=null) isr.close();
            if(content !=null)content.close();
            if(entity != null) EntityUtils.consume(entity);
            if(response != null)response.close();
            if(client != null)client.close();
        }

        return sb.toString();
    }
}
