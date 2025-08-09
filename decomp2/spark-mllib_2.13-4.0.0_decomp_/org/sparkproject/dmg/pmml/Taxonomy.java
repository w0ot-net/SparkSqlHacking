package org.sparkproject.dmg.pmml;

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
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Taxonomy",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "childParents"}
)
@JsonRootName("Taxonomy")
@JsonPropertyOrder({"name", "extensions", "childParents"})
public class Taxonomy extends PMMLObject implements HasExtensions, HasRequiredName {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @JsonProperty("name")
   private String name;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ChildParent",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ChildParent")
   @CollectionElementType(ChildParent.class)
   private List childParents;
   private static final long serialVersionUID = 67371272L;

   public Taxonomy() {
   }

   @ValueConstructor
   public Taxonomy(@Property("name") String name, @Property("childParents") List childParents) {
      this.name = name;
      this.childParents = childParents;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TAXONOMY_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public Taxonomy setName(@Property("name") String name) {
      this.name = name;
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

   public Taxonomy addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasChildParents() {
      return this.childParents != null && !this.childParents.isEmpty();
   }

   public List requireChildParents() {
      if (this.childParents != null && !this.childParents.isEmpty()) {
         return this.childParents;
      } else {
         throw new MissingElementException(this, PMMLElements.TAXONOMY_CHILDPARENTS);
      }
   }

   public List getChildParents() {
      if (this.childParents == null) {
         this.childParents = new ArrayList();
      }

      return this.childParents;
   }

   public Taxonomy addChildParents(ChildParent... childParents) {
      this.getChildParents().addAll(Arrays.asList(childParents));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasChildParents()) {
            status = PMMLObject.traverse(visitor, this.getChildParents());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
