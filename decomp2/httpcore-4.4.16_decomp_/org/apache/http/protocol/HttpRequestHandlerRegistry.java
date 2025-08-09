package org.apache.http.protocol;

import java.util.Map;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;

/** @deprecated */
@Deprecated
@Contract(
   threading = ThreadingBehavior.SAFE
)
public class HttpRequestHandlerRegistry implements HttpRequestHandlerResolver {
   private final UriPatternMatcher matcher = new UriPatternMatcher();

   public void register(String pattern, HttpRequestHandler handler) {
      Args.notNull(pattern, "URI request pattern");
      Args.notNull(handler, "Request handler");
      this.matcher.register(pattern, handler);
   }

   public void unregister(String pattern) {
      this.matcher.unregister(pattern);
   }

   public void setHandlers(Map map) {
      this.matcher.setObjects(map);
   }

   public Map getHandlers() {
      return this.matcher.getObjects();
   }

   public HttpRequestHandler lookup(String requestURI) {
      return (HttpRequestHandler)this.matcher.lookup(requestURI);
   }
}
