package org.glassfish.jersey.internal.guava;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMultimap extends AbstractSortedKeySortedSetMultimap {
   private static final long serialVersionUID = 0L;
   private transient Comparator keyComparator;
   private transient Comparator valueComparator;

   private TreeMultimap(Comparator keyComparator, Comparator valueComparator) {
      super(new TreeMap(keyComparator));
      this.keyComparator = keyComparator;
      this.valueComparator = valueComparator;
   }

   public static TreeMultimap create() {
      return new TreeMultimap(Ordering.natural(), Ordering.natural());
   }

   SortedSet createCollection() {
      return new TreeSet(this.valueComparator);
   }

   Collection createCollection(Object key) {
      if (key == null) {
         this.keyComparator().compare(key, key);
      }

      return super.createCollection(key);
   }

   private Comparator keyComparator() {
      return this.keyComparator;
   }

   public Comparator valueComparator() {
      return this.valueComparator;
   }

   NavigableMap backingMap() {
      return (NavigableMap)super.backingMap();
   }

   public NavigableSet get(Object key) {
      return (NavigableSet)super.get(key);
   }

   Collection unmodifiableCollectionSubclass(Collection collection) {
      return Sets.unmodifiableNavigableSet((NavigableSet)collection);
   }

   Collection wrapCollection(Object key, Collection collection) {
      return new AbstractMapBasedMultimap.WrappedNavigableSet(key, (NavigableSet)collection, (AbstractMapBasedMultimap.WrappedCollection)null);
   }

   public NavigableSet keySet() {
      return (NavigableSet)super.keySet();
   }

   NavigableSet createKeySet() {
      return new AbstractMapBasedMultimap.NavigableKeySet(this.backingMap());
   }

   public NavigableMap asMap() {
      return (NavigableMap)super.asMap();
   }

   NavigableMap createAsMap() {
      return new AbstractMapBasedMultimap.NavigableAsMap(this.backingMap());
   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.keyComparator());
      stream.writeObject(this.valueComparator());
      Serialization.writeMultimap(this, stream);
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.keyComparator = (Comparator)Preconditions.checkNotNull((Comparator)stream.readObject());
      this.valueComparator = (Comparator)Preconditions.checkNotNull((Comparator)stream.readObject());
      this.setMap(new TreeMap(this.keyComparator));
      Serialization.populateMultimap(this, stream);
   }
}
