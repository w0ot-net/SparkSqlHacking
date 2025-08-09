package org.sparkproject.dmg.pmml.tree;

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
import org.sparkproject.dmg.pmml.EmbeddedModel;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.False;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Partition;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.ScoreDistribution;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.SimpleSetPredicate;
import org.sparkproject.dmg.pmml.True;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.regression.Regression;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CopyConstructor;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Node",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "predicate", "partition", "scoreDistributions", "nodes", "embeddedModel"}
)
@JsonRootName("Node")
@JsonPropertyOrder({"id", "score", "recordCount", "defaultChild", "extensions", "predicate", "partition", "scoreDistributions", "nodes", "embeddedModel"})
public class ComplexNode extends Node implements HasExtensions {
   @XmlAttribute(
      name = "id"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("id")
   private Object id;
   @XmlAttribute(
      name = "score"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("score")
   @Optional(Version.PMML_3_2)
   private Object score;
   @XmlAttribute(
      name = "recordCount"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("recordCount")
   private Number recordCount;
   @XmlAttribute(
      name = "defaultChild"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("defaultChild")
   @Added(Version.PMML_3_1)
   private Object defaultChild;
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
   @XmlElement(
      name = "Partition",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Partition")
   @Added(Version.PMML_3_2)
   private Partition partition;
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
   name = "Node",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = ComplexNode.class
)})
   @JsonProperty("Node")
   @JsonTypeInfo(
      use = Id.NONE,
      defaultImpl = ComplexNode.class
   )
   @JsonDeserialize(
      contentAs = ComplexNode.class
   )
   @CollectionElementType(Node.class)
   private List nodes;
   @XmlElements({@XmlElement(
   name = "Regression",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Regression.class
), @XmlElement(
   name = "DecisionTree",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = DecisionTree.class
)})
   @JsonProperty("EmbeddedModel")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "Regression",
   value = Regression.class
), @Type(
   name = "DecisionTree",
   value = DecisionTree.class
)})
   private EmbeddedModel embeddedModel;
   private static final long serialVersionUID = 67371272L;

   public ComplexNode() {
   }

   @CopyConstructor
   public ComplexNode(Node node) {
      this.setId(node.getId());
      this.setScore(node.getScore());
      this.setRecordCount(node.getRecordCount());
      this.setDefaultChild(node.getDefaultChild());
      if (node.hasExtensions()) {
         this.getExtensions().addAll(node.getExtensions());
      }

      this.setPredicate(node.getPredicate());
      this.setPartition(node.getPartition());
      if (node.hasScoreDistributions()) {
         this.getScoreDistributions().addAll(node.getScoreDistributions());
      }

      if (node.hasNodes()) {
         this.getNodes().addAll(node.getNodes());
      }

      this.setEmbeddedModel(node.getEmbeddedModel());
   }

   @ValueConstructor
   public ComplexNode(@Property("score") Object score, @Property("predicate") Predicate predicate) {
      this.score = score;
      this.predicate = predicate;
   }

   public Object getId() {
      return this.id;
   }

   public ComplexNode setId(@Property("id") Object id) {
      this.id = id;
      return this;
   }

   public Object requireScore() {
      if (this.score == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPLEXNODE_SCORE);
      } else {
         return this.score;
      }
   }

   public Object getScore() {
      return this.score;
   }

   public ComplexNode setScore(@Property("score") Object score) {
      this.score = score;
      return this;
   }

   public Number getRecordCount() {
      return this.recordCount;
   }

   public ComplexNode setRecordCount(@Property("recordCount") Number recordCount) {
      this.recordCount = recordCount;
      return this;
   }

   public Object requireDefaultChild() {
      if (this.defaultChild == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPLEXNODE_DEFAULTCHILD);
      } else {
         return this.defaultChild;
      }
   }

   public Object getDefaultChild() {
      return this.defaultChild;
   }

   public ComplexNode setDefaultChild(@Property("defaultChild") Object defaultChild) {
      this.defaultChild = defaultChild;
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

   public ComplexNode addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Predicate requirePredicate() {
      if (this.predicate == null) {
         throw new MissingElementException(this, PMMLElements.COMPLEXNODE_PREDICATE);
      } else {
         return this.predicate;
      }
   }

   public Predicate getPredicate() {
      return this.predicate;
   }

   public ComplexNode setPredicate(@Property("predicate") Predicate predicate) {
      this.predicate = predicate;
      return this;
   }

   public Partition getPartition() {
      return this.partition;
   }

   public ComplexNode setPartition(@Property("partition") Partition partition) {
      this.partition = partition;
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

   public ComplexNode addScoreDistributions(ScoreDistribution... scoreDistributions) {
      this.getScoreDistributions().addAll(Arrays.asList(scoreDistributions));
      return this;
   }

   public boolean hasNodes() {
      return this.nodes != null && !this.nodes.isEmpty();
   }

   public List getNodes() {
      if (this.nodes == null) {
         this.nodes = new ArrayList();
      }

      return this.nodes;
   }

   public ComplexNode addNodes(Node... nodes) {
      this.getNodes().addAll(Arrays.asList(nodes));
      return this;
   }

   public EmbeddedModel getEmbeddedModel() {
      return this.embeddedModel;
   }

   public ComplexNode setEmbeddedModel(@Property("embeddedModel") EmbeddedModel embeddedModel) {
      this.embeddedModel = embeddedModel;
      return this;
   }

   public ComplexNode toComplexNode() {
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
            status = PMMLObject.traverse(visitor, this.getPredicate(), this.getPartition());
         }

         if (status == VisitorAction.CONTINUE && this.hasScoreDistributions()) {
            status = PMMLObject.traverse(visitor, this.getScoreDistributions());
         }

         if (status == VisitorAction.CONTINUE && this.hasNodes()) {
            status = PMMLObject.traverse(visitor, this.getNodes());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getEmbeddedModel());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
