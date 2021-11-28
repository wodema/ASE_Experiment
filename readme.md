# 合作软工实验

## 配置方面

1. 修改application.properties中的datasource root password
2. 修改WebConfig中放行的地址为浏览器ip

## 可能存在的问题

1. npm下载很慢？

```bash
npm install -g cnpm --registry=https://registry.npm.taobao.org
```

2. npm版本

```bash
npm -v
6.14.15
node -v
v14.18.1
```

## 工作流程
测试rebase流程，好了告诉你们

1. git pull origin dev 
2. 自己commit自己的
3. git pull origin dev --rebase
4. git push origin dev


问：当我每次需要在新分支上开发，然后合并到master上，怎样避免变基合并问题？

每次开发的新分支都是dev，每次到dev时，git pull --rebase origin dev，一次迭代开发完，合并完代码mr后跑一下这个命令： git pull --rebase origin master
参见<https://blog.csdn.net/wuhuagu_wuhuaguo/article/details/105006408>

Playground<https://learngitbranching.js.org/?NODEMO=&locale=zh_CN>
## 我的意见

Inf司马了
