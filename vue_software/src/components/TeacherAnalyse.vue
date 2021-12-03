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
          v-if="selection == '题目分析' || selection == '考试分析'"
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
            ></el-button>
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
            ></el-button>
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
          width="60px"
        ></el-table-column>
        <el-table-column label="科目" prop="kind" width="130px"></el-table-column>
        <el-table-column label="题目信息" prop="question"></el-table-column>
        <el-table-column label="选项1" prop="option1"></el-table-column>
        <el-table-column label="选项1" prop="option2"></el-table-column>
        <el-table-column label="选项1" prop="option3"></el-table-column>
        <el-table-column label="选项1" prop="option4"></el-table-column>
        <el-table-column label="答案" prop="answer"></el-table-column>
        <el-table-column label="查看分析">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-view"
              size="mini"
              @click="showQuestionAnalyseDialog(scope.row.id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 考试表格 -->
      <el-table
        v-if="this.selection == '考试分析'"
        ref="examTable"
        :data="examList"
        border
        stripe
      >
        <el-table-column
          type="index"
          :index="indexMethod"
          label="序号"
          width="60px"
        ></el-table-column>
        <el-table-column label="考试科目" prop="name"></el-table-column>
        <el-table-column label="考试总分" prop="age"></el-table-column>
        <el-table-column label="查看分析">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-view"
              size="mini"
              @click="showExamAnalyseDialog(scope.row.id)"
            ></el-button>
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
      width="75%"
      heigth="100%"
    >
      <div class="studentBox">
        <div class="box1" ref="studnetPieChart">
        </div>
        <div class="box2" ref="studnetLineChart">
        </div>
      </div>
    </el-dialog>

    <el-dialog
      class="QuestionAnalyse"
      title="问题分析信息"
      center
      :visible.sync="questionAnalyseDialogVisible"
      width="75%"
      heigth="100%"
    >
      <div class="questionBox" ref="questionPieChart"></div>
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
      examList: [],
      //考试类型
      examKindList: [],
      studentAnalyseDialogVisible: false,
      questionAnalyseDialogVisible: false,
      examAnalyseDialogVisible: false,
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
          "/exams/getExams",
          this.queryInfo
        );
        this.examList = res.examList;
        this.total = res.number;
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
        this.getQuestionPieChartInfo(id);
        this.questionAnalyseDialogVisible = true;
    },
    async getStudentPieChartInfo(id) {
      this.$http
        .get("/answeredQuesions/getStudentQuestionInfo/" + id)
        .then((result) => {
          let analyseInfo = result.data.analyseInfo;
          if (analyseInfo.length > 0) {
            var studentPieChart = this.$echarts.init(this.$refs.studnetPieChart);
            var option1 = {
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
              option1.series[0].data.push(data);
              option1.legend.data.push(item.kind);
            });
            studentPieChart.setOption(option1);
          } else {
            return this.$message.info("该学生暂无答题信息");
          }
        });
    },
    async getStudentLineChartInfo(id) {
      this.$http.get("/score/getStudentScoreInfo/" + id).then((result) => {
        let ScoreInfo = result.data.ScoreInfo;
        if (ScoreInfo.length > 0) {
          var studentLineChart = this.$echarts.init(this.$refs.studnetLineChart);
          var option2 = {
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
          ScoreInfo.forEach((item) => {
            let data = { value: item.score, name: item.kind };
            option2.series[0].data.push(data);
            option2.xAxis.data.push(item.kind);
          });
          studentLineChart.setOption(option2);
        } else {
          return this.$message.info("该学生暂无考试成绩信息");
        }
      });
    },
    async getQuestionPieChartInfo(){
        
    }
  },
};
</script>
<style lang="less" scoped>
.studentAnalyse {
  .studentBox {
    display: flex;
    .box1 {
      width: 500px;
      height: 400px;
      margin-left: 30px;
      margin-top: 5%;
    }
    .box2 {
      width: 500px;
      height: 400px;
      margin-left: 100px;
    }
  }
}
</style>