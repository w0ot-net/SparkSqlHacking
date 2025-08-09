package org.glassfish.jersey.server.internal.inject;

import jakarta.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

abstract class StringCollectionExtractor implements MultivaluedParameterExtractor {
   private final String parameter;
   private final String defaultValue;

   protected StringCollectionExtractor(String parameterName, String defaultValue) {
      this.parameter = parameterName;
      this.defaultValue = defaultValue;
   }

   public String getName() {
      return this.parameter;
   }

   public String getDefaultValueString() {
      return this.defaultValue;
   }

   public Collection extract(MultivaluedMap parameters) {
      List<String> stringList = (List)parameters.get(this.parameter);
      Collection<String> collection = this.newCollection();
      if (stringList != null) {
         collection.addAll(stringList);
      } else if (this.defaultValue != null) {
         collection.add(this.defaultValue);
      }

      return collection;
   }

   protected abstract Collection newCollection();

   public static StringCollectionExtractor getInstance(Class collectionType, String parameterName, String defaultValue) {
      if (List.class == collectionType) {
         return new ListString(parameterName, defaultValue);
      } else if (Set.class == collectionType) {
         return new SetString(parameterName, defaultValue);
      } else if (SortedSet.class == collectionType) {
         return new SortedSetString(parameterName, defaultValue);
      } else {
         throw new RuntimeException("Unsupported collection type: " + collectionType.getName());
      }
   }

   private static final class ListString extends StringCollectionExtractor {
      public ListString(String parameter, String defaultValue) {
         super(parameter, defaultValue);
      }

      protected List newCollection() {
         return new ArrayList();
      }
   }

   private static final class SetString extends StringCollectionExtractor {
      public SetString(String parameter, String defaultValue) {
         super(parameter, defaultValue);
      }

      protected Set newCollection() {
         return new HashSet();
      }
   }

   private static final class SortedSetString extends StringCollectionExtractor {
      public SortedSetString(String parameter, String defaultValue) {
         super(parameter, defaultValue);
      }

      protected SortedSet newCollection() {
         return new TreeSet();
      }
   }
}
