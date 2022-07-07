function storeSearch(){
    var name = document.getElementById("store-name-select").value;
    get("/store?name="+name);
}
function storeJump(){
    get("/store")
}
function jumpStoreAddPage(obj){
    if(obj == null){
        get("/store/addPage")
    }else{
        var storeId = obj.previousElementSibling.value;
        get('/store/addPage?storeId='+storeId);
    }
}


function strategySearch(){
    var name = document.getElementById("strategy-name-select").value;
    get("/strategy?name="+name)
}

function strategyJump(){
    get("/strategy")
}

function strategySave(){
    let table = document.getElementById("channel-table");
    let tbody = table.getElementsByTagName('tbody')[0];
    var trs = tbody.getElementsByTagName('tr');
    var channelIds = [];
    for (var i = 0; i< trs.length; i++){
        let value = trs[i].querySelector('select[name="channelId"]').value;
        channelIds.push(value);
    }

    var body = {
        "id": document.getElementById("strategyId").value,
        "name": document.getElementById("strategyName").value,
        "desc": document.getElementById("strategyDesc").value,
        "type": document.querySelector('input[name="strategyType"]:checked').value,
        "timeoutPeriod": document.getElementById("strategy-timeout-period").value,
        "channels": channelIds
    }
    postUrl("strategy/createOrUpdate",body,"strategy");
}

function strategyAddJump(obj){
    if(obj == null){
       get('/strategy/addPage');
    }else{
    var strategyId = obj.previousElementSibling.value;
     get('/strategy/addPage?strategyId='+strategyId);
    }
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


function storeAdd(obj){
    var id = obj.previousElementSibling.value;
    var body = {
            "storeCode" : document.getElementById("storeCode").value,
            "storeName" : document.getElementById("storeName").value,
            "phone" : document.getElementById("storePhone").value,
            "address" : document.getElementById("storeAddress").value,
            "longitude" : document.getElementById("longitude").value,
            "latitude": document.getElementById("latitude").value
        };
    if(id != null){
        body.id = id;
    }
    var selectEle = document.getElementById("strategy-mapping");
    var index=selectEle.selectedIndex
    var strategyId = selectEle.options[index].value;
    postUrl("store/add?strategyId="+strategyId, body,"store");
}


function postUrl(url,body,redirectUrl){
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", url, true);
    httpRequest.setRequestHeader("Content-Type","application/json");
    httpRequest.setRequestHeader("token", localStorage.getItem("token"));
    httpRequest.send(JSON.stringify(body));
    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
            get(redirectUrl)
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
