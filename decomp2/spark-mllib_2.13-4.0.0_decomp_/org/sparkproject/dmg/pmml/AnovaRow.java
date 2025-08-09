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
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "AnovaRow",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("AnovaRow")
@JsonPropertyOrder({"type", "sumOfSquares", "degreesOfFreedom", "meanOfSquares", "fValue", "pValue", "extensions"})
@Added(Version.PMML_4_0)
public class AnovaRow extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "type",
      required = true
   )
   @JsonProperty("type")
   private Type type;
   @XmlAttribute(
      name = "sumOfSquares",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("sumOfSquares")
   private Number sumOfSquares;
   @XmlAttribute(
      name = "degreesOfFreedom",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("degreesOfFreedom")
   private Number degreesOfFreedom;
   @XmlAttribute(
      name = "meanOfSquares"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("meanOfSquares")
   private Number meanOfSquares;
   @XmlAttribute(
      name = "fValue"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("fValue")
   private Number fValue;
   @XmlAttribute(
      name = "pValue"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("pValue")
   private Number pValue;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public AnovaRow() {
   }

   @ValueConstructor
   public AnovaRow(@Property("type") Type type, @Property("sumOfSquares") Number sumOfSquares, @Property("degreesOfFreedom") Number degreesOfFreedom) {
      this.type = type;
      this.sumOfSquares = sumOfSquares;
      this.degreesOfFreedom = degreesOfFreedom;
   }

   public Type requireType() {
      if (this.type == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ANOVAROW_TYPE);
      } else {
         return this.type;
      }
   }

   public Type getType() {
      return this.type;
   }

   public AnovaRow setType(@Property("type") Type type) {
      this.type = type;
      return this;
   }

   public Number requireSumOfSquares() {
      if (this.sumOfSquares == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ANOVAROW_SUMOFSQUARES);
      } else {
         return this.sumOfSquares;
      }
   }

   public Number getSumOfSquares() {
      return this.sumOfSquares;
   }

   public AnovaRow setSumOfSquares(@Property("sumOfSquares") Number sumOfSquares) {
      this.sumOfSquares = sumOfSquares;
      return this;
   }

   public Number requireDegreesOfFreedom() {
      if (this.degreesOfFreedom == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ANOVAROW_DEGREESOFFREEDOM);
      } else {
         return this.degreesOfFreedom;
      }
   }

   public Number getDegreesOfFreedom() {
      return this.degreesOfFreedom;
   }

   public AnovaRow setDegreesOfFreedom(@Property("degreesOfFreedom") Number degreesOfFreedom) {
      this.degreesOfFreedom = degreesOfFreedom;
      return this;
   }

   public Number getMeanOfSquares() {
      return this.meanOfSquares;
   }

   public AnovaRow setMeanOfSquares(@Property("meanOfSquares") Number meanOfSquares) {
      this.meanOfSquares = meanOfSquares;
      return this;
   }

   public Number getFValue() {
      return this.fValue;
   }

   public AnovaRow setFValue(@Property("fValue") Number fValue) {
      this.fValue = fValue;
      return this;
   }

   public Number getPValue() {
      return this.pValue;
   }

   public AnovaRow setPValue(@Property("pValue") Number pValue) {
      this.pValue = pValue;
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

   public AnovaRow addExtensions(Extension... extensions) {
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
   public static enum Type implements StringValue {
      @XmlEnumValue("Model")
      @JsonProperty("Model")
      MODEL("Model"),
      @XmlEnumValue("Error")
      @JsonProperty("Error")
      ERROR("Error"),
      @XmlEnumValue("Total")
      @JsonProperty("Total")
      TOTAL("Total");

      private final String value;

      private Type(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Type fromValue(String v) {
         for(Type c : values()) {
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
