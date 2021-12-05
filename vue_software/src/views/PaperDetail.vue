<template>
  <el-container>
    <el-header>
      <p>只有单选题  time:{{seconds_timeout}}</p>
    </el-header>
    <el-main>
      <div>
        {{checkedCities}}
      </div>
      <el-checkbox-group
          v-model="checkedCities"
          :min="0"
          :max="4">
        <el-checkbox v-for="city in cities" :label="city" :key="city" style="display:block;">{{ city }}</el-checkbox>
      </el-checkbox-group>
    </el-main>

<!--    &lt;!&ndash; 重做 &ndash;&gt;-->
<!--    <el-dialog-->
<!--        title="重新写题"-->
<!--        center-->
<!--        :visible.sync="rewriteDialogVisible"-->
<!--        width="50%"-->
<!--        @close="rewriteDialogClosed"-->
<!--    >-->
<!--      <p class="pQuestion">题目：{{ questionInfo.question }}</p>-->
<!--      <p>A：{{ questionInfo.option1 }}</p>-->
<!--      <p>B：{{ questionInfo.option2 }}</p>-->
<!--      <p>C：{{ questionInfo.option3 }}</p>-->
<!--      <p>D：{{ questionInfo.option4 }}</p>-->
<!--      <el-select v-model="rewriteInfo.answer" placeholder="选择答案">-->
<!--        <el-option label="请选择" value=""></el-option>-->
<!--        <el-option label="A" value="A"></el-option>-->
<!--        <el-option label="B" value="B"></el-option>-->
<!--        <el-option label="C" value="C"></el-option>-->
<!--        <el-option label="D" value="D"></el-option>-->
<!--      </el-select>-->
<!--      &lt;!&ndash; 内容底部区域 &ndash;&gt;-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--        <el-button @click="rewriteDialogVisible = false">取 消</el-button>-->
<!--        <el-button type="primary" @click="rewriteCommit(rewriteInfo.answer)"-->
<!--        >提 交</el-button-->
<!--        >-->
<!--      </span>-->
<!--    </el-dialog>-->
<!--    @click="change(index1)"-->
<!--    :class="{'border': index == index1 && currentType == 1,'bg': bg_flag && topic[1][index1].isClick == true}">-->
<!--    <span :class="{'mark': topic[1][index1].isMark == true}"></span>-->
    <el-footer>
      <div class="left" >
        <ul class="l-top">
          <li>
            <a href="javascript:;"></a>
            <span>当前</span>
          </li>
          <li>
            <a href="javascript:;"></a>
            <span>未答</span>
          </li>
          <li>
            <a href="javascript:;"></a>
            <span>已答</span>
          </li>
          <li>
            <a href="javascript:;"></a>
            <span>标记</span>
          </li>
        </ul>
      </div>

      <ul >
        <li v-for="city in cities" style="float: left">
          <a href="javascript:;">

            {{city}}
          </a>
        </li>
      </ul>

    </el-footer>
  </el-container>
</template>
<script>
const cityOptions = ['上海', '北京', '广州', '深圳'];
export default {
  data() {
    return {
      checkedCities: ['上海', '北京'],
      cities: cityOptions,
      seconds_timeout : 600,
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
    // this.queryInfo.id = this.$route.query.id;
    // this.getQuestionList();
    // this.getQuestionKinds();
    let that = this;
    that.time = setInterval(that.remain_sec,1000)
  },
  methods: {
    /**
     * @remain_sec 进行秒数自减的操作
     */
    remain_sec(){
      let that = this;
      if(that.seconds_timeout === 0){
        that.$router.push({path:'/'});
      }else{
        that.seconds_timeout--;
      }
    },
    back(){
      this.$router.go(-1);//返回上一层
    }
  },
  /**
   * @beforeDestroy  页面销毁前，将Interval清除
   */
  beforeDestroy : function () {
    let that = this;
    clearInterval(that.time);
  }
};
</script>
<style lang="less" scoped>
html,
body,
#app,
.left .l-top {
  display: flex;
  justify-content: space-around;
  padding: 16px 0px;
  border: 1px solid #eee;
  border-radius: 4px;
  margin-bottom: 10px;
  background-color: #fff;
}
.left {
  width: 260px;
  height: 100%;
  margin: 10px 10px 0px 10px;
}
.left .l-top li:nth-child(2) a {
  border: 1px solid #eee;
}
.left .l-top li:nth-child(3) a {
  background-color: #5188b8;
  border: none;
}
.left .l-top li:nth-child(4) a {
  position: relative;
  border: 1px solid #eee;
}
.left .l-top li:nth-child(4) a::before {
  width: 4px;
  height: 4px;
  content: " ";
  position: absolute;
  background-color: red;
  border-radius: 50%;
  top: 0px;
  left: 16px;
}
.left .l-top li {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.left .l-top li a {
  display: inline-block;
  padding: 10px;
  border-radius: 50%;
  background-color: #fff;
  border: 1px solid #FF90AA;
}
</style>
