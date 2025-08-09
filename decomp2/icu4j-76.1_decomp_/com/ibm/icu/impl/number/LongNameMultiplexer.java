package com.ibm.icu.impl.number;

import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.MeasureUnit;
import com.ibm.icu.util.ULocale;
import java.util.ArrayList;
import java.util.List;

public class LongNameMultiplexer implements MicroPropsGenerator {
   private final MicroPropsGenerator fParent;
   private List fHandlers;
   private List fMeasureUnits;

   public LongNameMultiplexer(MicroPropsGenerator fParent) {
      this.fParent = fParent;
   }

   public static LongNameMultiplexer forMeasureUnits(ULocale locale, List units, NumberFormatter.UnitWidth width, String unitDisplayCase, PluralRules rules, MicroPropsGenerator parent) {
      LongNameMultiplexer result = new LongNameMultiplexer(parent);

      assert units.size() > 0;

      result.fMeasureUnits = new ArrayList();
      result.fHandlers = new ArrayList();

      for(int i = 0; i < units.size(); ++i) {
         MeasureUnit unit = (MeasureUnit)units.get(i);
         result.fMeasureUnits.add(unit);
         if (unit.getComplexity() == MeasureUnit.Complexity.MIXED) {
            MixedUnitLongNameHandler mlnh = MixedUnitLongNameHandler.forMeasureUnit(locale, unit, width, unitDisplayCase, rules, (MicroPropsGenerator)null);
            result.fHandlers.add(mlnh);
         } else {
            LongNameHandler lnh = LongNameHandler.forMeasureUnit(locale, unit, width, unitDisplayCase, rules, (MicroPropsGenerator)null);
            result.fHandlers.add(lnh);
         }
      }

      return result;
   }

   public MicroProps processQuantity(DecimalQuantity quantity) {
      MicroProps micros = this.fParent.processQuantity(quantity);

      for(int i = 0; i < this.fHandlers.size(); ++i) {
         if (((MeasureUnit)this.fMeasureUnits.get(i)).equals(micros.outputUnit)) {
            ParentlessMicroPropsGenerator handler = (ParentlessMicroPropsGenerator)this.fHandlers.get(i);
            return handler.processQuantityWithMicros(quantity, micros);
         }
      }

      throw new AssertionError(" We shouldn't receive any outputUnit for which we haven't already got a LongNameHandler");
   }

   public interface ParentlessMicroPropsGenerator {
      MicroProps processQuantityWithMicros(DecimalQuantity var1, MicroProps var2);
   }
}
