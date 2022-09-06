<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="resources/js/jquery-3.5.0.js"></script>

</head>
  <!-- bradcam_area_start -->
    <div class="bradcam_area breadcam_bg_2">
        <h3>로그인</h3>
    </div>
    <!-- bradcam_area_end -->

    <!-- ================ contact section start ================= -->
  <!--  <section class="contact-section"></section>-->
            <div class="container">
            <div class="section-top-border">
                <div class="row" style="justify-content: center;" >
                    <div class="col-12">
                    <div class="typography">
                    </div></div>
                    <div class="col-lg-8" style="text-align:center;">
                        <form class="form-contact contact_form" action="contact_process.php" method="post" id="contactForm" novalidate="novalidate">
						   <hr style="border:1px solid #A9D0F5;" width="100%">
						   <h3 class="mb-30">어워즈 로그인</h3>
                           <span style="font-size:13px;">몬타나리워즈에 오신 것을 환영합니다.<br>
														몬타나리워즈 번호와 비밀번호를 입력해 주시기 바랍니다.<br>
														*리워즈 회원이 되시면 회원만을 위한 다양한 서비스와 혜택을 받으실 수 있습니다.</span>
														<div>&nbsp;</div>
                            <div class="row">
                              <div class="col-sm-6">
                                    <div class="form-group">
                                        <input class="single-input" required name="M_CODE" id="email" 
                                        type="email" onfocus="this.placeholder = ''" 
											nblur="this.placeholder = '몬타나 리워드 또는 이메일입력'" 
											placeholder="몬타나 리워드 또는 이메일입력"
											<c:if test="${!empty savecode }">
											value="${savecode }"</c:if>
											>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <input class="single-input" name="M_PASS" id="password" type="password" onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호'" placeholder="비밀번호">
                                    </div>
                                </div>
                                <div class="col-12">
                                <div class="switch-wrap d-flex justify-content-between">
								<p style="font-size:13px;">몬타나 리워즈 번호 또는 아이디 저장</p>
								<div class="confirm-checkbox">
									<input type="checkbox" name="remember" id="confirm-checkbox" 
									>
									<label for="confirm-checkbox"></label>
								</div>
							</div>
							<div class="switch-wrap d-flex justify-content-between">
								<p style="font-size:13px;">이메일, 연락처 등의 정보가 변경되면 웹사이트에서 회원정보를 수정해주시기 바랍니다.</p>
								<div class="confirm-switch">
									
								</div>
							</div>
                            </div>
                             </div>
                            <div class="form-group mt-6">
                              <!--  <button type="submit" style="width:48%;" class="button button-contactForm boxed-btn">로그인</button>-->
                                <button type="submit" style="width:48%;" class="genric-btn info large">로그인</button>
                                  <button type="submit2" style="width:48%;" class="genric-btn info-border large">비밀번호찾기</button>
                            </div>
                        </form>
                    </div>
             <!--  <div class="col-lg-3 offset-lg-1">
                        <div class="media contact-info">
                            <span class="contact-info__icon"><i class="ti-home"></i></span>
                            <div class="media-body">
                                <h3>Buttonwood, California.</h3>
                                <p>Rosemead, CA 91770</p>
                            </div>
                        </div>
                        <div class="media contact-info">
                            <span class="contact-info__icon"><i class="ti-tablet"></i></span>
                            <div class="media-body">
                                <h3>+1 253 565 2365</h3>
                                <p>Mon to Fri 9am to 6pm</p>
                            </div>
                        </div>
                        <div class="media contact-info">
                            <span class="contact-info__icon"><i class="ti-email"></i></span>
                            <div class="media-body">
                                <h3>support@colorlib.com</h3>
                                <p>Send us your query anytime!</p>
                                <p>이메일, 연락처 등의 정보가 변경되면 웹사이트에서 회원정보를 수정해주시기 바랍니다.</p>
                            </div>
                        </div>
                    </div>-->
                </div>
            </div>
            </div>
       
</html>


