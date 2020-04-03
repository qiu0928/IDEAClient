package jeecg.Client;

import jeecg.Client.Utils.StringUtils;

public class StringClient {
    public static void main(String[] args) {
//        System.out.println(StringUtils.isEmpty(null));
//        System.out.println(StringUtils.getDouble("1236",123));
//        System.out.println(StringUtils.getIp());
        System.out.println(StringUtils.randomGen(8));
        System.out.println(System.currentTimeMillis());
    }
}
