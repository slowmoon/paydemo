package com.test.pay.apppay.params;

import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;


/**
 * Created by jacky on 2017/3/20.
 */

public class HttpRequestUtil {

        public static String request(PayRequest request){
                OkHttpClient client=new OkHttpClient();
                client.setConnectTimeout(Config.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
                client.setReadTimeout(Config.REQUEST_TIMEOUT,TimeUnit.MILLISECONDS);

                 RequestBody body=  makeBuilder(new FormEncodingBuilder(),request);

                Request post=new Request.Builder().url(Config.REQUEST_URL).post(body).build();


                try {
                      Response response= client.newCall(post).execute();
                    Log.i("error-message",response.body().toString());

                        if (response.isSuccessful()) {
                            return response.body().string();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }


        private static RequestBody makeBuilder(FormEncodingBuilder builder, PayRequest request){

                if (request==null) throw new RuntimeException("请求参数不能为空");

               Field[] fields= request.getClass().getDeclaredFields();

                        for (Field field:fields){

                                        field.setAccessible(true);
                                try {
                                        Object value=field.get(request);
                                        if (value!=null && !"".equals(value)) {
                                                builder.addEncoded(field.getName(), value.toString());
                                        }

                                } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                }

                        }


             return    builder.build();

        }

}
