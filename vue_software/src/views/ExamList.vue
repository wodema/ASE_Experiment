<template>
  <div class="block">
    <div class="block" style="display:flex">
      <el-select
          v-model="searchCol"
          size="medium"
          filterable
          clearable
          placeholder="选择要搜索的栏(不输入则搜索匹配所有栏)">
        <el-option
            v-for="item in this.testCols"
            :key="item"
            :label="item"
            :value="item">
        </el-option>
      </el-select>
      <el-input
          v-model="searchContent"
          placeholder="在所有数据中搜索指定字段"
          size="medium"
          style="width:200px;float:right;margin-bottom:10px;"
          clearable
          @keyup.enter.native="filterData2(tableData4sort,searchContent,searchCol).slice((currentPage-1)*pageSize,currentPage*pageSize)"
      ></el-input>
    </div>
    <div class="sys-description">
      <p><i class="el-icon-info"></i>所有专业表</p>
    </div>
    <el-pagination
        :hide-on-single-page="false"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10*1, 10*2, 10*5, 10*10,tableData.length]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length">
    </el-pagination>
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
      <el-table-column
          v-for="(col, idx) in testCols"
          sortable="custom"
          :prop="col"
          :label="col"
          :key="idx"
          min-width="100">
      </el-table-column>
    </el-table>
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
      total: 0,
      btn: true,
      testCols: [],
      tableData: [],
      tableData4sort: []
    }
  },
  methods: {
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
      this.request.post('/statistic/allSortedTable', {
        'tableName': (this.$store.getters.getBindList)['规范专业表名']
      })
          .then(response => {
            console.log(response)
            this.tableData = response.data['array']
            this.tableData4sort = Object.create(Object.getPrototypeOf(response.data['array']), Object.getOwnPropertyDescriptors(response.data['array']))
            this.testCols = Object.keys(response.data['array'][0])
            console.log(this.testCols)
            console.log(this.testCols[0])
            console.log(Object.values(this.testCols[0]))
            console.log(Object.values(this.testCols[0])[0])
          })
          .catch(error => {
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
          if (parseInt(a[attr]).toString() !== 'NaN' && parseInt(a[attr]).toString() !== 'NaN') {
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
