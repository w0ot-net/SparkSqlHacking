package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.NotNull;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.IntArray;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.FieldAccess;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class FieldSerializer extends Serializer implements Comparator {
   final Kryo kryo;
   final Class type;
   final TypeVariable[] typeParameters;
   final Class componentType;
   protected final FieldSerializerConfig config;
   private CachedField[] fields;
   private CachedField[] transientFields;
   protected HashSet removedFields;
   Object access;
   private FieldSerializerUnsafeUtil unsafeUtil;
   private FieldSerializerGenericsUtil genericsUtil;
   private FieldSerializerAnnotationsUtil annotationsUtil;
   private Class[] generics;
   private Generics genericsScope;
   private boolean varIntsEnabled;
   private boolean useMemRegions;
   private boolean hasObjectFields;
   static CachedFieldFactory asmFieldFactory;
   static CachedFieldFactory objectFieldFactory;
   static CachedFieldFactory unsafeFieldFactory;
   static boolean unsafeAvailable;
   static Class unsafeUtilClass;
   static Method sortFieldsByOffsetMethod;

   public FieldSerializer(Kryo kryo, Class type) {
      this(kryo, type, (Class[])null);
   }

   public FieldSerializer(Kryo kryo, Class type, Class[] generics) {
      this(kryo, type, generics, kryo.getFieldSerializerConfig().clone());
   }

   protected FieldSerializer(Kryo kryo, Class type, Class[] generics, FieldSerializerConfig config) {
      this.fields = new CachedField[0];
      this.transientFields = new CachedField[0];
      this.removedFields = new HashSet();
      this.useMemRegions = false;
      this.hasObjectFields = false;
      this.varIntsEnabled = true;
      if (Log.TRACE) {
         Log.trace("kryo", "Optimize ints: " + this.varIntsEnabled);
      }

      this.config = config;
      this.kryo = kryo;
      this.type = type;
      this.generics = generics;
      this.typeParameters = type.getTypeParameters();
      if (this.typeParameters != null && this.typeParameters.length != 0) {
         this.componentType = null;
      } else {
         this.componentType = type.getComponentType();
      }

      this.genericsUtil = new FieldSerializerGenericsUtil(this);
      this.unsafeUtil = FieldSerializerUnsafeUtil.Factory.getInstance(this);
      this.annotationsUtil = new FieldSerializerAnnotationsUtil(this);
      this.rebuildCachedFields();
   }

   protected void rebuildCachedFields() {
      this.rebuildCachedFields(false);
   }

   protected void rebuildCachedFields(boolean minorRebuild) {
      if (Log.TRACE && this.generics != null) {
         Log.trace("kryo", "Generic type parameters: " + Arrays.toString(this.generics));
      }

      if (this.type.isInterface()) {
         this.fields = new CachedField[0];
      } else {
         this.hasObjectFields = false;
         if (this.config.isOptimizedGenerics()) {
            Generics genScope = this.genericsUtil.buildGenericsScope(this.type, this.generics);
            this.genericsScope = genScope;
            if (this.genericsScope != null) {
               this.kryo.getGenericsResolver().pushScope(this.type, this.genericsScope);
            }
         }

         IntArray useAsm = new IntArray();
         List<Field> validTransientFields;
         List<Field> validFields;
         if (!minorRebuild) {
            List<Field> allFields = new ArrayList();

            for(Class nextClass = this.type; nextClass != Object.class; nextClass = nextClass.getSuperclass()) {
               Field[] declaredFields = nextClass.getDeclaredFields();
               if (declaredFields != null) {
                  for(Field f : declaredFields) {
                     if (!Modifier.isStatic(f.getModifiers())) {
                        allFields.add(f);
                     }
                  }
               }
            }

            ObjectMap context = this.kryo.getContext();
            if (this.useMemRegions && !this.config.isUseAsm() && unsafeAvailable) {
               try {
                  Field[] allFieldsArray = (Field[])sortFieldsByOffsetMethod.invoke((Object)null, allFields);
                  allFields = Arrays.asList(allFieldsArray);
               } catch (Exception e) {
                  throw new RuntimeException("Cannot invoke UnsafeUtil.sortFieldsByOffset()", e);
               }
            }

            validFields = this.buildValidFields(false, allFields, context, useAsm);
            validTransientFields = this.buildValidFields(true, allFields, context, useAsm);
            if (this.config.isUseAsm() && !Util.IS_ANDROID && Modifier.isPublic(this.type.getModifiers()) && useAsm.indexOf(1) != -1) {
               try {
                  this.access = FieldAccess.get(this.type);
               } catch (RuntimeException var12) {
               }
            }
         } else {
            validFields = this.buildValidFieldsFromCachedFields(this.fields, useAsm);
            validTransientFields = this.buildValidFieldsFromCachedFields(this.transientFields, useAsm);
         }

         List<CachedField> cachedFields = new ArrayList(validFields.size());
         List<CachedField> cachedTransientFields = new ArrayList(validTransientFields.size());
         this.createCachedFields(useAsm, validFields, cachedFields, 0);
         this.createCachedFields(useAsm, validTransientFields, cachedTransientFields, validFields.size());
         Collections.sort(cachedFields, this);
         this.fields = (CachedField[])cachedFields.toArray(new CachedField[cachedFields.size()]);
         Collections.sort(cachedTransientFields, this);
         this.transientFields = (CachedField[])cachedTransientFields.toArray(new CachedField[cachedTransientFields.size()]);
         this.initializeCachedFields();
         if (this.genericsScope != null) {
            this.kryo.getGenericsResolver().popScope();
         }

         if (!minorRebuild) {
            for(CachedField field : this.removedFields) {
               this.removeField(field);
            }
         }

         this.annotationsUtil.processAnnotatedFields(this);
      }
   }

   private List buildValidFieldsFromCachedFields(CachedField[] cachedFields, IntArray useAsm) {
      ArrayList<Field> fields = new ArrayList(cachedFields.length);

      for(CachedField f : cachedFields) {
         fields.add(f.field);
         useAsm.add(f.accessIndex > -1 ? 1 : 0);
      }

      return fields;
   }

   private List buildValidFields(boolean transientFields, List allFields, ObjectMap context, IntArray useAsm) {
      List<Field> result = new ArrayList(allFields.size());
      int i = 0;

      for(int n = allFields.size(); i < n; ++i) {
         Field field = (Field)allFields.get(i);
         int modifiers = field.getModifiers();
         if (Modifier.isTransient(modifiers) == transientFields && !Modifier.isStatic(modifiers) && (!field.isSynthetic() || !this.config.isIgnoreSyntheticFields())) {
            if (!field.isAccessible()) {
               if (!this.config.isSetFieldsAsAccessible()) {
                  continue;
               }

               try {
                  field.setAccessible(true);
               } catch (AccessControlException var11) {
                  continue;
               }
            }

            Optional optional = (Optional)field.getAnnotation(Optional.class);
            if (optional == null || context.containsKey(optional.value())) {
               result.add(field);
               useAsm.add(!Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && Modifier.isPublic(field.getType().getModifiers()) ? 1 : 0);
            }
         }
      }

      return result;
   }

   private void createCachedFields(IntArray useAsm, List validFields, List cachedFields, int baseIndex) {
      if (!this.config.isUseAsm() && this.useMemRegions) {
         this.unsafeUtil.createUnsafeCacheFieldsAndRegions(validFields, cachedFields, baseIndex, useAsm);
      } else {
         int i = 0;

         for(int n = validFields.size(); i < n; ++i) {
            Field field = (Field)validFields.get(i);
            int accessIndex = -1;
            if (this.access != null && useAsm.get(baseIndex + i) == 1) {
               accessIndex = ((FieldAccess)this.access).getIndex(field.getName());
            }

            cachedFields.add(this.newCachedField(field, cachedFields.size(), accessIndex));
         }
      }

   }

   public void setGenerics(Kryo kryo, Class[] generics) {
      if (this.config.isOptimizedGenerics()) {
         this.generics = generics;
         if (this.typeParameters != null && this.typeParameters.length > 0) {
            this.rebuildCachedFields(true);
         }

      }
   }

   public Class[] getGenerics() {
      return this.generics;
   }

   protected void initializeCachedFields() {
   }

   CachedField newCachedField(Field field, int fieldIndex, int accessIndex) {
      Class[] fieldClass = new Class[]{field.getType()};
      Type fieldGenericType = this.config.isOptimizedGenerics() ? field.getGenericType() : null;
      CachedField cachedField;
      if (this.config.isOptimizedGenerics() && fieldGenericType != fieldClass[0]) {
         cachedField = this.genericsUtil.newCachedFieldOfGenericType(field, accessIndex, fieldClass, fieldGenericType);
      } else {
         if (Log.TRACE) {
            Log.trace("kryo", "Field " + field.getName() + ": " + fieldClass[0]);
         }

         cachedField = this.newMatchingCachedField(field, accessIndex, fieldClass[0], fieldGenericType, (Class[])null);
      }

      if (cachedField instanceof ObjectField) {
         this.hasObjectFields = true;
      }

      cachedField.field = field;
      cachedField.varIntsEnabled = this.varIntsEnabled;
      if (!this.config.isUseAsm()) {
         cachedField.offset = this.unsafeUtil.getObjectFieldOffset(field);
      }

      cachedField.access = (FieldAccess)this.access;
      cachedField.accessIndex = accessIndex;
      cachedField.canBeNull = this.config.isFieldsCanBeNull() && !fieldClass[0].isPrimitive() && !field.isAnnotationPresent(NotNull.class);
      if (this.kryo.isFinal(fieldClass[0]) || this.config.isFixedFieldTypes()) {
         cachedField.valueClass = fieldClass[0];
      }

      return cachedField;
   }

   CachedField newMatchingCachedField(Field field, int accessIndex, Class fieldClass, Type fieldGenericType, Class[] fieldGenerics) {
      CachedField cachedField;
      if (accessIndex != -1) {
         cachedField = this.getAsmFieldFactory().createCachedField(fieldClass, field, this);
      } else if (!this.config.isUseAsm()) {
         cachedField = this.getUnsafeFieldFactory().createCachedField(fieldClass, field, this);
      } else {
         cachedField = this.getObjectFieldFactory().createCachedField(fieldClass, field, this);
         if (this.config.isOptimizedGenerics()) {
            if (fieldGenerics != null) {
               ((ObjectField)cachedField).generics = fieldGenerics;
            } else if (fieldGenericType != null) {
               Class[] cachedFieldGenerics = FieldSerializerGenericsUtil.getGenerics(fieldGenericType, this.kryo);
               ((ObjectField)cachedField).generics = cachedFieldGenerics;
               if (Log.TRACE) {
                  Log.trace("kryo", "Field generics: " + Arrays.toString(cachedFieldGenerics));
               }
            }
         }
      }

      return cachedField;
   }

   private CachedFieldFactory getAsmFieldFactory() {
      if (asmFieldFactory == null) {
         asmFieldFactory = new AsmCachedFieldFactory();
      }

      return asmFieldFactory;
   }

   private CachedFieldFactory getObjectFieldFactory() {
      if (objectFieldFactory == null) {
         objectFieldFactory = new ObjectCachedFieldFactory();
      }

      return objectFieldFactory;
   }

   private CachedFieldFactory getUnsafeFieldFactory() {
      if (unsafeFieldFactory == null) {
         try {
            unsafeFieldFactory = (CachedFieldFactory)this.getClass().getClassLoader().loadClass("com.esotericsoftware.kryo.serializers.UnsafeCachedFieldFactory").newInstance();
         } catch (Exception e) {
            throw new RuntimeException("Cannot create UnsafeFieldFactory", e);
         }
      }

      return unsafeFieldFactory;
   }

   public int compare(CachedField o1, CachedField o2) {
      return this.getCachedFieldName(o1).compareTo(this.getCachedFieldName(o2));
   }

   public void setFieldsCanBeNull(boolean fieldsCanBeNull) {
      this.config.setFieldsCanBeNull(fieldsCanBeNull);
      this.rebuildCachedFields();
   }

   public void setFieldsAsAccessible(boolean setFieldsAsAccessible) {
      this.config.setFieldsAsAccessible(setFieldsAsAccessible);
      this.rebuildCachedFields();
   }

   public void setIgnoreSyntheticFields(boolean ignoreSyntheticFields) {
      this.config.setIgnoreSyntheticFields(ignoreSyntheticFields);
      this.rebuildCachedFields();
   }

   public void setFixedFieldTypes(boolean fixedFieldTypes) {
      this.config.setFixedFieldTypes(fixedFieldTypes);
      this.rebuildCachedFields();
   }

   public void setUseAsm(boolean setUseAsm) {
      this.config.setUseAsm(setUseAsm);
      this.rebuildCachedFields();
   }

   public void setCopyTransient(boolean setCopyTransient) {
      this.config.setCopyTransient(setCopyTransient);
   }

   public void setSerializeTransient(boolean setSerializeTransient) {
      this.config.setSerializeTransient(setSerializeTransient);
   }

   public void setOptimizedGenerics(boolean setOptimizedGenerics) {
      this.config.setOptimizedGenerics(setOptimizedGenerics);
      this.rebuildCachedFields();
   }

   public void write(Kryo kryo, Output output, Object object) {
      if (Log.TRACE) {
         Log.trace("kryo", "FieldSerializer.write fields of class: " + object.getClass().getName());
      }

      if (this.config.isOptimizedGenerics()) {
         if (this.typeParameters != null && this.generics != null) {
            this.rebuildCachedFields();
         }

         if (this.genericsScope != null) {
            kryo.getGenericsResolver().pushScope(this.type, this.genericsScope);
         }
      }

      CachedField[] fields = this.fields;
      int i = 0;

      for(int n = fields.length; i < n; ++i) {
         fields[i].write(output, object);
      }

      if (this.config.isSerializeTransient()) {
         i = 0;

         for(int n = this.transientFields.length; i < n; ++i) {
            this.transientFields[i].write(output, object);
         }
      }

      if (this.config.isOptimizedGenerics() && this.genericsScope != null) {
         kryo.getGenericsResolver().popScope();
      }

   }

   public Object read(Kryo kryo, Input input, Class type) {
      Object var12;
      try {
         if (this.config.isOptimizedGenerics()) {
            if (this.typeParameters != null && this.generics != null) {
               this.rebuildCachedFields();
            }

            if (this.genericsScope != null) {
               kryo.getGenericsResolver().pushScope(type, this.genericsScope);
            }
         }

         T object = (T)this.create(kryo, input, type);
         kryo.reference(object);
         CachedField[] fields = this.fields;
         int i = 0;

         for(int n = fields.length; i < n; ++i) {
            fields[i].read(input, object);
         }

         if (this.config.isSerializeTransient()) {
            i = 0;

            for(int n = this.transientFields.length; i < n; ++i) {
               this.transientFields[i].read(input, object);
            }
         }

         var12 = object;
      } finally {
         if (this.config.isOptimizedGenerics() && this.genericsScope != null && kryo.getGenericsResolver() != null) {
            kryo.getGenericsResolver().popScope();
         }

      }

      return var12;
   }

   protected Object create(Kryo kryo, Input input, Class type) {
      return kryo.newInstance(type);
   }

   public CachedField getField(String fieldName) {
      for(CachedField cachedField : this.fields) {
         if (this.getCachedFieldName(cachedField).equals(fieldName)) {
            return cachedField;
         }
      }

      throw new IllegalArgumentException("Field \"" + fieldName + "\" not found on class: " + this.type.getName());
   }

   protected String getCachedFieldName(CachedField cachedField) {
      return this.config.getCachedFieldNameStrategy().getName(cachedField);
   }

   public void removeField(String fieldName) {
      for(int i = 0; i < this.fields.length; ++i) {
         CachedField cachedField = this.fields[i];
         if (this.getCachedFieldName(cachedField).equals(fieldName)) {
            CachedField[] newFields = new CachedField[this.fields.length - 1];
            System.arraycopy(this.fields, 0, newFields, 0, i);
            System.arraycopy(this.fields, i + 1, newFields, i, newFields.length - i);
            this.fields = newFields;
            this.removedFields.add(cachedField);
            return;
         }
      }

      for(int i = 0; i < this.transientFields.length; ++i) {
         CachedField cachedField = this.transientFields[i];
         if (this.getCachedFieldName(cachedField).equals(fieldName)) {
            CachedField[] newFields = new CachedField[this.transientFields.length - 1];
            System.arraycopy(this.transientFields, 0, newFields, 0, i);
            System.arraycopy(this.transientFields, i + 1, newFields, i, newFields.length - i);
            this.transientFields = newFields;
            this.removedFields.add(cachedField);
            return;
         }
      }

      throw new IllegalArgumentException("Field \"" + fieldName + "\" not found on class: " + this.type.getName());
   }

   public void removeField(CachedField removeField) {
      for(int i = 0; i < this.fields.length; ++i) {
         CachedField cachedField = this.fields[i];
         if (cachedField == removeField) {
            CachedField[] newFields = new CachedField[this.fields.length - 1];
            System.arraycopy(this.fields, 0, newFields, 0, i);
            System.arraycopy(this.fields, i + 1, newFields, i, newFields.length - i);
            this.fields = newFields;
            this.removedFields.add(cachedField);
            return;
         }
      }

      for(int i = 0; i < this.transientFields.length; ++i) {
         CachedField cachedField = this.transientFields[i];
         if (cachedField == removeField) {
            CachedField[] newFields = new CachedField[this.transientFields.length - 1];
            System.arraycopy(this.transientFields, 0, newFields, 0, i);
            System.arraycopy(this.transientFields, i + 1, newFields, i, newFields.length - i);
            this.transientFields = newFields;
            this.removedFields.add(cachedField);
            return;
         }
      }

      throw new IllegalArgumentException("Field \"" + removeField + "\" not found on class: " + this.type.getName());
   }

   public CachedField[] getFields() {
      return this.fields;
   }

   public CachedField[] getTransientFields() {
      return this.transientFields;
   }

   public Class getType() {
      return this.type;
   }

   public Kryo getKryo() {
      return this.kryo;
   }

   public boolean getUseAsmEnabled() {
      return this.config.isUseAsm();
   }

   public boolean getUseMemRegions() {
      return this.useMemRegions;
   }

   public boolean getCopyTransient() {
      return this.config.isCopyTransient();
   }

   public boolean getSerializeTransient() {
      return this.config.isSerializeTransient();
   }

   protected Object createCopy(Kryo kryo, Object original) {
      return kryo.newInstance(original.getClass());
   }

   public Object copy(Kryo kryo, Object original) {
      T copy = (T)this.createCopy(kryo, original);
      kryo.reference(copy);
      if (this.config.isCopyTransient()) {
         int i = 0;

         for(int n = this.transientFields.length; i < n; ++i) {
            this.transientFields[i].copy(original, copy);
         }
      }

      int i = 0;

      for(int n = this.fields.length; i < n; ++i) {
         this.fields[i].copy(original, copy);
      }

      return copy;
   }

   final Generics getGenericsScope() {
      return this.genericsScope;
   }

   static {
      try {
         unsafeUtilClass = FieldSerializer.class.getClassLoader().loadClass("com.esotericsoftware.kryo.util.UnsafeUtil");
         Method unsafeMethod = unsafeUtilClass.getMethod("unsafe");
         sortFieldsByOffsetMethod = unsafeUtilClass.getMethod("sortFieldsByOffset", List.class);
         Object unsafe = unsafeMethod.invoke((Object)null);
         if (unsafe != null) {
            unsafeAvailable = true;
         }
      } catch (Throwable var2) {
         if (Log.TRACE) {
            Log.trace("kryo", "sun.misc.Unsafe is unavailable.");
         }
      }

   }

   public abstract static class CachedField {
      Field field;
      FieldAccess access;
      Class valueClass;
      Serializer serializer;
      boolean canBeNull;
      int accessIndex = -1;
      long offset = -1L;
      boolean varIntsEnabled = true;

      public void setClass(Class valueClass) {
         this.valueClass = valueClass;
         this.serializer = null;
      }

      public void setClass(Class valueClass, Serializer serializer) {
         this.valueClass = valueClass;
         this.serializer = serializer;
      }

      public void setSerializer(Serializer serializer) {
         this.serializer = serializer;
      }

      public Serializer getSerializer() {
         return this.serializer;
      }

      public void setCanBeNull(boolean canBeNull) {
         this.canBeNull = canBeNull;
      }

      public Field getField() {
         return this.field;
      }

      public String toString() {
         return this.field.getName();
      }

      public abstract void write(Output var1, Object var2);

      public abstract void read(Input var1, Object var2);

      public abstract void copy(Object var1, Object var2);
   }

   public interface CachedFieldNameStrategy {
      CachedFieldNameStrategy DEFAULT = new CachedFieldNameStrategy() {
         public String getName(CachedField cachedField) {
            return cachedField.field.getName();
         }
      };
      CachedFieldNameStrategy EXTENDED = new CachedFieldNameStrategy() {
         public String getName(CachedField cachedField) {
            return cachedField.field.getDeclaringClass().getSimpleName() + "." + cachedField.field.getName();
         }
      };

      String getName(CachedField var1);
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD})
   public @interface Bind {
      Class value();
   }

   public interface CachedFieldFactory {
      CachedField createCachedField(Class var1, Field var2, FieldSerializer var3);
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD})
   public @interface Optional {
      String value();
   }
}
