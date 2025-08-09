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
import org.sparkproject.dmg.pmml.HasDisplayName;
import org.sparkproject.dmg.pmml.HasRequiredArray;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TimeCycle",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"array"}
)
@JsonRootName("TimeCycle")
@JsonPropertyOrder({"length", "type", "displayName", "array"})
@Added(Version.PMML_4_0)
public class TimeCycle extends PMMLObject implements HasDisplayName, HasRequiredArray {
   @XmlAttribute(
      name = "length"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("length")
   private Integer length;
   @XmlAttribute(
      name = "type"
   )
   @JsonProperty("type")
   private Type type;
   @XmlAttribute(
      name = "displayName"
   )
   @JsonProperty("displayName")
   private String displayName;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   private Array array;
   private static final long serialVersionUID = 67371272L;

   public TimeCycle() {
   }

   @ValueConstructor
   public TimeCycle(@Property("array") Array array) {
      this.array = array;
   }

   public Integer getLength() {
      return this.length;
   }

   public TimeCycle setLength(@Property("length") Integer length) {
      this.length = length;
      return this;
   }

   public Type getType() {
      return this.type;
   }

   public TimeCycle setType(@Property("type") Type type) {
      this.type = type;
      return this;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public TimeCycle setDisplayName(@Property("displayName") String displayName) {
      this.displayName = displayName;
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.TIMECYCLE_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public TimeCycle setArray(@Property("array") Array array) {
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
      @XmlEnumValue("includeAll")
      @JsonProperty("includeAll")
      INCLUDE_ALL("includeAll"),
      @XmlEnumValue("includeFromTo")
      @JsonProperty("includeFromTo")
      INCLUDE_FROM_TO("includeFromTo"),
      @XmlEnumValue("excludeFromTo")
      @JsonProperty("excludeFromTo")
      EXCLUDE_FROM_TO("excludeFromTo"),
      @XmlEnumValue("includeSet")
      @JsonProperty("includeSet")
      INCLUDE_SET("includeSet"),
      @XmlEnumValue("excludeSet")
      @JsonProperty("excludeSet")
      EXCLUDE_SET("excludeSet");

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
