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

@XmlRootElement(
   name = "ModelStats",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "univariateStats", "multivariateStats"}
)
@JsonRootName("ModelStats")
@JsonPropertyOrder({"extensions", "univariateStats", "multivariateStats"})
public class ModelStats extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "UnivariateStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("UnivariateStats")
   @CollectionElementType(UnivariateStats.class)
   private List univariateStats;
   @XmlElement(
      name = "MultivariateStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MultivariateStats")
   @Added(Version.PMML_4_1)
   @CollectionElementType(MultivariateStats.class)
   private List multivariateStats;
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

   public ModelStats addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasUnivariateStats() {
      return this.univariateStats != null && !this.univariateStats.isEmpty();
   }

   public List getUnivariateStats() {
      if (this.univariateStats == null) {
         this.univariateStats = new ArrayList();
      }

      return this.univariateStats;
   }

   public ModelStats addUnivariateStats(UnivariateStats... univariateStats) {
      this.getUnivariateStats().addAll(Arrays.asList(univariateStats));
      return this;
   }

   public boolean hasMultivariateStats() {
      return this.multivariateStats != null && !this.multivariateStats.isEmpty();
   }

   public List getMultivariateStats() {
      if (this.multivariateStats == null) {
         this.multivariateStats = new ArrayList();
      }

      return this.multivariateStats;
   }

   public ModelStats addMultivariateStats(MultivariateStats... multivariateStats) {
      this.getMultivariateStats().addAll(Arrays.asList(multivariateStats));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasUnivariateStats()) {
            status = PMMLObject.traverse(visitor, this.getUnivariateStats());
         }

         if (status == VisitorAction.CONTINUE && this.hasMultivariateStats()) {
            status = PMMLObject.traverse(visitor, this.getMultivariateStats());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
