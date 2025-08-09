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
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasFieldReference;
import org.sparkproject.dmg.pmml.HasMatrix;
import org.sparkproject.dmg.pmml.Matrix;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Predictor",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "categories", "matrix"}
)
@JsonRootName("Predictor")
@JsonPropertyOrder({"field", "contrastMatrixType", "extensions", "categories", "matrix"})
public class Predictor extends PMMLObject implements HasExtensions, HasFieldReference, HasMatrix {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("name")
   private String field;
   @XmlAttribute(
      name = "contrastMatrixType"
   )
   @JsonProperty("contrastMatrixType")
   @Added(Version.PMML_4_0)
   private String contrastMatrixType;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Categories",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Categories")
   @Added(Version.PMML_4_1)
   private Categories categories;
   @XmlElement(
      name = "Matrix",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Matrix")
   @Added(Version.PMML_4_0)
   private Matrix matrix;
   private static final long serialVersionUID = 67371272L;

   public Predictor() {
   }

   @ValueConstructor
   public Predictor(@Property("field") String field) {
      this.field = field;
   }

   @AlternateValueConstructor
   public Predictor(Field field) {
      this(field != null ? field.requireName() : null);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PREDICTOR_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public Predictor setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public String getContrastMatrixType() {
      return this.contrastMatrixType;
   }

   public Predictor setContrastMatrixType(@Property("contrastMatrixType") String contrastMatrixType) {
      this.contrastMatrixType = contrastMatrixType;
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

   public Predictor addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Categories getCategories() {
      return this.categories;
   }

   public Predictor setCategories(@Property("categories") Categories categories) {
      this.categories = categories;
      return this;
   }

   public Matrix getMatrix() {
      return this.matrix;
   }

   public Predictor setMatrix(@Property("matrix") Matrix matrix) {
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
            status = PMMLObject.traverse(visitor, this.getCategories(), this.getMatrix());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
