<template>
    <div class="part" >
    <div class="box1" ref="box1"></div>
    <div class="box2" ref="box2"></div>
    <div v-if="isNull">
      <span>该学生还未有错题记录。</span>
    </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            analyseInfo:[[]],
            isNull:false,
        }
    },
    created(){
    },
    mounted(){
        this.getAnalyseInf();
    },
    methods:{
        async getAnalyseInf(){
            //const id = winodw.session.getItem("user").id;
            const id = 3;
            this.$http.get("/getAnalyseInformation/"+id)
            .then((result) => {
                let analyseInfo = result.data.analyseInfo;
                console.log(analyseInfo);
                if(analyseInfo.length > 0){
                    var charts = this.$echarts.init(this.$refs.box1);
                    var option = {
                        title: {
                            text: '错题分析图表',
                            subtext: '百分比显示',
                            x:'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            //系列名称、数据项名称、数值、百分比
                            formatter: "{a}：{b} <br/> {c}道 ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            right: 'right',
                            data:[]
                        },
                        series: [{
                            name: '错题类型',
                            type: 'pie',
                            radius : '50%',
                            center: ['50%', '35%'],
                            data:[],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }]

                    };
                    analyseInfo.forEach(item =>{
                        let data = {value:item.number, name:item.kind};
                        option.series[0].data.push(data);
                        option.legend.data.push(item.kind);
                    })
                    charts.setOption(option);
                }
                else{
                    this.isNull = true;
                }
            })
        }
    }
}
</script>

<style lang="less" scoped>
.part{
    display: flex;
    .box1{
        width: 600px;
        height: 600px;
        position: absolute;
        margin-left: 40px;
        top: 20%;
    }
}

</style>
