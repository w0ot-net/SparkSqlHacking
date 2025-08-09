package org.sparkproject.dmg.pmml.sequence;

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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "Time",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Time")
@JsonPropertyOrder({"min", "max", "mean", "standardDeviation", "extensions"})
public class Time extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "min"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("min")
   @Optional(Version.PMML_3_1)
   private Number min;
   @XmlAttribute(
      name = "max"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("max")
   @Optional(Version.PMML_3_1)
   private Number max;
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
   @Added(Version.PMML_3_1)
   private Number standardDeviation;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Number getMin() {
      return this.min;
   }

   public Time setMin(@Property("min") Number min) {
      this.min = min;
      return this;
   }

   public Number getMax() {
      return this.max;
   }

   public Time setMax(@Property("max") Number max) {
      this.max = max;
      return this;
   }

   public Number getMean() {
      return this.mean;
   }

   public Time setMean(@Property("mean") Number mean) {
      this.mean = mean;
      return this;
   }

   public Number getStandardDeviation() {
      return this.standardDeviation;
   }

   public Time setStandardDeviation(@Property("standardDeviation") Number standardDeviation) {
      this.standardDeviation = standardDeviation;
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

   public Time addExtensions(Extension... extensions) {
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
