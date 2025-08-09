package org.joda.time.tz;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.chrono.ISOChronology;

public class DateTimeZoneBuilder {
   private final ArrayList iRuleSets = new ArrayList(10);

   public static DateTimeZone readFrom(InputStream var0, String var1) throws IOException {
      return var0 instanceof DataInput ? readFrom((DataInput)var0, var1) : readFrom((DataInput)(new DataInputStream(var0)), var1);
   }

   public static DateTimeZone readFrom(DataInput var0, String var1) throws IOException {
      switch (var0.readUnsignedByte()) {
         case 67:
            return CachedDateTimeZone.forZone(DateTimeZoneBuilder.PrecalculatedZone.readFrom(var0, var1));
         case 70:
            Object var2 = new FixedDateTimeZone(var1, var0.readUTF(), (int)readMillis(var0), (int)readMillis(var0));
            if (((DateTimeZone)var2).equals(DateTimeZone.UTC)) {
               var2 = DateTimeZone.UTC;
            }

            return (DateTimeZone)var2;
         case 80:
            return DateTimeZoneBuilder.PrecalculatedZone.readFrom(var0, var1);
         default:
            throw new IOException("Invalid encoding");
      }
   }

   static void writeMillis(DataOutput var0, long var1) throws IOException {
      if (var1 % 1800000L == 0L) {
         long var3 = var1 / 1800000L;
         if (var3 << 58 >> 58 == var3) {
            var0.writeByte((int)(var3 & 63L));
            return;
         }
      }

      if (var1 % 60000L == 0L) {
         long var5 = var1 / 60000L;
         if (var5 << 34 >> 34 == var5) {
            var0.writeInt(1073741824 | (int)(var5 & 1073741823L));
            return;
         }
      }

      if (var1 % 1000L == 0L) {
         long var6 = var1 / 1000L;
         if (var6 << 26 >> 26 == var6) {
            var0.writeByte(128 | (int)(var6 >> 32 & 63L));
            var0.writeInt((int)(var6 & -1L));
            return;
         }
      }

      var0.writeByte(var1 < 0L ? 255 : 192);
      var0.writeLong(var1);
   }

   static long readMillis(DataInput var0) throws IOException {
      int var1 = var0.readUnsignedByte();
      switch (var1 >> 6) {
         case 0:
         default:
            var1 = var1 << 26 >> 26;
            return (long)var1 * 1800000L;
         case 1:
            var1 = var1 << 26 >> 2;
            var1 |= var0.readUnsignedByte() << 16;
            var1 |= var0.readUnsignedByte() << 8;
            var1 |= var0.readUnsignedByte();
            return (long)var1 * 60000L;
         case 2:
            long var2 = (long)var1 << 58 >> 26;
            var2 |= (long)(var0.readUnsignedByte() << 24);
            var2 |= (long)(var0.readUnsignedByte() << 16);
            var2 |= (long)(var0.readUnsignedByte() << 8);
            var2 |= (long)var0.readUnsignedByte();
            return var2 * 1000L;
         case 3:
            return var0.readLong();
      }
   }

   private static DateTimeZone buildFixedZone(String var0, String var1, int var2, int var3) {
      return (DateTimeZone)("UTC".equals(var0) && var0.equals(var1) && var2 == 0 && var3 == 0 ? DateTimeZone.UTC : new FixedDateTimeZone(var0, var1, var2, var3));
   }

   public DateTimeZoneBuilder addCutover(int var1, char var2, int var3, int var4, int var5, boolean var6, int var7) {
      if (this.iRuleSets.size() > 0) {
         OfYear var8 = new OfYear(var2, var3, var4, var5, var6, var7);
         RuleSet var9 = (RuleSet)this.iRuleSets.get(this.iRuleSets.size() - 1);
         var9.setUpperLimit(var1, var8);
      }

      this.iRuleSets.add(new RuleSet());
      return this;
   }

   public DateTimeZoneBuilder setStandardOffset(int var1) {
      this.getLastRuleSet().setStandardOffset(var1);
      return this;
   }

   public DateTimeZoneBuilder setFixedSavings(String var1, int var2) {
      this.getLastRuleSet().setFixedSavings(var1, var2);
      return this;
   }

   public DateTimeZoneBuilder addRecurringSavings(String var1, int var2, int var3, int var4, char var5, int var6, int var7, int var8, boolean var9, int var10) {
      if (var3 <= var4) {
         OfYear var11 = new OfYear(var5, var6, var7, var8, var9, var10);
         Recurrence var12 = new Recurrence(var11, var1, var2);
         Rule var13 = new Rule(var12, var3, var4);
         this.getLastRuleSet().addRule(var13);
      }

      return this;
   }

   private RuleSet getLastRuleSet() {
      if (this.iRuleSets.size() == 0) {
         this.addCutover(Integer.MIN_VALUE, 'w', 1, 1, 0, false, 0);
      }

      return (RuleSet)this.iRuleSets.get(this.iRuleSets.size() - 1);
   }

   public DateTimeZone toDateTimeZone(String var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         ArrayList var3 = new ArrayList();
         DSTZone var4 = null;
         long var5 = Long.MIN_VALUE;
         int var7 = 0;
         int var8 = this.iRuleSets.size();

         for(int var9 = 0; var9 < var8; ++var9) {
            RuleSet var10 = (RuleSet)this.iRuleSets.get(var9);
            Transition var11 = var10.firstTransition(var5);
            if (var11 != null) {
               this.addTransition(var3, var11);
               var5 = var11.getMillis();
               var7 = var11.getSaveMillis();
               var10 = new RuleSet(var10);

               while((var11 = var10.nextTransition(var5, var7)) != null && (!this.addTransition(var3, var11) || var4 == null)) {
                  var5 = var11.getMillis();
                  var7 = var11.getSaveMillis();
                  if (var4 == null && var9 == var8 - 1) {
                     var4 = var10.buildTailZone(var1);
                  }
               }

               var5 = var10.getUpperLimit(var7);
            }
         }

         if (var3.size() == 0) {
            if (var4 != null) {
               return var4;
            } else {
               return buildFixedZone(var1, "UTC", 0, 0);
            }
         } else if (var3.size() == 1 && var4 == null) {
            Transition var15 = (Transition)var3.get(0);
            return buildFixedZone(var1, var15.getNameKey(), var15.getWallOffset(), var15.getStandardOffset());
         } else {
            PrecalculatedZone var14 = DateTimeZoneBuilder.PrecalculatedZone.create(var1, var2, var3, var4);
            if (var14.isCachable()) {
               return CachedDateTimeZone.forZone(var14);
            } else {
               return var14;
            }
         }
      }
   }

   private boolean addTransition(ArrayList var1, Transition var2) {
      int var3 = var1.size();
      if (var3 == 0) {
         var1.add(var2);
         return true;
      } else {
         Transition var4 = (Transition)var1.get(var3 - 1);
         if (!var2.isTransitionFrom(var4)) {
            return false;
         } else {
            int var5 = 0;
            if (var3 >= 2) {
               var5 = ((Transition)var1.get(var3 - 2)).getWallOffset();
            }

            int var6 = var4.getWallOffset();
            long var7 = var4.getMillis() + (long)var5;
            long var9 = var2.getMillis() + (long)var6;
            if (var9 != var7) {
               var1.add(var2);
               return true;
            } else {
               Transition var11 = (Transition)var1.remove(var3 - 1);
               Transition var12 = var2.withMillis(var11.getMillis());
               return this.addTransition(var1, var12);
            }
         }
      }
   }

   public void writeTo(String var1, OutputStream var2) throws IOException {
      if (var2 instanceof DataOutput) {
         this.writeTo(var1, (DataOutput)var2);
      } else {
         DataOutputStream var3 = new DataOutputStream(var2);
         this.writeTo(var1, (DataOutput)var3);
         var3.flush();
      }

   }

   public void writeTo(String var1, DataOutput var2) throws IOException {
      DateTimeZone var3 = this.toDateTimeZone(var1, false);
      if (var3 instanceof FixedDateTimeZone) {
         var2.writeByte(70);
         var2.writeUTF(var3.getNameKey(0L));
         writeMillis(var2, (long)var3.getOffset(0L));
         writeMillis(var2, (long)var3.getStandardOffset(0L));
      } else {
         if (var3 instanceof CachedDateTimeZone) {
            var2.writeByte(67);
            var3 = ((CachedDateTimeZone)var3).getUncachedZone();
         } else {
            var2.writeByte(80);
         }

         ((PrecalculatedZone)var3).writeTo(var2);
      }

   }

   private static final class OfYear {
      final char iMode;
      final int iMonthOfYear;
      final int iDayOfMonth;
      final int iDayOfWeek;
      final boolean iAdvance;
      final int iMillisOfDay;

      static OfYear readFrom(DataInput var0) throws IOException {
         return new OfYear((char)var0.readUnsignedByte(), var0.readUnsignedByte(), var0.readByte(), var0.readUnsignedByte(), var0.readBoolean(), (int)DateTimeZoneBuilder.readMillis(var0));
      }

      OfYear(char var1, int var2, int var3, int var4, boolean var5, int var6) {
         if (var1 != 'u' && var1 != 'w' && var1 != 's') {
            throw new IllegalArgumentException("Unknown mode: " + var1);
         } else {
            this.iMode = var1;
            this.iMonthOfYear = var2;
            this.iDayOfMonth = var3;
            this.iDayOfWeek = var4;
            this.iAdvance = var5;
            this.iMillisOfDay = var6;
         }
      }

      public long setInstant(int var1, int var2, int var3) {
         int var4;
         if (this.iMode == 'w') {
            var4 = var2 + var3;
         } else if (this.iMode == 's') {
            var4 = var2;
         } else {
            var4 = 0;
         }

         ISOChronology var5 = ISOChronology.getInstanceUTC();
         long var6 = ((Chronology)var5).year().set(0L, var1);
         var6 = ((Chronology)var5).monthOfYear().set(var6, this.iMonthOfYear);
         var6 = ((Chronology)var5).millisOfDay().set(var6, this.iMillisOfDay);
         var6 = this.setDayOfMonth(var5, var6);
         if (this.iDayOfWeek != 0) {
            var6 = this.setDayOfWeek(var5, var6);
         }

         return var6 - (long)var4;
      }

      public long next(long var1, int var3, int var4) {
         int var5;
         if (this.iMode == 'w') {
            var5 = var3 + var4;
         } else if (this.iMode == 's') {
            var5 = var3;
         } else {
            var5 = 0;
         }

         var1 += (long)var5;
         ISOChronology var6 = ISOChronology.getInstanceUTC();
         long var7 = ((Chronology)var6).monthOfYear().set(var1, this.iMonthOfYear);
         var7 = ((Chronology)var6).millisOfDay().set(var7, 0);
         var7 = ((Chronology)var6).millisOfDay().add(var7, Math.min(this.iMillisOfDay, 86399999));
         var7 = this.setDayOfMonthNext(var6, var7);
         if (this.iDayOfWeek == 0) {
            if (var7 <= var1) {
               var7 = ((Chronology)var6).year().add(var7, 1);
               var7 = this.setDayOfMonthNext(var6, var7);
            }
         } else {
            var7 = this.setDayOfWeek(var6, var7);
            if (var7 <= var1) {
               var7 = ((Chronology)var6).year().add(var7, 1);
               var7 = ((Chronology)var6).monthOfYear().set(var7, this.iMonthOfYear);
               var7 = this.setDayOfMonthNext(var6, var7);
               var7 = this.setDayOfWeek(var6, var7);
            }
         }

         var7 = ((Chronology)var6).millisOfDay().set(var7, 0);
         var7 = ((Chronology)var6).millisOfDay().add(var7, this.iMillisOfDay);
         return var7 - (long)var5;
      }

      public long previous(long var1, int var3, int var4) {
         int var5;
         if (this.iMode == 'w') {
            var5 = var3 + var4;
         } else if (this.iMode == 's') {
            var5 = var3;
         } else {
            var5 = 0;
         }

         var1 += (long)var5;
         ISOChronology var6 = ISOChronology.getInstanceUTC();
         long var7 = ((Chronology)var6).monthOfYear().set(var1, this.iMonthOfYear);
         var7 = ((Chronology)var6).millisOfDay().set(var7, 0);
         var7 = ((Chronology)var6).millisOfDay().add(var7, this.iMillisOfDay);
         var7 = this.setDayOfMonthPrevious(var6, var7);
         if (this.iDayOfWeek == 0) {
            if (var7 >= var1) {
               var7 = ((Chronology)var6).year().add(var7, -1);
               var7 = this.setDayOfMonthPrevious(var6, var7);
            }
         } else {
            var7 = this.setDayOfWeek(var6, var7);
            if (var7 >= var1) {
               var7 = ((Chronology)var6).year().add(var7, -1);
               var7 = ((Chronology)var6).monthOfYear().set(var7, this.iMonthOfYear);
               var7 = this.setDayOfMonthPrevious(var6, var7);
               var7 = this.setDayOfWeek(var6, var7);
            }
         }

         var7 = ((Chronology)var6).millisOfDay().set(var7, 0);
         var7 = ((Chronology)var6).millisOfDay().add(var7, this.iMillisOfDay);
         return var7 - (long)var5;
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof OfYear)) {
            return false;
         } else {
            OfYear var2 = (OfYear)var1;
            return this.iMode == var2.iMode && this.iMonthOfYear == var2.iMonthOfYear && this.iDayOfMonth == var2.iDayOfMonth && this.iDayOfWeek == var2.iDayOfWeek && this.iAdvance == var2.iAdvance && this.iMillisOfDay == var2.iMillisOfDay;
         }
      }

      public int hashCode() {
         return Arrays.hashCode(new Object[]{this.iMode, this.iMonthOfYear, this.iDayOfMonth, this.iDayOfWeek, this.iAdvance, this.iMillisOfDay});
      }

      public String toString() {
         return "[OfYear]\nMode: " + this.iMode + '\n' + "MonthOfYear: " + this.iMonthOfYear + '\n' + "DayOfMonth: " + this.iDayOfMonth + '\n' + "DayOfWeek: " + this.iDayOfWeek + '\n' + "AdvanceDayOfWeek: " + this.iAdvance + '\n' + "MillisOfDay: " + this.iMillisOfDay + '\n';
      }

      public void writeTo(DataOutput var1) throws IOException {
         var1.writeByte(this.iMode);
         var1.writeByte(this.iMonthOfYear);
         var1.writeByte(this.iDayOfMonth);
         var1.writeByte(this.iDayOfWeek);
         var1.writeBoolean(this.iAdvance);
         DateTimeZoneBuilder.writeMillis(var1, (long)this.iMillisOfDay);
      }

      private long setDayOfMonthNext(Chronology var1, long var2) {
         try {
            var2 = this.setDayOfMonth(var1, var2);
         } catch (IllegalArgumentException var5) {
            if (this.iMonthOfYear != 2 || this.iDayOfMonth != 29) {
               throw var5;
            }

            while(!var1.year().isLeap(var2)) {
               var2 = var1.year().add(var2, 1);
            }

            var2 = this.setDayOfMonth(var1, var2);
         }

         return var2;
      }

      private long setDayOfMonthPrevious(Chronology var1, long var2) {
         try {
            var2 = this.setDayOfMonth(var1, var2);
         } catch (IllegalArgumentException var5) {
            if (this.iMonthOfYear != 2 || this.iDayOfMonth != 29) {
               throw var5;
            }

            while(!var1.year().isLeap(var2)) {
               var2 = var1.year().add(var2, -1);
            }

            var2 = this.setDayOfMonth(var1, var2);
         }

         return var2;
      }

      private long setDayOfMonth(Chronology var1, long var2) {
         if (this.iDayOfMonth >= 0) {
            var2 = var1.dayOfMonth().set(var2, this.iDayOfMonth);
         } else {
            var2 = var1.dayOfMonth().set(var2, 1);
            var2 = var1.monthOfYear().add(var2, 1);
            var2 = var1.dayOfMonth().add(var2, this.iDayOfMonth);
         }

         return var2;
      }

      private long setDayOfWeek(Chronology var1, long var2) {
         int var4 = var1.dayOfWeek().get(var2);
         int var5 = this.iDayOfWeek - var4;
         if (var5 != 0) {
            if (this.iAdvance) {
               if (var5 < 0) {
                  var5 += 7;
               }
            } else if (var5 > 0) {
               var5 -= 7;
            }

            var2 = var1.dayOfWeek().add(var2, var5);
         }

         return var2;
      }
   }

   private static final class Recurrence {
      final OfYear iOfYear;
      final String iNameKey;
      final int iSaveMillis;

      static Recurrence readFrom(DataInput var0) throws IOException {
         return new Recurrence(DateTimeZoneBuilder.OfYear.readFrom(var0), var0.readUTF(), (int)DateTimeZoneBuilder.readMillis(var0));
      }

      Recurrence(OfYear var1, String var2, int var3) {
         this.iOfYear = var1;
         this.iNameKey = var2;
         this.iSaveMillis = var3;
      }

      public OfYear getOfYear() {
         return this.iOfYear;
      }

      public long next(long var1, int var3, int var4) {
         return this.iOfYear.next(var1, var3, var4);
      }

      public long previous(long var1, int var3, int var4) {
         return this.iOfYear.previous(var1, var3, var4);
      }

      public String getNameKey() {
         return this.iNameKey;
      }

      public int getSaveMillis() {
         return this.iSaveMillis;
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Recurrence)) {
            return false;
         } else {
            Recurrence var2 = (Recurrence)var1;
            return this.iSaveMillis == var2.iSaveMillis && this.iNameKey.equals(var2.iNameKey) && this.iOfYear.equals(var2.iOfYear);
         }
      }

      public int hashCode() {
         return Arrays.hashCode(new Object[]{this.iSaveMillis, this.iNameKey, this.iOfYear});
      }

      public void writeTo(DataOutput var1) throws IOException {
         this.iOfYear.writeTo(var1);
         var1.writeUTF(this.iNameKey);
         DateTimeZoneBuilder.writeMillis(var1, (long)this.iSaveMillis);
      }

      Recurrence rename(String var1) {
         return new Recurrence(this.iOfYear, var1, this.iSaveMillis);
      }

      Recurrence renameAppend(String var1) {
         return this.rename((this.iNameKey + var1).intern());
      }

      public String toString() {
         return this.iOfYear + " named " + this.iNameKey + " at " + this.iSaveMillis;
      }
   }

   private static final class Rule {
      final Recurrence iRecurrence;
      final int iFromYear;
      final int iToYear;

      Rule(Recurrence var1, int var2, int var3) {
         this.iRecurrence = var1;
         this.iFromYear = var2;
         this.iToYear = var3;
      }

      public int getFromYear() {
         return this.iFromYear;
      }

      public int getToYear() {
         return this.iToYear;
      }

      public OfYear getOfYear() {
         return this.iRecurrence.getOfYear();
      }

      public String getNameKey() {
         return this.iRecurrence.getNameKey();
      }

      public int getSaveMillis() {
         return this.iRecurrence.getSaveMillis();
      }

      public long next(long var1, int var3, int var4) {
         ISOChronology var5 = ISOChronology.getInstanceUTC();
         int var6 = var3 + var4;
         long var12 = var1;
         int var9;
         if (var1 == Long.MIN_VALUE) {
            var9 = Integer.MIN_VALUE;
         } else {
            var9 = ((Chronology)var5).year().get(var1 + (long)var6);
         }

         if (var9 < this.iFromYear) {
            var12 = ((Chronology)var5).year().set(0L, this.iFromYear) - (long)var6;
            --var12;
         }

         long var10 = this.iRecurrence.next(var12, var3, var4);
         if (var10 > var1) {
            var9 = ((Chronology)var5).year().get(var10 + (long)var6);
            if (var9 > this.iToYear) {
               var10 = var1;
            }
         }

         return var10;
      }

      public String toString() {
         return this.iFromYear + " to " + this.iToYear + " using " + this.iRecurrence;
      }
   }

   private static final class Transition {
      private final long iMillis;
      private final String iNameKey;
      private final int iWallOffset;
      private final int iStandardOffset;

      Transition(long var1, Transition var3) {
         this.iMillis = var1;
         this.iNameKey = var3.iNameKey;
         this.iWallOffset = var3.iWallOffset;
         this.iStandardOffset = var3.iStandardOffset;
      }

      Transition(long var1, Rule var3, int var4) {
         this.iMillis = var1;
         this.iNameKey = var3.getNameKey();
         this.iWallOffset = var4 + var3.getSaveMillis();
         this.iStandardOffset = var4;
      }

      Transition(long var1, String var3, int var4, int var5) {
         this.iMillis = var1;
         this.iNameKey = var3;
         this.iWallOffset = var4;
         this.iStandardOffset = var5;
      }

      public long getMillis() {
         return this.iMillis;
      }

      public String getNameKey() {
         return this.iNameKey;
      }

      public int getWallOffset() {
         return this.iWallOffset;
      }

      public int getStandardOffset() {
         return this.iStandardOffset;
      }

      public int getSaveMillis() {
         return this.iWallOffset - this.iStandardOffset;
      }

      public Transition withMillis(long var1) {
         return new Transition(var1, this.iNameKey, this.iWallOffset, this.iStandardOffset);
      }

      public boolean isTransitionFrom(Transition var1) {
         if (var1 == null) {
            return true;
         } else {
            return this.iMillis > var1.iMillis && (this.iWallOffset != var1.iWallOffset || this.iStandardOffset != var1.iStandardOffset || !this.iNameKey.equals(var1.iNameKey));
         }
      }

      public String toString() {
         return new DateTime(this.iMillis, DateTimeZone.UTC) + " " + this.iStandardOffset + " " + this.iWallOffset;
      }
   }

   private static final class RuleSet {
      private static final int YEAR_LIMIT;
      private int iStandardOffset;
      private ArrayList iRules;
      private String iInitialNameKey;
      private int iInitialSaveMillis;
      private int iUpperYear;
      private OfYear iUpperOfYear;

      RuleSet() {
         this.iRules = new ArrayList(10);
         this.iUpperYear = Integer.MAX_VALUE;
      }

      RuleSet(RuleSet var1) {
         this.iStandardOffset = var1.iStandardOffset;
         this.iRules = new ArrayList(var1.iRules);
         this.iInitialNameKey = var1.iInitialNameKey;
         this.iInitialSaveMillis = var1.iInitialSaveMillis;
         this.iUpperYear = var1.iUpperYear;
         this.iUpperOfYear = var1.iUpperOfYear;
      }

      public int getStandardOffset() {
         return this.iStandardOffset;
      }

      public void setStandardOffset(int var1) {
         this.iStandardOffset = var1;
      }

      public void setFixedSavings(String var1, int var2) {
         this.iInitialNameKey = var1;
         this.iInitialSaveMillis = var2;
      }

      public void addRule(Rule var1) {
         if (!this.iRules.contains(var1)) {
            this.iRules.add(var1);
         }

      }

      public void setUpperLimit(int var1, OfYear var2) {
         this.iUpperYear = var1;
         this.iUpperOfYear = var2;
      }

      public Transition firstTransition(long var1) {
         if (this.iInitialNameKey != null) {
            return new Transition(var1, this.iInitialNameKey, this.iStandardOffset + this.iInitialSaveMillis, this.iStandardOffset);
         } else {
            ArrayList var3 = new ArrayList(this.iRules);
            long var4 = Long.MIN_VALUE;
            int var6 = 0;

            Transition var7;
            Transition var8;
            for(var7 = null; (var8 = this.nextTransition(var4, var6)) != null; var6 = var8.getSaveMillis()) {
               var4 = var8.getMillis();
               if (var4 == var1) {
                  var7 = new Transition(var1, var8);
                  break;
               }

               if (var4 > var1) {
                  if (var7 == null) {
                     for(Rule var10 : var3) {
                        if (var10.getSaveMillis() == 0) {
                           var7 = new Transition(var1, var10, this.iStandardOffset);
                           break;
                        }
                     }
                  }

                  if (var7 == null) {
                     var7 = new Transition(var1, var8.getNameKey(), this.iStandardOffset, this.iStandardOffset);
                  }
                  break;
               }

               var7 = new Transition(var1, var8);
            }

            this.iRules = var3;
            return var7;
         }
      }

      public Transition nextTransition(long var1, int var3) {
         ISOChronology var4 = ISOChronology.getInstanceUTC();
         Rule var5 = null;
         long var6 = Long.MAX_VALUE;
         Iterator var8 = this.iRules.iterator();

         while(var8.hasNext()) {
            Rule var9 = (Rule)var8.next();
            long var10 = var9.next(var1, this.iStandardOffset, var3);
            if (var10 <= var1) {
               var8.remove();
            } else if (var10 <= var6) {
               var5 = var9;
               var6 = var10;
            }
         }

         if (var5 == null) {
            return null;
         } else if (((Chronology)var4).year().get(var6) >= YEAR_LIMIT) {
            return null;
         } else {
            if (this.iUpperYear < Integer.MAX_VALUE) {
               long var12 = this.iUpperOfYear.setInstant(this.iUpperYear, this.iStandardOffset, var3);
               if (var6 >= var12) {
                  return null;
               }
            }

            return new Transition(var6, var5, this.iStandardOffset);
         }
      }

      public long getUpperLimit(int var1) {
         return this.iUpperYear == Integer.MAX_VALUE ? Long.MAX_VALUE : this.iUpperOfYear.setInstant(this.iUpperYear, this.iStandardOffset, var1);
      }

      public DSTZone buildTailZone(String var1) {
         if (this.iRules.size() == 2) {
            Rule var2 = (Rule)this.iRules.get(0);
            Rule var3 = (Rule)this.iRules.get(1);
            if (var2.getToYear() == Integer.MAX_VALUE && var3.getToYear() == Integer.MAX_VALUE) {
               return new DSTZone(var1, this.iStandardOffset, var2.iRecurrence, var3.iRecurrence);
            }
         }

         return null;
      }

      public String toString() {
         return this.iInitialNameKey + " initial: " + this.iInitialSaveMillis + " std: " + this.iStandardOffset + " upper: " + this.iUpperYear + " " + this.iUpperOfYear + " " + this.iRules;
      }

      static {
         long var0 = DateTimeUtils.currentTimeMillis();
         YEAR_LIMIT = ISOChronology.getInstanceUTC().year().get(var0) + 100;
      }
   }

   private static final class DSTZone extends DateTimeZone {
      private static final long serialVersionUID = 6941492635554961361L;
      final int iStandardOffset;
      final Recurrence iStartRecurrence;
      final Recurrence iEndRecurrence;

      static DSTZone readFrom(DataInput var0, String var1) throws IOException {
         return new DSTZone(var1, (int)DateTimeZoneBuilder.readMillis(var0), DateTimeZoneBuilder.Recurrence.readFrom(var0), DateTimeZoneBuilder.Recurrence.readFrom(var0));
      }

      DSTZone(String var1, int var2, Recurrence var3, Recurrence var4) {
         super(var1);
         this.iStandardOffset = var2;
         this.iStartRecurrence = var3;
         this.iEndRecurrence = var4;
      }

      public String getNameKey(long var1) {
         return this.findMatchingRecurrence(var1).getNameKey();
      }

      public int getOffset(long var1) {
         return this.iStandardOffset + this.findMatchingRecurrence(var1).getSaveMillis();
      }

      public int getStandardOffset(long var1) {
         return this.iStandardOffset;
      }

      public boolean isFixed() {
         return false;
      }

      public long nextTransition(long var1) {
         int var3 = this.iStandardOffset;
         Recurrence var4 = this.iStartRecurrence;
         Recurrence var5 = this.iEndRecurrence;

         long var6;
         try {
            var6 = var4.next(var1, var3, var5.getSaveMillis());
            if (var1 > 0L && var6 < 0L) {
               var6 = var1;
            }
         } catch (IllegalArgumentException var13) {
            var6 = var1;
         } catch (ArithmeticException var14) {
            var6 = var1;
         }

         long var8;
         try {
            var8 = var5.next(var1, var3, var4.getSaveMillis());
            if (var1 > 0L && var8 < 0L) {
               var8 = var1;
            }
         } catch (IllegalArgumentException var11) {
            var8 = var1;
         } catch (ArithmeticException var12) {
            var8 = var1;
         }

         return var6 > var8 ? var8 : var6;
      }

      public long previousTransition(long var1) {
         ++var1;
         int var3 = this.iStandardOffset;
         Recurrence var4 = this.iStartRecurrence;
         Recurrence var5 = this.iEndRecurrence;

         long var6;
         try {
            var6 = var4.previous(var1, var3, var5.getSaveMillis());
            if (var1 < 0L && var6 > 0L) {
               var6 = var1;
            }
         } catch (IllegalArgumentException var13) {
            var6 = var1;
         } catch (ArithmeticException var14) {
            var6 = var1;
         }

         long var8;
         try {
            var8 = var5.previous(var1, var3, var4.getSaveMillis());
            if (var1 < 0L && var8 > 0L) {
               var8 = var1;
            }
         } catch (IllegalArgumentException var11) {
            var8 = var1;
         } catch (ArithmeticException var12) {
            var8 = var1;
         }

         return (var6 > var8 ? var6 : var8) - 1L;
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof DSTZone)) {
            return false;
         } else {
            DSTZone var2 = (DSTZone)var1;
            return this.getID().equals(var2.getID()) && this.iStandardOffset == var2.iStandardOffset && this.iStartRecurrence.equals(var2.iStartRecurrence) && this.iEndRecurrence.equals(var2.iEndRecurrence);
         }
      }

      public int hashCode() {
         return Arrays.hashCode(new Object[]{this.iStandardOffset, this.iStartRecurrence, this.iEndRecurrence});
      }

      public void writeTo(DataOutput var1) throws IOException {
         DateTimeZoneBuilder.writeMillis(var1, (long)this.iStandardOffset);
         this.iStartRecurrence.writeTo(var1);
         this.iEndRecurrence.writeTo(var1);
      }

      private Recurrence findMatchingRecurrence(long var1) {
         int var3 = this.iStandardOffset;
         Recurrence var4 = this.iStartRecurrence;
         Recurrence var5 = this.iEndRecurrence;

         long var6;
         try {
            var6 = var4.next(var1, var3, var5.getSaveMillis());
         } catch (IllegalArgumentException var13) {
            var6 = var1;
         } catch (ArithmeticException var14) {
            var6 = var1;
         }

         long var8;
         try {
            var8 = var5.next(var1, var3, var4.getSaveMillis());
         } catch (IllegalArgumentException var11) {
            var8 = var1;
         } catch (ArithmeticException var12) {
            var8 = var1;
         }

         return var6 > var8 ? var4 : var5;
      }
   }

   private static final class PrecalculatedZone extends DateTimeZone {
      private static final long serialVersionUID = 7811976468055766265L;
      private final long[] iTransitions;
      private final int[] iWallOffsets;
      private final int[] iStandardOffsets;
      private final String[] iNameKeys;
      private final DSTZone iTailZone;

      static PrecalculatedZone readFrom(DataInput var0, String var1) throws IOException {
         int var2 = var0.readUnsignedShort();
         String[] var3 = new String[var2];

         for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = var0.readUTF();
         }

         int var12 = var0.readInt();
         long[] var5 = new long[var12];
         int[] var6 = new int[var12];
         int[] var7 = new int[var12];
         String[] var8 = new String[var12];

         for(int var9 = 0; var9 < var12; ++var9) {
            var5[var9] = DateTimeZoneBuilder.readMillis(var0);
            var6[var9] = (int)DateTimeZoneBuilder.readMillis(var0);
            var7[var9] = (int)DateTimeZoneBuilder.readMillis(var0);

            try {
               int var10;
               if (var2 < 256) {
                  var10 = var0.readUnsignedByte();
               } else {
                  var10 = var0.readUnsignedShort();
               }

               var8[var9] = var3[var10];
            } catch (ArrayIndexOutOfBoundsException var11) {
               throw new IOException("Invalid encoding");
            }
         }

         DSTZone var13 = null;
         if (var0.readBoolean()) {
            var13 = DateTimeZoneBuilder.DSTZone.readFrom(var0, var1);
         }

         return new PrecalculatedZone(var1, var5, var6, var7, var8, var13);
      }

      static PrecalculatedZone create(String var0, boolean var1, ArrayList var2, DSTZone var3) {
         int var4 = var2.size();
         if (var4 == 0) {
            throw new IllegalArgumentException();
         } else {
            long[] var5 = new long[var4];
            int[] var6 = new int[var4];
            int[] var7 = new int[var4];
            String[] var8 = new String[var4];
            Transition var9 = null;

            for(int var10 = 0; var10 < var4; ++var10) {
               Transition var11 = (Transition)var2.get(var10);
               if (!var11.isTransitionFrom(var9)) {
                  throw new IllegalArgumentException(var0);
               }

               var5[var10] = var11.getMillis();
               var6[var10] = var11.getWallOffset();
               var7[var10] = var11.getStandardOffset();
               var8[var10] = var11.getNameKey();
               var9 = var11;
            }

            String[] var25 = new String[5];
            String[][] var26 = (new DateFormatSymbols(Locale.ENGLISH)).getZoneStrings();

            for(int var12 = 0; var12 < var26.length; ++var12) {
               String[] var13 = var26[var12];
               if (var13 != null && var13.length == 5 && var0.equals(var13[0])) {
                  var25 = var13;
               }
            }

            ISOChronology var27 = ISOChronology.getInstanceUTC();

            for(int var28 = 0; var28 < var8.length - 1; ++var28) {
               String var14 = var8[var28];
               String var15 = var8[var28 + 1];
               long var16 = (long)var6[var28];
               long var18 = (long)var6[var28 + 1];
               long var20 = (long)var7[var28];
               long var22 = (long)var7[var28 + 1];
               Period var24 = new Period(var5[var28], var5[var28 + 1], PeriodType.yearMonthDay(), var27);
               if (var16 != var18 && var20 == var22 && var14.equals(var15) && var24.getYears() == 0 && var24.getMonths() > 4 && var24.getMonths() < 8 && var14.equals(var25[2]) && var14.equals(var25[4])) {
                  if (ZoneInfoLogger.verbose()) {
                     System.out.println("Fixing duplicate name key - " + var15);
                     System.out.println("     - " + new DateTime(var5[var28], var27) + " - " + new DateTime(var5[var28 + 1], var27));
                  }

                  if (var16 > var18) {
                     var8[var28] = (var14 + "-Summer").intern();
                  } else {
                     var8[var28 + 1] = (var15 + "-Summer").intern();
                     ++var28;
                  }
               }
            }

            if (var3 != null && var3.iStartRecurrence.getNameKey().equals(var3.iEndRecurrence.getNameKey())) {
               if (ZoneInfoLogger.verbose()) {
                  System.out.println("Fixing duplicate recurrent name key - " + var3.iStartRecurrence.getNameKey());
               }

               if (var3.iStartRecurrence.getSaveMillis() > 0) {
                  var3 = new DSTZone(var3.getID(), var3.iStandardOffset, var3.iStartRecurrence.renameAppend("-Summer"), var3.iEndRecurrence);
               } else {
                  var3 = new DSTZone(var3.getID(), var3.iStandardOffset, var3.iStartRecurrence, var3.iEndRecurrence.renameAppend("-Summer"));
               }
            }

            return new PrecalculatedZone(var1 ? var0 : "", var5, var6, var7, var8, var3);
         }
      }

      private PrecalculatedZone(String var1, long[] var2, int[] var3, int[] var4, String[] var5, DSTZone var6) {
         super(var1);
         this.iTransitions = var2;
         this.iWallOffsets = var3;
         this.iStandardOffsets = var4;
         this.iNameKeys = var5;
         this.iTailZone = var6;
      }

      public String getNameKey(long var1) {
         long[] var3 = this.iTransitions;
         int var4 = Arrays.binarySearch(var3, var1);
         if (var4 >= 0) {
            return this.iNameKeys[var4];
         } else {
            var4 = ~var4;
            if (var4 < var3.length) {
               return var4 > 0 ? this.iNameKeys[var4 - 1] : "UTC";
            } else {
               return this.iTailZone == null ? this.iNameKeys[var4 - 1] : this.iTailZone.getNameKey(var1);
            }
         }
      }

      public int getOffset(long var1) {
         long[] var3 = this.iTransitions;
         int var4 = Arrays.binarySearch(var3, var1);
         if (var4 >= 0) {
            return this.iWallOffsets[var4];
         } else {
            var4 = ~var4;
            if (var4 < var3.length) {
               return var4 > 0 ? this.iWallOffsets[var4 - 1] : 0;
            } else {
               return this.iTailZone == null ? this.iWallOffsets[var4 - 1] : this.iTailZone.getOffset(var1);
            }
         }
      }

      public int getStandardOffset(long var1) {
         long[] var3 = this.iTransitions;
         int var4 = Arrays.binarySearch(var3, var1);
         if (var4 >= 0) {
            return this.iStandardOffsets[var4];
         } else {
            var4 = ~var4;
            if (var4 < var3.length) {
               return var4 > 0 ? this.iStandardOffsets[var4 - 1] : 0;
            } else {
               return this.iTailZone == null ? this.iStandardOffsets[var4 - 1] : this.iTailZone.getStandardOffset(var1);
            }
         }
      }

      public boolean isFixed() {
         return false;
      }

      public long nextTransition(long var1) {
         long[] var3 = this.iTransitions;
         int var4 = Arrays.binarySearch(var3, var1);
         var4 = var4 >= 0 ? var4 + 1 : ~var4;
         if (var4 < var3.length) {
            return var3[var4];
         } else if (this.iTailZone == null) {
            return var1;
         } else {
            long var5 = var3[var3.length - 1];
            if (var1 < var5) {
               var1 = var5;
            }

            return this.iTailZone.nextTransition(var1);
         }
      }

      public long previousTransition(long var1) {
         long[] var3 = this.iTransitions;
         int var4 = Arrays.binarySearch(var3, var1);
         if (var4 >= 0) {
            return var1 > Long.MIN_VALUE ? var1 - 1L : var1;
         } else {
            var4 = ~var4;
            if (var4 < var3.length) {
               if (var4 > 0) {
                  long var9 = var3[var4 - 1];
                  if (var9 > Long.MIN_VALUE) {
                     return var9 - 1L;
                  }
               }

               return var1;
            } else {
               if (this.iTailZone != null) {
                  long var5 = this.iTailZone.previousTransition(var1);
                  if (var5 < var1) {
                     return var5;
                  }
               }

               long var8 = var3[var4 - 1];
               return var8 > Long.MIN_VALUE ? var8 - 1L : var1;
            }
         }
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof PrecalculatedZone)) {
            return false;
         } else {
            boolean var10000;
            label44: {
               PrecalculatedZone var2 = (PrecalculatedZone)var1;
               if (this.getID().equals(var2.getID()) && Arrays.equals(this.iTransitions, var2.iTransitions) && Arrays.equals(this.iNameKeys, var2.iNameKeys) && Arrays.equals(this.iWallOffsets, var2.iWallOffsets) && Arrays.equals(this.iStandardOffsets, var2.iStandardOffsets)) {
                  if (this.iTailZone == null) {
                     if (null == var2.iTailZone) {
                        break label44;
                     }
                  } else if (this.iTailZone.equals(var2.iTailZone)) {
                     break label44;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      public int hashCode() {
         return this.getID().hashCode();
      }

      public void writeTo(DataOutput var1) throws IOException {
         int var2 = this.iTransitions.length;
         HashSet var3 = new HashSet();

         for(int var4 = 0; var4 < var2; ++var4) {
            var3.add(this.iNameKeys[var4]);
         }

         int var10 = var3.size();
         if (var10 > 65535) {
            throw new UnsupportedOperationException("String pool is too large");
         } else {
            String[] var5 = new String[var10];
            Iterator var6 = var3.iterator();

            for(int var7 = 0; var6.hasNext(); ++var7) {
               var5[var7] = (String)var6.next();
            }

            var1.writeShort(var10);

            for(int var11 = 0; var11 < var10; ++var11) {
               var1.writeUTF(var5[var11]);
            }

            var1.writeInt(var2);

            for(int var12 = 0; var12 < var2; ++var12) {
               DateTimeZoneBuilder.writeMillis(var1, this.iTransitions[var12]);
               DateTimeZoneBuilder.writeMillis(var1, (long)this.iWallOffsets[var12]);
               DateTimeZoneBuilder.writeMillis(var1, (long)this.iStandardOffsets[var12]);
               String var8 = this.iNameKeys[var12];

               for(int var9 = 0; var9 < var10; ++var9) {
                  if (var5[var9].equals(var8)) {
                     if (var10 < 256) {
                        var1.writeByte(var9);
                     } else {
                        var1.writeShort(var9);
                     }
                     break;
                  }
               }
            }

            var1.writeBoolean(this.iTailZone != null);
            if (this.iTailZone != null) {
               this.iTailZone.writeTo(var1);
            }

         }
      }

      public boolean isCachable() {
         if (this.iTailZone != null) {
            return true;
         } else {
            long[] var1 = this.iTransitions;
            if (var1.length <= 1) {
               return false;
            } else {
               double var2 = (double)0.0F;
               int var4 = 0;

               for(int var5 = 1; var5 < var1.length; ++var5) {
                  long var6 = var1[var5] - var1[var5 - 1];
                  if (var6 < 63158400000L) {
                     var2 += (double)var6;
                     ++var4;
                  }
               }

               if (var4 > 0) {
                  double var8 = var2 / (double)var4;
                  var8 /= (double)8.64E7F;
                  if (var8 >= (double)25.0F) {
                     return true;
                  }
               }

               return false;
            }
         }
      }
   }
}
