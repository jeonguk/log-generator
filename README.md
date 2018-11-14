## log-generator

### Test Dummy log generator

```
Running Background
mvn clean install
cd target/
java -cp com.jeonguk.log-1.0-SNAPSHOT.jar com.jeonguk.LogGeneratorMain YYYYMMDD 3 &
```
 
```
Running Background
mvn clean install
cd target/
java -cp com.jeonguk.log-1.0-SNAPSHOT.jar com.jeonguk.LogMakerMain YYYYMMDD 3 &

tail -f log-file-name
```
