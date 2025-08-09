package org.sparkproject.jetty.client.api;

import java.net.URI;
import java.util.Map;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.StringUtil;

public interface Authentication {
   String ANY_REALM = "<<ANY_REALM>>";

   boolean matches(String var1, URI var2, String var3);

   Result authenticate(Request var1, ContentResponse var2, HeaderInfo var3, Attributes var4);

   public static class HeaderInfo {
      private final HttpHeader header;
      private final String type;
      private final Map params;

      public HeaderInfo(HttpHeader header, String type, Map params) throws IllegalArgumentException {
         this.header = header;
         this.type = type;
         this.params = params;
      }

      public String getType() {
         return this.type;
      }

      public String getRealm() {
         return (String)this.params.get("realm");
      }

      public String getBase64() {
         return (String)this.params.get("base64");
      }

      public Map getParameters() {
         return this.params;
      }

      public String getParameter(String paramName) {
         return (String)this.params.get(StringUtil.asciiToLowerCase(paramName));
      }

      public HttpHeader getHeader() {
         return this.header;
      }
   }

   public interface Result {
      URI getURI();

      void apply(Request var1);
   }
}
