<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!--<c:if test="${empty memberinfo.id}">
   <script>
      location.href = "login.net";
   </script>
</c:if>-->
 <div class="header-area ">
            <div id="sticky-header" class="main-header-area">
			<!-- 상단 메뉴 바-->
                <div class="container-fluid p-0">
                    <div class="row align-items-center no-gutters">
                        <div class="col-xl-5 col-lg-6">
                            <div class="main-menu  d-none d-lg-block">
                                <nav>
								<!-- 좌측 메뉴-->
                                    <ul id="navigation">
                                       <!-- <li><a class="active" href="index.html">예약</a></li>-->
                                        <li><a href="rooms.html">몬타나 리조트 소개</a></li>
                                       <!-- <li><a href="about.html">고객문의</a></li>-->
                                        <li><a href="#">객실예약 <i class="ti-angle-down"></i></a>
                                            <ul class="submenu">
                                                <li><a href="blog.html">발리 몬타나</a></li>
                                                <li><a href="single-blog.html">몰디브 몬타나</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="#">다이닝예약 <i class="ti-angle-down"></i></a>
                                            <ul class="submenu">
                                                <li><a href="blog.html">발리 몬타나</a></li>
                                                <li><a href="single-blog.html">몰디브 몬타나</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <!-- 중앙 로고 -->
                        <div class="col-xl-2 col-lg-2">
                            <div class="logo-img">
                                <a href="main">
                                    <img src="resources/img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <!-- 중앙 로고 -->
                        <!-- Book A room hide-->
                        <div class="col-xl-5 col-lg-4 d-none d-lg-block">
                            <div class="book_room">
                                <div class="socail_links">
                                    <ul>
                                        <li>
                                        <c:if test="${memberinfo.id=='admin'}">
                                            <a href="login.net">
                                                <i style="font-size:10pt;">관리자</i>
                                            </a>
                                         </c:if>
                                            <a href="login.net">
                                                <i style="font-size:10pt;">${memberinfo.id }님</i>
                                            </a>
                                        </li>
                                         <li>
                                            <!--<a href="#">-->
                                            <a href="insert.hr">
                                                <i style="font-size:10pt;">마이페이지</i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="logout.net">
                                                <i style="font-size:10pt;">로그아웃</i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="BoardList.bo">
                                                <i style="font-size:10pt;">고객문의</i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                               <!-- <div class="book_btn d-none d-lg-block">
                                    <a class="popup-with-form" href="#test-form">예약</a>
                                </div>-->
                            </div>
                        </div>
                        <!-- Book A room hide-->
                        <div class="col-12">
                            <div class="mobile_menu d-block d-lg-none"></div>
                        </div>
                    </div>
                </div>
				<!-- 상단 메뉴 바 종료-->
            </div>
        </div>