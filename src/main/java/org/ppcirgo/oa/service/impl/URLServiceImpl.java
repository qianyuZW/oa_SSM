package org.ppcirgo.oa.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ppcirgo.oa.mapper.URLMapper;
import org.ppcirgo.oa.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 刘周
 * 2019-01-23
 */
@Service
@Slf4j
public class URLServiceImpl implements URLService {
    @Autowired
    private URLMapper urlMapper;
    @Override
    public String getUrl(String name) {
        return urlMapper.getUrlByName(name);
    }
}
