package com.twitter.chill.config;

public abstract class Config {
   public abstract String get(String var1);

   public abstract void set(String var1, String var2);

   public String getOrElse(String var1, String var2) {
      String var3 = this.get(var1);
      return null == var3 ? var2 : var3;
   }

   public boolean contains(String var1) {
      return this.get(var1) != null;
   }

   public Boolean getBoolean(String var1) {
      String var2 = this.get(var1);
      return null == var2 ? null : Boolean.valueOf(var2);
   }

   public boolean getBoolean(String var1, boolean var2) {
      String var3 = this.get(var1);
      return null == var3 ? var2 : Boolean.valueOf(var3);
   }

   public void setBoolean(String var1, Boolean var2) {
      if (null == var2) {
         this.set(var1, (String)null);
      } else {
         this.set(var1, var2.toString());
      }

   }
}
