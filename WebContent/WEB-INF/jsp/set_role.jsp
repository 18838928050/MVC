<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置角色</title>
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
<!--layer JS-->
<script src="${ctx }/static/lib/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	function setRole() {

		var url = "${ctx}/func/user/do_update_user";
		var param = $("#setRoleForm").serialize();
		$("#selectRole").find("option").each(function(index, domEle) {
			param = param + "&roleList=" + $(domEle).val();
		});
		$.post(url, param, function(data) {
			data = eval("(" + data + ")");
			if (data.success) {
				layer.alert(data.msg, {
					icon : 6, btn:['确定'], title:'信息'
				}, function(index) {
					layer.close(index);
					parent.location.reload();
				});
			} else {
				layer.alert(data.msg, {
					icon : 6, btn:['确定'], title:'信息'
				});
			}
		});

	}

	function addSelectRole() {
		var $options = $('#roleCode option:selected');//获取当前选中的项  
		var $remove = $options.remove();//删除下拉列表中选中的项  
		$remove.appendTo('#selectRole');//追加给对方  
	}
	function delSelectRole() {
		var $options = $('#selectRole option:selected');//获取当前选中的项  
		var $remove = $options.remove();//删除下拉列表中选中的项  
		$remove.appendTo('#roleCode');//追加给对方  
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
	<div class="space1"></div>
	<div class="space1"></div>
	<div class="space1"></div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<form id="setRoleForm">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<div class="">
										<div class="form_gridinner">
											<div class="row">
												<div class="clear"></div>
											</div>
											<div class="row form-group">
												<label class="col-lg-2 form_name">
												<span class="red">*</span>姓名&nbsp;&nbsp;</label>
												<div class="col-lg-4">
													<input type="hidden" name="id" value="${model.id }" />
													<input type="hidden" name="state" value="${model.state }" />
													<input type="text" value="${model.name }"  class="form-control" readonly="readonly">
												</div>
												<div class="clear"></div>
											</div>
											<div class="row form-group">
												<div class="col-lg-2 form_name">
													<span class="red">*</span>
													角色&nbsp;&nbsp;
												</div>
												<div class="col-lg-4">
													<select id="roleCode" multiple="multiple"
														style="height: 150px; width: 100%;">
														<c:forEach items="${roleList}" var="item">
															<option value="${item.id}">${item.name}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-lg-2" style="text-align: center;">
													<br /> <br />
													<a href="javascript:void(0);" onclick="addSelectRole();" class="btn btn-sm btn-success" style="width: 40%;">
														&gt;&gt;
													</a>
													<br /> <br />
													<a href="javascript:void(0);" onclick="delSelectRole();" class="btn btn-sm btn-success" style="width: 40%;">
														&lt;&lt;
													</a>
												</div>
												<div class="col-lg-4">
													<select id="selectRole" multiple="multiple" style="height: 150px; width: 100%;">
														<c:forEach items="${userRole}" var="item">
															<option value="${item.roleId}">${item.roleName}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-lg-2"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<div class=""></div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<div class="panel-footer">
							<a href="javascript:void(0)" onclick="setRole()" class="btn btn-sm btn-primary pull-right publish" style="width: 100px; margin-left: 10px;">
								<i class="icon iconfont" style="font-family: 'Arial Black', Gadget, sans-serif; font-size: 14px;">&#xe60a;</i>
								&nbsp;&nbsp;保存
							</a>
							<div class="clear"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>