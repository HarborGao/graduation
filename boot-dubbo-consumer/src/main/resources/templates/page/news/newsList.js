layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    var tableIns = table.render({
        elem: '#newsList',
        url : '/fund/searchFund',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 20,
        limits : [10,15,20,25],
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


    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
            table.reload("newsListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    fundCode:$("#fundCode").val(),
                    fundName:$("#fundName").val(),
                    fundType:$("#fundType").val()
                }
            })
    });

    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'collect'){ //收藏
            var fundCode = data.fundCode;
            var fundName = data.fundName;
            var addNet = data.fundWorth;
            $.ajax({
                url: '/fund/collectFund',
                data: {
                    fundCode: fundCode,
                    fundName: fundName,
                    addNet: addNet
                },
                type: 'post',
                success:function(data){
                    if(data == '1'){
                        alert("收藏成功");
                    }
                }
            });
        }
    })
})