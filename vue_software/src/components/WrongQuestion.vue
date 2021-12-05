<template>
  <el-container>
    <el-header>
      <el-row type="flex">
        <el-select
          v-model="queryInfo.kind"
          placeholder="题目类型"
          @change="handleKindChange"
        >
          <el-option label="请选择题目的分类" value=""></el-option>
          <el-option
            v-for="item in questionsKinds"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>

        <el-col :span="6">
          <!-- 搜索区域 -->
          <el-input
            placeholder="请输入搜索的问题描述"
            v-model="queryInfo.information"
            clearable
            @clear="getQuestionList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getQuestionList"
            ></el-button>
          </el-input>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <el-table
        :data="questionList"
        border
        stripe
        :row-style="{ height: '20px' }"
      >
        <!-- 索引列 -->
        <el-table-column
          type="index"
          :index="indexMethod"
          label="序号"
          width="60px"
        ></el-table-column>
        <el-table-column label="问题" prop="question"></el-table-column>
        <el-table-column label="类型" prop="kind"></el-table-column>
        <el-table-column label="重做">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showRewriteDialog(scope.row.sid, scope.row.qid)"
            ></el-button>
          </template>
        </el-table-column>
        <el-table-column label="错误次数" prop="number"></el-table-column>
      </el-table>
    </el-main>

    <!-- 重做 -->
    <el-dialog
      title="重新写题"
      center
      :visible.sync="rewriteDialogVisible"
      width="50%"
      @close="rewriteDialogClosed"
    >
      <p class="pQuestion">题目：{{ questionInfo.question }}</p>
      <p>A：{{ questionInfo.option1 }}</p>
      <p>B：{{ questionInfo.option2 }}</p>
      <p>C：{{ questionInfo.option3 }}</p>
      <p>D：{{ questionInfo.option4 }}</p>
      <el-select v-model="rewriteInfo.answer" placeholder="选择答案">
        <el-option label="请选择" value=""></el-option>
        <el-option label="A" value="A"></el-option>
        <el-option label="B" value="B"></el-option>
        <el-option label="C" value="C"></el-option>
        <el-option label="D" value="D"></el-option>
      </el-select>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="rewriteDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="rewriteCommit(rewriteInfo.answer)"
          >提 交</el-button
        >
      </span>
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
        id: 0,
        information: "",
        kind: "",
        page: 1,
        size: 5,
      },
      // questionInfo:{
      //     question:'',
      //     options:[],
      // },
      rewriteInfo: {
        sid: 0,
        qid: 0,
        answer: "",
      },
      total: 0, //总记录数
      questionList: [],
      questionsKinds: [],
      questionInfo: {},
      rewriteDialogVisible: false,
    };
  },
  created() {
    this.queryInfo.id = this.$route.query.id;
    this.getQuestionList();
    this.getQuestionKinds();
  },
  methods: {
    async getQuestionList() {
      const { data: res } = await this.$http.post(
        "/answeredQuestions/getWrongQuestions",
        this.queryInfo
      );
      this.questionList = res.questionList;
      this.total = res.number;
    },
    async getQuestionKinds() {
      const { data: res } = await this.$http.get(
        "/answeredQuestions/getWrongQuestionsKinds/" + this.queryInfo.id
      );
      this.questionsKinds = res.kinds;
      console.log(res);
    },
    handleSizeChange(newSize) {
      this.queryInfo.size = newSize;
      this.getQuestionList();
    },
    //page的触发功能
    handleCurrentChange(newPage) {
      this.queryInfo.page = newPage;
      this.getQuestionList();
    },
    handleKindChange() {
      this.queryInfo.page = 1;
      this.getQuestionList();
    },
    indexMethod(index) {
      return (this.queryInfo.page - 1) * this.queryInfo.size + 1 + index;
    },
    async showRewriteDialog(sid, qid) {
      const { data: res } = await this.$http.get(
        "/questions/findQuestionById/" + qid
      );
      this.questionInfo = res.question;
      this.rewriteDialogVisible = true;
      this.rewriteInfo.sid = sid;
      this.rewriteInfo.qid = qid;
      console.log(res);
    },
    rewriteDialogClosed() {
      this.rewriteInfo.sid = 0;
      this.rewriteInfo.qid = 0;
      this.rewriteInfo.answer = "";
    },
    async rewriteCommit(answer) {
      if (answer != this.questionInfo.answer)
        return this.$message.error("答错了");
      const confirmRes = await this.$confirm(
        "已答对该题，是否从错题集里删除该题",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirmRes != "confirm") return this.$message.info("已取消删除");
      const { data: res } = await this.$http.delete(
        "/answeredQuestions/deleteWrongQuestion",
        {
          params: { sid: this.rewriteInfo.sid, qid: this.rewriteInfo.qid },
        }
      );
      if (res != "ok") return this.$message.error("删除失败");
      this.$message.success("删除成功");
      this.rewriteDialogVisible = false;
      this.getQuestionList();
    },
  },
};
</script>
<style lang="less" scoped>
html,
body,
#app,
.el-container {
  height: 100%;
}

.el-row.el-row--flex {
  padding-top: 20px;
  justify-content: space-between;
}

.el-card {
  box-shadow: 0 1px 1px rgba(0, 8, 10, 0.15) !important;
}
.el-table {
  margin-top: 15px;
  font-size: 15px;
  min-width: 350px;
  width: 100vw;
}
.el-dialog {
  font-size: 30px;
  .pQuestion {
    font-size: 20px;
  }
  .el-select {
    float: right;
  }
}
</style>
