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
import org.sparkproject.dmg.pmml.MathContext;
import org.sparkproject.dmg.pmml.MiningFunction;
import org.sparkproject.dmg.pmml.MiningSchema;
import org.sparkproject.dmg.pmml.Model;
import org.sparkproject.dmg.pmml.ModelExplanation;
import org.sparkproject.dmg.pmml.ModelStats;
import org.sparkproject.dmg.pmml.ModelVerification;
import org.sparkproject.dmg.pmml.Output;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Targets;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TreeModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "node", "modelVerification"}
)
@JsonRootName("TreeModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "missingValueStrategy", "missingValuePenalty", "noTrueChildStrategy", "splitCharacteristic", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "node", "modelVerification"})
public class TreeModel extends Model implements HasExtensions, HasNode {
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
   private MissingValueStrategy missingValueStrategy;
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
   private NoTrueChildStrategy noTrueChildStrategy;
   @XmlAttribute(
      name = "splitCharacteristic"
   )
   @JsonProperty("splitCharacteristic")
   private SplitCharacteristic splitCharacteristic;
   @XmlAttribute(
      name = "isScorable"
   )
   @JsonProperty("isScorable")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Boolean scorable;
   @XmlAttribute(
      name = "x-mathContext"
   )
   @JsonProperty("x-mathContext")
   @Added(
      value = Version.XPMML,
      removable = true
   )
   @Since("1.3.7")
   private MathContext mathContext;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "MiningSchema",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("MiningSchema")
   private MiningSchema miningSchema;
   @XmlElement(
      name = "Output",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Output")
   private Output output;
   @XmlElement(
      name = "ModelStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelStats")
   private ModelStats modelStats;
   @XmlElement(
      name = "ModelExplanation",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelExplanation")
   @Added(
      value = Version.PMML_4_0,
      removable = true
   )
   private ModelExplanation modelExplanation;
   @XmlElement(
      name = "Targets",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Targets")
   private Targets targets;
   @XmlElement(
      name = "LocalTransformations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LocalTransformations")
   private LocalTransformations localTransformations;
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
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Number DEFAULT_MISSING_VALUE_PENALTY = (new ProbabilityNumberAdapter()).unmarshal("1.0");
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public TreeModel() {
   }

   @ValueConstructor
   public TreeModel(@Property("miningFunction") MiningFunction miningFunction, @Property("miningSchema") MiningSchema miningSchema, @Property("node") Node node) {
      this.miningFunction = miningFunction;
      this.miningSchema = miningSchema;
      this.node = node;
   }

   public String getModelName() {
      return this.modelName;
   }

   public TreeModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TREEMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public TreeModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public TreeModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public MissingValueStrategy getMissingValueStrategy() {
      return this.missingValueStrategy == null ? TreeModel.MissingValueStrategy.NONE : this.missingValueStrategy;
   }

   public TreeModel setMissingValueStrategy(@Property("missingValueStrategy") MissingValueStrategy missingValueStrategy) {
      this.missingValueStrategy = missingValueStrategy;
      return this;
   }

   public Number getMissingValuePenalty() {
      return this.missingValuePenalty == null ? DEFAULT_MISSING_VALUE_PENALTY : this.missingValuePenalty;
   }

   public TreeModel setMissingValuePenalty(@Property("missingValuePenalty") Number missingValuePenalty) {
      this.missingValuePenalty = missingValuePenalty;
      return this;
   }

   public NoTrueChildStrategy getNoTrueChildStrategy() {
      return this.noTrueChildStrategy == null ? TreeModel.NoTrueChildStrategy.RETURN_NULL_PREDICTION : this.noTrueChildStrategy;
   }

   public TreeModel setNoTrueChildStrategy(@Property("noTrueChildStrategy") NoTrueChildStrategy noTrueChildStrategy) {
      this.noTrueChildStrategy = noTrueChildStrategy;
      return this;
   }

   public SplitCharacteristic getSplitCharacteristic() {
      return this.splitCharacteristic == null ? TreeModel.SplitCharacteristic.MULTI_SPLIT : this.splitCharacteristic;
   }

   public TreeModel setSplitCharacteristic(@Property("splitCharacteristic") SplitCharacteristic splitCharacteristic) {
      this.splitCharacteristic = splitCharacteristic;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public TreeModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public TreeModel setMathContext(@Property("mathContext") MathContext mathContext) {
      this.mathContext = mathContext;
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

   public TreeModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.TREEMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public TreeModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public TreeModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public TreeModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public TreeModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public TreeModel setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public TreeModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public Node requireNode() {
      if (this.node == null) {
         throw new MissingElementException(this, PMMLElements.TREEMODEL_NODE);
      } else {
         return this.node;
      }
   }

   public Node getNode() {
      return this.node;
   }

   public TreeModel setNode(@Property("node") Node node) {
      this.node = node;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public TreeModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
      this.modelVerification = modelVerification;
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations(), this.getNode(), this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum MissingValueStrategy implements StringValue {
      @XmlEnumValue("lastPrediction")
      @JsonProperty("lastPrediction")
      LAST_PREDICTION("lastPrediction"),
      @XmlEnumValue("nullPrediction")
      @JsonProperty("nullPrediction")
      NULL_PREDICTION("nullPrediction"),
      @XmlEnumValue("defaultChild")
      @JsonProperty("defaultChild")
      DEFAULT_CHILD("defaultChild"),
      @XmlEnumValue("weightedConfidence")
      @JsonProperty("weightedConfidence")
      WEIGHTED_CONFIDENCE("weightedConfidence"),
      @XmlEnumValue("aggregateNodes")
      @JsonProperty("aggregateNodes")
      @Added(Version.PMML_3_2)
      AGGREGATE_NODES("aggregateNodes"),
      @XmlEnumValue("none")
      @JsonProperty("none")
      NONE("none");

      private final String value;

      private MissingValueStrategy(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static MissingValueStrategy fromValue(String v) {
         for(MissingValueStrategy c : values()) {
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
   public static enum NoTrueChildStrategy implements StringValue {
      @XmlEnumValue("returnNullPrediction")
      @JsonProperty("returnNullPrediction")
      RETURN_NULL_PREDICTION("returnNullPrediction"),
      @XmlEnumValue("returnLastPrediction")
      @JsonProperty("returnLastPrediction")
      RETURN_LAST_PREDICTION("returnLastPrediction");

      private final String value;

      private NoTrueChildStrategy(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static NoTrueChildStrategy fromValue(String v) {
         for(NoTrueChildStrategy c : values()) {
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
   public static enum SplitCharacteristic implements StringValue {
      @XmlEnumValue("binarySplit")
      @JsonProperty("binarySplit")
      BINARY_SPLIT("binarySplit"),
      @XmlEnumValue("multiSplit")
      @JsonProperty("multiSplit")
      MULTI_SPLIT("multiSplit");

      private final String value;

      private SplitCharacteristic(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static SplitCharacteristic fromValue(String v) {
         for(SplitCharacteristic c : values()) {
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
