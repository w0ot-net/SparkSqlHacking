package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Deprecated;
import org.sparkproject.jpmml.model.annotations.Since;

@XmlType(
   name = "RESULT-FEATURE",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlEnum
public enum ResultFeature implements StringValue {
   @XmlEnumValue("predictedValue")
   @JsonProperty("predictedValue")
   PREDICTED_VALUE("predictedValue"),
   @XmlEnumValue("predictedDisplayValue")
   @JsonProperty("predictedDisplayValue")
   PREDICTED_DISPLAY_VALUE("predictedDisplayValue"),
   @XmlEnumValue("transformedValue")
   @JsonProperty("transformedValue")
   @Added(Version.PMML_4_1)
   TRANSFORMED_VALUE("transformedValue"),
   @XmlEnumValue("decision")
   @JsonProperty("decision")
   @Added(Version.PMML_4_1)
   DECISION("decision"),
   @XmlEnumValue("probability")
   @JsonProperty("probability")
   PROBABILITY("probability"),
   @XmlEnumValue("affinity")
   @JsonProperty("affinity")
   @Added(Version.PMML_4_1)
   AFFINITY("affinity"),
   @XmlEnumValue("residual")
   @JsonProperty("residual")
   RESIDUAL("residual"),
   @XmlEnumValue("standardError")
   @JsonProperty("standardError")
   STANDARD_ERROR("standardError"),
   @XmlEnumValue("standardDeviation")
   @JsonProperty("standardDeviation")
   @Added(Version.PMML_4_4)
   STANDARD_DEVIATION("standardDeviation"),
   @XmlEnumValue("clusterId")
   @JsonProperty("clusterId")
   @Deprecated(Version.PMML_4_1)
   CLUSTER_ID("clusterId"),
   @XmlEnumValue("clusterAffinity")
   @JsonProperty("clusterAffinity")
   @Deprecated(Version.PMML_4_1)
   CLUSTER_AFFINITY("clusterAffinity"),
   @XmlEnumValue("entityId")
   @JsonProperty("entityId")
   @Added(Version.PMML_3_2)
   ENTITY_ID("entityId"),
   @XmlEnumValue("entityAffinity")
   @JsonProperty("entityAffinity")
   @Added(Version.PMML_3_2)
   @Deprecated(Version.PMML_4_1)
   ENTITY_AFFINITY("entityAffinity"),
   @XmlEnumValue("warning")
   @JsonProperty("warning")
   WARNING("warning"),
   @XmlEnumValue("ruleValue")
   @JsonProperty("ruleValue")
   @Added(Version.PMML_4_0)
   @Deprecated(Version.PMML_4_2)
   RULE_VALUE("ruleValue"),
   @XmlEnumValue("reasonCode")
   @JsonProperty("reasonCode")
   @Added(Version.PMML_4_1)
   REASON_CODE("reasonCode"),
   @XmlEnumValue("antecedent")
   @JsonProperty("antecedent")
   @Added(Version.PMML_4_2)
   ANTECEDENT("antecedent"),
   @XmlEnumValue("consequent")
   @JsonProperty("consequent")
   @Added(Version.PMML_4_2)
   CONSEQUENT("consequent"),
   @XmlEnumValue("rule")
   @JsonProperty("rule")
   @Added(Version.PMML_4_2)
   RULE("rule"),
   @XmlEnumValue("ruleId")
   @JsonProperty("ruleId")
   @Added(Version.PMML_4_2)
   @Deprecated(Version.PMML_4_2)
   RULE_ID("ruleId"),
   @XmlEnumValue("confidence")
   @JsonProperty("confidence")
   @Added(Version.PMML_4_2)
   CONFIDENCE("confidence"),
   @XmlEnumValue("support")
   @JsonProperty("support")
   @Added(Version.PMML_4_2)
   SUPPORT("support"),
   @XmlEnumValue("lift")
   @JsonProperty("lift")
   @Added(Version.PMML_4_2)
   LIFT("lift"),
   @XmlEnumValue("leverage")
   @JsonProperty("leverage")
   @Added(Version.PMML_4_2)
   LEVERAGE("leverage"),
   @XmlEnumValue("confidenceIntervalLower")
   @JsonProperty("confidenceIntervalLower")
   CONFIDENCE_INTERVAL_LOWER("confidenceIntervalLower"),
   @XmlEnumValue("confidenceIntervalUpper")
   @JsonProperty("confidenceIntervalUpper")
   CONFIDENCE_INTERVAL_UPPER("confidenceIntervalUpper"),
   @XmlEnumValue("x-report")
   @JsonProperty("x-report")
   @Added(Version.XPMML)
   @Since("1.3.8")
   REPORT("x-report");

   private final String value;

   private ResultFeature(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static ResultFeature fromValue(String v) {
      for(ResultFeature c : values()) {
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
