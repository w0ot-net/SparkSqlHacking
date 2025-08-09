package org.glassfish.hk2.utilities.general.internal;

import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.hk2.utilities.cache.CacheKeyFilter;
import org.glassfish.hk2.utilities.general.WeakHashClock;

public class WeakHashClockImpl implements WeakHashClock {
   private final boolean isWeak;
   private final ConcurrentHashMap byKeyNotWeak;
   private final WeakHashMap byKey;
   private final ReferenceQueue myQueue = new ReferenceQueue();
   private DoubleNode head;
   private DoubleNode tail;
   private DoubleNode dot;

   public WeakHashClockImpl(boolean isWeak) {
      this.isWeak = isWeak;
      if (isWeak) {
         this.byKey = new WeakHashMap();
         this.byKeyNotWeak = null;
      } else {
         this.byKeyNotWeak = new ConcurrentHashMap();
         this.byKey = null;
      }

   }

   private DoubleNode addBeforeDot(Object key, Object value) {
      DoubleNode<K, V> toAdd = new DoubleNode(key, value, this.myQueue);
      if (this.dot == null) {
         this.head = toAdd;
         this.tail = toAdd;
         this.dot = toAdd;
         return toAdd;
      } else if (this.dot.getPrevious() == null) {
         this.dot.setPrevious(toAdd);
         toAdd.setNext(this.dot);
         this.head = toAdd;
         return toAdd;
      } else {
         toAdd.setNext(this.dot);
         toAdd.setPrevious(this.dot.getPrevious());
         this.dot.getPrevious().setNext(toAdd);
         this.dot.setPrevious(toAdd);
         return toAdd;
      }
   }

   private void removeFromDLL(DoubleNode removeMe) {
      if (removeMe.getPrevious() != null) {
         removeMe.getPrevious().setNext(removeMe.getNext());
      }

      if (removeMe.getNext() != null) {
         removeMe.getNext().setPrevious(removeMe.getPrevious());
      }

      if (removeMe == this.head) {
         this.head = removeMe.getNext();
      }

      if (removeMe == this.tail) {
         this.tail = removeMe.getPrevious();
      }

      if (removeMe == this.dot) {
         this.dot = removeMe.getNext();
         if (this.dot == null) {
            this.dot = this.head;
         }
      }

      removeMe.setNext((DoubleNode)null);
      removeMe.setPrevious((DoubleNode)null);
   }

   public void put(Object key, Object value) {
      if (key != null && value != null) {
         synchronized(this) {
            if (this.isWeak) {
               this.removeStale();
            }

            DoubleNode<K, V> addMe = this.addBeforeDot(key, value);
            if (this.isWeak) {
               this.byKey.put(key, addMe);
            } else {
               this.byKeyNotWeak.put(key, addMe);
            }

         }
      } else {
         throw new IllegalArgumentException("key " + key + " or value " + value + " is null");
      }
   }

   public Object get(Object key) {
      if (key == null) {
         return null;
      } else {
         DoubleNode<K, V> node;
         if (this.isWeak) {
            synchronized(this) {
               this.removeStale();
               node = (DoubleNode)this.byKey.get(key);
            }
         } else {
            node = (DoubleNode)this.byKeyNotWeak.get(key);
         }

         return node == null ? null : node.getValue();
      }
   }

   public Object remove(Object key) {
      if (key == null) {
         return null;
      } else {
         synchronized(this) {
            DoubleNode<K, V> node;
            if (this.isWeak) {
               this.removeStale();
               node = (DoubleNode)this.byKey.remove(key);
            } else {
               node = (DoubleNode)this.byKeyNotWeak.remove(key);
            }

            if (node == null) {
               return null;
            } else {
               this.removeFromDLL(node);
               return node.getValue();
            }
         }
      }
   }

   public synchronized void releaseMatching(CacheKeyFilter filter) {
      if (filter != null) {
         if (this.isWeak) {
            this.removeStale();
         }

         LinkedList<K> removeMe = new LinkedList();

         for(DoubleNode<K, V> current = this.head; current != null; current = current.getNext()) {
            K key = (K)current.getWeakKey().get();
            if (key != null && filter.matches(key)) {
               removeMe.add(key);
            }
         }

         for(Object removeKey : removeMe) {
            this.remove(removeKey);
         }

      }
   }

   public int size() {
      if (this.isWeak) {
         synchronized(this) {
            this.removeStale();
            return this.byKey.size();
         }
      } else {
         return this.byKeyNotWeak.size();
      }
   }

   private DoubleNode moveDot() {
      if (this.dot == null) {
         return null;
      } else {
         DoubleNode<K, V> returnSource = this.dot;
         this.dot = returnSource.getNext();
         if (this.dot == null) {
            this.dot = this.head;
         }

         return returnSource;
      }
   }

   private DoubleNode moveDotNoWeak() {
      DoubleNode<K, V> original = this.moveDot();
      DoubleNode<K, V> retVal = original;
      if (original == null) {
         return null;
      } else {
         K key;
         while((key = (K)retVal.getWeakKey().get()) == null) {
            retVal = this.moveDot();
            if (retVal == null) {
               return null;
            }

            if (retVal == original) {
               return null;
            }
         }

         retVal.setHardenedKey(key);
         return retVal;
      }
   }

   public synchronized Map.Entry next() {
      DoubleNode<K, V> hardenedNode = this.moveDotNoWeak();
      if (hardenedNode == null) {
         return null;
      } else {
         Map.Entry var4;
         try {
            final K key = (K)hardenedNode.getHardenedKey();
            final V value = (V)hardenedNode.getValue();
            var4 = new Map.Entry() {
               public Object getKey() {
                  return key;
               }

               public Object getValue() {
                  return value;
               }

               public Object setValue(Object valuex) {
                  throw new AssertionError("not implemented");
               }
            };
         } finally {
            hardenedNode.setHardenedKey((Object)null);
            this.removeStale();
         }

         return var4;
      }
   }

   public synchronized void clear() {
      if (this.isWeak) {
         this.byKey.clear();
      } else {
         this.byKeyNotWeak.clear();
      }

      this.head = this.tail = this.dot = null;
   }

   public synchronized void clearStaleReferences() {
      this.removeStale();
   }

   private void removeStale() {
      boolean goOn;
      for(goOn = false; this.myQueue.poll() != null; goOn = true) {
      }

      if (goOn) {
         DoubleNode<K, V> next;
         for(DoubleNode<K, V> current = this.head; current != null; current = next) {
            next = current.getNext();
            if (current.getWeakKey().get() == null) {
               this.removeFromDLL(current);
            }
         }

      }
   }

   public boolean hasWeakKeys() {
      return this.isWeak;
   }

   public synchronized String toString() {
      StringBuffer sb = new StringBuffer("WeakHashClockImpl({");
      boolean first = true;
      DoubleNode<K, V> current = this.dot;
      if (current != null) {
         do {
            K key = (K)current.getWeakKey().get();
            String keyString = key == null ? "null" : key.toString();
            if (first) {
               first = false;
               sb.append(keyString);
            } else {
               sb.append("," + keyString);
            }

            current = current.getNext();
            if (current == null) {
               current = this.head;
            }
         } while(current != this.dot);
      }

      sb.append("}," + System.identityHashCode(this) + ")");
      return sb.toString();
   }
}
