<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../includes/header.jsp"%>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Modify</h1>
<p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
    For more information about DataTables, please visit the <a target="_blank"
                                                               href="https://datatables.net">official DataTables documentation</a>.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Board Modify</h6>
    </div>
    <div class="card-body">
        <form id = "actionForm" action = "/board/modify" method = "post">
        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">bno</span>
            </div>
            <input type="text" name = "bno" class="form-control" value="<c:out value="${vo.bno}"/>" readonly>
        </div>
        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Title</span>
            </div>
            <input type="text" name = "title" class="form-control" value="<c:out value="${vo.title}"/>" >
        </div>
        <div class="input-group input-group-lg">
            <div class="input-group-prepend">
                <span class="input-group-text">Content</span>
            </div>
            <input type="text" name = "content" class="form-control" value="<c:out value="${vo.content}"/>" >
        </div>
            <div class="input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">Writer</span>
                </div>
                <input type="text" class="form-control" value="<c:out value="${vo.writer}"/>" readonly>
            </div>
            <div class="input-group input-group-lg">
                <div class="input-group-prepend">
                    <span class="input-group-text">RegDate</span>
                </div>
                <input type="text" class="form-control" value="<c:out value="${vo.regDate}"/>" readonly>
            </div>
            <div class="input-group input-group-lg">
                <button class = "btn btn-info btnList" type = "submit">List</button>
                <button class = "btn btn-warning btnModify" type = "submit">MODIFY</button>
                <button class = "btn btn-danger btnRemove" type = "submit">REMOVE</button>
            </div>
        </form>
    </div>
</div>

<%@ include file="../includes/footer.jsp"%>

<script>
    const actionForm = document.querySelector("#actionForm");
    const bno = document.querySelector('input[name="bno"]').value;

    document.querySelector(".btnList").addEventListener("click", e => {
        e.preventDefault();
        e.stopPropagation();

        window.location.href = "/board/list";
    }, false);

    document.querySelector(".btnModify").addEventListener("click", e => {
        e.preventDefault();
        e.stopPropagation();

        actionForm.action = `/board/modify/${bno}`;
        actionForm.method = 'post';
        actionForm.submit();
    }, false);

    document.querySelector(".btnRemove").addEventListener("click", e => {
        e.preventDefault();
        e.stopPropagation();

        actionForm.action = `/board/remove/${bno}`;
        actionForm.method = 'post';
        actionForm.submit();
    }, false);
</script>




<%@ include file="../includes/end.jsp"%>