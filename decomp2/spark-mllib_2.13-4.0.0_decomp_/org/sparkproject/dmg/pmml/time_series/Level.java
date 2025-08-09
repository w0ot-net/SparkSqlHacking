package org.sparkproject.dmg.pmml.time_series;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;

@XmlRootElement(
   name = "Level",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = ""
)
@JsonRootName("Level")
@JsonPropertyOrder({"alpha", "quadraticSmoothedValue", "cubicSmoothedValue", "smoothedValue"})
@Added(Version.PMML_4_0)
public class Level extends PMMLObject {
   @XmlAttribute(
      name = "alpha"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("alpha")
   private Number alpha;
   @XmlAttribute(
      name = "quadraticSmoothedValue"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("quadraticSmoothedValue")
   @Removed(Version.PMML_4_1)
   private Number quadraticSmoothedValue;
   @XmlAttribute(
      name = "cubicSmoothedValue"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("cubicSmoothedValue")
   @Removed(Version.PMML_4_1)
   private Number cubicSmoothedValue;
   @XmlAttribute(
      name = "smoothedValue"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("smoothedValue")
   private Number smoothedValue;
   private static final long serialVersionUID = 67371272L;

   public Number getAlpha() {
      return this.alpha;
   }

   public Level setAlpha(@Property("alpha") Number alpha) {
      this.alpha = alpha;
      return this;
   }

   public Number getQuadraticSmoothedValue() {
      return this.quadraticSmoothedValue;
   }

   public Level setQuadraticSmoothedValue(@Property("quadraticSmoothedValue") Number quadraticSmoothedValue) {
      this.quadraticSmoothedValue = quadraticSmoothedValue;
      return this;
   }

   public Number getCubicSmoothedValue() {
      return this.cubicSmoothedValue;
   }

   public Level setCubicSmoothedValue(@Property("cubicSmoothedValue") Number cubicSmoothedValue) {
      this.cubicSmoothedValue = cubicSmoothedValue;
      return this;
   }

   public Number getSmoothedValue() {
      return this.smoothedValue;
   }

   public Level setSmoothedValue(@Property("smoothedValue") Number smoothedValue) {
      this.smoothedValue = smoothedValue;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
