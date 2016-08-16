start java -jar selenium-server-standalone-2.48.2.jar -role hub
timeout /t 3
start java -jar selenium-server-standalone-2.48.2.jar -role node -Dwebdriver.ie.driver=..\..\drivers\IEDriverServer.exe  -Dwebdriver.chrome.driver=..\..\drivers\chromedriver.exe -nodeConfig DefaultNode_LOCAL_WIN.json