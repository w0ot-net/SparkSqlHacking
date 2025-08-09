package org.sparkproject.dmg.pmml;

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
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "Header",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "application", "annotations", "timestamp"}
)
@JsonRootName("Header")
@JsonPropertyOrder({"copyright", "description", "modelVersion", "extensions", "application", "annotations", "timestamp"})
public class Header extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "copyright"
   )
   @JsonProperty("copyright")
   @Optional(Version.PMML_4_1)
   private String copyright;
   @XmlAttribute(
      name = "description"
   )
   @JsonProperty("description")
   private String description;
   @XmlAttribute(
      name = "modelVersion"
   )
   @JsonProperty("modelVersion")
   @Added(
      value = Version.PMML_4_3,
      removable = true
   )
   private String modelVersion;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Application",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Application")
   private Application application;
   @XmlElement(
      name = "Annotation",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Annotation")
   @CollectionElementType(Annotation.class)
   private List annotations;
   @XmlElement(
      name = "Timestamp",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Timestamp")
   private Timestamp timestamp;
   private static final long serialVersionUID = 67371272L;

   public String getCopyright() {
      return this.copyright;
   }

   public Header setCopyright(@Property("copyright") String copyright) {
      this.copyright = copyright;
      return this;
   }

   public String getDescription() {
      return this.description;
   }

   public Header setDescription(@Property("description") String description) {
      this.description = description;
      return this;
   }

   public String getModelVersion() {
      return this.modelVersion;
   }

   public Header setModelVersion(@Property("modelVersion") String modelVersion) {
      this.modelVersion = modelVersion;
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

   public Header addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Application getApplication() {
      return this.application;
   }

   public Header setApplication(@Property("application") Application application) {
      this.application = application;
      return this;
   }

   public boolean hasAnnotations() {
      return this.annotations != null && !this.annotations.isEmpty();
   }

   public List getAnnotations() {
      if (this.annotations == null) {
         this.annotations = new ArrayList();
      }

      return this.annotations;
   }

   public Header addAnnotations(Annotation... annotations) {
      this.getAnnotations().addAll(Arrays.asList(annotations));
      return this;
   }

   public Timestamp getTimestamp() {
      return this.timestamp;
   }

   public Header setTimestamp(@Property("timestamp") Timestamp timestamp) {
      this.timestamp = timestamp;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getApplication());
         }

         if (status == VisitorAction.CONTINUE && this.hasAnnotations()) {
            status = PMMLObject.traverse(visitor, this.getAnnotations());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getTimestamp());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
