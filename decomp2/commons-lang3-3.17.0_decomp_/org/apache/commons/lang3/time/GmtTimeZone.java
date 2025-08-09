package org.apache.commons.lang3.time;

import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

final class GmtTimeZone extends TimeZone {
   private static final int MILLISECONDS_PER_MINUTE = 60000;
   private static final int MINUTES_PER_HOUR = 60;
   private static final int HOURS_PER_DAY = 24;
   static final long serialVersionUID = 1L;
   private final int offset;
   private final String zoneId;

   private static StringBuilder twoDigits(StringBuilder sb, int n) {
      return sb.append((char)(48 + n / 10)).append((char)(48 + n % 10));
   }

   GmtTimeZone(boolean negate, int hours, int minutes) {
      if (hours >= 24) {
         throw new IllegalArgumentException(hours + " hours out of range");
      } else if (minutes >= 60) {
         throw new IllegalArgumentException(minutes + " minutes out of range");
      } else {
         int milliseconds = (minutes + hours * 60) * '\uea60';
         this.offset = negate ? -milliseconds : milliseconds;
         this.zoneId = twoDigits(twoDigits((new StringBuilder(9)).append("GMT").append((char)(negate ? '-' : '+')), hours).append(':'), minutes).toString();
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof GmtTimeZone)) {
         return false;
      } else {
         GmtTimeZone other = (GmtTimeZone)obj;
         return this.offset == other.offset && Objects.equals(this.zoneId, other.zoneId);
      }
   }

   public String getID() {
      return this.zoneId;
   }

   public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {
      return this.offset;
   }

   public int getRawOffset() {
      return this.offset;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.offset, this.zoneId});
   }

   public boolean inDaylightTime(Date date) {
      return false;
   }

   public void setRawOffset(int offsetMillis) {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      return "[GmtTimeZone id=\"" + this.zoneId + "\",offset=" + this.offset + ']';
   }

   public boolean useDaylightTime() {
      return false;
   }
}
