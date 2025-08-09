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
import org.sparkproject.dmg.pmml.adapters.PercentageNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Quantile",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Quantile")
@JsonPropertyOrder({"quantileLimit", "quantileValue", "extensions"})
public class Quantile extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "quantileLimit",
      required = true
   )
   @XmlJavaTypeAdapter(PercentageNumberAdapter.class)
   @JsonProperty("quantileLimit")
   private Number quantileLimit;
   @XmlAttribute(
      name = "quantileValue",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("quantileValue")
   private Number quantileValue;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Quantile() {
   }

   @ValueConstructor
   public Quantile(@Property("quantileLimit") Number quantileLimit, @Property("quantileValue") Number quantileValue) {
      this.quantileLimit = quantileLimit;
      this.quantileValue = quantileValue;
   }

   public Number requireQuantileLimit() {
      if (this.quantileLimit == null) {
         throw new MissingAttributeException(this, PMMLAttributes.QUANTILE_QUANTILELIMIT);
      } else {
         return this.quantileLimit;
      }
   }

   public Number getQuantileLimit() {
      return this.quantileLimit;
   }

   public Quantile setQuantileLimit(@Property("quantileLimit") Number quantileLimit) {
      this.quantileLimit = quantileLimit;
      return this;
   }

   public Number requireQuantileValue() {
      if (this.quantileValue == null) {
         throw new MissingAttributeException(this, PMMLAttributes.QUANTILE_QUANTILEVALUE);
      } else {
         return this.quantileValue;
      }
   }

   public Number getQuantileValue() {
      return this.quantileValue;
   }

   public Quantile setQuantileValue(@Property("quantileValue") Number quantileValue) {
      this.quantileValue = quantileValue;
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

   public Quantile addExtensions(Extension... extensions) {
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
