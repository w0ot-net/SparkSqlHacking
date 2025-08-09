package org.sparkproject.dmg.pmml.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.annotations.CopyConstructor;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Node",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"predicate", "nodes"}
)
@JsonRootName("Node")
@JsonPropertyOrder({"id", "score", "recordCount", "defaultChild", "predicate", "nodes"})
public class CountingBranchNode extends BranchNode {
   @XmlAttribute(
      name = "recordCount"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("recordCount")
   private Number recordCount = null;

   public CountingBranchNode() {
   }

   @ValueConstructor
   public CountingBranchNode(@Property("score") Object score, @Property("predicate") Predicate predicate) {
      super(score, predicate);
   }

   @CopyConstructor
   public CountingBranchNode(Node node) {
      super(node);
      this.setRecordCount(node.getRecordCount());
   }

   public Number getRecordCount() {
      return this.recordCount;
   }

   public CountingBranchNode setRecordCount(@Property("recordCount") Number recordCount) {
      this.recordCount = recordCount;
      return this;
   }
}
