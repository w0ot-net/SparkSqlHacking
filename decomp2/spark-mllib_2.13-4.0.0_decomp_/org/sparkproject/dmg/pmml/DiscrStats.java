package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "DiscrStats",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "arrays"}
)
@JsonRootName("DiscrStats")
@JsonPropertyOrder({"modalValue", "extensions", "arrays"})
public class DiscrStats extends PMMLObject implements HasArrays, HasExtensions {
   @XmlAttribute(
      name = "modalValue"
   )
   @JsonProperty("modalValue")
   private String modalValue;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   @CollectionElementType(Array.class)
   private List arrays;
   private static final long serialVersionUID = 67371272L;

   public String getModalValue() {
      return this.modalValue;
   }

   public DiscrStats setModalValue(@Property("modalValue") String modalValue) {
      this.modalValue = modalValue;
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

   public DiscrStats addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasArrays() {
      return this.arrays != null && !this.arrays.isEmpty();
   }

   public List getArrays() {
      if (this.arrays == null) {
         this.arrays = new ArrayList();
      }

      return this.arrays;
   }

   public DiscrStats addArrays(Array... arrays) {
      this.getArrays().addAll(Arrays.asList(arrays));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasArrays()) {
            status = PMMLObject.traverse(visitor, this.getArrays());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
