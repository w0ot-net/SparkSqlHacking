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
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Value",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Value")
@JsonPropertyOrder({"value", "displayValue", "property", "extensions"})
public class Value extends PMMLObject implements HasDisplayValue, HasExtensions, HasValue {
   @XmlAttribute(
      name = "value",
      required = true
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("value")
   private Object value;
   @XmlAttribute(
      name = "displayValue"
   )
   @JsonProperty("displayValue")
   private String displayValue;
   @XmlAttribute(
      name = "property"
   )
   @JsonProperty("property")
   private Property property;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Value() {
   }

   @ValueConstructor
   public Value(@org.sparkproject.jpmml.model.annotations.Property("value") Object value) {
      this.value = value;
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.VALUE_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public Value setValue(@org.sparkproject.jpmml.model.annotations.Property("value") Object value) {
      this.value = value;
      return this;
   }

   public String getDisplayValue() {
      return this.displayValue;
   }

   public Value setDisplayValue(@org.sparkproject.jpmml.model.annotations.Property("displayValue") String displayValue) {
      this.displayValue = displayValue;
      return this;
   }

   public Property getProperty() {
      return this.property == null ? Value.Property.VALID : this.property;
   }

   public Value setProperty(@org.sparkproject.jpmml.model.annotations.Property("property") Property property) {
      this.property = property;
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

   public Value addExtensions(Extension... extensions) {
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
   public static enum Property implements StringValue {
      @XmlEnumValue("valid")
      @JsonProperty("valid")
      VALID("valid"),
      @XmlEnumValue("invalid")
      @JsonProperty("invalid")
      INVALID("invalid"),
      @XmlEnumValue("missing")
      @JsonProperty("missing")
      MISSING("missing");

      private final String value;

      private Property(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Property fromValue(String v) {
         for(Property c : values()) {
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
