package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "CompoundPredicate",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "predicates"}
)
@JsonRootName("CompoundPredicate")
@JsonPropertyOrder({"booleanOperator", "extensions", "predicates"})
public class CompoundPredicate extends Predicate implements HasExtensions {
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
   @XmlElements({@XmlElement(
   name = "SimplePredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimplePredicate.class
), @XmlElement(
   name = "CompoundPredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = CompoundPredicate.class
), @XmlElement(
   name = "SimpleSetPredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimpleSetPredicate.class
), @XmlElement(
   name = "True",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = True.class
), @XmlElement(
   name = "False",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = False.class
)})
   @JsonProperty("Predicate")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "SimplePredicate",
   value = SimplePredicate.class
), @Type(
   name = "CompoundPredicate",
   value = CompoundPredicate.class
), @Type(
   name = "SimpleSetPredicate",
   value = SimpleSetPredicate.class
), @Type(
   name = "True",
   value = True.class
), @Type(
   name = "False",
   value = False.class
)})
   @CollectionElementType(Predicate.class)
   private List predicates;
   private static final long serialVersionUID = 67371272L;

   public CompoundPredicate() {
   }

   @ValueConstructor
   public CompoundPredicate(@Property("booleanOperator") BooleanOperator booleanOperator, @Property("predicates") List predicates) {
      this.booleanOperator = booleanOperator;
      this.predicates = predicates;
   }

   public BooleanOperator requireBooleanOperator() {
      if (this.booleanOperator == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPOUNDPREDICATE_BOOLEANOPERATOR);
      } else {
         return this.booleanOperator;
      }
   }

   public BooleanOperator getBooleanOperator() {
      return this.booleanOperator;
   }

   public CompoundPredicate setBooleanOperator(@Property("booleanOperator") BooleanOperator booleanOperator) {
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

   public CompoundPredicate addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasPredicates() {
      return this.predicates != null && !this.predicates.isEmpty();
   }

   public List requirePredicates() {
      if (this.predicates != null && !this.predicates.isEmpty()) {
         return this.predicates;
      } else {
         throw new MissingElementException(this, PMMLElements.COMPOUNDPREDICATE_PREDICATES);
      }
   }

   public List getPredicates() {
      if (this.predicates == null) {
         this.predicates = new ArrayList();
      }

      return this.predicates;
   }

   public CompoundPredicate addPredicates(Predicate... predicates) {
      this.getPredicates().addAll(Arrays.asList(predicates));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasPredicates()) {
            status = PMMLObject.traverse(visitor, this.getPredicates());
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
      @XmlEnumValue("or")
      @JsonProperty("or")
      OR("or"),
      @XmlEnumValue("and")
      @JsonProperty("and")
      AND("and"),
      @XmlEnumValue("xor")
      @JsonProperty("xor")
      XOR("xor"),
      @XmlEnumValue("surrogate")
      @JsonProperty("surrogate")
      SURROGATE("surrogate");

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
