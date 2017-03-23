package com.test.pay.apppay.params;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jacky on 2017/3/20.
 * 签名类
 */

public class SignUtils {

    /**
     *
     * @param request 请求参数
     * @param signkey  秘钥的key
     * @return
     */

    public static void signSh256(PayRequest request, String signkey) {
        request.setSign("");

        Field[] fields = request.getClass().getDeclaredFields();

        Map<String, String> params = new HashMap<>();
        for (Field field : fields) {

            field.setAccessible(true);

            try {
                Object value = field.get(request);
                if (null != value && !"".equals(value.toString().trim())) {
                    params.put(field.getName(), value.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        if (params.size() != 0) {

           Set<String> keys= params.keySet();

            String[]   keys2str= keys.toArray(new String[keys.size()]);

            Arrays.sort(keys2str);

            StringBuffer sb=new StringBuffer();
            for (String str:keys2str){
                sb.append(params.get(str));
            }
         String line=  sb.toString()+signkey;

          request.setSign(Encrypt.SHA256(line));

        }else {
            throw new RuntimeException("签名出错，参数不能为零...");
        }

    }

}
