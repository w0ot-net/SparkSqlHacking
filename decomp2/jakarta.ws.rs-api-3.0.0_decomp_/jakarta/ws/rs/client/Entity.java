package jakarta.ws.rs.client;

import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Variant;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Locale;

public final class Entity {
   private static final Annotation[] EMPTY_ANNOTATIONS = new Annotation[0];
   private final Object entity;
   private final Variant variant;
   private final Annotation[] annotations;

   public static Entity entity(Object entity, MediaType mediaType) {
      return new Entity(entity, mediaType);
   }

   public static Entity entity(Object entity, MediaType mediaType, Annotation[] annotations) {
      return new Entity(entity, mediaType, annotations);
   }

   public static Entity entity(Object entity, String mediaType) {
      return new Entity(entity, MediaType.valueOf(mediaType));
   }

   public static Entity entity(Object entity, Variant variant) {
      return new Entity(entity, variant);
   }

   public static Entity entity(Object entity, Variant variant, Annotation[] annotations) {
      return new Entity(entity, variant, annotations);
   }

   public static Entity text(Object entity) {
      return new Entity(entity, MediaType.TEXT_PLAIN_TYPE);
   }

   public static Entity xml(Object entity) {
      return new Entity(entity, MediaType.APPLICATION_XML_TYPE);
   }

   public static Entity json(Object entity) {
      return new Entity(entity, MediaType.APPLICATION_JSON_TYPE);
   }

   public static Entity html(Object entity) {
      return new Entity(entity, MediaType.TEXT_HTML_TYPE);
   }

   public static Entity xhtml(Object entity) {
      return new Entity(entity, MediaType.APPLICATION_XHTML_XML_TYPE);
   }

   public static Entity form(Form form) {
      return new Entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
   }

   public static Entity form(MultivaluedMap formData) {
      return new Entity(new Form(formData), MediaType.APPLICATION_FORM_URLENCODED_TYPE);
   }

   private Entity(Object entity, MediaType mediaType) {
      this(entity, (Variant)(new Variant(mediaType, (Locale)null, (String)null)), (Annotation[])null);
   }

   private Entity(Object entity, Variant variant) {
      this(entity, (Variant)variant, (Annotation[])null);
   }

   private Entity(Object entity, MediaType mediaType, Annotation[] annotations) {
      this(entity, new Variant(mediaType, (Locale)null, (String)null), annotations);
   }

   private Entity(Object entity, Variant variant, Annotation[] annotations) {
      this.entity = entity;
      this.variant = variant;
      this.annotations = annotations == null ? EMPTY_ANNOTATIONS : annotations;
   }

   public Variant getVariant() {
      return this.variant;
   }

   public MediaType getMediaType() {
      return this.variant.getMediaType();
   }

   public String getEncoding() {
      return this.variant.getEncoding();
   }

   public Locale getLanguage() {
      return this.variant.getLanguage();
   }

   public Object getEntity() {
      return this.entity;
   }

   public Annotation[] getAnnotations() {
      return this.annotations;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Entity)) {
         return false;
      } else {
         Entity entity1 = (Entity)o;
         if (!Arrays.equals(this.annotations, entity1.annotations)) {
            return false;
         } else {
            if (this.entity != null) {
               if (!this.entity.equals(entity1.entity)) {
                  return false;
               }
            } else if (entity1.entity != null) {
               return false;
            }

            if (this.variant != null) {
               if (!this.variant.equals(entity1.variant)) {
                  return false;
               }
            } else if (entity1.variant != null) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int result = this.entity != null ? this.entity.hashCode() : 0;
      result = 31 * result + (this.variant != null ? this.variant.hashCode() : 0);
      result = 31 * result + Arrays.hashCode(this.annotations);
      return result;
   }

   public String toString() {
      return "Entity{entity=" + this.entity + ", variant=" + this.variant + ", annotations=" + Arrays.toString(this.annotations) + '}';
   }
}
