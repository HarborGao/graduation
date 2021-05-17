layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '/user/searchUser',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [5,10,20,25],
        limit : 1,
        id : "userListTable",
        cols : [[
            {field: 'userPhone', title: '手机号', minWidth:100, align:"center"},
            {field: 'nickName', title: '昵称', minWidth:200, align:"center"},
            {field: 'userRole', title: '身份', align:'center'},
            {field: 'gender', title: '性别',  align:'center'},
            {field: 'birthday', title: '生日', align:'center'},
            {field: 'userEmail', title: '邮箱', align:'center',minWidth:150},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
            table.reload("userListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    userPhone: $("#userPhone").val(),
                    nickName: $("#nickName").val()
                }
            })
    });


    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
          data = obj.data;
        if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                var userPhone = data.userPhone;
                $.ajax({
                    url: '/user/deleteUser',
                    data:{
                        userPhone: userPhone
                    },
                    type: 'post',
                    dataType: 'json',
                    success:function(data){
                        if(data == '1'){
                            alert("删除成功");
                            tableIns.reload();
                            layer.close(index);
                        }else{
                            alert("删除失败");
                        }
                    }
                });

                // })
            });
        }
    });

})