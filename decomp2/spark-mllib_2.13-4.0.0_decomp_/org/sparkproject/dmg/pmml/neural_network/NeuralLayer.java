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
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "NeuralLayer",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "neurons"}
)
@JsonRootName("NeuralLayer")
@JsonPropertyOrder({"numberOfNeurons", "activationFunction", "threshold", "leakage", "width", "altitude", "normalizationMethod", "extensions", "neurons"})
public class NeuralLayer extends PMMLObject implements HasExtensions, HasActivationFunction, HasNormalizationMethod {
   @XmlAttribute(
      name = "numberOfNeurons"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfNeurons")
   @CollectionSize("neurons")
   private Integer numberOfNeurons;
   @XmlAttribute(
      name = "activationFunction"
   )
   @JsonProperty("activationFunction")
   private NeuralNetwork.ActivationFunction activationFunction;
   @XmlAttribute(
      name = "threshold"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("threshold")
   private Number threshold;
   @XmlAttribute(
      name = "x-leakage"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("x-leakage")
   @Added(Version.XPMML)
   @Since("1.5.2")
   private Number leakage;
   @XmlAttribute(
      name = "width"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("width")
   private Number width;
   @XmlAttribute(
      name = "altitude"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("altitude")
   private Number altitude;
   @XmlAttribute(
      name = "normalizationMethod"
   )
   @JsonProperty("normalizationMethod")
   private NeuralNetwork.NormalizationMethod normalizationMethod;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Neuron",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Neuron")
   @CollectionElementType(Neuron.class)
   private List neurons;
   private static final long serialVersionUID = 67371272L;

   public NeuralLayer() {
   }

   @ValueConstructor
   public NeuralLayer(@Property("neurons") List neurons) {
      this.neurons = neurons;
   }

   public Integer getNumberOfNeurons() {
      return this.numberOfNeurons;
   }

   public NeuralLayer setNumberOfNeurons(@Property("numberOfNeurons") Integer numberOfNeurons) {
      this.numberOfNeurons = numberOfNeurons;
      return this;
   }

   public NeuralNetwork.ActivationFunction getActivationFunction() {
      return this.activationFunction;
   }

   public NeuralLayer setActivationFunction(@Property("activationFunction") NeuralNetwork.ActivationFunction activationFunction) {
      this.activationFunction = activationFunction;
      return this;
   }

   public Number getThreshold() {
      return this.threshold;
   }

   public NeuralLayer setThreshold(@Property("threshold") Number threshold) {
      this.threshold = threshold;
      return this;
   }

   public Number getLeakage() {
      return this.leakage;
   }

   public NeuralLayer setLeakage(@Property("leakage") Number leakage) {
      this.leakage = leakage;
      return this;
   }

   public Number getWidth() {
      return this.width;
   }

   public NeuralLayer setWidth(@Property("width") Number width) {
      this.width = width;
      return this;
   }

   public Number getAltitude() {
      return this.altitude;
   }

   public NeuralLayer setAltitude(@Property("altitude") Number altitude) {
      this.altitude = altitude;
      return this;
   }

   public NeuralNetwork.NormalizationMethod getNormalizationMethod() {
      return this.normalizationMethod;
   }

   public NeuralLayer setNormalizationMethod(@Property("normalizationMethod") NeuralNetwork.NormalizationMethod normalizationMethod) {
      this.normalizationMethod = normalizationMethod;
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

   public NeuralLayer addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasNeurons() {
      return this.neurons != null && !this.neurons.isEmpty();
   }

   public List requireNeurons() {
      if (this.neurons != null && !this.neurons.isEmpty()) {
         return this.neurons;
      } else {
         throw new MissingElementException(this, PMMLElements.NEURALLAYER_NEURONS);
      }
   }

   public List getNeurons() {
      if (this.neurons == null) {
         this.neurons = new ArrayList();
      }

      return this.neurons;
   }

   public NeuralLayer addNeurons(Neuron... neurons) {
      this.getNeurons().addAll(Arrays.asList(neurons));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasNeurons()) {
            status = PMMLObject.traverse(visitor, this.getNeurons());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
