package org.sparkproject.dmg.pmml.time_series;

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
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "RegressorValues",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "timeSeries", "trendCoefficients", "transferFunctionValues"}
)
@JsonRootName("RegressorValues")
@JsonPropertyOrder({"extensions", "timeSeries", "trendCoefficients", "transferFunctionValues"})
@Added(Version.PMML_4_4)
public class RegressorValues extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "TimeSeries",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TimeSeries")
   private TimeSeries timeSeries;
   @XmlElement(
      name = "TrendCoefficients",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TrendCoefficients")
   private TrendCoefficients trendCoefficients;
   @XmlElement(
      name = "TransferFunctionValues",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TransferFunctionValues")
   private TransferFunctionValues transferFunctionValues;
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

   public RegressorValues addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public TimeSeries getTimeSeries() {
      return this.timeSeries;
   }

   public RegressorValues setTimeSeries(@Property("timeSeries") TimeSeries timeSeries) {
      this.timeSeries = timeSeries;
      return this;
   }

   public TrendCoefficients getTrendCoefficients() {
      return this.trendCoefficients;
   }

   public RegressorValues setTrendCoefficients(@Property("trendCoefficients") TrendCoefficients trendCoefficients) {
      this.trendCoefficients = trendCoefficients;
      return this;
   }

   public TransferFunctionValues getTransferFunctionValues() {
      return this.transferFunctionValues;
   }

   public RegressorValues setTransferFunctionValues(@Property("transferFunctionValues") TransferFunctionValues transferFunctionValues) {
      this.transferFunctionValues = transferFunctionValues;
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
            status = PMMLObject.traverse(visitor, this.getTimeSeries(), this.getTrendCoefficients(), this.getTransferFunctionValues());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
