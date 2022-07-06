function popChannel(){
    layer.open({
        type:2,
        title : '选择一个渠道',
        maxmin: true,
        offset: '100px',
        area : [ '600px', '500px' ],
        content: '/channel/account/list'
    })
}