package org.sparkproject.guava.cache;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;

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
