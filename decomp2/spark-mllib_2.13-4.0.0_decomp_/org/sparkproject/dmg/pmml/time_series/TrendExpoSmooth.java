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
import org.sparkproject.dmg.pmml.Array;
import org.sparkproject.dmg.pmml.HasRequiredArray;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Trend_ExpoSmooth",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"array"}
)
@JsonRootName("Trend_ExpoSmooth")
@JsonPropertyOrder({"trend", "gamma", "phi", "smoothedValue", "array"})
@Added(Version.PMML_4_0)
public class TrendExpoSmooth extends PMMLObject implements HasRequiredArray {
   @XmlAttribute(
      name = "trend"
   )
   @JsonProperty("trend")
   private Trend trend;
   @XmlAttribute(
      name = "gamma"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("gamma")
   private Number gamma;
   @XmlAttribute(
      name = "phi"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("phi")
   private Number phi;
   @XmlAttribute(
      name = "smoothedValue"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("smoothedValue")
   @Optional(Version.PMML_4_1)
   private Number smoothedValue;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   private Array array;
   private static final Number DEFAULT_PHI = (new RealNumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public TrendExpoSmooth() {
   }

   @ValueConstructor
   public TrendExpoSmooth(@Property("array") Array array) {
      this.array = array;
   }

   public Trend getTrend() {
      return this.trend == null ? TrendExpoSmooth.Trend.ADDITIVE : this.trend;
   }

   public TrendExpoSmooth setTrend(@Property("trend") Trend trend) {
      this.trend = trend;
      return this;
   }

   public Number getGamma() {
      return this.gamma;
   }

   public TrendExpoSmooth setGamma(@Property("gamma") Number gamma) {
      this.gamma = gamma;
      return this;
   }

   public Number getPhi() {
      return this.phi == null ? DEFAULT_PHI : this.phi;
   }

   public TrendExpoSmooth setPhi(@Property("phi") Number phi) {
      this.phi = phi;
      return this;
   }

   public Number getSmoothedValue() {
      return this.smoothedValue;
   }

   public TrendExpoSmooth setSmoothedValue(@Property("smoothedValue") Number smoothedValue) {
      this.smoothedValue = smoothedValue;
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.TRENDEXPOSMOOTH_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public TrendExpoSmooth setArray(@Property("array") Array array) {
      this.array = array;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getArray());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Trend implements StringValue {
      @XmlEnumValue("additive")
      @JsonProperty("additive")
      ADDITIVE("additive"),
      @XmlEnumValue("damped_additive")
      @JsonProperty("damped_additive")
      DAMPED_ADDITIVE("damped_additive"),
      @XmlEnumValue("multiplicative")
      @JsonProperty("multiplicative")
      MULTIPLICATIVE("multiplicative"),
      @XmlEnumValue("damped_multiplicative")
      @JsonProperty("damped_multiplicative")
      DAMPED_MULTIPLICATIVE("damped_multiplicative"),
      @XmlEnumValue("double_exponential")
      @JsonProperty("double_exponential")
      @Removed(Version.PMML_4_1)
      DOUBLE_EXPONENTIAL("double_exponential"),
      @XmlEnumValue("polynomial_exponential")
      @JsonProperty("polynomial_exponential")
      @Added(Version.PMML_4_1)
      POLYNOMIAL_EXPONENTIAL("polynomial_exponential");

      private final String value;

      private Trend(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Trend fromValue(String v) {
         for(Trend c : values()) {
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
