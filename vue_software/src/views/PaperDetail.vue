<template>
  <el-container>
    <el-header>
<!--      <el-row style="flex:auto;">-->
      <el-row type="flex">
        <el-col style="width:100%;">
<!--          <p>只有单选题  剩余时间:{{seconds_timeout-new Date().getTime()}}</p>-->
          <p>只有单选题  剩余时间:{{remainTime}}</p>
        </el-col>
        <el-col style="width:100%;">
          <el-button @click="submitPaper()">submit</el-button>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <ol >
        <li v-for="(question1,index) in questionList" >
          <div>{{question1.question}}</div>
          <el-checkbox-group
              v-model="answerList[index]"
              :min="0"
              :max="4">
          {{index}}
          <el-checkbox label="A">{{question1.option1}}</el-checkbox>
          <el-checkbox label="B">{{question1.option2}}</el-checkbox>
          <el-checkbox label="C">{{question1.option3}}</el-checkbox>
          <el-checkbox label="D">{{question1.option4}}</el-checkbox>
          </el-checkbox-group>
        </li>
      </ol>
    </el-main>
    <el-footer>
    </el-footer>
  </el-container>
</template>
<script>
const cityOptions = ['上海', '北京', '广州', '深圳'];
export default {
  data() {
    return {
      // checkedCities: ['上海', '北京'],
      // cities: cityOptions,
      seconds_timeout : 10*1000+new Date().getTime(),
      remainTime: 10*1000,
      total: 0, //总记录数
      questionList: [],
      answerList:[],
      // questionsKinds: [],
      // questionInfo: {},
      // rewriteDialogVisible: false,
    };
  },
  created() {
    // this.queryInfo.id = this.$route.query.id;
    // this.getQuestionList();
    // this.getQuestionKinds();

    let that = this

    if((this.$store.getters.getUserId+''+this.$route.params.id) in this.$store.getters.getUserPaperMap4Time){
      console.log("is in")
      that.seconds_timeout=this.$store.getters.getUserPaperMap4Time[(this.$store.getters.getUserId+''+this.$route.params.id)]
    }
    else{
      console.log("not in")
      let timeMap=this.$store.getters.getUserPaperMap4Time
      // timeMap.set((this.$store.getters.getUserId+''+this.$route.params.id),that.seconds_timeout+new Date().getTime()+10*1000)
      timeMap[(this.$store.getters.getUserId+''+this.$route.params.id)] = new Date().getTime()+10*1000
      this.$store.commit("changeUserPaperMap4Time",{userPaperMap4Time:timeMap})
      that.seconds_timeout=this.$store.getters.getUserPaperMap4Time[(this.$store.getters.getUserId+''+this.$route.params.id)]
    }
    console.log(new Date().getTime())
    that.time = setInterval(that.remain_sec,1000)
    this.getPaperById()
  },
  methods: {
    getPaperById(){
      console.log(this.$route.params.id)
      this.$http.post('/paperList/getPaper', {
        'id': this.$route.params.id
      }).then(response => {
        console.log(response)
        this.questionList=response.data['paper']
        // this.seconds_timeout=response.data['paper']
        for(let i=0;i<this.questionList.length;i++){
          // this.answerList.push({index:i,studentAnswer:'null'})
          // this.answerList.push('null')
          this.answerList.push([])
        }
      }).catch(error=>{
        console.log(error)
      })
    },
    submitPaper(){
      console.log(this.answerList)
    },
    /**
     * @remain_sec 进行秒数自减的操作
     */
    remain_sec(){
      let that = this;
      // console.log(that.seconds_timeout)
      // console.log(new Date().getTime())
      this.remainTime = that.seconds_timeout-new Date().getTime()
      if(that.seconds_timeout-new Date().getTime() <= 0){
        // let timeMap=new Map(this.$store.getters.getUserPaperMap4Time)
        let timeMap=this.$store.getters.getUserPaperMap4Time
        delete timeMap[this.$store.getters.getUserId+''+this.$route.params.id]
        // timeMap.delete(this.$store.getters.getUserId+''+this.$route.params.id)
        this.$store.commit("changeUserPaperMap4Time",{userPaperMap4Time:timeMap})
        this.submitPaper();
        clearInterval(that.time);
      }
      // if(that.seconds_timeout === 0){
      //   that.$router.push({path:'/'});
      // }else{
      //   // that.seconds_timeout--;
      // }
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
