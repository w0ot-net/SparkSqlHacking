package org.sparkproject.dmg.pmml.general_regression;

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
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Deprecated;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Required;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "GeneralRegressionModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "parameterList", "factorList", "covariateList", "ppMatrix", "pCovMatrix", "paramMatrix", "eventValues", "baseCumHazardTables", "modelVerification"}
)
@JsonRootName("GeneralRegressionModel")
@JsonPropertyOrder({"targetVariable", "modelType", "modelName", "miningFunction", "algorithmName", "targetReferenceCategory", "cumulativeLinkFunction", "linkFunction", "linkParameter", "trialsVariable", "trialsValue", "distribution", "distParameter", "offsetVariable", "offsetValue", "modelDF", "endTimeVariable", "startTimeVariable", "subjectIDVariable", "statusVariable", "baselineStrataVariable", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "targets", "localTransformations", "parameterList", "factorList", "covariateList", "ppMatrix", "pCovMatrix", "paramMatrix", "eventValues", "baseCumHazardTables", "modelVerification"})
public class GeneralRegressionModel extends Model implements HasExtensions {
   @XmlAttribute(
      name = "targetVariableName"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("targetVariableName")
   @Deprecated(Version.PMML_3_0)
   private String targetVariable;
   @XmlAttribute(
      name = "modelType",
      required = true
   )
   @JsonProperty("modelType")
   private ModelType modelType;
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
      name = "targetReferenceCategory"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("targetReferenceCategory")
   @Added(Version.PMML_4_0)
   private Object targetReferenceCategory;
   @XmlAttribute(
      name = "cumulativeLink"
   )
   @JsonProperty("cumulativeLink")
   @Added(Version.PMML_3_1)
   private CumulativeLinkFunction cumulativeLinkFunction;
   @XmlAttribute(
      name = "linkFunction"
   )
   @JsonProperty("linkFunction")
   @Added(Version.PMML_3_2)
   private LinkFunction linkFunction;
   @XmlAttribute(
      name = "linkParameter"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("linkParameter")
   @Added(Version.PMML_3_2)
   private Number linkParameter;
   @XmlAttribute(
      name = "trialsVariable"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("trialsVariable")
   @Added(Version.PMML_3_2)
   private String trialsVariable;
   @XmlAttribute(
      name = "trialsValue"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("trialsValue")
   @Added(Version.PMML_3_2)
   private Integer trialsValue;
   @XmlAttribute(
      name = "distribution"
   )
   @JsonProperty("distribution")
   @Added(Version.PMML_3_2)
   private Distribution distribution;
   @XmlAttribute(
      name = "distParameter"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("distParameter")
   @Added(Version.PMML_3_2)
   private Number distParameter;
   @XmlAttribute(
      name = "offsetVariable"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("offsetVariable")
   @Added(Version.PMML_3_2)
   private String offsetVariable;
   @XmlAttribute(
      name = "offsetValue"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("offsetValue")
   @Added(Version.PMML_3_2)
   private Number offsetValue;
   @XmlAttribute(
      name = "modelDF"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("modelDF")
   @Added(Version.PMML_4_0)
   private Number modelDF;
   @XmlAttribute(
      name = "endTimeVariable"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("endTimeVariable")
   @Added(Version.PMML_4_0)
   private String endTimeVariable;
   @XmlAttribute(
      name = "startTimeVariable"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("startTimeVariable")
   @Added(Version.PMML_4_0)
   private String startTimeVariable;
   @XmlAttribute(
      name = "subjectIDVariable"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("subjectIDVariable")
   @Added(Version.PMML_4_0)
   private String subjectIDVariable;
   @XmlAttribute(
      name = "statusVariable"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("statusVariable")
   @Added(Version.PMML_4_0)
   private String statusVariable;
   @XmlAttribute(
      name = "baselineStrataVariable"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("baselineStrataVariable")
   @Added(Version.PMML_4_0)
   private String baselineStrataVariable;
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
      name = "ParameterList",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ParameterList")
   private ParameterList parameterList;
   @XmlElement(
      name = "FactorList",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("FactorList")
   private FactorList factorList;
   @XmlElement(
      name = "CovariateList",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("CovariateList")
   private CovariateList covariateList;
   @XmlElement(
      name = "PPMatrix",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("PPMatrix")
   @Required(Version.PMML_3_1)
   private PPMatrix ppMatrix;
   @XmlElement(
      name = "PCovMatrix",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("PCovMatrix")
   private PCovMatrix pCovMatrix;
   @XmlElement(
      name = "ParamMatrix",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ParamMatrix")
   private ParamMatrix paramMatrix;
   @XmlElement(
      name = "EventValues",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("EventValues")
   @Added(Version.PMML_4_0)
   private EventValues eventValues;
   @XmlElement(
      name = "BaseCumHazardTables",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("BaseCumHazardTables")
   @Added(Version.PMML_4_0)
   private BaseCumHazardTables baseCumHazardTables;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public GeneralRegressionModel() {
   }

   @ValueConstructor
   public GeneralRegressionModel(@Property("modelType") ModelType modelType, @Property("miningFunction") MiningFunction miningFunction, @Property("miningSchema") MiningSchema miningSchema, @Property("parameterList") ParameterList parameterList, @Property("ppMatrix") PPMatrix ppMatrix, @Property("paramMatrix") ParamMatrix paramMatrix) {
      this.modelType = modelType;
      this.miningFunction = miningFunction;
      this.miningSchema = miningSchema;
      this.parameterList = parameterList;
      this.ppMatrix = ppMatrix;
      this.paramMatrix = paramMatrix;
   }

   public String getTargetVariable() {
      return this.targetVariable;
   }

   public GeneralRegressionModel setTargetVariable(@Property("targetVariable") String targetVariable) {
      this.targetVariable = targetVariable;
      return this;
   }

   public ModelType requireModelType() {
      if (this.modelType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GENERALREGRESSIONMODEL_MODELTYPE);
      } else {
         return this.modelType;
      }
   }

   public ModelType getModelType() {
      return this.modelType;
   }

   public GeneralRegressionModel setModelType(@Property("modelType") ModelType modelType) {
      this.modelType = modelType;
      return this;
   }

   public String getModelName() {
      return this.modelName;
   }

   public GeneralRegressionModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GENERALREGRESSIONMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public GeneralRegressionModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public GeneralRegressionModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public Object getTargetReferenceCategory() {
      return this.targetReferenceCategory;
   }

   public GeneralRegressionModel setTargetReferenceCategory(@Property("targetReferenceCategory") Object targetReferenceCategory) {
      this.targetReferenceCategory = targetReferenceCategory;
      return this;
   }

   public CumulativeLinkFunction requireCumulativeLinkFunction() {
      if (this.cumulativeLinkFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GENERALREGRESSIONMODEL_CUMULATIVELINKFUNCTION);
      } else {
         return this.cumulativeLinkFunction;
      }
   }

   public CumulativeLinkFunction getCumulativeLinkFunction() {
      return this.cumulativeLinkFunction;
   }

   public GeneralRegressionModel setCumulativeLinkFunction(@Property("cumulativeLinkFunction") CumulativeLinkFunction cumulativeLinkFunction) {
      this.cumulativeLinkFunction = cumulativeLinkFunction;
      return this;
   }

   public LinkFunction requireLinkFunction() {
      if (this.linkFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GENERALREGRESSIONMODEL_LINKFUNCTION);
      } else {
         return this.linkFunction;
      }
   }

   public LinkFunction getLinkFunction() {
      return this.linkFunction;
   }

   public GeneralRegressionModel setLinkFunction(@Property("linkFunction") LinkFunction linkFunction) {
      this.linkFunction = linkFunction;
      return this;
   }

   public Number requireLinkParameter() {
      if (this.linkParameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GENERALREGRESSIONMODEL_LINKPARAMETER);
      } else {
         return this.linkParameter;
      }
   }

   public Number getLinkParameter() {
      return this.linkParameter;
   }

   public GeneralRegressionModel setLinkParameter(@Property("linkParameter") Number linkParameter) {
      this.linkParameter = linkParameter;
      return this;
   }

   public String getTrialsVariable() {
      return this.trialsVariable;
   }

   public GeneralRegressionModel setTrialsVariable(@Property("trialsVariable") String trialsVariable) {
      this.trialsVariable = trialsVariable;
      return this;
   }

   public Integer getTrialsValue() {
      return this.trialsValue;
   }

   public GeneralRegressionModel setTrialsValue(@Property("trialsValue") Integer trialsValue) {
      this.trialsValue = trialsValue;
      return this;
   }

   public Distribution getDistribution() {
      return this.distribution;
   }

   public GeneralRegressionModel setDistribution(@Property("distribution") Distribution distribution) {
      this.distribution = distribution;
      return this;
   }

   public Number requireDistParameter() {
      if (this.distParameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.GENERALREGRESSIONMODEL_DISTPARAMETER);
      } else {
         return this.distParameter;
      }
   }

   public Number getDistParameter() {
      return this.distParameter;
   }

   public GeneralRegressionModel setDistParameter(@Property("distParameter") Number distParameter) {
      this.distParameter = distParameter;
      return this;
   }

   public String getOffsetVariable() {
      return this.offsetVariable;
   }

   public GeneralRegressionModel setOffsetVariable(@Property("offsetVariable") String offsetVariable) {
      this.offsetVariable = offsetVariable;
      return this;
   }

   public Number getOffsetValue() {
      return this.offsetValue;
   }

   public GeneralRegressionModel setOffsetValue(@Property("offsetValue") Number offsetValue) {
      this.offsetValue = offsetValue;
      return this;
   }

   public Number getModelDF() {
      return this.modelDF;
   }

   public GeneralRegressionModel setModelDF(@Property("modelDF") Number modelDF) {
      this.modelDF = modelDF;
      return this;
   }

   public String getEndTimeVariable() {
      return this.endTimeVariable;
   }

   public GeneralRegressionModel setEndTimeVariable(@Property("endTimeVariable") String endTimeVariable) {
      this.endTimeVariable = endTimeVariable;
      return this;
   }

   public String getStartTimeVariable() {
      return this.startTimeVariable;
   }

   public GeneralRegressionModel setStartTimeVariable(@Property("startTimeVariable") String startTimeVariable) {
      this.startTimeVariable = startTimeVariable;
      return this;
   }

   public String getSubjectIDVariable() {
      return this.subjectIDVariable;
   }

   public GeneralRegressionModel setSubjectIDVariable(@Property("subjectIDVariable") String subjectIDVariable) {
      this.subjectIDVariable = subjectIDVariable;
      return this;
   }

   public String getStatusVariable() {
      return this.statusVariable;
   }

   public GeneralRegressionModel setStatusVariable(@Property("statusVariable") String statusVariable) {
      this.statusVariable = statusVariable;
      return this;
   }

   public String getBaselineStrataVariable() {
      return this.baselineStrataVariable;
   }

   public GeneralRegressionModel setBaselineStrataVariable(@Property("baselineStrataVariable") String baselineStrataVariable) {
      this.baselineStrataVariable = baselineStrataVariable;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public GeneralRegressionModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public GeneralRegressionModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public GeneralRegressionModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.GENERALREGRESSIONMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public GeneralRegressionModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public GeneralRegressionModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public GeneralRegressionModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public GeneralRegressionModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public GeneralRegressionModel setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public GeneralRegressionModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public ParameterList requireParameterList() {
      if (this.parameterList == null) {
         throw new MissingElementException(this, PMMLElements.GENERALREGRESSIONMODEL_PARAMETERLIST);
      } else {
         return this.parameterList;
      }
   }

   public ParameterList getParameterList() {
      return this.parameterList;
   }

   public GeneralRegressionModel setParameterList(@Property("parameterList") ParameterList parameterList) {
      this.parameterList = parameterList;
      return this;
   }

   public FactorList getFactorList() {
      return this.factorList;
   }

   public GeneralRegressionModel setFactorList(@Property("factorList") FactorList factorList) {
      this.factorList = factorList;
      return this;
   }

   public CovariateList getCovariateList() {
      return this.covariateList;
   }

   public GeneralRegressionModel setCovariateList(@Property("covariateList") CovariateList covariateList) {
      this.covariateList = covariateList;
      return this;
   }

   public PPMatrix requirePPMatrix() {
      if (this.ppMatrix == null) {
         throw new MissingElementException(this, PMMLElements.GENERALREGRESSIONMODEL_PPMATRIX);
      } else {
         return this.ppMatrix;
      }
   }

   public PPMatrix getPPMatrix() {
      return this.ppMatrix;
   }

   public GeneralRegressionModel setPPMatrix(@Property("ppMatrix") PPMatrix ppMatrix) {
      this.ppMatrix = ppMatrix;
      return this;
   }

   public PCovMatrix getPCovMatrix() {
      return this.pCovMatrix;
   }

   public GeneralRegressionModel setPCovMatrix(@Property("pCovMatrix") PCovMatrix pCovMatrix) {
      this.pCovMatrix = pCovMatrix;
      return this;
   }

   public ParamMatrix requireParamMatrix() {
      if (this.paramMatrix == null) {
         throw new MissingElementException(this, PMMLElements.GENERALREGRESSIONMODEL_PARAMMATRIX);
      } else {
         return this.paramMatrix;
      }
   }

   public ParamMatrix getParamMatrix() {
      return this.paramMatrix;
   }

   public GeneralRegressionModel setParamMatrix(@Property("paramMatrix") ParamMatrix paramMatrix) {
      this.paramMatrix = paramMatrix;
      return this;
   }

   public EventValues getEventValues() {
      return this.eventValues;
   }

   public GeneralRegressionModel setEventValues(@Property("eventValues") EventValues eventValues) {
      this.eventValues = eventValues;
      return this;
   }

   public BaseCumHazardTables requireBaseCumHazardTables() {
      if (this.baseCumHazardTables == null) {
         throw new MissingElementException(this, PMMLElements.GENERALREGRESSIONMODEL_BASECUMHAZARDTABLES);
      } else {
         return this.baseCumHazardTables;
      }
   }

   public BaseCumHazardTables getBaseCumHazardTables() {
      return this.baseCumHazardTables;
   }

   public GeneralRegressionModel setBaseCumHazardTables(@Property("baseCumHazardTables") BaseCumHazardTables baseCumHazardTables) {
      this.baseCumHazardTables = baseCumHazardTables;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public GeneralRegressionModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getTargets(), this.getLocalTransformations(), this.getParameterList(), this.getFactorList(), this.getCovariateList(), this.getPPMatrix(), this.getPCovMatrix(), this.getParamMatrix(), this.getEventValues(), this.getBaseCumHazardTables(), this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum CumulativeLinkFunction implements StringValue {
      @XmlEnumValue("logit")
      @JsonProperty("logit")
      LOGIT("logit"),
      @XmlEnumValue("probit")
      @JsonProperty("probit")
      PROBIT("probit"),
      @XmlEnumValue("cloglog")
      @JsonProperty("cloglog")
      CLOGLOG("cloglog"),
      @XmlEnumValue("loglog")
      @JsonProperty("loglog")
      LOGLOG("loglog"),
      @XmlEnumValue("cauchit")
      @JsonProperty("cauchit")
      CAUCHIT("cauchit");

      private final String value;

      private CumulativeLinkFunction(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static CumulativeLinkFunction fromValue(String v) {
         for(CumulativeLinkFunction c : values()) {
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
   public static enum Distribution implements StringValue {
      @XmlEnumValue("binomial")
      @JsonProperty("binomial")
      BINOMIAL("binomial"),
      @XmlEnumValue("gamma")
      @JsonProperty("gamma")
      GAMMA("gamma"),
      @XmlEnumValue("igauss")
      @JsonProperty("igauss")
      IGAUSS("igauss"),
      @XmlEnumValue("negbin")
      @JsonProperty("negbin")
      NEGBIN("negbin"),
      @XmlEnumValue("normal")
      @JsonProperty("normal")
      NORMAL("normal"),
      @XmlEnumValue("poisson")
      @JsonProperty("poisson")
      POISSON("poisson"),
      @XmlEnumValue("tweedie")
      @JsonProperty("tweedie")
      @Added(Version.PMML_4_0)
      TWEEDIE("tweedie");

      private final String value;

      private Distribution(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Distribution fromValue(String v) {
         for(Distribution c : values()) {
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
   public static enum LinkFunction implements StringValue {
      @XmlEnumValue("cloglog")
      @JsonProperty("cloglog")
      CLOGLOG("cloglog"),
      @XmlEnumValue("identity")
      @JsonProperty("identity")
      IDENTITY("identity"),
      @XmlEnumValue("log")
      @JsonProperty("log")
      LOG("log"),
      @XmlEnumValue("logc")
      @JsonProperty("logc")
      LOGC("logc"),
      @XmlEnumValue("logit")
      @JsonProperty("logit")
      LOGIT("logit"),
      @XmlEnumValue("loglog")
      @JsonProperty("loglog")
      LOGLOG("loglog"),
      @XmlEnumValue("negbin")
      @JsonProperty("negbin")
      NEGBIN("negbin"),
      @XmlEnumValue("oddspower")
      @JsonProperty("oddspower")
      ODDSPOWER("oddspower"),
      @XmlEnumValue("power")
      @JsonProperty("power")
      POWER("power"),
      @XmlEnumValue("probit")
      @JsonProperty("probit")
      PROBIT("probit");

      private final String value;

      private LinkFunction(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static LinkFunction fromValue(String v) {
         for(LinkFunction c : values()) {
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
   public static enum ModelType implements StringValue {
      @XmlEnumValue("regression")
      @JsonProperty("regression")
      REGRESSION("regression"),
      @XmlEnumValue("generalLinear")
      @JsonProperty("generalLinear")
      GENERAL_LINEAR("generalLinear"),
      @XmlEnumValue("multinomialLogistic")
      @JsonProperty("multinomialLogistic")
      MULTINOMIAL_LOGISTIC("multinomialLogistic"),
      @XmlEnumValue("ordinalMultinomial")
      @JsonProperty("ordinalMultinomial")
      @Added(Version.PMML_3_1)
      ORDINAL_MULTINOMIAL("ordinalMultinomial"),
      @XmlEnumValue("generalizedLinear")
      @JsonProperty("generalizedLinear")
      @Added(Version.PMML_3_2)
      GENERALIZED_LINEAR("generalizedLinear"),
      @XmlEnumValue("CoxRegression")
      @JsonProperty("CoxRegression")
      @Added(Version.PMML_4_0)
      COX_REGRESSION("CoxRegression");

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
}
