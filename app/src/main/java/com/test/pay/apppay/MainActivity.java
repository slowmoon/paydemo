package com.test.pay.apppay;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.test.pay.apppay.params.Config;
import com.test.pay.apppay.params.HttpRequestUtil;
import com.test.pay.apppay.params.OrderRTO;
import com.test.pay.apppay.params.ParseResponse;
import com.test.pay.apppay.params.PayRequest;
import com.test.pay.apppay.params.SignUtils;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

     Button wxH5;
     Button app;
     Button alip;

     EditText text;

     WebView view;

    Handler handler=new Handler(){

        @Override
        public void handleMessage(final Message msg) {
            String message=msg.obj.toString();
            text.setText(message);

            OrderRTO orderRTO= ParseResponse.parseParams(message);

            boolean result= ParseResponse.signCheck(message,Config.key);//验证签名结果

            if (!result){
                throw new RuntimeException("验证签名失败");
            }

            String itemResponseMsg=orderRTO.getPayments()[0].getItemResponseMsg();

            Map<String,String> params= (Map<String, String>) JSON.parse(itemResponseMsg);


            if (msg.what==0x123){
                //处理h5支付
                    handleH5Payment(params);

            }
            if (msg.what==0x124){
                //处理app支付
                handleAppPay(params);

            }
            if (msg.what==0x125){
                //处理android  支付宝支付
                handleAlipay(params);
            }


            super.handleMessage(msg);
        }
    };


    //处理h5返回信息部分
    private void handleH5Payment(Map<String,String> params){


       final String url=params.get("wxurl");
        System.out.println(url);

        view.loadUrl(url);

        Toast.makeText(getApplicationContext(),"微信支付等待开启",Toast.LENGTH_SHORT).show();

    }



    //处理支付宝返回信息
    private void handleAlipay(Map<String,String> params){

        final String url=params.get("barcodeInfo");
        System.out.println(url);
        view.loadUrl(url);
        setContentView(view);
        Toast.makeText(getApplicationContext(),"支付宝等待唤起",Toast.LENGTH_SHORT).show();


    }
    //处理app支付返回信息  ，未完全实现
    private void handleAppPay(Map<String,String> params){
        IWXAPI iwxapi= WXAPIFactory.createWXAPI(getApplicationContext(), null);
        iwxapi.registerApp("wx4fb06232c1cb476c");

        PayReq request=new PayReq();
        request.appId=params.get("appid");
        request.partnerId = params.get("partnerid");
        request.prepayId= params.get("prepayid");
        request.packageValue = "Sign=WXPay";
        request.nonceStr= params.get("noncestr");
        request.timeStamp= params.get("timestamp");
        request.sign= params.get("sign");
        iwxapi.sendReq(request);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(EditText) findViewById(R.id.edit1);
        wxH5= (Button)findViewById(R.id.bh5);
        app= (Button)findViewById(R.id.bapp);
        alip= (Button)findViewById(R.id.alip);

        wxH5.setOnClickListener(this);
        app.setOnClickListener(this);
        alip.setOnClickListener(this);


        view=new WebView(this);

        view.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    try {
                        if (url.contains("platformapi/startapp")) {
                                startAliApp(url);
                                return true;
                        }else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                                && (url.contains("platformapi") && url.contains("startapp"))){
                                startAliApp(url);
                            return true;
                        }else if(url.startsWith("weixin://")){
                            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("weixin://wap/pay?"));
                            intent.setData(Uri.parse(url));
                            startActivity(intent);
                            return true;
                        }
                        else {

                            view.loadUrl(url);
                            return true;
                        }
                    }catch (Exception e){
                        return false;
                    }

             }
        });

        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setAppCacheMaxSize(10*1024*1024);

    }


    public void startAliApp(String url){
        Intent intent=null;
        try{
            intent = Intent.parseUri(url,
                    Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            startActivity(intent);
            finish();
        }catch (Exception e){
            Log.i("error",e.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bh5:
                createH5Pay();
                break;

            case R.id.bapp:
                createAppPay();
                break;
            case R.id.alip:
                createAliPay();
                break;
        }

    }

    /**
     * 此部分逻辑应该是在服务器上使用，先保存数据，后向渠道发起请求，获取支付信息。
     */
    public void createH5Pay(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                PayRequest request=new PayRequest();


                request.setIp("180.173.252.214");///h5支付，需要是用户的真实ip地址。。

                /** h5支付 用于app端  ios 版本 app_name=王者荣耀&bundle_id=com.tencent.wzryIOS&周年纪念版
                 andriod 版本  app_name=王者荣耀&package_name=com.tencent.tmgp.sgame&周年纪念版
                 web网站端   wap_url=https://m.jd.com&wap_name=京东官网&618 专版
                 **/

                request.setExtend("app_name=王者荣耀&package_name=com.tencent.tmgp.sgame&周年纪念版");
                request.setPayment_1("5_"+request.getRequestAmount());

                SignUtils.signSh256(request, Config.key);

                String response=  HttpRequestUtil.request(request);

                Message message=Message.obtain();
                message.what=0x123;
                message.obj=response;


                handler.sendMessage(message);




            }
        }).start();


    }
    /**
     * 此部分逻辑应该是在服务器上使用，先保存数据，后向渠道发起请求，获取支付信息。
     */

    public void createAppPay(){


        new Thread(new Runnable() {
            @Override
            public void run() {

                PayRequest request=new PayRequest();

                request.setPayment_1("4_"+request.getRequestAmount());

                SignUtils.signSh256(request, Config.key);

                String response=  HttpRequestUtil.request(request);

                Message message=Message.obtain();
                message.what=0x124;
                message.obj=response;


                handler.sendMessage(message);



            }
        }).start();


    }

    /**
     * 此部分逻辑应该是在服务器上使用，先保存数据，后向渠道发起请求，获取支付信息。
     */
    public void createAliPay(){


        new Thread(new Runnable() {
            @Override
            public void run() {

                PayRequest request=new PayRequest();

                request.setPayment_1("6_"+request.getRequestAmount());

                SignUtils.signSh256(request, Config.key);

                String response=  HttpRequestUtil.request(request);

                Message message=Message.obtain();
                message.what=0x125;
                message.obj=response;


                handler.sendMessage(message);



            }
        }).start();


    }




}
