<template>
  <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>


</template>

<script>
export default {
  name: "Questions.vue",
  data() {
    return {
      dialogVisible: false,
      question: {
        question: '',
        id: '',
        kind: '',
        option1: 'A',
        option2: 'B',
        option3: 'C',
        option4: 'D',
        answer: 'A'
      },
      questionList: {

      }
    }
  },
  methods: {
    getAllQuestions() {
      let _this = this
      this.$http.get('/getQuestion')
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    }
  }
}
</script>

