package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "AnyDistribution",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("AnyDistribution")
@JsonPropertyOrder({"mean", "variance", "extensions"})
@Added(Version.PMML_4_1)
public class AnyDistribution extends ContinuousDistribution implements HasExtensions {
   @XmlAttribute(
      name = "mean",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("mean")
   private Number mean;
   @XmlAttribute(
      name = "variance",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("variance")
   private Number variance;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public AnyDistribution() {
   }

   @ValueConstructor
   public AnyDistribution(@Property("mean") Number mean, @Property("variance") Number variance) {
      this.mean = mean;
      this.variance = variance;
   }

   public Number requireMean() {
      if (this.mean == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ANYDISTRIBUTION_MEAN);
      } else {
         return this.mean;
      }
   }

   public Number getMean() {
      return this.mean;
   }

   public AnyDistribution setMean(@Property("mean") Number mean) {
      this.mean = mean;
      return this;
   }

   public Number requireVariance() {
      if (this.variance == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ANYDISTRIBUTION_VARIANCE);
      } else {
         return this.variance;
      }
   }

   public Number getVariance() {
      return this.variance;
   }

   public AnyDistribution setVariance(@Property("variance") Number variance) {
      this.variance = variance;
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

   public AnyDistribution addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
