function storeSearch(){
    var name = document.getElementById("store-name-select").value;
    get("/store?name="+name);
}
function storeJump(){
    get("/store")
}
function jumpStoreAddPage(){
    get("/store/addPage")
}


function strategySearch(){
    var name = document.getElementById("strategy-name-select").value;
    get("/strategy?name="+name)
}

function strategyJump(){
    get("/strategy")
}
function strategyAddJump(){
    get('/strategy/addPage');
}

function deliverySearch(){
    var storeId = document.getElementById("store-id-select").value;
    var deliveryId = document.getElementById("delivery-id-select").value;
    var orderId = document.getElementById("order-id-select").value;
    get("/delivery?id=" + id + "&orderId=" + orderId +"&storeId="+storeId);
}
function deliveryJump(){
    get("/delivery");
}

function deliveryDetail(obj){
    var id = obj.previousElementSibling.value;
    get("/delivery/get?id="+id);
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
                         window.location.href="login";
                     }
                 });
            }
        }
    }
}
function getBody(url){
  var httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", url, true);
    httpRequest.setRequestHeader('token',localStorage.getItem("token"))
    httpRequest.send();

    httpRequest.onreadystatechange = function(){
        if(httpRequest.readyState == 4){
            if(httpRequest.status == 200 && httpRequest.readyState == 4){
                var contentDiv = document.getElementById("content");
                return httpRequest.responseText;
            }
        }
    }
}


function storeAdd(){
    var body = {
        "storeCode" : document.getElementById("storeCode").value,
        "storeName" : document.getElementById("storeName").value,
        "phone" : document.getElementById("storePhone").value,
        "address" : document.getElementById("storeAddress").value,
        "longitude" : document.getElementById("longitude").value,
        "latitude": document.getElementById("latitude").value
    };
    postUrl("store/add", body,"store");
}


function postUrl(url,body,redirectUrl){
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", url, true);
    httpRequest.setRequestHeader("Content-Type","application/json");
    httpRequest.setRequestHeader("token", localStorage.getItem("token"));
    httpRequest.send(JSON.stringify(body));
    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
            window.href.location=redirectUrl;
        }
    }
}




function logOut(){
    var httpRequest = new XMLHttpRequest();
    httpRequest.open('GET', 'login/logout', true);
    httpRequest.setRequestHeader("token", localStorage.getItem("token"));
    httpRequest.send();

    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
              localStorage.removeItem("token");
              window.location.href="login";
        }
    }
}
