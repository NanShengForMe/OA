package cn.bagebeyond.oa.sysmanage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.bagebeyond.oa.framework.util.UserUtils;
import cn.bagebeyond.oa.sysmanage.dto.PageParam;
import cn.bagebeyond.oa.sysmanage.entity.Dict;
import cn.bagebeyond.oa.sysmanage.mapper.DictMapper;
import cn.bagebeyond.oa.sysmanage.service.IDictService;

@Service
public class DictServiceImpl implements IDictService {

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public List<String> getAllDictType() {
		return dictMapper.getAllDictType();
	}

	@Override
	public List<Dict> getDictList(Dict dict) {
		return dictMapper.getDictList(dict);
	}

	@Override
	public PageInfo<Dict> getDictListPage(Dict dict, PageParam pageParam) {
		PageHelper.startPage(pageParam.getPageNo(), pageParam.getPageSize());
		List<Dict> dictList = dictMapper.getDictList(dict);
		PageInfo<Dict> pageInfo = new PageInfo<Dict>(dictList);
		
		return pageInfo;
	}

	@Override
	public Dict getDictById(Long dictId) {
		return dictMapper.getDictById(dictId);
	}

	@Override
	public boolean addDict(Dict dict) {
		String userId = UserUtils.getCurrentUserId().toString();
		dict.setUpdateBy(userId);
		dict.setUpdateDate(new Date());
		return dictMapper.addDict(dict);
	}

	@Override
	public boolean updateDict(Dict dict) {
		String userId = UserUtils.getCurrentUserId().toString();
		dict.setUpdateBy(userId);
		dict.setUpdateDate(new Date());
		return dictMapper.updateDict(dict);
	}

	@Override
	public boolean delDict(Long dictId) {
		return dictMapper.delDict(dictId);
	}

}
