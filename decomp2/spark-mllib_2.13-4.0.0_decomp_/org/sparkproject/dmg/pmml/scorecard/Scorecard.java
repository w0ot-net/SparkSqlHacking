package org.sparkproject.dmg.pmml.scorecard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
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
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Scorecard",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "characteristics", "modelVerification"}
)
@JsonRootName("Scorecard")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "initialScore", "useReasonCodes", "reasonCodeAlgorithm", "baselineScore", "baselineMethod", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "characteristics", "modelVerification"})
@Added(Version.PMML_4_1)
public class Scorecard extends Model implements HasExtensions, HasBaselineScore {
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
      name = "initialScore"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("initialScore")
   private Number initialScore;
   @XmlAttribute(
      name = "useReasonCodes"
   )
   @JsonProperty("useReasonCodes")
   private Boolean useReasonCodes;
   @XmlAttribute(
      name = "reasonCodeAlgorithm"
   )
   @JsonProperty("reasonCodeAlgorithm")
   private ReasonCodeAlgorithm reasonCodeAlgorithm;
   @XmlAttribute(
      name = "baselineScore"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("baselineScore")
   private Number baselineScore;
   @XmlAttribute(
      name = "baselineMethod"
   )
   @JsonProperty("baselineMethod")
   private BaselineMethod baselineMethod;
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
      name = "Characteristics",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Characteristics")
   private Characteristics characteristics;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Number DEFAULT_INITIAL_SCORE = (new NumberAdapter()).unmarshal("0");
   private static final Boolean DEFAULT_USE_REASON_CODES = true;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public Scorecard() {
   }

   @ValueConstructor
   public Scorecard(@Property("miningFunction") MiningFunction miningFunction, @Property("miningSchema") MiningSchema miningSchema, @Property("characteristics") Characteristics characteristics) {
      this.miningFunction = miningFunction;
      this.miningSchema = miningSchema;
      this.characteristics = characteristics;
   }

   public String getModelName() {
      return this.modelName;
   }

   public Scorecard setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SCORECARD_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public Scorecard setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public Scorecard setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public Number getInitialScore() {
      return this.initialScore == null ? DEFAULT_INITIAL_SCORE : this.initialScore;
   }

   public Scorecard setInitialScore(@Property("initialScore") Number initialScore) {
      this.initialScore = initialScore;
      return this;
   }

   public boolean isUseReasonCodes() {
      return this.useReasonCodes == null ? DEFAULT_USE_REASON_CODES : this.useReasonCodes;
   }

   public Scorecard setUseReasonCodes(@Property("useReasonCodes") Boolean useReasonCodes) {
      this.useReasonCodes = useReasonCodes;
      return this;
   }

   public ReasonCodeAlgorithm getReasonCodeAlgorithm() {
      return this.reasonCodeAlgorithm == null ? Scorecard.ReasonCodeAlgorithm.POINTS_BELOW : this.reasonCodeAlgorithm;
   }

   public Scorecard setReasonCodeAlgorithm(@Property("reasonCodeAlgorithm") ReasonCodeAlgorithm reasonCodeAlgorithm) {
      this.reasonCodeAlgorithm = reasonCodeAlgorithm;
      return this;
   }

   public Number getBaselineScore() {
      return this.baselineScore;
   }

   public Scorecard setBaselineScore(@Property("baselineScore") Number baselineScore) {
      this.baselineScore = baselineScore;
      return this;
   }

   public BaselineMethod getBaselineMethod() {
      return this.baselineMethod == null ? Scorecard.BaselineMethod.OTHER : this.baselineMethod;
   }

   public Scorecard setBaselineMethod(@Property("baselineMethod") BaselineMethod baselineMethod) {
      this.baselineMethod = baselineMethod;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public Scorecard setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public Scorecard setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public Scorecard addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.SCORECARD_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public Scorecard setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public Scorecard setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public Scorecard setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public Scorecard setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public Scorecard setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public Scorecard setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public Characteristics requireCharacteristics() {
      if (this.characteristics == null) {
         throw new MissingElementException(this, PMMLElements.SCORECARD_CHARACTERISTICS);
      } else {
         return this.characteristics;
      }
   }

   public Characteristics getCharacteristics() {
      return this.characteristics;
   }

   public Scorecard setCharacteristics(@Property("characteristics") Characteristics characteristics) {
      this.characteristics = characteristics;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public Scorecard setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations(), this.getCharacteristics(), this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum BaselineMethod implements StringValue {
      @XmlEnumValue("max")
      @JsonProperty("max")
      MAX("max"),
      @XmlEnumValue("min")
      @JsonProperty("min")
      MIN("min"),
      @XmlEnumValue("mean")
      @JsonProperty("mean")
      MEAN("mean"),
      @XmlEnumValue("neutral")
      @JsonProperty("neutral")
      NEUTRAL("neutral"),
      @XmlEnumValue("other")
      @JsonProperty("other")
      OTHER("other");

      private final String value;

      private BaselineMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static BaselineMethod fromValue(String v) {
         for(BaselineMethod c : values()) {
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
   public static enum ReasonCodeAlgorithm implements StringValue {
      @XmlEnumValue("pointsAbove")
      @JsonProperty("pointsAbove")
      POINTS_ABOVE("pointsAbove"),
      @XmlEnumValue("pointsBelow")
      @JsonProperty("pointsBelow")
      POINTS_BELOW("pointsBelow");

      private final String value;

      private ReasonCodeAlgorithm(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static ReasonCodeAlgorithm fromValue(String v) {
         for(ReasonCodeAlgorithm c : values()) {
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
