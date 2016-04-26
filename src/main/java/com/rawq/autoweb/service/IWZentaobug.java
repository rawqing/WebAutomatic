package com.rawq.autoweb.service;

import com.rawq.autoweb.domain.WZentaobug;

public interface IWZentaobug {

	int delWZentaobugById(Integer id);

    int addWZentaobug(WZentaobug record);

    int addWZentaobugSelective(WZentaobug record);

    WZentaobug getWZentaobugById(Integer id);

    int updateWZentaobugByIdSelective(WZentaobug record);

    int updateWZentaobugByIdWithBLOBs(WZentaobug record);

    int updateWZentaobugById(WZentaobug record);
}
