package org.sparkproject.dmg.pmml.time_series;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
   name = "FinalTheta",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"thetas"}
)
@JsonRootName("FinalTheta")
@JsonPropertyOrder({"thetas"})
@Added(Version.PMML_4_4)
public class FinalTheta extends PMMLObject {
   @XmlElement(
      name = "Theta",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Theta")
   @CollectionElementType(Theta.class)
   private List thetas;
   private static final long serialVersionUID = 67371272L;

   public FinalTheta() {
   }

   @ValueConstructor
   public FinalTheta(@Property("thetas") List thetas) {
      this.thetas = thetas;
   }

   public boolean hasThetas() {
      return this.thetas != null && !this.thetas.isEmpty();
   }

   public List requireThetas() {
      if (this.thetas != null && !this.thetas.isEmpty()) {
         return this.thetas;
      } else {
         throw new MissingElementException(this, PMMLElements.FINALTHETA_THETAS);
      }
   }

   public List getThetas() {
      if (this.thetas == null) {
         this.thetas = new ArrayList();
      }

      return this.thetas;
   }

   public FinalTheta addThetas(Theta... thetas) {
      this.getThetas().addAll(Arrays.asList(thetas));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasThetas()) {
            status = PMMLObject.traverse(visitor, this.getThetas());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
