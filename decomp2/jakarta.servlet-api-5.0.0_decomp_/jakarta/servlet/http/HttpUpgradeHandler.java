package jakarta.servlet.http;

public interface HttpUpgradeHandler {
   void init(WebConnection var1);

   void destroy();
}
