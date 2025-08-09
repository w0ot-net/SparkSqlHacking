package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;
import org.glassfish.jaxb.core.annotation.XmlLocation;
import org.sparkproject.jpmml.model.ReflectionUtil;
import org.xml.sax.Locator;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlTransient
@JsonAutoDetect(
   fieldVisibility = Visibility.ANY,
   getterVisibility = Visibility.NONE,
   isGetterVisibility = Visibility.NONE,
   setterVisibility = Visibility.NONE
)
@JsonInclude(Include.NON_EMPTY)
public abstract class PMMLObject implements HasLocator, Serializable, Visitable {
   @XmlTransient
   @XmlLocation
   @org.eclipse.persistence.oxm.annotations.XmlLocation
   @JsonIgnore
   private Locator locator;

   public boolean hasLocator() {
      return this.locator != null;
   }

   public Locator getLocator() {
      return this.locator;
   }

   public void setLocator(Locator locator) {
      this.locator = locator;
   }

   public static int[] getSchemaVersion() {
      return getSchemaVersion(PMML.class);
   }

   public static int[] getSchemaVersion(Class clazz) {
      long serialVersionUID;
      try {
         java.lang.reflect.Field serialVersionUIDField = ReflectionUtil.getSerialVersionUIDField(clazz);
         if (!serialVersionUIDField.isAccessible()) {
            serialVersionUIDField.setAccessible(true);
         }

         serialVersionUID = serialVersionUIDField.getLong((Object)null);
      } catch (ReflectiveOperationException roe) {
         throw new RuntimeException(roe);
      }

      int major = (int)(serialVersionUID >> 24 & 255L);
      int minor = (int)(serialVersionUID >> 16 & 255L);
      int patch = (int)(serialVersionUID >> 8 & 255L);
      int implementation = (int)(serialVersionUID & 255L);
      return new int[]{major, minor, patch, implementation};
   }

   protected static VisitorAction traverse(Visitor visitor, Visitable first) {
      return first != null ? first.accept(visitor) : VisitorAction.CONTINUE;
   }

   protected static VisitorAction traverse(Visitor visitor, Visitable first, Visitable second) {
      if (first != null) {
         VisitorAction status = first.accept(visitor);
         if (status != VisitorAction.CONTINUE) {
            return status;
         }
      }

      return second != null ? second.accept(visitor) : VisitorAction.CONTINUE;
   }

   protected static VisitorAction traverse(Visitor visitor, Visitable... objects) {
      int i = 0;

      for(int max = objects.length; i < max; ++i) {
         Visitable visitable = objects[i];
         if (visitable != null) {
            VisitorAction status = visitable.accept(visitor);
            if (status != VisitorAction.CONTINUE) {
               return status;
            }
         }
      }

      return VisitorAction.CONTINUE;
   }

   protected static VisitorAction traverse(Visitor visitor, List objects) {
      int i = 0;

      for(int max = objects.size(); i < max; ++i) {
         Visitable visitable = (Visitable)objects.get(i);
         if (visitable != null) {
            VisitorAction status = visitable.accept(visitor);
            if (status != VisitorAction.CONTINUE) {
               return status;
            }
         }
      }

      return VisitorAction.CONTINUE;
   }

   protected static VisitorAction traverseMixed(Visitor visitor, List objects) {
      int i = 0;

      for(int max = objects.size(); i < max; ++i) {
         Object object = objects.get(i);
         if (object instanceof Visitable) {
            Visitable visitable = (Visitable)object;
            VisitorAction status = visitable.accept(visitor);
            if (status != VisitorAction.CONTINUE) {
               return status;
            }
         }
      }

      return VisitorAction.CONTINUE;
   }
}
