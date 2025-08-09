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
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SimplePredicate",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("SimplePredicate")
@JsonPropertyOrder({"field", "operator", "value", "extensions"})
public class SimplePredicate extends Predicate implements HasExtensions, HasFieldReference, HasValue {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "operator",
      required = true
   )
   @JsonProperty("operator")
   private Operator operator;
   @XmlAttribute(
      name = "value"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("value")
   private Object value;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public SimplePredicate() {
   }

   @ValueConstructor
   public SimplePredicate(@Property("field") String field, @Property("operator") Operator operator, @Property("value") Object value) {
      this.field = field;
      this.operator = operator;
      this.value = value;
   }

   @AlternateValueConstructor
   public SimplePredicate(Field field, Operator operator, Object value) {
      this(field != null ? field.requireName() : null, operator, value);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SIMPLEPREDICATE_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public SimplePredicate setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Operator requireOperator() {
      if (this.operator == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SIMPLEPREDICATE_OPERATOR);
      } else {
         return this.operator;
      }
   }

   public Operator getOperator() {
      return this.operator;
   }

   public SimplePredicate setOperator(@Property("operator") Operator operator) {
      this.operator = operator;
      return this;
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SIMPLEPREDICATE_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public SimplePredicate setValue(@Property("value") Object value) {
      this.value = value;
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

   public SimplePredicate addExtensions(Extension... extensions) {
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
   public static enum Operator implements StringValue {
      @XmlEnumValue("equal")
      @JsonProperty("equal")
      EQUAL("equal"),
      @XmlEnumValue("notEqual")
      @JsonProperty("notEqual")
      NOT_EQUAL("notEqual"),
      @XmlEnumValue("lessThan")
      @JsonProperty("lessThan")
      LESS_THAN("lessThan"),
      @XmlEnumValue("lessOrEqual")
      @JsonProperty("lessOrEqual")
      LESS_OR_EQUAL("lessOrEqual"),
      @XmlEnumValue("greaterThan")
      @JsonProperty("greaterThan")
      GREATER_THAN("greaterThan"),
      @XmlEnumValue("greaterOrEqual")
      @JsonProperty("greaterOrEqual")
      GREATER_OR_EQUAL("greaterOrEqual"),
      @XmlEnumValue("isMissing")
      @JsonProperty("isMissing")
      IS_MISSING("isMissing"),
      @XmlEnumValue("isNotMissing")
      @JsonProperty("isNotMissing")
      IS_NOT_MISSING("isNotMissing");

      private final String value;

      private Operator(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Operator fromValue(String v) {
         for(Operator c : values()) {
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
