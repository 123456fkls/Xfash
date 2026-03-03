XFsh员工管理系统 - 练手项目说明文档

===============================================

项目概述：
这是一个基于Spring Boot 3.5.7开发的员工管理系统练手项目，
主要用于学习和实践Java Web开发相关技术。

技术栈：
- 后端框架：Spring Boot 3.5.7
- 数据库：MySQL 8.0+
- ORM框架：MyBatis 3.0.5
- 分页插件：PageHelper 1.4.7
- 安全认证：JWT (jjwt 0.9.1)
- 文件存储：阿里云OSS
- 构建工具：Maven 3.8+

项目功能：
✅ 员工信息管理（增删改查）
✅ 部门信息管理
✅ 学生信息管理
✅ 班级信息管理
✅ 文件上传功能
✅ 操作日志记录
✅ JWT token认证
✅ 数据统计报表

运行环境要求：
- JDK 17+
- MySQL 8.0+
- Maven 3.6+

数据库配置：
1. 创建数据库：CREATE DATABASE xfash;
2. 执行数据库脚本初始化表结构
3. 修改application.yml中的数据库连接信息：
   url: jdbc:mysql://localhost:3306/xfash
   username: root
   password: 123456

项目启动步骤：
1. 克隆项目到本地
2. 确保MySQL服务已启动
3. 配置好数据库连接
4. 在项目根目录执行：mvn spring-boot:run
5. 访问地址：http://localhost:8080

API接口说明：
基础路径：http://localhost:8080

员工管理接口：
- GET    /emps          获取员工列表（分页）
- POST   /emps          新增员工
- GET    /emps/{id}     根据ID查询员工
- PUT    /emps          修改员工信息
- DELETE /emps          批量删除员工
- GET    /emps/list     获取所有员工

部门管理接口：
- GET    /depts         获取部门列表
- POST   /depts         新增部门
- GET    /depts/{id}    根据ID查询部门
- PUT    /depts         修改部门信息
- DELETE /depts         删除部门

学生管理接口：
- GET    /students      获取学生列表（分页）
- POST   /students      新增学生
- GET    /students/{id} 根据ID查询学生
- PUT    /students      修改学生信息
- DELETE /students/{id} 删除学生

班级管理接口：
- GET    /clazzs        获取班级列表（分页）
- POST   /clazzs        新增班级
- GET    /clazzs/{id}   根据ID查询班级
- PUT    /clazzs        修改班级信息
- DELETE /clazzs/{id}   删除班级

文件上传接口：
- POST   /upload        文件上传

登录认证：
- POST   /login         用户登录（返回JWT token）

注意事项：
⚠️ 此项目为练手用途，存在以下问题需要改进：
1. 密码为明文存储，实际项目中应使用BCrypt加密(已修复)
2. JWT密钥为硬编码，应从配置文件读取(已修复)
3. 缺少参数校验和输入过滤
4. 没有单元测试覆盖
5. 日志记录不够规范
6. 缺少异常统一处理

项目结构说明：
src/main/java/com/example/xfash/
├── Controller/     控制器层
├── Service/        服务层
├── Mapper/         数据访问层
├── pojo/           实体类
├── Utils/          工具类
├── Config/         配置类
├── Filter/         过滤器
├── Interceptor/    拦截器
├── Aop/            切面编程
├── Exception/      异常处理
└── Anno/           自定义注解

数据库表结构：
- emp: 员工表
- dept: 部门表  
- student: 学生表
- clazz: 班级表
- emp_expr: 员工工作经历表
- operate_log: 操作日志表

联系方式：
如有问题可在项目中提issue讨论学习。

更新日志：
2024-03-02: 初始版本发布，包含基本的CRUD功能和JWT认证
