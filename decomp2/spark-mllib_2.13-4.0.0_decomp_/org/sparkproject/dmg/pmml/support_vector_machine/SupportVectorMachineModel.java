package org.sparkproject.dmg.pmml.support_vector_machine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
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
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SupportVectorMachineModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "kernel", "vectorDictionary", "supportVectorMachines", "modelVerification"}
)
@JsonRootName("SupportVectorMachineModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "threshold", "representation", "alternateBinaryTargetCategory", "classificationMethod", "maxWins", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "kernel", "vectorDictionary", "supportVectorMachines", "modelVerification"})
public class SupportVectorMachineModel extends Model implements HasExtensions {
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
      name = "threshold"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("threshold")
   @Added(Version.PMML_4_0)
   private Number threshold;
   @XmlAttribute(
      name = "svmRepresentation"
   )
   @JsonProperty("svmRepresentation")
   private Representation representation;
   @XmlAttribute(
      name = "alternateBinaryTargetCategory"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("alternateBinaryTargetCategory")
   @Added(Version.PMML_3_1)
   @Removed(Version.PMML_4_0)
   private Object alternateBinaryTargetCategory;
   @XmlAttribute(
      name = "classificationMethod"
   )
   @JsonProperty("classificationMethod")
   @Added(Version.PMML_4_0)
   private ClassificationMethod classificationMethod;
   @XmlAttribute(
      name = "maxWins"
   )
   @JsonProperty("maxWins")
   @Added(Version.PMML_4_3)
   private Boolean maxWins;
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
   name = "LinearKernelType",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = LinearKernel.class
), @XmlElement(
   name = "PolynomialKernelType",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = PolynomialKernel.class
), @XmlElement(
   name = "RadialBasisKernelType",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = RadialBasisKernel.class
), @XmlElement(
   name = "SigmoidKernelType",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SigmoidKernel.class
)})
   @JsonProperty("Kernel")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "LinearKernel",
   value = LinearKernel.class
), @Type(
   name = "PolynomialKernel",
   value = PolynomialKernel.class
), @Type(
   name = "RadialBasisKernel",
   value = RadialBasisKernel.class
), @Type(
   name = "SigmoidKernel",
   value = SigmoidKernel.class
)})
   private Kernel kernel;
   @XmlElement(
      name = "VectorDictionary",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("VectorDictionary")
   @Required(Version.PMML_3_1)
   private VectorDictionary vectorDictionary;
   @XmlElement(
      name = "SupportVectorMachine",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("SupportVectorMachine")
   @CollectionElementType(SupportVectorMachine.class)
   private List supportVectorMachines;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Number DEFAULT_THRESHOLD = (new RealNumberAdapter()).unmarshal("0");
   private static final Boolean DEFAULT_MAX_WINS = false;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public SupportVectorMachineModel() {
   }

   @ValueConstructor
   public SupportVectorMachineModel(@Property("miningFunction") MiningFunction miningFunction, @Property("miningSchema") MiningSchema miningSchema, @Property("kernel") Kernel kernel, @Property("vectorDictionary") VectorDictionary vectorDictionary, @Property("supportVectorMachines") List supportVectorMachines) {
      this.miningFunction = miningFunction;
      this.miningSchema = miningSchema;
      this.kernel = kernel;
      this.vectorDictionary = vectorDictionary;
      this.supportVectorMachines = supportVectorMachines;
   }

   public String getModelName() {
      return this.modelName;
   }

   public SupportVectorMachineModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SUPPORTVECTORMACHINEMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public SupportVectorMachineModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public SupportVectorMachineModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public Number getThreshold() {
      return this.threshold == null ? DEFAULT_THRESHOLD : this.threshold;
   }

   public SupportVectorMachineModel setThreshold(@Property("threshold") Number threshold) {
      this.threshold = threshold;
      return this;
   }

   public Representation getRepresentation() {
      return this.representation == null ? SupportVectorMachineModel.Representation.SUPPORT_VECTORS : this.representation;
   }

   public SupportVectorMachineModel setRepresentation(@Property("representation") Representation representation) {
      this.representation = representation;
      return this;
   }

   public Object getAlternateBinaryTargetCategory() {
      return this.alternateBinaryTargetCategory;
   }

   public SupportVectorMachineModel setAlternateBinaryTargetCategory(@Property("alternateBinaryTargetCategory") Object alternateBinaryTargetCategory) {
      this.alternateBinaryTargetCategory = alternateBinaryTargetCategory;
      return this;
   }

   public ClassificationMethod getClassificationMethod() {
      return this.classificationMethod == null ? SupportVectorMachineModel.ClassificationMethod.ONE_AGAINST_ALL : this.classificationMethod;
   }

   public SupportVectorMachineModel setClassificationMethod(@Property("classificationMethod") ClassificationMethod classificationMethod) {
      this.classificationMethod = classificationMethod;
      return this;
   }

   public boolean isMaxWins() {
      return this.maxWins == null ? DEFAULT_MAX_WINS : this.maxWins;
   }

   public SupportVectorMachineModel setMaxWins(@Property("maxWins") Boolean maxWins) {
      this.maxWins = maxWins;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public SupportVectorMachineModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public SupportVectorMachineModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public SupportVectorMachineModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.SUPPORTVECTORMACHINEMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public SupportVectorMachineModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public SupportVectorMachineModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public SupportVectorMachineModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public SupportVectorMachineModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public SupportVectorMachineModel setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public SupportVectorMachineModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public Kernel requireKernel() {
      if (this.kernel == null) {
         throw new MissingElementException(this, PMMLElements.SUPPORTVECTORMACHINEMODEL_KERNEL);
      } else {
         return this.kernel;
      }
   }

   public Kernel getKernel() {
      return this.kernel;
   }

   public SupportVectorMachineModel setKernel(@Property("kernel") Kernel kernel) {
      this.kernel = kernel;
      return this;
   }

   public VectorDictionary requireVectorDictionary() {
      if (this.vectorDictionary == null) {
         throw new MissingElementException(this, PMMLElements.SUPPORTVECTORMACHINEMODEL_VECTORDICTIONARY);
      } else {
         return this.vectorDictionary;
      }
   }

   public VectorDictionary getVectorDictionary() {
      return this.vectorDictionary;
   }

   public SupportVectorMachineModel setVectorDictionary(@Property("vectorDictionary") VectorDictionary vectorDictionary) {
      this.vectorDictionary = vectorDictionary;
      return this;
   }

   public boolean hasSupportVectorMachines() {
      return this.supportVectorMachines != null && !this.supportVectorMachines.isEmpty();
   }

   public List requireSupportVectorMachines() {
      if (this.supportVectorMachines != null && !this.supportVectorMachines.isEmpty()) {
         return this.supportVectorMachines;
      } else {
         throw new MissingElementException(this, PMMLElements.SUPPORTVECTORMACHINEMODEL_SUPPORTVECTORMACHINES);
      }
   }

   public List getSupportVectorMachines() {
      if (this.supportVectorMachines == null) {
         this.supportVectorMachines = new ArrayList();
      }

      return this.supportVectorMachines;
   }

   public SupportVectorMachineModel addSupportVectorMachines(SupportVectorMachine... supportVectorMachines) {
      this.getSupportVectorMachines().addAll(Arrays.asList(supportVectorMachines));
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public SupportVectorMachineModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations(), this.getKernel(), this.getVectorDictionary());
         }

         if (status == VisitorAction.CONTINUE && this.hasSupportVectorMachines()) {
            status = PMMLObject.traverse(visitor, this.getSupportVectorMachines());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum ClassificationMethod implements StringValue {
      @XmlEnumValue("OneAgainstAll")
      @JsonProperty("OneAgainstAll")
      ONE_AGAINST_ALL("OneAgainstAll"),
      @XmlEnumValue("OneAgainstOne")
      @JsonProperty("OneAgainstOne")
      ONE_AGAINST_ONE("OneAgainstOne");

      private final String value;

      private ClassificationMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static ClassificationMethod fromValue(String v) {
         for(ClassificationMethod c : values()) {
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
   public static enum Representation implements StringValue {
      @XmlEnumValue("SupportVectors")
      @JsonProperty("SupportVectors")
      SUPPORT_VECTORS("SupportVectors"),
      @XmlEnumValue("Coefficients")
      @JsonProperty("Coefficients")
      COEFFICIENTS("Coefficients");

      private final String value;

      private Representation(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Representation fromValue(String v) {
         for(Representation c : values()) {
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
