package com.msj.elearning.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public class PageUtils {
    /**
     * 将PageInfo对象泛型中的Po对象转化为DTO对象
     * @param pageInfoPo PageInfo<Po>对象</>
     * @param <P> Po类型
     * @param <D> DTO类型
     * @return
     */
    public static <P, D> PageInfo<D> PageInfo2PageInfoDTO(PageInfo<P> pageInfoPo) {
        // 创建Page对象，实际上是一个ArrayList类型的集合
        Page<D> page = new Page<>(pageInfoPo.getPageNum(), pageInfoPo.getPageSize());
        page.setTotal(pageInfoPo.getTotal());
        return new PageInfo<>(page);
    }
}
