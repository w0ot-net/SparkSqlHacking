package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.field.DelegatedDateTimeField;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.SkipUndoDateTimeField;
import org.joda.time.field.UnsupportedDurationField;

public final class BuddhistChronology extends AssembledChronology {
   private static final long serialVersionUID = -3474595157769370126L;
   public static final int BE = 1;
   private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("BE");
   private static final int BUDDHIST_OFFSET = 543;
   private static final ConcurrentHashMap cCache = new ConcurrentHashMap();
   private static final BuddhistChronology INSTANCE_UTC;

   public static BuddhistChronology getInstanceUTC() {
      return INSTANCE_UTC;
   }

   public static BuddhistChronology getInstance() {
      return getInstance(DateTimeZone.getDefault());
   }

   public static BuddhistChronology getInstance(DateTimeZone var0) {
      if (var0 == null) {
         var0 = DateTimeZone.getDefault();
      }

      BuddhistChronology var1 = (BuddhistChronology)cCache.get(var0);
      if (var1 == null) {
         BuddhistChronology var4 = new BuddhistChronology(GJChronology.getInstance(var0, (ReadableInstant)null), (Object)null);
         DateTime var2 = new DateTime(1, 1, 1, 0, 0, 0, 0, var4);
         var1 = new BuddhistChronology(LimitChronology.getInstance(var4, var2, (ReadableDateTime)null), "");
         BuddhistChronology var3 = (BuddhistChronology)cCache.putIfAbsent(var0, var1);
         if (var3 != null) {
            var1 = var3;
         }
      }

      return var1;
   }

   private BuddhistChronology(Chronology var1, Object var2) {
      super(var1, var2);
   }

   private Object readResolve() {
      Chronology var1 = this.getBase();
      return var1 == null ? getInstanceUTC() : getInstance(var1.getZone());
   }

   public Chronology withUTC() {
      return INSTANCE_UTC;
   }

   public Chronology withZone(DateTimeZone var1) {
      if (var1 == null) {
         var1 = DateTimeZone.getDefault();
      }

      return var1 == this.getZone() ? this : getInstance(var1);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof BuddhistChronology) {
         BuddhistChronology var2 = (BuddhistChronology)var1;
         return this.getZone().equals(var2.getZone());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return "Buddhist".hashCode() * 11 + this.getZone().hashCode();
   }

   public String toString() {
      String var1 = "BuddhistChronology";
      DateTimeZone var2 = this.getZone();
      if (var2 != null) {
         var1 = var1 + '[' + var2.getID() + ']';
      }

      return var1;
   }

   protected void assemble(AssembledChronology.Fields var1) {
      if (this.getParam() == null) {
         var1.eras = UnsupportedDurationField.getInstance(DurationFieldType.eras());
         DateTimeField var2 = var1.year;
         var1.year = new OffsetDateTimeField(new SkipUndoDateTimeField(this, var2), 543);
         var2 = var1.yearOfEra;
         var1.yearOfEra = new DelegatedDateTimeField(var1.year, var1.eras, DateTimeFieldType.yearOfEra());
         var2 = var1.weekyear;
         var1.weekyear = new OffsetDateTimeField(new SkipUndoDateTimeField(this, var2), 543);
         OffsetDateTimeField var5 = new OffsetDateTimeField(var1.yearOfEra, 99);
         var1.centuryOfEra = new DividedDateTimeField(var5, var1.eras, DateTimeFieldType.centuryOfEra(), 100);
         var1.centuries = var1.centuryOfEra.getDurationField();
         RemainderDateTimeField var6 = new RemainderDateTimeField((DividedDateTimeField)var1.centuryOfEra);
         var1.yearOfCentury = new OffsetDateTimeField(var6, DateTimeFieldType.yearOfCentury(), 1);
         var6 = new RemainderDateTimeField(var1.weekyear, var1.centuries, DateTimeFieldType.weekyearOfCentury(), 100);
         var1.weekyearOfCentury = new OffsetDateTimeField(var6, DateTimeFieldType.weekyearOfCentury(), 1);
         var1.era = ERA_FIELD;
      }

   }

   static {
      INSTANCE_UTC = getInstance(DateTimeZone.UTC);
   }
}
