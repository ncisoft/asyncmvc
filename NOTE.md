# 开发日志

20151118 common-logging + log4j2 总是存在双次输出问题，切换回log4j-1.2.17就正常了，奇怪，折腾了一天

20151119 复杂的一天
* 把 Method 封装与 threadPool.submit(Callable<T> task)进行了统一
* 减少了封装层次。加入了 TimingFilter
* 统计每次请求的耗时。修改配置使用回 log4j-2.4.1

