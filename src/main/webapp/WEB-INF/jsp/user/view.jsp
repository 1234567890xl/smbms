<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form"%>


<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
        	<fm:form action="../update" method="post" modelAttribute="user" >
        		
        		<input type="hidden" name="id" value="${user.id} "/>
        		
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
                    <label >用户性别：</label>
					    男：<input type="radio" name="gender" value="1" <c:if test="${user.gender == 1}">checked</c:if>  />
					    女：<input type="radio" name="gender" value="2" <c:if test="${user.gender == 2}">checked</c:if> />
                </div>
                
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" Class="Wdate" id="birthday" name="birthday" 
					readonly="readonly" onclick="WdatePicker();" value="<fmt:formatDate value='${user.birthday}' type='date'/>">
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
						<c:forEach items="${roles}" var="r">
							<option value="${r.id}" <c:if test="${r.id == user.userRole }">selected</c:if>>${r.roleName }</option>
						</c:forEach>
	        		 </select>
	        		<font color="red"></font>
                </div>
                 
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="修改" >
					<input type="button" onclick="javascripr:history.back()" id="back" name="back" value="返回" >
                </div>
        	</fm:form>
        </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
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