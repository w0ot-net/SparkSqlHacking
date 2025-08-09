package org.sparkproject.dmg.pmml.bayesian_network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "LognormalDistributionForBN",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "mean", "variance"}
)
@JsonRootName("LognormalDistributionForBN")
@JsonPropertyOrder({"extensions", "mean", "variance"})
@Added(Version.PMML_4_3)
public class LognormalDistribution extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Mean",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Mean")
   private Mean mean;
   @XmlElement(
      name = "Variance",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Variance")
   private Variance variance;
   private static final long serialVersionUID = 67371272L;

   public LognormalDistribution() {
   }

   @ValueConstructor
   public LognormalDistribution(@Property("mean") Mean mean, @Property("variance") Variance variance) {
      this.mean = mean;
      this.variance = variance;
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

   public LognormalDistribution addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Mean requireMean() {
      if (this.mean == null) {
         throw new MissingElementException(this, PMMLElements.LOGNORMALDISTRIBUTION_MEAN);
      } else {
         return this.mean;
      }
   }

   public Mean getMean() {
      return this.mean;
   }

   public LognormalDistribution setMean(@Property("mean") Mean mean) {
      this.mean = mean;
      return this;
   }

   public Variance requireVariance() {
      if (this.variance == null) {
         throw new MissingElementException(this, PMMLElements.LOGNORMALDISTRIBUTION_VARIANCE);
      } else {
         return this.variance;
      }
   }

   public Variance getVariance() {
      return this.variance;
   }

   public LognormalDistribution setVariance(@Property("variance") Variance variance) {
      this.variance = variance;
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
            status = PMMLObject.traverse(visitor, this.getMean(), this.getVariance());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
