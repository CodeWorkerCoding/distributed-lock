package com.edu.nchu.distriuted;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 草稿验证测试
 * Created by fujianjian on 2016/11/22.
 */
@Slf4j
public class DraftTest {

    @Test
    public void testMapStrToJsonStr(){
        String mapStr = "{userId=00000C2001, businessId=16112215181300000000000000003360, serialNo=201611221518138331023600, amount=9781, tradeName=手工代扣交易, custBankName=建设银行, custBankAccountNo=6217001210063183931, custName=复议测试, custID=630105198212195737, custProtocolNo=1202125710031, orderNo=20161118102001, payChannel=LYCHEE01, source=HT, mobile=18502795863, period=6, productType=1006}";

        log.info("Map 原始字符串：{}" , mapStr);
        mapStr = mapStr.replace("{", "");
        mapStr = mapStr.replace("}", "");

        JSONObject json = new JSONObject();
        String[] contents = mapStr.split("\\,");
        List<String> contentList = Arrays.asList(contents);
        contentList.forEach((String item) -> {
            item.trim();
            String[] temp = item.split("\\=");
            json.put(temp[0], temp[1]);
        });
        log.info("转换之后的 json 字符串：{}", json.toString());

    }

}
