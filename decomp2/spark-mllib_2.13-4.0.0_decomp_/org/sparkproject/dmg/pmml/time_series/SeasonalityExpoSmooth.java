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
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Seasonality_ExpoSmooth",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"array"}
)
@JsonRootName("Seasonality_ExpoSmooth")
@JsonPropertyOrder({"type", "period", "unit", "phase", "delta", "array"})
@Added(Version.PMML_4_0)
public class SeasonalityExpoSmooth extends PMMLObject implements HasRequiredArray {
   @XmlAttribute(
      name = "type",
      required = true
   )
   @JsonProperty("type")
   private Type type;
   @XmlAttribute(
      name = "period",
      required = true
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("period")
   private Integer period;
   @XmlAttribute(
      name = "unit"
   )
   @JsonProperty("unit")
   private String unit;
   @XmlAttribute(
      name = "phase"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("phase")
   private Integer phase;
   @XmlAttribute(
      name = "delta"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("delta")
   private Number delta;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   private Array array;
   private static final long serialVersionUID = 67371272L;

   public SeasonalityExpoSmooth() {
   }

   @ValueConstructor
   public SeasonalityExpoSmooth(@Property("type") Type type, @Property("period") Integer period, @Property("array") Array array) {
      this.type = type;
      this.period = period;
      this.array = array;
   }

   public Type requireType() {
      if (this.type == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEASONALITYEXPOSMOOTH_TYPE);
      } else {
         return this.type;
      }
   }

   public Type getType() {
      return this.type;
   }

   public SeasonalityExpoSmooth setType(@Property("type") Type type) {
      this.type = type;
      return this;
   }

   public Integer requirePeriod() {
      if (this.period == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEASONALITYEXPOSMOOTH_PERIOD);
      } else {
         return this.period;
      }
   }

   public Integer getPeriod() {
      return this.period;
   }

   public SeasonalityExpoSmooth setPeriod(@Property("period") Integer period) {
      this.period = period;
      return this;
   }

   public String getUnit() {
      return this.unit;
   }

   public SeasonalityExpoSmooth setUnit(@Property("unit") String unit) {
      this.unit = unit;
      return this;
   }

   public Integer getPhase() {
      return this.phase;
   }

   public SeasonalityExpoSmooth setPhase(@Property("phase") Integer phase) {
      this.phase = phase;
      return this;
   }

   public Number getDelta() {
      return this.delta;
   }

   public SeasonalityExpoSmooth setDelta(@Property("delta") Number delta) {
      this.delta = delta;
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.SEASONALITYEXPOSMOOTH_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public SeasonalityExpoSmooth setArray(@Property("array") Array array) {
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
   public static enum Type implements StringValue {
      @XmlEnumValue("additive")
      @JsonProperty("additive")
      ADDITIVE("additive"),
      @XmlEnumValue("multiplicative")
      @JsonProperty("multiplicative")
      MULTIPLICATIVE("multiplicative");

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
