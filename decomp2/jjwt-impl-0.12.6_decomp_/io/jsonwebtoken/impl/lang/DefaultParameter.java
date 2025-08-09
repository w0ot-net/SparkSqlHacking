package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import java.util.Collection;

public class DefaultParameter implements Parameter {
   private final String ID;
   private final String NAME;
   private final boolean SECRET;
   private final Class IDIOMATIC_TYPE;
   private final Class COLLECTION_TYPE;
   private final Converter CONVERTER;

   public DefaultParameter(String id, String name, boolean secret, Class idiomaticType, Class collectionType, Converter converter) {
      this.ID = Strings.clean((String)Assert.hasText(id, "ID argument cannot be null or empty."));
      this.NAME = Strings.clean((String)Assert.hasText(name, "Name argument cannot be null or empty."));
      this.IDIOMATIC_TYPE = (Class)Assert.notNull(idiomaticType, "idiomaticType argument cannot be null.");
      this.CONVERTER = (Converter)Assert.notNull(converter, "Converter argument cannot be null.");
      this.SECRET = secret;
      this.COLLECTION_TYPE = collectionType;
   }

   public String getId() {
      return this.ID;
   }

   public String getName() {
      return this.NAME;
   }

   public boolean supports(Object value) {
      if (value == null) {
         return true;
      } else if (this.COLLECTION_TYPE != null && this.COLLECTION_TYPE.isInstance(value)) {
         Collection<? extends T> c = (Collection)this.COLLECTION_TYPE.cast(value);
         return c.isEmpty() || this.IDIOMATIC_TYPE.isInstance(c.iterator().next());
      } else {
         return this.IDIOMATIC_TYPE.isInstance(value);
      }
   }

   public Object cast(Object value) {
      if (value != null) {
         if (this.COLLECTION_TYPE != null) {
            if (!this.COLLECTION_TYPE.isInstance(value)) {
               String msg = "Cannot cast " + value.getClass().getName() + " to " + this.COLLECTION_TYPE.getName() + "<" + this.IDIOMATIC_TYPE.getName() + ">";
               throw new ClassCastException(msg);
            }

            Collection<?> c = (Collection)this.COLLECTION_TYPE.cast(value);
            if (!c.isEmpty()) {
               Object element = c.iterator().next();
               if (!this.IDIOMATIC_TYPE.isInstance(element)) {
                  String msg = "Cannot cast " + value.getClass().getName() + " to " + this.COLLECTION_TYPE.getName() + "<" + this.IDIOMATIC_TYPE.getName() + ">: At least one " + "element is not an instance of " + this.IDIOMATIC_TYPE.getName();
                  throw new ClassCastException(msg);
               }
            }
         } else if (!this.IDIOMATIC_TYPE.isInstance(value)) {
            String msg = "Cannot cast " + value.getClass().getName() + " to " + this.IDIOMATIC_TYPE.getName();
            throw new ClassCastException(msg);
         }
      }

      return value;
   }

   public boolean isSecret() {
      return this.SECRET;
   }

   public int hashCode() {
      return this.ID.hashCode();
   }

   public boolean equals(Object obj) {
      return obj instanceof Parameter ? this.ID.equals(((Parameter)obj).getId()) : false;
   }

   public String toString() {
      return "'" + this.ID + "' (" + this.NAME + ")";
   }

   public Object applyTo(Object t) {
      return this.CONVERTER.applyTo(t);
   }

   public Object applyFrom(Object o) {
      return this.CONVERTER.applyFrom(o);
   }
}
