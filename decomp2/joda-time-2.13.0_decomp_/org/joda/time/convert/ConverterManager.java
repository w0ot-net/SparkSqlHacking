package org.joda.time.convert;

import org.joda.time.JodaTimePermission;

public final class ConverterManager {
   private ConverterSet iInstantConverters;
   private ConverterSet iPartialConverters;
   private ConverterSet iDurationConverters;
   private ConverterSet iPeriodConverters;
   private ConverterSet iIntervalConverters;

   public static ConverterManager getInstance() {
      return ConverterManager.LazyConverterManagerHolder.INSTANCE;
   }

   protected ConverterManager() {
      this.iInstantConverters = new ConverterSet(new Converter[]{ReadableInstantConverter.INSTANCE, StringConverter.INSTANCE, CalendarConverter.INSTANCE, DateConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
      this.iPartialConverters = new ConverterSet(new Converter[]{ReadablePartialConverter.INSTANCE, ReadableInstantConverter.INSTANCE, StringConverter.INSTANCE, CalendarConverter.INSTANCE, DateConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
      this.iDurationConverters = new ConverterSet(new Converter[]{ReadableDurationConverter.INSTANCE, ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
      this.iPeriodConverters = new ConverterSet(new Converter[]{ReadableDurationConverter.INSTANCE, ReadablePeriodConverter.INSTANCE, ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, NullConverter.INSTANCE});
      this.iIntervalConverters = new ConverterSet(new Converter[]{ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, NullConverter.INSTANCE});
   }

   public InstantConverter getInstantConverter(Object var1) {
      InstantConverter var2 = (InstantConverter)this.iInstantConverters.select(var1 == null ? null : var1.getClass());
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalArgumentException("No instant converter found for type: " + (var1 == null ? "null" : var1.getClass().getName()));
      }
   }

   public InstantConverter[] getInstantConverters() {
      ConverterSet var1 = this.iInstantConverters;
      InstantConverter[] var2 = new InstantConverter[var1.size()];
      var1.copyInto(var2);
      return var2;
   }

   public InstantConverter addInstantConverter(InstantConverter var1) throws SecurityException {
      this.checkAlterInstantConverters();
      if (var1 == null) {
         return null;
      } else {
         InstantConverter[] var2 = new InstantConverter[1];
         this.iInstantConverters = this.iInstantConverters.add(var1, var2);
         return var2[0];
      }
   }

   public InstantConverter removeInstantConverter(InstantConverter var1) throws SecurityException {
      this.checkAlterInstantConverters();
      if (var1 == null) {
         return null;
      } else {
         InstantConverter[] var2 = new InstantConverter[1];
         this.iInstantConverters = this.iInstantConverters.remove(var1, var2);
         return var2[0];
      }
   }

   private void checkAlterInstantConverters() throws SecurityException {
      SecurityManager var1 = System.getSecurityManager();
      if (var1 != null) {
         var1.checkPermission(new JodaTimePermission("ConverterManager.alterInstantConverters"));
      }

   }

   public PartialConverter getPartialConverter(Object var1) {
      PartialConverter var2 = (PartialConverter)this.iPartialConverters.select(var1 == null ? null : var1.getClass());
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalArgumentException("No partial converter found for type: " + (var1 == null ? "null" : var1.getClass().getName()));
      }
   }

   public PartialConverter[] getPartialConverters() {
      ConverterSet var1 = this.iPartialConverters;
      PartialConverter[] var2 = new PartialConverter[var1.size()];
      var1.copyInto(var2);
      return var2;
   }

   public PartialConverter addPartialConverter(PartialConverter var1) throws SecurityException {
      this.checkAlterPartialConverters();
      if (var1 == null) {
         return null;
      } else {
         PartialConverter[] var2 = new PartialConverter[1];
         this.iPartialConverters = this.iPartialConverters.add(var1, var2);
         return var2[0];
      }
   }

   public PartialConverter removePartialConverter(PartialConverter var1) throws SecurityException {
      this.checkAlterPartialConverters();
      if (var1 == null) {
         return null;
      } else {
         PartialConverter[] var2 = new PartialConverter[1];
         this.iPartialConverters = this.iPartialConverters.remove(var1, var2);
         return var2[0];
      }
   }

   private void checkAlterPartialConverters() throws SecurityException {
      SecurityManager var1 = System.getSecurityManager();
      if (var1 != null) {
         var1.checkPermission(new JodaTimePermission("ConverterManager.alterPartialConverters"));
      }

   }

   public DurationConverter getDurationConverter(Object var1) {
      DurationConverter var2 = (DurationConverter)this.iDurationConverters.select(var1 == null ? null : var1.getClass());
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalArgumentException("No duration converter found for type: " + (var1 == null ? "null" : var1.getClass().getName()));
      }
   }

   public DurationConverter[] getDurationConverters() {
      ConverterSet var1 = this.iDurationConverters;
      DurationConverter[] var2 = new DurationConverter[var1.size()];
      var1.copyInto(var2);
      return var2;
   }

   public DurationConverter addDurationConverter(DurationConverter var1) throws SecurityException {
      this.checkAlterDurationConverters();
      if (var1 == null) {
         return null;
      } else {
         DurationConverter[] var2 = new DurationConverter[1];
         this.iDurationConverters = this.iDurationConverters.add(var1, var2);
         return var2[0];
      }
   }

   public DurationConverter removeDurationConverter(DurationConverter var1) throws SecurityException {
      this.checkAlterDurationConverters();
      if (var1 == null) {
         return null;
      } else {
         DurationConverter[] var2 = new DurationConverter[1];
         this.iDurationConverters = this.iDurationConverters.remove(var1, var2);
         return var2[0];
      }
   }

   private void checkAlterDurationConverters() throws SecurityException {
      SecurityManager var1 = System.getSecurityManager();
      if (var1 != null) {
         var1.checkPermission(new JodaTimePermission("ConverterManager.alterDurationConverters"));
      }

   }

   public PeriodConverter getPeriodConverter(Object var1) {
      PeriodConverter var2 = (PeriodConverter)this.iPeriodConverters.select(var1 == null ? null : var1.getClass());
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalArgumentException("No period converter found for type: " + (var1 == null ? "null" : var1.getClass().getName()));
      }
   }

   public PeriodConverter[] getPeriodConverters() {
      ConverterSet var1 = this.iPeriodConverters;
      PeriodConverter[] var2 = new PeriodConverter[var1.size()];
      var1.copyInto(var2);
      return var2;
   }

   public PeriodConverter addPeriodConverter(PeriodConverter var1) throws SecurityException {
      this.checkAlterPeriodConverters();
      if (var1 == null) {
         return null;
      } else {
         PeriodConverter[] var2 = new PeriodConverter[1];
         this.iPeriodConverters = this.iPeriodConverters.add(var1, var2);
         return var2[0];
      }
   }

   public PeriodConverter removePeriodConverter(PeriodConverter var1) throws SecurityException {
      this.checkAlterPeriodConverters();
      if (var1 == null) {
         return null;
      } else {
         PeriodConverter[] var2 = new PeriodConverter[1];
         this.iPeriodConverters = this.iPeriodConverters.remove(var1, var2);
         return var2[0];
      }
   }

   private void checkAlterPeriodConverters() throws SecurityException {
      SecurityManager var1 = System.getSecurityManager();
      if (var1 != null) {
         var1.checkPermission(new JodaTimePermission("ConverterManager.alterPeriodConverters"));
      }

   }

   public IntervalConverter getIntervalConverter(Object var1) {
      IntervalConverter var2 = (IntervalConverter)this.iIntervalConverters.select(var1 == null ? null : var1.getClass());
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalArgumentException("No interval converter found for type: " + (var1 == null ? "null" : var1.getClass().getName()));
      }
   }

   public IntervalConverter[] getIntervalConverters() {
      ConverterSet var1 = this.iIntervalConverters;
      IntervalConverter[] var2 = new IntervalConverter[var1.size()];
      var1.copyInto(var2);
      return var2;
   }

   public IntervalConverter addIntervalConverter(IntervalConverter var1) throws SecurityException {
      this.checkAlterIntervalConverters();
      if (var1 == null) {
         return null;
      } else {
         IntervalConverter[] var2 = new IntervalConverter[1];
         this.iIntervalConverters = this.iIntervalConverters.add(var1, var2);
         return var2[0];
      }
   }

   public IntervalConverter removeIntervalConverter(IntervalConverter var1) throws SecurityException {
      this.checkAlterIntervalConverters();
      if (var1 == null) {
         return null;
      } else {
         IntervalConverter[] var2 = new IntervalConverter[1];
         this.iIntervalConverters = this.iIntervalConverters.remove(var1, var2);
         return var2[0];
      }
   }

   private void checkAlterIntervalConverters() throws SecurityException {
      SecurityManager var1 = System.getSecurityManager();
      if (var1 != null) {
         var1.checkPermission(new JodaTimePermission("ConverterManager.alterIntervalConverters"));
      }

   }

   public String toString() {
      return "ConverterManager[" + this.iInstantConverters.size() + " instant," + this.iPartialConverters.size() + " partial," + this.iDurationConverters.size() + " duration," + this.iPeriodConverters.size() + " period," + this.iIntervalConverters.size() + " interval]";
   }

   private static final class LazyConverterManagerHolder {
      static final ConverterManager INSTANCE = new ConverterManager();
   }
}
