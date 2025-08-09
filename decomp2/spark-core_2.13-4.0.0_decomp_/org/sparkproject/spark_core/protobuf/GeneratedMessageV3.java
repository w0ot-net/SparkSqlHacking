package org.sparkproject.spark_core.protobuf;

import java.util.List;

/** @deprecated */
@Deprecated
public abstract class GeneratedMessageV3 extends GeneratedMessage.ExtendableMessage {
   private static final long serialVersionUID = 1L;

   /** @deprecated */
   @Deprecated
   protected GeneratedMessageV3() {
   }

   /** @deprecated */
   @Deprecated
   protected GeneratedMessageV3(Builder builder) {
      super(builder);
   }

   /** @deprecated */
   @Deprecated
   protected static Internal.IntList mutableCopy(Internal.IntList list) {
      return (Internal.IntList)makeMutableCopy(list);
   }

   /** @deprecated */
   @Deprecated
   protected static Internal.LongList mutableCopy(Internal.LongList list) {
      return (Internal.LongList)makeMutableCopy(list);
   }

   /** @deprecated */
   @Deprecated
   protected static Internal.FloatList mutableCopy(Internal.FloatList list) {
      return (Internal.FloatList)makeMutableCopy(list);
   }

   /** @deprecated */
   @Deprecated
   protected static Internal.DoubleList mutableCopy(Internal.DoubleList list) {
      return (Internal.DoubleList)makeMutableCopy(list);
   }

   /** @deprecated */
   @Deprecated
   protected static Internal.BooleanList mutableCopy(Internal.BooleanList list) {
      return (Internal.BooleanList)makeMutableCopy(list);
   }

   /** @deprecated */
   @Deprecated
   protected FieldAccessorTable internalGetFieldAccessorTable() {
      throw new UnsupportedOperationException("Should be overridden in gencode.");
   }

   /** @deprecated */
   @Deprecated
   protected Object newInstance(UnusedPrivateParameter unused) {
      throw new UnsupportedOperationException("This method must be overridden by the subclass.");
   }

   /** @deprecated */
   @Deprecated
   protected abstract Message.Builder newBuilderForType(BuilderParent parent);

   /** @deprecated */
   @Deprecated
   protected Message.Builder newBuilderForType(final AbstractMessage.BuilderParent parent) {
      return this.newBuilderForType(new BuilderParent() {
         public void markDirty() {
            parent.markDirty();
         }
      });
   }

   /** @deprecated */
   @Deprecated
   protected static final class UnusedPrivateParameter {
      static final UnusedPrivateParameter INSTANCE = new UnusedPrivateParameter();

      private UnusedPrivateParameter() {
      }
   }

   /** @deprecated */
   @Deprecated
   public abstract static class Builder extends GeneratedMessage.ExtendableBuilder {
      private BuilderParentImpl meAsParent;

      /** @deprecated */
      @Deprecated
      protected Builder() {
         super((AbstractMessage.BuilderParent)null);
      }

      /** @deprecated */
      @Deprecated
      protected Builder(BuilderParent builderParent) {
         super(builderParent);
      }

      /** @deprecated */
      @Deprecated
      public Builder clone() {
         return (Builder)super.clone();
      }

      /** @deprecated */
      @Deprecated
      public Builder clear() {
         return (Builder)super.clear();
      }

      /** @deprecated */
      @Deprecated
      protected FieldAccessorTable internalGetFieldAccessorTable() {
         throw new UnsupportedOperationException("Should be overridden in gencode.");
      }

      /** @deprecated */
      @Deprecated
      public Builder setField(final Descriptors.FieldDescriptor field, final Object value) {
         return (Builder)super.setField(field, value);
      }

      /** @deprecated */
      @Deprecated
      public Builder clearField(final Descriptors.FieldDescriptor field) {
         return (Builder)super.clearField(field);
      }

      /** @deprecated */
      @Deprecated
      public Builder clearOneof(final Descriptors.OneofDescriptor oneof) {
         return (Builder)super.clearOneof(oneof);
      }

      /** @deprecated */
      @Deprecated
      public Builder setRepeatedField(final Descriptors.FieldDescriptor field, final int index, final Object value) {
         return (Builder)super.setRepeatedField(field, index, value);
      }

      /** @deprecated */
      @Deprecated
      public Builder addRepeatedField(final Descriptors.FieldDescriptor field, final Object value) {
         return (Builder)super.addRepeatedField(field, value);
      }

      /** @deprecated */
      @Deprecated
      public Builder setUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.setUnknownFields(unknownFields);
      }

      /** @deprecated */
      @Deprecated
      public Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
         return (Builder)super.mergeUnknownFields(unknownFields);
      }

      /** @deprecated */
      @Deprecated
      protected BuilderParent getParentForChildren() {
         if (this.meAsParent == null) {
            this.meAsParent = new BuilderParentImpl();
         }

         return this.meAsParent;
      }

      /** @deprecated */
      @Deprecated
      private class BuilderParentImpl implements BuilderParent {
         private BuilderParentImpl() {
         }

         public void markDirty() {
            Builder.this.onChanged();
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public abstract static class ExtendableMessage extends GeneratedMessageV3 implements ExtendableMessageOrBuilder {
      /** @deprecated */
      @Deprecated
      protected ExtendableMessage() {
      }

      /** @deprecated */
      @Deprecated
      protected ExtendableMessage(ExtendableBuilder builder) {
         super(builder);
      }

      /** @deprecated */
      @Deprecated
      public final boolean hasExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.hasExtension(extension);
      }

      /** @deprecated */
      @Deprecated
      public final int getExtensionCount(final GeneratedMessage.GeneratedExtension extension) {
         return this.getExtensionCount(extension);
      }

      /** @deprecated */
      @Deprecated
      public final Object getExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.getExtension(extension);
      }

      /** @deprecated */
      @Deprecated
      public final Object getExtension(final GeneratedMessage.GeneratedExtension extension, final int index) {
         return this.getExtension(extension, index);
      }

      /** @deprecated */
      @Deprecated
      protected FieldAccessorTable internalGetFieldAccessorTable() {
         throw new UnsupportedOperationException("Should be overridden in gencode.");
      }

      /** @deprecated */
      @Deprecated
      protected ExtensionWriter newExtensionWriter() {
         return new ExtensionWriter(false);
      }

      /** @deprecated */
      @Deprecated
      protected ExtensionWriter newMessageSetExtensionWriter() {
         return new ExtensionWriter(true);
      }

      /** @deprecated */
      @Deprecated
      protected class ExtensionWriter extends GeneratedMessage.ExtensionWriter {
         private ExtensionWriter(final boolean messageSetWireFormat) {
            super(messageSetWireFormat);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public abstract static class ExtendableBuilder extends Builder implements ExtendableMessageOrBuilder {
      /** @deprecated */
      @Deprecated
      protected ExtendableBuilder() {
      }

      /** @deprecated */
      @Deprecated
      protected ExtendableBuilder(BuilderParent parent) {
         super(parent);
      }

      /** @deprecated */
      @Deprecated
      public final boolean hasExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.hasExtension(extension);
      }

      /** @deprecated */
      @Deprecated
      public final int getExtensionCount(final GeneratedMessage.GeneratedExtension extension) {
         return this.getExtensionCount(extension);
      }

      /** @deprecated */
      @Deprecated
      public final Object getExtension(final GeneratedMessage.GeneratedExtension extension) {
         return this.getExtension(extension);
      }

      /** @deprecated */
      @Deprecated
      public final Object getExtension(final GeneratedMessage.GeneratedExtension extension, final int index) {
         return this.getExtension(extension, index);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder setExtension(final GeneratedMessage.GeneratedExtension extension, final Object value) {
         return (ExtendableBuilder)this.setExtension(extension, value);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder setExtension(final GeneratedMessage.GeneratedExtension extension, final int index, final Object value) {
         return (ExtendableBuilder)this.setExtension(extension, index, value);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder addExtension(final GeneratedMessage.GeneratedExtension extension, final Object value) {
         return (ExtendableBuilder)this.addExtension(extension, value);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder clearExtension(final GeneratedMessage.GeneratedExtension extension) {
         return (ExtendableBuilder)this.clearExtension(extension);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder setField(final Descriptors.FieldDescriptor field, final Object value) {
         return (ExtendableBuilder)super.setField(field, value);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder clearField(final Descriptors.FieldDescriptor field) {
         return (ExtendableBuilder)super.clearField(field);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder clearOneof(final Descriptors.OneofDescriptor oneof) {
         return (ExtendableBuilder)super.clearOneof(oneof);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder setRepeatedField(final Descriptors.FieldDescriptor field, final int index, final Object value) {
         return (ExtendableBuilder)super.setRepeatedField(field, index, value);
      }

      /** @deprecated */
      @Deprecated
      public ExtendableBuilder addRepeatedField(final Descriptors.FieldDescriptor field, final Object value) {
         return (ExtendableBuilder)super.addRepeatedField(field, value);
      }

      /** @deprecated */
      @Deprecated
      protected final void mergeExtensionFields(final ExtendableMessage other) {
         super.mergeExtensionFields(other);
      }
   }

   /** @deprecated */
   @Deprecated
   public static final class FieldAccessorTable extends GeneratedMessage.FieldAccessorTable {
      /** @deprecated */
      @Deprecated
      public FieldAccessorTable(final Descriptors.Descriptor descriptor, final String[] camelCaseNames, final Class messageClass, final Class builderClass) {
         super(descriptor, camelCaseNames, messageClass, builderClass);
      }

      /** @deprecated */
      @Deprecated
      public FieldAccessorTable(final Descriptors.Descriptor descriptor, final String[] camelCaseNames) {
         super(descriptor, camelCaseNames);
      }

      /** @deprecated */
      @Deprecated
      public FieldAccessorTable ensureFieldAccessorsInitialized(Class messageClass, Class builderClass) {
         return (FieldAccessorTable)super.ensureFieldAccessorsInitialized(messageClass, builderClass);
      }
   }

   /** @deprecated */
   @Deprecated
   protected interface BuilderParent extends AbstractMessage.BuilderParent {
   }

   /** @deprecated */
   @Deprecated
   public interface ExtendableMessageOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder {
      /** @deprecated */
      @Deprecated
      boolean hasExtension(GeneratedMessage.GeneratedExtension extension);

      /** @deprecated */
      @Deprecated
      int getExtensionCount(GeneratedMessage.GeneratedExtension extension);

      /** @deprecated */
      @Deprecated
      Object getExtension(GeneratedMessage.GeneratedExtension extension);

      /** @deprecated */
      @Deprecated
      Object getExtension(GeneratedMessage.GeneratedExtension extension, int index);
   }
}
