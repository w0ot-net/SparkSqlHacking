package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "VerificationFields",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "verificationFields"}
)
@JsonRootName("VerificationFields")
@JsonPropertyOrder({"extensions", "verificationFields"})
public class VerificationFields extends PMMLObject implements Iterable, HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "VerificationField",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("VerificationField")
   @CollectionElementType(VerificationField.class)
   private List verificationFields;
   private static final long serialVersionUID = 67371272L;

   public VerificationFields() {
   }

   @ValueConstructor
   public VerificationFields(@Property("verificationFields") List verificationFields) {
      this.verificationFields = verificationFields;
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

   public VerificationFields addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireVerificationFields().iterator();
   }

   public boolean hasVerificationFields() {
      return this.verificationFields != null && !this.verificationFields.isEmpty();
   }

   public List requireVerificationFields() {
      if (this.verificationFields != null && !this.verificationFields.isEmpty()) {
         return this.verificationFields;
      } else {
         throw new MissingElementException(this, PMMLElements.VERIFICATIONFIELDS_VERIFICATIONFIELDS);
      }
   }

   public List getVerificationFields() {
      if (this.verificationFields == null) {
         this.verificationFields = new ArrayList();
      }

      return this.verificationFields;
   }

   public VerificationFields addVerificationFields(VerificationField... verificationFields) {
      this.getVerificationFields().addAll(Arrays.asList(verificationFields));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasVerificationFields()) {
            status = PMMLObject.traverse(visitor, this.getVerificationFields());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
