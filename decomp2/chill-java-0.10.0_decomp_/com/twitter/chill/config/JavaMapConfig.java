package com.twitter.chill.config;

import java.util.HashMap;
import java.util.Map;

public class JavaMapConfig extends Config {
   final Map conf;

   public JavaMapConfig(Map var1) {
      this.conf = var1;
   }

   public JavaMapConfig() {
      this(new HashMap());
   }

   public String get(String var1) {
      Object var2 = this.conf.get(var1);
      return null != var2 ? var2.toString() : null;
   }

   public void set(String var1, String var2) {
      this.conf.put(var1, var2);
   }
}
