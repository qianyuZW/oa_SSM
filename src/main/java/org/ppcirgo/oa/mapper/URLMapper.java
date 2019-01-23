package org.ppcirgo.oa.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface URLMapper {
    //查询URL，根据名字
    @Select("select url from url_map where label = #{name}")
    public String getUrlByName(String name);
}
