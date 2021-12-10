<template>
  <div>
<!--    <div class="block" style="display:flex ">-->
    <el-row type="flex">
      <div class="sys-description" style="align-content: center; align-items:center">
        <p style="align-content: center; align-items:center"><i class="el-icon-info"></i>所有卷子</p>
      </div>
      <el-button v-if="$store.getters.getPrivilege==='老师'" type="is-plain" icon="el-icon-upload" @click="updatePaperList()" round>上传卷子信息更改</el-button>
    </el-row>
      <el-row type="flex">
      <el-select style="left: revert; width:100%;padding-right: 20px"
          round
          v-model="searchCol"
          size="medium"
          filterable
          clearable
          placeholder="选择要搜索的栏(不输入则搜索匹配所有栏)" >
        <el-option
            v-for="item in this.testCols"
            :key="item"
            :label="item"
            :value="item">
        </el-option>
      </el-select>
<!--      <h3>&#45;&#45;&#45;&#45;</h3>-->
      <el-input
          round
          v-model="searchContent"
          placeholder="在所有数据中搜索指定字段"
          size="medium"
          style="width:300px;float:right;margin-bottom:10px;float:right "
          clearable
          @keyup.enter.native="filterData2(tableData4sort,searchContent,searchCol).slice((currentPage-1)*pageSize,currentPage*pageSize)"
      ></el-input>
        </el-row>
<!--    </div>-->
    <el-row style="padding-bottom: 10px">
    <el-pagination
        :hide-on-single-page="false"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10*1, 10*2, 10*5, 10*10,tableData4sort.length]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData4sort.length">

    </el-pagination>
    </el-row>
    <el-row>
    <el-form :model="filterData2(tableData4sort,searchContent,searchCol).slice((currentPage-1)*pageSize,currentPage*pageSize)"
             :rules="tableRules" style="display: flex"
             status-icon
             ref="tableRules">
    <el-table
        border
        :data="filterData2(tableData4sort,searchContent,searchCol).slice((currentPage-1)*pageSize,currentPage*pageSize)"
        :stripe="stripe"
        :current-page.sync="currentPage"
        @sort-change="changeSort"
        size:mini
        style="width: 100%;font-size: 6px"
        :header-cell-style="{background:'#f0f9eb', fontFamily:'Helvetica',fontSize:'8px'}"
        :row-style="{height:'20px'}"
        :cell-style="{padding:'0px'}"
    >
<!--      <el-table-column-->
<!--          v-for="(col, idx) in testCols"-->
<!--          sortable="custom"-->
<!--          :prop="col"-->
<!--          :label="col"-->
<!--          :key="idx"-->
<!--          min-width="100">-->
<!--      </el-table-column>-->
      <el-table-column
          sortable="custom"
          prop="paper_id"
          label="卷子id"
          min-width="60">
      </el-table-column>
      <el-table-column
          sortable="custom"
          prop="paper_name"
          label="卷子名称"
          min-width="150">
        <template  slot-scope="scope1">
<!--          <el-form-item :prop="scope1.column.property"-->
<!--                        :rules="tableRules.notNull">-->
            <el-input v-if="$store.getters.getPrivilege==='老师'" v-model="scope1.row[scope1.column.property]"
                      minlength="1" maxlength="20" show-word-limit></el-input>
            <div v-else>{{scope1.row[scope1.column.property]}}</div>
<!--          </el-form-item>-->
        </template>
      </el-table-column>
      <el-table-column
          sortable="custom"
          prop="total_time"
          label="考试总秒数"
          min-width="120">
        <template  slot-scope="scope1">
          <el-input-number v-if="$store.getters.getPrivilege==='老师'" v-model="scope1.row[scope1.column.property]" :step="1" :min=1 :max=9999 size="mini"></el-input-number>
          <div v-else>{{scope1.row[scope1.column.property]}}</div>
        </template>
      </el-table-column>
      <el-table-column
          sortable="custom"
          prop="total_score"
          label="考试总分"
          min-width="100">
      </el-table-column>
      <el-table-column label="师生共用操作:"
          min-width="100">
        <template slot-scope="scope2">
<!--          <el-button v-if="$store.getters.getPrivilege==='学生'" @click="interPaper(scope2.row)">做卷</el-button>-->
          <el-button
              type="primary is-plain"
              icon="el-icon-edit"
              size="mini"
              @click="interPaper(scope2.row)"
          ><span class="button_description">做卷</span></el-button>
        </template>
      </el-table-column>
      <el-table-column label="老师独有操作:"
                       min-width="100"
                       v-if="$store.getters.getPrivilege==='老师'" >
        <template slot-scope="scope2">
          <el-button
              type="danger is-plain"
              icon="el-icon-delete"
              size="mini"
              @click="deletePaper(scope2.row)"
          ><span class="button_description">删卷</span></el-button>
        </template>
      </el-table-column>
    </el-table>
    </el-form>
    </el-row>
  </div>
</template>
<script>

export default {
  data () {
    return {
      searchCol: [],
      searchContent: '',
      stripe: true, //  是否为斑马纹 table
      currentPage: 1,
      pageSize: 10,
      btn: true,
      testCols: [],
      tableData4sort: [],
      tableRules: {
        notNull: [{required: true, message: 'can\'t be null', trigger: ['blur', 'change']}]
      }
    }
  },
  methods: {
    interPaper(object){
      // console.log(object)
      const { href } = this.$router.resolve({
        name: `PaperDetail`,
        params:{
          // query: {
          id: object.paper_id,
          time: object.total_time,
          // name: object.paper_name,
          score: object.total_score,
        }
      })
      window.open(href, "_self")
      // window.open(href, "_blank")
    },
    deletePaper(object){
      console.log(object)
      this.$confirm("删除不可逆!确认删除?",'提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then((_) => {
          this.$http.post('/paperList/deletePaper', {
            'paper_id': object.paper_id,
          }).then(response => {
            this.$message("删除成功")
            this.fetchJobs()
            console.log(response )
          }).catch(error=>{
            this.$message("删除失败")
            this.$message(error)
            console.log(error)
          })
          done();
        })
        .catch((_) => { });
    },
    updatePaperList(){
      this.$confirm("更新不可逆!确认更新?",'提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then((_) => {
          this.$http.post('/paperList/updatePaperList', {
            'tableData4sort': this.tableData4sort,
          }).then(response => {
            this.$message("更新成功")
            this.fetchJobs()
            console.log(response )
          }).catch(error=>{
            this.$message("更新失败")
            this.$message(error)
            console.log(error)
          })
          done();
        })
        .catch((_) => { });
    },
    dateFormat(cellValue) {
      if (cellValue == null || cellValue == "") return "";
      let date = new Date(parseInt(cellValue));//时间戳为10位需*1000，如果为13位的话不需乘1000。
      let Y = date.getFullYear() + '-';
      let M = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) + '-' : date.getMonth() + 1 + '-';
      let D = date.getDate() < 10 ? '0' + date.getDate() + ' ' : date.getDate() + ' ';
      let h = date.getHours() < 10 ? '0' + date.getHours() + ':' : date.getHours() + ':';
      let m = date.getMinutes() < 10 ? '0' + date.getMinutes() + ':' : date.getMinutes() + ':';
      let s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
      return Y + M + D + h + m + s;
    },
    handleSizeChange (val) {
      this.pageSize = val
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange (val) {
      this.currentPage = val
      console.log(`当前页: ${val}`)
    },
    filterData2 (data, searchContent, searchCol) {
      var input = searchContent.toLowerCase()
      var items = data
      var items1
      if (input) {
        items1 = items.filter(function (item) {
          if (searchCol === null || searchCol === '') {
            return Object.keys(item).some(function (key1) {
              return String(item[key1])
                  .toLowerCase()
                  .match(input)
            })
          } else {
            return String(item[searchCol])
                .toLowerCase()
                .match(input)
          }
        })
        // }.bind(this)).bind(this)
      } else {
        items1 = items
      }
      return items1
    },
    fetchJobs: function () {
      this.$http.post('/paperList/getPaperList', {
        'tableName': "(this.$store.getters.getBindList)['规范专业表名']"
      })
          .then(response => {
            console.log(response)
            this.tableData4sort = response.data['paperList']
            this.testCols = Object.keys(response.data['paperList'][0])
            // for(let i=0;i<this.tableData4sort.length;i++){
            //   if(this.testCols[i]==='paper_date')this.testCols[i]['label'] = "截止日期"
            //   if(this.testCols[i]==='paper_id')this.testCols[i]['label'] = "主键"
            //   if(this.testCols[i]==='total_time')this.testCols[i]['label'] = "截止日期"
            //   if(this.testCols[i]==='total_score')this.testCols[i]['label'] = "截止日期"
            //
            // }
            console.log("this.testCols")
            console.log(this.testCols)
            /**日期转换**/
            for(let i=0;i<this.tableData4sort.length;i++){
              this.tableData4sort[i]['paper_date']=this.dateFormat(this.tableData4sort[i]['paper_date'])
            }
            // console.log(this.testCols)
            // console.log(this.testCols[0])
            // console.log(Object.values(this.testCols[0]))
            // console.log(Object.values(this.testCols[0])[0])
          })
          .catch(error => {
            this.$message("获取试卷列表失败")
            console.log(error)
          })
    },
    changeSort (val) {
      console.log(val) // column: {…} order: "ascending" prop: "date"// val.prop,val.order
      // 根据当前排序重新获取后台数据,一般后台会需要一个排序的参数
      function sortBy (attr, rev) { //  第二个参数没有传递默认升序排列
        if (rev === undefined) {
          rev = 1
        } else {
          rev = (rev === 'ascending') ? 1 : -1
        }
        return function (a, b) {
          if (parseInt(a[attr]).toString() !== 'NaN' && parseInt(b[attr]).toString() !== 'NaN') {
            a = parseInt(a[attr])
            b = parseInt(b[attr])
          } else {
            a = a[attr]
            b = b[attr]
          }
          if (a < b) {
            return rev * -1
          }
          if (a > b) {
            return rev * 1
          }
          return 0
        }
      }
      this.tableData4sort.sort(sortBy(val.prop, val.order))
    }
  },
  created () {
    this.fetchJobs()
  }
}
</script>
