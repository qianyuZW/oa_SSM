# oa_SSM
Office Automation implemented with SSM frame
本项目基于SSM框架实现办公自动化，主要功能包括：签到，日报，周计划，里程计算，邮件收发。整个项目不大，但五脏俱全。
提醒：
clone到本地后，修改配置文件dev.propeties变成下面这样
spring.datasource.url=jdbc:mysql://IP:3306/oa?characterEncoding=utf8&useUnicode=true&amp
spring.datasource.username=你的数据库名字
spring.datasource.password=你的数据库密码
按照mapper和model重新数据库，应该就可以测试功能
