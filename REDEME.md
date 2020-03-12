项目概述：设计出一个适合用于毕业设计时导师和学生可以双向选择的管理系统

项目需求：
导师：1、可以设定特定课程的最低分，权重
2、可以设置实际指导学生数
3、可以查看符合自己条件的学生范围
4、查看当前已接受学生
5、各项参数均可修改

学生：输入学号可以查看自己曾经选修的课程

项目环境：（暂定）jdk11，springboot、spring、mysql


demo1:完成数据库设计：
导师 teacher：int id、string name、int totalStudents、list<integer> SuitableStudents（学生id）、list<course> courses、list<student> students、list<direction> directions	

课程 Course：int id、string coursename、 lowestScore、weight、Teacher teacher、list<selectedCourses> courses

学生 Student: int stuNo、string name、Teacher teacher、list<expectionDirections> directions、list<selectedCourses> courses

选课（中间类） selectedCourses course：int id、score、Student student（foreign）、Course course（foreign）

方向 direction：int id、string detail、Teacher teacher、list<expectionDirection> directions

学生未来意愿（中间） expectionDirection：int id、Student student（foreign）、Direction direction（foreign）

各实体关系：
导师课程：1-N
导师学生：1-N
导师方向：1-N
课程学生：N-N（选课为中间类，学生-选课1-N，课程-选课1-N）
学生方向：N-N（学生未来意愿为中间类，学生-意愿1-N，方向-意愿1-N）

bug1：发现设计的list<integer>类型无法使用jpa插入数据库
