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
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "Theta",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = ""
)
@JsonRootName("Theta")
@JsonPropertyOrder({"i", "j", "theta"})
@Added(Version.PMML_4_4)
public class Theta extends PMMLObject {
   @XmlAttribute(
      name = "i"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("i")
   private Integer i;
   @XmlAttribute(
      name = "j"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("j")
   private Integer j;
   @XmlAttribute(
      name = "theta"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("theta")
   private Number theta;
   private static final long serialVersionUID = 67371272L;

   public Integer getI() {
      return this.i;
   }

   public Theta setI(@Property("i") Integer i) {
      this.i = i;
      return this;
   }

   public Integer getJ() {
      return this.j;
   }

   public Theta setJ(@Property("j") Integer j) {
      this.j = j;
      return this;
   }

   public Number getTheta() {
      return this.theta;
   }

   public Theta setTheta(@Property("theta") Number theta) {
      this.theta = theta;
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
