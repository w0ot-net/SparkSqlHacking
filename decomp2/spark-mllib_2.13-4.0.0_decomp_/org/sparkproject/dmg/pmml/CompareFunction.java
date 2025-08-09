package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "COMPARE-FUNCTION",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
public enum CompareFunction implements StringValue {
   @XmlEnumValue("absDiff")
   @JsonProperty("absDiff")
   ABS_DIFF("absDiff"),
   @XmlEnumValue("gaussSim")
   @JsonProperty("gaussSim")
   GAUSS_SIM("gaussSim"),
   @XmlEnumValue("delta")
   @JsonProperty("delta")
   DELTA("delta"),
   @XmlEnumValue("equal")
   @JsonProperty("equal")
   EQUAL("equal"),
   @XmlEnumValue("table")
   @JsonProperty("table")
   TABLE("table");

   private final String value;

   private CompareFunction(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CompareFunction fromValue(String v) {
      for(CompareFunction c : values()) {
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
