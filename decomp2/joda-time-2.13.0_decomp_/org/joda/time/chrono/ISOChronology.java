package org.joda.time.chrono;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.RemainderDateTimeField;

public final class ISOChronology extends AssembledChronology {
   private static final long serialVersionUID = -6212696554273812441L;
   private static final ISOChronology INSTANCE_UTC = new ISOChronology(GregorianChronology.getInstanceUTC());
   private static final ConcurrentHashMap cCache = new ConcurrentHashMap();

   public static ISOChronology getInstanceUTC() {
      return INSTANCE_UTC;
   }

   public static ISOChronology getInstance() {
      return getInstance(DateTimeZone.getDefault());
   }

   public static ISOChronology getInstance(DateTimeZone var0) {
      if (var0 == null) {
         var0 = DateTimeZone.getDefault();
      }

      ISOChronology var1 = (ISOChronology)cCache.get(var0);
      if (var1 == null) {
         var1 = new ISOChronology(ZonedChronology.getInstance(INSTANCE_UTC, var0));
         ISOChronology var2 = (ISOChronology)cCache.putIfAbsent(var0, var1);
         if (var2 != null) {
            var1 = var2;
         }
      }

      return var1;
   }

   private ISOChronology(Chronology var1) {
      super(var1, (Object)null);
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

   public String toString() {
      String var1 = "ISOChronology";
      DateTimeZone var2 = this.getZone();
      if (var2 != null) {
         var1 = var1 + '[' + var2.getID() + ']';
      }

      return var1;
   }

   protected void assemble(AssembledChronology.Fields var1) {
      if (this.getBase().getZone() == DateTimeZone.UTC) {
         var1.centuryOfEra = new DividedDateTimeField(ISOYearOfEraDateTimeField.INSTANCE, DateTimeFieldType.centuryOfEra(), 100);
         var1.centuries = var1.centuryOfEra.getDurationField();
         var1.yearOfCentury = new RemainderDateTimeField((DividedDateTimeField)var1.centuryOfEra, DateTimeFieldType.yearOfCentury());
         var1.weekyearOfCentury = new RemainderDateTimeField((DividedDateTimeField)var1.centuryOfEra, var1.weekyears, DateTimeFieldType.weekyearOfCentury());
      }

   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof ISOChronology) {
         ISOChronology var2 = (ISOChronology)var1;
         return this.getZone().equals(var2.getZone());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return "ISO".hashCode() * 11 + this.getZone().hashCode();
   }

   private Object writeReplace() {
      return new Stub(this.getZone());
   }

   static {
      cCache.put(DateTimeZone.UTC, INSTANCE_UTC);
   }

   private static final class Stub implements Serializable {
      private static final long serialVersionUID = -6212696554273812441L;
      private transient DateTimeZone iZone;

      Stub(DateTimeZone var1) {
         this.iZone = var1;
      }

      private Object readResolve() {
         return ISOChronology.getInstance(this.iZone);
      }

      private void writeObject(ObjectOutputStream var1) throws IOException {
         var1.writeObject(this.iZone);
      }

      private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
         this.iZone = (DateTimeZone)var1.readObject();
      }
   }
}
