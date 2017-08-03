cd config
call mvn clean 
call mvn install -DSkipTests
cd..
cd discovery
call mvn clean 
call mvn install -DSkipTests