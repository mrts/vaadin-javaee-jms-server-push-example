# vaadin-javaee-jms-server-push-example

An example Vaadin 8 Java EE application that demonstrates how to use Vaadin's
server push and Java EE messaging APIs to send messages to client browsers in
real time. Uses JMS 2.0 API for sending messages, message-driven beans for
receiving them and a separate singleton broadcaster to broadcast messages to
Vaadin UIs (the broadcaster is recommended by Vaadin as a bridge for connecting
message-driven beans and Vaadin UIs that have different lifetimes). Vaadin's
server push uses a WebSocket connection by default.

Tested with WildFly 10.1, but should work equally well with other Java EE 7
application servers (and probably Java EE 6 application servers once you
downgrade the `javaee-api` Maven dependency; you also need to change JMS APIs
and configuration).

## Building and running

You need Maven and Java 8 JDK to build and run the application.

Build the application WAR with `mvn package`, deploy it from
`target/vaadin-javaee-server-push-example-1.0-SNAPSHOT.war` to the application
server.

**Note that you need to run WildFly with full profile to enable JMS:**

    bin/standalone.{bat,sh} -c standalone-full.xml

## Testing

1. Open <http://localhost:8080/vaadin-javaee-server-push-example-1.0-SNAPSHOT/receive-message> in one browser window
2. Open <http://localhost:8080/vaadin-javaee-server-push-example-1.0-SNAPSHOT/send-message> in second browser window
3. Type a message and press *Send message*
4. Switch to the first window and observe that the message is visible on the page

There is also a Vaadin TestBench test in [SendAndReceiveMessageTest.java](src/test/java/com/serverpushexample/SendAndReceiveMessageTest.java).

If you have a TestBench licence and [Chrome WebDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads)
installed, you can run it with

    mvn test

## References

- [Server Push chapter in official Vaadin documentation](https://vaadin.com/docs/-/part/framework/advanced/advanced-push.html)

