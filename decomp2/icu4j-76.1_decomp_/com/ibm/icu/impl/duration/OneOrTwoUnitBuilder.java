package com.ibm.icu.impl.duration;

class OneOrTwoUnitBuilder extends PeriodBuilderImpl {
   OneOrTwoUnitBuilder(BasicPeriodBuilderFactory.Settings settings) {
      super(settings);
   }

   public static OneOrTwoUnitBuilder get(BasicPeriodBuilderFactory.Settings settings) {
      return settings == null ? null : new OneOrTwoUnitBuilder(settings);
   }

   protected PeriodBuilder withSettings(BasicPeriodBuilderFactory.Settings settingsToUse) {
      return get(settingsToUse);
   }

   protected Period handleCreate(long duration, long referenceDate, boolean inPast) {
      Period period = null;
      short uset = this.settings.effectiveSet();

      for(int i = 0; i < TimeUnit.units.length; ++i) {
         if (0 != (uset & 1 << i)) {
            TimeUnit unit = TimeUnit.units[i];
            long unitDuration = this.approximateDurationOf(unit);
            if (duration >= unitDuration || period != null) {
               double count = (double)duration / (double)unitDuration;
               if (period != null) {
                  if (count >= (double)1.0F) {
                     period = period.and((float)count, unit);
                  }
                  break;
               }

               if (count >= (double)2.0F) {
                  period = Period.at((float)count, unit);
                  break;
               }

               period = Period.at(1.0F, unit).inPast(inPast);
               duration -= unitDuration;
            }
         }
      }

      return period;
   }
}
