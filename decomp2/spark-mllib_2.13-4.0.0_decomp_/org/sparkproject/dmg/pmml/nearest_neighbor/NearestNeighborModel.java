package org.sparkproject.dmg.pmml.nearest_neighbor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.ComparisonMeasure;
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
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "NearestNeighborModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "trainingInstances", "comparisonMeasure", "knnInputs", "modelVerification"}
)
@JsonRootName("NearestNeighborModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "numberOfNeighbors", "continuousScoringMethod", "categoricalScoringMethod", "instanceIdVariable", "threshold", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "trainingInstances", "comparisonMeasure", "knnInputs", "modelVerification"})
@Added(Version.PMML_4_1)
public class NearestNeighborModel extends Model implements HasExtensions {
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
      name = "numberOfNeighbors",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfNeighbors")
   private Integer numberOfNeighbors;
   @XmlAttribute(
      name = "continuousScoringMethod"
   )
   @JsonProperty("continuousScoringMethod")
   private ContinuousScoringMethod continuousScoringMethod;
   @XmlAttribute(
      name = "categoricalScoringMethod"
   )
   @JsonProperty("categoricalScoringMethod")
   private CategoricalScoringMethod categoricalScoringMethod;
   @XmlAttribute(
      name = "instanceIdVariable"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("instanceIdVariable")
   private String instanceIdVariable;
   @XmlAttribute(
      name = "threshold"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("threshold")
   private Number threshold;
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
   @XmlElement(
      name = "TrainingInstances",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("TrainingInstances")
   private TrainingInstances trainingInstances;
   @XmlElement(
      name = "ComparisonMeasure",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ComparisonMeasure")
   private ComparisonMeasure comparisonMeasure;
   @XmlElement(
      name = "KNNInputs",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("KNNInputs")
   private KNNInputs knnInputs;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Number DEFAULT_THRESHOLD = (new RealNumberAdapter()).unmarshal("0.001");
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public NearestNeighborModel() {
   }

   @ValueConstructor
   public NearestNeighborModel(@Property("miningFunction") MiningFunction miningFunction, @Property("numberOfNeighbors") Integer numberOfNeighbors, @Property("miningSchema") MiningSchema miningSchema, @Property("trainingInstances") TrainingInstances trainingInstances, @Property("comparisonMeasure") ComparisonMeasure comparisonMeasure, @Property("knnInputs") KNNInputs knnInputs) {
      this.miningFunction = miningFunction;
      this.numberOfNeighbors = numberOfNeighbors;
      this.miningSchema = miningSchema;
      this.trainingInstances = trainingInstances;
      this.comparisonMeasure = comparisonMeasure;
      this.knnInputs = knnInputs;
   }

   public String getModelName() {
      return this.modelName;
   }

   public NearestNeighborModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NEARESTNEIGHBORMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public NearestNeighborModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public NearestNeighborModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public Integer requireNumberOfNeighbors() {
      if (this.numberOfNeighbors == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NEARESTNEIGHBORMODEL_NUMBEROFNEIGHBORS);
      } else {
         return this.numberOfNeighbors;
      }
   }

   public Integer getNumberOfNeighbors() {
      return this.numberOfNeighbors;
   }

   public NearestNeighborModel setNumberOfNeighbors(@Property("numberOfNeighbors") Integer numberOfNeighbors) {
      this.numberOfNeighbors = numberOfNeighbors;
      return this;
   }

   public ContinuousScoringMethod getContinuousScoringMethod() {
      return this.continuousScoringMethod == null ? NearestNeighborModel.ContinuousScoringMethod.AVERAGE : this.continuousScoringMethod;
   }

   public NearestNeighborModel setContinuousScoringMethod(@Property("continuousScoringMethod") ContinuousScoringMethod continuousScoringMethod) {
      this.continuousScoringMethod = continuousScoringMethod;
      return this;
   }

   public CategoricalScoringMethod getCategoricalScoringMethod() {
      return this.categoricalScoringMethod == null ? NearestNeighborModel.CategoricalScoringMethod.MAJORITY_VOTE : this.categoricalScoringMethod;
   }

   public NearestNeighborModel setCategoricalScoringMethod(@Property("categoricalScoringMethod") CategoricalScoringMethod categoricalScoringMethod) {
      this.categoricalScoringMethod = categoricalScoringMethod;
      return this;
   }

   public String getInstanceIdVariable() {
      return this.instanceIdVariable;
   }

   public NearestNeighborModel setInstanceIdVariable(@Property("instanceIdVariable") String instanceIdVariable) {
      this.instanceIdVariable = instanceIdVariable;
      return this;
   }

   public Number getThreshold() {
      return this.threshold == null ? DEFAULT_THRESHOLD : this.threshold;
   }

   public NearestNeighborModel setThreshold(@Property("threshold") Number threshold) {
      this.threshold = threshold;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public NearestNeighborModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public NearestNeighborModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public NearestNeighborModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.NEARESTNEIGHBORMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public NearestNeighborModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public NearestNeighborModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public NearestNeighborModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public NearestNeighborModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public NearestNeighborModel setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public NearestNeighborModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public TrainingInstances requireTrainingInstances() {
      if (this.trainingInstances == null) {
         throw new MissingElementException(this, PMMLElements.NEARESTNEIGHBORMODEL_TRAININGINSTANCES);
      } else {
         return this.trainingInstances;
      }
   }

   public TrainingInstances getTrainingInstances() {
      return this.trainingInstances;
   }

   public NearestNeighborModel setTrainingInstances(@Property("trainingInstances") TrainingInstances trainingInstances) {
      this.trainingInstances = trainingInstances;
      return this;
   }

   public ComparisonMeasure requireComparisonMeasure() {
      if (this.comparisonMeasure == null) {
         throw new MissingElementException(this, PMMLElements.NEARESTNEIGHBORMODEL_COMPARISONMEASURE);
      } else {
         return this.comparisonMeasure;
      }
   }

   public ComparisonMeasure getComparisonMeasure() {
      return this.comparisonMeasure;
   }

   public NearestNeighborModel setComparisonMeasure(@Property("comparisonMeasure") ComparisonMeasure comparisonMeasure) {
      this.comparisonMeasure = comparisonMeasure;
      return this;
   }

   public KNNInputs requireKNNInputs() {
      if (this.knnInputs == null) {
         throw new MissingElementException(this, PMMLElements.NEARESTNEIGHBORMODEL_KNNINPUTS);
      } else {
         return this.knnInputs;
      }
   }

   public KNNInputs getKNNInputs() {
      return this.knnInputs;
   }

   public NearestNeighborModel setKNNInputs(@Property("knnInputs") KNNInputs knnInputs) {
      this.knnInputs = knnInputs;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public NearestNeighborModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations(), this.getTrainingInstances(), this.getComparisonMeasure(), this.getKNNInputs(), this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum CategoricalScoringMethod implements StringValue {
      @XmlEnumValue("majorityVote")
      @JsonProperty("majorityVote")
      MAJORITY_VOTE("majorityVote"),
      @XmlEnumValue("weightedMajorityVote")
      @JsonProperty("weightedMajorityVote")
      WEIGHTED_MAJORITY_VOTE("weightedMajorityVote");

      private final String value;

      private CategoricalScoringMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static CategoricalScoringMethod fromValue(String v) {
         for(CategoricalScoringMethod c : values()) {
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
   public static enum ContinuousScoringMethod implements StringValue {
      @XmlEnumValue("median")
      @JsonProperty("median")
      MEDIAN("median"),
      @XmlEnumValue("average")
      @JsonProperty("average")
      AVERAGE("average"),
      @XmlEnumValue("weightedAverage")
      @JsonProperty("weightedAverage")
      WEIGHTED_AVERAGE("weightedAverage");

      private final String value;

      private ContinuousScoringMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static ContinuousScoringMethod fromValue(String v) {
         for(ContinuousScoringMethod c : values()) {
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
