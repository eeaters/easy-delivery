var adminLoginBtn = document.getElementById("adminLogin");
adminLoginBtn.onclick = function(){
    var userNameVal = document.getElementById("userName").value;
    var passwordVal = document.getElementById("password").value;

    var userInfo = {
        "userName" : userNameVal,
        "password" : passwordVal
    };

    var httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'management/doLogin', true);
    httpRequest.setRequestHeader('Content-type','application/json;charset=UTF-8')
    httpRequest.send(JSON.stringify(userInfo));

    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
            var res = httpRequest.responseText;
            var statusCode = JSON.parse(res).code;
            if(statusCode == '200'){
                localStorage.setItem("token",JSON.parse(res).result);
                window.location.href="management/home";
            }else {
                alert(JSON.parse(res).message)
                window.location.href="management/login";
            }
        }
    }
}







