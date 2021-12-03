/*eslint-disable*/
let myConfig = {
  "entity": null,
  "childs": [
    {
      "entity": {
        "id": 'question',
        "parentMenuId": 'components/try/AllSortedStudent',
        "name": "教师题库",
        "icon": "el-icon-bell",
        "alias": "教师题库",
        "state": "ENABLE",
        "sort": 1,
        "value": null,
        "type": "NONE",
        "createUserId": 1
      },
      "childs": null
    },
    {
      "entity": {
        "id": 'components/try/AllStandardMajor',
        "parentMenuId": 'components/try/AllStandardMajor',
        "name": "教师题库",
        "icon": "el-icon-rank",
        "alias": "教师组卷",
        "state": "ENABLE",
        "sort": 1,
        "value": null,
        "type": "NONE",
        "createUserId": 1
      },
      "childs": null
    },
    {
      "entity": {
        "id": 'paper-list',
        "parentMenuId": 'components/try/AllSortedStudent',
        "name": "考试列表",
        "icon": "el-icon-bell",
        "alias": "考试列表",
        "state": "ENABLE",
        "sort": 1,
        "value": null,
        "type": "NONE",
        "createUserId": 1
      },
      "childs": null
    },
    {
      "entity": {
        "id": 'wrongQuestion',
        "parentMenuId": 'components/try/AllSortedStudent',
        "name": "学生错题入口",
        "icon": "el-icon-bell",
        "alias": "学生错题入口",
        "state": "ENABLE",
        "sort": 1,
        "value": null,
        "type": "NONE",
        "createUserId": 1
      },
      "childs": null
    },
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
            "id": 'analyse',
            "parentMenuId": 'components/try/Pagination&Table',
            "name": "调剂队列",
            "icon": "el-icon-mobile-phone",
            "alias": "调剂队列",
            "state": "ENABLE",
            "sort": 1,
            "value": null,
            "type": "NONE",
            "createUserId": 1
          },
          "childs": null
        },
        {
          "entity": {
            "id": 'components/try/tableNoPage',
            "parentMenuId": 'components/try/tableNoPage',
            "name": "按专业统计各项数据",
            "icon": "el-icon-news",
            "alias": "按专业统计各项数据",
            "state": "ENABLE",
            "sort": 1,
            "value": null,
            "type": "NONE",
            "createUserId": 1
          },
          "childs": null
        },
        {
          "entity": {
            "id": 'components/try/groupByDepartment',
            "parentMenuId": 'components/try/groupByDepartment',
            "name": "按学院统计各项数据",
            "icon": "el-icon-news",
            "alias": "按学院统计各项数据",
            "state": "ENABLE",
            "sort": 1,
            "value": null,
            "type": "NONE",
            "createUserId": 1
          },
          "childs": null
        },
        {
          "entity": {
            "id": 'components/try/TotalStatistic',
            "parentMenuId": 'components/try/TotalStatistic',
            "name": "全校统计各项数据&热门专业",
            "icon": "el-icon-news",
            "alias": "全校统计各项数据&热门专业",
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
