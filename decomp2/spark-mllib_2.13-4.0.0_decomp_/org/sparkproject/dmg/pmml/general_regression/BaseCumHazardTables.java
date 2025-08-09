package org.sparkproject.dmg.pmml.general_regression;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "BaseCumHazardTables",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "baselineStrata", "baselineCells"}
)
@JsonRootName("BaseCumHazardTables")
@JsonPropertyOrder({"maxTime", "extensions", "baselineStrata", "baselineCells"})
@Added(Version.PMML_4_0)
public class BaseCumHazardTables extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "maxTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("maxTime")
   private Number maxTime;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "BaselineStratum",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("BaselineStratum")
   @CollectionElementType(BaselineStratum.class)
   private List baselineStrata;
   @XmlElement(
      name = "BaselineCell",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("BaselineCell")
   @CollectionElementType(BaselineCell.class)
   private List baselineCells;
   private static final long serialVersionUID = 67371272L;

   public BaseCumHazardTables() {
   }

   @ValueConstructor
   public BaseCumHazardTables(@Property("baselineStrata") List baselineStrata, @Property("baselineCells") List baselineCells) {
      this.baselineStrata = baselineStrata;
      this.baselineCells = baselineCells;
   }

   public Number requireMaxTime() {
      if (this.maxTime == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BASECUMHAZARDTABLES_MAXTIME);
      } else {
         return this.maxTime;
      }
   }

   public Number getMaxTime() {
      return this.maxTime;
   }

   public BaseCumHazardTables setMaxTime(@Property("maxTime") Number maxTime) {
      this.maxTime = maxTime;
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

   public BaseCumHazardTables addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasBaselineStrata() {
      return this.baselineStrata != null && !this.baselineStrata.isEmpty();
   }

   public List requireBaselineStrata() {
      if (this.baselineStrata != null && !this.baselineStrata.isEmpty()) {
         return this.baselineStrata;
      } else {
         throw new MissingElementException(this, PMMLElements.BASECUMHAZARDTABLES_BASELINESTRATA);
      }
   }

   public List getBaselineStrata() {
      if (this.baselineStrata == null) {
         this.baselineStrata = new ArrayList();
      }

      return this.baselineStrata;
   }

   public BaseCumHazardTables addBaselineStrata(BaselineStratum... baselineStrata) {
      this.getBaselineStrata().addAll(Arrays.asList(baselineStrata));
      return this;
   }

   public boolean hasBaselineCells() {
      return this.baselineCells != null && !this.baselineCells.isEmpty();
   }

   public List requireBaselineCells() {
      if (this.baselineCells != null && !this.baselineCells.isEmpty()) {
         return this.baselineCells;
      } else {
         throw new MissingElementException(this, PMMLElements.BASECUMHAZARDTABLES_BASELINECELLS);
      }
   }

   public List getBaselineCells() {
      if (this.baselineCells == null) {
         this.baselineCells = new ArrayList();
      }

      return this.baselineCells;
   }

   public BaseCumHazardTables addBaselineCells(BaselineCell... baselineCells) {
      this.getBaselineCells().addAll(Arrays.asList(baselineCells));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasBaselineStrata()) {
            status = PMMLObject.traverse(visitor, this.getBaselineStrata());
         }

         if (status == VisitorAction.CONTINUE && this.hasBaselineCells()) {
            status = PMMLObject.traverse(visitor, this.getBaselineCells());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
