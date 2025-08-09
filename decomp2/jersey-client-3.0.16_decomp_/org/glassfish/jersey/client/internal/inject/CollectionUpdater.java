package org.glassfish.jersey.client.internal.inject;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.ext.ParamConverter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.glassfish.jersey.client.inject.ParameterUpdater;
import org.glassfish.jersey.client.internal.LocalizationMessages;

abstract class CollectionUpdater extends AbstractParamValueUpdater implements ParameterUpdater {
   protected CollectionUpdater(ParamConverter converter, String parameterName, String defaultValue) {
      super(converter, parameterName, defaultValue);
   }

   public Collection update(Collection values) {
      Collection<String> results = Collections.EMPTY_LIST;
      if (values != null) {
         results = (Collection)values.stream().map((item) -> this.toString(item)).collect(Collectors.toList());
      } else if (this.isDefaultValueRegistered()) {
         results = Collections.singletonList(this.getDefaultValueString());
      }

      return results;
   }

   protected abstract Collection newCollection();

   public static CollectionUpdater getInstance(Class collectionType, ParamConverter converter, String parameterName, String defaultValue) {
      if (List.class == collectionType) {
         return new ListValueOf(converter, parameterName, defaultValue);
      } else if (Set.class == collectionType) {
         return new SetValueOf(converter, parameterName, defaultValue);
      } else if (SortedSet.class == collectionType) {
         return new SortedSetValueOf(converter, parameterName, defaultValue);
      } else {
         throw new ProcessingException(LocalizationMessages.COLLECTION_UPDATER_TYPE_UNSUPPORTED());
      }
   }

   private static final class ListValueOf extends CollectionUpdater {
      ListValueOf(ParamConverter converter, String parameter, String defaultValue) {
         super(converter, parameter, defaultValue);
      }

      protected List newCollection() {
         return new ArrayList();
      }
   }

   private static final class SetValueOf extends CollectionUpdater {
      SetValueOf(ParamConverter converter, String parameter, String defaultValue) {
         super(converter, parameter, defaultValue);
      }

      protected Set newCollection() {
         return new HashSet();
      }
   }

   private static final class SortedSetValueOf extends CollectionUpdater {
      SortedSetValueOf(ParamConverter converter, String parameter, String defaultValue) {
         super(converter, parameter, defaultValue);
      }

      protected SortedSet newCollection() {
         return new TreeSet();
      }
   }
}
