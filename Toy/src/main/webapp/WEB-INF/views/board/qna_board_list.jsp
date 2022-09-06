<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.bundle.css">

</head>
<div class="bradcam_area breadcam_bg_2">
	<h3>고객문의</h3>
</div>
<section class="contact-section" style="padding: 50px 0;">
		<div class="container">
		<div class="row" style="justify-content: center;">
			<div class="col-lg-8 col-md-8">
		<hr style="border:1px solid #A9D0F5;" width="100%">
			<div class="card card-custom">
				<div class="card-header">
					<div class="card-title">
						<span class="card-icon"> <i
							class="flaticon2-chat-1 text-info"></i>
						</span>
						<h3 class="card-label">
							
						</h3>
					</div>
					<div class="card-toolbar">
						<!-- 글 개수 : ${listcount} &nbsp;&nbsp;-->
						<div class="rows">
							<select class="form-control" id="viewcount">
								<option value="1">1</option>
								<option value="3">3</option>
								<option value="5">5</option>
								<option value="7">7</option>
								<option value="10" selected>10</option>
							</select>
						</div>
							<a href="BoardWrite.no" class="genric-btn info-border" style="margin-left: 10px;">글쓰기</a>
					</div>
				</div>
				<div class="card-body">
					<!-- 레코드가 없으면 -->
					<!-- 	<c:if test="${listcount == 0}">
                <font size=5 style="">등록된 글이 없습니다.</font>
            </c:if>  -->
					<table class="table table-hover"
						style="text-align: center; font-size: 13px;">
						<thead>
							<tr>
								<th scope="col">글번호</th>
							    <th scope="col">말머리</th>
								<th scope="col">글제목</th>
								<th scope="col">작성자</th>
								<th scope="col">작성날짜</th>
								<th scope="col">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="num" value="${listcount-(page1-1)*limit}" />
							<c:forEach var="b" items="${boardlist}">
								<tr>
									<th scope="row">
										<%--글번호--%> <c:out value="${num}" /> <%--num 출력 --%> <c:set
											var="num" value="${num-1}" /> <%--num = num-1; 의미 --%>
									</th>
									<td>
									<%-- 말머리 --%>
									<div>${b.BOARD_SORT}</div>
								</td>
									<td>
										<%--제목 --%>
										<div>
											<c:if test="${b.BOARD_RE_LEV != 0}">
												<!-- 답글인 경우 -->
												<c:forEach var="a" begin="0" end="${b.BOARD_RE_LEV *2}"
													step="1">
                                 &nbsp;
                          </c:forEach>
												<img src='resources/image/answerLine.gif'>
											</c:if>

											<c:if test="${b.BOARD_RE_LEV == 0}">
												<!-- 원문인 경우 -->
                         &nbsp;
                      </c:if>
											<a href="./BoardDetailAction.no?num=${b.BOARD_NUM}"
												style="color: #1B283F;"> ${b.BOARD_SUBJECT} </a>
										</div>
									</td>
									<td><div>
											<span
												class="label label-lg label-light-success label-inline">
												${b.BOARD_NAME} </span>
										</div></td>
									<td><div>${b.BOARD_DATE }</div></td>
									<%--작성날짜 --%>
									<td><div>${b.BOARD_READCOUNT}</div></td>
									<%--조회수 --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 레코드가 없으면 -->
					<c:if test="${listcount == 0}">
						<a style="text-align: center; font-size: 13px;">등록된 글이 없습니다.</a>
					</c:if>
				</div>
				<div class="card-footer d-flex justify-content-between">

					<!--begin::Pagination-->
					<div
						class="d-flex justify-content-between align-items-center flex-wrap"
						style="margin: auto;">
						<div class="d-flex flex-wrap py-2 mr-3">
							<c:if test="${page1 <= 1 }">
								<a class="btn btn-icon btn-sm btn-light mr-2 my-1"><i
									class="ki ki-bold-arrow-back icon-xs"></i></a>
							</c:if>
							<c:if test="${page1 > 1}">
								<a href="./BoardList.no?page=${page1 - 1}"
									class="btn btn-icon btn-sm btn-light mr-2 my-1"><i
									class="ki ki-bold-arrow-back icon-xs"></i></a>
							</c:if>

							<c:forEach var="a" begin="${startpage}" end="${endpage}">
								<c:if test="${a == page1 }">
									<a
										class="btn btn-icon btn-sm border-0 btn-hover-info active mr-2 my-1">${a }</a>
								</c:if>
								<c:if test="${a != page1}">
									<!-- 같지 않으면, 이동할 수 있다 -->
									<a href="./BoardList.no?page=${a}"
										class="btn btn-icon btn-sm border-0 btn-light mr-2 my-1">${a }</a>
								</c:if>
							</c:forEach>

							<c:if test="${page1 >= maxpage}">
								<a class="btn btn-icon btn-sm btn-light mr-2 my-1"><i
									class="ki ki-bold-arrow-next icon-xs"></i></a>
							</c:if>
							<c:if test="${page1 < maxpage}">
								<a href="./BoardList.no?page=${page1 + 1 }"
									class="btn btn-icon btn-sm btn-light mr-2 my-1"><i
									class="ki ki-bold-arrow-next icon-xs"></i></a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>
</section>
