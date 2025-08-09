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
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "NumericInfo",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "quantiles"}
)
@JsonRootName("NumericInfo")
@JsonPropertyOrder({"minimum", "maximum", "mean", "standardDeviation", "median", "interQuartileRange", "extensions", "quantiles"})
public class NumericInfo extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "minimum"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("minimum")
   private Number minimum;
   @XmlAttribute(
      name = "maximum"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("maximum")
   private Number maximum;
   @XmlAttribute(
      name = "mean"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("mean")
   private Number mean;
   @XmlAttribute(
      name = "standardDeviation"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("standardDeviation")
   private Number standardDeviation;
   @XmlAttribute(
      name = "median"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("median")
   private Number median;
   @XmlAttribute(
      name = "interQuartileRange"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("interQuartileRange")
   private Number interQuartileRange;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Quantile",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Quantile")
   @CollectionElementType(Quantile.class)
   private List quantiles;
   private static final long serialVersionUID = 67371272L;

   public Number getMinimum() {
      return this.minimum;
   }

   public NumericInfo setMinimum(@Property("minimum") Number minimum) {
      this.minimum = minimum;
      return this;
   }

   public Number getMaximum() {
      return this.maximum;
   }

   public NumericInfo setMaximum(@Property("maximum") Number maximum) {
      this.maximum = maximum;
      return this;
   }

   public Number getMean() {
      return this.mean;
   }

   public NumericInfo setMean(@Property("mean") Number mean) {
      this.mean = mean;
      return this;
   }

   public Number getStandardDeviation() {
      return this.standardDeviation;
   }

   public NumericInfo setStandardDeviation(@Property("standardDeviation") Number standardDeviation) {
      this.standardDeviation = standardDeviation;
      return this;
   }

   public Number getMedian() {
      return this.median;
   }

   public NumericInfo setMedian(@Property("median") Number median) {
      this.median = median;
      return this;
   }

   public Number getInterQuartileRange() {
      return this.interQuartileRange;
   }

   public NumericInfo setInterQuartileRange(@Property("interQuartileRange") Number interQuartileRange) {
      this.interQuartileRange = interQuartileRange;
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

   public NumericInfo addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasQuantiles() {
      return this.quantiles != null && !this.quantiles.isEmpty();
   }

   public List getQuantiles() {
      if (this.quantiles == null) {
         this.quantiles = new ArrayList();
      }

      return this.quantiles;
   }

   public NumericInfo addQuantiles(Quantile... quantiles) {
      this.getQuantiles().addAll(Arrays.asList(quantiles));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasQuantiles()) {
            status = PMMLObject.traverse(visitor, this.getQuantiles());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
