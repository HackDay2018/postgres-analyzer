# postgres-dpa-analyzer
# Create postgres db 'analyzer'
```
spring.datasource.url=jdbc:postgresql://localhost:5433/analyzer
spring.datasource.username=postgres
spring.datasource.password=postgres
```

# Build project (skip tests - optional)
mvn clean install -DskipTests=true

# Start postgres analyzer, optionally add for debug
```bash
-Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```
# Note slashes backward for windows, normal for unix
```bash
mvn spring-boot:run -Dserver.port=8088 -Dinstance.conf=src\main\resources\application.properties -Dlog4j.configuration=file:conf\log4j.properties 
```

# Step 1. Send gather data for analysis request (Saves information about current tables schema)
```bash
$ curl -H "Content-Type: application/json" -X POST -d "[\"messages\",\"tableName2\"]" http://localhost:8088/api/v1/gatherDataForAnalysis
```
# response
```
{"status":"SUCCESS","analysisId":"IpKvs","statusCode":0,"successful":true}
```
# Step 2. Do smth with tables: perform upgrade or smth
# Step 3. Send perform schema analysis request (performs tables schemas diff with data saved on previous step)
```bash
$ curl -H "Content-Type: application/json" -X POST  http://localhost:8088/api/v1/analyze?analysisId=IpKvs
```
# response
```
{  
   "status":"SUCCESS",
   "analysisId":"HYwpv",
   "statusCode":0,
   "data":[  
      {  
         "oldVersion":1,
         "newVersion":2,
         "schemaUpdateStatus":"UPDATED",
         "columnAdded":[  

         ],
         "columnDeleted":[  
            {  
               "columnName":"created",
               "dataType":"timestamp without time zone"
            }
         ],
         "columnTypeChanged":[  

         ]
      }
   ],
   "successful":true
}
```