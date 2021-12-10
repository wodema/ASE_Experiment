<template>
  <el-container>
    <el-header>
<!--      <el-row style="flex:auto;">-->
      <el-row type="flex">
        <el-col style="width:100%;">
<!--          <p>只有单选题  剩余时间:{{DDLTime-new Date().getTime()}}</p>-->
          <p>《{{this.$route.query.name}}》 {{this.$route.query.score}}分 只有单选题  剩余时间:{{remainTime}}秒  </p>
        </el-col>
        <el-col style="width:100%;">
          <el-button type="is-plain" icon="el-icon-upload" @click="submitPaper()" round>
            提交试卷</el-button>
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
              :max="1">
            <span>A.</span><el-checkbox label="A">A.  {{question1.option1}}</el-checkbox>
            <span>B.</span><el-checkbox label="B">B.  {{question1.option2}}</el-checkbox>
            <span>C.</span><el-checkbox label="C">C.  {{question1.option3}}</el-checkbox>
            <span>D.</span><el-checkbox label="D">D.  {{question1.option4}}</el-checkbox>
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
      DDLTime : parseInt(this.$route.query.time)*1000+new Date().getTime(),
      remainTime: parseInt(this.$route.query.time),
      questionList: [],
      answerList:[],
      idList:[],
      score:0
      // questionsKinds: [],
      // questionInfo: {},
      // rewriteDialogVisible: false,
    };
  },
  created() {
    this.getPaperById()
    // this.$store.commit("changeUserId",{userId:5})
    // console.log('userID')
    // console.log(this.$store.getters.getUserId)

    let that = this
    /**vuex-persistedstate只能这样手动清除**/
    // this.$store.commit("changeUserPaperMap4Time",{userPaperMap4Time:new Map()})
    // this.$store.commit("changeUserPaperMap4Answer",{userPaperMap4Answer:new Map()})
    // console.log(this.$store.getters.getUserPaperMap4Answer)
    /**同步答案**/
    if((this.$store.getters.getUserId+'+'+this.$route.query.id) in this.$store.getters.getUserPaperMap4Answer){
      // this.$store.commit("changeUserPaperMap4Answer",{userPaperMap4Answer:[[]]})
      console.log("answer is in vuex")
      // console.log(this.$store.getters.getUserPaperMap4Answer)
      that.answerList=this.$store.getters.getUserPaperMap4Answer[(this.$store.getters.getUserId+'+'+this.$route.query.id)]
    }
    /**同步时间**/
    if((this.$store.getters.getUserId+'+'+this.$route.query.id) in this.$store.getters.getUserPaperMap4Time){
      console.log("time is in vuex")
      that.DDLTime=this.$store.getters.getUserPaperMap4Time[(this.$store.getters.getUserId+'+'+this.$route.query.id)]
    }
    else{
      console.log("time is not in vuex")
      let timeMap=this.$store.getters.getUserPaperMap4Time
      timeMap[(this.$store.getters.getUserId+'+'+this.$route.query.id)] = new Date().getTime()+parseInt(this.$route.query.time)*1000
      this.$store.commit("changeUserPaperMap4Time",{userPaperMap4Time:timeMap})
      that.DDLTime=this.$store.getters.getUserPaperMap4Time[(this.$store.getters.getUserId+'+'+this.$route.query.id)]
      // console.log(this.$store.getters.getUserPaperMap4Time)
    }

    that.time = setInterval(that.remain_sec,1000)

  },
  methods: {
    getPaperById(){
      // this.DDLTime=response.data['paper']
      this.$http.post('/paperList/getPaper', {
        'id': this.$route.query.id
      }).then(response => {
        console.log(response)
        this.questionList=response.data['paper']
        for(let i=0;i<this.questionList.length;i++){
          if(this.answerList[i]===undefined){
            this.answerList.push([])
          }
        }
        for(let i=0;i<this.questionList.length;i++){
          if(this.idList[i]===undefined){
            this.idList.push(this.questionList[i].id)
          }
        }
      }).catch(error=>{
        console.log(error)
      })
    },
    submitPaper(){
      /**总交卷**/
      console.log(this.answerList)
      let tag=-1;
        if(this.remainTime> 0){
          for(let i=0;i<this.questionList.length;i++){
            if(this.answerList[i][0]===undefined) {
              tag=i
              break
            }
          }
          if(tag>=0){
            // alert("你有空白题!")
            this.$confirm("你有空白题 第" +(tag+1)+""+"题! 确认提交?",'提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then((_) => {
                this.calculateScore();
                done();
              })
              .catch((_) => { });
          }else{
            this.$confirm("确认提交?",'提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then((_) => {
                this.calculateScore();
                done();
              })
              .catch((_) => { });
          }
        }else{/**到了时间直接提交**/
          this.calculateScore();
        }
    },
    calculateScore(){
      for(let i=0;i<this.questionList.length;i++){
        if(this.answerList[i][0]===this.questionList[i].answer){
          this.score+=parseInt(this.questionList[i].score)
        }
      }
      this.$confirm("分数: "+this.score,'提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then((_) => {
          this.back()
          done();
        })
        .catch((_) => { this.back()});
      /**老师也可以做卷子但是不给提交到数据库**/
      if(this.$store.getters.getPrivilege==='老师'){
        this.$message("你是老师分数仅在前端显示")
        return;
      }
      this.$http.post('/answeredQuestions/insert', {
        // 'pid': this.$route.query.id,
        'sid': this.$store.getters.getUserId,
        'idList': this.idList,
        'answerList': this.answerList
      }).then(response => {
        this.$message("更新答卷表成功")
        console.log(response )
      }).catch(error=>{
        this.$message("更新答卷表失败")
        console.log(error)
      })
      this.$http.post('/score/insertScore', {
        // 'pid': this.$route.query.id,
        'sid': this.$store.getters.getUserId,
        'paper_id': this.$route.query.id,
        'score': this.score,
      }).then(response => {
        this.$message("更新分数表成功")
        console.log(response )
      }).catch(error=>{
        this.$message("更新答卷表失败")
        // this.$message({
        //   showClose: true,
        //   type: 'waring',
        //   message: "更新答卷表失败!"
        // })
        console.log(error)
      })



    },

    /**
     * @remain_sec 进行秒数自减的操作
     */
    remain_sec(){
      /**同步答案**/
      let answerMap=this.$store.getters.getUserPaperMap4Answer
      answerMap[(this.$store.getters.getUserId+'+'+this.$route.query.id)] = this.answerList
      this.$store.commit("changeUserPaperMap4Answer",{userPaperMap4Answer:answerMap})

      let that = this;
      // console.log("that.DDLTime")
      // console.log("new Date().getTime()")
      // console.log(that.DDLTime)
      // console.log(new Date().getTime())
      this.remainTime = Math.round((that.DDLTime-new Date().getTime())/1000)

      /**自动交卷**/
      if(this.remainTime <= 0){
        // let timeMap=new Map(this.$store.getters.getUserPaperMap4Time)
        let timeMap=this.$store.getters.getUserPaperMap4Time
        if(this.$store.getters.getUserId+'+'+this.$route.query.id in timeMap)delete timeMap[this.$store.getters.getUserId+'+'+this.$route.query.id]
        // timeMap.delete(this.$store.getters.getUserId+'+'+this.$route.query.id)
        this.$store.commit("changeUserPaperMap4Time",{userPaperMap4Time:timeMap})
        this.submitPaper()
        clearInterval(that.time)
      }
      // if(that.DDLTime === 0){
      //   that.$router.push({path:'/'});
      // }else{
      //   // that.DDLTime--;
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
</style>
