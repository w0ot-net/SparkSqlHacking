package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CopyConstructor;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlTransient
public abstract class SimpleScoreDistribution extends ScoreDistribution {
   @XmlAttribute(
      name = "value",
      required = true
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("value")
   private Object value;
   @XmlAttribute(
      name = "recordCount"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @Optional(Version.XPMML)
   @JsonProperty("recordCount")
   private Number recordCount;

   public SimpleScoreDistribution() {
   }

   @ValueConstructor
   public SimpleScoreDistribution(@Property("value") Object value, @Property("recordCount") Number recordCount) {
      this.value = value;
      this.recordCount = recordCount;
   }

   @CopyConstructor
   public SimpleScoreDistribution(ScoreDistribution scoreDistribution) {
      this.setValue(scoreDistribution.getValue());
      this.setRecordCount(scoreDistribution.getRecordCount());
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPLEXSCOREDISTRIBUTION_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public SimpleScoreDistribution setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public Number requireRecordCount() {
      if (this.recordCount == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COMPLEXSCOREDISTRIBUTION_RECORDCOUNT);
      } else {
         return this.recordCount;
      }
   }

   public Number getRecordCount() {
      return this.recordCount;
   }

   public SimpleScoreDistribution setRecordCount(@Property("recordCount") Number recordCount) {
      this.recordCount = recordCount;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit((ScoreDistribution)this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
