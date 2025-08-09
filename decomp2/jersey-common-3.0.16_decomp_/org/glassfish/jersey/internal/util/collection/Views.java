package org.glassfish.jersey.internal.util.collection;

import java.util.AbstractMap;
import java.util.AbstractSequentialList;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Views {
   private Views() {
   }

   public static List listView(final List originalList, final Function transformer) {
      return new AbstractSequentialList() {
         public ListIterator listIterator(final int index) {
            return new ListIterator() {
               final ListIterator iterator = originalList.listIterator(index);

               public boolean hasNext() {
                  return this.iterator.hasNext();
               }

               public Object next() {
                  return transformer.apply(this.iterator.next());
               }

               public boolean hasPrevious() {
                  return this.iterator.hasPrevious();
               }

               public Object previous() {
                  return transformer.apply(this.iterator.previous());
               }

               public int nextIndex() {
                  return this.iterator.nextIndex();
               }

               public int previousIndex() {
                  return this.iterator.previousIndex();
               }

               public void remove() {
                  this.iterator.remove();
               }

               public void set(Object t) {
                  throw new UnsupportedOperationException("Not supported.");
               }

               public void add(Object t) {
                  throw new UnsupportedOperationException("Not supported.");
               }
            };
         }

         public int size() {
            return originalList.size();
         }
      };
   }

   public static Map mapView(final Map originalMap, final Function valuesTransformer) {
      return new AbstractMap() {
         public Set entrySet() {
            return new AbstractSet() {
               Set originalSet = originalMap.entrySet();
               Iterator original;

               {
                  this.original = this.originalSet.iterator();
               }

               public Iterator iterator() {
                  return new Iterator() {
                     public boolean hasNext() {
                        return original.hasNext();
                     }

                     public Map.Entry next() {
                        final Map.Entry<K, O> next = (Map.Entry)original.next();
                        return new Map.Entry() {
                           public Object getKey() {
                              return next.getKey();
                           }

                           public Object getValue() {
                              return valuesTransformer.apply(next.getValue());
                           }

                           public Object setValue(Object value) {
                              throw new UnsupportedOperationException("Not supported.");
                           }
                        };
                     }

                     public void remove() {
                        original.remove();
                     }
                  };
               }

               public int size() {
                  return this.originalSet.size();
               }
            };
         }
      };
   }

   public static Set setUnionView(final Set set1, final Set set2) {
      Objects.requireNonNull(set1, "set1");
      Objects.requireNonNull(set2, "set2");
      return new AbstractSet() {
         public Iterator iterator() {
            return this.getUnion(set1, set2).iterator();
         }

         public int size() {
            return this.getUnion(set1, set2).size();
         }

         private Set getUnion(Set set1x, Set set2x) {
            HashSet<E> hashSet = new HashSet(set1);
            hashSet.addAll(set2);
            return hashSet;
         }
      };
   }

   public static Set setDiffView(final Set set1, final Set set2) {
      Objects.requireNonNull(set1, "set1");
      Objects.requireNonNull(set2, "set2");
      return new AbstractSet() {
         public Iterator iterator() {
            return this.getDiff(set1, set2).iterator();
         }

         public int size() {
            return this.getDiff(set1, set2).size();
         }

         private Set getDiff(final Set set1x, final Set set2x) {
            HashSet<E> hashSet = new HashSet();
            hashSet.addAll(set1);
            hashSet.addAll(set2);
            return (Set)hashSet.stream().filter(new Predicate() {
               public boolean test(Object e) {
                  return set1.contains(e) && !set2.contains(e);
               }
            }).collect(Collectors.toSet());
         }
      };
   }
}
