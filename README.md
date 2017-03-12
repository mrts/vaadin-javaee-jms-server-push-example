# vaadin-javaee-jms-server-push-example

An example Vaadin 8 Java EE application that demonstrates how to use Vaadin's
server push and Java EE messaging APIs to send messages to client browsers in
real time. Uses JMS context API for sending messages, message-driven beans for
receiving them and a separate singleton broadcaster to broadcast messages to
Vaadin UIs (the broadcaster is recommended by Vaadin as a bridge for connecting
message-driven beans and Vaadin UIs that have different lifetimes). Vaadin's
server push uses WebSockets by default.

Tested with WildFly 10.1, but should work equally well with other Java EE 7
application servers (and probably Java EE 6 application servers once you
downgrade the `javaee-api` Maven dependency; you may also need to change JMS
configuration).

## Building and running

You need Maven and Java 8 JDK to build and run the application.

Build the application WAR with `mvn package`, deploy it from
`target/vaadin-javaee-server-push-example-1.0-SNAPSHOT.war` to the application
server and open
<http://localhost:8080/vaadin-javaee-server-push-example-1.0-SNAPSHOT.war/> in
the browser.

## References

- [Server Push chapter in official Vaadin documentation](https://vaadin.com/docs/-/part/framework/advanced/advanced-push.html)

