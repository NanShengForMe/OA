package cn.bagebeyond.oa.sysmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.bagebeyond.oa.framework.util.PageUtils;
import cn.bagebeyond.oa.sysmanage.dto.PageParam;
import cn.bagebeyond.oa.sysmanage.entity.Dict;
import cn.bagebeyond.oa.sysmanage.service.IDictService;

@Controller
@RequestMapping("/sysmgr/dict")
public class DictController {

	private static Logger logger = Logger.getLogger(DictController.class);
	
	@Autowired
	private IDictService dictService;
	
	//进入字典查询列表功能
	@RequestMapping("/gotoDictList")
	public String gotoDictList(Model model){
		List<String> dictTypeList = dictService.getAllDictType();
		model.addAttribute("dictTypeList", dictTypeList);
		return "sysmanage/dict/dictList";
	}
	
	//进去字典编辑页面
	//进入字典修改页面时，要根据id查询字典对象明细
	@RequestMapping("/gotoDictEdit")
	public String gotoDictEdit(Long dictId, @ModelAttribute("editFlag") int editFlag,Model model){
		//当editflag为1时进入添加字典添加  当editFlag为2时进入修改，此时需要根据id查询明细
		if(editFlag==2){
			Dict dict = dictService.getDictById(dictId);
			model.addAttribute("dict", dict);
		}
		return "sysmanage/dict/dictEdit";
	}
	
	//按条件查询字典列表
	@RequestMapping("/getDictListPage")
	public @ResponseBody Map<String,Object> getDictListPage(String type,String description,int pageNo,int pageSize){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//获取查询条件
		//判断传过来的字符串是否为空
		Dict dict = new Dict();
		if(StringUtils.isNotEmpty(type))dict.setType(type);
		if(StringUtils.isNotEmpty(description))dict.setDescription(description);
		//构造分页对象
		PageParam pageParam = new PageParam();
		pageParam.setPageNo(pageNo);
		pageParam.setPageSize(pageSize);
		PageInfo<Dict> pageInfo = dictService.getDictListPage(dict, pageParam);
		List<Dict> dictList = pageInfo.getList();
		resultMap.put("dictList", dictList);
		
		//获取返回的分页条
		String pageStr = PageUtils.pageStr(pageInfo, "dictMgr.getDictListPage");
		resultMap.put("pageStr", pageStr);
		return resultMap;
	}
	
	//修改和增加字典
	@RequestMapping("/saveDict")
	@ResponseBody
	public Map<String,Object> saveDict(@RequestBody Dict dict){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(dict != null && dict.getId() != null){
				//修改
				if(dictService.updateDict(dict))
					resultMap.put("result", "修改字典信息成功！");
			}else{
				//添加
				if(dictService.addDict(dict))
					resultMap.put("result", "添加字典信息成功");
			}
		}catch(Exception e){
			logger.error("修改字典信息失败",e);
			resultMap.put("result", "修改字典信息失败！");
		}
		
		return resultMap;
	}
	
	@RequestMapping("/delDict")
	@ResponseBody
	public Map<String,Object> delDict(Long dictId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			if(dictService.delDict(dictId)){
				resultMap.put("result", "删除成功！");
			}
		}catch(Exception e){
			resultMap.put("result", "删除失败！");
			logger.error("删除失败！",e);
		}
		return resultMap;
	}
}
