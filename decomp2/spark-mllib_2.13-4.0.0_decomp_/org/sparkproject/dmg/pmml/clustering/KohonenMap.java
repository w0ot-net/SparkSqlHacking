package org.sparkproject.dmg.pmml.clustering;

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
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "KohonenMap",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("KohonenMap")
@JsonPropertyOrder({"coord1", "coord2", "coord3", "extensions"})
public class KohonenMap extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "coord1"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("coord1")
   private Number coord1;
   @XmlAttribute(
      name = "coord2"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("coord2")
   private Number coord2;
   @XmlAttribute(
      name = "coord3"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("coord3")
   private Number coord3;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Number getCoord1() {
      return this.coord1;
   }

   public KohonenMap setCoord1(@Property("coord1") Number coord1) {
      this.coord1 = coord1;
      return this;
   }

   public Number getCoord2() {
      return this.coord2;
   }

   public KohonenMap setCoord2(@Property("coord2") Number coord2) {
      this.coord2 = coord2;
      return this;
   }

   public Number getCoord3() {
      return this.coord3;
   }

   public KohonenMap setCoord3(@Property("coord3") Number coord3) {
      this.coord3 = coord3;
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

   public KohonenMap addExtensions(Extension... extensions) {
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
