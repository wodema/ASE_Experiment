<template>
    <div id="root">
        <el-card>
            <el-row :gutter="24">
                <el-col :span="10">
                    <!-- 搜索区域 -->
                    <el-input placeholder="请输入搜索内容" v-model="queryInf.information" clearable @clear="getQuestionList">
                        <el-button slot="append" icon="el-icon-search" @click="getQuestionList"></el-button>
                    </el-input>
                </el-col>
                <el-select v-model="queryInf.kind" placeholder="题目类型" @change="getQuestionList">
                    <el-option label="请选择" value=""></el-option>
                    <el-option v-for="item in questionsKinds" :key="item" :label="item" :value="item"></el-option>
                </el-select>
            </el-row>
            <el-table :data="questionList" border stripe>
                <!-- 索引列 -->
                <el-table-column type="index" :index="indexMethod" label="序号" width="100%"></el-table-column>
                <el-table-column label="问题" prop="question"></el-table-column>
                <el-table-column label="类型" prop="kind"></el-table-column>
                <el-table-column label="重做">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="showRewriteDialog(scope.row.id, scope.row.qid)"></el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div id="block"></div>
            <!-- 分页 size-change 每页最大变化数 current-change 当前最大变化数 layout功能组件-->
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="queryInf.page"
                :page-sizes="[1, 2, 5, 10]"
                :page-size="queryInf.size"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
            ></el-pagination>
            
            <!-- 重做 -->
            <el-dialog title="重新写题" center :visible.sync="rewriteDialogVisible" width="50%" @close="rewriteDialogClosed">
                <p class="pQuestion">题目：{{questionInf.question}}</p>
                <p>A：{{questionInf.option1}}</p>
                <p>B：{{questionInf.option2}}</p>
                <p>C：{{questionInf.option3}}</p>
                <p>D：{{questionInf.option4}}</p>
                <el-select v-model="rewriteInf.answer" placeholder="选择答案">
                    <el-option label="请选择" value=""></el-option>
                    <el-option label="A" value="A"></el-option>
                    <el-option label="B" value="B"></el-option>
                    <el-option label="C" value="C"></el-option>
                    <el-option label="D" value="D"></el-option>
                </el-select>
                <!-- 内容底部区域 -->
                <span slot="footer" class="dialog-footer">
                    <el-button @click="rewriteDialogVisible = false">取 消</el-button> 
                    <el-button type="primary" @click="rewriteCommit(rewriteInf.answer)">提 交</el-button>
                </span>
            </el-dialog>
        </el-card>
    </div>
</template>
<script>

export default{
    data(){
        return{
            queryInf:{
                //查询信息实体
                //id: window.sessionStorage.getItem("user").id,
                id:3,
                information:'',
                kind:'',
                page:1,
                size:5,
            },
            // questionInf:{
            //     question:'',
            //     options:[],
            // },
            rewriteInf:{
                id: 0,
                answer:'',
            },
            total: 0,//总记录数
            questionList:[],
            questionsKinds:[],
            questionInf:{},
            rewriteDialogVisible:false,
        }
    },
    created(){
        this.getQuestionList();
        this.getQuestionKinds();
    },
    methods:{
        async getQuestionList(){
            console.log(this.queryInf);
            const {data: res} = await this.$http.post("/getWrongQuestions", this.queryInf);
            this.questionList = res.questionList;
            this.total = res.number;
            console.log(res);
        },
        async getQuestionKinds(){
            const {data: res} = await this.$http.get("/getWrongQuestionsKinds/"+this.queryInf.id);
            this.questionsKinds = res.kinds;
            console.log(res);
        },
        handleSizeChange(newSize){
            this.queryInf.size = newSize;
            this.getQuestionList();
        },
        //page的触发功能
        handleCurrentChange(newPage){
            this.queryInf.page = newPage;
            this.getQuestionList();
        },
        indexMethod(index){
             return (this.queryInf.page - 1) * (this.queryInf.size) + 1 + index;
        },
        async showRewriteDialog(id, qid){
            const {data: res} = await this.$http.get("/getQuestionInf/"+qid);
            this.questionInf = res.questionInf;
            this.rewriteDialogVisible = true;
            this.rewriteInf.id = id;
            console.log(res);
        },
        rewriteDialogClosed(){
            this.rewriteInf.id = 0;
            this.rewriteInf.answer = '';
        },
        async rewriteCommit(answer){
            if(answer != this.questionInf.answer)
                return this.$message.error("答错了");
            this.$http.delete("/deleteWrongQuestion/"+this.rewriteInf.id)
            .then((result) => {
                if(result.data == "ok")
                    this.$message.success("提交成功");
                else
                    this.$message.error("提交失败");
                return this.getQuestionList();
            }).catch((err) => {
                this.$message.error("提交异常");
            });
        }
    }
}
</script>
<style lang="less" scoped>
.el-card{
    box-shadow: 0 1px 1px rgba(0, 8, 10, 0.15) !important;
}
.el-table{
    margin-top: 15px;
    font-size: 15px;
    min-width: 350px;
}
.el-dialog{
    font-size: 30px;
    .pQuestion{
        font-size: 20px;
    }
    .el-select{
        float: right;
    }

}

div#block {
    width: 100%;
    
    min-height: 10vh;
    
}




</style>
