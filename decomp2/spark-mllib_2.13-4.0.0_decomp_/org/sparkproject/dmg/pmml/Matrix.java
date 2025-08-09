package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Matrix",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"arrays", "matCells"}
)
@JsonRootName("Matrix")
@JsonPropertyOrder({"kind", "nbRows", "nbCols", "diagDefault", "offDiagDefault", "arrays", "matCells"})
public class Matrix extends PMMLObject implements HasArrays {
   @XmlAttribute(
      name = "kind"
   )
   @JsonProperty("kind")
   private Kind kind;
   @XmlAttribute(
      name = "nbRows"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("nbRows")
   private Integer nbRows;
   @XmlAttribute(
      name = "nbCols"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("nbCols")
   private Integer nbCols;
   @XmlAttribute(
      name = "diagDefault"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("diagDefault")
   private Number diagDefault;
   @XmlAttribute(
      name = "offDiagDefault"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("offDiagDefault")
   private Number offDiagDefault;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   @CollectionElementType(Array.class)
   private List arrays;
   @XmlElement(
      name = "MatCell",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MatCell")
   @CollectionElementType(MatCell.class)
   private List matCells;
   private static final long serialVersionUID = 67371272L;

   public Matrix() {
   }

   @ValueConstructor
   public Matrix(@Property("arrays") List arrays, @Property("matCells") List matCells) {
      this.arrays = arrays;
      this.matCells = matCells;
   }

   public Kind getKind() {
      return this.kind == null ? Matrix.Kind.ANY : this.kind;
   }

   public Matrix setKind(@Property("kind") Kind kind) {
      this.kind = kind;
      return this;
   }

   public Integer getNbRows() {
      return this.nbRows;
   }

   public Matrix setNbRows(@Property("nbRows") Integer nbRows) {
      this.nbRows = nbRows;
      return this;
   }

   public Integer getNbCols() {
      return this.nbCols;
   }

   public Matrix setNbCols(@Property("nbCols") Integer nbCols) {
      this.nbCols = nbCols;
      return this;
   }

   public Number getDiagDefault() {
      return this.diagDefault;
   }

   public Matrix setDiagDefault(@Property("diagDefault") Number diagDefault) {
      this.diagDefault = diagDefault;
      return this;
   }

   public Number getOffDiagDefault() {
      return this.offDiagDefault;
   }

   public Matrix setOffDiagDefault(@Property("offDiagDefault") Number offDiagDefault) {
      this.offDiagDefault = offDiagDefault;
      return this;
   }

   public boolean hasArrays() {
      return this.arrays != null && !this.arrays.isEmpty();
   }

   public List requireArrays() {
      if (this.arrays != null && !this.arrays.isEmpty()) {
         return this.arrays;
      } else {
         throw new MissingElementException(this, PMMLElements.MATRIX_ARRAYS);
      }
   }

   public List getArrays() {
      if (this.arrays == null) {
         this.arrays = new ArrayList();
      }

      return this.arrays;
   }

   public Matrix addArrays(Array... arrays) {
      this.getArrays().addAll(Arrays.asList(arrays));
      return this;
   }

   public boolean hasMatCells() {
      return this.matCells != null && !this.matCells.isEmpty();
   }

   public List requireMatCells() {
      if (this.matCells != null && !this.matCells.isEmpty()) {
         return this.matCells;
      } else {
         throw new MissingElementException(this, PMMLElements.MATRIX_MATCELLS);
      }
   }

   public List getMatCells() {
      if (this.matCells == null) {
         this.matCells = new ArrayList();
      }

      return this.matCells;
   }

   public Matrix addMatCells(MatCell... matCells) {
      this.getMatCells().addAll(Arrays.asList(matCells));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasArrays()) {
            status = PMMLObject.traverse(visitor, this.getArrays());
         }

         if (status == VisitorAction.CONTINUE && this.hasMatCells()) {
            status = PMMLObject.traverse(visitor, this.getMatCells());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Kind implements StringValue {
      @XmlEnumValue("diagonal")
      @JsonProperty("diagonal")
      DIAGONAL("diagonal"),
      @XmlEnumValue("symmetric")
      @JsonProperty("symmetric")
      SYMMETRIC("symmetric"),
      @XmlEnumValue("any")
      @JsonProperty("any")
      ANY("any");

      private final String value;

      private Kind(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Kind fromValue(String v) {
         for(Kind c : values()) {
            if (c.value.equals(v)) {
               return c;
            }
         }

         throw new IllegalArgumentException(v);
      }

      public String toString() {
         return this.value();
      }
   }
}
