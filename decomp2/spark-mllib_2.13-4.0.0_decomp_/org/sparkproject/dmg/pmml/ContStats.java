package org.sparkproject.dmg.pmml;

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
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "ContStats",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "intervals", "arrays"}
)
@JsonRootName("ContStats")
@JsonPropertyOrder({"totalValuesSum", "totalSquaresSum", "extensions", "intervals", "arrays"})
public class ContStats extends PMMLObject implements HasArrays, HasExtensions {
   @XmlAttribute(
      name = "totalValuesSum"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("totalValuesSum")
   private Number totalValuesSum;
   @XmlAttribute(
      name = "totalSquaresSum"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("totalSquaresSum")
   private Number totalSquaresSum;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Interval",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Interval")
   @CollectionElementType(Interval.class)
   private List intervals;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   @CollectionElementType(Array.class)
   private List arrays;
   private static final long serialVersionUID = 67371272L;

   public Number getTotalValuesSum() {
      return this.totalValuesSum;
   }

   public ContStats setTotalValuesSum(@Property("totalValuesSum") Number totalValuesSum) {
      this.totalValuesSum = totalValuesSum;
      return this;
   }

   public Number getTotalSquaresSum() {
      return this.totalSquaresSum;
   }

   public ContStats setTotalSquaresSum(@Property("totalSquaresSum") Number totalSquaresSum) {
      this.totalSquaresSum = totalSquaresSum;
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

   public ContStats addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasIntervals() {
      return this.intervals != null && !this.intervals.isEmpty();
   }

   public List getIntervals() {
      if (this.intervals == null) {
         this.intervals = new ArrayList();
      }

      return this.intervals;
   }

   public ContStats addIntervals(Interval... intervals) {
      this.getIntervals().addAll(Arrays.asList(intervals));
      return this;
   }

   public boolean hasArrays() {
      return this.arrays != null && !this.arrays.isEmpty();
   }

   public List getArrays() {
      if (this.arrays == null) {
         this.arrays = new ArrayList();
      }

      return this.arrays;
   }

   public ContStats addArrays(Array... arrays) {
      this.getArrays().addAll(Arrays.asList(arrays));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasIntervals()) {
            status = PMMLObject.traverse(visitor, this.getIntervals());
         }

         if (status == VisitorAction.CONTINUE && this.hasArrays()) {
            status = PMMLObject.traverse(visitor, this.getArrays());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
