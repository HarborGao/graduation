layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    var tableIns = table.render({
        elem: '#newsList',
        url : '/fund/searchCollect',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 5,
        limits : [5,10,15,20],
        id : "newsListTable",
        cols : [[
            {field: 'fundCode', title: '基金代码', width:130, align:"center"},
            {field: 'fundName', title: '基金名称', width:300},
            {field: 'curNetWorth', title: '基金净值', align:'center'},
            {field: 'todayProfit', title: '当日收益',  align:'center'},
            {field: 'weekProfit', title: '近一周收益', align:'center'},
            {field: 'monthProfit', title: '近一月收益', align:'center'},
            {field: 'sixMonthProfit', title: '近半年收益', align:'center'},
            {field: 'yearProfit', title: '近一年收益', align:'center'},
            {title: '操作', width:100, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });


    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'delCol'){ //取消收藏
            var fundCode = data.fundCode;
            $.ajax({
                url: '/fund/deleteCollect',
                data: {
                    fundCode: fundCode
                },
                type: 'post',
                success:function(data){
                    if(data == '1'){
                        alert("取消成功");
                        tableIns.reload();
                    }else{
                        alert("取消失败");
                    }
                }
            });
        }
    });

})