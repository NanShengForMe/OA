<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>角色管理</title>
	<%@ include file="/WEB-INF/pages/include/head.jsp"%>
<meta charset="utf-8" />
<meta name="renderer" content="webkit">
<script type="text/javascript">	  
    var roleMgr = { 		
   		delRole:function(roleId){
   			top.layer.confirm('您确定要删除此角色吗?', {icon: 3, title:'系统提示'}, function(index){
	 		  $.ajax({
					type:'post',//请求方式
					url:'${ctx}/sysmgr/role/delRole', 
					dataType:'json', //有几种格式 xml html json text 等常用
					//data传值的另外一种方式 form的序列化
					data: {"roleId":roleId},//传递给服务器的参数	
					success:function(data){//与服务器交互成功调用的回调函数
						//data就是out.print输出的内容
						//alert(data.result);
						top.layer.alert(data.result, {icon: 1, title:'提示：'});
					  	document.getElementById("roleListForm").submit();			
					}
				});
	 		 top.layer.close(index);
   			});
   	 	}		
    };
    
    	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0);">角色列表</a></li>
		<li><a href="${ctx}/sysmgr/role/gotoRoleEdit?editFlag=1">角色添加</a></li>
	</ul>

	<form id="roleListForm" method="post" action="${ctx}/sysmgr/role/gotoRoleList">
		<table id="roleListTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>名称</th>
					<th>描述</th>
 					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				 
			<c:forEach items="${roleList}" var="role">
			<tr>										 
				<td><a href="${ctx}/sysmgr/role/gotoRoleEdit?editFlag=2&roleId=${role.id}">${role.name}</a></td>
				<td>${role.remarks}</td>
				<td nowrap>
					<a href="${ctx}/sysmgr/role/gotoRoleEdit?editFlag=2&roleId=${role.id}">修改</a>
					<c:if test="${role.id != 1}">
						<a href="javascript:roleMgr.delRole(${role.id})">删除</a>
					</c:if>
					<a href="${ctx}/sysmgr/role/gotoRoleEdit?editFlag=1">添加</a>
				</td>		 
			</tr>
			</c:forEach>
				 
			</tbody>
		</table>
		 
	 </form>
</body>
</html>