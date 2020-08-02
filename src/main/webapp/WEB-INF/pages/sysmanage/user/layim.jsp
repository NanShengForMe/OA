<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/include/head.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>LayIM测试</title>
<link href="${ctxJsAndcss}/layer-v2.3/layim/layui/css/layui.css" type="text/css" rel="stylesheet"/>
<script src="${ctxJsAndcss}/layer-v2.3/layim/layui/layui.js"></script>

<script src="${ctxJsAndcss}/layer-v2.3/layim/layim.js"></script>
</head>
<body>
<script>
layui.use('layim', function(layim){
  //先来个客服模式压压精
  layim.config({
    brief: true //是否简约模式（如果true则不显示主面板）
  }).chat({
    name: '客服姐姐'
    ,type: 'friend'
    ,avatar: 'http://tp1.sinaimg.cn/5619439268/180/40030060651/1'
    ,id: -2
  });
});

layim.chat({
	  name: 'LayIM畅聊'
	  ,type: 'group' //群组类型
	  ,avatar: 'http://tp2.sinaimg.cn/5488749285/50/5719808192/1'
	  ,id: 10000000 //定义唯一的id方便你处理信息
	  ,members: 123 //成员数，不好获取的话，可以设置为0
	});
</script>
</body>
</html>