# Tomcat

tomcat基础架构

tomcat

![1603206306412](C:\Users\dad\AppData\Roaming\Typora\typora-user-images\1603206306412.png)



连接器 connector组件 负责通信 接受客户的请求 然后转给相关的容器处理 最后向客户返回相应结果 负责io

 然后 发送httpservlet 请求到 容器 Container中 其实就是一个servlet容器 根据请求找到对应的 Servlet 进行处理并返回



一个tomcat 会启动一个server 也就是一个服务

一个服务可以对应很多service

一个service中 可以有多个连接器 和一个容器 连接器可以解析不同协议的连接 http https等等

容器中 一个引擎 对应多个host

一个host代表的就是一个 主机地址 可以有多个主机地址

context为 项目的名称 代表一个webapp

wrapper为对应的  Servlet 

connect 里的端口号 +host 中的 地址名(localhost) 

![1603125227526](C:\Users\dad\AppData\Roaming\Typora\typora-user-images\1603125227526.png)

nginx配置 两台tomcat

![1603207554082](C:\Users\dad\AppData\Roaming\Typora\typora-user-images\1603207554082.png)

访问 99端口号 proxy_pass 就是反向代理的 模块 serverpool 对应两个tomcat server

范围】访问 locaklhost:8888 fan访问得劲就是的就是第一个tomcat                                                        访问localhost：9999fan访问的就是第二个tomcat  fan访问nginxde  的localhost：99fan访问的就是nginx nginx的默认负载均衡方式 就是轮网讯轮训轮询![1603209381955](C:\Users\dad\AppData\Roaming\Typora\typora-user-images\1603209381955.png)![1603209711009](C:\Users\dad\AppData\Roaming\Typora\typora-user-images\1603209711009.png)

nginx的基本参数  downji=机器啊之类的  可以给轮询配权重 weight aas、、

upstrean serverpool{

​	server localhost:8888 weight =2

​    server localhost:9999 weight =1 

}





# HTTP

​	TCP连接是 传输层协议 是操作系统提供的一种传输协议，只负责传输。

tcp在操作系统 写的三次握手 四次挥手的方法，但是当应用程序调用tcp连接时，不应该直接调用tcp的一些方法，所以计算机提供了tcp对外暴露的端口socket。

浏览器发送一个请求时，浏览器将 信息封装成 http请求，调用socket的连接方法，创建连接。服务器 tomcat捕获到 这个socket，（endpoint）然后 将socket获取到的信息，转成http请求信息，向servlet容器发送。

一个tomcat 可以接受 100个socket连接，创建一个线程池去处理每一个 socket的连接。 tomcat 1.7 bio

tomcat 1.8变成了 nio 不同一点。

![1603898123802](C:\Users\dad\AppData\Roaming\Typora\typora-user-images\1603898123802.png)



# 设计模式

### 设计模式的 六大原则

1 依赖倒转原则 

​	高层模块不应该 依赖低层模块，二者应该都依赖其抽象

​	抽象不应该依赖细节，细节应该依赖抽象。

 依赖倒转 的中心思想是面向接口模式。

​	相对于细节多变性，抽象应该稳定，以抽象为基础搭建的架构比细节搭建的架构要稳定，抽象值指的就是接口或抽象类，细节就是具体的实现类。

​	使用接口或抽象类的目的就是制定好规范，不涉及任何具体的操作，把展现细节的任务交给他们的实现类去完成。



2 接口隔离