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
   name = "NeuralOutput",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "derivedField"}
)
@JsonRootName("NeuralOutput")
@JsonPropertyOrder({"outputNeuron", "extensions", "derivedField"})
public class NeuralOutput extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "outputNeuron",
      required = true
   )
   @JsonProperty("outputNeuron")
   private String outputNeuron;
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

   public NeuralOutput() {
   }

   @ValueConstructor
   public NeuralOutput(@Property("outputNeuron") String outputNeuron, @Property("derivedField") DerivedField derivedField) {
      this.outputNeuron = outputNeuron;
      this.derivedField = derivedField;
   }

   public String requireOutputNeuron() {
      if (this.outputNeuron == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NEURALOUTPUT_OUTPUTNEURON);
      } else {
         return this.outputNeuron;
      }
   }

   public String getOutputNeuron() {
      return this.outputNeuron;
   }

   public NeuralOutput setOutputNeuron(@Property("outputNeuron") String outputNeuron) {
      this.outputNeuron = outputNeuron;
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

   public NeuralOutput addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public DerivedField requireDerivedField() {
      if (this.derivedField == null) {
         throw new MissingElementException(this, PMMLElements.NEURALOUTPUT_DERIVEDFIELD);
      } else {
         return this.derivedField;
      }
   }

   public DerivedField getDerivedField() {
      return this.derivedField;
   }

   public NeuralOutput setDerivedField(@Property("derivedField") DerivedField derivedField) {
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
