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
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "ClusteringModelQuality",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("ClusteringModelQuality")
@JsonPropertyOrder({"dataName", "sse", "ssb", "extensions"})
@Added(Version.PMML_4_0)
public class ClusteringModelQuality extends ModelQuality implements HasExtensions {
   @XmlAttribute(
      name = "dataName"
   )
   @JsonProperty("dataName")
   private String dataName;
   @XmlAttribute(
      name = "SSE"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("SSE")
   private Number sse;
   @XmlAttribute(
      name = "SSB"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("SSB")
   private Number ssb;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public String getDataName() {
      return this.dataName;
   }

   public ClusteringModelQuality setDataName(@Property("dataName") String dataName) {
      this.dataName = dataName;
      return this;
   }

   public Number getSSE() {
      return this.sse;
   }

   public ClusteringModelQuality setSSE(@Property("sse") Number sse) {
      this.sse = sse;
      return this;
   }

   public Number getSSB() {
      return this.ssb;
   }

   public ClusteringModelQuality setSSB(@Property("ssb") Number ssb) {
      this.ssb = ssb;
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

   public ClusteringModelQuality addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
