<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form id="frm" method="post" action="${pageContext.request.contextPath }/user/list">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>用户名：</span>
					 <input name="userName" class="input-text"	type="text" value="${user.userName }">
					 
					 <span>用户角色：</span>
					 <select name="role.id">
					 	<option value="0">全部</option>
						<c:forEach items="${APPLICATION_ROLE}" var="r">
							
							<option value="${r.id }" <c:if test="${r.id == user.role.id }">selected</c:if>>${r.roleName }</option>
						</c:forEach>
	        		</select>
					 
					 <input type="hidden" id="current" name="current" value="1"/>
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="new">添加用户</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户角色</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach items="${users}" var="u">
                	<tr>
                		<td>${u.id }</td>
                		<td>${u.userName }</td>
                	
                		<td>
                			<c:if test="${u.gender == 1}">男</c:if>
                			<c:if test="${u.gender == 2}">女</c:if>
                		</td>
                		<td><fmt:formatDate value="${u.birthday }" pattern="yyyy-MM-dd"/></td>
                		<td>${u.phone }</td>
                		<td>${u.role.roleName }</td>
                		<td>
                			<a href="javascript:;" class="delrow" did=${u.id}>删除</a>
                			
                			<a href="get/${u.id}" >修改</a>		
                		</td>
                	</tr>
                	
                </c:forEach>
                <tr>
                	<td colspan="7">
                		<a href="javascript:jump(${page.first})">首页</a>
                		<a href="javascript:jump(${page.previous})">上一页</a>
                		<a href="javascript:jump(${page.next})">下一页</a>
                		<a href="javascript:jump(${page.last})">末页</a>
                		${page.current}/${page.totalPage}
                		<input type="text" id="goPageNum" name="" size="3">
                		<input type="button" onclick="go()" value="Go">
                	</td>
                </tr>
			</table>
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
		  	
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<script type="text/javascript">
	function go(){
		jump(document.getElementById("goPageNum").value);
	}
	
	function jump(num){
		
		document.getElementById("current").value = num;
		
		document.getElementById("frm").submit();
	
	}
</script>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>

<script type="text/javascript">
	
	$(function(){
		
		$(".delrow").click(function(){
			
			var did  = $(this).attr("did");
			
			var o = $(this);
			
			if(confirm("你确定要删除该用户吗？")){
			
				$.ajax({
				
					url : "delete/" + did,
					
					dataType : "json",
					
					success:function(data){
						
						if(data == 1){
							
							o.parent().parent().remove();
							
						}
						
					}
				
				})
			
			}	
			
		})
		
	})
	
</script>
