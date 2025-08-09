package org.joda.time;

import java.util.SimpleTimeZone;
import java.util.TimeZone;

final class UTCDateTimeZone extends DateTimeZone {
   static final DateTimeZone INSTANCE = new UTCDateTimeZone();
   private static final long serialVersionUID = -3513011772763289092L;

   public UTCDateTimeZone() {
      super("UTC");
   }

   public String getNameKey(long var1) {
      return "UTC";
   }

   public int getOffset(long var1) {
      return 0;
   }

   public int getStandardOffset(long var1) {
      return 0;
   }

   public int getOffsetFromLocal(long var1) {
      return 0;
   }

   public boolean isFixed() {
      return true;
   }

   public long nextTransition(long var1) {
      return var1;
   }

   public long previousTransition(long var1) {
      return var1;
   }

   public TimeZone toTimeZone() {
      return new SimpleTimeZone(0, this.getID());
   }

   public boolean equals(Object var1) {
      return var1 instanceof UTCDateTimeZone;
   }

   public int hashCode() {
      return this.getID().hashCode();
   }
}
