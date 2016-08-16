start java -jar selenium-server-standalone-2.48.2.jar -role hub
timeout /t 4
start java -jar selenium-server-standalone-2.48.2.jar -role node -Dwebdriver.ie.driver=..\..\drivers\IEDriverServer.exe -port 8990 -hub http://localhost:4444/grid/register -browser "browserName=internet explorer,version=11,maxInstance=1,platform=WINDOWS" -hubHost localhost 
timeout /t 4
start java -jar selenium-server-standalone-2.48.2.jar -role node -port 8991 -hub http://localhost:4444/grid/register -browser "browserName=firefox,version=43.0.1,maxInstance=2,platform=WINDOWS" -hubHost localhost
timeout /t 4
start java -jar selenium-server-standalone-2.48.2.jar -role node -Dwebdriver.chrome.driver=..\..\drivers\chromedriver.exe -port 8992 -hub http://localhost:4444/grid/register -browser "browserName=chrome,version=48.0.2564.109,maxInstance=2,platform=WINDOWS" -hubHost localhost

