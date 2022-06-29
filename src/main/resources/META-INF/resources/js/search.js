function storeSearch(){
    var storeName = document.getElementById("store-name-select").value;
    get("http://127.0.0.1:8080/store?storeName="+storeName)
}
function storeJump(){
    get("http://127.0.0.1:8080/store")
}

function strategySearch(){
    var name = document.getElementById("strategy-name-select").value;
    get("http://127.0.0.1:8080/strategy?name="+name)
}

function strategyJump(){
    get("http://127.0.0.1:8080/strategy")
}

function deliverySearch(){
    var deliveryId = document.getElementById("delivery-id-select").value;
    var orderId = document.getElementById("order-id-select").value;
    get("http://127.0.0.1:8080/delivery?id=" + id + "&orderId=" + orderId);
}
function deliveryJump(){
    get("http://127.0.0.1:8080/delivery");
}


function get(url){
 var httpRequest = new XMLHttpRequest();
    httpRequest.open('GET', url, true);
    httpRequest.send();

    httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200 && httpRequest.readyState == 4){
            var contentDiv = document.getElementById("content");
            contentDiv.innerHTML = httpRequest.responseText;
        }
    }
}