<template>
  <el-container>
    <el-header>
      <el-button @click="turnToWrongQuestion">查看错题集</el-button>
    </el-header>
    <el-main>
      <div class="part">
        <div class="box1" ref="box1">
          <div v-if="isQuestionNull">
            <span>该学生还未有错题记录。</span>
          </div>
        </div>
        <div class="box2" ref="box2">
          <div v-if="isScoreNull">
            <span>该学生还未有成绩记录。</span>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      isQuestionNull: false,
      isScoreNull: false,
      //const id = winodw.session.getItem("user").id;
      id: this.$store.getters.getUserId,
    };
  },
  created() {},
  mounted() {
    this.getAnalyseInfo();
    this.getScoreInfo();
  },
  methods: {
    async getAnalyseInfo() {
      this.$http
        .get("/answeredQuestions/getStudentQuestionInfo/" + this.id)
        .then((result) => {
          let analyseInfo = result.data.analyseInfo;
          if (analyseInfo.length > 0) {
            var charts = this.$echarts.init(this.$refs.box1);
            var option1 = {
              title: {
                text: "错题分析图表",
                subtext: "百分比显示",
                x: "center",
              },
              grid: {
                top: "20%",
              },
              tooltip: {
                trigger: "item",
                //系列名称、数据项名称、数值、百分比
                formatter: "{a}：{b} <br/> {c}道 ({d}%)",
              },
              legend: {
                orient: "vertical",
                right: "right",
                data: [],
              },
              series: [
                {
                  name: "错题类型",
                  type: "pie",
                  radius: "50%",
                  center: ["50%", "35%"],
                  data: [],
                  itemStyle: {
                    emphasis: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: "rgba(0, 0, 0, 0.5)",
                    },
                  },
                },
              ],
            };
            analyseInfo.forEach((item) => {
              let data = { value: item.number, name: item.kind };
              option1.series[0].data.push(data);
              option1.legend.data.push(item.kind);
            });
            charts.setOption(option1);
          } else {
            this.isNull = true;
            return this.$message.info("你暂无答题信息");
          }
        });
    },
    async getScoreInfo() {
      this.$http.get("/score/getStudentScoreInfo/" + this.id).then((result) => {
        let ScoreInfo = result.data.ScoreInfo;
        if (ScoreInfo.length > 0) {
          var ScoreCharts = this.$echarts.init(this.$refs.box2);
          var option2 = {
            title: {
              text: "成绩分析图表",
              x: "center",
            },
            xAxis: {
              type: "category",
              data: [],
            },
            yAxis: {
              type: "value",
            },
            series: [
              {
                type: "line",
                data: [],
                itemStyle: { normal: { label: { show: true } } },
              },
            ],
          };
          ScoreInfo.forEach((item) => {
            let data = { value: item.score, name: item.paper_name };
            option2.series[0].data.push(data);
            option2.xAxis.data.push(item.paper_name);
          });
          ScoreCharts.setOption(option2);
        } else {
          this.isNull = true;
          return this.$message.info("你暂无考试成绩信息");
        }
      });
    },
    turnToWrongQuestion(){
      this.$router.push({path:"/wrongQuestion", query:{id:this.$store.getters.getUserId}
      })
    }
  },
};
</script>

<style lang="less" scoped>
.part {
    display: flex;
    .box1 {
      width: 500px;
      height: 500px;
      margin-left: 40px;
      margin-top: 10%;
    }
    .box2 {
      width: 500px;
      height: 500px;
      margin-left: 100px;
      margin-top: 5%;
    }
}
</style>
