package org.glassfish.jaxb.core.v2.runtime;

import jakarta.xml.bind.JAXBException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

public class IllegalAnnotationException extends JAXBException {
   private final transient List pos;
   private static final long serialVersionUID = 407680563506515709L;

   public IllegalAnnotationException(String message, Locatable src) {
      super(message);
      this.pos = this.build(src);
   }

   public IllegalAnnotationException(String message, Annotation src) {
      this(message, cast(src));
   }

   public IllegalAnnotationException(String message, Locatable src1, Locatable src2) {
      super(message);
      this.pos = this.build(src1, src2);
   }

   public IllegalAnnotationException(String message, Annotation src1, Annotation src2) {
      this(message, cast(src1), cast(src2));
   }

   public IllegalAnnotationException(String message, Annotation src1, Locatable src2) {
      this(message, cast(src1), src2);
   }

   public IllegalAnnotationException(String message, Throwable cause, Locatable src) {
      super(message, cause);
      this.pos = this.build(src);
   }

   private static Locatable cast(Annotation a) {
      return a instanceof Locatable ? (Locatable)a : null;
   }

   private List build(Locatable... srcs) {
      List<List<Location>> r = new ArrayList();

      for(Locatable l : srcs) {
         if (l != null) {
            List<Location> ll = this.convert(l);
            if (ll != null && !ll.isEmpty()) {
               r.add(ll);
            }
         }
      }

      return Collections.unmodifiableList(r);
   }

   private List convert(Locatable src) {
      if (src == null) {
         return null;
      } else {
         List<Location> r;
         for(r = new ArrayList(); src != null; src = src.getUpstream()) {
            r.add(src.getLocation());
         }

         return Collections.unmodifiableList(r);
      }
   }

   public List getSourcePos() {
      return this.pos;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder(this.getMessage());

      for(List locs : this.pos) {
         sb.append("\n\tthis problem is related to the following location:");

         for(Location loc : locs) {
            sb.append("\n\t\tat ").append(loc.toString());
         }
      }

      return sb.toString();
   }
}
