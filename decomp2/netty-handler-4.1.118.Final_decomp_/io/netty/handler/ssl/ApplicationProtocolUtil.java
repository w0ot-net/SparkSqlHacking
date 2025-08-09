package io.netty.handler.ssl;

import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.List;

final class ApplicationProtocolUtil {
   private static final int DEFAULT_LIST_SIZE = 2;

   private ApplicationProtocolUtil() {
   }

   static List toList(Iterable protocols) {
      return toList(2, (Iterable)protocols);
   }

   static List toList(int initialListSize, Iterable protocols) {
      if (protocols == null) {
         return null;
      } else {
         List<String> result = new ArrayList(initialListSize);

         for(String p : protocols) {
            result.add(ObjectUtil.checkNonEmpty(p, "p"));
         }

         return (List)ObjectUtil.checkNonEmpty(result, "result");
      }
   }

   static List toList(String... protocols) {
      return toList(2, (String[])protocols);
   }

   static List toList(int initialListSize, String... protocols) {
      if (protocols == null) {
         return null;
      } else {
         List<String> result = new ArrayList(initialListSize);

         for(String p : protocols) {
            result.add(ObjectUtil.checkNonEmpty(p, "p"));
         }

         return (List)ObjectUtil.checkNonEmpty(result, "result");
      }
   }
}
