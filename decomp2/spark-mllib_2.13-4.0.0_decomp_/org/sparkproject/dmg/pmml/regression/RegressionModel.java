package org.sparkproject.dmg.pmml.regression;

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
import org.sparkproject.dmg.pmml.HasTargetFieldReference;
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
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Deprecated;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "RegressionModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "regressionTables", "modelVerification"}
)
@JsonRootName("RegressionModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "modelType", "targetField", "normalizationMethod", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "regressionTables", "modelVerification"})
public class RegressionModel extends Model implements HasExtensions, HasTargetFieldReference, HasRegressionTables {
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
      name = "modelType"
   )
   @JsonProperty("modelType")
   @Deprecated(Version.PMML_3_0)
   private ModelType modelType;
   @XmlAttribute(
      name = "targetFieldName"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("targetFieldName")
   @Deprecated(Version.PMML_3_0)
   private String targetField;
   @XmlAttribute(
      name = "normalizationMethod"
   )
   @JsonProperty("normalizationMethod")
   private NormalizationMethod normalizationMethod;
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
      name = "RegressionTable",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("RegressionTable")
   @CollectionElementType(RegressionTable.class)
   private List regressionTables;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public RegressionModel() {
   }

   @ValueConstructor
   public RegressionModel(@Property("miningFunction") MiningFunction miningFunction, @Property("miningSchema") MiningSchema miningSchema, @Property("regressionTables") List regressionTables) {
      this.miningFunction = miningFunction;
      this.miningSchema = miningSchema;
      this.regressionTables = regressionTables;
   }

   public String getModelName() {
      return this.modelName;
   }

   public RegressionModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.REGRESSIONMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public RegressionModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public RegressionModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public ModelType getModelType() {
      return this.modelType;
   }

   public RegressionModel setModelType(@Property("modelType") ModelType modelType) {
      this.modelType = modelType;
      return this;
   }

   public String getTargetField() {
      return this.targetField;
   }

   public RegressionModel setTargetField(@Property("targetField") String targetField) {
      this.targetField = targetField;
      return this;
   }

   public NormalizationMethod getNormalizationMethod() {
      return this.normalizationMethod == null ? RegressionModel.NormalizationMethod.NONE : this.normalizationMethod;
   }

   public RegressionModel setNormalizationMethod(@Property("normalizationMethod") NormalizationMethod normalizationMethod) {
      this.normalizationMethod = normalizationMethod;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public RegressionModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public RegressionModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public RegressionModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.REGRESSIONMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public RegressionModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public RegressionModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public RegressionModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public RegressionModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public RegressionModel setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public RegressionModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public boolean hasRegressionTables() {
      return this.regressionTables != null && !this.regressionTables.isEmpty();
   }

   public List requireRegressionTables() {
      if (this.regressionTables != null && !this.regressionTables.isEmpty()) {
         return this.regressionTables;
      } else {
         throw new MissingElementException(this, PMMLElements.REGRESSIONMODEL_REGRESSIONTABLES);
      }
   }

   public List getRegressionTables() {
      if (this.regressionTables == null) {
         this.regressionTables = new ArrayList();
      }

      return this.regressionTables;
   }

   public RegressionModel addRegressionTables(RegressionTable... regressionTables) {
      this.getRegressionTables().addAll(Arrays.asList(regressionTables));
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public RegressionModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations());
         }

         if (status == VisitorAction.CONTINUE && this.hasRegressionTables()) {
            status = PMMLObject.traverse(visitor, this.getRegressionTables());
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
   public static enum ModelType implements StringValue {
      @XmlEnumValue("linearRegression")
      @JsonProperty("linearRegression")
      LINEAR_REGRESSION("linearRegression"),
      @XmlEnumValue("stepwisePolynomialRegression")
      @JsonProperty("stepwisePolynomialRegression")
      STEPWISE_POLYNOMIAL_REGRESSION("stepwisePolynomialRegression"),
      @XmlEnumValue("logisticRegression")
      @JsonProperty("logisticRegression")
      LOGISTIC_REGRESSION("logisticRegression");

      private final String value;

      private ModelType(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static ModelType fromValue(String v) {
         for(ModelType c : values()) {
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
      SOFTMAX("softmax"),
      @XmlEnumValue("logit")
      @JsonProperty("logit")
      LOGIT("logit"),
      @XmlEnumValue("probit")
      @JsonProperty("probit")
      PROBIT("probit"),
      @XmlEnumValue("cloglog")
      @JsonProperty("cloglog")
      CLOGLOG("cloglog"),
      @XmlEnumValue("exp")
      @JsonProperty("exp")
      EXP("exp"),
      @XmlEnumValue("loglog")
      @JsonProperty("loglog")
      @Added(Version.PMML_3_1)
      LOGLOG("loglog"),
      @XmlEnumValue("cauchit")
      @JsonProperty("cauchit")
      @Added(Version.PMML_3_1)
      CAUCHIT("cauchit");

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
