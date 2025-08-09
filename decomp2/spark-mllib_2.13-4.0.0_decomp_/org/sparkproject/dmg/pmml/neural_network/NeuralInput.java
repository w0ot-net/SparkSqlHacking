package org.sparkproject.dmg.pmml.neural_network;

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
import org.sparkproject.dmg.pmml.DerivedField;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "NeuralInput",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "derivedField"}
)
@JsonRootName("NeuralInput")
@JsonPropertyOrder({"id", "extensions", "derivedField"})
public class NeuralInput extends NeuralEntity implements HasExtensions {
   @XmlAttribute(
      name = "id",
      required = true
   )
   @JsonProperty("id")
   private String id;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "DerivedField",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("DerivedField")
   private DerivedField derivedField;
   private static final long serialVersionUID = 67371272L;

   public NeuralInput() {
   }

   @ValueConstructor
   public NeuralInput(@Property("id") String id, @Property("derivedField") DerivedField derivedField) {
      this.id = id;
      this.derivedField = derivedField;
   }

   public String requireId() {
      if (this.id == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NEURALINPUT_ID);
      } else {
         return this.id;
      }
   }

   public String getId() {
      return this.id;
   }

   public NeuralInput setId(@Property("id") String id) {
      this.id = id;
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

   public NeuralInput addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public DerivedField requireDerivedField() {
      if (this.derivedField == null) {
         throw new MissingElementException(this, PMMLElements.NEURALINPUT_DERIVEDFIELD);
      } else {
         return this.derivedField;
      }
   }

   public DerivedField getDerivedField() {
      return this.derivedField;
   }

   public NeuralInput setDerivedField(@Property("derivedField") DerivedField derivedField) {
      this.derivedField = derivedField;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getDerivedField());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
