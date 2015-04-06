#Intro
This is a sample project that evaluates Java Websockets usage with Guice. AngularJS is used for browser side stuff.

##Usage
Before anything, you should run `bower install` at folder angularjs-client/app. The project uses [grunt](http://gruntjs.com/) for managing the angularjs client app and [maven](http://maven.apache.org/) for managing the server side app.
Use `grunt develop` to start the development session on the files under the angularjs-client directory. Whenever you'll change a file grunt will copy the changed file under src/main/webapp directory. Hence you could use `mvn tomcat7:run` to run the embedded maven tomcat 7 server (but websockets won't work) and to navigate the app under [http://localhost:8080/server2/app/index.html](http://localhost:8080/server2/app/index.html)

##Deployment
Deployment is easy. Just edit server.properties file for proper values. Set `actAs` value to `server1` or `server2`. Depending on that value, Guice will inject different dependecies at runtime. When you are done with configs, run `mvn clean compile war:war` and your war file will be ready under `target` folder. 
