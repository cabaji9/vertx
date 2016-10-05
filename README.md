# vertx
Basic Vertx backend with cors.

This is a Vert.x web server with a simple rest to verify user. 

This project compiled with maven, and java. Vert.x allows any language. 
It has cors enabled to allow js frameworks to call this withouth the error 404 and 403. Specially with ajax.

To run it just compile it with mvn clean install.

With the jar created in target run:

java -jar back-rest-1.0-SNAPSHOT-fat.jar

This creates a web server with a rest service enabled and you can test it with:

curl --data "user=marcelo@mail.com&password="dasfasf"" http://localhost:8081/api/lgnusr

If you want to debug this jar you can add this:

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 -jar back-rest-1.0-SNAPSHOT-fat.jar

1044 could be any port.

This reads from a json used as a temporal datastore, uses Gson. Then verify the password with sha512.



