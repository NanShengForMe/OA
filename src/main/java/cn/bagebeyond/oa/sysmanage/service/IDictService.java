package cn.bagebeyond.oa.sysmanage.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.bagebeyond.oa.sysmanage.dto.PageParam;
import cn.bagebeyond.oa.sysmanage.entity.Dict;

/**
 * 字典相关业务接口
 * @author ZJD
 *
 */
public interface IDictService {
	
	/**
	 * 获取所有字典类型
	 * @return
	 */
	public List<String> getAllDictType();
	
	/**
	 * 根据字典id获取字典明细
	 * @param dictId
	 * @return
	 */
	public Dict getDictById(Long dictId);
	
	/**
	 * 根据条件查询字典列表
	 * @param dict
	 * @return
	 */
	public List<Dict> getDictList(Dict dict);
	
	/**
	 * 分页查询字典列表
	 * @param dict
	 * @param pageParam
	 * @return
	 */
	public PageInfo<Dict> getDictListPage(Dict dict,PageParam pageParam);
	
	/**
	 * 添加字典信息
	 * @param dict
	 * @return
	 */
	public boolean addDict(Dict dict);
	
	/**
	 * 更新字典信息
	 * @param dict
	 * @return
	 */
	public boolean updateDict(Dict dict);
	
	/**
	 * 根据字典id删除字典信息
	 * @param dictId
	 * @return
	 */
	public boolean delDict(Long dictId);
}
