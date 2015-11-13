@echo off
setLocal EnableDelayedExpansion
set CLASSPATH="
for /R . %%a in (*.jar) do (
	set CLASSPATH=!CLASSPATH!;%%a
)
FOR /D /r %%a IN (*) DO (
	set CLASSPATH=!CLASSPATH!;%%a
) 
set CLASSPATH=!CLASSPATH!"
 

java -cp !CLASSPATH! hu.bme.mit.v37zen.prepayment.application.datasync.DataSyncApplication etc/datasync.config >log/datasync.out 2>&1