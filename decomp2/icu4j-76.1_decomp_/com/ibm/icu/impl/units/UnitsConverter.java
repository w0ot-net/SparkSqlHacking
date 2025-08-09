package com.ibm.icu.impl.units;

import com.ibm.icu.impl.IllegalIcuArgumentException;
import com.ibm.icu.util.MeasureUnit;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

public class UnitsConverter {
   private BigDecimal conversionRate;
   private boolean reciprocal;
   private BigDecimal offset;
   private String specialSource;
   private String specialTarget;
   private static final BigDecimal[] minMetersPerSecForBeaufort = new BigDecimal[]{BigDecimal.valueOf((double)0.0F), BigDecimal.valueOf(0.3), BigDecimal.valueOf(1.6), BigDecimal.valueOf(3.4), BigDecimal.valueOf((double)5.5F), BigDecimal.valueOf((double)8.0F), BigDecimal.valueOf(10.8), BigDecimal.valueOf(13.9), BigDecimal.valueOf(17.2), BigDecimal.valueOf(20.8), BigDecimal.valueOf((double)24.5F), BigDecimal.valueOf((double)28.5F), BigDecimal.valueOf(32.7), BigDecimal.valueOf(36.9), BigDecimal.valueOf(41.4), BigDecimal.valueOf(46.1), BigDecimal.valueOf(51.1), BigDecimal.valueOf(55.8), BigDecimal.valueOf(61.4)};

   public UnitsConverter(String sourceIdentifier, String targetIdentifier) {
      this(MeasureUnitImpl.forIdentifier(sourceIdentifier), MeasureUnitImpl.forIdentifier(targetIdentifier), new ConversionRates());
   }

   public UnitsConverter(MeasureUnitImpl source, MeasureUnitImpl target, ConversionRates conversionRates) {
      Convertibility convertibility = extractConvertibility(source, target, conversionRates);
      if (convertibility != UnitsConverter.Convertibility.CONVERTIBLE && convertibility != UnitsConverter.Convertibility.RECIPROCAL) {
         throw new IllegalIcuArgumentException("input units must be convertible or reciprocal");
      } else {
         this.specialSource = conversionRates.getSpecialMappingName(source);
         this.specialTarget = conversionRates.getSpecialMappingName(target);
         if (this.specialSource == null && this.specialTarget == null) {
            Factor sourceToBase = conversionRates.getFactorToBase(source);
            Factor targetToBase = conversionRates.getFactorToBase(target);
            if (convertibility == UnitsConverter.Convertibility.CONVERTIBLE) {
               this.conversionRate = sourceToBase.divide(targetToBase).getConversionRate();
            } else {
               assert convertibility == UnitsConverter.Convertibility.RECIPROCAL;

               this.conversionRate = sourceToBase.multiply(targetToBase).getConversionRate();
            }

            this.reciprocal = convertibility == UnitsConverter.Convertibility.RECIPROCAL;
            this.offset = conversionRates.getOffset(source, target, sourceToBase, targetToBase, convertibility);

            assert convertibility != UnitsConverter.Convertibility.RECIPROCAL || this.offset == BigDecimal.ZERO;
         } else {
            this.reciprocal = false;
            this.offset = BigDecimal.ZERO;
            if (this.specialSource == null) {
               this.conversionRate = conversionRates.getFactorToBase(source).getConversionRate();
            } else if (this.specialTarget == null) {
               this.conversionRate = conversionRates.getFactorToBase(target).getConversionRate();
            } else {
               this.conversionRate = BigDecimal.ONE;
            }
         }

      }
   }

   public static Convertibility extractConvertibility(MeasureUnitImpl source, MeasureUnitImpl target, ConversionRates conversionRates) {
      ArrayList<SingleUnitImpl> sourceSingleUnits = conversionRates.extractBaseUnits(source);
      ArrayList<SingleUnitImpl> targetSingleUnits = conversionRates.extractBaseUnits(target);
      HashMap<String, Integer> dimensionMap = new HashMap();
      insertInMap(dimensionMap, sourceSingleUnits, 1);
      insertInMap(dimensionMap, targetSingleUnits, -1);
      if (areDimensionsZeroes(dimensionMap)) {
         return UnitsConverter.Convertibility.CONVERTIBLE;
      } else {
         insertInMap(dimensionMap, targetSingleUnits, 2);
         return areDimensionsZeroes(dimensionMap) ? UnitsConverter.Convertibility.RECIPROCAL : UnitsConverter.Convertibility.UNCONVERTIBLE;
      }
   }

   private static void insertInMap(HashMap dimensionMap, ArrayList singleUnits, int multiplier) {
      for(SingleUnitImpl singleUnit : singleUnits) {
         if (dimensionMap.containsKey(singleUnit.getSimpleUnitID())) {
            dimensionMap.put(singleUnit.getSimpleUnitID(), (Integer)dimensionMap.get(singleUnit.getSimpleUnitID()) + singleUnit.getDimensionality() * multiplier);
         } else {
            dimensionMap.put(singleUnit.getSimpleUnitID(), singleUnit.getDimensionality() * multiplier);
         }
      }

   }

   private static boolean areDimensionsZeroes(HashMap dimensionMap) {
      for(Integer value : dimensionMap.values()) {
         if (!value.equals(0)) {
            return false;
         }
      }

      return true;
   }

   public BigDecimal convert(BigDecimal inputValue) {
      if (this.specialSource == null && this.specialTarget == null) {
         BigDecimal var4 = inputValue.multiply(this.conversionRate).add(this.offset);
         if (this.reciprocal) {
            assert this.offset == BigDecimal.ZERO;

            if (var4.compareTo(BigDecimal.ZERO) == 0) {
               return BigDecimal.ZERO;
            }

            var4 = BigDecimal.ONE.divide(var4, MathContext.DECIMAL128);
         }

         return var4;
      } else {
         BigDecimal base;
         if (this.specialSource != null) {
            base = this.specialSource.equals("beaufort") ? this.scaleToBase(inputValue, minMetersPerSecForBeaufort) : inputValue;
         } else {
            base = inputValue.multiply(this.conversionRate);
         }

         BigDecimal result;
         if (this.specialTarget != null) {
            result = this.specialTarget.equals("beaufort") ? this.baseToScale(base, minMetersPerSecForBeaufort) : base;
         } else {
            result = base.divide(this.conversionRate, MathContext.DECIMAL128);
         }

         return result;
      }
   }

   public BigDecimal convertInverse(BigDecimal inputValue) {
      BigDecimal result = inputValue;
      if (this.specialSource == null && this.specialTarget == null) {
         if (this.reciprocal) {
            assert this.offset == BigDecimal.ZERO;

            if (inputValue.compareTo(BigDecimal.ZERO) == 0) {
               return BigDecimal.ZERO;
            }

            result = BigDecimal.ONE.divide(inputValue, MathContext.DECIMAL128);
         }

         result = result.subtract(this.offset).divide(this.conversionRate, MathContext.DECIMAL128);
         return result;
      } else {
         BigDecimal base;
         if (this.specialTarget != null) {
            base = this.specialTarget.equals("beaufort") ? this.scaleToBase(inputValue, minMetersPerSecForBeaufort) : inputValue;
         } else {
            base = inputValue.multiply(this.conversionRate);
         }

         if (this.specialSource != null) {
            result = this.specialSource.equals("beaufort") ? this.baseToScale(base, minMetersPerSecForBeaufort) : base;
         } else {
            result = base.divide(this.conversionRate, MathContext.DECIMAL128);
         }

         return result;
      }
   }

   private BigDecimal scaleToBase(BigDecimal scaleValue, BigDecimal[] minBaseForScaleValues) {
      BigDecimal pointFive = BigDecimal.valueOf((double)0.5F);
      BigDecimal scaleAdjust = scaleValue.abs().add(pointFive);
      BigDecimal scaleAdjustCapped = scaleAdjust.min(BigDecimal.valueOf((long)(minBaseForScaleValues.length - 2)));
      int scaleIndex = scaleAdjustCapped.intValue();
      return minBaseForScaleValues[scaleIndex].add(minBaseForScaleValues[scaleIndex + 1]).multiply(pointFive);
   }

   private BigDecimal baseToScale(BigDecimal baseValue, BigDecimal[] minBaseForScaleValues) {
      int scaleIndex = Arrays.binarySearch(minBaseForScaleValues, baseValue.abs());
      if (scaleIndex < 0) {
         scaleIndex = -scaleIndex - 2;
      }

      int scaleMax = minBaseForScaleValues.length - 2;
      if (scaleIndex > scaleMax) {
         scaleIndex = scaleMax;
      }

      return BigDecimal.valueOf((long)scaleIndex);
   }

   public ConversionInfo getConversionInfo() {
      ConversionInfo result = new ConversionInfo();
      result.conversionRate = this.conversionRate;
      result.offset = this.offset;
      result.reciprocal = this.reciprocal;
      return result;
   }

   public String toString() {
      return "UnitsConverter [conversionRate=" + this.conversionRate + ", offset=" + this.offset + "]";
   }

   public static enum Convertibility {
      CONVERTIBLE,
      RECIPROCAL,
      UNCONVERTIBLE;
   }

   public static class ConversionInfo {
      public BigDecimal conversionRate;
      public BigDecimal offset;
      public boolean reciprocal;
   }

   static class Factor {
      private BigDecimal factorNum = BigDecimal.valueOf(1L);
      private BigDecimal factorDen = BigDecimal.valueOf(1L);
      private int exponentFtToM = 0;
      private int exponentPi = 0;
      private int exponentGravity = 0;
      private int exponentG = 0;
      private int exponentGalImpToM3 = 0;
      private int exponentLbToKg = 0;
      private int exponentGlucoseMolarMass = 0;
      private int exponentItemPerMole = 0;
      private int exponentMetersPerAU = 0;
      private int exponentSecPerJulianYear = 0;
      private int exponentSpeedOfLightMetersPerSecond = 0;
      private int exponentShoToM3 = 0;
      private int exponentTsuboToM2 = 0;
      private int exponentShakuToM = 0;
      private int exponentAMU = 0;

      public Factor() {
      }

      public static Factor processFactor(String factor) {
         assert !factor.isEmpty();

         factor = factor.replaceAll("\\s+", "");
         String[] fractions = factor.split("/");

         assert fractions.length == 1 || fractions.length == 2;

         if (fractions.length == 1) {
            return processFactorWithoutDivision(fractions[0]);
         } else {
            Factor num = processFactorWithoutDivision(fractions[0]);
            Factor den = processFactorWithoutDivision(fractions[1]);
            return num.divide(den);
         }
      }

      private static Factor processFactorWithoutDivision(String factorWithoutDivision) {
         Factor result = new Factor();

         for(String poweredEntity : factorWithoutDivision.split(Pattern.quote("*"))) {
            result.addPoweredEntity(poweredEntity);
         }

         return result;
      }

      protected Factor copy() {
         Factor result = new Factor();
         result.factorNum = this.factorNum;
         result.factorDen = this.factorDen;
         result.exponentFtToM = this.exponentFtToM;
         result.exponentPi = this.exponentPi;
         result.exponentGravity = this.exponentGravity;
         result.exponentG = this.exponentG;
         result.exponentGalImpToM3 = this.exponentGalImpToM3;
         result.exponentLbToKg = this.exponentLbToKg;
         result.exponentGlucoseMolarMass = this.exponentGlucoseMolarMass;
         result.exponentItemPerMole = this.exponentItemPerMole;
         result.exponentMetersPerAU = this.exponentMetersPerAU;
         result.exponentSecPerJulianYear = this.exponentSecPerJulianYear;
         result.exponentSpeedOfLightMetersPerSecond = this.exponentSpeedOfLightMetersPerSecond;
         result.exponentShoToM3 = this.exponentShoToM3;
         result.exponentTsuboToM2 = this.exponentTsuboToM2;
         result.exponentShakuToM = this.exponentShakuToM;
         result.exponentAMU = this.exponentAMU;
         return result;
      }

      public BigDecimal getConversionRate() {
         Factor resultCollector = this.copy();
         resultCollector.multiply(new BigDecimal("0.3048"), this.exponentFtToM);
         resultCollector.multiply((new BigDecimal("411557987.0")).divide(new BigDecimal("131002976.0"), MathContext.DECIMAL128), this.exponentPi);
         resultCollector.multiply(new BigDecimal("9.80665"), this.exponentGravity);
         resultCollector.multiply(new BigDecimal("6.67408E-11"), this.exponentG);
         resultCollector.multiply(new BigDecimal("0.00454609"), this.exponentGalImpToM3);
         resultCollector.multiply(new BigDecimal("0.45359237"), this.exponentLbToKg);
         resultCollector.multiply(new BigDecimal("180.1557"), this.exponentGlucoseMolarMass);
         resultCollector.multiply(new BigDecimal("6.02214076E+23"), this.exponentItemPerMole);
         resultCollector.multiply(new BigDecimal("149597870700"), this.exponentMetersPerAU);
         resultCollector.multiply(new BigDecimal("31557600"), this.exponentSecPerJulianYear);
         resultCollector.multiply(new BigDecimal("299792458"), this.exponentSpeedOfLightMetersPerSecond);
         resultCollector.multiply(new BigDecimal("0.001803906836964688204"), this.exponentShoToM3);
         resultCollector.multiply(new BigDecimal("3.305785123966942"), this.exponentTsuboToM2);
         resultCollector.multiply(new BigDecimal("0.033057851239669"), this.exponentShakuToM);
         resultCollector.multiply(new BigDecimal("1.66053878283E-27"), this.exponentAMU);
         return resultCollector.factorNum.divide(resultCollector.factorDen, MathContext.DECIMAL128);
      }

      private void multiply(BigDecimal value, int power) {
         if (power != 0) {
            BigDecimal absPoweredValue = value.pow(Math.abs(power), MathContext.DECIMAL128);
            if (power > 0) {
               this.factorNum = this.factorNum.multiply(absPoweredValue);
            } else {
               this.factorDen = this.factorDen.multiply(absPoweredValue);
            }

         }
      }

      public Factor applyPrefix(MeasureUnit.MeasurePrefix unitPrefix) {
         Factor result = this.copy();
         if (unitPrefix == MeasureUnit.MeasurePrefix.ONE) {
            return result;
         } else {
            int base = unitPrefix.getBase();
            int power = unitPrefix.getPower();
            BigDecimal absFactor = BigDecimal.valueOf((long)base).pow(Math.abs(power), MathContext.DECIMAL128);
            if (power < 0) {
               result.factorDen = this.factorDen.multiply(absFactor);
               return result;
            } else {
               result.factorNum = this.factorNum.multiply(absFactor);
               return result;
            }
         }
      }

      public Factor power(int power) {
         Factor result = new Factor();
         if (power == 0) {
            return result;
         } else {
            if (power > 0) {
               result.factorNum = this.factorNum.pow(power);
               result.factorDen = this.factorDen.pow(power);
            } else {
               result.factorNum = this.factorDen.pow(power * -1);
               result.factorDen = this.factorNum.pow(power * -1);
            }

            result.exponentFtToM = this.exponentFtToM * power;
            result.exponentPi = this.exponentPi * power;
            result.exponentGravity = this.exponentGravity * power;
            result.exponentG = this.exponentG * power;
            result.exponentGalImpToM3 = this.exponentGalImpToM3 * power;
            result.exponentLbToKg = this.exponentLbToKg * power;
            result.exponentGlucoseMolarMass = this.exponentGlucoseMolarMass * power;
            result.exponentItemPerMole = this.exponentItemPerMole * power;
            result.exponentMetersPerAU = this.exponentMetersPerAU * power;
            result.exponentSecPerJulianYear = this.exponentSecPerJulianYear * power;
            result.exponentSpeedOfLightMetersPerSecond = this.exponentSpeedOfLightMetersPerSecond * power;
            result.exponentShoToM3 = this.exponentShoToM3 * power;
            result.exponentTsuboToM2 = this.exponentTsuboToM2 * power;
            result.exponentShakuToM = this.exponentShakuToM * power;
            result.exponentAMU = this.exponentAMU * power;
            return result;
         }
      }

      public Factor divide(Factor other) {
         Factor result = new Factor();
         result.factorNum = this.factorNum.multiply(other.factorDen);
         result.factorDen = this.factorDen.multiply(other.factorNum);
         result.exponentFtToM = this.exponentFtToM - other.exponentFtToM;
         result.exponentPi = this.exponentPi - other.exponentPi;
         result.exponentGravity = this.exponentGravity - other.exponentGravity;
         result.exponentG = this.exponentG - other.exponentG;
         result.exponentGalImpToM3 = this.exponentGalImpToM3 - other.exponentGalImpToM3;
         result.exponentLbToKg = this.exponentLbToKg - other.exponentLbToKg;
         result.exponentGlucoseMolarMass = this.exponentGlucoseMolarMass - other.exponentGlucoseMolarMass;
         result.exponentItemPerMole = this.exponentItemPerMole - other.exponentItemPerMole;
         result.exponentMetersPerAU = this.exponentMetersPerAU - other.exponentMetersPerAU;
         result.exponentSecPerJulianYear = this.exponentSecPerJulianYear - other.exponentSecPerJulianYear;
         result.exponentSpeedOfLightMetersPerSecond = this.exponentSpeedOfLightMetersPerSecond - other.exponentSpeedOfLightMetersPerSecond;
         result.exponentShoToM3 = this.exponentShoToM3 - other.exponentShoToM3;
         result.exponentTsuboToM2 = this.exponentTsuboToM2 - other.exponentTsuboToM2;
         result.exponentShakuToM = this.exponentShakuToM - other.exponentShakuToM;
         result.exponentAMU = this.exponentAMU - other.exponentAMU;
         return result;
      }

      public Factor multiply(Factor other) {
         Factor result = new Factor();
         result.factorNum = this.factorNum.multiply(other.factorNum);
         result.factorDen = this.factorDen.multiply(other.factorDen);
         result.exponentFtToM = this.exponentFtToM + other.exponentFtToM;
         result.exponentPi = this.exponentPi + other.exponentPi;
         result.exponentGravity = this.exponentGravity + other.exponentGravity;
         result.exponentG = this.exponentG + other.exponentG;
         result.exponentGalImpToM3 = this.exponentGalImpToM3 + other.exponentGalImpToM3;
         result.exponentLbToKg = this.exponentLbToKg + other.exponentLbToKg;
         result.exponentGlucoseMolarMass = this.exponentGlucoseMolarMass + other.exponentGlucoseMolarMass;
         result.exponentItemPerMole = this.exponentItemPerMole + other.exponentItemPerMole;
         result.exponentMetersPerAU = this.exponentMetersPerAU + other.exponentMetersPerAU;
         result.exponentSecPerJulianYear = this.exponentSecPerJulianYear + other.exponentSecPerJulianYear;
         result.exponentSpeedOfLightMetersPerSecond = this.exponentSpeedOfLightMetersPerSecond + other.exponentSpeedOfLightMetersPerSecond;
         result.exponentShoToM3 = this.exponentShoToM3 + other.exponentShoToM3;
         result.exponentTsuboToM2 = this.exponentTsuboToM2 + other.exponentTsuboToM2;
         result.exponentShakuToM = this.exponentShakuToM + other.exponentShakuToM;
         result.exponentAMU = this.exponentAMU + other.exponentAMU;
         return result;
      }

      private void addPoweredEntity(String poweredEntity) {
         String[] entities = poweredEntity.split(Pattern.quote("^"));

         assert entities.length == 1 || entities.length == 2;

         int power = entities.length == 2 ? Integer.parseInt(entities[1]) : 1;
         this.addEntity(entities[0], power);
      }

      private void addEntity(String entity, int power) {
         if ("ft_to_m".equals(entity)) {
            this.exponentFtToM += power;
         } else if ("ft2_to_m2".equals(entity)) {
            this.exponentFtToM += 2 * power;
         } else if ("ft3_to_m3".equals(entity)) {
            this.exponentFtToM += 3 * power;
         } else if ("in3_to_m3".equals(entity)) {
            this.exponentFtToM += 3 * power;
            this.factorDen = this.factorDen.multiply(BigDecimal.valueOf(Math.pow((double)12.0F, (double)3.0F)));
         } else if ("gal_to_m3".equals(entity)) {
            this.factorNum = this.factorNum.multiply(BigDecimal.valueOf(231L));
            this.exponentFtToM += 3 * power;
            this.factorDen = this.factorDen.multiply(BigDecimal.valueOf(1728L));
         } else if ("gal_imp_to_m3".equals(entity)) {
            this.exponentGalImpToM3 += power;
         } else if ("G".equals(entity)) {
            this.exponentG += power;
         } else if ("gravity".equals(entity)) {
            this.exponentGravity += power;
         } else if ("lb_to_kg".equals(entity)) {
            this.exponentLbToKg += power;
         } else if ("glucose_molar_mass".equals(entity)) {
            this.exponentGlucoseMolarMass += power;
         } else if ("item_per_mole".equals(entity)) {
            this.exponentItemPerMole += power;
         } else if ("meters_per_AU".equals(entity)) {
            this.exponentMetersPerAU += power;
         } else if ("PI".equals(entity)) {
            this.exponentPi += power;
         } else if ("sec_per_julian_year".equals(entity)) {
            this.exponentSecPerJulianYear += power;
         } else if ("speed_of_light_meters_per_second".equals(entity)) {
            this.exponentSpeedOfLightMetersPerSecond += power;
         } else if ("sho_to_m3".equals(entity)) {
            this.exponentShoToM3 += power;
         } else if ("tsubo_to_m2".equals(entity)) {
            this.exponentTsuboToM2 += power;
         } else if ("shaku_to_m".equals(entity)) {
            this.exponentShakuToM += power;
         } else if ("AMU".equals(entity)) {
            this.exponentAMU += power;
         } else {
            BigDecimal decimalEntity = (new BigDecimal(entity)).pow(power, MathContext.DECIMAL128);
            this.factorNum = this.factorNum.multiply(decimalEntity);
         }

      }
   }
}
