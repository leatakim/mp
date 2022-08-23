<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>회원가입</title>

        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/assets/올위어답터.ico" />

        <!-- main css -->
        <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

        <!-- 폰트어썸 (폰트)-->
        <script src="https://kit.fontawesome.com/e4f51ae88c.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Dongle&family=Gowun+Batang&family=Noto+Sans+KR:wght@100;300;400;500;700&display=swap" rel="stylesheet">

        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${contextPath}/resources/css/styles.css" rel="stylesheet" />

        <!-- signUp css -->
        <link rel="stylesheet" href="${contextPath}/resources/css/signUp-style.css">

        <!-- sweetalert-->
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    </head>
    <body class="d-flex flex-column">

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title mx-auto" id="exampleModalLabel">(로고)이용약관</h5>
                <button type="button" class="btn-close mx-0" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body py-4">
            
                <div class="d-flex flex-column align-items-center py-3">
                    <div class="px-auto mb-1">
                        <textarea cols="50" rows="10" style="resize: none">
제1조(목적)
이 약관은 포인핸드(전자상거래 사업자)가 운영하는 포인핸드몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리.의무 및 책임사항을 규정함을 목적으로 합니다.
※「PC통신, 무선 등을 이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다.」

제2조(정의)
① “몰”이란 포인핸드가 재화 또는 용역(이하 “재화 등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다.
② “이용자”란 “몰”에 접속하여 이 약관에 따라 “몰”이 제공하는 서비스를 받는 회원 및 비회원을 말합니다.
③ ‘회원’이라 함은 “몰”에 회원등록을 한 자로서, 계속적으로 “몰”이 제공하는 서비스를 이용할 수 있는 자를 말합니다.
④ ‘비회원’이라 함은 회원에 가입하지 않고 “몰”이 제공하는 서비스를 이용하는 자를 말합니다.

제3조 (약관 등의 명시와 설명 및 개정)
① “몰”은 이 약관의 내용과 상호 및 대표자 성명, 영업소 소재지 주소(소비자의 불만을 처리할 수 있는 곳의 주소를 포함), 전화번호.모사전송번호.전자우편주소, 사업자등록번호, 통신판매업 신고번호, 개인정보보호책임자등을 이용자가 쉽게 알 수 있도록 00 사이버몰의 초기 서비스화면(전면)에 게시합니다. 다만, 약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다.
② “몰은 이용자가 약관에 동의하기에 앞서 약관에 정하여져 있는 내용 중 청약철회.배송책임.환불조건 등과 같은 중요한 내용을 이용자가 이해할 수 있도록 별도의 연결화면 또는 팝업화면 등을 제공하여 이용자의 확인을 구하여야 합니다.
③ “몰”은 「전자상거래 등에서의 소비자보호에 관한 법률」, 「약관의 규제에 관한 법률」, 「전자문서 및 전자거래기본법」, 「전자금융거래법」, 「전자서명법」, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」, 「방문판매 등에 관한 법률」, 「소비자기본법」 등 관련 법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.
④ “몰”이 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 몰의 초기화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. 다만, 이용자에게 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 “몰“은 개정 전 내용과 개정 후 내용을 명확하게 비교하여 이용자가 알기 쉽도록 표시합니다.
⑤ “몰”이 약관을 개정할 경우에는 그 개정약관은 그 적용일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의 약관조항이 그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정약관 조항의 적용을 받기를 원하는 뜻을 제3항에 의한 개정약관의 공지기간 내에 “몰”에 송신하여 “몰”의 동의를 받은 경우에는 개정약관 조항이 적용됩니다.
⑥ 이 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 전자상거래 등에서의 소비자보호에 관한 법률, 약관의 규제 등에 관한 법률, 공정거래위원회가 정하는 전자상거래 등에서의 소비자 보호지침 및 관계법령 또는 상관례에 따릅니다.

                        </textarea>
                    </div>
                    <div class="px-3 mt-4">
                        <input type="checkbox" class="mx-1" id="checkBox"><label for="checkBox">ALL WE ADOPT 이용약관에 동의합니다. </label>
                    </div>
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <button type="submit" class="d-grid btn btn-primary btn-lg col-sm-11" id="goSignUp">회원가입</button>
            </div>
            </div>
        </div>
    </div>  <!-- 모달끝 -->

        <main class="flex-shrink-0">

            <!-- 헤더 -->
            <%-- <jsp:include page="/WEB-INF/views/common/header.jsp" /> --%>

            <!-- 회원가입 페이지 -->
            <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5" id="bg-light">
                        <div class="text-center mb-5">
                            <%-- <div class=" bg-gradient text-white rounded-3 mb-3"><img src="${contextPath}/resources/images/logo.png" id="signUpLogo"></div> --%>
                            <h1 class="fw-bolder">회원가입</h1>
                            <p class="lead fw-normal text-muted mb-0" id="welcome">환영합니다!</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">

                                <form action="${contextPath}/member/sign-up" id="signUpForm" method="POST" enctype="multipart/form-data" onsubmit="return signUpValidate()">

                                    <!-- 이메일 주소 -->
                                    <div class="form-floating mb-2 confirm-area">
                                        <input class="form-control" name="memberEmail" id="memberEmail" type="email" placeholder="name@example.com" required/>
                                        <label for="memberEmail"><span>* </span>이메일</label>
                                        <div class="spaceArea confirmBtnArea">
                                            <button type="button" id="sendBtn">인증</button>
                                            <button type="button" id="resendBtn" style='display:none;'>재발송</button>
                                            <input type="number" name="re" id="re" hidden value='0'>
                                        </div>
                                    </div>
                                    <div id="emailMsg" class="form-floating validate-area"></div>

                                    <div class="form-floating mb-2 confirm-area">
                                        <input class="form-control" id="cNumber" type="email" placeholder="인증번호 6자리"  />
                                        <label for="email" id="cMessage2"><span>* </span>인증번호</label>
                                        <div class="spaceArea confirmBtnArea">
                                            <button type="button" id="cBtn">확인</button>
                                        </div>
                                    </div>
                                    <div id="cMessage2" class="form-floating validate-area"></div>
                                    
                                    <!-- 비밀번호 -->
                                    <div class="form-floating mb-3 confirm-area">
                                        <input class="form-control" name="memberPw" id="memberPw" type="password" placeholder="Enter your name..."  />
                                        <label for="memberPw"><span>* </span>비밀번호</label>
                                    </div>

                                    <!-- 비밀번호 확인-->
                                    <div class="form-floating mb-2 confirm-area">
                                        <input class="form-control" id="memberPwConfirm" type="password" placeholder="Enter your name..."  />
                                        <label for="memberPwConfirm"><span>* </span>비밀번호 확인</label>
                                        <input type="number" name="re" id="re" hidden value='0'>
                                    </div>
                                    <div id="pwMsg" class="form-floating validate-area"></div>

                                    <!-- 이름 -->
                                    <div class="form-floating mb-2 confirm-area">
                                        <input class="form-control" name="memberName" id="memberName" type="text" placeholder="Enter your name..."  />
                                        <label for="memberName"><span>* </span>이름 </label>
                                    </div>
                                    <div id="nameMsg" class="form-floating validate-area"></div>

                                    <!-- 전화번호 -->
                                    <div class="form-floating mb-3 confirm-area">
                                        <input class="form-control" name="memberTel" id="memberTel" type="tel" placeholder="(010) 456-7890"  />
                                        <label for="memberTel"><span>* </span>휴대폰 번호(-제외) </label>
                                        <div class="spaceArea confirmBtnArea">
                                            <button id="confirmBtn" type="button">인증</button>
                                        </div>
                                    </div>

                                    <!-- 인증번호-->
                                    <div class="form-floating mb-2 confirm-area">
                                        <input class="form-control" id="number" type="tel" placeholder="(010) 456-7890"  />
                                        <label for="number" id="cMessage"><span>* </span>인증번호</label>
                                    </div>
                                    <div id="telMsg" class="form-floating validate-area"></div>

                                    <!-- 주소 (다음 api) -->
                                    <div class="form-floating mb-3 confirm-area">
                                        <input class="form-control" type="text" id="sample4_postcode" name="memberAddress" maxlength="6">
                                        <label for="memberAddress">우편번호</label>
                                        <div class="spaceArea confirmBtnArea">
                                            <button type="button" onclick="sample4_execDaumPostcode()">검색</button>
                                        </div>
                                    </div>

                                    <div class="form-floating mb-3 confirm-area">
                                        <input class="form-control" type="text" name="memberAddress" id="sample4_roadAddress">
                                        <label for="memberAddress">도로명주소</label>
                                    </div>

                                    <div class="form-floating mb-3 confirm-area">
                                        <input class="form-control" type="text" name="memberAddress" id="sample4_detailAddress">
                                        <label for="memberAddress">상세주소</label>
                                    </div>
                                    <div class="form-floating validate-area"></div>

                                    <!-- 프로필 사진 추가-->
                                    <div class="form-floating confirm-area profile-image-area">
                                        <div id="img-area">
                                            <img src="${contextPath}/resources/images/user.png" id="profileImg">
                                        </div>

                                        <div class="imageBtnArea">
                                            <input id="profileImage" type="file" name="uploadImage" accept="image/*">
                                            <label for="profileImage">등록 </label>
                                            <input type="hidden" id="deleteImage" name="deleteImage"> <!-- 가입시에는 필요가 없다. -->
                                            <label for="deleteImage" id="del" >삭제</label>
                                        </div>
                                    </div>   
                                    <div class="form-floating validate-area"></div>

                                    <!-- Submit Button-->
                                    <div class="d-grid"><button data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-primary btn-lg" id="signUpBtn" type="button" >회원가입</button></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <!-- 푸터 -->
        <%-- <jsp:include page="/WEB-INF/views/common/footer.jsp" /> --%>

        <script>
            const contextPath = "${contextPath}";
            const memberProfile = "${contextPath}/resources/images/user.png";
            const signUpBtn = document.getElementById("signUpBtn"); // 결제버튼
        	const signUpForm = document.getElementById("signUpForm"); // 결제폼태그
            const checkBox = document.getElementById("checkBox"); //체크박스
			
        	signUpBtn.addEventListener("click",function(){ // 회원가입 클릭 했을때
                    
                    goSignUp.addEventListener("click", function(){
                        if(checkBox.checked){
                            if(signUpValidate()){
                                signUpForm.submit();
                            }
                        }else{
                            signUpForm.onsubmit = false;
                        }
                    });
                	
        		
        	})
        </script>

        <!-- jQuery 라이브러리 추가(CDN) -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        
        <!-- 다음 주소 api-->
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

        <!-- Core theme JS-->
        <script src="${contextPath}/resources/js/scripts.js"></script>
        <script src="${contextPath}/resources/js/signUp.js"></script>
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>