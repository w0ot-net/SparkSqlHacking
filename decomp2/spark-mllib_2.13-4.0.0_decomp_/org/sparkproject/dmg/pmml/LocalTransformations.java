package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;

@XmlRootElement(
   name = "LocalTransformations",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "derivedFields"}
)
@JsonRootName("LocalTransformations")
@JsonPropertyOrder({"extensions", "derivedFields"})
public class LocalTransformations extends PMMLObject implements HasDerivedFields, HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "DerivedField",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("DerivedField")
   @CollectionElementType(DerivedField.class)
   private List derivedFields;
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

   public LocalTransformations addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasDerivedFields() {
      return this.derivedFields != null && !this.derivedFields.isEmpty();
   }

   public List getDerivedFields() {
      if (this.derivedFields == null) {
         this.derivedFields = new ArrayList();
      }

      return this.derivedFields;
   }

   public LocalTransformations addDerivedFields(DerivedField... derivedFields) {
      this.getDerivedFields().addAll(Arrays.asList(derivedFields));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasDerivedFields()) {
            status = PMMLObject.traverse(visitor, this.getDerivedFields());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
