package com.rawq.autoweb.service;

import com.rawq.autoweb.domain.WTestre;
import com.rawq.autoweb.domain.WZentaobug;

public interface IWTestre {

	int delWTestreById(Integer id);

    int addWTestre(WTestre record);

    int addWTestreSelective(WTestre record);

    WTestre getWTestreById(Integer id);

    int updateWTestreByIdSelective(WTestre record);

    int updateWTestreById(WTestre record);


	
}
