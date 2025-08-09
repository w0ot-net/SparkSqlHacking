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
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.EmbeddedModel;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.LocalTransformations;
import org.sparkproject.dmg.pmml.MiningFunction;
import org.sparkproject.dmg.pmml.ModelStats;
import org.sparkproject.dmg.pmml.Output;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.ResultField;
import org.sparkproject.dmg.pmml.Targets;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Deprecated;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "DecisionTree",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "output", "modelStats", "targets", "localTransformations", "resultFields", "node"}
)
@JsonRootName("DecisionTree")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "missingValueStrategy", "missingValuePenalty", "noTrueChildStrategy", "splitCharacteristic", "extensions", "output", "modelStats", "targets", "localTransformations", "resultFields", "node"})
@Deprecated(Version.PMML_4_1)
public class DecisionTree extends EmbeddedModel implements HasExtensions, HasNode {
   @XmlAttribute(
      name = "modelName"
   )
   @JsonProperty("modelName")
   private String modelName;
   @XmlAttribute(
      name = "functionName",
      required = true
   )
   @JsonProperty("functionName")
   private MiningFunction miningFunction;
   @XmlAttribute(
      name = "algorithmName"
   )
   @JsonProperty("algorithmName")
   private String algorithmName;
   @XmlAttribute(
      name = "missingValueStrategy"
   )
   @JsonProperty("missingValueStrategy")
   @Added(Version.PMML_3_1)
   private TreeModel.MissingValueStrategy missingValueStrategy;
   @XmlAttribute(
      name = "missingValuePenalty"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("missingValuePenalty")
   @Added(Version.PMML_3_1)
   private Number missingValuePenalty;
   @XmlAttribute(
      name = "noTrueChildStrategy"
   )
   @JsonProperty("noTrueChildStrategy")
   @Added(Version.PMML_3_1)
   private TreeModel.NoTrueChildStrategy noTrueChildStrategy;
   @XmlAttribute(
      name = "splitCharacteristic"
   )
   @JsonProperty("splitCharacteristic")
   private TreeModel.SplitCharacteristic splitCharacteristic;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Output",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Output")
   @Added(Version.PMML_4_0)
   private Output output;
   @XmlElement(
      name = "ModelStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelStats")
   @Added(
      value = Version.PMML_4_0,
      removable = true
   )
   private ModelStats modelStats;
   @XmlElement(
      name = "Targets",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Targets")
   @Added(Version.PMML_4_0)
   private Targets targets;
   @XmlElement(
      name = "LocalTransformations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LocalTransformations")
   private LocalTransformations localTransformations;
   @XmlElement(
      name = "ResultField",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ResultField")
   @CollectionElementType(ResultField.class)
   private List resultFields;
   @XmlElements({@XmlElement(
   name = "Node",
   namespace = "http://www.dmg.org/PMML-4_4",
   required = true,
   type = ComplexNode.class
)})
   @JsonProperty("Node")
   @JsonTypeInfo(
      use = Id.NONE,
      defaultImpl = ComplexNode.class
   )
   @JsonDeserialize(
      as = ComplexNode.class
   )
   private Node node;
   private static final Number DEFAULT_MISSING_VALUE_PENALTY = (new ProbabilityNumberAdapter()).unmarshal("1.0");
   private static final long serialVersionUID = 67371272L;

   public DecisionTree() {
   }

   @ValueConstructor
   public DecisionTree(@Property("miningFunction") MiningFunction miningFunction, @Property("node") Node node) {
      this.miningFunction = miningFunction;
      this.node = node;
   }

   public String getModelName() {
      return this.modelName;
   }

   public DecisionTree setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DECISIONTREE_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public DecisionTree setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public DecisionTree setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public TreeModel.MissingValueStrategy getMissingValueStrategy() {
      return this.missingValueStrategy == null ? TreeModel.MissingValueStrategy.NONE : this.missingValueStrategy;
   }

   public DecisionTree setMissingValueStrategy(@Property("missingValueStrategy") TreeModel.MissingValueStrategy missingValueStrategy) {
      this.missingValueStrategy = missingValueStrategy;
      return this;
   }

   public Number getMissingValuePenalty() {
      return this.missingValuePenalty == null ? DEFAULT_MISSING_VALUE_PENALTY : this.missingValuePenalty;
   }

   public DecisionTree setMissingValuePenalty(@Property("missingValuePenalty") Number missingValuePenalty) {
      this.missingValuePenalty = missingValuePenalty;
      return this;
   }

   public TreeModel.NoTrueChildStrategy getNoTrueChildStrategy() {
      return this.noTrueChildStrategy == null ? TreeModel.NoTrueChildStrategy.RETURN_NULL_PREDICTION : this.noTrueChildStrategy;
   }

   public DecisionTree setNoTrueChildStrategy(@Property("noTrueChildStrategy") TreeModel.NoTrueChildStrategy noTrueChildStrategy) {
      this.noTrueChildStrategy = noTrueChildStrategy;
      return this;
   }

   public TreeModel.SplitCharacteristic getSplitCharacteristic() {
      return this.splitCharacteristic == null ? TreeModel.SplitCharacteristic.MULTI_SPLIT : this.splitCharacteristic;
   }

   public DecisionTree setSplitCharacteristic(@Property("splitCharacteristic") TreeModel.SplitCharacteristic splitCharacteristic) {
      this.splitCharacteristic = splitCharacteristic;
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

   public DecisionTree addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public DecisionTree setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public DecisionTree setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public DecisionTree setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public DecisionTree setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public boolean hasResultFields() {
      return this.resultFields != null && !this.resultFields.isEmpty();
   }

   public List getResultFields() {
      if (this.resultFields == null) {
         this.resultFields = new ArrayList();
      }

      return this.resultFields;
   }

   public DecisionTree addResultFields(ResultField... resultFields) {
      this.getResultFields().addAll(Arrays.asList(resultFields));
      return this;
   }

   public Node requireNode() {
      if (this.node == null) {
         throw new MissingElementException(this, PMMLElements.DECISIONTREE_NODE);
      } else {
         return this.node;
      }
   }

   public Node getNode() {
      return this.node;
   }

   public DecisionTree setNode(@Property("node") Node node) {
      this.node = node;
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
            status = PMMLObject.traverse(visitor, this.getOutput(), this.getModelStats(), this.getTargets(), this.getLocalTransformations());
         }

         if (status == VisitorAction.CONTINUE && this.hasResultFields()) {
            status = PMMLObject.traverse(visitor, this.getResultFields());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getNode());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
