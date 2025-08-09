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
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Output",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "outputFields"}
)
@JsonRootName("Output")
@JsonPropertyOrder({"extensions", "outputFields"})
public class Output extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "OutputField",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("OutputField")
   @CollectionElementType(OutputField.class)
   private List outputFields;
   private static final long serialVersionUID = 67371272L;

   public Output() {
   }

   @ValueConstructor
   public Output(@Property("outputFields") List outputFields) {
      this.outputFields = outputFields;
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

   public Output addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasOutputFields() {
      return this.outputFields != null && !this.outputFields.isEmpty();
   }

   public List requireOutputFields() {
      if (this.outputFields != null && !this.outputFields.isEmpty()) {
         return this.outputFields;
      } else {
         throw new MissingElementException(this, PMMLElements.OUTPUT_OUTPUTFIELDS);
      }
   }

   public List getOutputFields() {
      if (this.outputFields == null) {
         this.outputFields = new ArrayList();
      }

      return this.outputFields;
   }

   public Output addOutputFields(OutputField... outputFields) {
      this.getOutputFields().addAll(Arrays.asList(outputFields));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasOutputFields()) {
            status = PMMLObject.traverse(visitor, this.getOutputFields());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
