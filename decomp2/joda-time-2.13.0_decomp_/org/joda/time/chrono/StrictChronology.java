package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.field.StrictDateTimeField;

public final class StrictChronology extends AssembledChronology {
   private static final long serialVersionUID = 6633006628097111960L;
   private transient Chronology iWithUTC;

   public static StrictChronology getInstance(Chronology var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Must supply a chronology");
      } else {
         return new StrictChronology(var0);
      }
   }

   private StrictChronology(Chronology var1) {
      super(var1, (Object)null);
   }

   public Chronology withUTC() {
      if (this.iWithUTC == null) {
         if (this.getZone() == DateTimeZone.UTC) {
            this.iWithUTC = this;
         } else {
            this.iWithUTC = getInstance(this.getBase().withUTC());
         }
      }

      return this.iWithUTC;
   }

   public Chronology withZone(DateTimeZone var1) {
      if (var1 == null) {
         var1 = DateTimeZone.getDefault();
      }

      if (var1 == DateTimeZone.UTC) {
         return this.withUTC();
      } else {
         return var1 == this.getZone() ? this : getInstance(this.getBase().withZone(var1));
      }
   }

   protected void assemble(AssembledChronology.Fields var1) {
      var1.year = convertField(var1.year);
      var1.yearOfEra = convertField(var1.yearOfEra);
      var1.yearOfCentury = convertField(var1.yearOfCentury);
      var1.centuryOfEra = convertField(var1.centuryOfEra);
      var1.era = convertField(var1.era);
      var1.dayOfWeek = convertField(var1.dayOfWeek);
      var1.dayOfMonth = convertField(var1.dayOfMonth);
      var1.dayOfYear = convertField(var1.dayOfYear);
      var1.monthOfYear = convertField(var1.monthOfYear);
      var1.weekOfWeekyear = convertField(var1.weekOfWeekyear);
      var1.weekyear = convertField(var1.weekyear);
      var1.weekyearOfCentury = convertField(var1.weekyearOfCentury);
      var1.millisOfSecond = convertField(var1.millisOfSecond);
      var1.millisOfDay = convertField(var1.millisOfDay);
      var1.secondOfMinute = convertField(var1.secondOfMinute);
      var1.secondOfDay = convertField(var1.secondOfDay);
      var1.minuteOfHour = convertField(var1.minuteOfHour);
      var1.minuteOfDay = convertField(var1.minuteOfDay);
      var1.hourOfDay = convertField(var1.hourOfDay);
      var1.hourOfHalfday = convertField(var1.hourOfHalfday);
      var1.clockhourOfDay = convertField(var1.clockhourOfDay);
      var1.clockhourOfHalfday = convertField(var1.clockhourOfHalfday);
      var1.halfdayOfDay = convertField(var1.halfdayOfDay);
   }

   private static final DateTimeField convertField(DateTimeField var0) {
      return StrictDateTimeField.getInstance(var0);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof StrictChronology)) {
         return false;
      } else {
         StrictChronology var2 = (StrictChronology)var1;
         return this.getBase().equals(var2.getBase());
      }
   }

   public int hashCode() {
      return 352831696 + this.getBase().hashCode() * 7;
   }

   public String toString() {
      return "StrictChronology[" + this.getBase().toString() + ']';
   }
}
