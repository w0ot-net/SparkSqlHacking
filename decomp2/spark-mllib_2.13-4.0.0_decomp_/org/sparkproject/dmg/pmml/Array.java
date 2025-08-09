package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.oxm.annotations.XmlValueExtension;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Array",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"value"}
)
@JsonRootName("Array")
@JsonPropertyOrder({"n", "type", "value"})
public class Array extends PMMLObject {
   @XmlAttribute(
      name = "n"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("n")
   private Integer n;
   @XmlAttribute(
      name = "type",
      required = true
   )
   @JsonProperty("type")
   private Type type;
   @XmlValue
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @XmlValueExtension
   @JsonProperty("value")
   private Object value;
   private static final long serialVersionUID = 67371272L;

   public Array() {
   }

   @ValueConstructor
   public Array(@Property("type") Type type, @Property("value") Object value) {
      this.type = type;
      this.value = value;
   }

   public Integer getN() {
      return this.n;
   }

   public Array setN(@Property("n") Integer n) {
      this.n = n;
      return this;
   }

   public Type requireType() {
      if (this.type == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ARRAY_TYPE);
      } else {
         return this.type;
      }
   }

   public Type getType() {
      return this.type;
   }

   public Array setType(@Property("type") Type type) {
      this.type = type;
      return this;
   }

   public Object getValue() {
      return this.value;
   }

   public Array setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Type implements StringValue {
      @XmlEnumValue("int")
      @JsonProperty("int")
      INT("int"),
      @XmlEnumValue("real")
      @JsonProperty("real")
      REAL("real"),
      @XmlEnumValue("string")
      @JsonProperty("string")
      STRING("string");

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
