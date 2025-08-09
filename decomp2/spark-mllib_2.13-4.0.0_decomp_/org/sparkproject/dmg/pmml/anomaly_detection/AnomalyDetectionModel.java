package org.sparkproject.dmg.pmml.anomaly_detection;

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
import org.sparkproject.dmg.pmml.ModelVerification;
import org.sparkproject.dmg.pmml.Output;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.association.AssociationModel;
import org.sparkproject.dmg.pmml.baseline.BaselineModel;
import org.sparkproject.dmg.pmml.bayesian_network.BayesianNetworkModel;
import org.sparkproject.dmg.pmml.clustering.ClusteringModel;
import org.sparkproject.dmg.pmml.gaussian_process.GaussianProcessModel;
import org.sparkproject.dmg.pmml.general_regression.GeneralRegressionModel;
import org.sparkproject.dmg.pmml.mining.MiningModel;
import org.sparkproject.dmg.pmml.naive_bayes.NaiveBayesModel;
import org.sparkproject.dmg.pmml.nearest_neighbor.NearestNeighborModel;
import org.sparkproject.dmg.pmml.neural_network.NeuralNetwork;
import org.sparkproject.dmg.pmml.regression.RegressionModel;
import org.sparkproject.dmg.pmml.rule_set.RuleSetModel;
import org.sparkproject.dmg.pmml.scorecard.Scorecard;
import org.sparkproject.dmg.pmml.sequence.SequenceModel;
import org.sparkproject.dmg.pmml.support_vector_machine.SupportVectorMachineModel;
import org.sparkproject.dmg.pmml.text.TextModel;
import org.sparkproject.dmg.pmml.time_series.TimeSeriesModel;
import org.sparkproject.dmg.pmml.tree.TreeModel;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "AnomalyDetectionModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "localTransformations", "modelVerification", "model", "meanClusterDistances"}
)
@JsonRootName("AnomalyDetectionModel")
@JsonPropertyOrder({"modelName", "algorithmName", "miningFunction", "algorithmType", "sampleDataSize", "scorable", "mathContext", "extensions", "miningSchema", "output", "localTransformations", "modelVerification", "model", "meanClusterDistances"})
@Added(Version.PMML_4_4)
public class AnomalyDetectionModel extends Model implements HasExtensions {
   @XmlAttribute(
      name = "modelName"
   )
   @JsonProperty("modelName")
   private String modelName;
   @XmlAttribute(
      name = "algorithmName"
   )
   @JsonProperty("algorithmName")
   private String algorithmName;
   @XmlAttribute(
      name = "functionName",
      required = true
   )
   @JsonProperty("functionName")
   private MiningFunction miningFunction;
   @XmlAttribute(
      name = "algorithmType",
      required = true
   )
   @JsonProperty("algorithmType")
   private String algorithmType;
   @XmlAttribute(
      name = "sampleDataSize"
   )
   @JsonProperty("sampleDataSize")
   private String sampleDataSize;
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
   @Since("1.5.0")
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
      name = "LocalTransformations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LocalTransformations")
   private LocalTransformations localTransformations;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   @XmlElements({@XmlElement(
   name = "AnomalyDetectionModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = AnomalyDetectionModel.class
), @XmlElement(
   name = "AssociationModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = AssociationModel.class
), @XmlElement(
   name = "BayesianNetworkModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = BayesianNetworkModel.class
), @XmlElement(
   name = "BaselineModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = BaselineModel.class
), @XmlElement(
   name = "ClusteringModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = ClusteringModel.class
), @XmlElement(
   name = "GaussianProcessModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = GaussianProcessModel.class
), @XmlElement(
   name = "GeneralRegressionModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = GeneralRegressionModel.class
), @XmlElement(
   name = "MiningModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = MiningModel.class
), @XmlElement(
   name = "NaiveBayesModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = NaiveBayesModel.class
), @XmlElement(
   name = "NearestNeighborModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = NearestNeighborModel.class
), @XmlElement(
   name = "NeuralNetwork",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = NeuralNetwork.class
), @XmlElement(
   name = "RegressionModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = RegressionModel.class
), @XmlElement(
   name = "RuleSetModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = RuleSetModel.class
), @XmlElement(
   name = "SequenceModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SequenceModel.class
), @XmlElement(
   name = "Scorecard",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Scorecard.class
), @XmlElement(
   name = "SupportVectorMachineModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SupportVectorMachineModel.class
), @XmlElement(
   name = "TextModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = TextModel.class
), @XmlElement(
   name = "TimeSeriesModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = TimeSeriesModel.class
), @XmlElement(
   name = "TreeModel",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = TreeModel.class
)})
   @JsonProperty("Model")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "AnomalyDetectionModel",
   value = AnomalyDetectionModel.class
), @Type(
   name = "AssociationModel",
   value = AssociationModel.class
), @Type(
   name = "BayesianNetworkModel",
   value = BayesianNetworkModel.class
), @Type(
   name = "BaselineModel",
   value = BaselineModel.class
), @Type(
   name = "ClusteringModel",
   value = ClusteringModel.class
), @Type(
   name = "GaussianProcessModel",
   value = GaussianProcessModel.class
), @Type(
   name = "GeneralRegressionModel",
   value = GeneralRegressionModel.class
), @Type(
   name = "MiningModel",
   value = MiningModel.class
), @Type(
   name = "NaiveBayesModel",
   value = NaiveBayesModel.class
), @Type(
   name = "NearestNeighborModel",
   value = NearestNeighborModel.class
), @Type(
   name = "NeuralNetwork",
   value = NeuralNetwork.class
), @Type(
   name = "RegressionModel",
   value = RegressionModel.class
), @Type(
   name = "RuleSetModel",
   value = RuleSetModel.class
), @Type(
   name = "SequenceModel",
   value = SequenceModel.class
), @Type(
   name = "Scorecard",
   value = Scorecard.class
), @Type(
   name = "SupportVectorMachineModel",
   value = SupportVectorMachineModel.class
), @Type(
   name = "TextModel",
   value = TextModel.class
), @Type(
   name = "TimeSeriesModel",
   value = TimeSeriesModel.class
), @Type(
   name = "TreeModel",
   value = TreeModel.class
)})
   private Model model;
   @XmlElement(
      name = "MeanClusterDistances",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MeanClusterDistances")
   private MeanClusterDistances meanClusterDistances;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public AnomalyDetectionModel() {
   }

   @ValueConstructor
   public AnomalyDetectionModel(@Property("miningFunction") MiningFunction miningFunction, @Property("algorithmType") String algorithmType, @Property("miningSchema") MiningSchema miningSchema, @Property("model") Model model) {
      this.miningFunction = miningFunction;
      this.algorithmType = algorithmType;
      this.miningSchema = miningSchema;
      this.model = model;
   }

   public String getModelName() {
      return this.modelName;
   }

   public AnomalyDetectionModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public AnomalyDetectionModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ANOMALYDETECTIONMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public AnomalyDetectionModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String requireAlgorithmType() {
      if (this.algorithmType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ANOMALYDETECTIONMODEL_ALGORITHMTYPE);
      } else {
         return this.algorithmType;
      }
   }

   public String getAlgorithmType() {
      return this.algorithmType;
   }

   public AnomalyDetectionModel setAlgorithmType(@Property("algorithmType") String algorithmType) {
      this.algorithmType = algorithmType;
      return this;
   }

   public String getSampleDataSize() {
      return this.sampleDataSize;
   }

   public AnomalyDetectionModel setSampleDataSize(@Property("sampleDataSize") String sampleDataSize) {
      this.sampleDataSize = sampleDataSize;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public AnomalyDetectionModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public AnomalyDetectionModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public AnomalyDetectionModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.ANOMALYDETECTIONMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public AnomalyDetectionModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public AnomalyDetectionModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public AnomalyDetectionModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public AnomalyDetectionModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
      this.modelVerification = modelVerification;
      return this;
   }

   public Model requireModel() {
      if (this.model == null) {
         throw new MissingElementException(this, PMMLElements.ANOMALYDETECTIONMODEL_MODEL);
      } else {
         return this.model;
      }
   }

   public Model getModel() {
      return this.model;
   }

   public AnomalyDetectionModel setModel(@Property("model") Model model) {
      this.model = model;
      return this;
   }

   public MeanClusterDistances getMeanClusterDistances() {
      return this.meanClusterDistances;
   }

   public AnomalyDetectionModel setMeanClusterDistances(@Property("meanClusterDistances") MeanClusterDistances meanClusterDistances) {
      this.meanClusterDistances = meanClusterDistances;
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getLocalTransformations(), this.getModelVerification(), this.getModel(), this.getMeanClusterDistances());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
