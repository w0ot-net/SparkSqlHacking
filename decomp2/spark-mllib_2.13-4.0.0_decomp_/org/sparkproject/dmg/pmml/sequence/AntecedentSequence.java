package org.sparkproject.dmg.pmml.sequence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
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
   name = "AntecedentSequence",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "sequenceReference", "time"}
)
@JsonRootName("AntecedentSequence")
@JsonPropertyOrder({"extensions", "sequenceReference", "time"})
public class AntecedentSequence extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "SequenceReference",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("SequenceReference")
   private SequenceReference sequenceReference;
   @XmlElement(
      name = "Time",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Time")
   private Time time;
   private static final long serialVersionUID = 67371272L;

   public AntecedentSequence() {
   }

   @ValueConstructor
   public AntecedentSequence(@Property("sequenceReference") SequenceReference sequenceReference) {
      this.sequenceReference = sequenceReference;
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

   public AntecedentSequence addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public SequenceReference requireSequenceReference() {
      if (this.sequenceReference == null) {
         throw new MissingElementException(this, PMMLElements.ANTECEDENTSEQUENCE_SEQUENCEREFERENCE);
      } else {
         return this.sequenceReference;
      }
   }

   public SequenceReference getSequenceReference() {
      return this.sequenceReference;
   }

   public AntecedentSequence setSequenceReference(@Property("sequenceReference") SequenceReference sequenceReference) {
      this.sequenceReference = sequenceReference;
      return this;
   }

   public Time getTime() {
      return this.time;
   }

   public AntecedentSequence setTime(@Property("time") Time time) {
      this.time = time;
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
            status = PMMLObject.traverse(visitor, this.getSequenceReference(), this.getTime());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
