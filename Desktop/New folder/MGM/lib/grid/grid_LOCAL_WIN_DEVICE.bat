java -jar selenium-server-standalone-2.48.2.jar -role node -Dwebdriver.chrome.driver=..\..\drivers\chromedriver.exe -port 8558 -hub http://localhost:4444/grid/register -browser "browserName=chrome,version=android,maxInstance=1,platform=WINDOWS" -hubHost localhost
