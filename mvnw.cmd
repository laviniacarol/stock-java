@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
@SET ___MVNW_JAVAEXE_=java.exe
@IF NOT "%JAVA_HOME%"=="" SET ___MVNW_JAVAEXE_=%JAVA_HOME%\bin\java.exe
@SET MAVEN_PROJECTBASEDIR=%~dp0
@IF NOT "%MAVEN_BASEDIR%"=="" SET MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%
@SET MAVEN_WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.jar
@SET MAVEN_WRAPPER_PROPERTIES=%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.properties

@FOR /F "tokens=2 delims== " %%G IN ('type "%MAVEN_WRAPPER_PROPERTIES%" ^| findstr /i "distributionUrl"') DO (
    @SET DISTRIBUTION_URL=%%G
)

@"%___MVNW_JAVAEXE_%" -jar "%MAVEN_WRAPPER_JAR%" %*
