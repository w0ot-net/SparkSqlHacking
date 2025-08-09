package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.AbstractMultivaluedMap;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.ext.RuntimeDelegate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.RuntimeDelegateDecorator;
import org.glassfish.jersey.internal.util.collection.ImmutableMultivaluedMap;
import org.glassfish.jersey.internal.util.collection.StringKeyIgnoreCaseMultivaluedMap;
import org.glassfish.jersey.internal.util.collection.Views;

public final class HeaderUtils {
   private static final Logger LOGGER = Logger.getLogger(HeaderUtils.class.getName());

   public static AbstractMultivaluedMap createInbound() {
      return new StringKeyIgnoreCaseMultivaluedMap();
   }

   public static MultivaluedMap empty() {
      return ImmutableMultivaluedMap.empty();
   }

   public static AbstractMultivaluedMap createOutbound() {
      return new StringKeyIgnoreCaseMultivaluedMap();
   }

   public static String asString(Object headerValue, RuntimeDelegate rd) {
      if (headerValue == null) {
         return null;
      } else if (headerValue instanceof String) {
         return (String)headerValue;
      } else {
         if (rd == null) {
            rd = RuntimeDelegate.getInstance();
         }

         RuntimeDelegate.HeaderDelegate hp = rd.createHeaderDelegate(headerValue.getClass());
         return hp != null ? hp.toString(headerValue) : headerValue.toString();
      }
   }

   public static String asString(Object headerValue, Configuration configuration) {
      return asString(headerValue, RuntimeDelegateDecorator.configured(configuration));
   }

   public static List asStringList(List headerValues, RuntimeDelegate rd) {
      return headerValues != null && !headerValues.isEmpty() ? Views.listView(headerValues, (input) -> input == null ? "[null]" : asString(input, rd)) : Collections.emptyList();
   }

   public static List asStringList(List headerValues, Configuration configuration) {
      return asStringList(headerValues, RuntimeDelegateDecorator.configured(configuration));
   }

   public static MultivaluedMap asStringHeaders(MultivaluedMap headers, Configuration configuration) {
      return headers == null ? null : asStringHeaders(headers, RuntimeDelegateDecorator.configured(configuration));
   }

   public static MultivaluedMap asStringHeaders(MultivaluedMap headers, RuntimeDelegate rd) {
      return headers == null ? null : new AbstractMultivaluedMap(Views.mapView(headers, (input) -> asStringList(input, rd))) {
      };
   }

   public static Map asStringHeadersSingleValue(MultivaluedMap headers, Configuration configuration) {
      if (headers == null) {
         return null;
      } else {
         RuntimeDelegate rd = RuntimeDelegateDecorator.configured(configuration);
         return Collections.unmodifiableMap((Map)headers.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (entry) -> asHeaderString((List)entry.getValue(), rd))));
      }
   }

   public static String asHeaderString(List values, RuntimeDelegate rd) {
      if (values == null) {
         return null;
      } else {
         Iterator<String> stringValues = asStringList(values, rd).iterator();
         if (!stringValues.hasNext()) {
            return "";
         } else {
            StringBuilder buffer = new StringBuilder((String)stringValues.next());

            while(stringValues.hasNext()) {
               buffer.append(',').append((String)stringValues.next());
            }

            return buffer.toString();
         }
      }
   }

   public static void checkHeaderChanges(Map headersSnapshot, MultivaluedMap currentHeaders, String connectorName, Configuration configuration) {
      if (LOGGER.isLoggable(Level.WARNING)) {
         RuntimeDelegate rd = RuntimeDelegateDecorator.configured(configuration);
         Set<String> changedHeaderNames = new HashSet();

         for(Map.Entry entry : currentHeaders.entrySet()) {
            if (!headersSnapshot.containsKey(entry.getKey())) {
               changedHeaderNames.add(entry.getKey());
            } else {
               String prevValue = (String)headersSnapshot.get(entry.getKey());
               String newValue = asHeaderString((List)currentHeaders.get(entry.getKey()), rd);
               if (!prevValue.equals(newValue)) {
                  changedHeaderNames.add(entry.getKey());
               }
            }
         }

         if (!changedHeaderNames.isEmpty() && LOGGER.isLoggable(Level.WARNING)) {
            LOGGER.warning(LocalizationMessages.SOME_HEADERS_NOT_SENT(connectorName, changedHeaderNames.toString()));
         }
      }

   }

   public static NewCookie getPreferredCookie(NewCookie first, NewCookie second) {
      return Comparator.nullsFirst(Comparator.comparingInt(NewCookie::getMaxAge).thenComparing(NewCookie::getExpiry, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Cookie::getPath, Comparator.nullsLast(Comparator.comparing(String::length)))).compare(first, second) > 0 ? first : second;
   }

   /** @deprecated */
   @Deprecated
   public static String asString(Object headerValue) {
      return asString(headerValue, (Configuration)null);
   }

   /** @deprecated */
   @Deprecated
   public static List asStringList(List headerValues) {
      return asStringList(headerValues, (Configuration)null);
   }

   /** @deprecated */
   @Deprecated
   public static MultivaluedMap asStringHeaders(MultivaluedMap headers) {
      return asStringHeaders(headers, (Configuration)null);
   }

   /** @deprecated */
   @Deprecated
   public static Map asStringHeadersSingleValue(MultivaluedMap headers) {
      return asStringHeadersSingleValue(headers, (Configuration)null);
   }

   /** @deprecated */
   @Deprecated
   public static void checkHeaderChanges(Map headersSnapshot, MultivaluedMap currentHeaders, String connectorName) {
      checkHeaderChanges(headersSnapshot, currentHeaders, connectorName, (Configuration)null);
   }

   private HeaderUtils() {
      throw new AssertionError("No instances allowed.");
   }
}
