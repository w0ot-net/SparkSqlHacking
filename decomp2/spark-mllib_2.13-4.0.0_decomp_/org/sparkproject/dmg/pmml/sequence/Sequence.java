package org.sparkproject.dmg.pmml.sequence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasRequiredId;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Sequence",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "setReference", "content", "time"}
)
@JsonRootName("Sequence")
@JsonPropertyOrder({"id", "numberOfSets", "occurrence", "support", "extensions", "setReference", "content", "time"})
public class Sequence extends PMMLObject implements HasExtensions, HasRequiredId {
   @XmlAttribute(
      name = "id",
      required = true
   )
   @JsonProperty("id")
   private String id;
   @XmlAttribute(
      name = "numberOfSets"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfSets")
   private Integer numberOfSets;
   @XmlAttribute(
      name = "occurrence"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("occurrence")
   private Integer occurrence;
   @XmlAttribute(
      name = "support"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("support")
   private Number support;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "SetReference",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("SetReference")
   private SetReference setReference;
   @XmlElements({@XmlElement(
   name = "Extension",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Extension.class
), @XmlElement(
   name = "Delimiter",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Delimiter.class
), @XmlElement(
   name = "Time",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Time.class
), @XmlElement(
   name = "SetReference",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SetReference.class
)})
   @JsonProperty("content")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "Extension",
   value = Extension.class
), @Type(
   name = "Delimiter",
   value = Delimiter.class
), @Type(
   name = "Time",
   value = Time.class
), @Type(
   name = "SetReference",
   value = SetReference.class
)})
   @CollectionElementType(PMMLObject.class)
   private List content;
   @XmlElement(
      name = "Time",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Time")
   @Added(Version.PMML_3_1)
   private Time time;
   private static final long serialVersionUID = 67371272L;

   public Sequence() {
   }

   @ValueConstructor
   public Sequence(@Property("id") String id, @Property("setReference") SetReference setReference) {
      this.id = id;
      this.setReference = setReference;
   }

   public String requireId() {
      if (this.id == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCE_ID);
      } else {
         return this.id;
      }
   }

   public String getId() {
      return this.id;
   }

   public Sequence setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public Integer getNumberOfSets() {
      return this.numberOfSets;
   }

   public Sequence setNumberOfSets(@Property("numberOfSets") Integer numberOfSets) {
      this.numberOfSets = numberOfSets;
      return this;
   }

   public Integer getOccurrence() {
      return this.occurrence;
   }

   public Sequence setOccurrence(@Property("occurrence") Integer occurrence) {
      this.occurrence = occurrence;
      return this;
   }

   public Number getSupport() {
      return this.support;
   }

   public Sequence setSupport(@Property("support") Number support) {
      this.support = support;
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

   public Sequence addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public SetReference requireSetReference() {
      if (this.setReference == null) {
         throw new MissingElementException(this, PMMLElements.SEQUENCE_SETREFERENCE);
      } else {
         return this.setReference;
      }
   }

   public SetReference getSetReference() {
      return this.setReference;
   }

   public Sequence setSetReference(@Property("setReference") SetReference setReference) {
      this.setReference = setReference;
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

   public Sequence addContent(PMMLObject... content) {
      this.getContent().addAll(Arrays.asList(content));
      return this;
   }

   public Time getTime() {
      return this.time;
   }

   public Sequence setTime(@Property("time") Time time) {
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getSetReference());
         }

         if (status == VisitorAction.CONTINUE && this.hasContent()) {
            status = PMMLObject.traverse(visitor, this.getContent());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getTime());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
