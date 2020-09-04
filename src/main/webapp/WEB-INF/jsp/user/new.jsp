<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>


<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
        	<fm:form action="save" method="post" modelAttribute="user" enctype="multipart/form-data">
        		<div>
                    <label for="userCode">用户编码：</label>
                    <input type="text" name="userCode" id="userCode" value="${user.userCode}">
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" value="${user.userName}"> 
					<font color="red"><fm:errors path="userName" cssStyle="color:red"/></font>
                </div>
                <div>
                    <label for="userPassword">用户密码：</label>
                    <input type="password" name="userPassword" id="userPassword" value=""> 
					<font color="red"><fm:errors path="userPassword" cssStyle="color:red"/></font>
                </div>
                <div>
                    <label for="ruserPassword">确认密码：</label>
                    <input type="password" name="ruserPassword" id="ruserPassword" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label >用户性别：</label>
					<select name="gender" id="gender" >
					    <option value="1" selected="selected">男</option>
					    <option value="2">女</option>
					 </select>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" class="Wdate" id="birthday" name="birthday" 
					 onclick="WdatePicker();" value="<fmt:formatDate 
					 value='${user.birthday}' type='date'/>">
					<font color="red"></font>
					<fm:errors path="birthday" cssStyle="color:red"/>
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <input type="text" name="phone" id="phone" value="${user.phone}"> 
					<font color="red"><fm:errors path="phone" cssStyle="color:red"/></font>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                   <input name="address" id="address"  value="${user.address}">
                </div>
                <div>
                    <label >用户角色：</label>
                    <!-- 列出所有的角色分类 -->
					<select name="role.id">
						<c:forEach items="${APPLICATION_ROLE}" var="r">
							<option value="${r.id}" <c:if test="${r.id == user.role.id}">selected</c:if>>${r.roleName}</option>
						</c:forEach>
	        		 </select>
	        		<font color="red"></font>
                </div>
                 <div>
                    <label for="attach">选择文件：</label>
                   <input name="attach" id="attach" type="file">
                </div>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
        	</fm:form>
        </div>
</div>
</section>
<!--<%@include file="/WEB-INF/jsp/common/foot.jsp" %>-->
<script type="text/javascript">
	$(function(){
		$("#userCode").blur(function(){
			//alert($(this).val())
			$.ajax({
				url:"check/"+$(this).val(),
				dataType:"json",
				success:function(data){
					if(data == 1){
						$("#userCode").next().html("用户编码已存在")
					}else{
						$("#userCode").next().html("用户编码可以使用")
					}
				}
			})
		})
	})
</script>