package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "REAL-SparseArray",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"indices", "entries"}
)
@JsonRootName("REAL-SparseArray")
@JsonPropertyOrder({"n", "defaultValue", "indices", "entries"})
public class RealSparseArray extends SparseArray {
   @XmlAttribute(
      name = "n"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("n")
   private Integer n;
   @XmlAttribute(
      name = "defaultValue"
   )
   @JsonProperty("defaultValue")
   private Double defaultValue;
   @XmlList
   @XmlElement(
      name = "Indices",
      namespace = "http://www.dmg.org/PMML-4_4",
      type = Integer.class
   )
   @JsonProperty("Indices")
   @CollectionElementType(Integer.class)
   private List indices;
   @XmlList
   @XmlElement(
      name = "REAL-Entries",
      namespace = "http://www.dmg.org/PMML-4_4",
      type = Double.class
   )
   @JsonProperty("REAL-Entries")
   @CollectionElementType(Double.class)
   private List entries;
   private static final Double DEFAULT_DEFAULT_VALUE = (double)0.0F;
   private static final long serialVersionUID = 67371272L;

   public Integer getN() {
      return this.n;
   }

   public RealSparseArray setN(@Property("n") Integer n) {
      this.n = n;
      return this;
   }

   public Double getDefaultValue() {
      return this.defaultValue == null ? DEFAULT_DEFAULT_VALUE : this.defaultValue;
   }

   public RealSparseArray setDefaultValue(@Property("defaultValue") Double defaultValue) {
      this.defaultValue = defaultValue;
      return this;
   }

   public boolean hasIndices() {
      return this.indices != null && !this.indices.isEmpty();
   }

   public List getIndices() {
      if (this.indices == null) {
         this.indices = new ArrayList();
      }

      return this.indices;
   }

   public RealSparseArray addIndices(Integer... indices) {
      this.getIndices().addAll(Arrays.asList(indices));
      return this;
   }

   public boolean hasEntries() {
      return this.entries != null && !this.entries.isEmpty();
   }

   public List getEntries() {
      if (this.entries == null) {
         this.entries = new ArrayList();
      }

      return this.entries;
   }

   public RealSparseArray addEntries(Double... entries) {
      this.getEntries().addAll(Arrays.asList(entries));
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
