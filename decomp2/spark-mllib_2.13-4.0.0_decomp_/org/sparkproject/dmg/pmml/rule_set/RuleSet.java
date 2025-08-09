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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasRecordCount;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.ScoreDistribution;
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
   name = "RuleSet",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "ruleSelectionMethods", "scoreDistributions", "rules"}
)
@JsonRootName("RuleSet")
@JsonPropertyOrder({"recordCount", "nbCorrect", "defaultScore", "defaultConfidence", "extensions", "ruleSelectionMethods", "scoreDistributions", "rules"})
public class RuleSet extends PMMLObject implements HasExtensions, HasRecordCount {
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
      name = "defaultScore"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("defaultScore")
   private Object defaultScore;
   @XmlAttribute(
      name = "defaultConfidence"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("defaultConfidence")
   private Number defaultConfidence;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "RuleSelectionMethod",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("RuleSelectionMethod")
   @CollectionElementType(RuleSelectionMethod.class)
   private List ruleSelectionMethods;
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
   @XmlElements({@XmlElement(
   name = "SimpleRule",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimpleRule.class
), @XmlElement(
   name = "CompoundRule",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = CompoundRule.class
)})
   @JsonProperty("Rule")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "SimpleRule",
   value = SimpleRule.class
), @Type(
   name = "CompoundRule",
   value = CompoundRule.class
)})
   @CollectionElementType(Rule.class)
   private List rules;
   private static final long serialVersionUID = 67371272L;

   public RuleSet() {
   }

   @ValueConstructor
   public RuleSet(@Property("ruleSelectionMethods") List ruleSelectionMethods) {
      this.ruleSelectionMethods = ruleSelectionMethods;
   }

   public Number getRecordCount() {
      return this.recordCount;
   }

   public RuleSet setRecordCount(@Property("recordCount") Number recordCount) {
      this.recordCount = recordCount;
      return this;
   }

   public Number getNbCorrect() {
      return this.nbCorrect;
   }

   public RuleSet setNbCorrect(@Property("nbCorrect") Number nbCorrect) {
      this.nbCorrect = nbCorrect;
      return this;
   }

   public Object requireDefaultScore() {
      if (this.defaultScore == null) {
         throw new MissingAttributeException(this, PMMLAttributes.RULESET_DEFAULTSCORE);
      } else {
         return this.defaultScore;
      }
   }

   public Object getDefaultScore() {
      return this.defaultScore;
   }

   public RuleSet setDefaultScore(@Property("defaultScore") Object defaultScore) {
      this.defaultScore = defaultScore;
      return this;
   }

   public Number requireDefaultConfidence() {
      if (this.defaultConfidence == null) {
         throw new MissingAttributeException(this, PMMLAttributes.RULESET_DEFAULTCONFIDENCE);
      } else {
         return this.defaultConfidence;
      }
   }

   public Number getDefaultConfidence() {
      return this.defaultConfidence;
   }

   public RuleSet setDefaultConfidence(@Property("defaultConfidence") Number defaultConfidence) {
      this.defaultConfidence = defaultConfidence;
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

   public RuleSet addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasRuleSelectionMethods() {
      return this.ruleSelectionMethods != null && !this.ruleSelectionMethods.isEmpty();
   }

   public List requireRuleSelectionMethods() {
      if (this.ruleSelectionMethods != null && !this.ruleSelectionMethods.isEmpty()) {
         return this.ruleSelectionMethods;
      } else {
         throw new MissingElementException(this, PMMLElements.RULESET_RULESELECTIONMETHODS);
      }
   }

   public List getRuleSelectionMethods() {
      if (this.ruleSelectionMethods == null) {
         this.ruleSelectionMethods = new ArrayList();
      }

      return this.ruleSelectionMethods;
   }

   public RuleSet addRuleSelectionMethods(RuleSelectionMethod... ruleSelectionMethods) {
      this.getRuleSelectionMethods().addAll(Arrays.asList(ruleSelectionMethods));
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

   public RuleSet addScoreDistributions(ScoreDistribution... scoreDistributions) {
      this.getScoreDistributions().addAll(Arrays.asList(scoreDistributions));
      return this;
   }

   public boolean hasRules() {
      return this.rules != null && !this.rules.isEmpty();
   }

   public List getRules() {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      return this.rules;
   }

   public RuleSet addRules(Rule... rules) {
      this.getRules().addAll(Arrays.asList(rules));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasRuleSelectionMethods()) {
            status = PMMLObject.traverse(visitor, this.getRuleSelectionMethods());
         }

         if (status == VisitorAction.CONTINUE && this.hasScoreDistributions()) {
            status = PMMLObject.traverse(visitor, this.getScoreDistributions());
         }

         if (status == VisitorAction.CONTINUE && this.hasRules()) {
            status = PMMLObject.traverse(visitor, this.getRules());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
