package org.sparkproject.dmg.pmml.rule_set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.ComplexScoreDistribution;
import org.sparkproject.dmg.pmml.CompoundPredicate;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.False;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasRecordCount;
import org.sparkproject.dmg.pmml.HasScoreDistributions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.ScoreDistribution;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.SimpleSetPredicate;
import org.sparkproject.dmg.pmml.True;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SimpleRule",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "predicate", "scoreDistributions"}
)
@JsonRootName("SimpleRule")
@JsonPropertyOrder({"id", "score", "recordCount", "nbCorrect", "confidence", "weight", "extensions", "predicate", "scoreDistributions"})
public class SimpleRule extends Rule implements HasExtensions, HasRecordCount, HasScoreDistributions {
   @XmlAttribute(
      name = "id"
   )
   @JsonProperty("id")
   private String id;
   @XmlAttribute(
      name = "score",
      required = true
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("score")
   private Object score;
   @XmlAttribute(
      name = "recordCount"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("recordCount")
   private Number recordCount;
   @XmlAttribute(
      name = "nbCorrect"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("nbCorrect")
   private Number nbCorrect;
   @XmlAttribute(
      name = "confidence"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("confidence")
   private Number confidence;
   @XmlAttribute(
      name = "weight"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("weight")
   private Number weight;
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
   private Predicate predicate;
   @XmlElements({@XmlElement(
   name = "ScoreDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = ComplexScoreDistribution.class
)})
   @JsonProperty("ScoreDistribution")
   @JsonTypeInfo(
      use = Id.NONE,
      defaultImpl = ComplexScoreDistribution.class
   )
   @JsonDeserialize(
      contentAs = ComplexScoreDistribution.class
   )
   @CollectionElementType(ScoreDistribution.class)
   private List scoreDistributions;
   private static final Number DEFAULT_CONFIDENCE = (new NumberAdapter()).unmarshal("1");
   private static final Number DEFAULT_WEIGHT = (new NumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public SimpleRule() {
   }

   @ValueConstructor
   public SimpleRule(@Property("score") Object score, @Property("predicate") Predicate predicate) {
      this.score = score;
      this.predicate = predicate;
   }

   public String getId() {
      return this.id;
   }

   public SimpleRule setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public Object requireScore() {
      if (this.score == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SIMPLERULE_SCORE);
      } else {
         return this.score;
      }
   }

   public Object getScore() {
      return this.score;
   }

   public SimpleRule setScore(@Property("score") Object score) {
      this.score = score;
      return this;
   }

   public Number getRecordCount() {
      return this.recordCount;
   }

   public SimpleRule setRecordCount(@Property("recordCount") Number recordCount) {
      this.recordCount = recordCount;
      return this;
   }

   public Number getNbCorrect() {
      return this.nbCorrect;
   }

   public SimpleRule setNbCorrect(@Property("nbCorrect") Number nbCorrect) {
      this.nbCorrect = nbCorrect;
      return this;
   }

   public Number getConfidence() {
      return this.confidence == null ? DEFAULT_CONFIDENCE : this.confidence;
   }

   public SimpleRule setConfidence(@Property("confidence") Number confidence) {
      this.confidence = confidence;
      return this;
   }

   public Number getWeight() {
      return this.weight == null ? DEFAULT_WEIGHT : this.weight;
   }

   public SimpleRule setWeight(@Property("weight") Number weight) {
      this.weight = weight;
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

   public SimpleRule addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Predicate requirePredicate() {
      if (this.predicate == null) {
         throw new MissingElementException(this, PMMLElements.SIMPLERULE_PREDICATE);
      } else {
         return this.predicate;
      }
   }

   public Predicate getPredicate() {
      return this.predicate;
   }

   public SimpleRule setPredicate(@Property("predicate") Predicate predicate) {
      this.predicate = predicate;
      return this;
   }

   public boolean hasScoreDistributions() {
      return this.scoreDistributions != null && !this.scoreDistributions.isEmpty();
   }

   public List getScoreDistributions() {
      if (this.scoreDistributions == null) {
         this.scoreDistributions = new ArrayList();
      }

      return this.scoreDistributions;
   }

   public SimpleRule addScoreDistributions(ScoreDistribution... scoreDistributions) {
      this.getScoreDistributions().addAll(Arrays.asList(scoreDistributions));
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getPredicate());
         }

         if (status == VisitorAction.CONTINUE && this.hasScoreDistributions()) {
            status = PMMLObject.traverse(visitor, this.getScoreDistributions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
