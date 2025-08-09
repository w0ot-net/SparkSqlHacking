package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "Extension",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"content"}
)
@JsonRootName("Extension")
@JsonPropertyOrder({"extender", "name", "value", "content"})
public class Extension extends PMMLObject implements HasMixedContent {
   @XmlAttribute(
      name = "extender"
   )
   @JsonProperty("extender")
   private String extender;
   @XmlAttribute(
      name = "name"
   )
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "value"
   )
   @JsonProperty("value")
   private String value;
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   @JsonProperty("content")
   @CollectionElementType(Object.class)
   private List content;
   private static final long serialVersionUID = 67371272L;

   public String getExtender() {
      return this.extender;
   }

   public Extension setExtender(@Property("extender") String extender) {
      this.extender = extender;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public Extension setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public String getValue() {
      return this.value;
   }

   public Extension setValue(@Property("value") String value) {
      this.value = value;
      return this;
   }

   public boolean hasContent() {
      return this.content != null && !this.content.isEmpty();
   }

   public List getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public Extension addContent(Object... content) {
      this.getContent().addAll(Arrays.asList(content));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasContent()) {
            status = PMMLObject.traverseMixed(visitor, this.getContent());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
