package com.fasterxml.jackson.annotation;

import java.util.UUID;

public class ObjectIdGenerators {
   private abstract static class Base extends ObjectIdGenerator {
      protected final Class _scope;

      protected Base(Class scope) {
         this._scope = scope;
      }

      public final Class getScope() {
         return this._scope;
      }

      public boolean canUseFor(ObjectIdGenerator gen) {
         return gen.getClass() == this.getClass() && gen.getScope() == this._scope;
      }

      public abstract Object generateId(Object var1);
   }

   public abstract static class None extends ObjectIdGenerator {
   }

   public abstract static class PropertyGenerator extends Base {
      private static final long serialVersionUID = 1L;

      protected PropertyGenerator(Class scope) {
         super(scope);
      }
   }

   public static final class IntSequenceGenerator extends Base {
      private static final long serialVersionUID = 1L;
      protected transient int _nextValue;

      public IntSequenceGenerator() {
         this(Object.class, -1);
      }

      public IntSequenceGenerator(Class scope, int fv) {
         super(scope);
         this._nextValue = fv;
      }

      protected int initialValue() {
         return 1;
      }

      public ObjectIdGenerator forScope(Class scope) {
         return this._scope == scope ? this : new IntSequenceGenerator(scope, this._nextValue);
      }

      public ObjectIdGenerator newForSerialization(Object context) {
         return new IntSequenceGenerator(this._scope, this.initialValue());
      }

      public ObjectIdGenerator.IdKey key(Object key) {
         return key == null ? null : new ObjectIdGenerator.IdKey(this.getClass(), this._scope, key);
      }

      public Integer generateId(Object forPojo) {
         if (forPojo == null) {
            return null;
         } else {
            int id = this._nextValue++;
            return id;
         }
      }
   }

   public static final class UUIDGenerator extends Base {
      private static final long serialVersionUID = 1L;

      public UUIDGenerator() {
         this(Object.class);
      }

      private UUIDGenerator(Class scope) {
         super(Object.class);
      }

      public ObjectIdGenerator forScope(Class scope) {
         return this;
      }

      public ObjectIdGenerator newForSerialization(Object context) {
         return this;
      }

      public UUID generateId(Object forPojo) {
         return UUID.randomUUID();
      }

      public ObjectIdGenerator.IdKey key(Object key) {
         return key == null ? null : new ObjectIdGenerator.IdKey(this.getClass(), (Class)null, key);
      }

      public boolean canUseFor(ObjectIdGenerator gen) {
         return gen.getClass() == this.getClass();
      }
   }

   public static final class StringIdGenerator extends Base {
      private static final long serialVersionUID = 1L;

      public StringIdGenerator() {
         this(Object.class);
      }

      private StringIdGenerator(Class scope) {
         super(Object.class);
      }

      public ObjectIdGenerator forScope(Class scope) {
         return this;
      }

      public ObjectIdGenerator newForSerialization(Object context) {
         return this;
      }

      public String generateId(Object forPojo) {
         return UUID.randomUUID().toString();
      }

      public ObjectIdGenerator.IdKey key(Object key) {
         return key == null ? null : new ObjectIdGenerator.IdKey(this.getClass(), (Class)null, key);
      }

      public boolean canUseFor(ObjectIdGenerator gen) {
         return gen instanceof StringIdGenerator;
      }
   }
}
