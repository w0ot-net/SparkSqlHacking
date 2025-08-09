package org.sparkproject.dmg.pmml.general_regression;

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
import org.sparkproject.dmg.pmml.Interval;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Value;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;

@XmlRootElement(
   name = "EventValues",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "values", "intervals"}
)
@JsonRootName("EventValues")
@JsonPropertyOrder({"extensions", "values", "intervals"})
@Added(Version.PMML_4_0)
public class EventValues extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Value",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Value")
   @CollectionElementType(Value.class)
   private List values;
   @XmlElement(
      name = "Interval",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Interval")
   @CollectionElementType(Interval.class)
   private List intervals;
   private static final long serialVersionUID = 67371272L;

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public List getExtensions() {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      return this.extensions;
   }

   public EventValues addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasValues() {
      return this.values != null && !this.values.isEmpty();
   }

   public List getValues() {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      return this.values;
   }

   public EventValues addValues(Value... values) {
      this.getValues().addAll(Arrays.asList(values));
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

   public EventValues addIntervals(Interval... intervals) {
      this.getIntervals().addAll(Arrays.asList(intervals));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasValues()) {
            status = PMMLObject.traverse(visitor, this.getValues());
         }

         if (status == VisitorAction.CONTINUE && this.hasIntervals()) {
            status = PMMLObject.traverse(visitor, this.getIntervals());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
