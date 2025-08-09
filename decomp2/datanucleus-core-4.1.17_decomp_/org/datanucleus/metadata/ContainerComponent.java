package org.datanucleus.metadata;

import java.io.Serializable;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.StringUtils;

class ContainerComponent implements Serializable {
   private static final long serialVersionUID = -5662004381416396246L;
   protected Boolean embedded;
   protected Boolean serialized;
   protected Boolean dependent;
   protected String type = "java.lang.Object";
   protected AbstractClassMetaData classMetaData;

   public ContainerComponent() {
   }

   public Boolean getEmbedded() {
      return this.embedded;
   }

   public void setEmbedded(Boolean embedded) {
      this.embedded = embedded;
   }

   public Boolean getSerialized() {
      return this.serialized;
   }

   public void setSerialized(Boolean serialized) {
      this.serialized = serialized;
   }

   public Boolean getDependent() {
      return this.dependent;
   }

   public void setDependent(Boolean dependent) {
      this.dependent = dependent;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = StringUtils.isWhitespace(type) ? null : type;
   }

   void populate(String packageName, ClassLoaderResolver clr, ClassLoader primary, MetaDataManager mmgr) {
      if (this.type != null && !ClassUtils.isPrimitiveArrayType(this.type) && !ClassUtils.isPrimitiveType(this.type)) {
         try {
            clr.classForName(this.type, primary, false);
         } catch (ClassNotResolvedException var9) {
            String name = ClassUtils.createFullClassName(packageName, this.type);

            try {
               clr.classForName(name, primary, false);
               this.type = name;
            } catch (ClassNotResolvedException var8) {
               name = ClassUtils.getJavaLangClassForType(this.type);
               clr.classForName(name, primary, false);
               this.type = name;
            }
         }
      }

   }

   public String toString() {
      return "Type=" + this.type + " embedded=" + this.embedded + " serialized=" + this.serialized + " dependent=" + this.dependent + " cmd=" + this.classMetaData;
   }
}
