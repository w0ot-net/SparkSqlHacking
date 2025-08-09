package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Since;

@XmlType(
   name = "DATATYPE",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
public enum DataType implements StringValue {
   @XmlEnumValue("string")
   @JsonProperty("string")
   STRING("string"),
   @XmlEnumValue("integer")
   @JsonProperty("integer")
   INTEGER("integer"),
   @XmlEnumValue("float")
   @JsonProperty("float")
   FLOAT("float"),
   @XmlEnumValue("double")
   @JsonProperty("double")
   DOUBLE("double"),
   @XmlEnumValue("boolean")
   @JsonProperty("boolean")
   BOOLEAN("boolean"),
   @XmlEnumValue("date")
   @JsonProperty("date")
   DATE("date"),
   @XmlEnumValue("time")
   @JsonProperty("time")
   TIME("time"),
   @XmlEnumValue("dateTime")
   @JsonProperty("dateTime")
   DATE_TIME("dateTime"),
   @XmlEnumValue("dateDaysSince[0]")
   @JsonProperty("dateDaysSince[0]")
   DATE_DAYS_SINCE_0("dateDaysSince[0]"),
   @XmlEnumValue("dateDaysSince[1960]")
   @JsonProperty("dateDaysSince[1960]")
   DATE_DAYS_SINCE_1960("dateDaysSince[1960]"),
   @XmlEnumValue("dateDaysSince[1970]")
   @JsonProperty("dateDaysSince[1970]")
   DATE_DAYS_SINCE_1970("dateDaysSince[1970]"),
   @XmlEnumValue("dateDaysSince[1980]")
   @JsonProperty("dateDaysSince[1980]")
   DATE_DAYS_SINCE_1980("dateDaysSince[1980]"),
   @XmlEnumValue("x-dateDaysSince[1990]")
   @JsonProperty("x-dateDaysSince[1990]")
   @Added(Version.XPMML)
   @Since("1.4.1")
   DATE_DAYS_SINCE_1990("x-dateDaysSince[1990]"),
   @XmlEnumValue("x-dateDaysSince[2000]")
   @JsonProperty("x-dateDaysSince[2000]")
   @Added(Version.XPMML)
   @Since("1.4.1")
   DATE_DAYS_SINCE_2000("x-dateDaysSince[2000]"),
   @XmlEnumValue("x-dateDaysSince[2010]")
   @JsonProperty("x-dateDaysSince[2010]")
   @Added(Version.XPMML)
   @Since("1.4.1")
   DATE_DAYS_SINCE_2010("x-dateDaysSince[2010]"),
   @XmlEnumValue("x-dateDaysSince[2020]")
   @JsonProperty("x-dateDaysSince[2020]")
   @Added(Version.XPMML)
   @Since("1.4.1")
   DATE_DAYS_SINCE_2020("x-dateDaysSince[2020]"),
   @XmlEnumValue("timeSeconds")
   @JsonProperty("timeSeconds")
   TIME_SECONDS("timeSeconds"),
   @XmlEnumValue("dateTimeSecondsSince[0]")
   @JsonProperty("dateTimeSecondsSince[0]")
   DATE_TIME_SECONDS_SINCE_0("dateTimeSecondsSince[0]"),
   @XmlEnumValue("dateTimeSecondsSince[1960]")
   @JsonProperty("dateTimeSecondsSince[1960]")
   DATE_TIME_SECONDS_SINCE_1960("dateTimeSecondsSince[1960]"),
   @XmlEnumValue("dateTimeSecondsSince[1970]")
   @JsonProperty("dateTimeSecondsSince[1970]")
   DATE_TIME_SECONDS_SINCE_1970("dateTimeSecondsSince[1970]"),
   @XmlEnumValue("dateTimeSecondsSince[1980]")
   @JsonProperty("dateTimeSecondsSince[1980]")
   DATE_TIME_SECONDS_SINCE_1980("dateTimeSecondsSince[1980]"),
   @XmlEnumValue("x-dateTimeSecondsSince[1990]")
   @JsonProperty("x-dateTimeSecondsSince[1990]")
   @Added(Version.XPMML)
   @Since("1.4.1")
   DATE_TIME_SECONDS_SINCE_1990("x-dateTimeSecondsSince[1990]"),
   @XmlEnumValue("x-dateTimeSecondsSince[2000]")
   @JsonProperty("x-dateTimeSecondsSince[2000]")
   @Added(Version.XPMML)
   @Since("1.4.1")
   DATE_TIME_SECONDS_SINCE_2000("x-dateTimeSecondsSince[2000]"),
   @XmlEnumValue("x-dateTimeSecondsSince[2010]")
   @JsonProperty("x-dateTimeSecondsSince[2010]")
   @Added(Version.XPMML)
   @Since("1.4.1")
   DATE_TIME_SECONDS_SINCE_2010("x-dateTimeSecondsSince[2010]"),
   @XmlEnumValue("x-dateTimeSecondsSince[2020]")
   @JsonProperty("x-dateTimeSecondsSince[2020]")
   @Added(Version.XPMML)
   @Since("1.4.1")
   DATE_TIME_SECONDS_SINCE_2020("x-dateTimeSecondsSince[2020]");

   private final String value;

   private DataType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static DataType fromValue(String v) {
      for(DataType c : values()) {
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
