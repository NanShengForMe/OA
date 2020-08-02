<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>字典编辑</title>
<%@ include file="/WEB-INF/pages/include/head.jsp"%>
<meta charset="utf-8" />
<meta name="renderer" content="webkit">
<script type="text/javascript">
 

$(document).ready(function() {
	$("#leaveDate").focus();
	$("#leaveProssEditForm").validate({
		submitHandler: function(form){
			loading('正在提交，请稍等...');
	 		var editFlag = ${editFlag};
	 		//获取字典修改后的信息数据,组装成json字符串
			var formObject = {};
			var formArray =$("#leaveProssEditForm").serializeArray();
			$.each(formArray, function(i, item){
				formObject[item.name]=item.value;
			 });
 			//alert(formObject);
 			//alert(JSON.stringify(formObject));
 			loading('正在提交，请稍等...');
				$.ajax({
				type:'post',//请求方式
				url:'${ctx}/activitimgr/leaveProcess/saveLeaveProcess', 
				dataType:'json', //返回数据的几种格式 xml html json text 等常用
				contentType :"application/json;charset=UTF-8",
				//data传值的另外一种方式 form的序列化
				data: JSON.stringify(formObject),//传递给服务器的参数					
				success:function(data){//与服务器交互成功调用的回调函数
					//后台返回则关掉提示
					top.$.jBox.closeTip();
					//alert(data.result);
					top.layer.alert(data.result, {icon: 1, title:'提示：'});
					history.go(-1);
 				}
			});
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}	
	});
	
	laydate({
        elem: '#leaveDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
         //响应事件。如果没有传入event，则按照默认的click
        istoday: true,
        calendar: true,
    	min:laydate.now()
    });	
 
});
</script>
</head>
<body>
<ul class="nav nav-tabs">
 		<li><a href="${ctx}/activitimgr/leaveProcess/gotoLeaveProcessList">请假单列表</a></li>
		<li class="active"><a href="javascript:void(0);">请假单
			<c:choose>
			   <c:when test="${editFlag==1}">录入
			   </c:when>
			   <c:otherwise>修改</c:otherwise>
			 </c:choose>
		 </a></li>
		 <li><a href="${ctx}/activitimgr/leaveProcess/gotoLeaveProcessImage">流程图</a></li>
	</ul><br/>
	<form id="leaveProssEditForm" class="form-horizontal" action="#" method="post">
		<input id="leaveId" name="leaveId" type="hidden" value="${leaveBean.leaveId}"/>
		 
		<div class="control-group">
			<label class="control-label">请假日期:</label>
			<div class="controls">
				<%-- <input id="leaveDate" name="leaveDate" class="required Wdate"  type="text" 
				
				value='<fmt:formatDate value="${leaveBean.leaveDate}" type="date" pattern="yyyy-MM-dd"/>'
				onFocus="WdatePicker({dateFmt:'yyyy-MM-dd' ,isShowClear:false,readOnly:true})"/> --%>
				<input id="leaveDate" name="leaveDate" class="required laydate-icon layer-date"  type="text" 
				
				value='<fmt:formatDate value="${leaveBean.leaveDate}" type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="help-inline"><span style="color:red">*</span> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请假天数:</label>
			<div class="controls">
				<input id="leaveDays" name="leaveDays" class="required digits input-small" type="text" value="${leaveBean.leaveDays}" maxlength="5"/>
				<span class="help-inline"><span style="color:red">*</span> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请假原因:</label>
			<div class="controls">
				<textarea id="leaveReason" name="leaveReason" maxlength="200" class="input-xlarge required" rows="3">${leaveBean.leaveReason}</textarea>
				<span class="help-inline"><span style="color:red">*</span> </span>
			</div>
		</div>  
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<textarea id="remark" name="remark" maxlength="200" class="input-xlarge" rows="3">${userDto.remarks}</textarea>
			</div>
		</div>	
		 
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
</body>
</html> 