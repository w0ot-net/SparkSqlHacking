package org.sparkproject.dmg.pmml.clustering;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Array;
import org.sparkproject.dmg.pmml.Entity;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasName;
import org.sparkproject.dmg.pmml.HasRequiredArray;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Partition;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Cluster",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "kohonenMap", "array", "partition", "covariances"}
)
@JsonRootName("Cluster")
@JsonPropertyOrder({"id", "name", "size", "extensions", "kohonenMap", "array", "partition", "covariances"})
public class Cluster extends Entity implements HasExtensions, HasName, HasRequiredArray {
   @XmlAttribute(
      name = "id"
   )
   @JsonProperty("id")
   @Added(Version.PMML_4_1)
   private String id;
   @XmlAttribute(
      name = "name"
   )
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "size"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("size")
   private Integer size;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "KohonenMap",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("KohonenMap")
   private KohonenMap kohonenMap;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   private Array array;
   @XmlElement(
      name = "Partition",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Partition")
   private Partition partition;
   @XmlElement(
      name = "Covariances",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Covariances")
   private Covariances covariances;
   private static final long serialVersionUID = 67371272L;

   public Cluster() {
   }

   @ValueConstructor
   public Cluster(@Property("array") Array array) {
      this.array = array;
   }

   public String getId() {
      return this.id;
   }

   public Cluster setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public Cluster setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public Integer getSize() {
      return this.size;
   }

   public Cluster setSize(@Property("size") Integer size) {
      this.size = size;
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

   public Cluster addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public KohonenMap getKohonenMap() {
      return this.kohonenMap;
   }

   public Cluster setKohonenMap(@Property("kohonenMap") KohonenMap kohonenMap) {
      this.kohonenMap = kohonenMap;
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.CLUSTER_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public Cluster setArray(@Property("array") Array array) {
      this.array = array;
      return this;
   }

   public Partition getPartition() {
      return this.partition;
   }

   public Cluster setPartition(@Property("partition") Partition partition) {
      this.partition = partition;
      return this;
   }

   public Covariances getCovariances() {
      return this.covariances;
   }

   public Cluster setCovariances(@Property("covariances") Covariances covariances) {
      this.covariances = covariances;
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
            status = PMMLObject.traverse(visitor, this.getKohonenMap(), this.getArray(), this.getPartition(), this.getCovariances());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
