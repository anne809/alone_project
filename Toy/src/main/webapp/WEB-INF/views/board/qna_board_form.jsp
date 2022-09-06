<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/pages/wizard/wizard-4.css?v=7.0.4"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/css/style.bundle.css">
<script src="resources/js/jquery-3.5.0.js"></script>
<!-- <script src="resources/js/writeform.js"></script> -->


</head>
<div class="bradcam_area breadcam_bg">
	<h3>문의글 작성</h3>
</div>
<body>

	<div class="container">
		<div class="section-top-border">
			<div class="row" style="justify-content: center;">
				<div class="col-lg-8 col-md-8">
					<hr style="border: 1px solid #F5D0A9;" width="100%">
					<!--begin::Form-->
					<form action="Board_write_ok.no" method="post"
						enctype="multipart/form-data" name="boardform">
						<div class="card-body">
						<!-- 말머리 NO_SORT 부분  -->
							 <div class="form-group row">
								<label class="col-lg-3 col-form-label text-lg-right">말머리
									선택</label>
								<div class="col-lg-2">
									<div class="rows">
										<select name="BOARD_SORT" class="form-control" id="viewcount">
											<option value="문의" selected>문의</option>
											<!-- <option value="문의">문의</option>
											<option value="문의">문의</option> -->
										</select>
									</div>
								</div>
							</div> 

							<div class="form-group row">
								<label class="col-lg-3 col-form-label text-lg-right">글쓴이</label>
								<div class="col-lg-4">
									<input name="BOARD_NAME" id="board_name" type="text" size="10"
										maxlength="30" class="form-control">
								</div>  
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label text-lg-right">제목</label>
								<div class="col-lg-7">
									<input name="BOARD_SUBJECT" id="board_subject" type="text"
										size="50" maxlength="100" class="form-control"
										placeholder="제목을 입력하세요">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-lg-3 col-form-label text-lg-right">내용</label>
								<div class="col-lg-7">
									<textarea name="BOARD_CONTENT" style="height: 325px"
										class="form-control" rows="3" placeholder="내용을 입력하세요"></textarea>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label text-lg-right">파일첨부</label>
								<div class="col-lg-9">
									<div class="dropzone dropzone-multi" id="kt_dropzone_4">
										<div class="dropzone-panel mb-lg-0 mb-2">
											<input type="file" id="upfile" name="uploadfile"> <span
												id="filevalue"></span>
										</div>
										<div class="dropzone-items"></div>
									</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label text-lg-right">비밀번호</label>
								<div class="col-lg-4">
									<input type="password" class="form-control" id="pass"
										placeholder="비밀번호를 입력하세요" name="BOARD_PASS">
								</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="row">
								<div class="col-lg-3"></div>
								<div class="col-lg-9">
									<button type="submit" class="genric-btn primary circle">등록</button>
									<!--  	<a href="javascript:window.history.back();"
										class="btn btn-outline-secondary">취소
										-->
									</button>
								</div>
							</div>
						</div>
					</form>
					<!--end::Form-->
				</div>
				<!--end::Card-->
			</div>
		</div>
	</div>
</body>