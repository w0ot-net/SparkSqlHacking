package org.sparkproject.dmg.pmml;

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
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "DiscretizeBin",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "interval"}
)
@JsonRootName("DiscretizeBin")
@JsonPropertyOrder({"binValue", "extensions", "interval"})
public class DiscretizeBin extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "binValue",
      required = true
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("binValue")
   private Object binValue;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Interval",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Interval")
   private Interval interval;
   private static final long serialVersionUID = 67371272L;

   public DiscretizeBin() {
   }

   @ValueConstructor
   public DiscretizeBin(@Property("binValue") Object binValue, @Property("interval") Interval interval) {
      this.binValue = binValue;
      this.interval = interval;
   }

   public Object requireBinValue() {
      if (this.binValue == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DISCRETIZEBIN_BINVALUE);
      } else {
         return this.binValue;
      }
   }

   public Object getBinValue() {
      return this.binValue;
   }

   public DiscretizeBin setBinValue(@Property("binValue") Object binValue) {
      this.binValue = binValue;
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

   public DiscretizeBin addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Interval requireInterval() {
      if (this.interval == null) {
         throw new MissingElementException(this, PMMLElements.DISCRETIZEBIN_INTERVAL);
      } else {
         return this.interval;
      }
   }

   public Interval getInterval() {
      return this.interval;
   }

   public DiscretizeBin setInterval(@Property("interval") Interval interval) {
      this.interval = interval;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getInterval());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
