package org.ppcirgo.oa.controller;


import lombok.extern.slf4j.Slf4j;
import org.ppcirgo.oa.AJAXResult;
import org.ppcirgo.oa.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class URLController {

    @Autowired
    private URLService urlService;


    @CrossOrigin("*")
    @GetMapping("/getUrlByLabel")
    public Object getUrl(
            @RequestParam(name = "name") String name
    ){
        String url = urlService.getUrl(name);
        log.info("name:-->"+name+"  return url:-->"+url);
        return new AJAXResult(url);
    }
}
