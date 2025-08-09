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
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ROC",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "rocGraph"}
)
@JsonRootName("ROC")
@JsonPropertyOrder({"positiveTargetFieldValue", "positiveTargetFieldDisplayValue", "negativeTargetFieldValue", "negativeTargetFieldDisplayValue", "extensions", "rocGraph"})
@Added(Version.PMML_4_0)
public class ROC extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "positiveTargetFieldValue",
      required = true
   )
   @JsonProperty("positiveTargetFieldValue")
   private String positiveTargetFieldValue;
   @XmlAttribute(
      name = "positiveTargetFieldDisplayValue"
   )
   @JsonProperty("positiveTargetFieldDisplayValue")
   private String positiveTargetFieldDisplayValue;
   @XmlAttribute(
      name = "negativeTargetFieldValue"
   )
   @JsonProperty("negativeTargetFieldValue")
   private String negativeTargetFieldValue;
   @XmlAttribute(
      name = "negativeTargetFieldDisplayValue"
   )
   @JsonProperty("negativeTargetFieldDisplayValue")
   private String negativeTargetFieldDisplayValue;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ROCGraph",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ROCGraph")
   private ROCGraph rocGraph;
   private static final long serialVersionUID = 67371272L;

   public ROC() {
   }

   @ValueConstructor
   public ROC(@Property("positiveTargetFieldValue") String positiveTargetFieldValue, @Property("rocGraph") ROCGraph rocGraph) {
      this.positiveTargetFieldValue = positiveTargetFieldValue;
      this.rocGraph = rocGraph;
   }

   public String requirePositiveTargetFieldValue() {
      if (this.positiveTargetFieldValue == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ROC_POSITIVETARGETFIELDVALUE);
      } else {
         return this.positiveTargetFieldValue;
      }
   }

   public String getPositiveTargetFieldValue() {
      return this.positiveTargetFieldValue;
   }

   public ROC setPositiveTargetFieldValue(@Property("positiveTargetFieldValue") String positiveTargetFieldValue) {
      this.positiveTargetFieldValue = positiveTargetFieldValue;
      return this;
   }

   public String getPositiveTargetFieldDisplayValue() {
      return this.positiveTargetFieldDisplayValue;
   }

   public ROC setPositiveTargetFieldDisplayValue(@Property("positiveTargetFieldDisplayValue") String positiveTargetFieldDisplayValue) {
      this.positiveTargetFieldDisplayValue = positiveTargetFieldDisplayValue;
      return this;
   }

   public String getNegativeTargetFieldValue() {
      return this.negativeTargetFieldValue;
   }

   public ROC setNegativeTargetFieldValue(@Property("negativeTargetFieldValue") String negativeTargetFieldValue) {
      this.negativeTargetFieldValue = negativeTargetFieldValue;
      return this;
   }

   public String getNegativeTargetFieldDisplayValue() {
      return this.negativeTargetFieldDisplayValue;
   }

   public ROC setNegativeTargetFieldDisplayValue(@Property("negativeTargetFieldDisplayValue") String negativeTargetFieldDisplayValue) {
      this.negativeTargetFieldDisplayValue = negativeTargetFieldDisplayValue;
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

   public ROC addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public ROCGraph requireROCGraph() {
      if (this.rocGraph == null) {
         throw new MissingElementException(this, PMMLElements.ROC_ROCGRAPH);
      } else {
         return this.rocGraph;
      }
   }

   public ROCGraph getROCGraph() {
      return this.rocGraph;
   }

   public ROC setROCGraph(@Property("rocGraph") ROCGraph rocGraph) {
      this.rocGraph = rocGraph;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getROCGraph());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
