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
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "VectorDictionary",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "vectorFields", "vectorInstances"}
)
@JsonRootName("VectorDictionary")
@JsonPropertyOrder({"numberOfVectors", "extensions", "vectorFields", "vectorInstances"})
public class VectorDictionary extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "numberOfVectors"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfVectors")
   @CollectionSize("vectorInstances")
   private Integer numberOfVectors;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "VectorFields",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("VectorFields")
   @Added(Version.PMML_3_1)
   private VectorFields vectorFields;
   @XmlElement(
      name = "VectorInstance",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("VectorInstance")
   @Optional(Version.PMML_3_1)
   @CollectionElementType(VectorInstance.class)
   private List vectorInstances;
   private static final long serialVersionUID = 67371272L;

   public VectorDictionary() {
   }

   @ValueConstructor
   public VectorDictionary(@Property("vectorFields") VectorFields vectorFields) {
      this.vectorFields = vectorFields;
   }

   public Integer getNumberOfVectors() {
      return this.numberOfVectors;
   }

   public VectorDictionary setNumberOfVectors(@Property("numberOfVectors") Integer numberOfVectors) {
      this.numberOfVectors = numberOfVectors;
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

   public VectorDictionary addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VectorFields requireVectorFields() {
      if (this.vectorFields == null) {
         throw new MissingElementException(this, PMMLElements.VECTORDICTIONARY_VECTORFIELDS);
      } else {
         return this.vectorFields;
      }
   }

   public VectorFields getVectorFields() {
      return this.vectorFields;
   }

   public VectorDictionary setVectorFields(@Property("vectorFields") VectorFields vectorFields) {
      this.vectorFields = vectorFields;
      return this;
   }

   public boolean hasVectorInstances() {
      return this.vectorInstances != null && !this.vectorInstances.isEmpty();
   }

   public List getVectorInstances() {
      if (this.vectorInstances == null) {
         this.vectorInstances = new ArrayList();
      }

      return this.vectorInstances;
   }

   public VectorDictionary addVectorInstances(VectorInstance... vectorInstances) {
      this.getVectorInstances().addAll(Arrays.asList(vectorInstances));
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getVectorFields());
         }

         if (status == VisitorAction.CONTINUE && this.hasVectorInstances()) {
            status = PMMLObject.traverse(visitor, this.getVectorInstances());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
