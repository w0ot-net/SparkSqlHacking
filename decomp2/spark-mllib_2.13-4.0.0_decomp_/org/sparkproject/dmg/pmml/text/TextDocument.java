package org.sparkproject.dmg.pmml.text;

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
import org.sparkproject.dmg.pmml.HasRequiredId;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TextDocument",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("TextDocument")
@JsonPropertyOrder({"id", "name", "length", "file", "extensions"})
public class TextDocument extends PMMLObject implements HasExtensions, HasRequiredId {
   @XmlAttribute(
      name = "id",
      required = true
   )
   @JsonProperty("id")
   private String id;
   @XmlAttribute(
      name = "name"
   )
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "length"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("length")
   private Integer length;
   @XmlAttribute(
      name = "file"
   )
   @JsonProperty("file")
   private String file;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public TextDocument() {
   }

   @ValueConstructor
   public TextDocument(@Property("id") String id) {
      this.id = id;
   }

   public String requireId() {
      if (this.id == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TEXTDOCUMENT_ID);
      } else {
         return this.id;
      }
   }

   public String getId() {
      return this.id;
   }

   public TextDocument setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public TextDocument setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public Integer getLength() {
      return this.length;
   }

   public TextDocument setLength(@Property("length") Integer length) {
      this.length = length;
      return this;
   }

   public String getFile() {
      return this.file;
   }

   public TextDocument setFile(@Property("file") String file) {
      this.file = file;
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

   public TextDocument addExtensions(Extension... extensions) {
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
