package rd.controller;

import com.alibaba.fastjson.JSON;
import com.rd.pojo.Product;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rd.service.impl.IProductService;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/product/api001/demo1")
    public String demo1() {
        return "demo";
    }

    @RequestMapping("/product/api001/demo2")
    public String demo2() {
        return "demo2";
    }

    @RequestMapping("/product/api002/demo1")
    public String demo3() {
        return "demo3";
    }

    @RequestMapping("/product/api002/demo2")
    public String demo4() {
        return "demo4";
    }


    @GetMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid){
        Product product = productService.findByPid(pid);
        log.info("查询商品:" + JSON.toJSONString(product));
        return product;
    }
}
