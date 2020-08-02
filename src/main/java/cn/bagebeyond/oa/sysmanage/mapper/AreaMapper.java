package cn.bagebeyond.oa.sysmanage.mapper;

import java.util.List;

import cn.bagebeyond.oa.sysmanage.entity.Area;

/**
 * 
 * <p>Title: AreaMapper</p>
 * <p>Description: 区域的增删查改的mapper代理接口</p>
 * <p>Conpany:  </p>
 * @author ZJD
 * @date 2017年10月12日 下午8:49:55
 *
 */
public interface AreaMapper {

	/**
	 * @Title : addAreaInfo
	 * @Description : TODO(添加区域信息)
	 * @Param @param area
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:23:16
	 */
	public boolean addAreaInfo(Area area);
	
	/**
	 * @Title : getAreaListByUserId
	 * @Description : 根据用户id获取区域列表
	 * @Param @param currentUserId
	 * @Return List<Area>
	 * @Author ZJD
	 * 2018年3月15日上午11:37:35
	 */
	public List<Area> getAreaListByUserId(Long userId);
	
	/**
	 * @Title : getAllAreaList
	 * @Description : TODO(查询所有区域列表)
	 * @Param @return 设定文件
	 * @Return List<Area>
	 * @Author ZJD
	 * 2017年10月12日下午8:55:21
	 */
	public List<Area> getAllAreaList();
	
	/**
	 * @Title : getAreaById
	 * @Description : TODO(通过id获取区域信息)
	 * @Param @param areaId
	 * @Param @return 设定文件
	 * @Return Area
	 * @Author ZJD
	 * 2017年10月16日下午10:49:03
	 */
	public Area getAreaById(Long areaId);
	
	/**
	 * @Title : getChildCount
	 * @Description : TODO(根据areaId得到其子节点的数量)
	 * @Param @param areaId
	 * @Param @return 设定文件
	 * @Return int
	 * @Author ZJD
	 * 2017年10月12日下午9:10:59
	 */
	public int getChildCount(Long areaId);
	
	/**
	 * @Title : delArea
	 * @Description : TODO(删除区域)
	 * @Param @param areaId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月12日下午10:08:51
	 */
	public boolean delArea(Long areaId);
	
	/**
	 * @Title : updateAreaInfo
	 * @Description : TODO(更新区域信息)
	 * @Param @param area
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:21:41
	 */
	public boolean updateAreaInfo(Area area);

	
}
