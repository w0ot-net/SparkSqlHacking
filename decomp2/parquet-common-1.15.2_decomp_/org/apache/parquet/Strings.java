package org.apache.parquet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.parquet.glob.GlobExpander;
import org.apache.parquet.glob.WildcardPath;

public final class Strings {
   private Strings() {
   }

   /** @deprecated */
   @Deprecated
   public static String join(Iterable s, String on) {
      return join(s.iterator(), on);
   }

   /** @deprecated */
   @Deprecated
   public static String join(Iterator iter, String on) {
      StringBuilder sb = new StringBuilder();

      while(iter.hasNext()) {
         sb.append((String)iter.next());
         if (iter.hasNext()) {
            sb.append(on);
         }
      }

      return sb.toString();
   }

   /** @deprecated */
   @Deprecated
   public static String join(String[] s, String on) {
      return join((Iterable)Arrays.asList(s), on);
   }

   public static boolean isNullOrEmpty(String s) {
      return s == null || s.isEmpty();
   }

   public static List expandGlob(String globPattern) {
      return GlobExpander.expand(globPattern);
   }

   public static List expandGlobToWildCardPaths(String globPattern, char delim) {
      List<WildcardPath> ret = new ArrayList();

      for(String expandedGlob : expandGlob(globPattern)) {
         ret.add(new WildcardPath(globPattern, expandedGlob, delim));
      }

      return ret;
   }
}
