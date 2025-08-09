package org.sparkproject.dmg.pmml.baseline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.AnyDistribution;
import org.sparkproject.dmg.pmml.ContinuousDistribution;
import org.sparkproject.dmg.pmml.FieldRef;
import org.sparkproject.dmg.pmml.GaussianDistribution;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.PoissonDistribution;
import org.sparkproject.dmg.pmml.UniformDistribution;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Baseline",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"continuousDistribution", "countTable", "normalizedCountTable", "fieldRefs"}
)
@JsonRootName("Baseline")
@JsonPropertyOrder({"continuousDistribution", "countTable", "normalizedCountTable", "fieldRefs"})
@Added(Version.PMML_4_1)
public class Baseline extends PMMLObject {
   @XmlElements({@XmlElement(
   name = "AnyDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = AnyDistribution.class
), @XmlElement(
   name = "GaussianDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = GaussianDistribution.class
), @XmlElement(
   name = "PoissonDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = PoissonDistribution.class
), @XmlElement(
   name = "UniformDistribution",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = UniformDistribution.class
)})
   @JsonProperty("ContinuousDistribution")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "AnyDistribution",
   value = AnyDistribution.class
), @Type(
   name = "GaussianDistribution",
   value = GaussianDistribution.class
), @Type(
   name = "PoissonDistribution",
   value = PoissonDistribution.class
), @Type(
   name = "UniformDistribution",
   value = UniformDistribution.class
)})
   private ContinuousDistribution continuousDistribution;
   @XmlElement(
      name = "CountTable",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("CountTable")
   private CountTable countTable;
   @XmlElement(
      name = "NormalizedCountTable",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("NormalizedCountTable")
   private CountTable normalizedCountTable;
   @XmlElement(
      name = "FieldRef",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("FieldRef")
   @CollectionElementType(FieldRef.class)
   private List fieldRefs;
   private static final long serialVersionUID = 67371272L;

   public Baseline() {
   }

   @ValueConstructor
   public Baseline(@Property("continuousDistribution") ContinuousDistribution continuousDistribution, @Property("countTable") CountTable countTable, @Property("normalizedCountTable") CountTable normalizedCountTable, @Property("fieldRefs") List fieldRefs) {
      this.continuousDistribution = continuousDistribution;
      this.countTable = countTable;
      this.normalizedCountTable = normalizedCountTable;
      this.fieldRefs = fieldRefs;
   }

   public ContinuousDistribution requireContinuousDistribution() {
      if (this.continuousDistribution == null) {
         throw new MissingElementException(this, PMMLElements.BASELINE_CONTINUOUSDISTRIBUTION);
      } else {
         return this.continuousDistribution;
      }
   }

   public ContinuousDistribution getContinuousDistribution() {
      return this.continuousDistribution;
   }

   public Baseline setContinuousDistribution(@Property("continuousDistribution") ContinuousDistribution continuousDistribution) {
      this.continuousDistribution = continuousDistribution;
      return this;
   }

   public CountTable requireCountTable() {
      if (this.countTable == null) {
         throw new MissingElementException(this, PMMLElements.BASELINE_COUNTTABLE);
      } else {
         return this.countTable;
      }
   }

   public CountTable getCountTable() {
      return this.countTable;
   }

   public Baseline setCountTable(@Property("countTable") CountTable countTable) {
      this.countTable = countTable;
      return this;
   }

   public CountTable requireNormalizedCountTable() {
      if (this.normalizedCountTable == null) {
         throw new MissingElementException(this, PMMLElements.BASELINE_NORMALIZEDCOUNTTABLE);
      } else {
         return this.normalizedCountTable;
      }
   }

   public CountTable getNormalizedCountTable() {
      return this.normalizedCountTable;
   }

   public Baseline setNormalizedCountTable(@Property("normalizedCountTable") CountTable normalizedCountTable) {
      this.normalizedCountTable = normalizedCountTable;
      return this;
   }

   public boolean hasFieldRefs() {
      return this.fieldRefs != null && !this.fieldRefs.isEmpty();
   }

   public List requireFieldRefs() {
      if (this.fieldRefs != null && !this.fieldRefs.isEmpty()) {
         return this.fieldRefs;
      } else {
         throw new MissingElementException(this, PMMLElements.BASELINE_FIELDREFS);
      }
   }

   public List getFieldRefs() {
      if (this.fieldRefs == null) {
         this.fieldRefs = new ArrayList();
      }

      return this.fieldRefs;
   }

   public Baseline addFieldRefs(FieldRef... fieldRefs) {
      this.getFieldRefs().addAll(Arrays.asList(fieldRefs));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getContinuousDistribution(), this.getCountTable(), this.getNormalizedCountTable());
         }

         if (status == VisitorAction.CONTINUE && this.hasFieldRefs()) {
            status = PMMLObject.traverse(visitor, this.getFieldRefs());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
