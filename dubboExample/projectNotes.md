## 项目遇到的一些小问题记录

1. 修改log4j的log文件随便打印在各个路径下的问题。（包含项目根路径下+D盘中）

   - 在`log4j.properties`文件里没有设置`LOG_PATH`和`LOG_FILE`字段值。

2. 理解了`classpath`是什么。

   - classpath在这里指的是Java项目编译后的目录即项目的target\classes目录。

   - `@ImportResource(value = {"classpath:spring/spring-jdbc.xml","classpath:spring/spring-dubbo.xml"})`

     通过`System.out.println(System.getProperty("java.class.path"));`得到了

     classpath： D:\Coding\IDEA\Workspace\dubboExample\sever\target\classes

3. MySQL，MySQL-connector，druid三者包版本的一致问题。

4. 解决了一个server模块引入api模块的问题。

   maven配置中`groupId`写错，还有dependency中引入了错误的自己的模块。全部写对之后还是不行，运行了`mvn-clean`命令+IDEA的`invalidate caches/restart`命令清除缓存之后就可以了。

5. 解决了什么叫mybatis的逆向工程。

   最后还是没有去了解，直接复制粘贴了课程代码。、

6. 解决了`No data sources are configured to run this SQL`，把MySQL加入到IDEA的数据库中去。

7. 解决了`<statement> or DELIMITER expected, got 'id'`，删除了校验。

8. 解决了` No enum constant org.apache.ibatis.type.JdbcType.Integer`。

   Mybatis中的JdbcType为枚举类型，只有特定的几种类型且**区分大小写**。

9. `path`解决了一个接口有多个实现类的情况下，调用方调用了接口具体实际上调用的是哪个实现类的问题。

10. 解决了一个端口被占用的问题。

    - 根据报错信息`Failed to initialize connector [Connector[HTTP/1.1-9013]]`知道是9013端口启动失败。
    - 去项目文件中知道了9013端口是dubbo HTTP rest协议的端口。
    - cmd中用命令`netstat -aon|findstr "9013"`知道了占用9013端口的进程的PID
    - 打开任务管理器，根据PID找到对应的进程，发现是内网通。
    - 找一个没被使用的端口，修改dubbo端口。

11. 解决了`org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)`问题。

    问题原因：mybatis中dao接口与mapper配置文件在做映射绑定时出现了问题。要么接口或XML找不到，要么找到了却匹配不到。

    - XML文件所在package名称(mapper namespace)是否与interface所在包名一致。（本例出错原因）

12. `required a bean of type 'xxx' that could not be found.`问题

    这里是service无法发现，找来找去也写了`@Service`，后来发现是`@Service`引错了包。应该是`org.springframework.stereotype.Service;`。