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
    var userNameVal = document.getElementById("userName").value;
    var passwordVal = document.getElementById("password").value;
    alert(userNameVal +" : " + passwordVal)
}