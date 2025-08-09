package org.sparkproject.dmg.pmml.gaussian_process;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
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
import org.sparkproject.dmg.pmml.Targets;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.nearest_neighbor.TrainingInstances;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "GaussianProcessModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "radialBasisKernel", "ardSquaredExponentialKernel", "absoluteExponentialKernel", "generalizedExponentialKernel", "trainingInstances", "modelVerification"}
)
@JsonRootName("GaussianProcessModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "optimizer", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "radialBasisKernel", "ardSquaredExponentialKernel", "absoluteExponentialKernel", "generalizedExponentialKernel", "trainingInstances", "modelVerification"})
@Added(Version.PMML_4_3)
public class GaussianProcessModel extends Model implements HasExtensions {
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
   @Added(Version.PMML_4_4)
   private String algorithmName;
   @XmlAttribute(
      name = "optimizer"
   )
   @JsonProperty("optimizer")
   private String optimizer;
   @XmlAttribute(
      name = "isScorable"
   )
   @JsonProperty("isScorable")
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
      name = "RadialBasisKernel",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("RadialBasisKernel")
   private RadialBasisKernel radialBasisKernel;
   @XmlElement(
      name = "ARDSquaredExponentialKernel",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ARDSquaredExponentialKernel")
   private ARDSquaredExponentialKernel ardSquaredExponentialKernel;
   @XmlElement(
      name = "AbsoluteExponentialKernel",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("AbsoluteExponentialKernel")
   private AbsoluteExponentialKernel absoluteExponentialKernel;
   @XmlElement(
      name = "GeneralizedExponentialKernel",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("GeneralizedExponentialKernel")
   private GeneralizedExponentialKernel generalizedExponentialKernel;
   @XmlElement(
      name = "TrainingInstances",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("TrainingInstances")
   private TrainingInstances trainingInstances;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public GaussianProcessModel() {
   }

   @ValueConstructor
   public GaussianProcessModel(@Property("miningFunction") MiningFunction miningFunction, @Property("miningSchema") MiningSchema miningSchema, @Property("radialBasisKernel") RadialBasisKernel radialBasisKernel, @Property("ardSquaredExponentialKernel") ARDSquaredExponentialKernel ardSquaredExponentialKernel, @Property("absoluteExponentialKernel") AbsoluteExponentialKernel absoluteExponentialKernel, @Property("generalizedExponentialKernel") GeneralizedExponentialKernel generalizedExponentialKernel, @Property("trainingInstances") TrainingInstances trainingInstances) {
      this.miningFunction = miningFunction;
      this.miningSchema = miningSchema;
      this.radialBasisKernel = radialBasisKernel;
      this.ardSquaredExponentialKernel = ardSquaredExponentialKernel;
      this.absoluteExponentialKernel = absoluteExponentialKernel;
      this.generalizedExponentialKernel = generalizedExponentialKernel;
      this.trainingInstances = trainingInstances;
   }

   public String getModelName() {
      return this.modelName;
   }

   public GaussianProcessModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GAUSSIANPROCESSMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public GaussianProcessModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public GaussianProcessModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public String getOptimizer() {
      return this.optimizer;
   }

   public GaussianProcessModel setOptimizer(@Property("optimizer") String optimizer) {
      this.optimizer = optimizer;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public GaussianProcessModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public GaussianProcessModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public GaussianProcessModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.GAUSSIANPROCESSMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public GaussianProcessModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public GaussianProcessModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public GaussianProcessModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public GaussianProcessModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public GaussianProcessModel setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public GaussianProcessModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public RadialBasisKernel requireRadialBasisKernel() {
      if (this.radialBasisKernel == null) {
         throw new MissingElementException(this, PMMLElements.GAUSSIANPROCESSMODEL_RADIALBASISKERNEL);
      } else {
         return this.radialBasisKernel;
      }
   }

   public RadialBasisKernel getRadialBasisKernel() {
      return this.radialBasisKernel;
   }

   public GaussianProcessModel setRadialBasisKernel(@Property("radialBasisKernel") RadialBasisKernel radialBasisKernel) {
      this.radialBasisKernel = radialBasisKernel;
      return this;
   }

   public ARDSquaredExponentialKernel requireARDSquaredExponentialKernel() {
      if (this.ardSquaredExponentialKernel == null) {
         throw new MissingElementException(this, PMMLElements.GAUSSIANPROCESSMODEL_ARDSQUAREDEXPONENTIALKERNEL);
      } else {
         return this.ardSquaredExponentialKernel;
      }
   }

   public ARDSquaredExponentialKernel getARDSquaredExponentialKernel() {
      return this.ardSquaredExponentialKernel;
   }

   public GaussianProcessModel setARDSquaredExponentialKernel(@Property("ardSquaredExponentialKernel") ARDSquaredExponentialKernel ardSquaredExponentialKernel) {
      this.ardSquaredExponentialKernel = ardSquaredExponentialKernel;
      return this;
   }

   public AbsoluteExponentialKernel requireAbsoluteExponentialKernel() {
      if (this.absoluteExponentialKernel == null) {
         throw new MissingElementException(this, PMMLElements.GAUSSIANPROCESSMODEL_ABSOLUTEEXPONENTIALKERNEL);
      } else {
         return this.absoluteExponentialKernel;
      }
   }

   public AbsoluteExponentialKernel getAbsoluteExponentialKernel() {
      return this.absoluteExponentialKernel;
   }

   public GaussianProcessModel setAbsoluteExponentialKernel(@Property("absoluteExponentialKernel") AbsoluteExponentialKernel absoluteExponentialKernel) {
      this.absoluteExponentialKernel = absoluteExponentialKernel;
      return this;
   }

   public GeneralizedExponentialKernel requireGeneralizedExponentialKernel() {
      if (this.generalizedExponentialKernel == null) {
         throw new MissingElementException(this, PMMLElements.GAUSSIANPROCESSMODEL_GENERALIZEDEXPONENTIALKERNEL);
      } else {
         return this.generalizedExponentialKernel;
      }
   }

   public GeneralizedExponentialKernel getGeneralizedExponentialKernel() {
      return this.generalizedExponentialKernel;
   }

   public GaussianProcessModel setGeneralizedExponentialKernel(@Property("generalizedExponentialKernel") GeneralizedExponentialKernel generalizedExponentialKernel) {
      this.generalizedExponentialKernel = generalizedExponentialKernel;
      return this;
   }

   public TrainingInstances requireTrainingInstances() {
      if (this.trainingInstances == null) {
         throw new MissingElementException(this, PMMLElements.GAUSSIANPROCESSMODEL_TRAININGINSTANCES);
      } else {
         return this.trainingInstances;
      }
   }

   public TrainingInstances getTrainingInstances() {
      return this.trainingInstances;
   }

   public GaussianProcessModel setTrainingInstances(@Property("trainingInstances") TrainingInstances trainingInstances) {
      this.trainingInstances = trainingInstances;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public GaussianProcessModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations(), this.getRadialBasisKernel(), this.getARDSquaredExponentialKernel(), this.getAbsoluteExponentialKernel(), this.getGeneralizedExponentialKernel(), this.getTrainingInstances(), this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
