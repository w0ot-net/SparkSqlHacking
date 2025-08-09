package org.sparkproject.jetty.http.pathmap;

public interface MatchedPath {
   MatchedPath EMPTY = new MatchedPath() {
      public String getPathMatch() {
         return null;
      }

      public String getPathInfo() {
         return null;
      }

      public String toString() {
         return MatchedPath.class.getSimpleName() + ".EMPTY";
      }
   };

   static MatchedPath from(final String pathMatch, final String pathInfo) {
      return new MatchedPath() {
         public String getPathMatch() {
            return pathMatch;
         }

         public String getPathInfo() {
            return pathInfo;
         }

         public String toString() {
            String var10000 = MatchedPath.class.getSimpleName();
            return var10000 + "[pathMatch=" + pathMatch + ", pathInfo=" + pathInfo + "]";
         }
      };
   }

   String getPathMatch();

   String getPathInfo();
}
