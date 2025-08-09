package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CopyConstructor;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ScoreDistribution",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("ScoreDistribution")
@JsonPropertyOrder({"value", "recordCount", "confidence", "probability", "extensions"})
public class ComplexScoreDistribution extends ScoreDistribution implements HasExtensions {
   @XmlAttribute(
      name = "value",
      required = true
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("value")
   private Object value;
   @XmlAttribute(
      name = "recordCount"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("recordCount")
   @Optional(Version.XPMML)
   private Number recordCount;
   @XmlAttribute(
      name = "confidence"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("confidence")
   @Added(Version.PMML_3_1)
   private Number confidence;
   @XmlAttribute(
      name = "probability"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("probability")
   @Added(Version.PMML_4_1)
   private Number probability;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public ComplexScoreDistribution() {
   }

   @CopyConstructor
   public ComplexScoreDistribution(ScoreDistribution scoreDistribution) {
      this.setValue(scoreDistribution.getValue());
      this.setRecordCount(scoreDistribution.getRecordCount());
      this.setConfidence(scoreDistribution.getConfidence());
      this.setProbability(scoreDistribution.getProbability());
      if (scoreDistribution.hasExtensions()) {
         this.getExtensions().addAll(scoreDistribution.getExtensions());
      }

   }

   @ValueConstructor
   public ComplexScoreDistribution(@Property("value") Object value, @Property("recordCount") Number recordCount) {
      this.value = value;
      this.recordCount = recordCount;
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPLEXSCOREDISTRIBUTION_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public ComplexScoreDistribution setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public Number requireRecordCount() {
      if (this.recordCount == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPLEXSCOREDISTRIBUTION_RECORDCOUNT);
      } else {
         return this.recordCount;
      }
   }

   public Number getRecordCount() {
      return this.recordCount;
   }

   public ComplexScoreDistribution setRecordCount(@Property("recordCount") Number recordCount) {
      this.recordCount = recordCount;
      return this;
   }

   public Number getConfidence() {
      return this.confidence;
   }

   public ComplexScoreDistribution setConfidence(@Property("confidence") Number confidence) {
      this.confidence = confidence;
      return this;
   }

   public Number requireProbability() {
      if (this.probability == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPLEXSCOREDISTRIBUTION_PROBABILITY);
      } else {
         return this.probability;
      }
   }

   public Number getProbability() {
      return this.probability;
   }

   public ComplexScoreDistribution setProbability(@Property("probability") Number probability) {
      this.probability = probability;
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

   public ComplexScoreDistribution addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public ComplexScoreDistribution toComplexScoreDistribution() {
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
}
