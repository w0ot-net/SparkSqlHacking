package org.sparkproject.dmg.pmml.support_vector_machine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Coefficients",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "coefficients"}
)
@JsonRootName("Coefficients")
@JsonPropertyOrder({"numberOfCoefficients", "absoluteValue", "extensions", "coefficients"})
public class Coefficients extends PMMLObject implements Iterable, HasExtensions {
   @XmlAttribute(
      name = "numberOfCoefficients"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfCoefficients")
   @CollectionSize("coefficients")
   private Integer numberOfCoefficients;
   @XmlAttribute(
      name = "absoluteValue"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("absoluteValue")
   private Number absoluteValue;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Coefficient",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Coefficient")
   @CollectionElementType(Coefficient.class)
   private List coefficients;
   private static final Number DEFAULT_ABSOLUTE_VALUE = (new RealNumberAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public Coefficients() {
   }

   @ValueConstructor
   public Coefficients(@Property("coefficients") List coefficients) {
      this.coefficients = coefficients;
   }

   public Integer getNumberOfCoefficients() {
      return this.numberOfCoefficients;
   }

   public Coefficients setNumberOfCoefficients(@Property("numberOfCoefficients") Integer numberOfCoefficients) {
      this.numberOfCoefficients = numberOfCoefficients;
      return this;
   }

   public Number getAbsoluteValue() {
      return this.absoluteValue == null ? DEFAULT_ABSOLUTE_VALUE : this.absoluteValue;
   }

   public Coefficients setAbsoluteValue(@Property("absoluteValue") Number absoluteValue) {
      this.absoluteValue = absoluteValue;
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

   public Coefficients addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireCoefficients().iterator();
   }

   public boolean hasCoefficients() {
      return this.coefficients != null && !this.coefficients.isEmpty();
   }

   public List requireCoefficients() {
      if (this.coefficients != null && !this.coefficients.isEmpty()) {
         return this.coefficients;
      } else {
         throw new MissingElementException(this, PMMLElements.COEFFICIENTS_COEFFICIENTS);
      }
   }

   public List getCoefficients() {
      if (this.coefficients == null) {
         this.coefficients = new ArrayList();
      }

      return this.coefficients;
   }

   public Coefficients addCoefficients(Coefficient... coefficients) {
      this.getCoefficients().addAll(Arrays.asList(coefficients));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasCoefficients()) {
            status = PMMLObject.traverse(visitor, this.getCoefficients());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
