package org.sparkproject.dmg.pmml.clustering;

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
import org.sparkproject.dmg.pmml.CompareFunction;
import org.sparkproject.dmg.pmml.ComparisonField;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ClusteringField",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "comparisons"}
)
@JsonRootName("ClusteringField")
@JsonPropertyOrder({"field", "centerField", "fieldWeight", "similarityScale", "compareFunction", "extensions", "comparisons"})
public class ClusteringField extends ComparisonField implements HasExtensions {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "isCenterField"
   )
   @JsonProperty("isCenterField")
   @Added(Version.PMML_3_2)
   private CenterField centerField;
   @XmlAttribute(
      name = "fieldWeight"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("fieldWeight")
   private Number fieldWeight;
   @XmlAttribute(
      name = "similarityScale"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("similarityScale")
   private Number similarityScale;
   @XmlAttribute(
      name = "compareFunction"
   )
   @JsonProperty("compareFunction")
   private CompareFunction compareFunction;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Comparisons",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Comparisons")
   private Comparisons comparisons;
   private static final Number DEFAULT_FIELD_WEIGHT = (new RealNumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public ClusteringField() {
   }

   @ValueConstructor
   public ClusteringField(@Property("field") String field) {
      this.field = field;
   }

   @AlternateValueConstructor
   public ClusteringField(Field field) {
      this(field != null ? field.requireName() : null);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.CLUSTERINGFIELD_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public ClusteringField setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public CenterField getCenterField() {
      return this.centerField == null ? ClusteringField.CenterField.TRUE : this.centerField;
   }

   public ClusteringField setCenterField(@Property("centerField") CenterField centerField) {
      this.centerField = centerField;
      return this;
   }

   public Number getFieldWeight() {
      return this.fieldWeight == null ? DEFAULT_FIELD_WEIGHT : this.fieldWeight;
   }

   public ClusteringField setFieldWeight(@Property("fieldWeight") Number fieldWeight) {
      this.fieldWeight = fieldWeight;
      return this;
   }

   public Number getSimilarityScale() {
      return this.similarityScale;
   }

   public ClusteringField setSimilarityScale(@Property("similarityScale") Number similarityScale) {
      this.similarityScale = similarityScale;
      return this;
   }

   public CompareFunction getCompareFunction() {
      return this.compareFunction;
   }

   public ClusteringField setCompareFunction(@Property("compareFunction") CompareFunction compareFunction) {
      this.compareFunction = compareFunction;
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

   public ClusteringField addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Comparisons getComparisons() {
      return this.comparisons;
   }

   public ClusteringField setComparisons(@Property("comparisons") Comparisons comparisons) {
      this.comparisons = comparisons;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getComparisons());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum CenterField implements StringValue {
      @XmlEnumValue("true")
      @JsonProperty("true")
      TRUE("true"),
      @XmlEnumValue("false")
      @JsonProperty("false")
      FALSE("false");

      private final String value;

      private CenterField(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static CenterField fromValue(String v) {
         for(CenterField c : values()) {
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
