package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.field.LenientDateTimeField;

public final class LenientChronology extends AssembledChronology {
   private static final long serialVersionUID = -3148237568046877177L;
   private transient Chronology iWithUTC;

   public static LenientChronology getInstance(Chronology var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Must supply a chronology");
      } else {
         return new LenientChronology(var0);
      }
   }

   private LenientChronology(Chronology var1) {
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
      var1.year = this.convertField(var1.year);
      var1.yearOfEra = this.convertField(var1.yearOfEra);
      var1.yearOfCentury = this.convertField(var1.yearOfCentury);
      var1.centuryOfEra = this.convertField(var1.centuryOfEra);
      var1.era = this.convertField(var1.era);
      var1.dayOfWeek = this.convertField(var1.dayOfWeek);
      var1.dayOfMonth = this.convertField(var1.dayOfMonth);
      var1.dayOfYear = this.convertField(var1.dayOfYear);
      var1.monthOfYear = this.convertField(var1.monthOfYear);
      var1.weekOfWeekyear = this.convertField(var1.weekOfWeekyear);
      var1.weekyear = this.convertField(var1.weekyear);
      var1.weekyearOfCentury = this.convertField(var1.weekyearOfCentury);
      var1.millisOfSecond = this.convertField(var1.millisOfSecond);
      var1.millisOfDay = this.convertField(var1.millisOfDay);
      var1.secondOfMinute = this.convertField(var1.secondOfMinute);
      var1.secondOfDay = this.convertField(var1.secondOfDay);
      var1.minuteOfHour = this.convertField(var1.minuteOfHour);
      var1.minuteOfDay = this.convertField(var1.minuteOfDay);
      var1.hourOfDay = this.convertField(var1.hourOfDay);
      var1.hourOfHalfday = this.convertField(var1.hourOfHalfday);
      var1.clockhourOfDay = this.convertField(var1.clockhourOfDay);
      var1.clockhourOfHalfday = this.convertField(var1.clockhourOfHalfday);
      var1.halfdayOfDay = this.convertField(var1.halfdayOfDay);
   }

   private final DateTimeField convertField(DateTimeField var1) {
      return LenientDateTimeField.getInstance(var1, this.getBase());
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof LenientChronology)) {
         return false;
      } else {
         LenientChronology var2 = (LenientChronology)var1;
         return this.getBase().equals(var2.getBase());
      }
   }

   public int hashCode() {
      return 236548278 + this.getBase().hashCode() * 7;
   }

   public String toString() {
      return "LenientChronology[" + this.getBase().toString() + ']';
   }
}
