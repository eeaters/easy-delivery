const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});

// 下面为自定义js代码

var loginBtn = document.getElementById("login");
loginBtn.onclick = function(){
    var phoneVal = document.getElementById("phone").value;
    var passwordVal = document.getElementById("password").value;
    if(phoneVal == null || phoneVal == '' ){
        alert("请输入手机号");
        return;
    }
    if(passwordVal == null || passwordVal == ''){
        alert("请输入密码");
        return;
    }

    var userInfo = {
        "phone" : phoneVal,
        "password" : passwordVal
    };

    var httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'http://127.0.0.1:9000/login/doLogin', true);
    httpRequest.setRequestHeader('Content-type','application/json;charset=UTF-8')
    httpRequest.send(JSON.stringify(userInfo));

    httpRequest.onreadystatechange = function(){
    if(httpRequest.status == 200 && httpRequest.readyState == 4){
            var res = httpRequest.responseText;
            var statusCode = JSON.parse(res).code;
            if(statusCode == '200'){
                localStorage.setItem("token",JSON.parse(res).result);
                window.location.href="home";
            }else {
                 alert(JSON.parse(res).message)
                 window.location.href="login";
            }
        }
    }
}