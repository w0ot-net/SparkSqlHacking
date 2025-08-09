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
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SimpleSetPredicate",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "array"}
)
@JsonRootName("SimpleSetPredicate")
@JsonPropertyOrder({"field", "booleanOperator", "extensions", "array"})
public class SimpleSetPredicate extends Predicate implements HasExtensions, HasFieldReference, HasRequiredArray, HasValueSet {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "booleanOperator",
      required = true
   )
   @JsonProperty("booleanOperator")
   private BooleanOperator booleanOperator;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Array")
   private Array array;
   private static final long serialVersionUID = 67371272L;

   public SimpleSetPredicate() {
   }

   @ValueConstructor
   public SimpleSetPredicate(@Property("field") String field, @Property("booleanOperator") BooleanOperator booleanOperator, @Property("array") Array array) {
      this.field = field;
      this.booleanOperator = booleanOperator;
      this.array = array;
   }

   @AlternateValueConstructor
   public SimpleSetPredicate(Field field, BooleanOperator booleanOperator, Array array) {
      this(field != null ? field.requireName() : null, booleanOperator, array);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SIMPLESETPREDICATE_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public SimpleSetPredicate setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public BooleanOperator requireBooleanOperator() {
      if (this.booleanOperator == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SIMPLESETPREDICATE_BOOLEANOPERATOR);
      } else {
         return this.booleanOperator;
      }
   }

   public BooleanOperator getBooleanOperator() {
      return this.booleanOperator;
   }

   public SimpleSetPredicate setBooleanOperator(@Property("booleanOperator") BooleanOperator booleanOperator) {
      this.booleanOperator = booleanOperator;
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

   public SimpleSetPredicate addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.SIMPLESETPREDICATE_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public SimpleSetPredicate setArray(@Property("array") Array array) {
      this.array = array;
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
   public static enum BooleanOperator implements StringValue {
      @XmlEnumValue("isIn")
      @JsonProperty("isIn")
      IS_IN("isIn"),
      @XmlEnumValue("isNotIn")
      @JsonProperty("isNotIn")
      IS_NOT_IN("isNotIn");

      private final String value;

      private BooleanOperator(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static BooleanOperator fromValue(String v) {
         for(BooleanOperator c : values()) {
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
