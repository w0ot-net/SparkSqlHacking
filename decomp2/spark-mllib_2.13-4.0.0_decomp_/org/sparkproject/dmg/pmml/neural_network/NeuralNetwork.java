package org.sparkproject.dmg.pmml.neural_network;

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
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "NeuralNetwork",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "neuralInputs", "neuralLayers", "neuralOutputs", "modelVerification"}
)
@JsonRootName("NeuralNetwork")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "activationFunction", "normalizationMethod", "threshold", "leakage", "width", "altitude", "numberOfLayers", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "neuralInputs", "neuralLayers", "neuralOutputs", "modelVerification"})
public class NeuralNetwork extends Model implements HasExtensions, HasActivationFunction, HasNormalizationMethod {
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
      name = "activationFunction",
      required = true
   )
   @JsonProperty("activationFunction")
   private ActivationFunction activationFunction;
   @XmlAttribute(
      name = "normalizationMethod"
   )
   @JsonProperty("normalizationMethod")
   private NormalizationMethod normalizationMethod;
   @XmlAttribute(
      name = "threshold"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("threshold")
   private Number threshold;
   @XmlAttribute(
      name = "x-leakage"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("x-leakage")
   @Added(Version.XPMML)
   @Since("1.5.2")
   private Number leakage;
   @XmlAttribute(
      name = "width"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("width")
   private Number width;
   @XmlAttribute(
      name = "altitude"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("altitude")
   private Number altitude;
   @XmlAttribute(
      name = "numberOfLayers"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfLayers")
   @CollectionSize("neuralLayers")
   private Integer numberOfLayers;
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
   @XmlElement(
      name = "NeuralInputs",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("NeuralInputs")
   private NeuralInputs neuralInputs;
   @XmlElement(
      name = "NeuralLayer",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("NeuralLayer")
   @CollectionElementType(NeuralLayer.class)
   private List neuralLayers;
   @XmlElement(
      name = "NeuralOutputs",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("NeuralOutputs")
   private NeuralOutputs neuralOutputs;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Number DEFAULT_THRESHOLD = (new RealNumberAdapter()).unmarshal("0");
   private static final Number DEFAULT_ALTITUDE = (new RealNumberAdapter()).unmarshal("1.0");
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public NeuralNetwork() {
   }

   @ValueConstructor
   public NeuralNetwork(@Property("miningFunction") MiningFunction miningFunction, @Property("activationFunction") ActivationFunction activationFunction, @Property("miningSchema") MiningSchema miningSchema, @Property("neuralInputs") NeuralInputs neuralInputs, @Property("neuralLayers") List neuralLayers) {
      this.miningFunction = miningFunction;
      this.activationFunction = activationFunction;
      this.miningSchema = miningSchema;
      this.neuralInputs = neuralInputs;
      this.neuralLayers = neuralLayers;
   }

   public String getModelName() {
      return this.modelName;
   }

   public NeuralNetwork setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NEURALNETWORK_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public NeuralNetwork setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public NeuralNetwork setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public ActivationFunction requireActivationFunction() {
      if (this.activationFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NEURALNETWORK_ACTIVATIONFUNCTION);
      } else {
         return this.activationFunction;
      }
   }

   public ActivationFunction getActivationFunction() {
      return this.activationFunction;
   }

   public NeuralNetwork setActivationFunction(@Property("activationFunction") ActivationFunction activationFunction) {
      this.activationFunction = activationFunction;
      return this;
   }

   public NormalizationMethod getNormalizationMethod() {
      return this.normalizationMethod == null ? NeuralNetwork.NormalizationMethod.NONE : this.normalizationMethod;
   }

   public NeuralNetwork setNormalizationMethod(@Property("normalizationMethod") NormalizationMethod normalizationMethod) {
      this.normalizationMethod = normalizationMethod;
      return this;
   }

   public Number getThreshold() {
      return this.threshold == null ? DEFAULT_THRESHOLD : this.threshold;
   }

   public NeuralNetwork setThreshold(@Property("threshold") Number threshold) {
      this.threshold = threshold;
      return this;
   }

   public Number getLeakage() {
      return this.leakage;
   }

   public NeuralNetwork setLeakage(@Property("leakage") Number leakage) {
      this.leakage = leakage;
      return this;
   }

   public Number getWidth() {
      return this.width;
   }

   public NeuralNetwork setWidth(@Property("width") Number width) {
      this.width = width;
      return this;
   }

   public Number getAltitude() {
      return this.altitude == null ? DEFAULT_ALTITUDE : this.altitude;
   }

   public NeuralNetwork setAltitude(@Property("altitude") Number altitude) {
      this.altitude = altitude;
      return this;
   }

   public Integer getNumberOfLayers() {
      return this.numberOfLayers;
   }

   public NeuralNetwork setNumberOfLayers(@Property("numberOfLayers") Integer numberOfLayers) {
      this.numberOfLayers = numberOfLayers;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public NeuralNetwork setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public NeuralNetwork setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public NeuralNetwork addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.NEURALNETWORK_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public NeuralNetwork setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public NeuralNetwork setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public NeuralNetwork setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public NeuralNetwork setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public NeuralNetwork setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public NeuralNetwork setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public NeuralInputs requireNeuralInputs() {
      if (this.neuralInputs == null) {
         throw new MissingElementException(this, PMMLElements.NEURALNETWORK_NEURALINPUTS);
      } else {
         return this.neuralInputs;
      }
   }

   public NeuralInputs getNeuralInputs() {
      return this.neuralInputs;
   }

   public NeuralNetwork setNeuralInputs(@Property("neuralInputs") NeuralInputs neuralInputs) {
      this.neuralInputs = neuralInputs;
      return this;
   }

   public boolean hasNeuralLayers() {
      return this.neuralLayers != null && !this.neuralLayers.isEmpty();
   }

   public List requireNeuralLayers() {
      if (this.neuralLayers != null && !this.neuralLayers.isEmpty()) {
         return this.neuralLayers;
      } else {
         throw new MissingElementException(this, PMMLElements.NEURALNETWORK_NEURALLAYERS);
      }
   }

   public List getNeuralLayers() {
      if (this.neuralLayers == null) {
         this.neuralLayers = new ArrayList();
      }

      return this.neuralLayers;
   }

   public NeuralNetwork addNeuralLayers(NeuralLayer... neuralLayers) {
      this.getNeuralLayers().addAll(Arrays.asList(neuralLayers));
      return this;
   }

   public NeuralOutputs requireNeuralOutputs() {
      if (this.neuralOutputs == null) {
         throw new MissingElementException(this, PMMLElements.NEURALNETWORK_NEURALOUTPUTS);
      } else {
         return this.neuralOutputs;
      }
   }

   public NeuralOutputs getNeuralOutputs() {
      return this.neuralOutputs;
   }

   public NeuralNetwork setNeuralOutputs(@Property("neuralOutputs") NeuralOutputs neuralOutputs) {
      this.neuralOutputs = neuralOutputs;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public NeuralNetwork setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations(), this.getNeuralInputs());
         }

         if (status == VisitorAction.CONTINUE && this.hasNeuralLayers()) {
            status = PMMLObject.traverse(visitor, this.getNeuralLayers());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getNeuralOutputs(), this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum ActivationFunction implements StringValue {
      @XmlEnumValue("threshold")
      @JsonProperty("threshold")
      THRESHOLD("threshold"),
      @XmlEnumValue("logistic")
      @JsonProperty("logistic")
      LOGISTIC("logistic"),
      @XmlEnumValue("tanh")
      @JsonProperty("tanh")
      TANH("tanh"),
      @XmlEnumValue("identity")
      @JsonProperty("identity")
      IDENTITY("identity"),
      @XmlEnumValue("exponential")
      @JsonProperty("exponential")
      EXPONENTIAL("exponential"),
      @XmlEnumValue("reciprocal")
      @JsonProperty("reciprocal")
      RECIPROCAL("reciprocal"),
      @XmlEnumValue("square")
      @JsonProperty("square")
      SQUARE("square"),
      @XmlEnumValue("Gauss")
      @JsonProperty("Gauss")
      GAUSS("Gauss"),
      @XmlEnumValue("sine")
      @JsonProperty("sine")
      SINE("sine"),
      @XmlEnumValue("cosine")
      @JsonProperty("cosine")
      COSINE("cosine"),
      @XmlEnumValue("Elliott")
      @JsonProperty("Elliott")
      ELLIOTT("Elliott"),
      @XmlEnumValue("arctan")
      @JsonProperty("arctan")
      ARCTAN("arctan"),
      @XmlEnumValue("rectifier")
      @JsonProperty("rectifier")
      @Added(Version.PMML_4_3)
      RECTIFIER("rectifier"),
      @XmlEnumValue("radialBasis")
      @JsonProperty("radialBasis")
      RADIAL_BASIS("radialBasis");

      private final String value;

      private ActivationFunction(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static ActivationFunction fromValue(String v) {
         for(ActivationFunction c : values()) {
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
   public static enum NormalizationMethod implements StringValue {
      @XmlEnumValue("none")
      @JsonProperty("none")
      NONE("none"),
      @XmlEnumValue("simplemax")
      @JsonProperty("simplemax")
      SIMPLEMAX("simplemax"),
      @XmlEnumValue("softmax")
      @JsonProperty("softmax")
      SOFTMAX("softmax");

      private final String value;

      private NormalizationMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static NormalizationMethod fromValue(String v) {
         for(NormalizationMethod c : values()) {
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
