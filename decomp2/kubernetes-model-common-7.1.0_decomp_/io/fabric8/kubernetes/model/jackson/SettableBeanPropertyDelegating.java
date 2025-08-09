package io.fabric8.kubernetes.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.function.BooleanSupplier;

public class SettableBeanPropertyDelegating extends SettableBeanProperty.Delegating {
   private final SettableAnyProperty anySetter;
   private final transient BooleanSupplier useAnySetter;

   SettableBeanPropertyDelegating(SettableBeanProperty delegate, SettableAnyProperty anySetter, BooleanSupplier useAnySetter) {
      super(delegate);
      this.anySetter = anySetter;
      this.useAnySetter = useAnySetter;
   }

   protected SettableBeanProperty withDelegate(SettableBeanProperty d) {
      return new SettableBeanPropertyDelegating(d, this.anySetter, this.useAnySetter);
   }

   public void markAsIgnorable() {
      this.delegate.markAsIgnorable();
   }

   public boolean isIgnorable() {
      return this.delegate.isIgnorable();
   }

   public void setViews(Class[] views) {
      this.delegate.setViews(views);
   }

   public Annotation getContextAnnotation(Class acls) {
      return this.delegate.getContextAnnotation(acls);
   }

   public PropertyName getWrapperName() {
      return this.delegate.getWrapperName();
   }

   public NullValueProvider getNullValueProvider() {
      return this.delegate.getNullValueProvider();
   }

   public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
      this.delegate.depositSchemaProperty(objectVisitor, provider);
   }

   public JavaType getType() {
      return this.delegate.getType();
   }

   public PropertyName getFullName() {
      return this.delegate.getFullName();
   }

   public void setManagedReferenceName(String n) {
      this.delegate.setManagedReferenceName(n);
   }

   public SettableBeanProperty withSimpleName(String simpleName) {
      return this._with(this.delegate.withSimpleName(simpleName));
   }

   public void setObjectIdInfo(ObjectIdInfo objectIdInfo) {
      this.delegate.setObjectIdInfo(objectIdInfo);
   }

   public String toString() {
      return this.delegate.toString();
   }

   public void deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
      try {
         this.delegate.deserializeAndSet(p, ctxt, instance);
      } catch (MismatchedInputException ex) {
         if (!this.shouldUseAnySetter()) {
            throw ex;
         }

         this.anySetter.set(instance, this.delegate.getName(), p.getText());
      }

   }

   public Object deserializeSetAndReturn(JsonParser p, DeserializationContext ctxt, Object instance) throws IOException {
      try {
         return this.delegate.deserializeSetAndReturn(p, ctxt, instance);
      } catch (MismatchedInputException var5) {
         this.deserializeAndSet(p, ctxt, instance);
         return instance;
      }
   }

   private boolean shouldUseAnySetter() {
      return this.anySetter == null ? false : this.useAnySetter.getAsBoolean();
   }
}
