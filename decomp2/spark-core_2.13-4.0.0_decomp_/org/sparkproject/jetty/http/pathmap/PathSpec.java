package org.sparkproject.jetty.http.pathmap;

import java.util.Objects;

public interface PathSpec extends Comparable {
   static PathSpec from(String pathSpecString) {
      Objects.requireNonNull(pathSpecString, "null PathSpec not supported");
      if (pathSpecString.length() == 0) {
         return new ServletPathSpec("");
      } else {
         return (PathSpec)(pathSpecString.charAt(0) == '^' ? new RegexPathSpec(pathSpecString) : new ServletPathSpec(pathSpecString));
      }
   }

   int getSpecLength();

   PathSpecGroup getGroup();

   int getPathDepth();

   /** @deprecated */
   @Deprecated
   String getPathInfo(String var1);

   /** @deprecated */
   @Deprecated
   String getPathMatch(String var1);

   String getDeclaration();

   String getPrefix();

   String getSuffix();

   /** @deprecated */
   @Deprecated
   boolean matches(String var1);

   MatchedPath matched(String var1);
}
