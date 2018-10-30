<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员管理</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- Css for boot -->
<link href="${ctx }/static/public/css/boot.css" rel="stylesheet" type="text/css" />
<!--Admin Core Css-->
<link href="${ctx }/static/public/css/admin.css" rel="stylesheet" type="text/css" />
<!-- My Fonts -->
<link href="${ctx }/static/lib/iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<!--Tree Menu-->
<link href="${ctx }/static/lib/tree-menu/tree.css" rel="stylesheet" type="text/css" />
<!--jquery-->
<script src="${ctx}/static/public/js/jquery-1.12.4.min.js" type="text/javascript"></script>
<!-- 弹窗插件 -->
<script src="${ctx }/static/lib/layer/layer.js" type="text/javascript"></script>
<!-- 分页插件 -->
<script src="${ctx}/static/lib/laypage/laypage.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	// 初始化分页
	laypage({
		  cont: $("#div_page"), //容器。值支持id名、原生dom对象，jquery对象,
		  pages: '${pageInfo.pages}', //总页数
		  skin: 'yahei', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00
		  groups: 7, //连续显示分页数
		  first: '首页',
	      last:'尾页',
	      prev:'上一页',
	      next:'下一页',
		  curr: function(){
			  return ${pageInfo.pageNum};
		  }(), 
	      jump: function(e, first){ //触发分页后的回调
	    	  if(!first){ //一定要加此判断，否则初始时会无限刷新
	          	  $("input[name='pageNum']").val(e.curr);
	          	  $("#userForm").submit();
	      	  }
		  }
	});
	
	//搜索
	$('#user_search').on('click',function(){
		 $("input[name='pageNum']").val(1);
		$("#userForm").submit();
	});
	
	$("#corpId option").each(function() {
		if ($(this).val() == '${vo.corpId}') {
		$(this).attr("selected", "selected");
		}
	});  
	
	$("#roleId option").each(function() {
		if ($(this).val() == '${vo.roleId}') {
		$(this).attr("selected", "selected");
		}
	}); 
});

function set_role(userId) {
	layer.open({
		type: 2,
		title: '设置角色',
		fix: false,
		shadeClose: true,
		area: ['660px', '400px'],
		content: "${ctx}/func/user/to_set_role?userId=" + userId,
	});
}	

//重置密码
function resetPwd(id){
	layer.confirm('是否重置密码?', {icon: 3,btn: ['确定', '取消'], title:'提示'}, function(index){
		var url = '${ctx}/func/user/resetPwd';
		param = {
			"id":id
		};
		$.post(url, param, function(data){
			data = eval("(" + data + ")");
			if(data.success) {
				var back_pass = data.msg;
				layer.alert(back_pass, {icon: 6, btn:['发送邮件','取消']}, function(index){
					$.ajax({
						type: "post",
						dataType: "json",
						async: false,
						data: {"id":id,"password": back_pass},
						url: "${ctx}/func/user/do_send_email",
						success: function(data) {
							if(data.success){
								layer.alert(data.msg, {icon: 6,btn:['确定']}, function(index){
									layer.close(index);
									window.location.reload();
								});
							}else{
								layer.alert(data.msg, {icon: 6,btn:['确定']}, function(index){
									layer.close(index);
								});
							}
						}
					});
				});

			} else {
				layer.alert(data.msg, {icon: 6, btn:['确定']});
			}
		});
		});
}



//更新状态，启用/禁用
function updateState(state,id){
	var confirm = "";
	if(state == 0){
		confirm = '是否启用?';
	}
	if(state == 1){
		confirm = '是否禁用?';
	}
	layer.confirm(confirm, {icon: 3, btn: ['确定', '取消'], title:'提示'}, function(index){
		var url = '${ctx}/func/user/update_state';
		param = {
			"state":state,
			"id":id
		};
		$.post(url, param, function(data){
			data = eval("(" + data + ")");
			if(data.success) {
				layer.alert(data.msg, {icon: 6, btn:['确定']}, function(index){
				layer.close(index);
				window.location.reload();
				});
			} else {
				layer.alert(data.msg, {icon: 6, btn:['确定']});
			}
		});
		});
}

function _toAddUser(){
	location.href = "${ctx }/func/user/to_add_user";
}

function _toUpdateUser(userId){
	location.href = "${ctx }/func/user/to_update_user?userId="+userId
}
</script>

<style>
label {
	margin: 0px;
}

.form-control {
	padding: 0px;
	border-radius: 0px;
}
</style>
</head>

<body>
	<div class="row">
		<div class="col-lg-12 now_position">
			<i class="icon iconfont" style="font-family: 'Arial Black', Gadget, sans-serif; font-size: 14px;">&#xe62d;</i>
				&nbsp;首页&nbsp;/&nbsp;系统管理&nbsp;/&nbsp;人员管理
		</div>
	</div>
	<div class="space1"></div>
	<div class="space1"></div>
	<div class="space1"></div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="row">
							<form id="userForm" action="${ctx }/func/user/to_user_list" method="post">
								<input type="hidden" name="pageNum" value="${pageInfo.pageNum }" />
								<div class="col-lg-12">
									<div class="row form-group">
										<label class="col-lg-1 form_name">机构名称&nbsp;&nbsp;</label>
										<div class="col-lg-2">
											<select class="form-control" name="corpId" id="corpId">
												<option value="">--请选择--</option>
												<option <c:if test="${1 == item.id }">selected="selected"</c:if> value="1">中粮集团</option>
												<c:forEach items="${corpList }" var="item">
													<option <c:if test="${vo.corpId == item.id }">selected="selected"</c:if> value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
										<label class="col-lg-1 form_name">角色&nbsp;&nbsp;</label>
										<div class="col-lg-2">
											<select class="form-control" name="roleId" id="roleId">
												<option value="">--请选择--</option>
												<c:forEach items="${roleList }" var="role">
													<option value="${role.id }">${role.name }</option>
												</c:forEach>
											</select>
										</div>
										<label class="col-lg-1 form_name">姓名&nbsp;&nbsp;</label>
										<div class="col-lg-2">
											<input type="text" class="form-control" name="name" value="${vo.name}" />
										</div>
										<label class="col-lg-1 form_name">帐号&nbsp;&nbsp;</label>
										<div class="col-lg-2">
											<input type="text" class="form-control" name="account" value="${vo.account}" />
										</div>
										<div class="clear"></div>
									</div>
									<div class="row form-group">
										<label class="col-lg-1 form_name">显示条数&nbsp;&nbsp;</label>
										<div class="col-lg-2">
											<select class="form-control" id="pageSize" name="pageSize">
												<option <c:if test="${vo.pageSize == 10}"> selected="selected"</c:if> value="10">10</option>
												<option <c:if test="${vo.pageSize == 20}"> selected="selected"</c:if> value="20">20</option>
												<option <c:if test="${vo.pageSize == 50}"> selected="selected"</c:if> value="50">50</option>
												<option <c:if test="${vo.pageSize == 100}"> selected="selected"</c:if> value="100">100</option>
											</select>
										</div>
										<label class="col-lg-1 form_name"></label>
										<div class="col-lg-2 pull-right">
											<a href="javascript:" id="user_search" class="btn btn-primary btn-xs" style="width: 80%;">
												<i class="icon iconfont" style="font-family: 'Arial Black', Gadget, sans-serif; font-size: 14px">&#xe611;</i>
												搜索
											</a>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="pull-left list_name">人员管理</div>
							<div class="pull-right">
								<a href="javascript:;" onclick="_toAddUser();" class="btn btn-success btn-xs">
									<i class="icon iconfont" style="font-family: 'Arial Black', Gadget, sans-serif; font-size: 14px">&#xe612;</i>
									新增
								</a>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<div class="table-responsive">
										<table
											class="table table-striped table-bordered table-hover table-minwidth">
											<thead>
												<tr>
													<th class="center">序号</th>
													<th class="center">机构名称</th>
													<th class="center">姓名</th>
													<th class="center">帐号</th>
													<th class="center">邮箱</th>
													<th class="center">座机</th>
													<th class="center">手机</th>
													<th class="center">状态</th>
													<th class="center">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${pageInfo.list }" var="user" varStatus="index">
													<tr>
														<td align="center">${index.index+1 }</td>
														<td>${user.corpName }</td>
													
														<td>${user.name }</td>
														<td>${user.account }</td>
														<td>${user.eMail }</td>
														<td align="center">${user.landline }</td>
														<td align="center">${user.telephone }</td>
														<td align="center">
															<c:if test="${user.state eq '0' }">
																正常
															</c:if>
															<c:if test="${user.state eq '1' }">
																禁用
															</c:if>
														</td>
														<td align="center">
															<a href="javascript:;" onclick="resetPwd(${user.id});" class="link2 cu" title="重置密码">
																重置密码
															</a>
															&nbsp;
															<a href="javascript:;" onclick="_toUpdateUser('${user.id}');" class="link2 cu" title="编辑">
																编辑
															</a>
															&nbsp;
															<c:if test="${user.state == 1}">
																<a href="javascript:;" onclick="updateState(0,'${user.id }')" class="link2 cu">
																	 启用
																</a>
																&nbsp;
                      										</c:if>
                      										<c:if test="${user.state == 0 }">
																<a href="javascript:" onclick="updateState(1,'${user.id }')" class="link2 cu">
																	禁用
																</a>&nbsp;
                      										</c:if>
                      									</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<div align="right" class="page font1">
										<div id="div_page"></div>
									</div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>