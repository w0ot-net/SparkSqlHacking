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
import org.sparkproject.dmg.pmml.adapters.PositiveIntegerAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Lag",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "blockIndicators"}
)
@JsonRootName("Lag")
@JsonPropertyOrder({"field", "n", "aggregate", "extensions", "blockIndicators"})
@Added(Version.PMML_4_3)
public class Lag extends Expression implements HasExtensions, HasFieldReference {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "n"
   )
   @XmlJavaTypeAdapter(PositiveIntegerAdapter.class)
   @XmlSchemaType(
      name = "positiveInteger"
   )
   @JsonProperty("n")
   private Integer n;
   @XmlAttribute(
      name = "aggregate"
   )
   @JsonProperty("aggregate")
   @Added(Version.PMML_4_4)
   private Aggregate aggregate;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "BlockIndicator",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("BlockIndicator")
   @CollectionElementType(BlockIndicator.class)
   private List blockIndicators;
   private static final Integer DEFAULT_N = (new PositiveIntegerAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public Lag() {
   }

   @ValueConstructor
   public Lag(@Property("field") String field) {
      this.field = field;
   }

   @AlternateValueConstructor
   public Lag(Field field) {
      this(field != null ? field.requireName() : null);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.LAG_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public Lag setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Integer getN() {
      return this.n == null ? DEFAULT_N : this.n;
   }

   public Lag setN(@Property("n") Integer n) {
      this.n = n;
      return this;
   }

   public Aggregate getAggregate() {
      return this.aggregate == null ? Lag.Aggregate.NONE : this.aggregate;
   }

   public Lag setAggregate(@Property("aggregate") Aggregate aggregate) {
      this.aggregate = aggregate;
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

   public Lag addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasBlockIndicators() {
      return this.blockIndicators != null && !this.blockIndicators.isEmpty();
   }

   public List getBlockIndicators() {
      if (this.blockIndicators == null) {
         this.blockIndicators = new ArrayList();
      }

      return this.blockIndicators;
   }

   public Lag addBlockIndicators(BlockIndicator... blockIndicators) {
      this.getBlockIndicators().addAll(Arrays.asList(blockIndicators));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasBlockIndicators()) {
            status = PMMLObject.traverse(visitor, this.getBlockIndicators());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Aggregate implements StringValue {
      @XmlEnumValue("none")
      @JsonProperty("none")
      NONE("none"),
      @XmlEnumValue("avg")
      @JsonProperty("avg")
      AVG("avg"),
      @XmlEnumValue("max")
      @JsonProperty("max")
      MAX("max"),
      @XmlEnumValue("median")
      @JsonProperty("median")
      MEDIAN("median"),
      @XmlEnumValue("min")
      @JsonProperty("min")
      MIN("min"),
      @XmlEnumValue("product")
      @JsonProperty("product")
      PRODUCT("product"),
      @XmlEnumValue("sum")
      @JsonProperty("sum")
      SUM("sum"),
      @XmlEnumValue("stddev")
      @JsonProperty("stddev")
      STDDEV("stddev");

      private final String value;

      private Aggregate(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Aggregate fromValue(String v) {
         for(Aggregate c : values()) {
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
