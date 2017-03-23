package com.test.pay.apppay.params;

/**
 * Created by jacky on 2017/3/20.
 */

public class Config {

        public static final String REQUEST_URL="http://api.99epay.net/mctrpc/order/mkReceiptOrder.htm";

        public static final String TEST_REQUEST="http://121.42.24.178:9010/mctrpc/order/mkReceiptOrder.htm";

        public static int REQUEST_TIMEOUT=10000;
        public static int CONNECTION_TIMEOUT=10000;

        public static final String merchantId="100001"; //测试商户号
        public static final String key="1234512345";   //签名key

}
