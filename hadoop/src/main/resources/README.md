## 打包
mvn clean build -x test
> 注意：package com.huma.hadoop下面只能有一个主类

## 查看执行结果
> D:\hadoop-for-winx64\bin>hadoop fs -cat /output/*