@echo off
@
@rem This will start a cache server
@
SET JAVA_HOME=C:\oracle\jdk1.7.0_03
SET WORKSPACE=C:\projecten\workspace\11gR2\Coherence_ADF_BC\ModelJPA\classes
SET JDEV_HOME=C:\oracle\JDevR2


setlocal

:config
@rem specify the Coherence installation directory
set coherence_home=%~dp0\..

@rem specify the JVM heap size
set memory=512m


:start
if not exist "%coherence_home%\lib\coherence.jar" goto instructions

if "%java_home%"=="" (set java_exec=java) else (set java_exec=%java_home%\bin\java)


:launch

set java_opts="-Xms%memory% -Xmx%memory% -Dtangosol.coherence.cacheconfig=%coherence_home%\bin\jpa-cache-config-web.xml"

"%java_exec%" -server -showversion "%java_opts%" -cp "%coherence_home%\lib\coherence.jar;%coherence_home%\lib\coherence-jpa.jar;%JDEV_HOME%\wlserver_10.3\server\lib\ojdbc6.jar;%JDEV_HOME%\modules\com.oracle.toplink_1.0.0.0_11-1-1-5-0.jar;%JDEV_HOME%\modules\org.eclipse.persistence_1.1.0.0_2-1.jar;%JDEV_HOME%\modules\javax.persistence_1.0.0.0_2-0-0.jar;%JDEV_HOME%\modules\com.bea.core.antlr.runtime_2.7.7.jar;%WORKSPACE%" com.tangosol.net.DefaultCacheServer %1

goto exit

:instructions

echo Usage:
echo   ^<coherence_home^>\bin\cache-server.cmd
goto exit

:exit
endlocal
@echo on