package org.sparkproject.dmg.pmml.neural_network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Neuron",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "connections"}
)
@JsonRootName("Neuron")
@JsonPropertyOrder({"id", "bias", "width", "altitude", "extensions", "connections"})
public class Neuron extends NeuralEntity implements HasExtensions {
   @XmlAttribute(
      name = "id",
      required = true
   )
   @JsonProperty("id")
   private String id;
   @XmlAttribute(
      name = "bias"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("bias")
   private Number bias;
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
   @Added(Version.PMML_3_2)
   private Number altitude;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Con",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Con")
   @CollectionElementType(Connection.class)
   private List connections;
   private static final long serialVersionUID = 67371272L;

   public Neuron() {
   }

   @ValueConstructor
   public Neuron(@Property("id") String id, @Property("connections") List connections) {
      this.id = id;
      this.connections = connections;
   }

   public String requireId() {
      if (this.id == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NEURON_ID);
      } else {
         return this.id;
      }
   }

   public String getId() {
      return this.id;
   }

   public Neuron setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public Number getBias() {
      return this.bias;
   }

   public Neuron setBias(@Property("bias") Number bias) {
      this.bias = bias;
      return this;
   }

   public Number getWidth() {
      return this.width;
   }

   public Neuron setWidth(@Property("width") Number width) {
      this.width = width;
      return this;
   }

   public Number getAltitude() {
      return this.altitude;
   }

   public Neuron setAltitude(@Property("altitude") Number altitude) {
      this.altitude = altitude;
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

   public Neuron addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasConnections() {
      return this.connections != null && !this.connections.isEmpty();
   }

   public List requireConnections() {
      if (this.connections != null && !this.connections.isEmpty()) {
         return this.connections;
      } else {
         throw new MissingElementException(this, PMMLElements.NEURON_CONNECTIONS);
      }
   }

   public List getConnections() {
      if (this.connections == null) {
         this.connections = new ArrayList();
      }

      return this.connections;
   }

   public Neuron addConnections(Connection... connections) {
      this.getConnections().addAll(Arrays.asList(connections));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasConnections()) {
            status = PMMLObject.traverse(visitor, this.getConnections());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
