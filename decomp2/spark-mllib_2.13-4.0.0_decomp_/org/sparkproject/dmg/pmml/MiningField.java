package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Deprecated;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "MiningField",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("MiningField")
@JsonPropertyOrder({"name", "usageType", "opType", "importance", "outlierTreatment", "lowValue", "highValue", "missingValueReplacement", "missingValueTreatment", "invalidValueTreatment", "invalidValueReplacement", "extensions"})
public class MiningField extends PMMLObject implements HasExtensions, HasFieldReference, HasOpType, Indexable {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "usageType"
   )
   @JsonProperty("usageType")
   private UsageType usageType;
   @XmlAttribute(
      name = "optype"
   )
   @JsonProperty("optype")
   private OpType opType;
   @XmlAttribute(
      name = "importance"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("importance")
   private Number importance;
   @XmlAttribute(
      name = "outliers"
   )
   @JsonProperty("outliers")
   private OutlierTreatmentMethod outlierTreatment;
   @XmlAttribute(
      name = "lowValue"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("lowValue")
   private Number lowValue;
   @XmlAttribute(
      name = "highValue"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("highValue")
   private Number highValue;
   @XmlAttribute(
      name = "missingValueReplacement"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("missingValueReplacement")
   private Object missingValueReplacement;
   @XmlAttribute(
      name = "missingValueTreatment"
   )
   @JsonProperty("missingValueTreatment")
   private MissingValueTreatmentMethod missingValueTreatment;
   @XmlAttribute(
      name = "invalidValueTreatment"
   )
   @JsonProperty("invalidValueTreatment")
   @Added(Version.PMML_3_1)
   private InvalidValueTreatmentMethod invalidValueTreatment;
   @XmlAttribute(
      name = "invalidValueReplacement"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("invalidValueReplacement")
   @Added(Version.PMML_4_4)
   @Since("1.3.8")
   private Object invalidValueReplacement;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public MiningField() {
   }

   @ValueConstructor
   public MiningField(@Property("name") String name) {
      this.name = name;
   }

   @AlternateValueConstructor
   public MiningField(Field nameField) {
      this(nameField != null ? nameField.requireName() : null);
   }

   public String requireField() {
      return this.requireName();
   }

   public String getKey() {
      return this.requireName();
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.MININGFIELD_NAME);
      } else {
         return this.name;
      }
   }

   public String getField() {
      return this.getName();
   }

   public MiningField setField(String field) {
      return this.setName(field);
   }

   public String getName() {
      return this.name;
   }

   public MiningField setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public UsageType getUsageType() {
      return this.usageType == null ? MiningField.UsageType.ACTIVE : this.usageType;
   }

   public MiningField setUsageType(@Property("usageType") UsageType usageType) {
      this.usageType = usageType;
      return this;
   }

   public OpType getOpType() {
      return this.opType;
   }

   public MiningField setOpType(@Property("opType") OpType opType) {
      this.opType = opType;
      return this;
   }

   public Number getImportance() {
      return this.importance;
   }

   public MiningField setImportance(@Property("importance") Number importance) {
      this.importance = importance;
      return this;
   }

   public OutlierTreatmentMethod getOutlierTreatment() {
      return this.outlierTreatment == null ? OutlierTreatmentMethod.AS_IS : this.outlierTreatment;
   }

   public MiningField setOutlierTreatment(@Property("outlierTreatment") OutlierTreatmentMethod outlierTreatment) {
      this.outlierTreatment = outlierTreatment;
      return this;
   }

   public Number getLowValue() {
      return this.lowValue;
   }

   public MiningField setLowValue(@Property("lowValue") Number lowValue) {
      this.lowValue = lowValue;
      return this;
   }

   public Number getHighValue() {
      return this.highValue;
   }

   public MiningField setHighValue(@Property("highValue") Number highValue) {
      this.highValue = highValue;
      return this;
   }

   public Object getMissingValueReplacement() {
      return this.missingValueReplacement;
   }

   public MiningField setMissingValueReplacement(@Property("missingValueReplacement") Object missingValueReplacement) {
      this.missingValueReplacement = missingValueReplacement;
      return this;
   }

   public MissingValueTreatmentMethod getMissingValueTreatment() {
      return this.missingValueTreatment;
   }

   public MiningField setMissingValueTreatment(@Property("missingValueTreatment") MissingValueTreatmentMethod missingValueTreatment) {
      this.missingValueTreatment = missingValueTreatment;
      return this;
   }

   public InvalidValueTreatmentMethod getInvalidValueTreatment() {
      return this.invalidValueTreatment == null ? InvalidValueTreatmentMethod.RETURN_INVALID : this.invalidValueTreatment;
   }

   public MiningField setInvalidValueTreatment(@Property("invalidValueTreatment") InvalidValueTreatmentMethod invalidValueTreatment) {
      this.invalidValueTreatment = invalidValueTreatment;
      return this;
   }

   public Object getInvalidValueReplacement() {
      return this.invalidValueReplacement;
   }

   public MiningField setInvalidValueReplacement(@Property("invalidValueReplacement") Object invalidValueReplacement) {
      this.invalidValueReplacement = invalidValueReplacement;
      return this;
   }

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public List getExtensions() {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      return this.extensions;
   }

   public MiningField addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum UsageType implements StringValue {
      @XmlEnumValue("active")
      @JsonProperty("active")
      ACTIVE("active"),
      @XmlEnumValue("predicted")
      @JsonProperty("predicted")
      @Deprecated(Version.PMML_4_2)
      PREDICTED("predicted"),
      @XmlEnumValue("target")
      @JsonProperty("target")
      @Added(Version.PMML_4_2)
      TARGET("target"),
      @XmlEnumValue("supplementary")
      @JsonProperty("supplementary")
      SUPPLEMENTARY("supplementary"),
      @XmlEnumValue("group")
      @JsonProperty("group")
      GROUP("group"),
      @XmlEnumValue("order")
      @JsonProperty("order")
      ORDER("order"),
      @XmlEnumValue("frequencyWeight")
      @JsonProperty("frequencyWeight")
      @Added(Version.PMML_4_0)
      FREQUENCY_WEIGHT("frequencyWeight"),
      @XmlEnumValue("analysisWeight")
      @JsonProperty("analysisWeight")
      @Added(Version.PMML_4_0)
      ANALYSIS_WEIGHT("analysisWeight");

      private final String value;

      private UsageType(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static UsageType fromValue(String v) {
         for(UsageType c : values()) {
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
}
