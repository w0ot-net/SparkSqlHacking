package net.razorvine.pickle.objects;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class TimeDelta implements Serializable {
   private static final long serialVersionUID = 7655189815303876847L;
   public final int days;
   public final int seconds;
   public final int microseconds;
   public final double total_seconds;

   public TimeDelta(int days, int seconds, int microseconds) {
      this.days = days;
      this.seconds = seconds;
      this.microseconds = microseconds;
      this.total_seconds = (double)(days * 86400 + seconds) + (double)microseconds / (double)1000000.0F;
   }

   public String toString() {
      NumberFormat nf = NumberFormat.getInstance(Locale.UK);
      nf.setGroupingUsed(false);
      nf.setMaximumFractionDigits(6);
      String floatsecs = nf.format(this.total_seconds);
      return String.format("Timedelta: %d days, %d seconds, %d microseconds (total: %s seconds)", this.days, this.seconds, this.microseconds, floatsecs);
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + this.days;
      result = 31 * result + this.microseconds;
      result = 31 * result + this.seconds;
      long temp = Double.doubleToLongBits(this.total_seconds);
      result = 31 * result + (int)(temp ^ temp >>> 32);
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof TimeDelta)) {
         return false;
      } else {
         TimeDelta other = (TimeDelta)obj;
         return this.days == other.days && this.seconds == other.seconds && this.microseconds == other.microseconds && this.total_seconds == other.total_seconds;
      }
   }
}
