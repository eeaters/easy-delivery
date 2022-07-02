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
    get("/strategy/addPage")
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
    url = "http://121.4.50.38:9000" + url;
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", url, true);
    httpRequest.send();

    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
            var contentDiv = document.getElementById("content");
            contentDiv.innerHTML = httpRequest.responseText;
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
    postUrl("store/add", body);
}


function postUrl(url,body){
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", url, true);
    httpRequest.setRequestHeader("Content-Type","application/json");
    httpRequest.setRequestHeader("token", localStorage.getItem("token"));
    httpRequest.send(JSON.stringify(body));
    alert(JSON.stringify(body));
    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
            alert(httpRequest.responseText)
        }
    }
}