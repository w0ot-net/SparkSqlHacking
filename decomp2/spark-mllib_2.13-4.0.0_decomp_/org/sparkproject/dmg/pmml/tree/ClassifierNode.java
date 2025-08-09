package org.sparkproject.dmg.pmml.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
import java.util.List;
import org.sparkproject.dmg.pmml.ComplexScoreDistribution;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.ScoreDistribution;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CopyConstructor;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Node",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"predicate", "scoreDistributions", "nodes"}
)
@JsonRootName("Node")
@JsonPropertyOrder({"id", "score", "recordCount", "defaultChild", "predicate", "scoreDistributions", "nodes"})
public class ClassifierNode extends SimpleNode {
   @XmlAttribute(
      name = "id"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("id")
   private Object id = null;
   @XmlAttribute(
      name = "recordCount"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("recordCount")
   private Number recordCount = null;
   @XmlAttribute(
      name = "defaultChild"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @Added(Version.PMML_3_1)
   @JsonProperty("defaultChild")
   private Object defaultChild = null;
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
   private List scoreDistributions = null;
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
   private List nodes = null;

   public ClassifierNode() {
   }

   @ValueConstructor
   public ClassifierNode(@Property("score") Object score, @Property("predicate") Predicate predicate) {
      super(score, predicate);
   }

   @CopyConstructor
   public ClassifierNode(Node node) {
      super(node);
      this.setId(node.getId());
      this.setRecordCount(node.getRecordCount());
      this.setDefaultChild(node.getDefaultChild());
      if (node.hasScoreDistributions()) {
         this.getScoreDistributions().addAll(node.getScoreDistributions());
      }

      if (node.hasNodes()) {
         this.getNodes().addAll(node.getNodes());
      }

   }

   public Object getId() {
      return this.id;
   }

   public ClassifierNode setId(@Property("id") Object id) {
      this.id = id;
      return this;
   }

   public Number getRecordCount() {
      return this.recordCount;
   }

   public ClassifierNode setRecordCount(@Property("recordCount") Number recordCount) {
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

   public ClassifierNode setDefaultChild(@Property("defaultChild") Object defaultChild) {
      this.defaultChild = defaultChild;
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

   public boolean hasNodes() {
      return this.nodes != null && !this.nodes.isEmpty();
   }

   public List getNodes() {
      if (this.nodes == null) {
         this.nodes = new ArrayList();
      }

      return this.nodes;
   }
}
