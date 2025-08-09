package org.sparkproject.dmg.pmml.support_vector_machine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.FieldRef;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.regression.CategoricalPredictor;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "VectorFields",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "content"}
)
@JsonRootName("VectorFields")
@JsonPropertyOrder({"numberOfFields", "extensions", "content"})
@Added(Version.PMML_3_1)
public class VectorFields extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "numberOfFields"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfFields")
   @CollectionSize("content")
   private Integer numberOfFields;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElements({@XmlElement(
   name = "FieldRef",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = FieldRef.class
), @XmlElement(
   name = "CategoricalPredictor",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = CategoricalPredictor.class
)})
   @JsonProperty("content")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "FieldRef",
   value = FieldRef.class
), @Type(
   name = "CategoricalPredictor",
   value = CategoricalPredictor.class
)})
   @CollectionElementType(PMMLObject.class)
   private List content;
   private static final long serialVersionUID = 67371272L;

   public VectorFields() {
   }

   @ValueConstructor
   public VectorFields(@Property("content") List content) {
      this.content = content;
   }

   public Integer getNumberOfFields() {
      return this.numberOfFields;
   }

   public VectorFields setNumberOfFields(@Property("numberOfFields") Integer numberOfFields) {
      this.numberOfFields = numberOfFields;
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

   public VectorFields addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasContent() {
      return this.content != null && !this.content.isEmpty();
   }

   public List requireContent() {
      if (this.content != null && !this.content.isEmpty()) {
         return this.content;
      } else {
         throw new MissingElementException(this, PMMLElements.VECTORFIELDS_CONTENT);
      }
   }

   public List getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public VectorFields addContent(PMMLObject... content) {
      this.getContent().addAll(Arrays.asList(content));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasContent()) {
            status = PMMLObject.traverse(visitor, this.getContent());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
