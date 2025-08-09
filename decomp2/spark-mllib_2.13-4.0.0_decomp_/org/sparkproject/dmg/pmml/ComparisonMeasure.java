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
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ComparisonMeasure",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "measure"}
)
@JsonRootName("ComparisonMeasure")
@JsonPropertyOrder({"kind", "compareFunction", "minimum", "maximum", "extensions", "measure"})
public class ComparisonMeasure extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "kind",
      required = true
   )
   @JsonProperty("kind")
   private Kind kind;
   @XmlAttribute(
      name = "compareFunction"
   )
   @JsonProperty("compareFunction")
   private CompareFunction compareFunction;
   @XmlAttribute(
      name = "minimum"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("minimum")
   private Number minimum;
   @XmlAttribute(
      name = "maximum"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("maximum")
   private Number maximum;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElements({@XmlElement(
   name = "euclidean",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Euclidean.class
), @XmlElement(
   name = "squaredEuclidean",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SquaredEuclidean.class
), @XmlElement(
   name = "chebychev",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Chebychev.class
), @XmlElement(
   name = "cityBlock",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = CityBlock.class
), @XmlElement(
   name = "minkowski",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Minkowski.class
), @XmlElement(
   name = "simpleMatching",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimpleMatching.class
), @XmlElement(
   name = "jaccard",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Jaccard.class
), @XmlElement(
   name = "tanimoto",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Tanimoto.class
), @XmlElement(
   name = "binarySimilarity",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = BinarySimilarity.class
)})
   @JsonProperty("Measure")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "Euclidean",
   value = Euclidean.class
), @Type(
   name = "SquaredEuclidean",
   value = SquaredEuclidean.class
), @Type(
   name = "Chebychev",
   value = Chebychev.class
), @Type(
   name = "CityBlock",
   value = CityBlock.class
), @Type(
   name = "Minkowski",
   value = Minkowski.class
), @Type(
   name = "SimpleMatching",
   value = SimpleMatching.class
), @Type(
   name = "Jaccard",
   value = Jaccard.class
), @Type(
   name = "Tanimoto",
   value = Tanimoto.class
), @Type(
   name = "BinarySimilarity",
   value = BinarySimilarity.class
)})
   private Measure measure;
   private static final long serialVersionUID = 67371272L;

   public ComparisonMeasure() {
   }

   @ValueConstructor
   public ComparisonMeasure(@Property("kind") Kind kind, @Property("measure") Measure measure) {
      this.kind = kind;
      this.measure = measure;
   }

   public Kind requireKind() {
      if (this.kind == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPARISONMEASURE_KIND);
      } else {
         return this.kind;
      }
   }

   public Kind getKind() {
      return this.kind;
   }

   public ComparisonMeasure setKind(@Property("kind") Kind kind) {
      this.kind = kind;
      return this;
   }

   public CompareFunction getCompareFunction() {
      return this.compareFunction == null ? CompareFunction.ABS_DIFF : this.compareFunction;
   }

   public ComparisonMeasure setCompareFunction(@Property("compareFunction") CompareFunction compareFunction) {
      this.compareFunction = compareFunction;
      return this;
   }

   public Number getMinimum() {
      return this.minimum;
   }

   public ComparisonMeasure setMinimum(@Property("minimum") Number minimum) {
      this.minimum = minimum;
      return this;
   }

   public Number getMaximum() {
      return this.maximum;
   }

   public ComparisonMeasure setMaximum(@Property("maximum") Number maximum) {
      this.maximum = maximum;
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

   public ComparisonMeasure addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Measure requireMeasure() {
      if (this.measure == null) {
         throw new MissingElementException(this, PMMLElements.COMPARISONMEASURE_MEASURE);
      } else {
         return this.measure;
      }
   }

   public Measure getMeasure() {
      return this.measure;
   }

   public ComparisonMeasure setMeasure(@Property("measure") Measure measure) {
      this.measure = measure;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getMeasure());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Kind implements StringValue {
      @XmlEnumValue("distance")
      @JsonProperty("distance")
      DISTANCE("distance"),
      @XmlEnumValue("similarity")
      @JsonProperty("similarity")
      SIMILARITY("similarity");

      private final String value;

      private Kind(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Kind fromValue(String v) {
         for(Kind c : values()) {
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
