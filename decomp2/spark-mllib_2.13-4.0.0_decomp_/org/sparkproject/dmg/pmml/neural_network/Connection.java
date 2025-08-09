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
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Con",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Con")
@JsonPropertyOrder({"from", "weight", "extensions"})
public class Connection extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "from",
      required = true
   )
   @JsonProperty("from")
   private String from;
   @XmlAttribute(
      name = "weight",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("weight")
   private Number weight;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Connection() {
   }

   @ValueConstructor
   public Connection(@Property("from") String from, @Property("weight") Number weight) {
      this.from = from;
      this.weight = weight;
   }

   public String requireFrom() {
      if (this.from == null) {
         throw new MissingAttributeException(this, PMMLAttributes.CONNECTION_FROM);
      } else {
         return this.from;
      }
   }

   public String getFrom() {
      return this.from;
   }

   public Connection setFrom(@Property("from") String from) {
      this.from = from;
      return this;
   }

   public Number requireWeight() {
      if (this.weight == null) {
         throw new MissingAttributeException(this, PMMLAttributes.CONNECTION_WEIGHT);
      } else {
         return this.weight;
      }
   }

   public Number getWeight() {
      return this.weight;
   }

   public Connection setWeight(@Property("weight") Number weight) {
      this.weight = weight;
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

   public Connection addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
