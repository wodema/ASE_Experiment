/*eslint-disable*/
let myConfig = {
  "entity": null,
  "childs": [
    {
      "entity": {
        "id": 'question',
        "parentMenuId": 'components/try/AllSortedStudent',
        "name": "教师题库&组卷",
        "icon": "el-icon-rank",
        "alias": "教师题库",
        "state": "ENABLE",
        "sort": 1,
        "value": '老师',
        "type": '老师',
        "createUserId": 1
      },
      "childs": null
    },
    {
      "entity": {
        "id": 'teacherAnalyse',
        "parentMenuId": 'components/try/AllStandardMajor',
        "name": "各项统计",
        "icon": "el-icon-s-marketing",
        "alias": "各项统计",
        "state": "ENABLE",
        "sort": 1,
        "value": '老师',
        "type": '老师',
        "createUserId": 1
      },
      "childs": null
    },
    {
      "entity": {
        "id": 'paper-list',
        "parentMenuId": 'components/try/AllSortedStudent',
        "name": "卷子列表",
        "icon": "el-icon-edit",
        "alias": "卷子列表",
        "state": "ENABLE",
        "sort": 1,
        "value": '老师',
        "type": '学生',
        "createUserId": 1
      },
      "childs": null
    },
    {
      "entity": {
        "id": 'studentAnalyse',
        "parentMenuId": 'components/try/AllSortedStudent',
        "name": "学生本人的统计数据",
        "icon": "el-icon-s-marketing",
        "alias": "学生本人的统计数据",
        "state": "ENABLE",
        "sort": 1,
        "value": '学生',
        "type": '学生',
        "createUserId": 1
      },
      "childs": null
    },/**以下的没用只是懒得删了**/
    {
      "entity": {
        "id": 'analyse',
        "parentMenuId": 0,
        "name": "数据统计",
        "icon": "el-icon-news",
        "alias": "数据统计",
        "state": "ENABLE",
        "sort": 1,
        "value": null,
        "type": "NONE",
        "createUserId": 1
      },
      "childs":[
        {
          "entity": {
            "id": 'studentAnalyse',
            "parentMenuId": 'components/try/Pagination&Table',
            "name": "学生统计",
            "icon": "el-icon-mobile-phone",
            "alias": "调剂队列",
            "state": "ENABLE",
            "sort": 1,
            "value": null,
            "type": "NONE",
            "createUserId": 1
          },
          "childs": null
        }
      ]
    }
  ]
}
export default myConfig
// /*eslint-disable*/
