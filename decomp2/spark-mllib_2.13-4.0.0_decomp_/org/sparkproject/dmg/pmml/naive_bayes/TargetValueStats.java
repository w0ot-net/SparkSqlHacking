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
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TargetValueStats",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "targetValueStats"}
)
@JsonRootName("TargetValueStats")
@JsonPropertyOrder({"extensions", "targetValueStats"})
@Added(Version.PMML_4_2)
public class TargetValueStats extends PMMLObject implements Iterable, HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "TargetValueStat",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("TargetValueStat")
   @CollectionElementType(TargetValueStat.class)
   private List targetValueStats;
   private static final long serialVersionUID = 67371272L;

   public TargetValueStats() {
   }

   @ValueConstructor
   public TargetValueStats(@Property("targetValueStats") List targetValueStats) {
      this.targetValueStats = targetValueStats;
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

   public TargetValueStats addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireTargetValueStats().iterator();
   }

   public boolean hasTargetValueStats() {
      return this.targetValueStats != null && !this.targetValueStats.isEmpty();
   }

   public List requireTargetValueStats() {
      if (this.targetValueStats != null && !this.targetValueStats.isEmpty()) {
         return this.targetValueStats;
      } else {
         throw new MissingElementException(this, PMMLElements.TARGETVALUESTATS_TARGETVALUESTATS);
      }
   }

   public List getTargetValueStats() {
      if (this.targetValueStats == null) {
         this.targetValueStats = new ArrayList();
      }

      return this.targetValueStats;
   }

   public TargetValueStats addTargetValueStats(TargetValueStat... targetValueStats) {
      this.getTargetValueStats().addAll(Arrays.asList(targetValueStats));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasTargetValueStats()) {
            status = PMMLObject.traverse(visitor, this.getTargetValueStats());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
