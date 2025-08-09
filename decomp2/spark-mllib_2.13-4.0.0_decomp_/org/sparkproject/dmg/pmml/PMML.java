package org.sparkproject.dmg.pmml;

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
import org.sparkproject.dmg.pmml.anomaly_detection.AnomalyDetectionModel;
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
   name = "PMML",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"header", "miningBuildTask", "dataDictionary", "transformationDictionary", "models", "extensions"}
)
@JsonRootName("PMML")
@JsonPropertyOrder({"version", "baseVersion", "header", "miningBuildTask", "dataDictionary", "transformationDictionary", "models", "extensions"})
public class PMML extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "version",
      required = true
   )
   @JsonProperty("version")
   private String version;
   @XmlAttribute(
      name = "x-baseVersion"
   )
   @JsonProperty("x-baseVersion")
   @Added(
      value = Version.XPMML,
      removable = true
   )
   @Since("1.3.3")
   private String baseVersion;
   @XmlElement(
      name = "Header",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Header")
   private Header header;
   @XmlElement(
      name = "MiningBuildTask",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MiningBuildTask")
   private MiningBuildTask miningBuildTask;
   @XmlElement(
      name = "DataDictionary",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("DataDictionary")
   private DataDictionary dataDictionary;
   @XmlElement(
      name = "TransformationDictionary",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TransformationDictionary")
   private TransformationDictionary transformationDictionary;
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
   @CollectionElementType(Model.class)
   private List models;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public PMML() {
   }

   @ValueConstructor
   public PMML(@Property("version") String version, @Property("header") Header header, @Property("dataDictionary") DataDictionary dataDictionary) {
      this.version = version;
      this.header = header;
      this.dataDictionary = dataDictionary;
   }

   public String requireVersion() {
      if (this.version == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PMML_VERSION);
      } else {
         return this.version;
      }
   }

   public String getVersion() {
      return this.version;
   }

   public PMML setVersion(@Property("version") String version) {
      this.version = version;
      return this;
   }

   public String getBaseVersion() {
      return this.baseVersion;
   }

   public PMML setBaseVersion(@Property("baseVersion") String baseVersion) {
      this.baseVersion = baseVersion;
      return this;
   }

   public Header requireHeader() {
      if (this.header == null) {
         throw new MissingElementException(this, PMMLElements.PMML_HEADER);
      } else {
         return this.header;
      }
   }

   public Header getHeader() {
      return this.header;
   }

   public PMML setHeader(@Property("header") Header header) {
      this.header = header;
      return this;
   }

   public MiningBuildTask getMiningBuildTask() {
      return this.miningBuildTask;
   }

   public PMML setMiningBuildTask(@Property("miningBuildTask") MiningBuildTask miningBuildTask) {
      this.miningBuildTask = miningBuildTask;
      return this;
   }

   public DataDictionary requireDataDictionary() {
      if (this.dataDictionary == null) {
         throw new MissingElementException(this, PMMLElements.PMML_DATADICTIONARY);
      } else {
         return this.dataDictionary;
      }
   }

   public DataDictionary getDataDictionary() {
      return this.dataDictionary;
   }

   public PMML setDataDictionary(@Property("dataDictionary") DataDictionary dataDictionary) {
      this.dataDictionary = dataDictionary;
      return this;
   }

   public TransformationDictionary getTransformationDictionary() {
      return this.transformationDictionary;
   }

   public PMML setTransformationDictionary(@Property("transformationDictionary") TransformationDictionary transformationDictionary) {
      this.transformationDictionary = transformationDictionary;
      return this;
   }

   public boolean hasModels() {
      return this.models != null && !this.models.isEmpty();
   }

   public List getModels() {
      if (this.models == null) {
         this.models = new ArrayList();
      }

      return this.models;
   }

   public PMML addModels(Model... models) {
      this.getModels().addAll(Arrays.asList(models));
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

   public PMML addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getHeader(), this.getMiningBuildTask(), this.getDataDictionary(), this.getTransformationDictionary());
         }

         if (status == VisitorAction.CONTINUE && this.hasModels()) {
            status = PMMLObject.traverse(visitor, this.getModels());
         }

         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
