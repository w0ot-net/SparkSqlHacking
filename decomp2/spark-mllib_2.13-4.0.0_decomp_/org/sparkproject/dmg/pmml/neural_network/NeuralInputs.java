package org.sparkproject.dmg.pmml.neural_network;

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
import java.util.Iterator;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "NeuralInputs",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "neuralInputs"}
)
@JsonRootName("NeuralInputs")
@JsonPropertyOrder({"numberOfInputs", "extensions", "neuralInputs"})
public class NeuralInputs extends PMMLObject implements Iterable, HasExtensions {
   @XmlAttribute(
      name = "numberOfInputs"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfInputs")
   @CollectionSize("neuralInputs")
   private Integer numberOfInputs;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "NeuralInput",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("NeuralInput")
   @CollectionElementType(NeuralInput.class)
   private List neuralInputs;
   private static final long serialVersionUID = 67371272L;

   public NeuralInputs() {
   }

   @ValueConstructor
   public NeuralInputs(@Property("neuralInputs") List neuralInputs) {
      this.neuralInputs = neuralInputs;
   }

   public Integer getNumberOfInputs() {
      return this.numberOfInputs;
   }

   public NeuralInputs setNumberOfInputs(@Property("numberOfInputs") Integer numberOfInputs) {
      this.numberOfInputs = numberOfInputs;
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

   public NeuralInputs addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireNeuralInputs().iterator();
   }

   public boolean hasNeuralInputs() {
      return this.neuralInputs != null && !this.neuralInputs.isEmpty();
   }

   public List requireNeuralInputs() {
      if (this.neuralInputs != null && !this.neuralInputs.isEmpty()) {
         return this.neuralInputs;
      } else {
         throw new MissingElementException(this, PMMLElements.NEURALINPUTS_NEURALINPUTS);
      }
   }

   public List getNeuralInputs() {
      if (this.neuralInputs == null) {
         this.neuralInputs = new ArrayList();
      }

      return this.neuralInputs;
   }

   public NeuralInputs addNeuralInputs(NeuralInput... neuralInputs) {
      this.getNeuralInputs().addAll(Arrays.asList(neuralInputs));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasNeuralInputs()) {
            status = PMMLObject.traverse(visitor, this.getNeuralInputs());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
