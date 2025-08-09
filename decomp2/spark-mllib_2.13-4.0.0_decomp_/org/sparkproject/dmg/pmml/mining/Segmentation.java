package org.sparkproject.dmg.pmml.mining;

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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.LocalTransformations;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Segmentation",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "localTransformations", "segments"}
)
@JsonRootName("Segmentation")
@JsonPropertyOrder({"multipleModelMethod", "missingPredictionTreatment", "missingThreshold", "extensions", "localTransformations", "segments"})
@Added(Version.PMML_4_0)
public class Segmentation extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "multipleModelMethod",
      required = true
   )
   @JsonProperty("multipleModelMethod")
   private MultipleModelMethod multipleModelMethod;
   @XmlAttribute(
      name = "missingPredictionTreatment"
   )
   @JsonProperty("missingPredictionTreatment")
   @Added(Version.PMML_4_4)
   @Since("1.4.3")
   private MissingPredictionTreatment missingPredictionTreatment;
   @XmlAttribute(
      name = "missingThreshold"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("missingThreshold")
   @Added(Version.PMML_4_4)
   @Since("1.4.3")
   private Number missingThreshold;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "LocalTransformations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LocalTransformations")
   @Removed(Version.PMML_4_1)
   private LocalTransformations localTransformations;
   @XmlElement(
      name = "Segment",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Segment")
   @CollectionElementType(Segment.class)
   private List segments;
   private static final Number DEFAULT_MISSING_THRESHOLD = (new ProbabilityNumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public Segmentation() {
   }

   @ValueConstructor
   public Segmentation(@Property("multipleModelMethod") MultipleModelMethod multipleModelMethod, @Property("segments") List segments) {
      this.multipleModelMethod = multipleModelMethod;
      this.segments = segments;
   }

   public MultipleModelMethod requireMultipleModelMethod() {
      if (this.multipleModelMethod == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEGMENTATION_MULTIPLEMODELMETHOD);
      } else {
         return this.multipleModelMethod;
      }
   }

   public MultipleModelMethod getMultipleModelMethod() {
      return this.multipleModelMethod;
   }

   public Segmentation setMultipleModelMethod(@Property("multipleModelMethod") MultipleModelMethod multipleModelMethod) {
      this.multipleModelMethod = multipleModelMethod;
      return this;
   }

   public MissingPredictionTreatment getMissingPredictionTreatment() {
      return this.missingPredictionTreatment == null ? Segmentation.MissingPredictionTreatment.CONTINUE : this.missingPredictionTreatment;
   }

   public Segmentation setMissingPredictionTreatment(@Property("missingPredictionTreatment") MissingPredictionTreatment missingPredictionTreatment) {
      this.missingPredictionTreatment = missingPredictionTreatment;
      return this;
   }

   public Number getMissingThreshold() {
      return this.missingThreshold == null ? DEFAULT_MISSING_THRESHOLD : this.missingThreshold;
   }

   public Segmentation setMissingThreshold(@Property("missingThreshold") Number missingThreshold) {
      this.missingThreshold = missingThreshold;
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

   public Segmentation addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public Segmentation setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public boolean hasSegments() {
      return this.segments != null && !this.segments.isEmpty();
   }

   public List requireSegments() {
      if (this.segments != null && !this.segments.isEmpty()) {
         return this.segments;
      } else {
         throw new MissingElementException(this, PMMLElements.SEGMENTATION_SEGMENTS);
      }
   }

   public List getSegments() {
      if (this.segments == null) {
         this.segments = new ArrayList();
      }

      return this.segments;
   }

   public Segmentation addSegments(Segment... segments) {
      this.getSegments().addAll(Arrays.asList(segments));
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getLocalTransformations());
         }

         if (status == VisitorAction.CONTINUE && this.hasSegments()) {
            status = PMMLObject.traverse(visitor, this.getSegments());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   @Added(Version.PMML_4_4)
   public static enum MissingPredictionTreatment implements StringValue {
      @XmlEnumValue("returnMissing")
      @JsonProperty("returnMissing")
      RETURN_MISSING("returnMissing"),
      @XmlEnumValue("skipSegment")
      @JsonProperty("skipSegment")
      SKIP_SEGMENT("skipSegment"),
      @XmlEnumValue("continue")
      @JsonProperty("continue")
      CONTINUE("continue");

      private final String value;

      private MissingPredictionTreatment(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static MissingPredictionTreatment fromValue(String v) {
         for(MissingPredictionTreatment c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum MultipleModelMethod implements StringValue {
      @XmlEnumValue("majorityVote")
      @JsonProperty("majorityVote")
      MAJORITY_VOTE("majorityVote"),
      @XmlEnumValue("weightedMajorityVote")
      @JsonProperty("weightedMajorityVote")
      WEIGHTED_MAJORITY_VOTE("weightedMajorityVote"),
      @XmlEnumValue("average")
      @JsonProperty("average")
      AVERAGE("average"),
      @XmlEnumValue("weightedAverage")
      @JsonProperty("weightedAverage")
      WEIGHTED_AVERAGE("weightedAverage"),
      @XmlEnumValue("median")
      @JsonProperty("median")
      MEDIAN("median"),
      @XmlEnumValue("weightedMedian")
      @JsonProperty("weightedMedian")
      @Added(Version.PMML_4_4)
      @Since("1.3.3")
      WEIGHTED_MEDIAN("weightedMedian"),
      @XmlEnumValue("max")
      @JsonProperty("max")
      MAX("max"),
      @XmlEnumValue("sum")
      @JsonProperty("sum")
      SUM("sum"),
      @XmlEnumValue("weightedSum")
      @JsonProperty("weightedSum")
      @Added(Version.PMML_4_4)
      @Since("1.3.3")
      WEIGHTED_SUM("weightedSum"),
      @XmlEnumValue("selectFirst")
      @JsonProperty("selectFirst")
      SELECT_FIRST("selectFirst"),
      @XmlEnumValue("selectAll")
      @JsonProperty("selectAll")
      SELECT_ALL("selectAll"),
      @XmlEnumValue("modelChain")
      @JsonProperty("modelChain")
      @Added(Version.PMML_4_1)
      MODEL_CHAIN("modelChain"),
      @XmlEnumValue("x-multiModelChain")
      @JsonProperty("x-multiModelChain")
      @Added(Version.XPMML)
      @Since("1.6.4")
      MULTI_MODEL_CHAIN("x-multiModelChain");

      private final String value;

      private MultipleModelMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static MultipleModelMethod fromValue(String v) {
         for(MultipleModelMethod c : values()) {
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
