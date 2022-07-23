function channelList() {
    get('management/channel')
}


function channelUpdatePage(obj){
    var id = obj.previousElementSibling.value;
    get('management/channelUpdatePage?channelId=' + id);
}

function channelAddPage(){
    get('management/channelAddPage')
}

function channelUpdate(obj) {
    var data = {
        "id": obj.previousElementSibling.value,
        "appKey": document.getElementById("appKey").value,
        "appSecret": document.getElementById("appSecret").value,
        "appToken": document.getElementById("appToken").value
    }

    var httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", "management/channelAdd", true);
    httpRequest.setRequestHeader("Content-Type","application/json");
    httpRequest.setRequestHeader("token", localStorage.getItem("token"));
    httpRequest.send(JSON.stringify(data));
    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
            get('management/channel')
        }
    }
}

function channelAdd(obj) {
    var channel = document.getElementById("channel");
    var index = channel.selectedIndex
    var channelVal = channel.options[index].value;

    var data = {
        "id": obj.previousElementSibling.value,
        "channel": channelVal,
        "appKey": document.getElementById("appKey").value,
        "appSecret": document.getElementById("appSecret").value,
        "appToken": document.getElementById("appToken").value
    }

    var httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", "management/channelAdd", true);
    httpRequest.setRequestHeader("Content-Type","application/json");
    httpRequest.setRequestHeader("token", localStorage.getItem("token"));
    httpRequest.send(JSON.stringify(data));
    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
            var res = httpRequest.responseText;
            var statusCode = JSON.parse(res).code;
            if(statusCode == '200'){
                get('management/channel')
            }else {
                alert(JSON.parse(res).message)
            }
        }
    }
}






function get(url){
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", url, true);
    httpRequest.setRequestHeader('token',localStorage.getItem("token"))
    httpRequest.send();

    httpRequest.onreadystatechange = function(){
        if(httpRequest.readyState == 4){
            if(httpRequest.status == 200 && httpRequest.readyState == 4){
                var contentDiv = document.getElementById("content");
                contentDiv.innerHTML = httpRequest.responseText;
            }
            if(httpRequest.status == 405 ){
                layer.open({
                    title: ['登录已失效'],
                    btn: ['返回登录页面'],
                    shade: 0.5,
                    yes: function(index){
                        window.location.href="management/login";
                    }
                });
            }
        }
    }
}


function logOut() {
    var httpRequest = new XMLHttpRequest();
    httpRequest.open('GET', 'login/logout', true);
    httpRequest.setRequestHeader("token", localStorage.getItem("token"));
    httpRequest.send();

    httpRequest.onreadystatechange = function () {
        if (httpRequest.status == 200 && httpRequest.readyState == 4) {
            localStorage.removeItem("token");
            window.location.href = "management/login";
        }
    }
}