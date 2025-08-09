package org.sparkproject.dmg.pmml.clustering;

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
import org.sparkproject.dmg.pmml.ComparisonMeasure;
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
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ClusteringModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "localTransformations", "comparisonMeasure", "clusteringFields", "centerFields", "missingValueWeights", "clusters", "modelVerification"}
)
@JsonRootName("ClusteringModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "modelClass", "numberOfClusters", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "localTransformations", "comparisonMeasure", "clusteringFields", "centerFields", "missingValueWeights", "clusters", "modelVerification"})
public class ClusteringModel extends Model implements HasExtensions {
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
      name = "modelClass",
      required = true
   )
   @JsonProperty("modelClass")
   private ModelClass modelClass;
   @XmlAttribute(
      name = "numberOfClusters",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfClusters")
   @CollectionSize("clusters")
   private Integer numberOfClusters;
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
      name = "LocalTransformations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LocalTransformations")
   private LocalTransformations localTransformations;
   @XmlElement(
      name = "ComparisonMeasure",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ComparisonMeasure")
   private ComparisonMeasure comparisonMeasure;
   @XmlElement(
      name = "ClusteringField",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ClusteringField")
   @Required(Version.PMML_4_2)
   @CollectionElementType(ClusteringField.class)
   private List clusteringFields;
   @XmlElement(
      name = "CenterFields",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("CenterFields")
   @Removed(Version.PMML_3_2)
   private CenterFields centerFields;
   @XmlElement(
      name = "MissingValueWeights",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MissingValueWeights")
   private MissingValueWeights missingValueWeights;
   @XmlElement(
      name = "Cluster",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Cluster")
   @CollectionElementType(Cluster.class)
   private List clusters;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public ClusteringModel() {
   }

   @ValueConstructor
   public ClusteringModel(@Property("miningFunction") MiningFunction miningFunction, @Property("modelClass") ModelClass modelClass, @Property("numberOfClusters") Integer numberOfClusters, @Property("miningSchema") MiningSchema miningSchema, @Property("comparisonMeasure") ComparisonMeasure comparisonMeasure, @Property("clusteringFields") List clusteringFields, @Property("clusters") List clusters) {
      this.miningFunction = miningFunction;
      this.modelClass = modelClass;
      this.numberOfClusters = numberOfClusters;
      this.miningSchema = miningSchema;
      this.comparisonMeasure = comparisonMeasure;
      this.clusteringFields = clusteringFields;
      this.clusters = clusters;
   }

   public String getModelName() {
      return this.modelName;
   }

   public ClusteringModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.CLUSTERINGMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public ClusteringModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public ClusteringModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public ModelClass requireModelClass() {
      if (this.modelClass == null) {
         throw new MissingAttributeException(this, PMMLAttributes.CLUSTERINGMODEL_MODELCLASS);
      } else {
         return this.modelClass;
      }
   }

   public ModelClass getModelClass() {
      return this.modelClass;
   }

   public ClusteringModel setModelClass(@Property("modelClass") ModelClass modelClass) {
      this.modelClass = modelClass;
      return this;
   }

   public Integer requireNumberOfClusters() {
      if (this.numberOfClusters == null) {
         throw new MissingAttributeException(this, PMMLAttributes.CLUSTERINGMODEL_NUMBEROFCLUSTERS);
      } else {
         return this.numberOfClusters;
      }
   }

   public Integer getNumberOfClusters() {
      return this.numberOfClusters;
   }

   public ClusteringModel setNumberOfClusters(@Property("numberOfClusters") Integer numberOfClusters) {
      this.numberOfClusters = numberOfClusters;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public ClusteringModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public ClusteringModel setMathContext(@Property("mathContext") MathContext mathContext) {
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

   public ClusteringModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.CLUSTERINGMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public ClusteringModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public ClusteringModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public ClusteringModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public ClusteringModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public ClusteringModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public ComparisonMeasure requireComparisonMeasure() {
      if (this.comparisonMeasure == null) {
         throw new MissingElementException(this, PMMLElements.CLUSTERINGMODEL_COMPARISONMEASURE);
      } else {
         return this.comparisonMeasure;
      }
   }

   public ComparisonMeasure getComparisonMeasure() {
      return this.comparisonMeasure;
   }

   public ClusteringModel setComparisonMeasure(@Property("comparisonMeasure") ComparisonMeasure comparisonMeasure) {
      this.comparisonMeasure = comparisonMeasure;
      return this;
   }

   public boolean hasClusteringFields() {
      return this.clusteringFields != null && !this.clusteringFields.isEmpty();
   }

   public List requireClusteringFields() {
      if (this.clusteringFields != null && !this.clusteringFields.isEmpty()) {
         return this.clusteringFields;
      } else {
         throw new MissingElementException(this, PMMLElements.CLUSTERINGMODEL_CLUSTERINGFIELDS);
      }
   }

   public List getClusteringFields() {
      if (this.clusteringFields == null) {
         this.clusteringFields = new ArrayList();
      }

      return this.clusteringFields;
   }

   public ClusteringModel addClusteringFields(ClusteringField... clusteringFields) {
      this.getClusteringFields().addAll(Arrays.asList(clusteringFields));
      return this;
   }

   public CenterFields getCenterFields() {
      return this.centerFields;
   }

   public ClusteringModel setCenterFields(@Property("centerFields") CenterFields centerFields) {
      this.centerFields = centerFields;
      return this;
   }

   public MissingValueWeights getMissingValueWeights() {
      return this.missingValueWeights;
   }

   public ClusteringModel setMissingValueWeights(@Property("missingValueWeights") MissingValueWeights missingValueWeights) {
      this.missingValueWeights = missingValueWeights;
      return this;
   }

   public boolean hasClusters() {
      return this.clusters != null && !this.clusters.isEmpty();
   }

   public List requireClusters() {
      if (this.clusters != null && !this.clusters.isEmpty()) {
         return this.clusters;
      } else {
         throw new MissingElementException(this, PMMLElements.CLUSTERINGMODEL_CLUSTERS);
      }
   }

   public List getClusters() {
      if (this.clusters == null) {
         this.clusters = new ArrayList();
      }

      return this.clusters;
   }

   public ClusteringModel addClusters(Cluster... clusters) {
      this.getClusters().addAll(Arrays.asList(clusters));
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public ClusteringModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getLocalTransformations(), this.getComparisonMeasure());
         }

         if (status == VisitorAction.CONTINUE && this.hasClusteringFields()) {
            status = PMMLObject.traverse(visitor, this.getClusteringFields());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getCenterFields(), this.getMissingValueWeights());
         }

         if (status == VisitorAction.CONTINUE && this.hasClusters()) {
            status = PMMLObject.traverse(visitor, this.getClusters());
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
   public static enum ModelClass implements StringValue {
      @XmlEnumValue("centerBased")
      @JsonProperty("centerBased")
      CENTER_BASED("centerBased"),
      @XmlEnumValue("distributionBased")
      @JsonProperty("distributionBased")
      DISTRIBUTION_BASED("distributionBased");

      private final String value;

      private ModelClass(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static ModelClass fromValue(String v) {
         for(ModelClass c : values()) {
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
