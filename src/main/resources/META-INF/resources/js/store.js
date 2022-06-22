function storeSearch(){
    var storeName = document.getElementById("store-name-select").value;
    var url = "http://127.0.0.1:8080/store?storeName="+storeName;
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