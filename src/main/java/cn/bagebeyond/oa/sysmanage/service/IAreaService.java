package cn.bagebeyond.oa.sysmanage.service;

import java.util.List;

import cn.bagebeyond.oa.sysmanage.entity.Area;

/**
 * 区域的逻辑业务实现
 * @author ZJD
 *
 */
public interface IAreaService {
	
	/**
	 * @Title : addArea
	 * @Description : TODO(添加区域信息)
	 * @Param @param area
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:20:11
	 */
	public boolean addAreaInfo(Area area);

	/**
	 * @Title : getAreaListByUserId
	 * @Description : 根据用户id获取区域列表
	 * @Param @param currentUserId
	 * @Return List<Area>
	 * @Author ZJD
	 * 2018年3月15日上午11:35:01
	 */
	public List<Area> getAreaListByUserId(Long currentUserId);
	
	/**
	 * @Title : getAllAreaList
	 * @Description : TODO(获取所有区域列表)
	 * @Param @return 设定文件
	 * @Return List<Area>
	 * @Author ZJD
	 * 2017年10月12日下午8:47:22
	 */
	public List<Area> getAllAreaList();
	
	/**
	 * @Title : getAreaById
	 * @Description : TODO(根据id获取区域信息)
	 * @Param @return 设定文件
	 * @Return Area
	 * @Author ZJD
	 * 2017年10月16日下午10:46:24
	 */
	public Area getAreaById(Long areaId);
	
	/**
	 * @Title : getChildCount
	 * @Description : TODO(得到子节点的数量)
	 * @Param @param areaId
	 * @Param @return 设定文件
	 * @Return int
	 * @Author ZJD
	 * 2017年10月12日下午9:09:34
	 */
	public int getChildCount(Long areaId);
	
	/**
	 * @Title : delArea
	 * @Description : TODO(删除区域)
	 * @Param @param areaId
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月12日下午10:01:04
	 */
	public boolean delArea(Long areaId);
	
	/**
	 * @Title : updateArea
	 * @Description : TODO(更新区域信息)
	 * @Param @param area
	 * @Param @return 设定文件
	 * @Return boolean
	 * @Author ZJD
	 * 2017年10月16日下午11:20:50
	 */
	public boolean updateAreaInfo(Area area);

	
}
