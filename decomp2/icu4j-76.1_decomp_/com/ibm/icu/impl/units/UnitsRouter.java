package com.ibm.icu.impl.units;

import com.ibm.icu.impl.IllegalIcuArgumentException;
import com.ibm.icu.impl.number.MicroProps;
import com.ibm.icu.number.Precision;
import com.ibm.icu.util.ULocale;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UnitsRouter {
   private ArrayList outputUnits_;
   private ArrayList converterPreferences_;

   public UnitsRouter(String inputUnitIdentifier, ULocale locale, String usage) {
      this(MeasureUnitImpl.forIdentifier(inputUnitIdentifier), locale, usage);
   }

   public UnitsRouter(MeasureUnitImpl inputUnit, ULocale locale, String usage) {
      this.outputUnits_ = new ArrayList();
      this.converterPreferences_ = new ArrayList();
      UnitsData data = new UnitsData();
      String category = data.getCategory(inputUnit);
      UnitPreferences.UnitPreference[] unitPreferences = data.getPreferencesFor(category, usage, locale);

      for(int i = 0; i < unitPreferences.length; ++i) {
         UnitPreferences.UnitPreference preference = unitPreferences[i];
         MeasureUnitImpl complexTargetUnitImpl = MeasureUnitImpl.UnitsParser.parseForIdentifier(preference.getUnit());
         String precision = preference.getSkeleton();
         if (!precision.isEmpty() && !precision.startsWith("precision-increment")) {
            throw new AssertionError("Only `precision-increment` is allowed");
         }

         this.outputUnits_.add(complexTargetUnitImpl.build());
         this.converterPreferences_.add(new ConverterPreference(inputUnit, complexTargetUnitImpl, preference.getGeq(), precision, data.getConversionRates()));
      }

   }

   public RouteResult route(BigDecimal quantity, MicroProps micros) {
      Precision rounder = micros == null ? null : micros.rounder;
      ConverterPreference converterPreference = null;

      for(ConverterPreference itr : this.converterPreferences_) {
         converterPreference = itr;
         if (itr.converter.greaterThanOrEqual(quantity.abs(), itr.limit)) {
            break;
         }
      }

      assert converterPreference != null;

      assert converterPreference.precision != null;

      if (rounder != null && rounder instanceof Precision.BogusRounder) {
         Precision.BogusRounder bogus = (Precision.BogusRounder)rounder;
         if (converterPreference.precision.length() > 0) {
            rounder = bogus.into(parseSkeletonToPrecision(converterPreference.precision));
         } else {
            rounder = bogus.into(Precision.integer().withMinDigits(2));
         }
      }

      if (micros != null) {
         micros.rounder = rounder;
      }

      return new RouteResult(converterPreference.converter.convert(quantity, rounder), converterPreference.targetUnit);
   }

   private static Precision parseSkeletonToPrecision(String precisionSkeleton) {
      String kSkeletonPrefix = "precision-increment/";
      if (!precisionSkeleton.startsWith("precision-increment/")) {
         throw new IllegalIcuArgumentException("precisionSkeleton is only precision-increment");
      } else {
         String incrementValue = precisionSkeleton.substring("precision-increment/".length());
         return Precision.increment(new BigDecimal(incrementValue));
      }
   }

   public List getOutputUnits() {
      return this.outputUnits_;
   }

   public static class ConverterPreference {
      final MeasureUnitImpl targetUnit;
      final ComplexUnitsConverter converter;
      final BigDecimal limit;
      final String precision;

      public ConverterPreference(MeasureUnitImpl source, MeasureUnitImpl targetUnit, String precision, ConversionRates conversionRates) {
         this(source, targetUnit, BigDecimal.valueOf(Double.MIN_VALUE), precision, conversionRates);
      }

      public ConverterPreference(MeasureUnitImpl source, MeasureUnitImpl targetUnit, BigDecimal limit, String precision, ConversionRates conversionRates) {
         this.converter = new ComplexUnitsConverter(source, targetUnit, conversionRates);
         this.limit = limit;
         this.precision = precision;
         this.targetUnit = targetUnit;
      }
   }

   public class RouteResult {
      public final ComplexUnitsConverter.ComplexConverterResult complexConverterResult;
      public final MeasureUnitImpl outputUnit;

      RouteResult(ComplexUnitsConverter.ComplexConverterResult complexConverterResult, MeasureUnitImpl outputUnit) {
         this.complexConverterResult = complexConverterResult;
         this.outputUnit = outputUnit;
      }
   }
}
