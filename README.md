This is a project automated testing running on browser Chrome of Android phone with app demo banking


### Before Run Test

1.  *** IMPORTANT: The current scripts is run without start Appium Server manually. Test will be failed if existing an Appium Server instance before running.
       If you don't want to use this function, please go to file "\src\test\java\TestRunner.java" and comment out <startAppiumServer();> line
       
    -> How to use on other computer ?
	    Go to file AppiumServerController.java following path "\src\main\java\core\server\AppiumServerController.java"
	    and change value of <appiumJSPath> variable (currently is "C:/Users/quoctran/AppData/Local/Programs/Appium/resources/app/node_modules/appium/build/lib/main.js";)
	    to your associate path, because this value is not the same on other computers.		
2.  In file "AppiumServer.properties", change <appium.platformVersion> and <appium.deviceName> to your desired values.	
    You can change other properties if needed.
3.  Have an android emulator or device connected and running
3.  ANDROID_HOME environment varialbe to be set as "C:\Users\<user_name>\AppData\Local\Android\Sdk"


### How To Run

mvn test
