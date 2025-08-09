package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.jpmml.model.MarkupException;
import org.sparkproject.jpmml.model.MisplacedAttributeException;
import org.sparkproject.jpmml.model.MisplacedElementException;
import org.sparkproject.jpmml.model.MisplacedElementListException;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.ReflectionUtil;
import org.sparkproject.jpmml.model.UnsupportedAttributeException;
import org.sparkproject.jpmml.model.UnsupportedElementException;
import org.sparkproject.jpmml.model.UnsupportedElementListException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;

public class VersionChecker extends VersionInspector implements Resettable {
   private Version version = null;
   private List exceptions = new ArrayList();

   public VersionChecker(Version version) {
      this.version = (Version)Objects.requireNonNull(version);
   }

   public void reset() {
      this.exceptions.clear();
   }

   public void handleAdded(PMMLObject object, AnnotatedElement element, Added added) {
      Version version = added.value();
      if (version != null && version.compareTo(this.version) > 0) {
         if (element instanceof Field) {
            Field field = (Field)element;
            Object value = ReflectionUtil.getFieldValue(field, object);
            if (isAttribute(field)) {
               this.report(new UnsupportedAttributeException(object, field, value));
            } else if (isEnumValue(field)) {
               this.report(new UnsupportedAttributeException(object, (Enum)value));
            } else {
               if (!isElement(field)) {
                  throw new IllegalArgumentException();
               }

               if (value instanceof List) {
                  this.report(new UnsupportedElementListException((List)value));
               } else {
                  this.report(new UnsupportedElementException((PMMLObject)value));
               }
            }
         } else {
            this.report(new UnsupportedElementException(object));
         }
      }

   }

   public void handleRemoved(PMMLObject object, AnnotatedElement element, Removed removed) {
      Version version = removed.value().previous();
      if (version != null && version.compareTo(this.version) < 0) {
         if (element instanceof Field) {
            Field field = (Field)element;
            Object value = ReflectionUtil.getFieldValue(field, object);
            if (isAttribute(field)) {
               this.report(new MisplacedAttributeException(object, field, value));
            } else {
               if (!isElement(field)) {
                  throw new IllegalArgumentException();
               }

               if (value instanceof List) {
                  this.report(new MisplacedElementListException((List)value));
               } else {
                  this.report(new MisplacedElementException((PMMLObject)value));
               }
            }
         } else {
            this.report(new MisplacedElementException(object));
         }
      }

   }

   public void handleOptional(PMMLObject object, AnnotatedElement element, Optional optional) {
      Version version = optional.value();
      if (version != null && version.compareTo(this.version) > 0) {
         if (!(element instanceof Field)) {
            throw new IllegalArgumentException();
         }

         Field field = (Field)element;
         if (isAttribute(field)) {
            this.report(new MissingAttributeException(object, field));
         } else {
            if (!isElement(field)) {
               throw new IllegalArgumentException();
            }

            this.report(new MissingElementException(object, field));
         }
      }

   }

   public void handleRequired(PMMLObject object, AnnotatedElement element, Required required) {
      Version version = required.value();
      if (version != null && version.compareTo(this.version) <= 0) {
         if (!(element instanceof Field)) {
            throw new IllegalArgumentException();
         }

         Field field = (Field)element;
         if (isAttribute(field)) {
            this.report(new MissingAttributeException(object, field));
         } else {
            if (!isElement(field)) {
               throw new IllegalArgumentException();
            }

            this.report(new MissingElementException(object, field));
         }
      }

   }

   protected void report(MarkupException exception) {
      this.exceptions.add(exception);
   }

   public List getExceptions() {
      return this.exceptions;
   }
}
