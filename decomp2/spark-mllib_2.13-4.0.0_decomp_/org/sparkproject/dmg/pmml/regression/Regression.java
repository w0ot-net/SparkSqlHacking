package org.sparkproject.dmg.pmml.regression;

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
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Deprecated;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Regression",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "output", "modelStats", "targets", "localTransformations", "resultFields", "regressionTables"}
)
@JsonRootName("Regression")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "normalizationMethod", "extensions", "output", "modelStats", "targets", "localTransformations", "resultFields", "regressionTables"})
@Deprecated(Version.PMML_4_1)
public class Regression extends EmbeddedModel implements HasExtensions, HasRegressionTables {
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
      name = "normalizationMethod"
   )
   @JsonProperty("normalizationMethod")
   private RegressionModel.NormalizationMethod normalizationMethod;
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
   @XmlElement(
      name = "RegressionTable",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("RegressionTable")
   @CollectionElementType(RegressionTable.class)
   private List regressionTables;
   private static final long serialVersionUID = 67371272L;

   public Regression() {
   }

   @ValueConstructor
   public Regression(@Property("miningFunction") MiningFunction miningFunction, @Property("regressionTables") List regressionTables) {
      this.miningFunction = miningFunction;
      this.regressionTables = regressionTables;
   }

   public String getModelName() {
      return this.modelName;
   }

   public Regression setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.REGRESSION_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public Regression setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public Regression setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public RegressionModel.NormalizationMethod getNormalizationMethod() {
      return this.normalizationMethod == null ? RegressionModel.NormalizationMethod.NONE : this.normalizationMethod;
   }

   public Regression setNormalizationMethod(@Property("normalizationMethod") RegressionModel.NormalizationMethod normalizationMethod) {
      this.normalizationMethod = normalizationMethod;
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

   public Regression addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public Regression setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public Regression setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public Targets getTargets() {
      return this.targets;
   }

   public Regression setTargets(@Property("targets") Targets targets) {
      this.targets = targets;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public Regression setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
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

   public Regression addResultFields(ResultField... resultFields) {
      this.getResultFields().addAll(Arrays.asList(resultFields));
      return this;
   }

   public boolean hasRegressionTables() {
      return this.regressionTables != null && !this.regressionTables.isEmpty();
   }

   public List requireRegressionTables() {
      if (this.regressionTables != null && !this.regressionTables.isEmpty()) {
         return this.regressionTables;
      } else {
         throw new MissingElementException(this, PMMLElements.REGRESSION_REGRESSIONTABLES);
      }
   }

   public List getRegressionTables() {
      if (this.regressionTables == null) {
         this.regressionTables = new ArrayList();
      }

      return this.regressionTables;
   }

   public Regression addRegressionTables(RegressionTable... regressionTables) {
      this.getRegressionTables().addAll(Arrays.asList(regressionTables));
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

         if (status == VisitorAction.CONTINUE && this.hasRegressionTables()) {
            status = PMMLObject.traverse(visitor, this.getRegressionTables());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
