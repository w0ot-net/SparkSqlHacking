package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "OUTLIER-TREATMENT-METHOD",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
public enum OutlierTreatmentMethod implements StringValue {
   @XmlEnumValue("asIs")
   @JsonProperty("asIs")
   AS_IS("asIs"),
   @XmlEnumValue("asMissingValues")
   @JsonProperty("asMissingValues")
   AS_MISSING_VALUES("asMissingValues"),
   @XmlEnumValue("asExtremeValues")
   @JsonProperty("asExtremeValues")
   AS_EXTREME_VALUES("asExtremeValues");

   private final String value;

   private OutlierTreatmentMethod(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static OutlierTreatmentMethod fromValue(String v) {
      for(OutlierTreatmentMethod c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }

   public String toString() {
      return this.value();
   }
}
