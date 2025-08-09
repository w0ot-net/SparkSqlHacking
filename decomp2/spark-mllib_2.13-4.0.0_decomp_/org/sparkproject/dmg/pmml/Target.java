package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "Target",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "targetValues"}
)
@JsonRootName("Target")
@JsonPropertyOrder({"field", "opType", "castInteger", "min", "max", "rescaleConstant", "rescaleFactor", "extensions", "targetValues"})
public class Target extends PMMLObject implements HasExtensions, HasOpType, HasTargetFieldReference, Indexable {
   @XmlAttribute(
      name = "field"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   @Optional(Version.PMML_4_3)
   private String field;
   @XmlAttribute(
      name = "optype"
   )
   @JsonProperty("optype")
   private OpType opType;
   @XmlAttribute(
      name = "castInteger"
   )
   @JsonProperty("castInteger")
   private CastInteger castInteger;
   @XmlAttribute(
      name = "min"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("min")
   @Added(Version.PMML_3_1)
   private Number min;
   @XmlAttribute(
      name = "max"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("max")
   @Added(Version.PMML_3_1)
   private Number max;
   @XmlAttribute(
      name = "rescaleConstant"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("rescaleConstant")
   @Added(Version.PMML_3_1)
   private Number rescaleConstant;
   @XmlAttribute(
      name = "rescaleFactor"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("rescaleFactor")
   @Added(Version.PMML_3_1)
   private Number rescaleFactor;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "TargetValue",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TargetValue")
   @Optional(Version.PMML_3_1)
   @CollectionElementType(TargetValue.class)
   private List targetValues;
   private static final Number DEFAULT_RESCALE_CONSTANT = (new RealNumberAdapter()).unmarshal("0");
   private static final Number DEFAULT_RESCALE_FACTOR = (new RealNumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public String getKey() {
      return this.getTargetField();
   }

   public String getTargetField() {
      return this.getField();
   }

   public Target setTargetField(String targetField) {
      return this.setField(targetField);
   }

   public String getField() {
      return this.field;
   }

   public Target setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public OpType getOpType() {
      return this.opType;
   }

   public Target setOpType(@Property("opType") OpType opType) {
      this.opType = opType;
      return this;
   }

   public CastInteger getCastInteger() {
      return this.castInteger;
   }

   public Target setCastInteger(@Property("castInteger") CastInteger castInteger) {
      this.castInteger = castInteger;
      return this;
   }

   public Number getMin() {
      return this.min;
   }

   public Target setMin(@Property("min") Number min) {
      this.min = min;
      return this;
   }

   public Number getMax() {
      return this.max;
   }

   public Target setMax(@Property("max") Number max) {
      this.max = max;
      return this;
   }

   public Number getRescaleConstant() {
      return this.rescaleConstant == null ? DEFAULT_RESCALE_CONSTANT : this.rescaleConstant;
   }

   public Target setRescaleConstant(@Property("rescaleConstant") Number rescaleConstant) {
      this.rescaleConstant = rescaleConstant;
      return this;
   }

   public Number getRescaleFactor() {
      return this.rescaleFactor == null ? DEFAULT_RESCALE_FACTOR : this.rescaleFactor;
   }

   public Target setRescaleFactor(@Property("rescaleFactor") Number rescaleFactor) {
      this.rescaleFactor = rescaleFactor;
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

   public Target addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasTargetValues() {
      return this.targetValues != null && !this.targetValues.isEmpty();
   }

   public List getTargetValues() {
      if (this.targetValues == null) {
         this.targetValues = new ArrayList();
      }

      return this.targetValues;
   }

   public Target addTargetValues(TargetValue... targetValues) {
      this.getTargetValues().addAll(Arrays.asList(targetValues));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasTargetValues()) {
            status = PMMLObject.traverse(visitor, this.getTargetValues());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum CastInteger implements StringValue {
      @XmlEnumValue("round")
      @JsonProperty("round")
      ROUND("round"),
      @XmlEnumValue("ceiling")
      @JsonProperty("ceiling")
      CEILING("ceiling"),
      @XmlEnumValue("floor")
      @JsonProperty("floor")
      FLOOR("floor");

      private final String value;

      private CastInteger(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static CastInteger fromValue(String v) {
         for(CastInteger c : values()) {
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
