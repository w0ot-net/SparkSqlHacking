package org.sparkproject.spark_core.protobuf;

/** @deprecated */
@Deprecated
public class SingleFieldBuilderV3 implements AbstractMessage.BuilderParent {
   private AbstractMessage.BuilderParent parent;
   private AbstractMessage.Builder builder;
   private AbstractMessage message;
   private boolean isClean;

   /** @deprecated */
   @Deprecated
   public SingleFieldBuilderV3(AbstractMessage message, AbstractMessage.BuilderParent parent, boolean isClean) {
      this.message = (AbstractMessage)Internal.checkNotNull(message);
      this.parent = parent;
      this.isClean = isClean;
   }

   /** @deprecated */
   @Deprecated
   public void dispose() {
      this.parent = null;
   }

   /** @deprecated */
   @Deprecated
   public AbstractMessage getMessage() {
      if (this.message == null) {
         this.message = (AbstractMessage)this.builder.buildPartial();
      }

      return this.message;
   }

   /** @deprecated */
   @Deprecated
   public AbstractMessage build() {
      this.isClean = true;
      return this.getMessage();
   }

   /** @deprecated */
   @Deprecated
   public AbstractMessage.Builder getBuilder() {
      if (this.builder == null) {
         this.builder = (AbstractMessage.Builder)this.message.newBuilderForType(this);
         this.builder.mergeFrom((Message)this.message);
         this.builder.markClean();
      }

      return this.builder;
   }

   /** @deprecated */
   @Deprecated
   public MessageOrBuilder getMessageOrBuilder() {
      return (MessageOrBuilder)(this.builder != null ? this.builder : this.message);
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   public SingleFieldBuilderV3 setMessage(AbstractMessage message) {
      this.message = (AbstractMessage)Internal.checkNotNull(message);
      if (this.builder != null) {
         this.builder.dispose();
         this.builder = null;
      }

      this.onChanged();
      return this;
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   public SingleFieldBuilderV3 mergeFrom(AbstractMessage value) {
      if (this.builder == null && this.message == this.message.getDefaultInstanceForType()) {
         this.message = value;
      } else {
         this.getBuilder().mergeFrom((Message)value);
      }

      this.onChanged();
      return this;
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   public SingleFieldBuilderV3 clear() {
      this.message = (AbstractMessage)(this.message != null ? this.message.getDefaultInstanceForType() : this.builder.getDefaultInstanceForType());
      if (this.builder != null) {
         this.builder.dispose();
         this.builder = null;
      }

      this.onChanged();
      this.isClean = true;
      return this;
   }

   /** @deprecated */
   @Deprecated
   private void onChanged() {
      if (this.builder != null) {
         this.message = null;
      }

      if (this.isClean && this.parent != null) {
         this.parent.markDirty();
         this.isClean = false;
      }

   }

   /** @deprecated */
   @Deprecated
   public void markDirty() {
      this.onChanged();
   }
}
