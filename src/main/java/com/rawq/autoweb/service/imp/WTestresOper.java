package com.rawq.autoweb.service.imp;

import org.apache.ibatis.session.SqlSession;

import com.rawq.autoweb.domain.WTestre;
import com.rawq.autoweb.domain.WZentaobug;
import com.rawq.autoweb.service.IWTestre;
import com.rawq.autoweb.service.IWZentaobug;
import com.rawq.autoweb.utils.Log;
import com.rawq.autoweb.utils.MybatisUtil;

public class WTestresOper implements IWTestre,IWZentaobug{

	private SqlSession sqlSession = MybatisUtil.getSqlSession();
	
	@Override
	public int delWTestreById(Integer id) {
		
		return sqlSession.getMapper(IWTestre.class).delWTestreById(id);
	}

	@Override
	public int addWTestre(WTestre record) {
		int res = -1;
		try {
			res = sqlSession.getMapper(IWTestre.class).addWTestre(record);
			//提交
			if (res != -1){
				sqlSession.commit();
			}else{
				Log.warn(this.getClass(),"未成功插入 wSteep ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
				
	}

	@Override
	public int addWTestreSelective(WTestre record) {
		return sqlSession.getMapper(IWTestre.class).addWTestreSelective(record);
	}

	@Override
	public WTestre getWTestreById(Integer id) {
		return sqlSession.getMapper(IWTestre.class).getWTestreById(id);
	}

	@Override
	public int updateWTestreByIdSelective(WTestre record) {
		return sqlSession.getMapper(IWTestre.class).updateWTestreByIdSelective(record);
	}

	@Override
	public int updateWTestreById(WTestre record) {
		return sqlSession.getMapper(IWTestre.class).updateWTestreById(record);
	}

	
	/********************************************IWZentaobug*******************************************/
	
	
	@Override
	public int delWZentaobugById(Integer id) {
		return sqlSession.getMapper(IWZentaobug.class).delWZentaobugById(id);
	}

	@Override
	public int addWZentaobug(WZentaobug record) {
		int res = -1;
		try {
			res = sqlSession.getMapper(IWZentaobug.class).addWZentaobug(record);
			//提交
			if (res != -1){
				sqlSession.commit();
			}else{
				Log.warn(this.getClass(),"未成功插入 wSteep ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int addWZentaobugSelective(WZentaobug record) {
		return sqlSession.getMapper(IWZentaobug.class).addWZentaobugSelective(record);
	}

	@Override
	public WZentaobug getWZentaobugById(Integer id) {
		return sqlSession.getMapper(IWZentaobug.class).getWZentaobugById(id);
	}

	@Override
	public int updateWZentaobugByIdSelective(WZentaobug record) {
		return sqlSession.getMapper(IWZentaobug.class).updateWZentaobugByIdSelective(record);
	}

	@Override
	public int updateWZentaobugByIdWithBLOBs(WZentaobug record) {
		return sqlSession.getMapper(IWZentaobug.class).updateWZentaobugByIdWithBLOBs(record);
	}

	@Override
	public int updateWZentaobugById(WZentaobug record) {
		return sqlSession.getMapper(IWZentaobug.class).updateWZentaobugById(record);
	}

}
