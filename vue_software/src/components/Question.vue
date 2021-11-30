<template>
  <div id="root">
  <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>

  <el-dialog
      title="编辑新题目"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
    <span>
      <el-form ref="questionInfo" :model="questionInfo" :rules="rules"  label-width="80px">
  <el-form-item label="题目描述" prop="question">
    <el-input type="textarea" v-model="questionInfo.question" placeholder="请输入题目的描述"></el-input>
  </el-form-item>

       <el-form-item label="题目类型" prop="kind">
    <el-input v-model="questionInfo.kind" placeholder="请输入题目的类型"></el-input>
  </el-form-item>
        <el-form-item label="选项A"  prop="option1" >
    <el-input v-model="questionInfo.option1" placeholder="请输入选项A的内容"></el-input>
  </el-form-item>
         <el-form-item label="选项B" prop="option2">
    <el-input v-model="questionInfo.option2" placeholder="请输入选项B的内容"></el-input>
  </el-form-item>
        <el-form-item label="选项C" prop="option3">
    <el-input v-model="questionInfo.option3" placeholder="请输入选项C的内容"></el-input>
  </el-form-item>
        <el-form-item label="选项D" prop="option4" >
    <el-input v-model="questionInfo.option4" placeholder="请输入选项D的内容"></el-input>
  </el-form-item>

  <el-form-item label="正确答案" prop="answer">
    <el-select v-model="questionInfo.answer" placeholder="请选择正确答案"   >

      <el-option :label="'A. ' + questionInfo.option1" value="A"></el-option>
      <el-option :label="'B. ' +questionInfo.option2" value="B"></el-option>
      <el-option :label="'C. ' +questionInfo.option3" value="C"></el-option>
      <el-option :label="'D. ' +questionInfo.option4" value="D"></el-option>
    </el-select>
  </el-form-item>


<!--  <el-form-item>-->
<!--    <el-button type="primary" @click="onSubmit">立即创建</el-button>-->
<!--    <el-button>取消</el-button>-->
<!--  </el-form-item>-->
</el-form>

    </span>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleCancel">取 消</el-button>
    <el-button type="primary" @click="handleClick(questionInfo)">确 定</el-button>
  </span>

  </el-dialog>

  </div>
</template>

<script>
export default {

  data() {
    return {
      addQuestionSuccessOrNot: false,
      dialogVisible: false,
      questionInfo: {
        question: 'testingQuestion',
        kind: 'testingKind',
        option1: 'testingOptionA',
        option2: 'testingOptionB',
        option3: 'testingOptionC',
        option4: 'testingOptionD',
        answer: ''
      },
      rules: {
        question: [
          {required: true, message: '请输入题目的描述', trigger: 'blur'},
          {max: 254, message: '题目的描述最长为254个字符', trigger: 'blur'}
        ],
        kind: [
            { required: true, message: '请输入题目的类型', trigger: 'blur' },
                { max: 29, message: '题目的类型最长为29个字符', trigger: 'blur' }
        ],
        option1: [
            { required: true, message: '请输入选项A的描述', trigger: 'blur' },
          { max: 254, message: '选项A的描述最长为254个字符', trigger: 'blur' }
        ],
        option2: [
          { required: true, message: '请输入选项B的描述', trigger: 'blur' },
          { max: 254, message: '选项B的描述最长为254个字符', trigger: 'blur' }
        ],
        option3: [
          { required: true, message: '请输入选项C的描述', trigger: 'blur' },
          { max: 254, message: '选项C的描述最长为254个字符', trigger: 'blur' }
        ],
        option4: [
          { required: true, message: '请输入选项D的描述', trigger: 'blur' },
          { max: 254, message: '选项D的描述最长为254个字符', trigger: 'blur' }
        ],
        answer: [
          { required: true, message: '答案不能为空', trigger: 'blur' },

        ]

      }
    }
  },
  methods: {
    getAllQuestions() {
      let _this = this
      this.$http.get('/questions/getQuestion')
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            this.$message.warning('题目已暂时保存，为防止数据丢失请勿刷新浏览器')
            done();
          })
          .catch(_ => {});
    },
    handleCancel() {
        this.dialogVisible = false
        this.$message.warning('题目已暂时保存，为防止数据丢失请勿刷新浏览器')
    },
    handleClick() {

      this.$refs.questionInfo.validate((valid) => {
        if (valid) {
          this.addQuestion()
          this.dialogVisible = false
        } else {
          this.$message.error('题目不完全，提交失败！')
          return false;
        }
      });


    },
    addQuestion() {

      let _this = this.questionInfo

      this.$http.post('/questions/addQuestion', {
        question: _this.question,
        kind: _this.kind,
        option1: _this.option1,
        option2: _this.option2,
        option3: _this.option3,
        option4: _this.option4,
        answer: _this.answer,
        id: 114514
      })
      .then(res => {
        console.log(res)
        this.$message({
          message: '提交题目成功！',
          type: 'success'
        })
      })
      .catch(err => {
        console.log(err)
        this.$message.error('提交题目失败！')
      })

    }
  }
}
</script>

