package com.edu.nchu.distriuted.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alen on 2016/10/30.
 */
@Controller
public class OrderAction {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(ModelMap module){
        return "order/list";
    }
}
