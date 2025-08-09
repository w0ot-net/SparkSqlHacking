package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ConfusionMatrix",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "classLabels", "matrix"}
)
@JsonRootName("ConfusionMatrix")
@JsonPropertyOrder({"extensions", "classLabels", "matrix"})
@Added(Version.PMML_4_0)
public class ConfusionMatrix extends PMMLObject implements HasExtensions, HasRequiredMatrix {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ClassLabels",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ClassLabels")
   private ClassLabels classLabels;
   @XmlElement(
      name = "Matrix",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Matrix")
   private Matrix matrix;
   private static final long serialVersionUID = 67371272L;

   public ConfusionMatrix() {
   }

   @ValueConstructor
   public ConfusionMatrix(@Property("classLabels") ClassLabels classLabels, @Property("matrix") Matrix matrix) {
      this.classLabels = classLabels;
      this.matrix = matrix;
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

   public ConfusionMatrix addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public ClassLabels requireClassLabels() {
      if (this.classLabels == null) {
         throw new MissingElementException(this, PMMLElements.CONFUSIONMATRIX_CLASSLABELS);
      } else {
         return this.classLabels;
      }
   }

   public ClassLabels getClassLabels() {
      return this.classLabels;
   }

   public ConfusionMatrix setClassLabels(@Property("classLabels") ClassLabels classLabels) {
      this.classLabels = classLabels;
      return this;
   }

   public Matrix requireMatrix() {
      if (this.matrix == null) {
         throw new MissingElementException(this, PMMLElements.CONFUSIONMATRIX_MATRIX);
      } else {
         return this.matrix;
      }
   }

   public Matrix getMatrix() {
      return this.matrix;
   }

   public ConfusionMatrix setMatrix(@Property("matrix") Matrix matrix) {
      this.matrix = matrix;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getClassLabels(), this.getMatrix());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
