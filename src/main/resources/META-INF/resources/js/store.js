window.onload=function(){
    var queryButton = document.getElementById("store-query-button");

    queryButton.onclick = function(){
        var storeName = document.getElementById("store-name-select").value;
        var url = "http://127.0.0.1:8080/store?storeName="+storeName;
        alert(url);
        var httpRequest = new XMLHttpRequest();
        httpRequest.open('GET', url, true);
        httpRequest.send();

        httpRequest.onreadystatechange = function(){
        if(httpRequest.status == 200){
                var contentDiv = document.getElementById("content");
                contentDiv.innerHTML = httpRequest.responseText;
            }
        }
    }
}