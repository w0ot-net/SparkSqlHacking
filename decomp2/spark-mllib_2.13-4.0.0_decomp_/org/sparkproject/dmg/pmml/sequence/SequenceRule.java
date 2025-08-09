package org.sparkproject.dmg.pmml.sequence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Entity;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SequenceRule",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"content"}
)
@JsonRootName("SequenceRule")
@JsonPropertyOrder({"id", "numberOfSets", "occurrence", "support", "confidence", "lift", "content"})
public class SequenceRule extends Entity {
   @XmlAttribute(
      name = "id",
      required = true
   )
   @JsonProperty("id")
   private String id;
   @XmlAttribute(
      name = "numberOfSets",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfSets")
   private Integer numberOfSets;
   @XmlAttribute(
      name = "occurrence",
      required = true
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("occurrence")
   private Integer occurrence;
   @XmlAttribute(
      name = "support",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("support")
   private Number support;
   @XmlAttribute(
      name = "confidence",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("confidence")
   private Number confidence;
   @XmlAttribute(
      name = "lift"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("lift")
   @Added(Version.PMML_3_1)
   private Number lift;
   @XmlElementRefs({@XmlElementRef(
   name = "Extension",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Extension.class,
   required = false
), @XmlElementRef(
   name = "AntecedentSequence",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = AntecedentSequence.class,
   required = false
), @XmlElementRef(
   name = "Delimiter",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Delimiter.class,
   required = false
), @XmlElementRef(
   name = "Time",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Time.class,
   required = false
), @XmlElementRef(
   name = "ConsequentSequence",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = ConsequentSequence.class,
   required = false
)})
   @XmlAnyElement(
      lax = true
   )
   @JsonProperty("content")
   @CollectionElementType(Object.class)
   private List content;
   private static final long serialVersionUID = 67371272L;

   public SequenceRule() {
   }

   @ValueConstructor
   public SequenceRule(@Property("id") String id, @Property("numberOfSets") Integer numberOfSets, @Property("occurrence") Integer occurrence, @Property("support") Number support, @Property("confidence") Number confidence) {
      this.id = id;
      this.numberOfSets = numberOfSets;
      this.occurrence = occurrence;
      this.support = support;
      this.confidence = confidence;
   }

   public String requireId() {
      if (this.id == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCERULE_ID);
      } else {
         return this.id;
      }
   }

   public String getId() {
      return this.id;
   }

   public SequenceRule setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public Integer requireNumberOfSets() {
      if (this.numberOfSets == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCERULE_NUMBEROFSETS);
      } else {
         return this.numberOfSets;
      }
   }

   public Integer getNumberOfSets() {
      return this.numberOfSets;
   }

   public SequenceRule setNumberOfSets(@Property("numberOfSets") Integer numberOfSets) {
      this.numberOfSets = numberOfSets;
      return this;
   }

   public Integer requireOccurrence() {
      if (this.occurrence == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCERULE_OCCURRENCE);
      } else {
         return this.occurrence;
      }
   }

   public Integer getOccurrence() {
      return this.occurrence;
   }

   public SequenceRule setOccurrence(@Property("occurrence") Integer occurrence) {
      this.occurrence = occurrence;
      return this;
   }

   public Number requireSupport() {
      if (this.support == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCERULE_SUPPORT);
      } else {
         return this.support;
      }
   }

   public Number getSupport() {
      return this.support;
   }

   public SequenceRule setSupport(@Property("support") Number support) {
      this.support = support;
      return this;
   }

   public Number requireConfidence() {
      if (this.confidence == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEQUENCERULE_CONFIDENCE);
      } else {
         return this.confidence;
      }
   }

   public Number getConfidence() {
      return this.confidence;
   }

   public SequenceRule setConfidence(@Property("confidence") Number confidence) {
      this.confidence = confidence;
      return this;
   }

   public Number getLift() {
      return this.lift;
   }

   public SequenceRule setLift(@Property("lift") Number lift) {
      this.lift = lift;
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

   public SequenceRule addContent(Object... content) {
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
