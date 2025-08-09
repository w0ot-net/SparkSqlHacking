package org.sparkproject.dmg.pmml.bayesian_network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "BayesianNetworkNodes",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "content"}
)
@JsonRootName("BayesianNetworkNodes")
@JsonPropertyOrder({"extensions", "content"})
@Added(Version.PMML_4_3)
public class BayesianNetworkNodes extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElements({@XmlElement(
   name = "DiscreteNode",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = DiscreteNode.class
), @XmlElement(
   name = "ContinuousNode",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = ContinuousNode.class
)})
   @JsonProperty("content")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "DiscreteNode",
   value = DiscreteNode.class
), @Type(
   name = "ContinuousNode",
   value = ContinuousNode.class
)})
   @CollectionElementType(PMMLObject.class)
   private List content;
   private static final long serialVersionUID = 67371272L;

   public BayesianNetworkNodes() {
   }

   @ValueConstructor
   public BayesianNetworkNodes(@Property("content") List content) {
      this.content = content;
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

   public BayesianNetworkNodes addExtensions(Extension... extensions) {
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
         throw new MissingElementException(this, PMMLElements.BAYESIANNETWORKNODES_CONTENT);
      }
   }

   public List getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public BayesianNetworkNodes addContent(PMMLObject... content) {
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
