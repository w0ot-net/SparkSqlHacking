package org.sparkproject.dmg.pmml.nearest_neighbor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
   name = "InstanceFields",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "instanceFields"}
)
@JsonRootName("InstanceFields")
@JsonPropertyOrder({"extensions", "instanceFields"})
@Added(Version.PMML_4_1)
public class InstanceFields extends PMMLObject implements Iterable, HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "InstanceField",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("InstanceField")
   @CollectionElementType(InstanceField.class)
   private List instanceFields;
   private static final long serialVersionUID = 67371272L;

   public InstanceFields() {
   }

   @ValueConstructor
   public InstanceFields(@Property("instanceFields") List instanceFields) {
      this.instanceFields = instanceFields;
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

   public InstanceFields addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireInstanceFields().iterator();
   }

   public boolean hasInstanceFields() {
      return this.instanceFields != null && !this.instanceFields.isEmpty();
   }

   public List requireInstanceFields() {
      if (this.instanceFields != null && !this.instanceFields.isEmpty()) {
         return this.instanceFields;
      } else {
         throw new MissingElementException(this, PMMLElements.INSTANCEFIELDS_INSTANCEFIELDS);
      }
   }

   public List getInstanceFields() {
      if (this.instanceFields == null) {
         this.instanceFields = new ArrayList();
      }

      return this.instanceFields;
   }

   public InstanceFields addInstanceFields(InstanceField... instanceFields) {
      this.getInstanceFields().addAll(Arrays.asList(instanceFields));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasInstanceFields()) {
            status = PMMLObject.traverse(visitor, this.getInstanceFields());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
