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
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SupportVectors",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "supportVectors"}
)
@JsonRootName("SupportVectors")
@JsonPropertyOrder({"numberOfSupportVectors", "numberOfAttributes", "extensions", "supportVectors"})
public class SupportVectors extends PMMLObject implements Iterable, HasExtensions {
   @XmlAttribute(
      name = "numberOfSupportVectors"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfSupportVectors")
   @CollectionSize("supportVectors")
   private Integer numberOfSupportVectors;
   @XmlAttribute(
      name = "numberOfAttributes"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfAttributes")
   private Integer numberOfAttributes;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "SupportVector",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("SupportVector")
   @CollectionElementType(SupportVector.class)
   private List supportVectors;
   private static final long serialVersionUID = 67371272L;

   public SupportVectors() {
   }

   @ValueConstructor
   public SupportVectors(@Property("supportVectors") List supportVectors) {
      this.supportVectors = supportVectors;
   }

   public Integer getNumberOfSupportVectors() {
      return this.numberOfSupportVectors;
   }

   public SupportVectors setNumberOfSupportVectors(@Property("numberOfSupportVectors") Integer numberOfSupportVectors) {
      this.numberOfSupportVectors = numberOfSupportVectors;
      return this;
   }

   public Integer getNumberOfAttributes() {
      return this.numberOfAttributes;
   }

   public SupportVectors setNumberOfAttributes(@Property("numberOfAttributes") Integer numberOfAttributes) {
      this.numberOfAttributes = numberOfAttributes;
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

   public SupportVectors addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireSupportVectors().iterator();
   }

   public boolean hasSupportVectors() {
      return this.supportVectors != null && !this.supportVectors.isEmpty();
   }

   public List requireSupportVectors() {
      if (this.supportVectors != null && !this.supportVectors.isEmpty()) {
         return this.supportVectors;
      } else {
         throw new MissingElementException(this, PMMLElements.SUPPORTVECTORS_SUPPORTVECTORS);
      }
   }

   public List getSupportVectors() {
      if (this.supportVectors == null) {
         this.supportVectors = new ArrayList();
      }

      return this.supportVectors;
   }

   public SupportVectors addSupportVectors(SupportVector... supportVectors) {
      this.getSupportVectors().addAll(Arrays.asList(supportVectors));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasSupportVectors()) {
            status = PMMLObject.traverse(visitor, this.getSupportVectors());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
