package com.ibm.icu.impl.units;

import com.ibm.icu.util.MeasureUnit;

public class SingleUnitImpl {
   private int index = -1;
   private String simpleUnitID = "";
   private int dimensionality = 1;
   private MeasureUnit.MeasurePrefix unitPrefix;

   public SingleUnitImpl() {
      this.unitPrefix = MeasureUnit.MeasurePrefix.ONE;
   }

   public SingleUnitImpl copy() {
      SingleUnitImpl result = new SingleUnitImpl();
      result.index = this.index;
      result.dimensionality = this.dimensionality;
      result.simpleUnitID = this.simpleUnitID;
      result.unitPrefix = this.unitPrefix;
      return result;
   }

   public MeasureUnit build() {
      MeasureUnitImpl measureUnit = new MeasureUnitImpl(this);
      return measureUnit.build();
   }

   public String getNeutralIdentifier() {
      StringBuilder result = new StringBuilder();
      int absPower = Math.abs(this.getDimensionality());

      assert absPower > 0 : "this function does not support the dimensionless single units";

      if (absPower != 1) {
         if (absPower == 2) {
            result.append("square-");
         } else if (absPower == 3) {
            result.append("cubic-");
         } else {
            if (absPower > 15) {
               throw new IllegalArgumentException("Unit Identifier Syntax Error");
            }

            result.append("pow");
            result.append(absPower);
            result.append('-');
         }
      }

      result.append(this.getPrefix().getIdentifier());
      result.append(this.getSimpleUnitID());
      return result.toString();
   }

   int compareTo(SingleUnitImpl other) {
      if (this.dimensionality < 0 && other.dimensionality > 0) {
         return 1;
      } else if (this.dimensionality > 0 && other.dimensionality < 0) {
         return -1;
      } else {
         int thisCategoryIndex = UnitsData.getCategoryIndexOfSimpleUnit(this.index);
         int otherCategoryIndex = UnitsData.getCategoryIndexOfSimpleUnit(other.index);
         if (thisCategoryIndex < otherCategoryIndex) {
            return -1;
         } else if (thisCategoryIndex > otherCategoryIndex) {
            return 1;
         } else if (this.index < other.index) {
            return -1;
         } else if (this.index > other.index) {
            return 1;
         } else {
            int unitBase = this.unitPrefix.getBase();
            int otherUnitBase = other.unitPrefix.getBase();
            int unitPowerComp = unitBase == 1024 ? this.unitPrefix.getPower() * 3 : this.unitPrefix.getPower();
            int otherUnitPowerComp = otherUnitBase == 1024 ? other.unitPrefix.getPower() * 3 : other.unitPrefix.getPower();
            if (unitPowerComp < otherUnitPowerComp) {
               return 1;
            } else if (unitPowerComp > otherUnitPowerComp) {
               return -1;
            } else if (unitBase < otherUnitBase) {
               return 1;
            } else {
               return unitBase > otherUnitBase ? -1 : 0;
            }
         }
      }
   }

   boolean isCompatibleWith(SingleUnitImpl other) {
      return this.compareTo(other) == 0;
   }

   public String getSimpleUnitID() {
      return this.simpleUnitID;
   }

   public void setSimpleUnit(int simpleUnitIndex, String[] simpleUnits) {
      this.index = simpleUnitIndex;
      this.simpleUnitID = simpleUnits[simpleUnitIndex];
   }

   public int getDimensionality() {
      return this.dimensionality;
   }

   public void setDimensionality(int dimensionality) {
      this.dimensionality = dimensionality;
   }

   public MeasureUnit.MeasurePrefix getPrefix() {
      return this.unitPrefix;
   }

   public void setPrefix(MeasureUnit.MeasurePrefix unitPrefix) {
      this.unitPrefix = unitPrefix;
   }

   public int getIndex() {
      return this.index;
   }
}
