package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "MATH-CONTEXT",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
public enum MathContext implements StringValue {
   @XmlEnumValue("float")
   @JsonProperty("float")
   FLOAT("float"),
   @XmlEnumValue("double")
   @JsonProperty("double")
   DOUBLE("double");

   private final String value;

   private MathContext(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static MathContext fromValue(String v) {
      for(MathContext c : values()) {
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
