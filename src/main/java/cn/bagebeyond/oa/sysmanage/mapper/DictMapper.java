package cn.bagebeyond.oa.sysmanage.mapper;

import java.util.List;

import cn.bagebeyond.oa.sysmanage.entity.Dict;

public interface DictMapper {
	
	/**
	 * 获取所有字典类型
	 * @return
	 */
	public List<String> getAllDictType();
	
	/**
	 * 获取字典列表
	 * @param dict
	 * @return
	 */
	public List<Dict> getDictList(Dict dict);
	
	/**
	 * 根据字典的id获取字典的明细
	 * @param dictId
	 * @return
	 */
	public Dict getDictById(Long dictId);
	
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
	 * 删除字典信息 id
	 * @param dictId
	 * @return
	 */
	public boolean delDict(Long dictId);
}
