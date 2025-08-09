package jodd.datetime;

import java.io.Serializable;
import java.math.BigDecimal;
import jodd.util.HashCode;

public class JulianDateStamp implements Serializable, Cloneable {
   protected int integer;
   protected double fraction;

   public int getInteger() {
      return this.integer;
   }

   public double getFraction() {
      return this.fraction;
   }

   public int getSignificantFraction() {
      return (int)(this.fraction * (double)1.0E8F);
   }

   public int getJulianDayNumber() {
      return this.fraction >= (double)0.5F ? this.integer + 1 : this.integer;
   }

   public JulianDateStamp() {
   }

   public JulianDateStamp(double jd) {
      this.set(jd);
   }

   public JulianDateStamp(int i, double f) {
      this.set(i, f);
   }

   public JulianDateStamp(BigDecimal bd) {
      double d = bd.doubleValue();
      this.integer = (int)d;
      bd = bd.subtract(new BigDecimal(this.integer));
      this.fraction = bd.doubleValue();
   }

   public double doubleValue() {
      return (double)this.integer + this.fraction;
   }

   public BigDecimal toBigDecimal() {
      BigDecimal bd = new BigDecimal(this.integer);
      return bd.add(new BigDecimal(this.fraction));
   }

   public String toString() {
      String s = Double.toString(this.fraction);
      int i = s.indexOf(46);
      s = s.substring(i);
      return this.integer + s;
   }

   public JulianDateStamp add(JulianDateStamp jds) {
      int i = this.integer + jds.integer;
      double f = this.fraction + jds.fraction;
      this.set(i, f);
      return this;
   }

   public JulianDateStamp add(double delta) {
      this.set(this.integer, this.fraction + delta);
      return this;
   }

   public JulianDateStamp sub(JulianDateStamp jds) {
      int i = this.integer - jds.integer;
      double f = this.fraction - jds.fraction;
      this.set(i, f);
      return this;
   }

   public JulianDateStamp sub(double delta) {
      this.set(this.integer, this.fraction - delta);
      return this;
   }

   public void set(int i, double f) {
      this.integer = i;
      int fi = (int)f;
      f -= (double)fi;
      this.integer += fi;
      if (f < (double)0.0F) {
         ++f;
         --this.integer;
      }

      this.fraction = f;
   }

   public void set(double jd) {
      this.integer = (int)jd;
      this.fraction = jd - (double)this.integer;
   }

   public int daysBetween(JulianDateStamp otherDate) {
      int difference = this.daysSpan(otherDate);
      return difference >= 0 ? difference : -difference;
   }

   public int daysSpan(JulianDateStamp otherDate) {
      int now = this.getJulianDayNumber();
      int then = otherDate.getJulianDayNumber();
      return now - then;
   }

   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else if (!(object instanceof JulianDateStamp)) {
         return false;
      } else {
         JulianDateStamp stamp = (JulianDateStamp)object;
         return stamp.integer == this.integer && Double.compare(stamp.fraction, this.fraction) == 0;
      }
   }

   public int hashCode() {
      int result = 173;
      result = HashCode.hash(result, this.integer);
      result = HashCode.hash(result, this.fraction);
      return result;
   }

   protected JulianDateStamp clone() {
      return new JulianDateStamp(this.integer, this.fraction);
   }

   public JulianDateStamp getReducedJulianDate() {
      return new JulianDateStamp(this.integer - 2400000, this.fraction);
   }

   public void setReducedJulianDate(double rjd) {
      this.set(rjd + (double)2400000.0F);
   }

   public JulianDateStamp getModifiedJulianDate() {
      return new JulianDateStamp(this.integer - 2400000, this.fraction - (double)0.5F);
   }

   public void setModifiedJulianDate(double mjd) {
      this.set(mjd + (double)2400000.5F);
   }

   public JulianDateStamp getTruncatedJulianDate() {
      return new JulianDateStamp(this.integer - 2440000, this.fraction - (double)0.5F);
   }

   public void setTruncatedJulianDate(double tjd) {
      this.set(tjd + (double)2440000.5F);
   }
}
