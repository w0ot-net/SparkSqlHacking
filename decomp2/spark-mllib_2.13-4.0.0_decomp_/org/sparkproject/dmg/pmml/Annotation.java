package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;

@XmlRootElement(
   name = "Annotation",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"content"}
)
@JsonRootName("Annotation")
@JsonPropertyOrder({"content"})
public class Annotation extends PMMLObject implements HasMixedContent {
   @XmlElementRef(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4",
      type = Extension.class,
      required = false
   )
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   @JsonProperty("content")
   @CollectionElementType(Object.class)
   private List content;
   private static final long serialVersionUID = 67371272L;

   public boolean hasContent() {
      return this.content != null && !this.content.isEmpty();
   }

   public List getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public Annotation addContent(Object... content) {
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
