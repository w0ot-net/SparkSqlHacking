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
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Required;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "DataField",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "intervals", "values"}
)
@JsonRootName("DataField")
@JsonPropertyOrder({"name", "displayName", "opType", "dataType", "taxonomy", "cyclic", "extensions", "intervals", "values"})
public class DataField extends Field implements Decorable, HasContinuousDomain, HasDiscreteDomain, HasExtensions {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "displayName"
   )
   @JsonProperty("displayName")
   private String displayName;
   @XmlAttribute(
      name = "optype",
      required = true
   )
   @JsonProperty("optype")
   private OpType opType;
   @XmlAttribute(
      name = "dataType",
      required = true
   )
   @JsonProperty("dataType")
   @Required(Version.PMML_3_1)
   private DataType dataType;
   @XmlAttribute(
      name = "taxonomy"
   )
   @JsonProperty("taxonomy")
   private String taxonomy;
   @XmlAttribute(
      name = "isCyclic"
   )
   @JsonProperty("isCyclic")
   private Cyclic cyclic;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Interval",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Interval")
   @CollectionElementType(Interval.class)
   private List intervals;
   @XmlElement(
      name = "Value",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Value")
   @CollectionElementType(Value.class)
   private List values;
   private static final long serialVersionUID = 67371272L;

   public DataField() {
   }

   @ValueConstructor
   public DataField(@Property("name") String name, @Property("opType") OpType opType, @Property("dataType") DataType dataType) {
      this.name = name;
      this.opType = opType;
      this.dataType = dataType;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DATAFIELD_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public DataField setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public DataField setDisplayName(@Property("displayName") String displayName) {
      this.displayName = displayName;
      return this;
   }

   public OpType requireOpType() {
      if (this.opType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DATAFIELD_OPTYPE);
      } else {
         return this.opType;
      }
   }

   public OpType getOpType() {
      return this.opType;
   }

   public DataField setOpType(@Property("opType") OpType opType) {
      this.opType = opType;
      return this;
   }

   public DataType requireDataType() {
      if (this.dataType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DATAFIELD_DATATYPE);
      } else {
         return this.dataType;
      }
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public DataField setDataType(@Property("dataType") DataType dataType) {
      this.dataType = dataType;
      return this;
   }

   public String getTaxonomy() {
      return this.taxonomy;
   }

   public DataField setTaxonomy(@Property("taxonomy") String taxonomy) {
      this.taxonomy = taxonomy;
      return this;
   }

   public Cyclic getCyclic() {
      return this.cyclic == null ? DataField.Cyclic.ZERO : this.cyclic;
   }

   public DataField setCyclic(@Property("cyclic") Cyclic cyclic) {
      this.cyclic = cyclic;
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

   public DataField addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasIntervals() {
      return this.intervals != null && !this.intervals.isEmpty();
   }

   public List getIntervals() {
      if (this.intervals == null) {
         this.intervals = new ArrayList();
      }

      return this.intervals;
   }

   public DataField addIntervals(Interval... intervals) {
      this.getIntervals().addAll(Arrays.asList(intervals));
      return this;
   }

   public boolean hasValues() {
      return this.values != null && !this.values.isEmpty();
   }

   public List getValues() {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      return this.values;
   }

   public DataField addValues(Value... values) {
      this.getValues().addAll(Arrays.asList(values));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasIntervals()) {
            status = PMMLObject.traverse(visitor, this.getIntervals());
         }

         if (status == VisitorAction.CONTINUE && this.hasValues()) {
            status = PMMLObject.traverse(visitor, this.getValues());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Cyclic implements StringValue {
      @XmlEnumValue("0")
      @JsonProperty("0")
      ZERO("0"),
      @XmlEnumValue("1")
      @JsonProperty("1")
      ONE("1");

      private final String value;

      private Cyclic(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Cyclic fromValue(String v) {
         for(Cyclic c : values()) {
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
