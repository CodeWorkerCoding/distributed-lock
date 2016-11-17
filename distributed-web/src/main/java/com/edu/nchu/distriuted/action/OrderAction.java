package com.edu.nchu.distriuted.action;

import com.edu.nchu.distributed.domain.Order;
import com.edu.nchu.distributed.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

/**
 * Created by fujianjian on 2016/10/30.
 */
@Controller
@RequestMapping("/order")
public class OrderAction {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    Order order;

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(ModelMap module){
        logger.info("hint this method");
        order.getAmount();
        return "order/list";
    }

    @RequestMapping(value = "/sqlexp", method = RequestMethod.GET)
    public String sqlException() throws Exception{
        logger.info("sql出错了");
        throw new SQLException("sql出错鸟");
    }

    @RequestMapping(value = "/bizexp", method = RequestMethod.GET)
    public String bizException() throws Exception{
        logger.info("业务出错了");
        throw new BizException("业务出错鸟");
    }



}
