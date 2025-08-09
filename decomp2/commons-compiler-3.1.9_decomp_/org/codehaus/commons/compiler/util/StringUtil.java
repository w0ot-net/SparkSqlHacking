package org.codehaus.commons.compiler.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.commons.nullanalysis.Nullable;

public final class StringUtil {
   private StringUtil() {
   }

   public static File[] parsePath(String s) {
      int from = 0;
      List<File> l = new ArrayList();

      while(true) {
         int to = s.indexOf(File.pathSeparatorChar, from);
         if (to == -1) {
            if (from != s.length()) {
               l.add(new File(s.substring(from)));
            }

            return (File[])l.toArray(new File[l.size()]);
         }

         if (to != from) {
            l.add(new File(s.substring(from, to)));
         }

         from = to + 1;
      }
   }

   @Nullable
   public static File[] parseOptionalPath(@Nullable String s) {
      return s == null ? null : parsePath(s);
   }
}
