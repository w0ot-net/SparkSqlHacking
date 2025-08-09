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
   name = "NeuralOutputs",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "neuralOutputs"}
)
@JsonRootName("NeuralOutputs")
@JsonPropertyOrder({"numberOfOutputs", "extensions", "neuralOutputs"})
public class NeuralOutputs extends PMMLObject implements Iterable, HasExtensions {
   @XmlAttribute(
      name = "numberOfOutputs"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfOutputs")
   @CollectionSize("neuralOutputs")
   private Integer numberOfOutputs;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "NeuralOutput",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("NeuralOutput")
   @CollectionElementType(NeuralOutput.class)
   private List neuralOutputs;
   private static final long serialVersionUID = 67371272L;

   public NeuralOutputs() {
   }

   @ValueConstructor
   public NeuralOutputs(@Property("neuralOutputs") List neuralOutputs) {
      this.neuralOutputs = neuralOutputs;
   }

   public Integer getNumberOfOutputs() {
      return this.numberOfOutputs;
   }

   public NeuralOutputs setNumberOfOutputs(@Property("numberOfOutputs") Integer numberOfOutputs) {
      this.numberOfOutputs = numberOfOutputs;
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

   public NeuralOutputs addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireNeuralOutputs().iterator();
   }

   public boolean hasNeuralOutputs() {
      return this.neuralOutputs != null && !this.neuralOutputs.isEmpty();
   }

   public List requireNeuralOutputs() {
      if (this.neuralOutputs != null && !this.neuralOutputs.isEmpty()) {
         return this.neuralOutputs;
      } else {
         throw new MissingElementException(this, PMMLElements.NEURALOUTPUTS_NEURALOUTPUTS);
      }
   }

   public List getNeuralOutputs() {
      if (this.neuralOutputs == null) {
         this.neuralOutputs = new ArrayList();
      }

      return this.neuralOutputs;
   }

   public NeuralOutputs addNeuralOutputs(NeuralOutput... neuralOutputs) {
      this.getNeuralOutputs().addAll(Arrays.asList(neuralOutputs));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasNeuralOutputs()) {
            status = PMMLObject.traverse(visitor, this.getNeuralOutputs());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
