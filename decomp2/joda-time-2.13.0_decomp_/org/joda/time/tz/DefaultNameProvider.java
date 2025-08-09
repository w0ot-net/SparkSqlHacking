package org.joda.time.tz;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.DateTimeUtils;

public class DefaultNameProvider implements NameProvider {
   private HashMap iByLocaleCache = this.createCache();
   private HashMap iByLocaleCache2 = this.createCache();

   public String getShortName(Locale var1, String var2, String var3) {
      String[] var4 = this.getNameSet(var1, var2, var3);
      return var4 == null ? null : var4[0];
   }

   public String getName(Locale var1, String var2, String var3) {
      String[] var4 = this.getNameSet(var1, var2, var3);
      return var4 == null ? null : var4[1];
   }

   private synchronized String[] getNameSet(Locale var1, String var2, String var3) {
      if (var1 != null && var2 != null && var3 != null) {
         Object var4 = (Map)this.iByLocaleCache.get(var1);
         if (var4 == null) {
            this.iByLocaleCache.put(var1, var4 = this.createCache());
         }

         Object var5 = (Map)((Map)var4).get(var2);
         if (var5 == null) {
            ((Map)var4).put(var2, var5 = this.createCache());
            String[][] var6 = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
            String[] var7 = null;

            for(String[] var11 : var6) {
               if (var11 != null && var11.length >= 5 && var2.equals(var11[0])) {
                  var7 = var11;
                  break;
               }
            }

            String[][] var14 = DateTimeUtils.getDateFormatSymbols(var1).getZoneStrings();
            String[] var15 = null;

            for(String[] var13 : var14) {
               if (var13 != null && var13.length >= 5 && var2.equals(var13[0])) {
                  var15 = var13;
                  break;
               }
            }

            if (var7 != null && var15 != null) {
               ((Map)var5).put(var7[2], new String[]{var15[2], var15[1]});
               if (var7[2].equals(var7[4])) {
                  ((Map)var5).put(var7[4] + "-Summer", new String[]{var15[4], var15[3]});
               } else {
                  ((Map)var5).put(var7[4], new String[]{var15[4], var15[3]});
               }
            }
         }

         return (String[])((Map)var5).get(var3);
      } else {
         return null;
      }
   }

   public String getShortName(Locale var1, String var2, String var3, boolean var4) {
      String[] var5 = this.getNameSet(var1, var2, var3, var4);
      return var5 == null ? null : var5[0];
   }

   public String getName(Locale var1, String var2, String var3, boolean var4) {
      String[] var5 = this.getNameSet(var1, var2, var3, var4);
      return var5 == null ? null : var5[1];
   }

   private synchronized String[] getNameSet(Locale var1, String var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null) {
         if (var2.startsWith("Etc/")) {
            var2 = var2.substring(4);
         }

         Object var5 = (Map)this.iByLocaleCache2.get(var1);
         if (var5 == null) {
            this.iByLocaleCache2.put(var1, var5 = this.createCache());
         }

         Object var6 = (Map)((Map)var5).get(var2);
         if (var6 == null) {
            ((Map)var5).put(var2, var6 = this.createCache());
            String[][] var7 = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
            String[] var8 = null;

            for(String[] var12 : var7) {
               if (var12 != null && var12.length >= 5 && var2.equals(var12[0])) {
                  var8 = var12;
                  break;
               }
            }

            String[][] var15 = DateTimeUtils.getDateFormatSymbols(var1).getZoneStrings();
            String[] var16 = null;

            for(String[] var14 : var15) {
               if (var14 != null && var14.length >= 5 && var2.equals(var14[0])) {
                  var16 = var14;
                  break;
               }
            }

            if (var8 != null && var16 != null) {
               ((Map)var6).put(Boolean.TRUE, new String[]{var16[2], var16[1]});
               ((Map)var6).put(Boolean.FALSE, new String[]{var16[4], var16[3]});
            }
         }

         return (String[])((Map)var6).get(var4);
      } else {
         return null;
      }
   }

   private HashMap createCache() {
      return new HashMap(7);
   }
}
