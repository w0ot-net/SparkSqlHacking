package jakarta.xml.bind;

import java.io.Serializable;
import javax.xml.namespace.QName;

public class JAXBElement implements Serializable {
   protected final QName name;
   protected final Class declaredType;
   protected final Class scope;
   protected Object value;
   protected boolean nil;
   private static final long serialVersionUID = 1L;

   public JAXBElement(QName name, Class declaredType, Class scope, Object value) {
      this.nil = false;
      if (declaredType != null && name != null) {
         this.declaredType = declaredType;
         if (scope == null) {
            scope = GlobalScope.class;
         }

         this.scope = scope;
         this.name = name;
         this.setValue(value);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public JAXBElement(QName name, Class declaredType, Object value) {
      this(name, declaredType, GlobalScope.class, value);
   }

   public Class getDeclaredType() {
      return this.declaredType;
   }

   public QName getName() {
      return this.name;
   }

   public void setValue(Object t) {
      this.value = t;
   }

   public Object getValue() {
      return this.value;
   }

   public Class getScope() {
      return this.scope;
   }

   public boolean isNil() {
      return this.value == null || this.nil;
   }

   public void setNil(boolean value) {
      this.nil = value;
   }

   public boolean isGlobalScope() {
      return this.scope == GlobalScope.class;
   }

   public boolean isTypeSubstituted() {
      if (this.value == null) {
         return false;
      } else {
         return this.value.getClass() != this.declaredType;
      }
   }

   public static final class GlobalScope {
      private GlobalScope() {
      }
   }
}
