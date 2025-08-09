package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class DefaultAttributeMap implements AttributeMap {
   private static final AtomicReferenceFieldUpdater ATTRIBUTES_UPDATER = AtomicReferenceFieldUpdater.newUpdater(DefaultAttributeMap.class, DefaultAttribute[].class, "attributes");
   private static final DefaultAttribute[] EMPTY_ATTRIBUTES = new DefaultAttribute[0];
   private volatile DefaultAttribute[] attributes;

   public DefaultAttributeMap() {
      this.attributes = EMPTY_ATTRIBUTES;
   }

   private static int searchAttributeByKey(DefaultAttribute[] sortedAttributes, AttributeKey key) {
      int low = 0;
      int high = sortedAttributes.length - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         DefaultAttribute midVal = sortedAttributes[mid];
         AttributeKey midValKey = midVal.key;
         if (midValKey == key) {
            return mid;
         }

         int midValKeyId = midValKey.id();
         int keyId = key.id();

         assert midValKeyId != keyId;

         boolean searchRight = midValKeyId < keyId;
         if (searchRight) {
            low = mid + 1;
         } else {
            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   private static void orderedCopyOnInsert(DefaultAttribute[] sortedSrc, int srcLength, DefaultAttribute[] copy, DefaultAttribute toInsert) {
      int id = toInsert.key.id();

      int i;
      for(i = srcLength - 1; i >= 0; --i) {
         DefaultAttribute attribute = sortedSrc[i];

         assert attribute.key.id() != id;

         if (attribute.key.id() < id) {
            break;
         }

         copy[i + 1] = sortedSrc[i];
      }

      copy[i + 1] = toInsert;
      int toCopy = i + 1;
      if (toCopy > 0) {
         System.arraycopy(sortedSrc, 0, copy, 0, toCopy);
      }

   }

   public Attribute attr(AttributeKey key) {
      ObjectUtil.checkNotNull(key, "key");
      DefaultAttribute newAttribute = null;

      DefaultAttribute[] attributes;
      DefaultAttribute[] newAttributes;
      do {
         attributes = this.attributes;
         int index = searchAttributeByKey(attributes, key);
         if (index >= 0) {
            DefaultAttribute attribute = attributes[index];

            assert attribute.key() == key;

            if (!attribute.isRemoved()) {
               return attribute;
            }

            if (newAttribute == null) {
               newAttribute = new DefaultAttribute(this, key);
            }

            int count = attributes.length;
            newAttributes = (DefaultAttribute[])Arrays.copyOf(attributes, count);
            newAttributes[index] = newAttribute;
         } else {
            if (newAttribute == null) {
               newAttribute = new DefaultAttribute(this, key);
            }

            int count = attributes.length;
            newAttributes = new DefaultAttribute[count + 1];
            orderedCopyOnInsert(attributes, count, newAttributes, newAttribute);
         }
      } while(!ATTRIBUTES_UPDATER.compareAndSet(this, attributes, newAttributes));

      return newAttribute;
   }

   public boolean hasAttr(AttributeKey key) {
      ObjectUtil.checkNotNull(key, "key");
      return searchAttributeByKey(this.attributes, key) >= 0;
   }

   private void removeAttributeIfMatch(AttributeKey key, DefaultAttribute value) {
      DefaultAttribute[] attributes;
      DefaultAttribute[] newAttributes;
      do {
         attributes = this.attributes;
         int index = searchAttributeByKey(attributes, key);
         if (index < 0) {
            return;
         }

         DefaultAttribute attribute = attributes[index];

         assert attribute.key() == key;

         if (attribute != value) {
            return;
         }

         int count = attributes.length;
         int newCount = count - 1;
         newAttributes = newCount == 0 ? EMPTY_ATTRIBUTES : new DefaultAttribute[newCount];
         System.arraycopy(attributes, 0, newAttributes, 0, index);
         int remaining = count - index - 1;
         if (remaining > 0) {
            System.arraycopy(attributes, index + 1, newAttributes, index, remaining);
         }
      } while(!ATTRIBUTES_UPDATER.compareAndSet(this, attributes, newAttributes));

   }

   private static final class DefaultAttribute extends AtomicReference implements Attribute {
      private static final AtomicReferenceFieldUpdater MAP_UPDATER = AtomicReferenceFieldUpdater.newUpdater(DefaultAttribute.class, DefaultAttributeMap.class, "attributeMap");
      private static final long serialVersionUID = -2661411462200283011L;
      private volatile DefaultAttributeMap attributeMap;
      private final AttributeKey key;

      DefaultAttribute(DefaultAttributeMap attributeMap, AttributeKey key) {
         this.attributeMap = attributeMap;
         this.key = key;
      }

      public AttributeKey key() {
         return this.key;
      }

      private boolean isRemoved() {
         return this.attributeMap == null;
      }

      public Object setIfAbsent(Object value) {
         while(true) {
            if (!this.compareAndSet((Object)null, value)) {
               T old = (T)this.get();
               if (old == null) {
                  continue;
               }

               return old;
            }

            return null;
         }
      }

      public Object getAndRemove() {
         DefaultAttributeMap attributeMap = this.attributeMap;
         boolean removed = attributeMap != null && MAP_UPDATER.compareAndSet(this, attributeMap, (Object)null);
         T oldValue = (T)this.getAndSet((Object)null);
         if (removed) {
            attributeMap.removeAttributeIfMatch(this.key, this);
         }

         return oldValue;
      }

      public void remove() {
         DefaultAttributeMap attributeMap = this.attributeMap;
         boolean removed = attributeMap != null && MAP_UPDATER.compareAndSet(this, attributeMap, (Object)null);
         this.set((Object)null);
         if (removed) {
            attributeMap.removeAttributeIfMatch(this.key, this);
         }

      }
   }
}
