function getContextPath() {
    const hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf("/", hostIndex + 1) );
};

if(document.getElementById("logout")!=null){
    const logout = document.getElementById("logout");

    logout.addEventListener("click", function(){
        Swal.fire({
            title: '로그아웃',
            text: "로그아웃 하시겠습니까?",
            width: 340,		
            icon: 'warning',
            iconColor: 'rgb(251, 131, 107)',
            showCancelButton: true,
            confirmButtonColor: 'rgb(251, 131, 107)',
            cancelButtonColor: '#999',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    location.href = getContextPath()+"/member/logout";
                }else{
                    console.log("로그아웃 취소됨");
                }
            })
    });
}