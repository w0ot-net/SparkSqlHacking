package org.sparkproject.dmg.pmml.naive_bayes;

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
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TargetValueCounts",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "targetValueCounts"}
)
@JsonRootName("TargetValueCounts")
@JsonPropertyOrder({"extensions", "targetValueCounts"})
public class TargetValueCounts extends PMMLObject implements Iterable, HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "TargetValueCount",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("TargetValueCount")
   @CollectionElementType(TargetValueCount.class)
   private List targetValueCounts;
   private static final long serialVersionUID = 67371272L;

   public TargetValueCounts() {
   }

   @ValueConstructor
   public TargetValueCounts(@Property("targetValueCounts") List targetValueCounts) {
      this.targetValueCounts = targetValueCounts;
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

   public TargetValueCounts addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireTargetValueCounts().iterator();
   }

   public boolean hasTargetValueCounts() {
      return this.targetValueCounts != null && !this.targetValueCounts.isEmpty();
   }

   public List requireTargetValueCounts() {
      if (this.targetValueCounts != null && !this.targetValueCounts.isEmpty()) {
         return this.targetValueCounts;
      } else {
         throw new MissingElementException(this, PMMLElements.TARGETVALUECOUNTS_TARGETVALUECOUNTS);
      }
   }

   public List getTargetValueCounts() {
      if (this.targetValueCounts == null) {
         this.targetValueCounts = new ArrayList();
      }

      return this.targetValueCounts;
   }

   public TargetValueCounts addTargetValueCounts(TargetValueCount... targetValueCounts) {
      this.getTargetValueCounts().addAll(Arrays.asList(targetValueCounts));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasTargetValueCounts()) {
            status = PMMLObject.traverse(visitor, this.getTargetValueCounts());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
