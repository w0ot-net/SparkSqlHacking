package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.jpmml.model.annotations.Added;

@XmlType(
   name = "INVALID-VALUE-TREATMENT-METHOD",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
@Added(Version.PMML_3_1)
public enum InvalidValueTreatmentMethod implements StringValue {
   @XmlEnumValue("returnInvalid")
   @JsonProperty("returnInvalid")
   RETURN_INVALID("returnInvalid"),
   @XmlEnumValue("asIs")
   @JsonProperty("asIs")
   AS_IS("asIs"),
   @XmlEnumValue("asMissing")
   @JsonProperty("asMissing")
   AS_MISSING("asMissing"),
   @XmlEnumValue("asValue")
   @JsonProperty("asValue")
   @Added(Version.PMML_4_4)
   AS_VALUE("asValue");

   private final String value;

   private InvalidValueTreatmentMethod(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static InvalidValueTreatmentMethod fromValue(String v) {
      for(InvalidValueTreatmentMethod c : values()) {
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
