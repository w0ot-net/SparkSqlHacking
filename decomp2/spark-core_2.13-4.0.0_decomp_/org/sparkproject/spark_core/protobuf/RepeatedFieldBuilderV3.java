package org.sparkproject.spark_core.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/** @deprecated */
public class RepeatedFieldBuilderV3 implements AbstractMessage.BuilderParent {
   private AbstractMessage.BuilderParent parent;
   private List messages;
   private boolean isMessagesListMutable;
   private List builders;
   private boolean isClean;
   private MessageExternalList externalMessageList;
   private BuilderExternalList externalBuilderList;
   private MessageOrBuilderExternalList externalMessageOrBuilderList;

   /** @deprecated */
   @Deprecated
   public RepeatedFieldBuilderV3(List messages, boolean isMessagesListMutable, AbstractMessage.BuilderParent parent, boolean isClean) {
      this.messages = messages;
      this.isMessagesListMutable = isMessagesListMutable;
      this.parent = parent;
      this.isClean = isClean;
   }

   /** @deprecated */
   @Deprecated
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

   /** @deprecated */
   @Deprecated
   public int getCount() {
      return this.messages.size();
   }

   /** @deprecated */
   @Deprecated
   public boolean isEmpty() {
      return this.messages.isEmpty();
   }

   /** @deprecated */
   @Deprecated
   public AbstractMessage getMessage(int index) {
      return this.getMessage(index, false);
   }

   private AbstractMessage getMessage(int index, boolean forBuild) {
      if (this.builders == null) {
         return (AbstractMessage)this.messages.get(index);
      } else {
         SingleFieldBuilderV3<MType, BType, IType> builder = (SingleFieldBuilderV3)this.builders.get(index);
         if (builder == null) {
            return (AbstractMessage)this.messages.get(index);
         } else {
            return forBuild ? builder.build() : builder.getMessage();
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public AbstractMessage.Builder getBuilder(int index) {
      this.ensureBuilders();
      SingleFieldBuilderV3<MType, BType, IType> builder = (SingleFieldBuilderV3)this.builders.get(index);
      if (builder == null) {
         MType message = (MType)((AbstractMessage)this.messages.get(index));
         builder = new SingleFieldBuilderV3(message, this, this.isClean);
         this.builders.set(index, builder);
      }

      return builder.getBuilder();
   }

   /** @deprecated */
   @Deprecated
   public MessageOrBuilder getMessageOrBuilder(int index) {
      if (this.builders == null) {
         return (MessageOrBuilder)this.messages.get(index);
      } else {
         SingleFieldBuilderV3<MType, BType, IType> builder = (SingleFieldBuilderV3)this.builders.get(index);
         return builder == null ? (MessageOrBuilder)this.messages.get(index) : builder.getMessageOrBuilder();
      }
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   public RepeatedFieldBuilderV3 setMessage(int index, AbstractMessage message) {
      Internal.checkNotNull(message);
      this.ensureMutableMessageList();
      this.messages.set(index, message);
      if (this.builders != null) {
         SingleFieldBuilderV3<MType, BType, IType> entry = (SingleFieldBuilderV3)this.builders.set(index, (Object)null);
         if (entry != null) {
            entry.dispose();
         }
      }

      this.onChanged();
      this.incrementModCounts();
      return this;
   }

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   public RepeatedFieldBuilderV3 addMessage(AbstractMessage message) {
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

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   public RepeatedFieldBuilderV3 addMessage(int index, AbstractMessage message) {
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

   /** @deprecated */
   @Deprecated
   @CanIgnoreReturnValue
   public RepeatedFieldBuilderV3 addAllMessages(Iterable values) {
      for(AbstractMessage value : values) {
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

      for(AbstractMessage value : values) {
         this.addMessage(value);
      }

      this.onChanged();
      this.incrementModCounts();
      return this;
   }

   /** @deprecated */
   @Deprecated
   public AbstractMessage.Builder addBuilder(AbstractMessage message) {
      this.ensureMutableMessageList();
      this.ensureBuilders();
      SingleFieldBuilderV3<MType, BType, IType> builder = new SingleFieldBuilderV3(message, this, this.isClean);
      this.messages.add((Object)null);
      this.builders.add(builder);
      this.onChanged();
      this.incrementModCounts();
      return builder.getBuilder();
   }

   /** @deprecated */
   @Deprecated
   public AbstractMessage.Builder addBuilder(int index, AbstractMessage message) {
      this.ensureMutableMessageList();
      this.ensureBuilders();
      SingleFieldBuilderV3<MType, BType, IType> builder = new SingleFieldBuilderV3(message, this, this.isClean);
      this.messages.add(index, (Object)null);
      this.builders.add(index, builder);
      this.onChanged();
      this.incrementModCounts();
      return builder.getBuilder();
   }

   /** @deprecated */
   @Deprecated
   public void remove(int index) {
      this.ensureMutableMessageList();
      this.messages.remove(index);
      if (this.builders != null) {
         SingleFieldBuilderV3<MType, BType, IType> entry = (SingleFieldBuilderV3)this.builders.remove(index);
         if (entry != null) {
            entry.dispose();
         }
      }

      this.onChanged();
      this.incrementModCounts();
   }

   /** @deprecated */
   @Deprecated
   public void clear() {
      this.messages = Collections.emptyList();
      this.isMessagesListMutable = false;
      if (this.builders != null) {
         for(SingleFieldBuilderV3 entry : this.builders) {
            if (entry != null) {
               entry.dispose();
            }
         }

         this.builders = null;
      }

      this.onChanged();
      this.incrementModCounts();
   }

   /** @deprecated */
   @Deprecated
   public List build() {
      this.isClean = true;
      if (!this.isMessagesListMutable && this.builders == null) {
         return this.messages;
      } else {
         boolean allMessagesInSync = true;
         if (!this.isMessagesListMutable) {
            for(int i = 0; i < this.messages.size(); ++i) {
               Message message = (Message)this.messages.get(i);
               SingleFieldBuilderV3<MType, BType, IType> builder = (SingleFieldBuilderV3)this.builders.get(i);
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

   /** @deprecated */
   @Deprecated
   public List getMessageList() {
      if (this.externalMessageList == null) {
         this.externalMessageList = new MessageExternalList(this);
      }

      return this.externalMessageList;
   }

   /** @deprecated */
   @Deprecated
   public List getBuilderList() {
      if (this.externalBuilderList == null) {
         this.externalBuilderList = new BuilderExternalList(this);
      }

      return this.externalBuilderList;
   }

   /** @deprecated */
   @Deprecated
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

   /** @deprecated */
   @Deprecated
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
      RepeatedFieldBuilderV3 builder;

      /** @deprecated */
      @Deprecated
      MessageExternalList(RepeatedFieldBuilderV3 builder) {
         this.builder = builder;
      }

      /** @deprecated */
      @Deprecated
      public int size() {
         return this.builder.getCount();
      }

      /** @deprecated */
      @Deprecated
      public AbstractMessage get(int index) {
         return this.builder.getMessage(index);
      }

      /** @deprecated */
      @Deprecated
      void incrementModCount() {
         ++this.modCount;
      }
   }

   private static class BuilderExternalList extends AbstractList implements List, RandomAccess {
      RepeatedFieldBuilderV3 builder;

      /** @deprecated */
      @Deprecated
      BuilderExternalList(RepeatedFieldBuilderV3 builder) {
         this.builder = builder;
      }

      /** @deprecated */
      @Deprecated
      public int size() {
         return this.builder.getCount();
      }

      /** @deprecated */
      @Deprecated
      public AbstractMessage.Builder get(int index) {
         return this.builder.getBuilder(index);
      }

      /** @deprecated */
      @Deprecated
      void incrementModCount() {
         ++this.modCount;
      }
   }

   private static class MessageOrBuilderExternalList extends AbstractList implements List, RandomAccess {
      RepeatedFieldBuilderV3 builder;

      MessageOrBuilderExternalList(RepeatedFieldBuilderV3 builder) {
         this.builder = builder;
      }

      /** @deprecated */
      @Deprecated
      public int size() {
         return this.builder.getCount();
      }

      /** @deprecated */
      @Deprecated
      public MessageOrBuilder get(int index) {
         return this.builder.getMessageOrBuilder(index);
      }

      /** @deprecated */
      @Deprecated
      void incrementModCount() {
         ++this.modCount;
      }
   }
}
