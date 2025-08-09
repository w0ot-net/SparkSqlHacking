package org.glassfish.jersey.server.wadl.internal.generators.resourcedoc.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "classDoc",
   propOrder = {}
)
public class ClassDocType {
   private String className;
   private String commentText;
   @XmlElementWrapper(
      name = "methodDocs"
   )
   private List methodDoc;
   @XmlAnyElement(
      lax = true
   )
   private List any;

   public List getMethodDocs() {
      if (this.methodDoc == null) {
         this.methodDoc = new ArrayList();
      }

      return this.methodDoc;
   }

   public List getAny() {
      if (this.any == null) {
         this.any = new ArrayList();
      }

      return this.any;
   }

   public String getCommentText() {
      return this.commentText;
   }

   public void setCommentText(String commentText) {
      this.commentText = commentText;
   }

   public String getClassName() {
      return this.className;
   }

   public void setClassName(String className) {
      this.className = className;
   }
}
