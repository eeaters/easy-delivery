function popChannel(){
    // layer.open({
    //     type:2,
    //     title : '选择一个渠道',
    //     maxmin: true,
    //     offset: '150px',
    //     area : [ '600px', '500px' ],
    //     content: '/channel/account/list'
    // })

     let selectInfo = document.getElementById("select_hidden").innerHTML;
     var tbody = document.getElementById("channel-table").getElementsByTagName('tbody')[0];
     tbody.insertRow().innerHTML =
        " <tr>\n" +
        "   <td>" +
                selectInfo +
        "  </td>\n" +
        "   <td>" +
        "      <a href=\"javascript:void(0)\">删除</a>\n" +
        "  </td>\n" +
        " </tr>";
}