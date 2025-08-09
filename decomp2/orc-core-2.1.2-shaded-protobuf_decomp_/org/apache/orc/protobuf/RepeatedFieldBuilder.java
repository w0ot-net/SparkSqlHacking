package org.apache.orc.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class RepeatedFieldBuilder implements GeneratedMessage.BuilderParent {
   private GeneratedMessage.BuilderParent parent;
   private List messages;
   private boolean isMessagesListMutable;
   private List builders;
   private boolean isClean;
   private MessageExternalList externalMessageList;
   private BuilderExternalList externalBuilderList;
   private MessageOrBuilderExternalList externalMessageOrBuilderList;

   public RepeatedFieldBuilder(List messages, boolean isMessagesListMutable, GeneratedMessage.BuilderParent parent, boolean isClean) {
      this.messages = messages;
      this.isMessagesListMutable = isMessagesListMutable;
      this.parent = parent;
      this.isClean = isClean;
   }

   public void dispose() {
      this.parent = null;
   }

   private void ensureMutableMessageList() {
      if (!this.isMessagesListMutable) {
         this.messages = new ArrayList(this.messages);
         this.isMessagesListMutable = true;
      }

   }

   private void ensureBuilders() {
      if (this.builders == null) {
         this.builders = new ArrayList(this.messages.size());

         for(int i = 0; i < this.messages.size(); ++i) {
            this.builders.add((Object)null);
         }
      }

   }

   public int getCount() {
      return this.messages.size();
   }

   public boolean isEmpty() {
      return this.messages.isEmpty();
   }

   public GeneratedMessage getMessage(int index) {
      return this.getMessage(index, false);
   }

   private GeneratedMessage getMessage(int index, boolean forBuild) {
      if (this.builders == null) {
         return (GeneratedMessage)this.messages.get(index);
      } else {
         SingleFieldBuilder<MType, BType, IType> builder = (SingleFieldBuilder)this.builders.get(index);
         if (builder == null) {
            return (GeneratedMessage)this.messages.get(index);
         } else {
            return forBuild ? builder.build() : builder.getMessage();
         }
      }
   }

   public GeneratedMessage.Builder getBuilder(int index) {
      this.ensureBuilders();
      SingleFieldBuilder<MType, BType, IType> builder = (SingleFieldBuilder)this.builders.get(index);
      if (builder == null) {
         MType message = (MType)((GeneratedMessage)this.messages.get(index));
         builder = new SingleFieldBuilder(message, this, this.isClean);
         this.builders.set(index, builder);
      }

      return builder.getBuilder();
   }

   public MessageOrBuilder getMessageOrBuilder(int index) {
      if (this.builders == null) {
         return (MessageOrBuilder)this.messages.get(index);
      } else {
         SingleFieldBuilder<MType, BType, IType> builder = (SingleFieldBuilder)this.builders.get(index);
         return builder == null ? (MessageOrBuilder)this.messages.get(index) : builder.getMessageOrBuilder();
      }
   }

   @CanIgnoreReturnValue
   public RepeatedFieldBuilder setMessage(int index, GeneratedMessage message) {
      Internal.checkNotNull(message);
      this.ensureMutableMessageList();
      this.messages.set(index, message);
      if (this.builders != null) {
         SingleFieldBuilder<MType, BType, IType> entry = (SingleFieldBuilder)this.builders.set(index, (Object)null);
         if (entry != null) {
            entry.dispose();
         }
      }

      this.onChanged();
      this.incrementModCounts();
      return this;
   }

   @CanIgnoreReturnValue
   public RepeatedFieldBuilder addMessage(GeneratedMessage message) {
      Internal.checkNotNull(message);
      this.ensureMutableMessageList();
      this.messages.add(message);
      if (this.builders != null) {
         this.builders.add((Object)null);
      }

      this.onChanged();
      this.incrementModCounts();
      return this;
   }

   @CanIgnoreReturnValue
   public RepeatedFieldBuilder addMessage(int index, GeneratedMessage message) {
      Internal.checkNotNull(message);
      this.ensureMutableMessageList();
      this.messages.add(index, message);
      if (this.builders != null) {
         this.builders.add(index, (Object)null);
      }

      this.onChanged();
      this.incrementModCounts();
      return this;
   }

   @CanIgnoreReturnValue
   public RepeatedFieldBuilder addAllMessages(Iterable values) {
      for(GeneratedMessage value : values) {
         Internal.checkNotNull(value);
      }

      int size = -1;
      if (values instanceof Collection) {
         Collection<?> collection = (Collection)values;
         if (collection.isEmpty()) {
            return this;
         }

         size = collection.size();
      }

      this.ensureMutableMessageList();
      if (size >= 0 && this.messages instanceof ArrayList) {
         ((ArrayList)this.messages).ensureCapacity(this.messages.size() + size);
      }

      for(GeneratedMessage value : values) {
         this.addMessage(value);
      }

      this.onChanged();
      this.incrementModCounts();
      return this;
   }

   public GeneratedMessage.Builder addBuilder(GeneratedMessage message) {
      this.ensureMutableMessageList();
      this.ensureBuilders();
      SingleFieldBuilder<MType, BType, IType> builder = new SingleFieldBuilder(message, this, this.isClean);
      this.messages.add((Object)null);
      this.builders.add(builder);
      this.onChanged();
      this.incrementModCounts();
      return builder.getBuilder();
   }

   public GeneratedMessage.Builder addBuilder(int index, GeneratedMessage message) {
      this.ensureMutableMessageList();
      this.ensureBuilders();
      SingleFieldBuilder<MType, BType, IType> builder = new SingleFieldBuilder(message, this, this.isClean);
      this.messages.add(index, (Object)null);
      this.builders.add(index, builder);
      this.onChanged();
      this.incrementModCounts();
      return builder.getBuilder();
   }

   public void remove(int index) {
      this.ensureMutableMessageList();
      this.messages.remove(index);
      if (this.builders != null) {
         SingleFieldBuilder<MType, BType, IType> entry = (SingleFieldBuilder)this.builders.remove(index);
         if (entry != null) {
            entry.dispose();
         }
      }

      this.onChanged();
      this.incrementModCounts();
   }

   public void clear() {
      this.messages = Collections.emptyList();
      this.isMessagesListMutable = false;
      if (this.builders != null) {
         for(SingleFieldBuilder entry : this.builders) {
            if (entry != null) {
               entry.dispose();
            }
         }

         this.builders = null;
      }

      this.onChanged();
      this.incrementModCounts();
   }

   public List build() {
      this.isClean = true;
      if (!this.isMessagesListMutable && this.builders == null) {
         return this.messages;
      } else {
         boolean allMessagesInSync = true;
         if (!this.isMessagesListMutable) {
            for(int i = 0; i < this.messages.size(); ++i) {
               Message message = (Message)this.messages.get(i);
               SingleFieldBuilder<MType, BType, IType> builder = (SingleFieldBuilder)this.builders.get(i);
               if (builder != null && builder.build() != message) {
                  allMessagesInSync = false;
                  break;
               }
            }

            if (allMessagesInSync) {
               return this.messages;
            }
         }

         this.ensureMutableMessageList();

         for(int i = 0; i < this.messages.size(); ++i) {
            this.messages.set(i, this.getMessage(i, true));
         }

         this.messages = Collections.unmodifiableList(this.messages);
         this.isMessagesListMutable = false;
         return this.messages;
      }
   }

   public List getMessageList() {
      if (this.externalMessageList == null) {
         this.externalMessageList = new MessageExternalList(this);
      }

      return this.externalMessageList;
   }

   public List getBuilderList() {
      if (this.externalBuilderList == null) {
         this.externalBuilderList = new BuilderExternalList(this);
      }

      return this.externalBuilderList;
   }

   public List getMessageOrBuilderList() {
      if (this.externalMessageOrBuilderList == null) {
         this.externalMessageOrBuilderList = new MessageOrBuilderExternalList(this);
      }

      return this.externalMessageOrBuilderList;
   }

   private void onChanged() {
      if (this.isClean && this.parent != null) {
         this.parent.markDirty();
         this.isClean = false;
      }

   }

   public void markDirty() {
      this.onChanged();
   }

   private void incrementModCounts() {
      if (this.externalMessageList != null) {
         this.externalMessageList.incrementModCount();
      }

      if (this.externalBuilderList != null) {
         this.externalBuilderList.incrementModCount();
      }

      if (this.externalMessageOrBuilderList != null) {
         this.externalMessageOrBuilderList.incrementModCount();
      }

   }

   private static class MessageExternalList extends AbstractList implements List, RandomAccess {
      RepeatedFieldBuilder builder;

      MessageExternalList(RepeatedFieldBuilder builder) {
         this.builder = builder;
      }

      public int size() {
         return this.builder.getCount();
      }

      public GeneratedMessage get(int index) {
         return this.builder.getMessage(index);
      }

      void incrementModCount() {
         ++this.modCount;
      }
   }

   private static class BuilderExternalList extends AbstractList implements List, RandomAccess {
      RepeatedFieldBuilder builder;

      BuilderExternalList(RepeatedFieldBuilder builder) {
         this.builder = builder;
      }

      public int size() {
         return this.builder.getCount();
      }

      public GeneratedMessage.Builder get(int index) {
         return this.builder.getBuilder(index);
      }

      void incrementModCount() {
         ++this.modCount;
      }
   }

   private static class MessageOrBuilderExternalList extends AbstractList implements List, RandomAccess {
      RepeatedFieldBuilder builder;

      MessageOrBuilderExternalList(RepeatedFieldBuilder builder) {
         this.builder = builder;
      }

      public int size() {
         return this.builder.getCount();
      }

      public MessageOrBuilder get(int index) {
         return this.builder.getMessageOrBuilder(index);
      }

      void incrementModCount() {
         ++this.modCount;
      }
   }
}
