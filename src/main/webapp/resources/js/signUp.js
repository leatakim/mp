// 회원가입 유효성 검사

// 유효성 검사 여부를 기록할 객체 생성
const checkObj = { 
    "memberEmail"     : false,
    "sendEmail"     : false, 
    "memberPw"        : false,
    "memberPwConfirm" : false,
    "memberName"  : false,
    "memberTel"       : false,
    // "number"       : false,
    // "timer"         : false,
};

// 이메일 유효성 검사
// const memberEmail = document.getElementById("memberEmail");
// const emailMsg = document.getElementById("emailMsg");

// memberEmail.addEventListener("input", function(){

//     // 입력이 되지 않은 경우
//     if( memberEmail.value.length == 0 ){

//         emailMsg.innerText = "이메일을 입력해주세요.";
//         emailMsg.classList.remove("confirm");
//         emailMsg.classList.add("error");

//         checkObj.memberEmail = false; // 유효 X 기록
//         return;
//     }

//     // 입력된 경우 유효성 검사 진행
//     const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

//     if( regExp.test(memberEmail.value) ){ // 유효한 경우

//         $.ajax({
//             url : "emailDupCheck",   
//             data : { "memberEmail" : memberEmail.value },
//             type : "GET", // 데이터 전달 방식 type
//             success : function(result){

//                 if(result == 0){
//                     emailMsg.innerText = "사용 가능한 이메일입니다.";
//                     emailMsg.classList.remove("error");
//                     emailMsg.classList.add("confirm");
//                     checkObj.memberEmail = true; // 유효 O 기록
//                 }

//                 if(result == 1){ // 사이트 가입 회원 이메일
//                     emailMsg.innerText = "이미 사용중인 이메일입니다.";
//                     emailMsg.classList.remove("confirm");
//                     emailMsg.classList.add("error");

//                     checkObj.memberEmail = false; // 유효 X 기록
//                 } 

//                 if(result==2){ // 카카오 가입 회원 이메일
//                     emailMsg.innerText = "사용중인 이메일입니다. 카카오 로그인 시도 혹은 다른 이메일을 입력해주세요.";
//                     emailMsg.classList.remove("confirm");
//                     emailMsg.classList.add("error");

//                     checkObj.memberEmail = false; // 유효 X 기록
//                 }             
//             },
//             error : function(){
//                 // 비동기 통신(ajax) 중 오류가 발생한 경우
//                 console.log("에러 발생");
//             }
//         });

//     }else{
//         emailMsg.innerText = "이메일 형식이 유효하지 않습니다.";
//         emailMsg.classList.remove("confirm");
//         emailMsg.classList.add("error");

//         checkObj.memberEmail = false; // 유효 X 기록
//     }

// });



// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMsg = document.getElementById("pwMsg");

memberPw.addEventListener("input", function(){

    // 입력되지 않은 경우
    if(memberPw.value.length == 0){

        pwMsg.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.";
        pwMsg.classList.remove("confirm");
        pwMsg.classList.add("error");

        checkObj.memberPw = false; // 유효 X 기록
        return;
    }

    // 입력된 경우 유효성 검사 (영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이)
    const regExp = /^[\w!@#_-]{6,30}$/; 
    if( regExp.test(memberPw.value) ){ // 비밀번호 유효

        checkObj.memberPw = true; // 유효 O 기록

        if(memberPwConfirm.value.length == 0){ // 비밀번호 유효, 확인 작성 X
            pwMsg.innerText = "유효한 비밀번호 형식입니다.";
            pwMsg.classList.remove("error");
            pwMsg.classList.add("confirm");
        } else { // 비밀번호 유효, 확인 작성 O
            checkPw(); // 비밀번호 일치 검사 함수 호출()
        }

    }else{

        pwMsg.innerText = "비밀번호 형식이 유효하지 않습니다. ";
        pwMsg.classList.remove("confirm");
        pwMsg.classList.add("error");

        checkObj.memberPw = false; // 유효 X 기록
    }
});

// 비밀번호 확인 유효성 검사

// 함수명() : 함수 호출(수행)
// 함수명   : 함수에 작성된 코드 반환
memberPwConfirm.addEventListener("input", checkPw );
// -> 이벤트가 발생 되었을 때 정의된 함수를 호출하겠다


function checkPw(){ // 비밀번호 일치 검사
    // 비밀번호 / 비밀번호 확인이 같을 경우
    if(memberPw.value == memberPwConfirm.value){

        pwMsg.innerText = "비밀번호 일치";
        pwMsg.classList.remove("error");
        pwMsg.classList.add("confirm");
        checkObj.memberPwConfirm = true; // 유효 O 기록

    } else{
        pwMsg.innerText = "비밀번호 불일치 ";
        pwMsg.classList.remove("confirm");
        pwMsg.classList.add("error");
        checkObj.memberPwConfirm = false; // 유효 X 기록
    }
}


// 이름 유효성 검사
const memberName = document.getElementById("memberName");
const nameMsg = document.getElementById("nameMsg");

memberName.addEventListener("input", function(){

    // 입력되지 않은 경우
    if(memberName.value.length == 0){

        nameMsg.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
        nameMsg.classList.remove("confirm");
        nameMsg.classList.add("error");

        checkObj.memberName = false; // 유효 X 기록
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if( regExp.test(memberName.value) ){ // 유효한 경우

        nameMsg.innerText = "정상 입력";
        nameMsg.classList.remove("error");
        nameMsg.classList.add("confirm");
        checkObj.memberName = true; // 유효 O 기록

    }else{

        nameMsg.innerText = "이름 형식이 유효하지 않습니다.";
        nameMsg.classList.remove("confirm");
        nameMsg.classList.add("error");

        checkObj.memberName = false; // 유효 X 기록

    }

});


// 전화번호 유효성 검사
const memberTel = document.getElementById("memberTel");
const telMsg = document.getElementById("telMsg");

memberTel.addEventListener("input", function(){

    // 입력이 되지 않은 경우
    if(memberTel.value.length == 0){
        telMsg.innerText = "휴대폰 번호를 입력해주세요.(- 제외)";
        telMsg.classList.remove("confirm");
        telMsg.classList.add("error");

        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록

        return;
    }

    // 전화번호 정규식
    const regExp = /^01[01679]\d{3,4}\d{4}$/;

    if(regExp.test(memberTel.value)){ // 유효한 경우
        telMsg.innerText = "유효한 전화번호 형식입니다.";
        telMsg.classList.add("confirm");
        telMsg.classList.remove("error");

        checkObj.memberTel = true; // 유효한 상태임을 기록
        
    } else{ // 유효하지 않은 경우
        telMsg.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMsg.classList.add("error");
        telMsg.classList.remove("confirm");

        checkObj.memberTel = false; // 유효하지 않은 상태임을 기록
    }
});



// 다음 주소 api
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;

        }
    }).open();
}


// 회원 프로필 이미지 (미리보기)
const profileImage = document.getElementById("profileImage");

if(profileImage != null){ // inputImage 요소가 화면에 존재 할 때
 
    profileImage.addEventListener("change", function(){

        if(this.files[0] != undefined){ // 파일이 선택되었을 때

            const reader = new FileReader();
            reader.readAsDataURL(this.files[0]);

            reader.onload = function(e){
                const profileImg = document.getElementById("profileImg");
                profileImg.setAttribute("src", e.target.result);
            }

        }else{ 
            // 파일이 선택되지 않은 경우 (업로드 중 취소버튼 클릭시) 기본 프로필로!
            profileImg.setAttribute("src", memberProfile);
        }
    });
}

// 프로필 이미지 옆 삭제버튼 클릭 시
if(document.getElementById("del")!=null){
	
	del.addEventListener("click", function(){

        // 삭제버튼 클릭시 기존 프로필 사진으로!
        profileImg.setAttribute("src", memberProfile );
	
	});
	
}

// 회원가입 버튼 클릭시 유효성 검사여부 확인
function signUpValidate(){

    let str;

    for( let key  in checkObj ){ // 객체용 향상된 for문

        // 현재 접근 중인 key의 value가 false인 경우
        if( !checkObj[key] ){ 

            switch(key){
            case "memberEmail"    : str="이메일 입력란을"; break;
            case "sendEmail"      : str="이메일 인증을"; break;
            case "memberPw"       : str="비밀번호 입력란을"; break;    
            case "memberPwConfirm": str="비밀번호 확인 입력란을"; break;
            case "memberName"     : str="이름 입력란을"; break;
            case "memberTel"      : str="전화번호 입력란을"; break;
            // case "number"         : str="휴대폰 인증을"; break;
            }

            str += " 확인해주세요.";

            Swal.fire({
                title: str,
                width: 350,
                padding: '3em',
                color: 'black',
                confirmButtonColor: 'rgb(251, 131, 107)',
                confirmButtonText: '확인'
                });

            return false; // form태그 기본 이벤트 제거
        }
    }

    return true; // form태그 기본 이벤트 수행

}


// 문자 인증
const confirmBtn = document.getElementById("confirmBtn");

// 타이머에 사용될 변수
const cMessage = document.getElementById("cMessage");
let checkInterval; // setInterval을 저장할 변수
let min = 4;
let sec = 59;

confirmBtn.addEventListener("click", function(){

    const number = document.getElementById("number");

    if(checkInterval!=null){
        clearInterval(checkInterval);
    }

    number.readOnly = false;
    number.value="";
    cMessage.innerText = "";
    checkObj.timer = false;

    if(!checkObj.memberTel){
        Swal.fire({
            title: '휴대폰 번호를 확인해주세요.',
            width: 350,
            padding: '3em',
            color: 'black',
            confirmButtonColor: 'rgb(251, 131, 107)',
            confirmButtonText: '확인'
            });
    }else{
        Swal.fire({
            title: '인증번호 전송 완료! 30초~ 1분정도 소요됩니다 :^)',
            width: 350,
            padding: '3em',
            color: 'black',
            confirmButtonColor: 'rgb(251, 131, 107)',
            confirmButtonText: '확인'
            });

        $.ajax({
            url : contextPath + "/member/sendSMS",
            data : { "memberTel" : memberTel.value },
            type : "GET", // 데이터 전달 방식 type
            success : function( randomNumber ){

                console.log(randomNumber);
                
                // 성공시 인증번호 반환, 실패시 0 반환
                if(randomNumber==0){
                    console.log("문자 발송 실패");
                    telMsg.innerText = "휴대폰번호 재확인 후 다시 인증버튼을 눌러주세요. ";
                    memberTel.focus();
                    checkObj.number = false; // 유효하지 않은 상태임을 기록
                }else{
                    
                    console.log("문자 발송 성공 : " + randomNumber);
                    checkObj.timer = true;

                    // 5분 타이머
                    // setInerval(함수, 지연시간) : 지연시간이 지난 후 함수를 수행 (반복)
                    cMessage.innerText = "5:00"; // 초기값 5분
                    min = 4;
                    sec = 59; // 분, 초 초기화

                    cMessage.classList.remove("confirm");
                    cMessage.classList.remove("error");

                    // 변수에 저장해야지 멈출 수 있음
                    checkInterval = setInterval(function(){
                        if(sec < 10) sec = "0" + sec;
                            cMessage.innerText = min + ":" + sec;

                        if(Number(sec) === 0){
                        min--;
                        sec = 59;
                        }else{
                            sec--;
                        }

                        if(min === -1){ // 만료
                        cMessage.innerText = "";
                        telMsg.classList.add("error");
                        telMsg.classList.remove("confirm");
                        telMsg.innerText = "인증시간 만료";
                        checkObj.timer = false;
                        clearInterval(checkInterval); // checkInterval 반복을 지움

                        }else{

                            number.addEventListener("input",function(){

                                if(number.value.trim().length == 0){

                                    telMsg.innerText = "인증번호를 입력해주세요.";
                                    telMsg.classList.add("error");
                                    telMsg.classList.remove("confirm");
                                    checkObj.number = false; // 유효하지 않은 상태임을 기록

                                }

                                if(number.value.trim().length != 0){

                                    if((number.value == randomNumber) && checkObj.timer){

                                        telMsg.innerText = "휴대폰 인증 완료";
                                        clearInterval(checkInterval);
                                        telMsg.classList.remove("error");
                                        telMsg.classList.add("confirm");
                                        checkObj.number = true; // 유효 O 기록
                                        number.readOnly = true;
                                    }
                                    
                                    if( !checkObj.timer ){
                                        telMsg.innerText = "인증시간 만료";
                                        telMsg.classList.add("error");
                                        telMsg.classList.remove("confirm");
                                        checkObj.number = false; // 유효하지 않은 상태임을 기록
                                    }
                                    
                                    if( number.value != randomNumber ){
                                    
                                        telMsg.innerText = "인증번호 불일치";
                                        telMsg.classList.add("error");
                                        telMsg.classList.remove("confirm");
                                        checkObj.number = false; // 유효하지 않은 상태임을 기록

                                    }

                                }

                            });

                        }

                    }, 1000); // 1초 지연 후 수행

                }

            },
            error : function(){
                // 비동기 통신(ajax) 중 오류가 발생한 경우
                console.log("에러 발생");
            }

        });

    }

});

////////////////////////////////////////////////////////////////////////////////////////////////////


// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMsg = document.getElementById("emailMsg");

memberEmail.addEventListener("input", function(){
    if(memberEmail.value.length == 0){
        emailMsg.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        emailMsg.classList.remove("confirm", "error");

        checkObj.memberEmail = false;
        return;
    }

    const regExp = /^[\w\-\_]{5,}@[\w\-\_]+(\.\w+){1,3}$/;

    if( regExp.test(memberEmail.value) ){ 
        
        $.ajax({
            url : "email-reduplicate-check",   
            data : {"memberEmail" : memberEmail.value},
            type : "GET", 
            success : function(result){

                if(result == 1){ // 중복 O
                    emailMsg.innerText = "이미 사용중인 이메일 입니다.";
                    emailMsg.classList.add("error");
                    emailMsg.classList.remove("confirm");
                    checkObj.memberEmail = false; 

                } else { // 중복 X
                    emailMsg.innerText = "사용 가능한 이메일 형식 입니다.";
                    emailMsg.classList.add("confirm");
                    emailMsg.classList.remove("error");
                    checkObj.memberEmail = true; 
                }
            },
            error : function(){
                console.log("에러 발생");
            }
        });
        
    }else{
        emailMsg.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMsg.classList.add("error");
        emailMsg.classList.remove("confirm");

        checkObj.memberEmail = false; // 유효 X 기록
    }
});



// 이메일 인증번호 발송
const sendBtn = document.getElementById("sendBtn");
const cMessage2 = document.getElementById("cMessage2");
let reValue = document.getElementById("re");

// 타이머 변수
let checkInterval2; // setInterval을 저장할 변수
let min2 = 4;
let sec2 = 59;

function intervalTime(){
    if(sec2 < 10) sec2 = "0" + sec2;
    cMessage2.innerText = min2 + ":" + sec2;

    if(Number(sec) === 0){
        min2--;
        sec2 = 59;
    }else{
        sec2--;
    }

    if(min2 === -1){ // 만료
        cMessage2.classList.add("error");
        cMessage2.innerText = "인증번호가 만료되었습니다.";

        clearInterval(checkInterval2); 
    }
};

sendBtn.addEventListener("click", function(){
        
        $.ajax({
            url : contextPath + "/member/sendEmail",
            data : {"inputEmail" : memberEmail.value,
                    "flag": reValue.value},
            type : "GET",
            success : function(result){
                if(reValue.value == 0){
                    reValue.value = 1;
                    sendBtn.innerText = "재발송"
                    console.log("이메일 발송 성공");
                    checkObj.sendEmail = true;
    
                    cMessage.innerText = "5:00"; 
                    min = 4;
                    sec = 59;
                    cMessage.classList.remove("confirm", "error");
                    checkInterval = setInterval(intervalTime, 1000);
                }

                if(reValue.value == 1){
                    clearInterval(checkInterval);
                    cMessage.innerText = "5:00"; 
                    min = 4;
                    sec = 59;
                    checkInterval = setInterval(intervalTime, 1000);
                }
            }
        });
       
        alert("인증번호가 발송되었습니다. 이메일을 확인 후 인증번호를 입력해주세요.");
});



// 인증번호 확인 클릭시
const cNumber = document.getElementById("cNumber");
const cBtn = document.getElementById("cBtn");

cBtn.addEventListener("click", function(){

    if(checkObj.sendEmail){

        alert("cBtn");

        if( cNumber.value.length == 6 ){
            $.ajax({
                url : "checkNumber",
                data : { "cNumber" : cNumber.value,
                         "memberEmail" : memberEmail.value },
                type : "GET",
                success : function(result){
                    console.log(result);  
                    // 1 : 인증번호 일치 O, 시간 만족O
                    // 2 : 인증번호 일치 O, 시간 만족X
                    // 3 : 인증번호 일치 X

                    if(result == 1){

                        clearInterval(checkInterval); // 타이머 멈춤     

                        cMessage2.innerText = "인증되었습니다.";
                        cMessage2.classList.add("confirm");
                        cMessage2.classList.remove("error");

                    } else if(result == 2){
                        alert("만료된 인증 번호 입니다.");

                    } else{ // 3
                        alert("인증 번호가 일치하기 않습니다.");
                    }
                },
                error : function(){
                    console.log("이메일 인증 실패")
                }
            });

        } else { // 6자리 아님
            alert("인증번호를 정확하게 입력해주세요.");
            cNumber.focus();
        }

    } else { 
        alert("인증번호 받기 버튼을 먼저 클릭해주세요.");
    }
});