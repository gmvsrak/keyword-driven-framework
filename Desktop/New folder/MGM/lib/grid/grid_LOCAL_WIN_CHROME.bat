java -jar selenium-server-standalone-2.48.2.jar -role node -Dwebdriver.chrome.driver=..\..\drivers\chromedriver.exe -port 8992 -hub http://localhost:4444/grid/register -browser "browserName=chrome,version=48.0.2564.109,maxInstance=2,platform=WINDOWS" -hubHost localhost

