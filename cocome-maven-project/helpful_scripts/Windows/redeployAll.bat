#!/bin/bash


:: !!!!WINDOWS ONLY!!!!!
:: !!!!!!!!!Change the SET Locations to your destination folders/files!!!!!!!!
REM set environment variables
::--------------------------------------------------------------------------------------------------------------------------------------
SET cocome_settings=C:\Users\  path to cocome settings        \settings.xml
SET cocome_pom=C:\Users\   path tococome pom                   \pom.xml

SET cocome_adapter_settings=C:\Users\ path to adapter settings                \settings.xml
SET cocome_adapter_pom=C:\Users\  path to adapter pom              \pom.xml

SET cocome_cli=C:\Users\path to client                 \cocome-maven-project\cloud-cli-frontend

SET glassfish_home=C:\Users\  path to glassfish       \glassfish5\bin
SET glassfish_domain=C:\Users\ path to glassfissh domains                \domains
::--------------------------------------------------------------------------------------------------------------------------------------


::------------------------------------(re-)deploy adapter------------------------------------------------------------------------------
cd %glassfish_home%

::start to execute clean --> this will wipe out the database
call asadmin start-domain adapter & 

call mvn -s %cocome_adapter_settings% clean post-clean -f %cocome_adapter_pom% &

call asadmin stop-domain adapter & 

cd %glassfish_domain%

::wipe out old data
del /S /Q .\adapter\applications\* &
FOR /D %%p IN (".\adapter\applications\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\adapter\generated\* &
FOR /D %%p IN (".\adapter\generated\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\adapter\osgi-cache\* &
FOR /D %%p IN (".\adapter\osgi-cache\*.*") DO rmdir "%%p" /s /q &

cd %glassfish_home%
 
call asadmin start-domain adapter & 
::undeploy if adapter is still in cache
call asadmin undeploy service-adapter-ear --port 8248 &
::deploy 
call mvn -s %cocome_adapter_settings% install -f %cocome_adapter_pom% -DskipTests

REM (re-)deployment adapter finished



::------------------------------------(re-deploy cocome)------------------------------------------------------------------------------
::stop domains if there are still running
cd %glassfish_home%
call asadmin stop-domain registry & 
call asadmin stop-domain web & 
call asadmin stop-domain store & 
call asadmin stop-domain enterprise & 
call asadmin stop-domain plant & 



cd %glassfish_domain%
::wipe out old data
del /S /Q .\enterprise\applications\* &
FOR /D %%p IN (".\enterprise\applications\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\enterprise\generated\* &
FOR /D %%p IN (".\enterprise\generated\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\enterprise\osgi-cache\* &
FOR /D %%p IN (".\enterprise\osgi-cache\*.*") DO rmdir "%%p" /s /q &

del /S /Q .\registry\applications\* &
FOR /D %%p IN (".\registry\applications\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\registry\generated\* &
FOR /D %%p IN (".\registry\generated\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\registry\osgi-cache\* &
FOR /D %%p IN (".\registry\osgi-cache\*.*") DO rmdir "%%p" /s /q &

del /S /Q .\store\applications\* &
FOR /D %%p IN (".\store\applications\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\store\generated\* &
FOR /D %%p IN (".\store\generated\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\store\osgi-cache\* &
FOR /D %%p IN (".\store\osgi-cache\*.*") DO rmdir "%%p" /s /q &

del /S /Q .\web\applications\* &
FOR /D %%p IN (".\web\applications\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\web\generated\* &
FOR /D %%p IN (".\web\generated\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\web\osgi-cache\* &
FOR /D %%p IN (".\web\osgi-cache\*.*") DO rmdir "%%p" /s /q &

del /S /Q .\plant\applications\* &
FOR /D %%p IN (".\plant\applications\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\plant\generated\* &
FOR /D %%p IN (".\plant\generated\*.*") DO rmdir "%%p" /s /q &
del /S /Q .\plant\osgi-cache\* &
FOR /D %%p IN (".\plant\osgi-cache\*.*") DO rmdir "%%p" /s /q &


cd %glassfish_home%
call asadmin start-domain registry & 
call asadmin start-domain web & 
call asadmin start-domain store & 
call asadmin start-domain enterprise & 
call asadmin start-domain plant & 

::undeploy if jar-files are still in glassfish cache
call asadmin undeploy store-logic-ear --port 8148 &
call asadmin undeploy cloud-registry-service --port 8448 &
call asadmin undeploy enterprise-logic-ear --port 8348 &
call asadmin undeploy plant-logic-ear --port 8548 &
call asadmin undeploy cloud-web-frontend --port 8048 &

::build and deploy cocome
call mvn -s %cocome_settings% install -f %cocome_pom% -DskipTests

::------------------------------------fill database------------------------------------------------------------------------------
call mvn clean install
call mvn exec:java -f %cocome_cli%
REM database filled

echo Redeployment successful if mvn build was successfull!!!!!!
pause
