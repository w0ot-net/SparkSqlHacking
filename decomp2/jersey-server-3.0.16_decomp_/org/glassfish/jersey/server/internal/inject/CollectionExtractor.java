package org.glassfish.jersey.server.internal.inject;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.ParamConverter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.glassfish.jersey.server.internal.LocalizationMessages;

abstract class CollectionExtractor extends AbstractParamValueExtractor implements MultivaluedParameterExtractor {
   protected CollectionExtractor(ParamConverter converter, String parameterName, String defaultStringValue) {
      super(converter, parameterName, defaultStringValue);
   }

   public Collection extract(MultivaluedMap parameters) {
      List<String> stringList = (List)parameters.get(this.getName());
      Collection<T> valueList = this.newCollection();
      if (stringList != null) {
         for(String v : stringList) {
            valueList.add(this.fromString(v));
         }
      } else if (this.isDefaultValueRegistered()) {
         valueList.add(this.defaultValue());
      }

      return valueList;
   }

   protected abstract Collection newCollection();

   public static CollectionExtractor getInstance(Class collectionType, ParamConverter converter, String parameterName, String defaultValueString) {
      if (List.class == collectionType) {
         return new ListValueOf(converter, parameterName, defaultValueString);
      } else if (Set.class == collectionType) {
         return new SetValueOf(converter, parameterName, defaultValueString);
      } else if (SortedSet.class == collectionType) {
         return new SortedSetValueOf(converter, parameterName, defaultValueString);
      } else {
         throw new ProcessingException(LocalizationMessages.COLLECTION_EXTRACTOR_TYPE_UNSUPPORTED());
      }
   }

   private static final class ListValueOf extends CollectionExtractor {
      ListValueOf(ParamConverter converter, String parameter, String defaultValueString) {
         super(converter, parameter, defaultValueString);
      }

      protected List newCollection() {
         return new ArrayList();
      }
   }

   private static final class SetValueOf extends CollectionExtractor {
      SetValueOf(ParamConverter converter, String parameter, String defaultValueString) {
         super(converter, parameter, defaultValueString);
      }

      protected Set newCollection() {
         return new HashSet();
      }
   }

   private static final class SortedSetValueOf extends CollectionExtractor {
      SortedSetValueOf(ParamConverter converter, String parameter, String defaultValueString) {
         super(converter, parameter, defaultValueString);
      }

      protected SortedSet newCollection() {
         return new TreeSet();
      }
   }
}
