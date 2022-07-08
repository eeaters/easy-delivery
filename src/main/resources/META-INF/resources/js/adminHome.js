function channelList() {
    get('management/channel')
}




function channelAddPage(obj){
    if(obj ==null){
        get('management/channelAddPage')
    }else{
        var id = obj.previousElementSibling.value;
        get('management/channelAddPage?channelId=' + id);
    }
}

function channelAdd(obj) {
    var data = {
        "id": obj.previousElementSibling.value,
        "name": document.getElementById("accountName").value,
        "channel": document.getElementById("accountChannel").value,
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