package org.sparkproject.dmg.pmml.time_series;

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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasFieldReference;
import org.sparkproject.dmg.pmml.HasTargetFieldReference;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "DynamicRegressor",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "numerator", "denominator", "regressorValues"}
)
@JsonRootName("DynamicRegressor")
@JsonPropertyOrder({"field", "transformation", "delay", "futureValuesMethod", "targetField", "extensions", "numerator", "denominator", "regressorValues"})
@Added(Version.PMML_4_4)
public class DynamicRegressor extends PMMLObject implements HasExtensions, HasFieldReference, HasTargetFieldReference {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "transformation"
   )
   @JsonProperty("transformation")
   private Transformation transformation;
   @XmlAttribute(
      name = "delay"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("delay")
   private Integer delay;
   @XmlAttribute(
      name = "futureValuesMethod"
   )
   @JsonProperty("futureValuesMethod")
   private FutureValuesMethod futureValuesMethod;
   @XmlAttribute(
      name = "targetField"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("targetField")
   private String targetField;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Numerator",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Numerator")
   private Numerator numerator;
   @XmlElement(
      name = "Denominator",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Denominator")
   private Denominator denominator;
   @XmlElement(
      name = "RegressorValues",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("RegressorValues")
   private RegressorValues regressorValues;
   private static final Integer DEFAULT_DELAY = (new IntegerAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public DynamicRegressor() {
   }

   @ValueConstructor
   public DynamicRegressor(@Property("field") String field) {
      this.field = field;
   }

   @AlternateValueConstructor
   public DynamicRegressor(Field field) {
      this(field != null ? field.requireName() : null);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DYNAMICREGRESSOR_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public DynamicRegressor setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Transformation getTransformation() {
      return this.transformation == null ? DynamicRegressor.Transformation.NONE : this.transformation;
   }

   public DynamicRegressor setTransformation(@Property("transformation") Transformation transformation) {
      this.transformation = transformation;
      return this;
   }

   public Integer getDelay() {
      return this.delay == null ? DEFAULT_DELAY : this.delay;
   }

   public DynamicRegressor setDelay(@Property("delay") Integer delay) {
      this.delay = delay;
      return this;
   }

   public FutureValuesMethod getFutureValuesMethod() {
      return this.futureValuesMethod == null ? DynamicRegressor.FutureValuesMethod.CONSTANT : this.futureValuesMethod;
   }

   public DynamicRegressor setFutureValuesMethod(@Property("futureValuesMethod") FutureValuesMethod futureValuesMethod) {
      this.futureValuesMethod = futureValuesMethod;
      return this;
   }

   public String getTargetField() {
      return this.targetField;
   }

   public DynamicRegressor setTargetField(@Property("targetField") String targetField) {
      this.targetField = targetField;
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

   public DynamicRegressor addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Numerator getNumerator() {
      return this.numerator;
   }

   public DynamicRegressor setNumerator(@Property("numerator") Numerator numerator) {
      this.numerator = numerator;
      return this;
   }

   public Denominator getDenominator() {
      return this.denominator;
   }

   public DynamicRegressor setDenominator(@Property("denominator") Denominator denominator) {
      this.denominator = denominator;
      return this;
   }

   public RegressorValues getRegressorValues() {
      return this.regressorValues;
   }

   public DynamicRegressor setRegressorValues(@Property("regressorValues") RegressorValues regressorValues) {
      this.regressorValues = regressorValues;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getNumerator(), this.getDenominator(), this.getRegressorValues());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum FutureValuesMethod implements StringValue {
      @XmlEnumValue("constant")
      @JsonProperty("constant")
      CONSTANT("constant"),
      @XmlEnumValue("trend")
      @JsonProperty("trend")
      TREND("trend"),
      @XmlEnumValue("stored")
      @JsonProperty("stored")
      STORED("stored"),
      @XmlEnumValue("otherModel")
      @JsonProperty("otherModel")
      OTHER_MODEL("otherModel"),
      @XmlEnumValue("userSupplied")
      @JsonProperty("userSupplied")
      USER_SUPPLIED("userSupplied");

      private final String value;

      private FutureValuesMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static FutureValuesMethod fromValue(String v) {
         for(FutureValuesMethod c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Transformation implements StringValue {
      @XmlEnumValue("none")
      @JsonProperty("none")
      NONE("none"),
      @XmlEnumValue("logarithmic")
      @JsonProperty("logarithmic")
      LOGARITHMIC("logarithmic"),
      @XmlEnumValue("squareroot")
      @JsonProperty("squareroot")
      SQUAREROOT("squareroot");

      private final String value;

      private Transformation(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Transformation fromValue(String v) {
         for(Transformation c : values()) {
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
