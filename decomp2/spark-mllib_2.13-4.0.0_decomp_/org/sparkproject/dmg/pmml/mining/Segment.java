package org.sparkproject.dmg.pmml.mining;

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
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.CompoundPredicate;
import org.sparkproject.dmg.pmml.Entity;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.False;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasModel;
import org.sparkproject.dmg.pmml.HasPredicate;
import org.sparkproject.dmg.pmml.Model;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.SimpleSetPredicate;
import org.sparkproject.dmg.pmml.True;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.anomaly_detection.AnomalyDetectionModel;
import org.sparkproject.dmg.pmml.association.AssociationModel;
import org.sparkproject.dmg.pmml.baseline.BaselineModel;
import org.sparkproject.dmg.pmml.bayesian_network.BayesianNetworkModel;
import org.sparkproject.dmg.pmml.clustering.ClusteringModel;
import org.sparkproject.dmg.pmml.gaussian_process.GaussianProcessModel;
import org.sparkproject.dmg.pmml.general_regression.GeneralRegressionModel;
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
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Segment",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "predicate", "model", "variableWeight"}
)
@JsonRootName("Segment")
@JsonPropertyOrder({"id", "weight", "extensions", "predicate", "model", "variableWeight"})
@Added(Version.PMML_4_0)
public class Segment extends Entity implements HasExtensions, HasModel, HasPredicate {
   @XmlAttribute(
      name = "id"
   )
   @JsonProperty("id")
   private String id;
   @XmlAttribute(
      name = "weight"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("weight")
   private Number weight;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElements({@XmlElement(
   name = "SimplePredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimplePredicate.class
), @XmlElement(
   name = "CompoundPredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = CompoundPredicate.class
), @XmlElement(
   name = "SimpleSetPredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimpleSetPredicate.class
), @XmlElement(
   name = "True",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = True.class
), @XmlElement(
   name = "False",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = False.class
)})
   @JsonProperty("Predicate")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "SimplePredicate",
   value = SimplePredicate.class
), @Type(
   name = "CompoundPredicate",
   value = CompoundPredicate.class
), @Type(
   name = "SimpleSetPredicate",
   value = SimpleSetPredicate.class
), @Type(
   name = "True",
   value = True.class
), @Type(
   name = "False",
   value = False.class
)})
   private Predicate predicate;
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
      name = "VariableWeight",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("VariableWeight")
   @Added(Version.PMML_4_4)
   private VariableWeight variableWeight;
   private static final Number DEFAULT_WEIGHT = (new NumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public Segment() {
   }

   @ValueConstructor
   public Segment(@Property("predicate") Predicate predicate, @Property("model") Model model) {
      this.predicate = predicate;
      this.model = model;
   }

   public String getId() {
      return this.id;
   }

   public Segment setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public Number getWeight() {
      return this.weight == null ? DEFAULT_WEIGHT : this.weight;
   }

   public Segment setWeight(@Property("weight") Number weight) {
      this.weight = weight;
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

   public Segment addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Predicate requirePredicate() {
      if (this.predicate == null) {
         throw new MissingElementException(this, PMMLElements.SEGMENT_PREDICATE);
      } else {
         return this.predicate;
      }
   }

   public Predicate getPredicate() {
      return this.predicate;
   }

   public Segment setPredicate(@Property("predicate") Predicate predicate) {
      this.predicate = predicate;
      return this;
   }

   public Model requireModel() {
      if (this.model == null) {
         throw new MissingElementException(this, PMMLElements.SEGMENT_MODEL);
      } else {
         return this.model;
      }
   }

   public Model getModel() {
      return this.model;
   }

   public Segment setModel(@Property("model") Model model) {
      this.model = model;
      return this;
   }

   public VariableWeight getVariableWeight() {
      return this.variableWeight;
   }

   public Segment setVariableWeight(@Property("variableWeight") VariableWeight variableWeight) {
      this.variableWeight = variableWeight;
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
            status = PMMLObject.traverse(visitor, this.getPredicate(), this.getModel(), this.getVariableWeight());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
