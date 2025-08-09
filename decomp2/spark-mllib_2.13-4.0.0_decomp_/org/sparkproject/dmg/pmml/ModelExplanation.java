package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "ModelExplanation",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "predictiveModelQualities", "clusteringModelQualities", "correlations"}
)
@JsonRootName("ModelExplanation")
@JsonPropertyOrder({"extensions", "predictiveModelQualities", "clusteringModelQualities", "correlations"})
@Added(Version.PMML_4_0)
public class ModelExplanation extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "PredictiveModelQuality",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("PredictiveModelQuality")
   @CollectionElementType(PredictiveModelQuality.class)
   private List predictiveModelQualities;
   @XmlElement(
      name = "ClusteringModelQuality",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ClusteringModelQuality")
   @CollectionElementType(ClusteringModelQuality.class)
   private List clusteringModelQualities;
   @XmlElement(
      name = "Correlations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Correlations")
   private Correlations correlations;
   private static final long serialVersionUID = 67371272L;

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public List getExtensions() {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      return this.extensions;
   }

   public ModelExplanation addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasPredictiveModelQualities() {
      return this.predictiveModelQualities != null && !this.predictiveModelQualities.isEmpty();
   }

   public List getPredictiveModelQualities() {
      if (this.predictiveModelQualities == null) {
         this.predictiveModelQualities = new ArrayList();
      }

      return this.predictiveModelQualities;
   }

   public ModelExplanation addPredictiveModelQualities(PredictiveModelQuality... predictiveModelQualities) {
      this.getPredictiveModelQualities().addAll(Arrays.asList(predictiveModelQualities));
      return this;
   }

   public boolean hasClusteringModelQualities() {
      return this.clusteringModelQualities != null && !this.clusteringModelQualities.isEmpty();
   }

   public List getClusteringModelQualities() {
      if (this.clusteringModelQualities == null) {
         this.clusteringModelQualities = new ArrayList();
      }

      return this.clusteringModelQualities;
   }

   public ModelExplanation addClusteringModelQualities(ClusteringModelQuality... clusteringModelQualities) {
      this.getClusteringModelQualities().addAll(Arrays.asList(clusteringModelQualities));
      return this;
   }

   public Correlations getCorrelations() {
      return this.correlations;
   }

   public ModelExplanation setCorrelations(@Property("correlations") Correlations correlations) {
      this.correlations = correlations;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasPredictiveModelQualities()) {
            status = PMMLObject.traverse(visitor, this.getPredictiveModelQualities());
         }

         if (status == VisitorAction.CONTINUE && this.hasClusteringModelQualities()) {
            status = PMMLObject.traverse(visitor, this.getClusteringModelQualities());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getCorrelations());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
