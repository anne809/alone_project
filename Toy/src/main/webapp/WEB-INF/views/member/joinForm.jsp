<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="resources/js/jquery-3.5.0.js"></script>
<script>
	$(function() {

		var checkid = false;
		var checkemail = false;
		var checkcountry = false;
		var checkprovision = false;

		$('form').submit(function() {
			//이름 확인
			if (!checkid) {
				alert("사용가능한 이름(id)으로 입력하세요.");
				$("input:eq(0)").val('').focus();
				return false;
			}
			//이메일 확인
			if (!checkemail) {
				alert("email형식을 확인하세요");
				$("input:eq(1)").focus();
				return false;
			}
			//국가 확인
			if (!checkcountry) {
				alert("국가를 선택하세요.");
				$("#selectcountry").focus();
				return false;
			}

			if ($("#primary-switch").is(":checked") == false) {
				alert("개인정보 동의를 체크해주세요")
				return false;
			}
		}); //submit

		//이메일 유효성 검사
		$("input:eq(1)").on(
				'keyup',
				function() {

					$("#email_message").empty();

					//[A-Za-z0-9_]와 동일한 것이 \w
					//+는 1회 이상 반복을 의미합니다. {1,}와 동일합니다
					//\w+는 [A-Za-z0-9_]를 1개 이상 사용하라는 의미입니다

					var pattern = /\w+@\w+[.]\w{3}/;
					var email = $("input:eq(1)").val();

					if (!pattern.test(email)) {
						$("#email_message").css('color', 'red').html(
								"이메일 형식이 맞지 않습니다.");

						checkemail = false;
						return;
					}

					//중복 이메일 검사 > MemberController 
					$.ajax({
						url : "emailcheck.net",
						data : {
							"email" : email
						},
						success : function(resp) {
							if (resp == -1) { //db에 해당 id가 없는 경우
								$("#email_message").css('color', 'green').html(
										"사용 가능한 이메일 입니다.");
								checkemail = true;
							} else { //db에 해당 id가 있는 경우(0) 걍 내가 정해서 하믄 대 
								$("#email_message").css('color', 'blue').html(
										"사용 중인 이메일 입니다.");
								checkemail = false;
							}
						}
					});
				}); //email keyup 이벤트 처리 끝 

		//이름(id) 유효성 검사
		$("input:eq(0)").on(
				'keyup',
				function() {
					$("#message").empty(); //처음에 pattern에 적합하지 않은 경우 메시지 출력 후 적합한
					//[A-Za-z0-9_]의 의미가 \w
					//var pattern = /^\w{5,12}$/; 
					var pattern = /[a-zA-Z]/; //영어
					var id = $("input:eq(0)").val();
					if (!pattern.test(id)) {
						$("#message").css('color', 'red').html(
								"영문자로만 입력 가능합니다.");

						checkid = false;
						return;
					}
					$.ajax({
						url : "idcheck.net",
						data : {
							"id" : id
						},
						success : function(resp) {
							if (resp == -1) { //db에 해당 id가 없는 경우
								$("#message").css('color', 'green').html(
										"사용 가능한 아이디 입니다.");
								checkid = true;
							} else { //db에 해당 id가 있는 경우(0) 걍 내가 정해서 하믄 대 
								$("#message").css('color', 'blue').html(
										"사용 중인 아이디 입니다.");
								checkid = false;
							}
						}
					}); //ajax end
				}); //id keyup end

		//국가 유효성 검사
		$("#selectcountry").change(function() {
			$("#country_message").empty();

			//var countryVal = $("#selectcountry").val();
			if ($("#selectcountry").val() == '') {
				$("#country_message").css('color', 'red').html("국가를 선택해주세요.");
				checkcountry = false;

			} else {
				checkcountry = true;
				return;
			}
		});

	});
</script>
</head>
<div class="bradcam_area breadcam_bg">
	<h3>어워즈 가입</h3>
</div>
<!-- forQuery_start -->
<!--   <div class="forQuery">-->
<div class="container">
	<div class="section-top-border">
		<div class="row" style="justify-content: center;">
			<div class="col-lg-8 col-md-8">
			<hr style="border:1px solid #F5D0A9;" width="100%">
				<h3 class="mb-30">약관 동의</h3>
				<form name="joinform" action="joinProcess.net" method="post">
					<div class="mt-10">
						<input type="text" name="id" placeholder="영문이름  Name" required
							class="single-input"><span id="message"
							style="font-size: 13px;"></span>
					</div>

					<div class="mt-10">
						<input type="text" name="email" placeholder="이메일 E-mail" required
							class="single-input"><span id="email_message"
							style="font-size: 13px;"></span>
					</div>

					<div class="mt-10">
						<input type="password" name="password" placeholder="비밀번호 Password"
							onblur="this.placeholder = '비밀번호 Password'" required
							class="single-input">
					</div>

					<div class="input-group-icon mt-10">
						<div class="icon">
							<i class="fa fa-plane" aria-hidden="true"></i>
						</div>
						<div class="form-select" id="country">
							<select id="selectcountry" name="country">
								<option value="" selected>국가/지역</option>
								<option value="korea">대한민국</option>
								<option value="malli">말리</option>
								<option value="NewYork">뉴욕</option>
								<option value="Mauritius">모리셔스</option>
							</select>
						</div>
						<span id="country_message" style="font-size: 13px;"></span>
					</div>

					<br>
					<section class="sample-text-area"
						style="padding: 30px 0 70px 0;!important">
						<div class="container box_1170">
							<h3 class="text-heading">개인 정보 동의</h3>
							<p class="sample-text">
							<div class="switch-wrap d-flex justify-content-between">
								<p>아래 모든 필수 및 선택 사항에 동의합니다.</p>
								<div class="primary-switch">
									<input type="checkbox" id="primary-switch" checked> <label
										for="primary-switch"></label>
								</div>
							</div>
							<br> 몬타나 그룹의 글로벌 개인정보 보호정책과 아래 설명된 내용에 따라 몬타나 인터내셔널이 <u>본인의
								필수 개인정보를 수집하고 이용하는 것에 동의</u>합니다. 본인은 동의를 거부할 수 있으며, 거부할 경우 필요 목적과
							관련된 상품 및 서비스를 제공 받지 못할 수 있음을 이해합니다.수집 및 이용되는 개인정보 (‘필수 개인정보’) 이름,
							성별, 우편주소⋅전화번호⋅이메일 주소와 같은 연락처 정보, 신용카드 및 직불카드 번호나 기타 결제 정보 또는 금융
							정보(결제 카드 유형 포함), 선호 언어, 생년월일 및 출생지, 국적, 멤버십 또는 로열티 프로그램 정보(공동 브랜드
							결제 카드, 여행 파트너사 프로그램 제휴, 멤버십 번호, 멤버십 등급, 멤버십 포인트 정보 포함), 직장 정보, 이전
							투숙 또는 연락, 구매한 제품과 서비스, 특별 서비스와 어메니티 요청과 같은 예약 및 투숙 관련 정보, SNS 계정
							및 로열티 계정에 연결 시 이용 가능한 정보, 동반자 정보, 온라인 서비스 이용 시 브라우저나 기기 정보⋅쿠키⋅IP
							주소 등 개인 식별 관련 정보. <br>
							
							<br>
							<p>
								<b>‘가입’을 선택하면 몬타나 이용약관 및 Montana Bonvoy 이용약관에 동의하게 됩니다.</b>
							</p>
						</div>
					</section>
					<div class="button-group-area mt-40">
						<button type="submit" style="width:48%;"class="genric-btn primary e-large" >가입</button>
						<button type="reset" style="width:48%;" class="genric-btn primary-border e-large">취소</button>
					</div>
				</form>
			</div>

		</div>
	</div>
</div>

</html>


