# Springboot学习

## 	1 springboot 配置

 首先配置启动类 

```java
@SpringBootApplication
public class PterosauriaApplication { 
    public static void main(String[] args) {        		
    SpringApplication.run(PterosauriaApplication.class, args);    }}
    
```

必须有 @SpringBootApplication注解，说明此类是springboot的主配置类

springboot应该用这个类的main方法来启动springboot

@SpringBootApplication注解如下

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
```

@SpringBootConfiguration：springboot的配置类

   说明这个类是springboot的注解类

​      @configuration  配置类上的注解 其实就是spring最底层的配置类

​			配置类 ----- 配置文件

@EnableAutoConfiguration 

​    	开启自动配置，以前需要配置的东西，springboot帮我们自动配置@EnableAutoConfiguration告诉         	springboot开启自动配置功能。

```java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {}
```

@AutoConfigurationPackage 自动配置包

​	@Import(AutoConfigurationPackages.Registrar.class)

  	spring的底层注解@import，给容器中导入一个组件，导入的组件由**AutoConfigurationPackages.Registrar.class 将主配置类（application类 springbootapplication注解标注的类）所在包，及包下所有组件扫描到Spring容器中。**

```java
@Import(AutoConfigurationImportSelector.class)
```

​	给容器中导入组件 AutoConfigurationImportSelector

​	将所有需要导入的组件以全类名的方式返回，这些组件就被添加到容器中

​	会给容器中导入非常多的自动配置类 （xxxAutoConfiguration）aop  批处理 什么的---

有了自动配置类，免去了我们手动编写配置注入功能组件的工作

springboot-autoconfigure 所有的组件全都注入进去了 

javaee的整体整合解决方案和自动配置都在spring-boot-autoconfigure-1.5.9.RELEASE.jar

## 2 Spring Initializr

快速创建springboot 项目

选择需要的模块

web sql cache 分布式需要的各种starter 选择 

 向导会联网创建springboot项目

主程序写好啦只需要写 业务逻辑

​       

## 3 SpringBoot 程序分布

· java

· resources

​		static           保存所有的静态资源 js css images 

​		templates  保存所有的的模板页面 （springboot默认jar包使用嵌入式tomcat 默认不支持jsp）

​						     可以使用模板引擎（thymeleaf freemarker）

application.properties  springboot  可以修改一些配置文件



## 4 SpringBoot配置文件

springboot使用一个全局配置文件 配置文件名称是固定的

 application.properties

 application.yml

配置文件的作用 ：修改springboot自动配置的默认值，springboot在底层都给我们自动配置好了



### YML语法

key -value 以空格来控制层级关系 只要是左对齐的，都是同一个层级。

```yml
server
	port: 8081
	path: /hahaha
```

属性和值 大小写敏感



### 配置文件注入

#### @ConfigurationProperties

配置文件 application.yml写入配置

```yaml
person:
  name: boniu
  age: 22
  boss: false
  birthday: 1998/01/14
  maps: {k1: v1,k2: 12}
  lists:
    - lisi
    - chaoren
    - xiedajiao
  dog:
    name: 小狗
    age: 2

```

在Person类中加入

```java
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
}
```

@Component  可以把普通pojo实例化到spring容器中，就是说把这个类交给Spring管理，

​							因为不清楚这个类是属于哪个层面，所以就用@Component。 

@ConfigurationProperties(prefix = "person") prefix 扫描对应配置文件中 名为person 的内容 



需要在pom.xml导入配置文件处理器 配置文件绑定就会有提示

```xml
<dependency>   
    <groupId>org.springframework.boot</groupId>   
    <artifactId>spring-boot-configuration-processor</artifactId>    <optional>true</optional>
</dependency>
```

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class PterosauriaApplicationTests {

    @Autowired
    Person person;

    @Test
    public void contextLoads(){

        System.out.println(person);
    }
}

```

在springtest中注入 person 就可以获得person对象 

或者是 类属性   

@Autowired
    Person person;

即可获得 被配置文件中赋值的 这个对象



#### @value

也可以通过 @value赋值 

```yaml
cat: name: 狸花猫 
	 age: 3
```

```
@Component
public class Cat {   
    @Value("${cat.name}")
    private String name;
    @Value("${cat.age}")
    private int age;
}
```

即可将对应的 对象 注入到spring容器中 

```
@Autowired
private Cat cat;

@RequestMapping("/hellocat")
    @ResponseBody
    public String hellocat (){
        System.out.println(cat);
        return "hellobobo"+cat.getName();
    }
```



#### 两者区别

|                       | @ConfigurationProperties | @value     |
| --------------------- | ------------------------ | ---------- |
| 功能                  | 批量注入配置文件的属性   | 一个个配置 |
| 数据校验              | 有效                     | 无效       |
| SpEL                  | 不支持                   | 支持       |
| 复杂封装类型（map等） | 支持                     | 不支持     |

数据校验

```java
@Component
@ConfigurationProperties(prefix = "person")
@Validatedpublic class Person {  
@Email    private String name;
}
```

可以 判断 name是否为email格式 如果不是就会报错



SpEL

spring EL表达式 可以做一些计算

```
@Value("#{2*10}")
private int age;
```

如果说 我们只是在某个业务逻辑中获取一下配置文件中的某个值 推荐使用 @value注解 

如果说 我们专门编写了一个javabean来合配置文件进行映射  我们直接使用@ConfigurationProperties

#### @PropertiesSource & ImportResource

@PropertiesSource 加载指定的配置文件 

​    @ConfigurationProperties 这个注解只从默认的配置文件导入 默认配置文件东西不能太多 



@ImportResource

​		到入spring配置文件 让配置文件生效 

​		springboot中没有默认的spring配置文件，自己编写也不能自动生效 

​		想让spring配置文件 生效，加载进来 ，需要在主配置类上加载@ImportResource，既可以加载配置文件。

主配置类注解

```
@ImportResource(locations = {"classpath:service.xml"})
```

```
<?xml version="1.0" encoding="UTF-8"?><beans xmlns="http://www.springframework.org/schema/beans"       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">        <bean id="springservice" class="com.bozai.Service.HelloService"></bean></beans>
```

spring配置文件 注入一个对象 springservice



### springboot 推荐 给容器中添加组件的方式 

1 配置类 spring 配置类

 新建一个 config类  添加注解@configuration

新建方法 添加@bean注解

​		方法的返回值 将被添加至容器中

​		方法的方法名 为对象的id

```
@Configuration
public class MyAppConfig {
    //默认的对象id 为方法名 将方法的返回值添加到容器中 
    @Bean
    public HelloService springService(){
        System.out.println("给容器中加入 这个对象 ");
        return new HelloService();
    }
}
```

### 配置文件 （不同开发环境配置）profile

#### 1 多profile文件

我们在主配置文件编写时 文件名可以是application-(profile).properties.yml

默认使用application.yml 文件 

#### 2 激活指定profile

application.yml 

```
spring:  
	profiles:   
		active: dev
```

当谢了这个注解后，application-dev.yml将被当做主配置文件执行



#### 3 yml 文档块

yml 文档中可以用符号  --- 三个横杠 分很多document 还是用上述命令激活 

 

```
server:
  port: 8081
spring:
  profiles:
    active: test
---


server:
  port: 8082
spring:
  profiles: dev


---
server:
  port: 8083
spring:
  profiles: test


---
```

#### 4 命令行激活

 --spring.profiles.active =dev  在项目启动配置里面edit里面 

#### 5 打包时修改 

可以在直接测试时 使用命令行参数 java -jar xxxx.jar --spring.profiles.active=dev

虚拟机参数 -Dspring.profiles.active = dev 

### 配置文件的加载位置

 -file:./config/     （和项目 同级下的config文件 中配置文件）

-file:./                    （和项目同级配置文件 和pom.xml 同级）

-classpath:/config   （resource文件夹中 config文件夹中 配置文件）

-classpath:/                  （springboot中的默认的配置文件的位置）

 优先级由高到低配置，高优先级覆盖低优先级。

但是springboot会从这四个位置全部加载配置文件，互补配置。



打成jar包后  java-jar  xxx.jar --spring.config.location=C:/application.yml 

可以指定使用外部的配置文件，和里面的配置文件互补使用，不需要重新打成jar包重新部署啦

也可以直接 命令行修改java-jar  xxx.jar --server.port = 8088



### 自动配置原理

1、springboot启动时 加载了主配置类，开启了自动配置的功能。@EnableAutoConfiguration/

2、@EnableAutoConfiguration 利用EnableAutoConfigurationImportSelector给容器中导入一些组件

3		查看 selectImports()方法的内容

4 将类路径下的META-INF/		spring.factories里面配置的所有的EnableAutoConfiguration的值配置到容器中

   每一个 xxxAutoConfiguration类都是容器中的一个组件，加入到容器中，用他们来做自动配置。

   以HttpEncodingAutoConfiguration为例解释自动配置原理

```java

@Configuration         //表示这是一个配置类，和以前编写的配置文件一样，可以给容器中添加组件
@EnableConfigurationProperties(HttpProperties.class)   //启动指定的类的configurationProperties功能 将配置文件的对应的值 和httppecodingproperties绑定起来

@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//spring condition注解 判断当前应用是否为web应用 如果是 生效 如果不是 就不生效

@ConditionalOnClass(CharacterEncodingFilter.class)
//判断当前项目有没有 CharacterEncdingFilter类 
//springmvc 乱码解决过滤器 
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)
//判断配置文件中是否存在某个配置 spring.http.enabled 如果不存在 也是默认生效
public class HttpEncodingAutoConfiguration {
    //他已经和springboot
    private final HttpProperties.Encoding properties;
//只有一个有参构造器     
    public HttpEncodingAutoConfiguration(HttpProperties properties) {
		this.properties = properties.getEncoding();
	}

    @Bean                //给容器添加一个组件
	@ConditionalOnMissingBean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
		filter.setEncoding(this.properties.getCharset().name());
		filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
		filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
		return filter;
	}
}
```

 根据当前不同的条件判断，决定这个配置类是否生效。

如果生效，这个配置类给容器中添加各组件，这些组件的属性是从对应的 properties中获取的 

这些类的每一个属性，又是和配置文件绑定的。

5 所有的配置文件 中能配置的属性都能在xxx.properties类的封装中找到,配置文件需要什么功能，就可以参照这个类的属性。

```java
@ConfigurationProperties(prefix = "spring.http")
public class HttpProperties {
```



精髓 

  1 springboot启动会加载大量的自动配置类

 2看有没有启动我们需要的功能有没有springboot默认写好的自动配置类

 3 给容器中自动配置类添加组件时 会从properties类中获取某些属性，可以在配置文件中指定配置的值。

xxxAutoConfiguration 自动配置类 给容器中添加组件 

xxxProperties 封装文件中相关属性 



细节

@conditonal 必须指定的条件生效 才允许容器中加载组件 ，配置内容才生效。

@ConditionalOnMissingBean 容器中没有这个组件 

@ConditionalOnjava 系统的java版本 是否符合要求

@ConditionalBean 容器中指定存在组件 



自动配置类必须在一定的条件下才能生效 ，

如何知道哪些自动配置类生效了 

配置文件中 debug  = true  开启springboot的debug模式 告诉我们哪些组件启动了



```java

Positive matches:
-----------------

   CodecsAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.http.codec.CodecConfigurer' (OnClassCondition)

   CodecsAutoConfiguration.JacksonCodecConfiguration matched:
      - @ConditionalOnClass found required class 'com.fasterxml.jackson.databind.ObjectMapper' (OnClassCondition)
```

```
Negative matches:
-----------------

   ActiveMQAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'javax.jms.ConnectionFactory' (OnClassCondition)

   AopAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.aspectj.lang.annotation.Aspect' (OnClassCondition)

```

debug模式帮你分析 哪些组件启用 哪些没有

## 5 druid+mybatis

### druid

1 配置druid连接池

 引入pom.xml

```xml
 <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.14</version>
    </dependency>
```

2 application.yml 中配置 druid的属性

```yml
spring:
  datasource:
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    url: jdbc:mysql://localhost:3306/rushroom
#    数据源基本配置

#    数据源其他配置

    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true

```

3 写一个配置类 druidconfig 配置

```java
package com.bozai.pterosaur.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        System.out.println("druid");
        return  new DruidDataSource();
    }

    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认就是允许所有访问
//        initParams.put("deny","192.168.15.21");

        bean.setInitParameters(initParams);
        return bean;
    }


    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return  bean;
    }
}

```

4 localhost:8080/druid 访问 druid 的配置



### mybatis



1 pom文件写依赖

```xml
 <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.1</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.47</version>
    </dependency>
```



2 写配置的mapper接口

```java


@Mapper
public interface boyMapper {

    @Select("select * from boy where id = #{id}")
    public boy selectboybyid(int id);
}

```



3 启动类中加入注解 扫描mapper包

```java
@SpringBootApplication
@MapperScan("com.bozai.springboot.mapper")
public class PterosaurApplication {

    public static void main(String[] args) {
        SpringApplication.run(PterosaurApplication.class, args);
    }

}
```

## 6 缓存



1spring 默认的缓存 

 coachmanager 来管理缓存 生成coach 储存缓存，每次调用时，manager会检测是否有对应名字的coach，如果没有则生成对应的coach，并存入key-value。如果存在则去对应的coach中查找key， 如果存在key 则返回value ，不进入方法执行，如果不存在则 执行对应的方法并把相应的值存入缓存中。

使用注解

@coachable(name="") 缓存在 name = ""的缓存coach中 ，默认的key为传入的参数 值为 返回值



@coachPut  将方法执行的返回值存入缓存，但仍要执行对应的方法。

@catchEvict 将方法对应的缓存 从缓存中删除，以后无法调用相应的缓存。



2 redis

基本数据类型

string（字符串)

hash(hash键值对) 

list(数组 有序)

 set(集合 无序)

 zset(集合 有序)

springboot 整合redis

1 引入redis starter 

2 系统就会默认使用rediscoachmanager 来管理缓存

3 redis存储对象类型的数据需要序列化，默认使用jdk序列化，可以根据自己的需求更改序列化方式，更改rediscoachmanager将对象改为json类型。



## 7 springcloud

### 1 注册中心 

​	eureka     ap 好死不如赖活着 有一个保护机制

​	zookeeper 	cp 保证数据一致性 如果有微服务不存在 直接就挂掉

​	consul			cp

​		原理就是	httpclient 封装 

CAP  c 高一致性

​		a 高可用性

​		p 分区容错性

### 2  ribbon 

spring cloud ribbon 是基于 netflix ribbon 实现的一套客户端 负载聚恒的工具 



ribbon 和nginx 服务端负载均衡的区别

nginx 是服务器负载军训 客户端的所有请求都交给nginx  然后由nginx 实现转发请求 即负载君合是由服务端实现的

ribbon 本地负载均衡，在调用服务接口的时候，会在注册中心上获取注册信息服务列表之后，返回到jvm本地，从而在本地实现 RPC远程服务器调用 

eureka的pom 包里 也引入了ribbon

### 一句话 负载均衡 +**RestTemplate**





负载均衡算法

​	1 轮训算法 请求数/service集群数 取余 判断用第几台

​	2  



### openfeign 使编写javahttp客户端更加容易

在ribbon 中 我们是用 ribbon+resttemplate 对请求进行封装 ，形成一套模板式的调用方法，但接口往往会被多次调用，feign通过注解的方式 绑定 服务，类似于 dao接口上的mapper 注解，减少了开发量。

feign继承了ribbon 

利用ribbon 维护了payment的服务列表信息，并通过轮训实现了客户端的负载均衡。



openfeign = ribbon + restTemplat每次
客户端 调用 服务端的接口的时候 ，原来使用的是resttemplate +ribbon  实现调用和负载均衡 ，现在 我们将调用的 接口  加service注解，直接调用service 服务 就可以调用方法。

 如果请求 超时的话，feign 也集成了 ribbon功能，通过底层功能，feign默认设置为1s 如果请求不到就会报错，可以设置 请求的时间限制。



### hystrix 

​	多个微服务互相调用，如果出现长时间未响应，或者不可用，就会占用越来越多的系统资源。引起系统雪崩。



对于高流量来说，单一后端调用，可能会导致所有服务器上的所有资源在几秒钟饱和，导致系统发生级联故障。

hystrix 可以保证 一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障。

保险丝！



服务降级  fallback

​	设置 反应的时间 

​	设置  服务器运行异常 

​		设置一个兜底的方法 ，就是如果别的出错 ，那么走默认的 方法，实现消费降级。

服务端 可以 进行服务降级 

客户端也可以 进行服务降级  如果客户端也出现问题 

每个业务配置一个 降级方法

也可以 多个业务 用同一个降级方法 只有在某些特别的业务使用 特殊的方法

运行时异常 超时异常 宕机异常

![1585243317383](https://gitee.com/guoguoguoguo101/pic/raw/master/1585243317383.png)



服务熔断

​	熔断机制

​	对雪崩效应的一种微服务链路裱糊机制当某个微服务不可应或者相应时间太长 汇景轩服务的降级 进而熔断该节点的微服务调用 快速返回错误的相应信息 当检测到该节点微服务相应政策后 恢复调用链路

​	在springcloud 框架中 熔断机制通过hystrix实现 hystrix 会监控微服务之间调用的状况 当失败的调用到达一定的阈值  就会启动熔断机制注解是@HystrixCommond![1585243807385](https://gitee.com/guoguoguoguo101/pic/raw/master/1585243807385.png)

closed  open  half open 



设置熔断的 判断  

​		再 失败次数太多 成功率太低 就会开启熔断 ，进行服务降级， 部分请求还是会访问 如果成功的话 熔断恢复![1585245263977](https://gitee.com/guoguoguoguo101/pic/raw/master/1585245263977.png)



服务限流

hystrix 的可视化实现

配置一个hystrix可视化 微服务

可以看到每个微服务的调用情况 访问的次数出错次数 是否熔断等等



网管 

zuul和getway

zuul是一个基于阻塞的IO api getway 每次工作都去找一个线程去执行，类似于tomecat线程池 

当一个请求发送过来 ，从tomecat线程池中 找到一个线程来对这个请求进行初始化 调用 效率较低![1585373574892](https://gitee.com/guoguoguoguo101/pic/raw/master/1585373574892.png)

springcloud getway是一个基于非阻塞的 webflux + netty









明天要看的内容

​	锁 cas 自旋锁 原子锁 synchronize lock 锁！看完！ http 解决无状态 tcp ip 看完

​	spring 事务隔离的等级 require

​	

​							![1585373733969](https://gitee.com/guoguoguoguo101/pic/raw/master/1585373733969.png)

​	三大核心概念 路由 构建网管的基本模块 id url  如果为true则去访问

断言 http请求的所有内容 请求头和请求参数 如果与断言匹配 则可以进行路由

过滤器 对请求的路由前后对请求进行修改



web请求，匹配一定的条件，跳转到真正的服务节点 ，并在这个转发过程的额前后，进行一些精细化控制。

predicate 断言 就是匹配条件， filter就是一个拦截器，有了这两个元素 加上目标url就可以实现一个基本的路由。

pre

过滤器可以在请求到达之前进行过滤进行参数校验 流量控制 日志输出 协议转换

post  在服务器返回数据之后 也可以过滤相应内容 响应头修改 日志输出 流量监控等



总结 实现路由转发 执行过滤链



可以干那些工作

网关配置

​	1 隐藏自己的端口号 原来的 是

localhost：8081/payment/31

我们可以隐藏并更换端口号 在配置文件里面 加入 端口号和断言规则

​	server 9527

​	cloud 

​		getway 

​				routes: id payment_routh

​							url http://localhost:8001

​							predicates path=/payment/get/** 断言

当访问http 9527端口的时候 访问payment/get 请求 符合断言 就会访问真实的 端口号的地方



也通过 bean 

断言的配置 不仅可以配置path地址 也可以

before after between配置时间，什么时候之后可以访问 之前可以访问

cookie 匹配cookie

http请求头

请求方法登登登



filter 可以自定义配置一些过滤 



config 

总配置服务中心 springcloud config 提供了一个 外部的 配置中心

![1585378360028](https://gitee.com/guoguoguoguo101/pic/raw/master/1585378360028.png)



配置一个web 端 可以通过git查看配置文件 微服务

也需要配置一个本地端的微服务

需要配置 两个  一个github 端 一个 本地客户端 

（curl -X POST "http://localhost:3355/actuator/refresh"）

可以实现动态刷新 

spring cloud bus 

![1585408433481](https://gitee.com/guoguoguoguo101/pic/raw/master/1585408433481.png)

消息总线

微服务架构中 通常使用轻量级的消息代理 来构建一个共同的消息主题让系统中的所有微服务实例都连接上来，由于该主题中产生的消息会被所有实例监听和消费，成为消息总线。



原理

configclient实例都去监听mq中的一个topic 当一个服务刷新数据，放到topic中，其他的监听同一topic的服务就能得到通知，然后更新自身的配置。









压力测试 jmeter 

crul 也可以测试 cmd 里面的

spring  aop  ioc  

​	数据库调优 

 hashmap 

链表的题 排序的题 树的题 

​	