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
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SupportVectorMachine",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "supportVectors", "coefficients"}
)
@JsonRootName("SupportVectorMachine")
@JsonPropertyOrder({"targetCategory", "alternateTargetCategory", "threshold", "extensions", "supportVectors", "coefficients"})
public class SupportVectorMachine extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "targetCategory"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("targetCategory")
   @Added(Version.PMML_3_1)
   private Object targetCategory;
   @XmlAttribute(
      name = "alternateTargetCategory"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("alternateTargetCategory")
   @Added(Version.PMML_4_0)
   private Object alternateTargetCategory;
   @XmlAttribute(
      name = "threshold"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("threshold")
   @Added(Version.PMML_4_0)
   private Number threshold;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "SupportVectors",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("SupportVectors")
   private SupportVectors supportVectors;
   @XmlElement(
      name = "Coefficients",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Coefficients")
   private Coefficients coefficients;
   private static final long serialVersionUID = 67371272L;

   public SupportVectorMachine() {
   }

   @ValueConstructor
   public SupportVectorMachine(@Property("coefficients") Coefficients coefficients) {
      this.coefficients = coefficients;
   }

   public Object requireTargetCategory() {
      if (this.targetCategory == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SUPPORTVECTORMACHINE_TARGETCATEGORY);
      } else {
         return this.targetCategory;
      }
   }

   public Object getTargetCategory() {
      return this.targetCategory;
   }

   public SupportVectorMachine setTargetCategory(@Property("targetCategory") Object targetCategory) {
      this.targetCategory = targetCategory;
      return this;
   }

   public Object requireAlternateTargetCategory() {
      if (this.alternateTargetCategory == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SUPPORTVECTORMACHINE_ALTERNATETARGETCATEGORY);
      } else {
         return this.alternateTargetCategory;
      }
   }

   public Object getAlternateTargetCategory() {
      return this.alternateTargetCategory;
   }

   public SupportVectorMachine setAlternateTargetCategory(@Property("alternateTargetCategory") Object alternateTargetCategory) {
      this.alternateTargetCategory = alternateTargetCategory;
      return this;
   }

   public Number getThreshold() {
      return this.threshold;
   }

   public SupportVectorMachine setThreshold(@Property("threshold") Number threshold) {
      this.threshold = threshold;
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

   public SupportVectorMachine addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public SupportVectors getSupportVectors() {
      return this.supportVectors;
   }

   public SupportVectorMachine setSupportVectors(@Property("supportVectors") SupportVectors supportVectors) {
      this.supportVectors = supportVectors;
      return this;
   }

   public Coefficients requireCoefficients() {
      if (this.coefficients == null) {
         throw new MissingElementException(this, PMMLElements.SUPPORTVECTORMACHINE_COEFFICIENTS);
      } else {
         return this.coefficients;
      }
   }

   public Coefficients getCoefficients() {
      return this.coefficients;
   }

   public SupportVectorMachine setCoefficients(@Property("coefficients") Coefficients coefficients) {
      this.coefficients = coefficients;
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
            status = PMMLObject.traverse(visitor, this.getSupportVectors(), this.getCoefficients());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
