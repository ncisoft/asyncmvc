# 开发日志

20151118 common-logging + log4j2 总是存在双次输出问题，切换回log4j-1.2.17就正常了，奇怪，折腾了一天

20151119 复杂的一天
* 把 Method 封装与 *threadPool.submit(Callable<T> task)* 进行了统一
* 减少了封装层次。加入了 TimingFilter
* 统计每次请求的耗时。修改配置使用回 log4j-2.4.1
* 但是很奇怪，通过 **TimingFilter** 的 Profile 数据，显示这种封装性能较差

20151120 * System.nanoTime() * 对性能影响比较大，故删除一系列的 profile 调试信息

20151120 引入了 ** ASyncFilter.java**，直接通过 Filter 完成对 servlet 的异步封装
