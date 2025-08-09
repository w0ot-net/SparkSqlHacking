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
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Correlations",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "correlationFields", "correlationValues", "correlationMethods"}
)
@JsonRootName("Correlations")
@JsonPropertyOrder({"extensions", "correlationFields", "correlationValues", "correlationMethods"})
@Added(Version.PMML_4_0)
public class Correlations extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "CorrelationFields",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("CorrelationFields")
   private CorrelationFields correlationFields;
   @XmlElement(
      name = "CorrelationValues",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("CorrelationValues")
   private CorrelationValues correlationValues;
   @XmlElement(
      name = "CorrelationMethods",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("CorrelationMethods")
   private CorrelationMethods correlationMethods;
   private static final long serialVersionUID = 67371272L;

   public Correlations() {
   }

   @ValueConstructor
   public Correlations(@Property("correlationFields") CorrelationFields correlationFields, @Property("correlationValues") CorrelationValues correlationValues) {
      this.correlationFields = correlationFields;
      this.correlationValues = correlationValues;
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

   public Correlations addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public CorrelationFields requireCorrelationFields() {
      if (this.correlationFields == null) {
         throw new MissingElementException(this, PMMLElements.CORRELATIONS_CORRELATIONFIELDS);
      } else {
         return this.correlationFields;
      }
   }

   public CorrelationFields getCorrelationFields() {
      return this.correlationFields;
   }

   public Correlations setCorrelationFields(@Property("correlationFields") CorrelationFields correlationFields) {
      this.correlationFields = correlationFields;
      return this;
   }

   public CorrelationValues requireCorrelationValues() {
      if (this.correlationValues == null) {
         throw new MissingElementException(this, PMMLElements.CORRELATIONS_CORRELATIONVALUES);
      } else {
         return this.correlationValues;
      }
   }

   public CorrelationValues getCorrelationValues() {
      return this.correlationValues;
   }

   public Correlations setCorrelationValues(@Property("correlationValues") CorrelationValues correlationValues) {
      this.correlationValues = correlationValues;
      return this;
   }

   public CorrelationMethods getCorrelationMethods() {
      return this.correlationMethods;
   }

   public Correlations setCorrelationMethods(@Property("correlationMethods") CorrelationMethods correlationMethods) {
      this.correlationMethods = correlationMethods;
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
            status = PMMLObject.traverse(visitor, this.getCorrelationFields(), this.getCorrelationValues(), this.getCorrelationMethods());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
