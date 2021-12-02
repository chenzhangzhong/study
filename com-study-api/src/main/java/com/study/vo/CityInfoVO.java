package com.study.vo;

import lombok.Data;

/**
 * @author chenzhangzhong
 * @date 2021/11/26
 * @description 城市信息VO对象
 */
@Data
public class CityInfoVO {
    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区名称
     */
    private String areaName;

    /**
     * 运营商名称
     */
    private String operatorName;
}
