package org.glassfish.jersey.message.internal;

import jakarta.inject.Singleton;
import jakarta.ws.rs.core.Link;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.Tokenizer;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

@Singleton
public class LinkProvider implements HeaderDelegateProvider {
   private static final Logger LOGGER = Logger.getLogger(LinkProvider.class.getName());

   public boolean supports(Class type) {
      return Link.class.isAssignableFrom(type);
   }

   public Link fromString(String value) throws IllegalArgumentException {
      return initBuilder(new JerseyLink.Builder(), value).build();
   }

   static JerseyLink.Builder initBuilder(JerseyLink.Builder lb, String value) {
      Utils.throwIllegalArgumentExceptionIfNull(value, LocalizationMessages.LINK_IS_NULL());

      try {
         value = value.trim();
         if (!value.startsWith("<")) {
            throw new IllegalArgumentException("Missing starting token < in " + value);
         }

         int gtIndex = value.indexOf(62);
         if (gtIndex == -1) {
            throw new IllegalArgumentException("Missing token > in " + value);
         }

         lb.uri(value.substring(1, gtIndex).trim());
         String params = value.substring(gtIndex + 1).trim();

         String n;
         String v;
         for(StringTokenizer st = new StringTokenizer(params, ";=\"", true); st.hasMoreTokens(); lb.param(n, v)) {
            checkToken(st, ";");
            n = st.nextToken().trim();
            checkToken(st, "=");
            v = nextNonEmptyToken(st);
            if (v.equals("\"")) {
               v = st.nextToken();
               checkToken(st, "\"");
            }
         }
      } catch (Throwable e) {
         if (LOGGER.isLoggable(Level.FINER)) {
            LOGGER.log(Level.FINER, "Error parsing link value '" + value + "'", e);
         }

         lb = null;
      }

      if (lb == null) {
         throw new IllegalArgumentException("Unable to parse link " + value);
      } else {
         return lb;
      }
   }

   private static String nextNonEmptyToken(StringTokenizer st) throws IllegalArgumentException {
      String token;
      do {
         token = st.nextToken().trim();
      } while(token.length() == 0);

      return token;
   }

   private static void checkToken(StringTokenizer st, String expected) throws IllegalArgumentException {
      String token;
      do {
         token = st.nextToken().trim();
      } while(token.length() == 0);

      if (!token.equals(expected)) {
         throw new IllegalArgumentException("Expected token " + expected + " but found " + token);
      }
   }

   public String toString(Link value) {
      return stringfy(value);
   }

   static String stringfy(Link value) {
      Utils.throwIllegalArgumentExceptionIfNull(value, LocalizationMessages.LINK_IS_NULL());
      Map<String, String> map = value.getParams();
      StringBuilder sb = new StringBuilder();
      sb.append('<').append(value.getUri()).append('>');

      for(Map.Entry entry : map.entrySet()) {
         sb.append("; ").append((String)entry.getKey()).append("=\"").append((String)entry.getValue()).append("\"");
      }

      return sb.toString();
   }

   static List getLinkRelations(String rel) {
      return rel == null ? null : Arrays.asList(Tokenizer.tokenize(rel, "\" "));
   }
}
