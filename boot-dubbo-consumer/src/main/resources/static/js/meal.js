function addMeal(){
    var fundConsist = $("#code").val();
    var industry = $("#industry").val();
    var riskRank = $("#risk").val();
    $.ajax({
        url: "/meal/addMeal",
        data:{
            fundConsist: fundConsist,
            industry: industry,
            riskRank: riskRank
        },
        type: 'post',
        success:function(data){
            console.log('添加成功')
        }
    })
}