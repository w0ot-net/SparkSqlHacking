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
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "NormDiscrete",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("NormDiscrete")
@JsonPropertyOrder({"field", "method", "value", "mapMissingTo", "extensions"})
public class NormDiscrete extends Expression implements HasExtensions, HasFieldReference, HasMapMissingTo, HasValue {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "method"
   )
   @JsonProperty("method")
   @Removed(Version.PMML_4_3)
   private Method method;
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
      name = "mapMissingTo"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("mapMissingTo")
   private Number mapMissingTo;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public NormDiscrete() {
   }

   @ValueConstructor
   public NormDiscrete(@Property("field") String field, @Property("value") Object value) {
      this.field = field;
      this.value = value;
   }

   @AlternateValueConstructor
   public NormDiscrete(Field field, Object value) {
      this(field != null ? field.requireName() : null, value);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NORMDISCRETE_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public NormDiscrete setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Method getMethod() {
      return this.method == null ? NormDiscrete.Method.INDICATOR : this.method;
   }

   public NormDiscrete setMethod(@Property("method") Method method) {
      this.method = method;
      return this;
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NORMDISCRETE_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public NormDiscrete setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public Number getMapMissingTo() {
      return this.mapMissingTo;
   }

   public NormDiscrete setMapMissingTo(@Property("mapMissingTo") Number mapMissingTo) {
      this.mapMissingTo = mapMissingTo;
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

   public NormDiscrete addExtensions(Extension... extensions) {
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
   public static enum Method implements StringValue {
      @XmlEnumValue("indicator")
      @JsonProperty("indicator")
      @Removed(Version.PMML_4_3)
      INDICATOR("indicator"),
      @XmlEnumValue("thermometer")
      @JsonProperty("thermometer")
      @Removed(Version.PMML_3_2)
      THERMOMETER("thermometer");

      private final String value;

      private Method(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Method fromValue(String v) {
         for(Method c : values()) {
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
