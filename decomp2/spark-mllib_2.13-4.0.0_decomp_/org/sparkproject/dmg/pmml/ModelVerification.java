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
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ModelVerification",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "verificationFields", "inlineTable"}
)
@JsonRootName("ModelVerification")
@JsonPropertyOrder({"recordCount", "fieldCount", "extensions", "verificationFields", "inlineTable"})
public class ModelVerification extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "recordCount"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("recordCount")
   private Integer recordCount;
   @XmlAttribute(
      name = "fieldCount"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("fieldCount")
   private Integer fieldCount;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "VerificationFields",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("VerificationFields")
   private VerificationFields verificationFields;
   @XmlElement(
      name = "InlineTable",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("InlineTable")
   private InlineTable inlineTable;
   private static final long serialVersionUID = 67371272L;

   public ModelVerification() {
   }

   @ValueConstructor
   public ModelVerification(@Property("verificationFields") VerificationFields verificationFields, @Property("inlineTable") InlineTable inlineTable) {
      this.verificationFields = verificationFields;
      this.inlineTable = inlineTable;
   }

   public Integer getRecordCount() {
      return this.recordCount;
   }

   public ModelVerification setRecordCount(@Property("recordCount") Integer recordCount) {
      this.recordCount = recordCount;
      return this;
   }

   public Integer getFieldCount() {
      return this.fieldCount;
   }

   public ModelVerification setFieldCount(@Property("fieldCount") Integer fieldCount) {
      this.fieldCount = fieldCount;
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

   public ModelVerification addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VerificationFields requireVerificationFields() {
      if (this.verificationFields == null) {
         throw new MissingElementException(this, PMMLElements.MODELVERIFICATION_VERIFICATIONFIELDS);
      } else {
         return this.verificationFields;
      }
   }

   public VerificationFields getVerificationFields() {
      return this.verificationFields;
   }

   public ModelVerification setVerificationFields(@Property("verificationFields") VerificationFields verificationFields) {
      this.verificationFields = verificationFields;
      return this;
   }

   public InlineTable requireInlineTable() {
      if (this.inlineTable == null) {
         throw new MissingElementException(this, PMMLElements.MODELVERIFICATION_INLINETABLE);
      } else {
         return this.inlineTable;
      }
   }

   public InlineTable getInlineTable() {
      return this.inlineTable;
   }

   public ModelVerification setInlineTable(@Property("inlineTable") InlineTable inlineTable) {
      this.inlineTable = inlineTable;
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
            status = PMMLObject.traverse(visitor, this.getVerificationFields(), this.getInlineTable());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
