package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Since;

@XmlType(
   name = "MISSING-VALUE-TREATMENT-METHOD",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
public enum MissingValueTreatmentMethod implements StringValue {
   @XmlEnumValue("asIs")
   @JsonProperty("asIs")
   AS_IS("asIs"),
   @XmlEnumValue("asMean")
   @JsonProperty("asMean")
   AS_MEAN("asMean"),
   @XmlEnumValue("asMode")
   @JsonProperty("asMode")
   AS_MODE("asMode"),
   @XmlEnumValue("asMedian")
   @JsonProperty("asMedian")
   AS_MEDIAN("asMedian"),
   @XmlEnumValue("asValue")
   @JsonProperty("asValue")
   AS_VALUE("asValue"),
   @XmlEnumValue("returnInvalid")
   @JsonProperty("returnInvalid")
   @Added(Version.PMML_4_4)
   @Since("1.4.2")
   RETURN_INVALID("returnInvalid");

   private final String value;

   private MissingValueTreatmentMethod(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static MissingValueTreatmentMethod fromValue(String v) {
      for(MissingValueTreatmentMethod c : values()) {
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
