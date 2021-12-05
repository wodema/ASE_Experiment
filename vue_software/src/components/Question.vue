<template>
  <div id="root">
    <el-container>
      <el-header>
        <el-row type="flex">
          <el-select v-model="queryInfo.kind" placeholder="题目类型" @change="handleSelectionChange">
            <el-option label="全部" value></el-option>
            <el-option v-for="kind in questionKinds" :key="kind.index" :label="kind" :value="kind"></el-option>
          </el-select>
          <el-button icon="el-icon-edit-outline" @click="addQuestionDialogVisible = true" round>添加题目</el-button>
          <span v-if="queryInfo.kind!=''">
          <el-button type="is-plain" v-if="!selectingQuestionForNewExam" icon="el-icon-plus" @click="submitNewExamWithQuestions" round>
          添加试卷</el-button>
          <el-button type="is-plain" v-else  icon="el-icon-upload" @click="questionsReadyToSubmit" round>
            提交试卷</el-button>
            </span>
          <el-col :span="6">
            <!-- 搜索区域 -->
            <el-input
              placeholder="搜索内容"
              v-model="queryInfo.information"
              clearable
              @clear="getQuestionList"
            >

              <el-button @click="getQuestionList" slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-table :data="questionList" border stripe>
          <!-- 索引列 -->
          <el-table-column type="index" :index="indexMethod" label="序号" width="50" ></el-table-column>
          <el-table-column label="类型" prop="kind" width="100"></el-table-column>
          <el-table-column label="问题" prop="question"  ></el-table-column>
          <el-table-column label="选择" width="54" v-if="selectingQuestionForNewExam&&queryInfo.kind!=''" >
            <template slot-scope="scope">
              <el-checkbox v-model="scope.row.isSelected" ></el-checkbox>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="260">
            <template slot-scope="scope">
              <el-button
                type="info"
                icon="el-icon-info"
                size="mini"
                @click="showQuestionInfo(scope.row)"
              >详情</el-button>


            </template>

            <template slot-scope="scope">
              <el-button
                      type="info is-plain"
                      icon="el-icon-info"
                      size="mini"
                      @click="showQuestionInfo(scope.row)"
              ><span class="button_description">详情</span>
                </el-button>
              <el-button
                      type="primary is-plain"
                      icon="el-icon-edit"
                      size="mini"
                      @click="editQuestion(scope.row)"
              ><span class="button_description">编辑</span></el-button>
              <el-button
                      type="danger is-plain"
                      icon="el-icon-delete"
                      size="mini"
                      @click="deleteQuestionById(scope.row.id)"
              ><span class="button_description">删除</span></el-button>
            </template>

          </el-table-column>
        </el-table>
      </el-main>

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





    <el-dialog
            class="addQuestionDialog"
      title="编辑题目"
      :visible.sync="addQuestionDialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <span>
        <el-form ref="questionInfo" :model="questionInfo" :rules="rulesForAddingQuestion" label-width="80px">
          <el-form-item label="题目描述" prop="question">
            <el-input type="textarea" v-model="questionInfo.question" placeholder="请输入题目的描述"></el-input>
          </el-form-item>

          <el-form-item label="题目类型" prop="kind">
            <el-input v-model="questionInfo.kind" placeholder="请输入题目的类型"></el-input>
          </el-form-item>
          <el-form-item label="选项A" prop="option1">
            <el-input v-model="questionInfo.option1" placeholder="请输入选项A的内容"></el-input>
          </el-form-item>
          <el-form-item label="选项B" prop="option2">
            <el-input v-model="questionInfo.option2" placeholder="请输入选项B的内容"></el-input>
          </el-form-item>
          <el-form-item label="选项C" prop="option3">
            <el-input v-model="questionInfo.option3" placeholder="请输入选项C的内容"></el-input>
          </el-form-item>
          <el-form-item label="选项D" prop="option4">
            <el-input v-model="questionInfo.option4" placeholder="请输入选项D的内容"></el-input>
          </el-form-item>

          <el-form-item label="正确答案" prop="answer">
            <el-select v-model="questionInfo.answer" placeholder="请选择正确答案">
              <el-option :label="'A. ' + questionInfo.option1" value="A"></el-option>
              <el-option :label="'B. ' + questionInfo.option2" value="B"></el-option>
              <el-option :label="'C. ' + questionInfo.option3" value="C"></el-option>
              <el-option :label="'D. ' + questionInfo.option4" value="D"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </span>

      <span slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="handleClick(questionInfo)">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog

      class="detailViewDialog"
      :visible.sync="showQuestionDetailsDialogVisible"
      width="30%"
      
    >

  <el-descriptions class="margin-top" title="题目详情" :column="1" size="small"  border>
    <template slot="extra">
      <el-button type="primary is-plain" size="small" @click="showQuestionDetailsDialogVisible=false" icon="el-icon-check" round>确定</el-button>
    </template>
    <el-descriptions-item>
      <template slot="label"> 
        <i class="el-icon-s-flag"></i>
        题目描述
      </template>
      {{ questionDetail.question}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-collection-tag"></i>
        题目类型
      </template>
      {{questionDetail.kind}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-key"></i>
        正确答案
      </template>
      {{ questionDetail.answer }}
    </el-descriptions-item>
    
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        选项A
      </template>
      {{questionDetail.option1}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        选项B
      </template>
      {{questionDetail.option2}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        选项C
      </template>
      {{questionDetail.option3}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        选项D
      </template>
      {{questionDetail.option4}}
    </el-descriptions-item>
  </el-descriptions>
    </el-dialog>


    <el-dialog title="新建试卷" :visible.sync="newExamSubmitDialogVisible" :before-close="handleClose">
      <span>
      <el-form ref="paperList" :model="paperList" :rules="rulesForAddingExam">
        <el-form-item label="试卷名称" prop="paper_name">
          <el-input v-model="paperList.paper_name"></el-input>
        </el-form-item>
        <el-form-item label="试卷科目">
          <br/>
          <el-tag>{{questionInfo.kind}}</el-tag>
        </el-form-item>
        <el-form-item label="考试时间">
          <div class="block">
    <span class="demonstration">{{paperList.totalTimeMinutesNumeric}}分钟</span>
    <el-slider
            show-input
            v-model="paperList.totalTimeMinutesNumeric"
            :min="15"
            :max="120"
            :step="15">
    </el-slider>
  </div>
        </el-form-item>
      </el-form>
    </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="examAddingDialogConfirmHandler">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      paperList: {
        paper_name: '',
        totalTimeMinutesNumeric: 15,
      },

      queryInfo: {
        information: "",
        kind: "",
        page: 1,
        size: 10,
      },
      candidateQuestionsForExam: [],
      selectingQuestionForNewExam: false,
      questionList: [

      ],
      questionKinds: [],
      addQuestionDialogVisible: false,
      editQuestionDialogVisible: false,
      showQuestionDetailsDialogVisible: false,
      newExamSubmitDialogVisible: false,
      questionDetail: {
        id: 114514,
        question: "",
        kind: "",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        answer: "",
      },

      questionInfo: {
        question: "",
        kind: "计算机",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        answer: "",
      },




      total: 0,
      rulesForAddingQuestion: {
        question: [
          {
            required: true,
            message: "请输入题目的描述",
            trigger: "blur",
          },
          {
            max: 254,
            message: "题目的描述最长为254个字符",
            trigger: "blur",
          },
        ],
        kind: [
          {
            required: true,
            message: "请输入题目的类型",
            trigger: "blur",
          },
          {
            max: 29,
            message: "题目的类型最长为29个字符",
            trigger: "blur",
          },
        ],
        option1: [
          {
            required: true,
            message: "请输入选项A的描述",
            trigger: "blur",
          },
          {
            max: 254,
            message: "选项A的描述最长为254个字符",
            trigger: "blur",
          },
        ],
        option2: [
          {
            required: true,
            message: "请输入选项B的描述",
            trigger: "blur",
          },
          {
            max: 254,
            message: "选项B的描述最长为254个字符",
            trigger: "blur",
          },
        ],
        option3: [
          {
            required: true,
            message: "请输入选项C的描述",
            trigger: "blur",
          },
          {
            max: 254,
            message: "选项C的描述最长为254个字符",
            trigger: "blur",
          },
        ],
        option4: [
          {
            required: true,
            message: "请输入选项D的描述",
            trigger: "blur",
          },
          {
            max: 254,
            message: "选项D的描述最长为254个字符",
            trigger: "blur",
          },
        ],

        answer: [{ required: true, message: "答案不能为空", trigger: "blur" }],
      },

      rulesForAddingExam: {
        paper_name: [{
          required: true,
          message: '试卷的名称不能为空',
          trigger: 'blur'
        }]
      }
    };
  },
  created() {
    this.getQuestionList();
    this.getQuestionKinds();
  },
  methods: {
    examAddingDialogConfirmHandler() {
      this.$refs.paperList.validate((valid) => {
        if(valid) {
          this.questionList.forEach((questionInfo) => {
            // 去重！
            if (questionInfo.isSelected === true && !this.candidateQuestionsForExam.includes(questionInfo.id)) {
              this.candidateQuestionsForExam.push(questionInfo.id)
            }
          })

          console.log(this.candidateQuestionsForExam)
          if (this.candidateQuestionsForExam.length !== 0) {
            let _this = this
            this.$http.post('/paperList/addPaperWithQuestions', {
              candidateQuestionsForExam: _this.candidateQuestionsForExam,
              paperList: {
                paper_name: _this.paperList.paper_name,
                total_time: _this.paperList.totalTimeMinutesNumeric.toString(10)
              }
            })
                    .then(res => {
                      console.log(res)
                      _this.candidateQuestionsForExam = []
                      _this.selectingQuestionForNewExam = false
                      _this.newExamSubmitDialogVisible = false
                      _this.$message.success({message: '添加试卷成功！', showClose: true})
                    })
                    .catch(err => {
                      console.log(err)
                      _this.$message.error({message: '添加试卷失败，请检查后台情况！', showClose: true})
                    })
          } else {
            this.$message.error({message: '试卷不可不选择试题！', showClose: true})
          }
        } else {
          this.$message.error({message: '试卷名称不可为空！', showClose: true})
        }

      })
    },
    questionsReadyToSubmit() {
      this.newExamSubmitDialogVisible = true
    },
    submitNewExamWithQuestions() {
      this.selectingQuestionForNewExam = true
    },
    resetQuestionSelection() {
      this.selectingQuestionForNewExam = false
      this.questionList.forEach((val,idx) => {
        val['isSelected'] = false
      })
    },
    getQuestionKinds() {
      let _this = this;
      this.$http
        .get("/questions/getQuestionKinds")
        .then((res) => {
          console.log("Here comes the kinds");
          console.log(res);
          _this.questionKinds = res.data.kinds;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    showQuestionInfo(question) {
      this.showQuestionDetailsDialogVisible = true;
      this.questionDetail.id = question.id;
      this.questionDetail.question = question.question;
      this.questionDetail.kind = question.kind;
      this.questionDetail.option1 = question.option1;
      this.questionDetail.option2 = question.option2;
      this.questionDetail.option3 = question.option3;
      this.questionDetail.option4 = question.option4;
      this.questionDetail.answer = question.answer;
      console.log(this.questionDetail);
    },
    editQuestion(question) {
      this.editQuestionDialogVisible = true
      this.addQuestionDialogVisible = true
      this.questionInfo.id = question.id
      this.questionInfo.question = question.question
      this.questionInfo.option1 = question.option1;
      this.questionInfo.option2 = question.option2;
      this.questionInfo.option3 = question.option3;
      this.questionInfo.option4 = question.option4;
      this.questionInfo.answer = question.answer;
      this.questionInfo.kind = question.kind;

      console.log(this.questionInfo);
    },
    updateQuestion() {

      this.$http.put('/questions/updateQuestion', this.questionInfo)
      .then(res => {
        console.log(res)
        this.resetQuestion()
        this.$message.success({message:'更新问题成功!', showClose: true})
        this.getQuestionList()

      })
      .catch(err => {
        console.log(err)
      })

    },
    deleteQuestionById(id) {
      let _this = this
      this.$http.delete('/questions/deleteQuestionById/' + id)
      .then(res => {
        console.log(res)
        _this.getQuestionList()
      })
      .catch(err => {
        console.log(err)
      })
    },
    getQuestionList() {
      let _this = this;
      this.$http
        .post("/questions/getQuestions", _this.queryInfo)
        .then((res) => {
          console.log("Here comes the questions");
          res.data.questionList.forEach((val, idx) => {
            if (this.candidateQuestionsForExam.includes(val.id) ) {
              val['isSelected'] = true
            } else {
              val['isSelected'] = false
            }

          })
          console.log(res.data.questionList);
          _this.questionList = res.data.questionList;
          _this.total = res.data.number;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleSelectionChange() {
      this.queryInfo.page = 1
      this.questionInfo.kind = this.queryInfo.kind
      this.selectingQuestionForNewExam = false
      this.resetQuestionSelection()
      this.candidateQuestionsForExam = []
      this.getQuestionList();
    },
    handleCurrentChange(newPage) {
      this.queryInfo.page = newPage;
      this.questionList.forEach((questionInfo) => {
        if (questionInfo.isSelected === true) {
          if (!this.candidateQuestionsForExam.includes(questionInfo.id)) {
            this.candidateQuestionsForExam.push(questionInfo.id)
          }


        }
      })
      console.log('candidates: ' + this.candidateQuestionsForExam)
      this.getQuestionList();
    },
    handleSizeChange(newSize) {
      this.queryInfo.size = newSize;
      this.getQuestionList();
    },
    indexMethod(index) {
      return (this.queryInfo.page - 1) * this.queryInfo.size + 1 + index;
    },
    handleClose(done) {
      console.log('editing: ' + this.editQuestionDialogVisible)
      if (!this.editQuestionDialogVisible) {
        this.$confirm("确认关闭？")

                .then((_) => {
                  if (!this.newExamSubmitDialogVisible)
                  {this.$message.warning({showClose: true, message: "题目已暂时保存，为防止数据丢失请勿刷新浏览器"});


                  } else  {
                    this.newExamSubmitDialogVisible = false
                  }
                  done();
                })
                .catch((_) => { });
      } else {
        this.addQuestionDialogVisible = false
        this.editQuestionDialogVisible = false

        this.setTimeout(this.resetQuestion, 100)
      }

    },
    resetQuestion() {
      this.questionInfo.question = ''
      this.questionInfo.kind = this.queryInfo.kind
      this.questionInfo.answer = ''
      this.questionInfo.option1 = ''
      this.questionInfo.option2 = ''
      this.questionInfo.option3 = ''
      this.questionInfo.option4 = ''
      this.questionInfo.id = 114514
    },

    handleCancel() {
      this.addQuestionDialogVisible = false;
      if (!this.editQuestionDialogVisible && !this.newExamSubmitDialogVisible) {
        this.$message.warning({showClose: true, message: "题目已暂时保存，为防止数据丢失请勿刷新浏览器"});
      } else {
        this.editQuestionDialogVisible = false
        this.newExamSubmitDialogVisible = false
        this.setTimeout(this.resetQuestion, 100)
      }

    },

    handleClick() {
      this.$refs.questionInfo.validate((valid) => {
        if (valid) {

          if (this.editQuestionDialogVisible) {
            this.updateQuestion()
            this.editQuestionDialogVisible = false
            this.addQuestionDialogVisible = false
          } else {
            this.addQuestion();
            this.addQuestionDialogVisible = false;
          }


        } else {
          this.$message.error({message: "题目不完全，提交失败！",showClose: true});
          return false;
        }
      });
    },
    addQuestion() {
      let _this = this.questionInfo;

      this.$http
        .post("/questions/addQuestion", {
          question: _this.question,
          kind: _this.kind,
          option1: _this.option1,
          option2: _this.option2,
          option3: _this.option3,
          option4: _this.option4,
          answer: _this.answer,
          id: 114514,
        })
        .then((res) => {
          console.log(res);
          this.$message({
            message: "提交题目成功！",
            type: "success",
            showClose: true
          });
          this.resetQuestion()
          this.getQuestionList();
        })
        .catch((err) => {
          console.log(err);
          this.$message.error({message:"提交题目失败！", showClose:true});
        });
    },
  },
};
</script>

<style>
#root,
.el-container {
  height: 100%;
}
.el-row.el-row--flex {
  padding-top: 20px;
  justify-content: space-between;
}
.el-dialog .addQuestionDialog {
    width: 50% !important;

}


.detailViewDialog .el-dialog__header, .detailViewDialog .el-dialog__close {
  display: none !important;
  padding: 0 !important;
}

.detailViewDialog .el-dialog__body {
  padding: 20px;
}


.detailViewDialog .el-descriptions__header {
  padding-left: 20px;
  padding-right: 20px; 
}

.detailViewDialog .el-descriptions-item__label.is-bordered-label {
  width: 12vw;
}

@media screen and (max-width: 600px) { /*当屏幕尺寸小于600px时，应用下面的CSS样式*/
  /*你的css代码*/
  .el-select {
    width: 7rem;
  }
  .el-table {
    overflow: hidden;
  }
  .detailViewDialog {
    width: 70% !important;
  }
  .button_description {
    display: none;
  }
  .el-button--info.is-plain {
    margin-left: 0px;
    /*padding-left: 4px !important;*/
    /*padding-right: 4px !important;*/
  }
  .el-button--primary.is-plain {
    margin-top: 2px !important;
    margin-left: 2px !important;
    /*padding-left: 4px !important;*/
    /*padding-right: 4px !important;*/
  }
  .el-button--danger.is-plain {
    margin-top: 2px !important;
    margin-left: 2px !important;
    /*padding-left: 4px !important;*/
    /*padding-right: 4px !important;*/

  }




  #root .el-container .el-header .el-row.el-row--flex {
    flex-flow: row ;

  }
  .detailViewDialog .el-descriptions-item__label.is-bordered-label {
  width: 25vw;
  }

  .el-input-group--prepend .el-input__inner, .el-input-group__append {
    padding-left: 5px !important;

    padding-right: 5px !important;
  }

  .el-input--suffix .el-input__inner {
    padding-right: 15px !important;
  }
  .el-footer {
   height: 32px !important;
  }

}


</style>
