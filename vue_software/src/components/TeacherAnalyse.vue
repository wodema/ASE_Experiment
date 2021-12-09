<template>
  <el-container>
    <el-header>
      <el-row type="flex">
        <el-select
          v-model="selection"
          placeholder="选择类型"
          @change="handleKindChange"
        >
          <el-option
            v-for="item in selectionKindList"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
        <el-select
          v-if="selection == '题目分析'"
          v-model="queryInfo.kind"
          placeholder="选择类型"
          @change="handleKindChange"
        >
          <el-option label="请选择科目" value=""></el-option>
          <el-option
            v-for="item in questionKindList"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
        <el-select
          v-if="selection == '考试分析'"
          v-model="queryInfo.kind"
          placeholder="选择类型"
          @change="handleKindChange"
        >
          <el-option label="请选择科目" value=""></el-option>
          <el-option
            v-for="item in paperKindList"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
        <el-col :span="6">
          <!-- 搜索区域 -->
          <el-input
            placeholder="请输入搜索的信息"
            v-model="queryInfo.information"
            clearable
            @clear="getList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getList"
            >搜索</el-button>
          </el-input>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <!-- 学生表格 -->
      <el-table
        v-if="this.selection == '学生分析'"
        ref="studentTable"
        :data="studentList"
        border
        stripe
      >
        <el-table-column
          type="index"
          :index="indexMethod"
          label="序号"
          width="60px"
        ></el-table-column>
        <el-table-column label="姓名" prop="name"></el-table-column>
        <el-table-column label="年龄" prop="age"></el-table-column>
        <el-table-column label="查看分析">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-view"
              size="mini"
              @click="showStudentAnalyseDialog(scope.row.id)"
            >查看信息</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 问题表格 -->
      <el-table
        v-if="this.selection == '题目分析'"
        ref="questionTable"
        :data="questionList"
        border
        stripe
      >
        <el-table-column
          type="index"
          :index="indexMethod"
          label="序号"
          width="50px"
        ></el-table-column>
        <el-table-column label="科目" prop="kind" width="130px"></el-table-column>
        <el-table-column label="题目信息" prop="question"></el-table-column>
        <el-table-column label="选项1" prop="option1"></el-table-column>
        <el-table-column label="选项1" prop="option2"></el-table-column>
        <el-table-column label="选项1" prop="option3"></el-table-column>
        <el-table-column label="选项1" prop="option4"></el-table-column>
        <el-table-column label="答案" prop="answer" width="50px"></el-table-column>
        <el-table-column label="查看分析" width="120px">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-view"
              size="mini"
              @click="showQuestionAnalyseDialog(scope.row.id)"
            >查看信息</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 考试表格 -->
      <el-table
        v-if="this.selection == '考试分析'"
        ref="examTable"
        :data="paperList"
        border
        stripe
      >
        <el-table-column
          type="index"
          :index="indexMethod"
          label="序号"
          width="60px"
        ></el-table-column>
        <el-table-column label="考试科目" prop="paper_name"></el-table-column>
        <el-table-column label="考试时间" prop="paper_date" :formatter="dateFormat"></el-table-column>
        <el-table-column label="考试限时(秒)" prop="total_time"></el-table-column>
        <el-table-column label="考试总分" prop="total_score"></el-table-column>
        <el-table-column label="查看分析">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-view"
              size="mini"
              @click="showPaperAnalyseDialog(scope.row.paper_id)"
            >查看信息</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>

    <!-- dialog区，用于显示图表 -->
    <el-dialog
      class="studentAnalyse"
      title="学生分析信息"
      center
      :visible.sync="studentAnalyseDialogVisible"
      @close="studentDialogClosed"
    >
      <div class="studentBox">
        <div class="studentPieBox" ref="studentPieChart">
        </div>
        <div class="studentLineBox" ref="studentLineChart">
        </div>
      </div>
    </el-dialog>

    <el-dialog
      class="questionAnalyse"
      title="问题分析信息"
      center
      :visible.sync="questionAnalyseDialogVisible"
      @close="questionDialogClosed"
    >
      <div class="questionBox" ref="questionBarChart"></div>
    </el-dialog>

    <el-dialog
      class="paperAnalyse"
      title="考试分析信息"
      center
      :visible.sync="paperAnalyseDialogVisible"
      @close="paperDialogClosed"
    >
    <div class="paperBox">
      <div class="paperScoreBox" ref="paperPieChart"></div>
      <div class="paperStudentBox" ref="paperStudentTable">
        <el-table 
        :data="studentList"
        border
        stripe>
         <el-table-column
          type="index"
          :index="indexMethod"
          label="序号"
          width="60px"
        ></el-table-column>
        <el-table-column label="学生名" prop="name"></el-table-column>
        <el-table-column label="成绩" prop="score"></el-table-column>
        </el-table>
      </div>
    </div>
    </el-dialog>

    <el-footer>
      <!-- 分页 size-change 每页最大变化数 current-change 当前最大变化数 layout功能组件-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.page"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-footer>
  </el-container>
</template>
<script>
export default {

  data() {
    return {
      queryInfo: {
        //查询信息实体
        //id: window.sessionStorage.getItem("user").id,
        id: 3,
        information: "",
        kind: "",
        page: 1,
        size: 5,
      },
      //分析类别选择
      selection: "",
      //总共的数据量
      total: 0,
      selectionKindList: ["学生分析", "题目分析", "考试分析"],
      //存放三种不同数据的数组
      studentList: [],
      questionList: [],
      //问题类型
      questionKindList: [],
      paperList: [],
      //考试类型
      paperKindList: [],
      studentAnalyseDialogVisible: false,
      questionAnalyseDialogVisible: false,
      paperAnalyseDialogVisible: false,
    };
  },
  created() {
    this.selection = this.selectionKindList[0];
    this.getList();
  },
  methods: {
    async getList() {
      if (this.selection == "学生分析") {
        const { data: res } = await this.$http.post(
          "/students/getStudents",
          this.queryInfo
        );
        this.studentList = res.studentList;
        this.total = res.number;
      } else if (this.selection == "题目分析") {
        const { data: res } = await this.$http.post(
          "/questions/getQuestions",
          this.queryInfo
        );
        this.questionList = res.questionList;
        this.total = res.number;
        this.getQuestionKindList();
      } else {
        const { data: res } = await this.$http.post(
          "/paperList/getPaperOfAnalyse",
          this.queryInfo
        );
        this.paperList = res.paperList;
        this.total = res.number;
        this.getPaperKindList();
        this.studentList = [];
      }
    },
    async getQuestionKindList() {
      await this.$http
        .get("/questions/getQuestionKinds")
        .then((res) => {
          console.log(res);
          this.questionKindList = res.data.kinds;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async getPaperKindList(){
      await this.$http
        .get("/paperList/getPaperKinds")
        .then((res) => {
          console.log(res);
          this.paperKindList = res.data.kinds;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    indexMethod(index) {
      return (this.queryInfo.page - 1) * this.queryInfo.size + 1 + index;
    },
    handleKindChange() {
      this.queryInfo.page = 1;
      this.getList();
    },
    handleSizeChange(newSize) {
      this.queryInfo.size = newSize;
      this.getList();
    },
    //page的触发功能
    handleCurrentChange(newPage) {
      this.queryInfo.page = newPage;
      this.getList();
    },
    showStudentAnalyseDialog(id) {
      this.getStudentPieChartInfo(id);
      this.getStudentLineChartInfo(id);
      this.studentAnalyseDialogVisible = true;
    },
    showQuestionAnalyseDialog(id){
        this.getQuestionBarChartInfo(id);
        this.questionAnalyseDialogVisible = true;
    },
    showPaperAnalyseDialog(id){
      this.getPaperPieChartInfo(id);
      this.paperAnalyseDialogVisible = true;
    },
    //销毁图表
    studentDialogClosed(){
      this.$echarts.dispose(this.$refs.studentPieChart);
      this.$echarts.dispose(this.$refs.studentLineChart);
    },
    questionDialogClosed(){
      this.$echarts.dispose(this.$refs.questionBarChart);
    },
    paperDialogClosed(){
      this.$echarts.dispose(this.$refs.paperPieChart);
      this.studentList = [];
    },
    async getStudentPieChartInfo(id) {
      await this.$http
        .get("/answeredQuestions/getStudentQuestionInfo/" + id)
        .then((result) => {
          let analyseInfo = result.data.analyseInfo;
          if (analyseInfo.length > 0) {
            var studentPieChart = this.$echarts.init(this.$refs.studentPieChart);
            var option = {
              title: {
                text: "错题分析图表",
                subtext: "百分比显示",
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
              option.series[0].data.push(data);
              option.legend.data.push(item.kind);
            });
            studentPieChart.setOption(option);
          } else {
            return this.$message.info("该学生暂无答题信息");
          }
        });
    },
    async getStudentLineChartInfo(id) {
      await this.$http.get("/score/getStudentScoreInfo/" + id).then((result) => {
        let scoreInfo = result.data.ScoreInfo;
        if (scoreInfo.length > 0) {
          var studentLineChart = this.$echarts.init(this.$refs.studentLineChart);
          var option = {
            title: {
              text: "成绩分析图表",
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
          scoreInfo.forEach((item) => {
            let data = { value: item.score, name: item.paper_name };
            option.series[0].data.push(data);
            option.xAxis.data.push(item.paper_name);
          });
          studentLineChart.setOption(option);
        } else {
          return this.$message.info("该学生暂无考试成绩信息");
        }
      });
    },
    async getQuestionBarChartInfo(id){
        await this.$http.get("/answeredQuestions/getQuestionAnalyseInfo/" + id).then((result) => {
        let questionInfo = result.data.questionInfo;
        if (questionInfo.length > 0) {
          var questionBarChart = this.$echarts.init(this.$refs.questionBarChart);
          var option = {
            title: {
                text: "问题选项分布图表",
                subtext: "百分比显示",
                x: "center"
              },
              grid: {
                    y: 101,    //上下距离
                    x2: 10,
                    height: "70%"
                },
              tooltip: {
                trigger: "item",
                formatter: "{a}：{b} <br/> {c}道",
              },
              xAxis: {
                data: ['A','B','C','D']
              },
              yAxis: {
                show:false,
                min:0, //y轴的最小值
                interval:1,
              },
              legend: {
                orient: "vertical",
                right: "right",
                data: ['A','B','C','D'],
              },
              series: [
                {
                  name: "选择类型",
                  type: "bar",
                  data: [
                    {value:0, name:'A'},
                    {value:0, name:'B'},
                    {value:0, name:'C'},
                    {value:0, name:'D'},
                  ],
                  itemStyle: {
                    normal: {
                      label: {
                        show: true,
                        position: 'top',
                        formatter: '{c}道'
                      },
                        color: function(params) {
                            //让不同选项显示不同的颜色
                            var colorList = [
                              '#C1232B','#B5C334','#FCCE10','#E87C25',
                            ];
                            return colorList[params.dataIndex]
                        },
                    }
                 },
                },
              ],
          };
          questionInfo.forEach((item) => {
              switch(item.studentAnswer){
                case 'A': 
                  option.series[0].data[0].value = item.number;
                  break;
                case 'B':
                  option.series[0].data[1].value = item.number;
                  break;
                case 'C':
                  option.series[0].data[2].value = item.number;
                  break;
                case 'D':
                  option.series[0].data[3].value = item.number;
                  break;
                default:
                  break;
              }
          });
          questionBarChart.setOption(option);
        } else {
          return this.$message.info("该问题还没有学生作答信息");
        }
      });
    },
    async getPaperPieChartInfo(id){
      await this.$http
        .get("/score/getPaperAnswerInfo/" + id)
        .then((result) => {
          let analyseInfo = result.data.paperInfo;
          if (analyseInfo.length > 0) {
            var paperPieChart = this.$echarts.init(this.$refs.paperPieChart);
            var option = {
              title: {
                text: "试卷分析图表",
                subtext: "百分比显示",
              },
              tooltip: {
                trigger: "item",
                formatter: "{a}：{b} <br/> {c}个 ({d}%) <br/> 点击查看学生信息",
              },
              legend: {
                bottom: 40,
                left: 'center',
                data: ['满分人数','90-99分','80-89分','70-79分','60-69分','60分以下'],
              },
              series: [
                {
                  name: "成绩分布",
                  type: "pie",
                  radius: "50%",
                  center: ["50%", "35%"],
                  data: [
                    {value:0, name:'满分人数'},
                    {value:0, name:'90-99分'},
                    {value:0, name:'80-89分'},
                    {value:0, name:'70-79分'},
                    {value:0, name:'60-69分'},
                    {value:0, name:'60分以下'}
                  ],
                  avoidLabelOverlap: true,
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
              switch(item.score/10){
                case 10:
                  option.series[0].data[0].value++;
                  break;
                case 9:
                  option.series[0].data[1].value++;
                  break;
                case 8:
                  option.series[0].data[2].value++;
                  break;
                case 7:
                  option.series[0].data[3].value++;
                  break;
                case 6:
                  option.series[0].data[4].value++;
                  break;
                default:
                  option.series[0].data[5].value++;
                  break;
              }
            });
            paperPieChart.setOption(option);
            this.getPaperStudentInfo(null, id);
            paperPieChart.on('click',(params)=>{
              this.getPaperStudentInfo(params, id);
            });
          } else {
            return this.$message.info("该试卷暂无答题信息");
          }
      });
    },
    async getPaperStudentInfo(pieInfo, paper_id){
      var score = 0;
      if(pieInfo != null){
        switch(pieInfo.data.name){
          case '满分人数':
            score = 100;
            break;
          case '90-99分':
            score = 90;
            break;
          case '80-89分':
            score = 80;
            break;
          case '70-79分':
            score = 70;
            break;
          case '60-69分':
            score = 60;
            break;
          default:
            score = 50;
            break;
        }
      }
      await this.$http.get("/score/getPaperStudentInfo/", {params:{id: paper_id, score: score}})
            .then((res) => {
              this.studentList = res.data.paperStudentInfo;
            })
            .catch((err) => {
              console.log(err);
            });
    },
    dateFormat(row, column, cellValue, index) {
            if (cellValue == null || cellValue == "") return "";
            let date = new Date(parseInt(cellValue));//时间戳为10位需*1000，如果为13位的话不需乘1000。
            let Y = date.getFullYear() + '-';
            let M = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) + '-' : date.getMonth() + 1 + '-';
            let D = date.getDate() < 10 ? '0' + date.getDate() + ' ' : date.getDate() + ' ';
            let h = date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':';
            let m = date.getMinutes() < 10 ? '0' + date.getMinutes() + ':' : date.getMinutes() + ':';
            let s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
            return Y + M + D + h + m + s;
        }
  },
};
</script>
<style lang="less" scoped>
.studentAnalyse {
  .studentBox {
    display: flex;
    .studentPieBox {
      width: 500px;
      height: 400px;
      margin-left: 30px;
      margin-top: 5%;
    }
    .studentLineBox {
      width: 500px;
      height: 400px;
      margin-left: 100px;
    }
  }
}
.questionAnalyse{
  width: 75%;
  margin: 0 auto;
  .questionBox{
      width: 400px;
      height: 450px;
      margin: 0 auto;
  }
}
.paperAnalyse{
  .paperBox{
    //width: 90% !important;
    //height: 90% !important;
    display: flex;
    .paperScoreBox{
      width: 60vw;
      height: 400px;
    }
    .paperStudentBox {
      overflow:auto;
      width: 500px;
      height: 400px;
    }
  }
}
</style>