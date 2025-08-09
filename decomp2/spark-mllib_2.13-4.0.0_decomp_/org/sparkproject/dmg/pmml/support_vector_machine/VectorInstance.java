package org.sparkproject.dmg.pmml.support_vector_machine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Array;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasRequiredArray;
import org.sparkproject.dmg.pmml.HasRequiredId;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.RealSparseArray;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "VectorInstance",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "realSparseArray", "array"}
)
@JsonRootName("VectorInstance")
@JsonPropertyOrder({"id", "extensions", "realSparseArray", "array"})
public class VectorInstance extends PMMLObject implements HasExtensions, HasRequiredArray, HasRequiredId {
   @XmlAttribute(
      name = "id",
      required = true
   )
   @JsonProperty("id")
   private String id;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "REAL-SparseArray",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("REAL-SparseArray")
   private RealSparseArray realSparseArray;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   private Array array;
   private static final long serialVersionUID = 67371272L;

   public VectorInstance() {
   }

   @ValueConstructor
   public VectorInstance(@Property("id") String id, @Property("realSparseArray") RealSparseArray realSparseArray, @Property("array") Array array) {
      this.id = id;
      this.realSparseArray = realSparseArray;
      this.array = array;
   }

   public String requireId() {
      if (this.id == null) {
         throw new MissingAttributeException(this, PMMLAttributes.VECTORINSTANCE_ID);
      } else {
         return this.id;
      }
   }

   public String getId() {
      return this.id;
   }

   public VectorInstance setId(@Property("id") String id) {
      this.id = id;
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

   public VectorInstance addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public RealSparseArray requireRealSparseArray() {
      if (this.realSparseArray == null) {
         throw new MissingElementException(this, PMMLElements.VECTORINSTANCE_REALSPARSEARRAY);
      } else {
         return this.realSparseArray;
      }
   }

   public RealSparseArray getRealSparseArray() {
      return this.realSparseArray;
   }

   public VectorInstance setRealSparseArray(@Property("realSparseArray") RealSparseArray realSparseArray) {
      this.realSparseArray = realSparseArray;
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.VECTORINSTANCE_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public VectorInstance setArray(@Property("array") Array array) {
      this.array = array;
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
            status = PMMLObject.traverse(visitor, this.getRealSparseArray(), this.getArray());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
