package org.sparkproject.dmg.pmml;

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
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Partition",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "partitionFieldStats"}
)
@JsonRootName("Partition")
@JsonPropertyOrder({"name", "size", "extensions", "partitionFieldStats"})
public class Partition extends PMMLObject implements HasExtensions, HasRequiredName {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "size"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("size")
   private Number size;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "PartitionFieldStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("PartitionFieldStats")
   @CollectionElementType(PartitionFieldStats.class)
   private List partitionFieldStats;
   private static final long serialVersionUID = 67371272L;

   public Partition() {
   }

   @ValueConstructor
   public Partition(@Property("name") String name) {
      this.name = name;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PARTITION_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public Partition setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public Number getSize() {
      return this.size;
   }

   public Partition setSize(@Property("size") Number size) {
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

   public Partition addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasPartitionFieldStats() {
      return this.partitionFieldStats != null && !this.partitionFieldStats.isEmpty();
   }

   public List getPartitionFieldStats() {
      if (this.partitionFieldStats == null) {
         this.partitionFieldStats = new ArrayList();
      }

      return this.partitionFieldStats;
   }

   public Partition addPartitionFieldStats(PartitionFieldStats... partitionFieldStats) {
      this.getPartitionFieldStats().addAll(Arrays.asList(partitionFieldStats));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasPartitionFieldStats()) {
            status = PMMLObject.traverse(visitor, this.getPartitionFieldStats());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
