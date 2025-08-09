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
   name = "UniformDistributionForBN",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "lower", "upper"}
)
@JsonRootName("UniformDistributionForBN")
@JsonPropertyOrder({"extensions", "lower", "upper"})
@Added(Version.PMML_4_3)
public class UniformDistribution extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Lower",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Lower")
   private Lower lower;
   @XmlElement(
      name = "Upper",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Upper")
   private Upper upper;
   private static final long serialVersionUID = 67371272L;

   public UniformDistribution() {
   }

   @ValueConstructor
   public UniformDistribution(@Property("lower") Lower lower, @Property("upper") Upper upper) {
      this.lower = lower;
      this.upper = upper;
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

   public UniformDistribution addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Lower requireLower() {
      if (this.lower == null) {
         throw new MissingElementException(this, PMMLElements.UNIFORMDISTRIBUTION_LOWER);
      } else {
         return this.lower;
      }
   }

   public Lower getLower() {
      return this.lower;
   }

   public UniformDistribution setLower(@Property("lower") Lower lower) {
      this.lower = lower;
      return this;
   }

   public Upper requireUpper() {
      if (this.upper == null) {
         throw new MissingElementException(this, PMMLElements.UNIFORMDISTRIBUTION_UPPER);
      } else {
         return this.upper;
      }
   }

   public Upper getUpper() {
      return this.upper;
   }

   public UniformDistribution setUpper(@Property("upper") Upper upper) {
      this.upper = upper;
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
            status = PMMLObject.traverse(visitor, this.getLower(), this.getUpper());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
