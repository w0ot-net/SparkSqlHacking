package org.sparkproject.dmg.pmml.time_series;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.sparkproject.dmg.pmml.HasRequiredMatrix;
import org.sparkproject.dmg.pmml.Matrix;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "FinalOmega",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"matrix"}
)
@JsonRootName("FinalOmega")
@JsonPropertyOrder({"matrix"})
@Added(Version.PMML_4_4)
public class FinalOmega extends PMMLObject implements HasRequiredMatrix {
   @XmlElement(
      name = "Matrix",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Matrix")
   private Matrix matrix;
   private static final long serialVersionUID = 67371272L;

   public FinalOmega() {
   }

   @ValueConstructor
   public FinalOmega(@Property("matrix") Matrix matrix) {
      this.matrix = matrix;
   }

   public Matrix requireMatrix() {
      if (this.matrix == null) {
         throw new MissingElementException(this, PMMLElements.FINALOMEGA_MATRIX);
      } else {
         return this.matrix;
      }
   }

   public Matrix getMatrix() {
      return this.matrix;
   }

   public FinalOmega setMatrix(@Property("matrix") Matrix matrix) {
      this.matrix = matrix;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getMatrix());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
