package org.apache.orc.protobuf;

public class SingleFieldBuilder implements GeneratedMessage.BuilderParent {
   private GeneratedMessage.BuilderParent parent;
   private GeneratedMessage.Builder builder;
   private GeneratedMessage message;
   private boolean isClean;

   public SingleFieldBuilder(GeneratedMessage message, GeneratedMessage.BuilderParent parent, boolean isClean) {
      this.message = (GeneratedMessage)Internal.checkNotNull(message);
      this.parent = parent;
      this.isClean = isClean;
   }

   public void dispose() {
      this.parent = null;
   }

   public GeneratedMessage getMessage() {
      if (this.message == null) {
         this.message = (GeneratedMessage)this.builder.buildPartial();
      }

      return this.message;
   }

   public GeneratedMessage build() {
      this.isClean = true;
      return this.getMessage();
   }

   public GeneratedMessage.Builder getBuilder() {
      if (this.builder == null) {
         this.builder = (GeneratedMessage.Builder)this.message.newBuilderForType((GeneratedMessage.BuilderParent)this);
         this.builder.mergeFrom(this.message);
         this.builder.markClean();
      }

      return this.builder;
   }

   public MessageOrBuilder getMessageOrBuilder() {
      return (MessageOrBuilder)(this.builder != null ? this.builder : this.message);
   }

   @CanIgnoreReturnValue
   public SingleFieldBuilder setMessage(GeneratedMessage message) {
      this.message = (GeneratedMessage)Internal.checkNotNull(message);
      if (this.builder != null) {
         this.builder.dispose();
         this.builder = null;
      }

      this.onChanged();
      return this;
   }

   @CanIgnoreReturnValue
   public SingleFieldBuilder mergeFrom(GeneratedMessage value) {
      if (this.builder == null && this.message == this.message.getDefaultInstanceForType()) {
         this.message = value;
      } else {
         this.getBuilder().mergeFrom(value);
      }

      this.onChanged();
      return this;
   }

   @CanIgnoreReturnValue
   public SingleFieldBuilder clear() {
      this.message = (GeneratedMessage)(this.message != null ? this.message.getDefaultInstanceForType() : this.builder.getDefaultInstanceForType());
      if (this.builder != null) {
         this.builder.dispose();
         this.builder = null;
      }

      this.onChanged();
      this.isClean = true;
      return this;
   }

   private void onChanged() {
      if (this.builder != null) {
         this.message = null;
      }

      if (this.isClean && this.parent != null) {
         this.parent.markDirty();
         this.isClean = false;
      }

   }

   public void markDirty() {
      this.onChanged();
   }
}
