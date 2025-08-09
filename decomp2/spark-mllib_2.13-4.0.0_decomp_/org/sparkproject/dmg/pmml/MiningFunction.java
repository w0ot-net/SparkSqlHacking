package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.jpmml.model.annotations.Added;

@XmlType(
   name = "MINING-FUNCTION",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
public enum MiningFunction implements StringValue {
   @XmlEnumValue("associationRules")
   @JsonProperty("associationRules")
   ASSOCIATION_RULES("associationRules"),
   @XmlEnumValue("sequences")
   @JsonProperty("sequences")
   SEQUENCES("sequences"),
   @XmlEnumValue("classification")
   @JsonProperty("classification")
   CLASSIFICATION("classification"),
   @XmlEnumValue("regression")
   @JsonProperty("regression")
   REGRESSION("regression"),
   @XmlEnumValue("clustering")
   @JsonProperty("clustering")
   CLUSTERING("clustering"),
   @XmlEnumValue("timeSeries")
   @JsonProperty("timeSeries")
   @Added(Version.PMML_4_0)
   TIME_SERIES("timeSeries"),
   @XmlEnumValue("mixed")
   @JsonProperty("mixed")
   @Added(Version.PMML_4_1)
   MIXED("mixed");

   private final String value;

   private MiningFunction(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static MiningFunction fromValue(String v) {
      for(MiningFunction c : values()) {
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
