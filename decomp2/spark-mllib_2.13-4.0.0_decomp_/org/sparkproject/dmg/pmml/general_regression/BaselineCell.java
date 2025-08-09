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
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "BaselineCell",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("BaselineCell")
@JsonPropertyOrder({"time", "cumHazard", "extensions"})
@Added(Version.PMML_4_0)
public class BaselineCell extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "time",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("time")
   private Number time;
   @XmlAttribute(
      name = "cumHazard",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("cumHazard")
   private Number cumHazard;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public BaselineCell() {
   }

   @ValueConstructor
   public BaselineCell(@Property("time") Number time, @Property("cumHazard") Number cumHazard) {
      this.time = time;
      this.cumHazard = cumHazard;
   }

   public Number requireTime() {
      if (this.time == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BASELINECELL_TIME);
      } else {
         return this.time;
      }
   }

   public Number getTime() {
      return this.time;
   }

   public BaselineCell setTime(@Property("time") Number time) {
      this.time = time;
      return this;
   }

   public Number requireCumHazard() {
      if (this.cumHazard == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BASELINECELL_CUMHAZARD);
      } else {
         return this.cumHazard;
      }
   }

   public Number getCumHazard() {
      return this.cumHazard;
   }

   public BaselineCell setCumHazard(@Property("cumHazard") Number cumHazard) {
      this.cumHazard = cumHazard;
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

   public BaselineCell addExtensions(Extension... extensions) {
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
