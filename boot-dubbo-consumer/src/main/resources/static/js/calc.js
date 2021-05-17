function calcMoney() {
    var money = $("#money").val();
    var year = $("#year").val();
    var profit = $("#profit").val();
    $.ajax({
        url: '/calc/calcResult',
        data:{
          money: money,
          year: year,
          profit: profit
        },
        type: 'post',
        success:function(data){
            $("#planMoney").val(data.planMoney);
            $("#allMoney").val(data.allMoney);
        }
    })
}