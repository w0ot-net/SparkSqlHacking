package py4j;

public class DefaultApplication {
   public static void main(String[] args) {
      GatewayServer server = new GatewayServer(new Object());
      server.start();
   }
}
