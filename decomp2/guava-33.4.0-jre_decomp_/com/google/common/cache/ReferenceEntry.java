package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
interface ReferenceEntry {
   @CheckForNull
   LocalCache.ValueReference getValueReference();

   void setValueReference(LocalCache.ValueReference valueReference);

   @CheckForNull
   ReferenceEntry getNext();

   int getHash();

   @CheckForNull
   Object getKey();

   long getAccessTime();

   void setAccessTime(long time);

   ReferenceEntry getNextInAccessQueue();

   void setNextInAccessQueue(ReferenceEntry next);

   ReferenceEntry getPreviousInAccessQueue();

   void setPreviousInAccessQueue(ReferenceEntry previous);

   long getWriteTime();

   void setWriteTime(long time);

   ReferenceEntry getNextInWriteQueue();

   void setNextInWriteQueue(ReferenceEntry next);

   ReferenceEntry getPreviousInWriteQueue();

   void setPreviousInWriteQueue(ReferenceEntry previous);
}
