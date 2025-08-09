package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "OPTYPE",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
public enum OpType implements StringValue {
   @XmlEnumValue("categorical")
   @JsonProperty("categorical")
   CATEGORICAL("categorical"),
   @XmlEnumValue("ordinal")
   @JsonProperty("ordinal")
   ORDINAL("ordinal"),
   @XmlEnumValue("continuous")
   @JsonProperty("continuous")
   CONTINUOUS("continuous");

   private final String value;

   private OpType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static OpType fromValue(String v) {
      for(OpType c : values()) {
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
