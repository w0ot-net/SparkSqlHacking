package org.sparkproject.dmg.pmml.bayesian_network;

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
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.DerivedField;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "DiscreteNode",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "derivedFields", "content"}
)
@JsonRootName("DiscreteNode")
@JsonPropertyOrder({"name", "count", "extensions", "derivedFields", "content"})
@Added(Version.PMML_4_3)
public class DiscreteNode extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "count"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("count")
   private Number count;
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
   @XmlElements({@XmlElement(
   name = "DiscreteConditionalProbability",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = DiscreteConditionalProbability.class
), @XmlElement(
   name = "ValueProbability",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = ValueProbability.class
)})
   @JsonProperty("content")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "DiscreteConditionalProbability",
   value = DiscreteConditionalProbability.class
), @Type(
   name = "ValueProbability",
   value = ValueProbability.class
)})
   @CollectionElementType(PMMLObject.class)
   private List content;
   private static final long serialVersionUID = 67371272L;

   public DiscreteNode() {
   }

   @ValueConstructor
   public DiscreteNode(@Property("name") String name, @Property("content") List content) {
      this.name = name;
      this.content = content;
   }

   @AlternateValueConstructor
   public DiscreteNode(Field nameField, List content) {
      this(nameField != null ? nameField.requireName() : null, content);
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DISCRETENODE_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public DiscreteNode setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public Number getCount() {
      return this.count;
   }

   public DiscreteNode setCount(@Property("count") Number count) {
      this.count = count;
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

   public DiscreteNode addExtensions(Extension... extensions) {
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

   public DiscreteNode addDerivedFields(DerivedField... derivedFields) {
      this.getDerivedFields().addAll(Arrays.asList(derivedFields));
      return this;
   }

   public boolean hasContent() {
      return this.content != null && !this.content.isEmpty();
   }

   public List requireContent() {
      if (this.content != null && !this.content.isEmpty()) {
         return this.content;
      } else {
         throw new MissingElementException(this, PMMLElements.DISCRETENODE_CONTENT);
      }
   }

   public List getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public DiscreteNode addContent(PMMLObject... content) {
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

         if (status == VisitorAction.CONTINUE && this.hasDerivedFields()) {
            status = PMMLObject.traverse(visitor, this.getDerivedFields());
         }

         if (status == VisitorAction.CONTINUE && this.hasContent()) {
            status = PMMLObject.traverse(visitor, this.getContent());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
