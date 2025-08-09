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
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TimeException",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"array"}
)
@JsonRootName("TimeException")
@JsonPropertyOrder({"type", "count", "array"})
@Added(Version.PMML_4_0)
public class TimeException extends PMMLObject implements HasRequiredArray {
   @XmlAttribute(
      name = "type"
   )
   @JsonProperty("type")
   private Type type;
   @XmlAttribute(
      name = "count"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("count")
   private Integer count;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   private Array array;
   private static final long serialVersionUID = 67371272L;

   public TimeException() {
   }

   @ValueConstructor
   public TimeException(@Property("array") Array array) {
      this.array = array;
   }

   public Type getType() {
      return this.type;
   }

   public TimeException setType(@Property("type") Type type) {
      this.type = type;
      return this;
   }

   public Integer getCount() {
      return this.count;
   }

   public TimeException setCount(@Property("count") Integer count) {
      this.count = count;
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.TIMEEXCEPTION_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public TimeException setArray(@Property("array") Array array) {
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
      @XmlEnumValue("exclude")
      @JsonProperty("exclude")
      EXCLUDE("exclude"),
      @XmlEnumValue("include")
      @JsonProperty("include")
      INCLUDE("include");

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
