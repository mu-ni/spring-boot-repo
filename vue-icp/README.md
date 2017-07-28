
# install vue-cli
~~~
npm install -g vue-cli
~~~

# init project
~~~
vue init webpack vue-icp
~~~

# run for debug
~~~
npm run dev
~~~

# build for production
~~~
npm run build
~~~

# deployment
~~~
1. copy content in /dist(/static & index.html)
2. paste to spring-boot project src\main\resources\public
3. run spring-boot project
~~~

# Install Weblogic
~~~
cd C:\Users\dexxis\Downloads\weblogic
java -jar fmw_12.2.1.2.0_wls.jar
~~~
# Start Weblogic
~~~
cd C:\Oracle\Middleware\Oracle_Home\user_projects\domains\base_domain
startWebLogic.cmd
localhost:7001/console
weblogc/Gemalto1
~~~
# Build Deployment War
~~~
cd C:\Users\nmu\workspace\icp-server
mvn clean package
~~~
# Deploy War
~~~
localhost:7001/console -> 部署 -> 安装 -> 完成
http://localhost:7001/icp-server-0.0.1
~~~
