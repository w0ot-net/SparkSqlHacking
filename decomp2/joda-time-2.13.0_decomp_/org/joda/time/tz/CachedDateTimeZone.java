package org.joda.time.tz;

import org.joda.time.DateTimeZone;

public class CachedDateTimeZone extends DateTimeZone {
   private static final long serialVersionUID = 5472298452022250685L;
   private static final int cInfoCacheMask;
   private final DateTimeZone iZone;
   private final transient Info[] iInfoCache;

   public static CachedDateTimeZone forZone(DateTimeZone var0) {
      return var0 instanceof CachedDateTimeZone ? (CachedDateTimeZone)var0 : new CachedDateTimeZone(var0);
   }

   private CachedDateTimeZone(DateTimeZone var1) {
      super(var1.getID());
      this.iInfoCache = new Info[cInfoCacheMask + 1];
      this.iZone = var1;
   }

   public DateTimeZone getUncachedZone() {
      return this.iZone;
   }

   public String getNameKey(long var1) {
      return this.getInfo(var1).getNameKey(var1);
   }

   public int getOffset(long var1) {
      return this.getInfo(var1).getOffset(var1);
   }

   public int getStandardOffset(long var1) {
      return this.getInfo(var1).getStandardOffset(var1);
   }

   public boolean isFixed() {
      return this.iZone.isFixed();
   }

   public long nextTransition(long var1) {
      return this.iZone.nextTransition(var1);
   }

   public long previousTransition(long var1) {
      return this.iZone.previousTransition(var1);
   }

   public int hashCode() {
      return this.iZone.hashCode();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return var1 instanceof CachedDateTimeZone ? this.iZone.equals(((CachedDateTimeZone)var1).iZone) : false;
      }
   }

   private Info getInfo(long var1) {
      int var3 = (int)(var1 >> 32);
      Info[] var4 = this.iInfoCache;
      int var5 = var3 & cInfoCacheMask;
      Info var6 = var4[var5];
      if (var6 == null || (int)(var6.iPeriodStart >> 32) != var3) {
         var6 = this.createInfo(var1);
         var4[var5] = var6;
      }

      return var6;
   }

   private Info createInfo(long var1) {
      long var3 = var1 & -4294967296L;
      Info var5 = new Info(this.iZone, var3);
      long var6 = var3 | 4294967295L;
      Info var8 = var5;

      while(true) {
         long var9 = this.iZone.nextTransition(var3);
         if (var9 == var3 || var9 > var6) {
            return var5;
         }

         var3 = var9;
         var8 = var8.iNextInfo = new Info(this.iZone, var9);
      }
   }

   static {
      Integer var0;
      try {
         var0 = Integer.getInteger("org.joda.time.tz.CachedDateTimeZone.size");
      } catch (SecurityException var3) {
         var0 = null;
      }

      int var1;
      if (var0 == null) {
         var1 = 512;
      } else {
         var1 = var0;
         --var1;

         int var2;
         for(var2 = 0; var1 > 0; var1 >>= 1) {
            ++var2;
         }

         var1 = 1 << var2;
      }

      cInfoCacheMask = var1 - 1;
   }

   private static final class Info {
      public final long iPeriodStart;
      public final DateTimeZone iZoneRef;
      Info iNextInfo;
      private String iNameKey;
      private int iOffset = Integer.MIN_VALUE;
      private int iStandardOffset = Integer.MIN_VALUE;

      Info(DateTimeZone var1, long var2) {
         this.iPeriodStart = var2;
         this.iZoneRef = var1;
      }

      public String getNameKey(long var1) {
         if (this.iNextInfo != null && var1 >= this.iNextInfo.iPeriodStart) {
            return this.iNextInfo.getNameKey(var1);
         } else {
            if (this.iNameKey == null) {
               this.iNameKey = this.iZoneRef.getNameKey(this.iPeriodStart);
            }

            return this.iNameKey;
         }
      }

      public int getOffset(long var1) {
         if (this.iNextInfo != null && var1 >= this.iNextInfo.iPeriodStart) {
            return this.iNextInfo.getOffset(var1);
         } else {
            if (this.iOffset == Integer.MIN_VALUE) {
               this.iOffset = this.iZoneRef.getOffset(this.iPeriodStart);
            }

            return this.iOffset;
         }
      }

      public int getStandardOffset(long var1) {
         if (this.iNextInfo != null && var1 >= this.iNextInfo.iPeriodStart) {
            return this.iNextInfo.getStandardOffset(var1);
         } else {
            if (this.iStandardOffset == Integer.MIN_VALUE) {
               this.iStandardOffset = this.iZoneRef.getStandardOffset(this.iPeriodStart);
            }

            return this.iStandardOffset;
         }
      }
   }
}
