package jeecg.Client.Utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RpcHttpUtil {
        public static final String GET = "GET";
        public static final String POST = "POST";
        public static Map<String, String> invokeHttp(String url, String method,
                                                     Map<String, String> paramMap, List<String> returnParamList) throws UnsupportedOperationException,IOException {        //1. 创建HttpClient对象和响应对象
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = null;        //2. 判断请求方法是get还是post
            if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(method, GET)) {            //2.1 如果是get请求，要拼接请求url的参数
                StringBuilder urlSb = new StringBuilder(url);
                int paramIndex = 0;
                //遍历Map集合，将map转化成set集合（有序，不重复）foreach遍历
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    //get请求要追加参数，中间有一个?
                    if (paramIndex == 0) {
                        urlSb.append("?");
                    }                //拼接参数
                    urlSb.append(entry.getKey() + "=" + entry.getValue() + "&");
                }
                //前面在拼接参数时最后多了一个&，应去掉
                urlSb.delete(urlSb.length() - 1, urlSb.length());
                // 创建Get请求
                HttpGet get = new HttpGet(urlSb.toString());
                //2.2 让HttpClient去发送get请求，得到响应
                response = client.execute(get);
            }else if (StringUtils.equalsIgnoreCase(method, POST)) {
                //// 创建Post请求
                HttpPost post = new HttpPost(url);
                //2.3 如果是post请求，要构造虚拟表单，并封装参数
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    // 将参数放入键值对类NameValuePair中,再放入集合中
                    paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                //2.4 设置请求正文的编码
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(paramList, "GBK");
                post.setEntity(uefEntity);
                //2.5 让HttpClient去发送post请求，得到响应
                response = client.execute(post);
            }else {            //其他请求类型不支持
                throw new RuntimeException("对不起，该请求方式不支持！");
            }
            //3. 提取响应正文，并封装成Map
            InputStream is = response.getEntity().getContent();
            Map<String, String> returnMap = new LinkedHashMap<String, String>();
            String ret = IOUtils.toString(is, "GBK");
            //循环正则表达式匹配（因为有多个参数，无法预处理Pattern）
            for (String param : returnParamList) {
                Pattern pattern = Pattern.compile(param + ":['\"]?.+['\"]?");
                Matcher matcher = pattern.matcher(ret);
                while (matcher.find()) {
                    String keyAndValue = matcher.group();
                    String value = keyAndValue.substring(keyAndValue.indexOf("'") + 1, keyAndValue.lastIndexOf("'"));
                    returnMap.put(param, value);
                }
                //如果没有匹配到，则put进空串(jdk8的方法)
                returnMap.putIfAbsent(param, "");
            }        return returnMap;
        }
}


