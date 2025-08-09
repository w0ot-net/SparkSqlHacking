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
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "PartitionFieldStats",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "counts", "numericInfo", "arrays"}
)
@JsonRootName("PartitionFieldStats")
@JsonPropertyOrder({"field", "weighted", "extensions", "counts", "numericInfo", "arrays"})
public class PartitionFieldStats extends PMMLObject implements HasArrays, HasExtensions, HasFieldReference {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "weighted"
   )
   @JsonProperty("weighted")
   @Added(Version.PMML_4_0)
   private Weighted weighted;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Counts",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Counts")
   private Counts counts;
   @XmlElement(
      name = "NumericInfo",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("NumericInfo")
   private NumericInfo numericInfo;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   @CollectionElementType(Array.class)
   private List arrays;
   private static final long serialVersionUID = 67371272L;

   public PartitionFieldStats() {
   }

   @ValueConstructor
   public PartitionFieldStats(@Property("field") String field) {
      this.field = field;
   }

   @AlternateValueConstructor
   public PartitionFieldStats(Field field) {
      this(field != null ? field.requireName() : null);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PARTITIONFIELDSTATS_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public PartitionFieldStats setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Weighted getWeighted() {
      return this.weighted == null ? PartitionFieldStats.Weighted.ZERO : this.weighted;
   }

   public PartitionFieldStats setWeighted(@Property("weighted") Weighted weighted) {
      this.weighted = weighted;
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

   public PartitionFieldStats addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Counts getCounts() {
      return this.counts;
   }

   public PartitionFieldStats setCounts(@Property("counts") Counts counts) {
      this.counts = counts;
      return this;
   }

   public NumericInfo getNumericInfo() {
      return this.numericInfo;
   }

   public PartitionFieldStats setNumericInfo(@Property("numericInfo") NumericInfo numericInfo) {
      this.numericInfo = numericInfo;
      return this;
   }

   public boolean hasArrays() {
      return this.arrays != null && !this.arrays.isEmpty();
   }

   public List getArrays() {
      if (this.arrays == null) {
         this.arrays = new ArrayList();
      }

      return this.arrays;
   }

   public PartitionFieldStats addArrays(Array... arrays) {
      this.getArrays().addAll(Arrays.asList(arrays));
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
            status = PMMLObject.traverse(visitor, this.getCounts(), this.getNumericInfo());
         }

         if (status == VisitorAction.CONTINUE && this.hasArrays()) {
            status = PMMLObject.traverse(visitor, this.getArrays());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Weighted implements StringValue {
      @XmlEnumValue("0")
      @JsonProperty("0")
      ZERO("0"),
      @XmlEnumValue("1")
      @JsonProperty("1")
      ONE("1");

      private final String value;

      private Weighted(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Weighted fromValue(String v) {
         for(Weighted c : values()) {
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
