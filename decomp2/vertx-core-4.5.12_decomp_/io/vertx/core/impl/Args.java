package io.vertx.core.impl;

import java.util.HashMap;
import java.util.Map;

public class Args {
   public final Map map = new HashMap();

   public Args(String[] args) {
      String currentKey = null;

      for(String arg : args) {
         if (arg.startsWith("-")) {
            if (currentKey != null) {
               this.map.put(currentKey, "");
            }

            currentKey = arg;
         } else if (currentKey != null) {
            this.map.put(currentKey, arg);
            currentKey = null;
         }
      }

      if (currentKey != null) {
         this.map.put(currentKey, "");
      }

   }

   public int getInt(String argName) {
      String arg = (String)this.map.get(argName);
      int val;
      if (arg != null) {
         try {
            val = Integer.parseInt(arg.trim());
         } catch (NumberFormatException var5) {
            throw new IllegalArgumentException("Invalid " + argName + ": " + arg);
         }
      } else {
         val = -1;
      }

      return val;
   }
}
