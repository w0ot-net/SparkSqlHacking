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
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Aggregate",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Aggregate")
@JsonPropertyOrder({"field", "function", "groupField", "sqlWhere", "extensions"})
public class Aggregate extends Expression implements HasExtensions, HasFieldReference {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "function",
      required = true
   )
   @JsonProperty("function")
   private Function function;
   @XmlAttribute(
      name = "groupField"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("groupField")
   private String groupField;
   @XmlAttribute(
      name = "sqlWhere"
   )
   @JsonProperty("sqlWhere")
   private String sqlWhere;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Aggregate() {
   }

   @ValueConstructor
   public Aggregate(@Property("field") String field, @Property("function") Function function) {
      this.field = field;
      this.function = function;
   }

   @AlternateValueConstructor
   public Aggregate(Field field, Function function) {
      this(field != null ? field.requireName() : null, function);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.AGGREGATE_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public Aggregate setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Function requireFunction() {
      if (this.function == null) {
         throw new MissingAttributeException(this, PMMLAttributes.AGGREGATE_FUNCTION);
      } else {
         return this.function;
      }
   }

   public Function getFunction() {
      return this.function;
   }

   public Aggregate setFunction(@Property("function") Function function) {
      this.function = function;
      return this;
   }

   public String getGroupField() {
      return this.groupField;
   }

   public Aggregate setGroupField(@Property("groupField") String groupField) {
      this.groupField = groupField;
      return this;
   }

   public String getSqlWhere() {
      return this.sqlWhere;
   }

   public Aggregate setSqlWhere(@Property("sqlWhere") String sqlWhere) {
      this.sqlWhere = sqlWhere;
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

   public Aggregate addExtensions(Extension... extensions) {
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
   public static enum Function implements StringValue {
      @XmlEnumValue("count")
      @JsonProperty("count")
      COUNT("count"),
      @XmlEnumValue("sum")
      @JsonProperty("sum")
      SUM("sum"),
      @XmlEnumValue("average")
      @JsonProperty("average")
      AVERAGE("average"),
      @XmlEnumValue("min")
      @JsonProperty("min")
      MIN("min"),
      @XmlEnumValue("max")
      @JsonProperty("max")
      MAX("max"),
      @XmlEnumValue("multiset")
      @JsonProperty("multiset")
      MULTISET("multiset");

      private final String value;

      private Function(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Function fromValue(String v) {
         for(Function c : values()) {
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
