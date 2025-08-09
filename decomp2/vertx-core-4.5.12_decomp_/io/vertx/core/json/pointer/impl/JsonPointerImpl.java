package io.vertx.core.json.pointer.impl;

import io.vertx.core.json.pointer.JsonPointer;
import io.vertx.core.json.pointer.JsonPointerIterator;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JsonPointerImpl implements JsonPointer {
   public static final Pattern VALID_POINTER_PATTERN = Pattern.compile("^(/(([^/~])|(~[01]))*)*$");
   URI startingUri;
   List decodedTokens;

   public JsonPointerImpl(URI uri) {
      this.startingUri = this.removeFragment(uri);
      this.decodedTokens = this.parse(uri.getFragment());
   }

   public JsonPointerImpl(String pointer) {
      this.startingUri = URI.create("#");
      this.decodedTokens = this.parse(pointer);
   }

   public JsonPointerImpl() {
      this.startingUri = URI.create("#");
      this.decodedTokens = this.parse((String)null);
   }

   protected JsonPointerImpl(URI startingUri, List decodedTokens) {
      this.startingUri = startingUri;
      this.decodedTokens = new ArrayList(decodedTokens);
   }

   private ArrayList parse(String pointer) {
      if (pointer != null && !"".equals(pointer)) {
         if (VALID_POINTER_PATTERN.matcher(pointer).matches()) {
            return (ArrayList)Arrays.stream(pointer.split("\\/", -1)).skip(1L).map(this::unescape).collect(Collectors.toCollection(ArrayList::new));
         } else {
            throw new IllegalArgumentException("The provided pointer is not a valid JSON Pointer");
         }
      } else {
         return new ArrayList();
      }
   }

   private String escape(String path) {
      return path.replace("~", "~0").replace("/", "~1");
   }

   private String unescape(String path) {
      return path.replace("~1", "/").replace("~0", "~");
   }

   public boolean isRootPointer() {
      return this.decodedTokens.size() == 0;
   }

   public boolean isLocalPointer() {
      return this.startingUri == null || this.startingUri.getSchemeSpecificPart() == null || this.startingUri.getSchemeSpecificPart().isEmpty();
   }

   public boolean isParent(JsonPointer c) {
      JsonPointerImpl child = (JsonPointerImpl)c;
      return child != null && (child.getURIWithoutFragment() == null && this.getURIWithoutFragment() == null || child.getURIWithoutFragment().equals(this.getURIWithoutFragment())) && this.decodedTokens.size() < child.decodedTokens.size() && (Boolean)IntStream.range(0, this.decodedTokens.size()).mapToObj((i) -> ((String)this.decodedTokens.get(i)).equals(child.decodedTokens.get(i))).reduce(Boolean::logicalAnd).orElse(true);
   }

   public String toString() {
      return this.isRootPointer() ? "" : "/" + String.join("/", (Iterable)this.decodedTokens.stream().map(this::escape).collect(Collectors.toList()));
   }

   public URI toURI() {
      return this.isRootPointer() ? this.replaceFragment(this.startingUri, "") : this.replaceFragment(this.startingUri, "/" + String.join("/", (Iterable)this.decodedTokens.stream().map(this::escape).collect(Collectors.toList())));
   }

   public URI getURIWithoutFragment() {
      return this.startingUri;
   }

   public JsonPointer append(String path) {
      this.decodedTokens.add(path);
      return this;
   }

   public JsonPointer append(int i) {
      return this.append(Integer.toString(i));
   }

   public JsonPointer append(List paths) {
      this.decodedTokens.addAll(paths);
      return this;
   }

   public JsonPointer append(JsonPointer pointer) {
      this.decodedTokens.addAll(((JsonPointerImpl)pointer).decodedTokens);
      return this;
   }

   public JsonPointer parent() {
      if (!this.isRootPointer()) {
         this.decodedTokens.remove(this.decodedTokens.size() - 1);
      }

      return this;
   }

   public JsonPointer copy() {
      return new JsonPointerImpl(this.startingUri, this.decodedTokens);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         JsonPointerImpl that = (JsonPointerImpl)o;
         return Objects.equals(this.startingUri, that.startingUri) && Objects.equals(this.decodedTokens, that.decodedTokens);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.startingUri, this.decodedTokens});
   }

   public Object queryOrDefault(Object value, JsonPointerIterator iterator, Object defaultValue) {
      if (this.isRootPointer()) {
         return iterator.isNull(value) ? defaultValue : value;
      } else {
         value = this.walkTillLastElement(value, iterator, false, (Consumer)null);
         String lastKey = (String)this.decodedTokens.get(this.decodedTokens.size() - 1);
         if (iterator.isObject(value)) {
            Object finalValue = iterator.getObjectParameter(value, lastKey, false);
            return !iterator.isNull(finalValue) ? finalValue : defaultValue;
         } else if (iterator.isArray(value) && !"-".equals(lastKey)) {
            try {
               Object finalValue = iterator.getArrayElement(value, Integer.parseInt(lastKey));
               return !iterator.isNull(finalValue) ? finalValue : defaultValue;
            } catch (NumberFormatException var6) {
               return defaultValue;
            }
         } else {
            return defaultValue;
         }
      }
   }

   public List tracedQuery(Object objectToQuery, JsonPointerIterator iterator) {
      List<Object> list = new ArrayList();
      if (this.isRootPointer() && !iterator.isNull(objectToQuery)) {
         list.add(objectToQuery);
      } else {
         list.getClass();
         Object lastValue = this.walkTillLastElement(objectToQuery, iterator, false, list::add);
         if (!iterator.isNull(lastValue)) {
            list.add(lastValue);
         }

         String lastKey = (String)this.decodedTokens.get(this.decodedTokens.size() - 1);
         if (iterator.isObject(lastValue)) {
            lastValue = iterator.getObjectParameter(lastValue, lastKey, false);
         } else if (iterator.isArray(lastValue) && !"-".equals(lastKey)) {
            try {
               lastValue = iterator.getArrayElement(lastValue, Integer.parseInt(lastKey));
            } catch (NumberFormatException var7) {
            }
         }

         if (!iterator.isNull(lastValue)) {
            list.add(lastValue);
         }
      }

      return list;
   }

   public Object write(Object valueToWrite, JsonPointerIterator iterator, Object newElement, boolean createOnMissing) {
      if (this.isRootPointer()) {
         return iterator.isNull(valueToWrite) ? null : newElement;
      } else {
         Object walkedValue = this.walkTillLastElement(valueToWrite, iterator, createOnMissing, (Consumer)null);
         return this.writeLastElement(walkedValue, iterator, newElement) ? valueToWrite : null;
      }
   }

   private Object walkTillLastElement(Object value, JsonPointerIterator iterator, boolean createOnMissing, Consumer onNewValue) {
      for(int i = 0; i < this.decodedTokens.size() - 1; ++i) {
         String k = (String)this.decodedTokens.get(i);
         if (i != 0 || !"".equals(k)) {
            if (iterator.isObject(value)) {
               if (onNewValue != null) {
                  onNewValue.accept(value);
               }

               value = iterator.getObjectParameter(value, k, createOnMissing);
            } else {
               if (!iterator.isArray(value)) {
                  return null;
               }

               if (onNewValue != null) {
                  onNewValue.accept(value);
               }

               try {
                  value = iterator.getArrayElement(value, Integer.parseInt(k));
                  if (iterator.isNull(value) && createOnMissing) {
                     value = iterator.getObjectParameter(value, k, true);
                  }
               } catch (NumberFormatException var8) {
                  value = null;
               }
            }
         }
      }

      return value;
   }

   private boolean writeLastElement(Object valueToWrite, JsonPointerIterator iterator, Object newElement) {
      String lastKey = (String)this.decodedTokens.get(this.decodedTokens.size() - 1);
      if (iterator.isObject(valueToWrite)) {
         return iterator.writeObjectParameter(valueToWrite, lastKey, newElement);
      } else if (iterator.isArray(valueToWrite)) {
         if ("-".equals(lastKey)) {
            return iterator.appendArrayElement(valueToWrite, newElement);
         } else {
            try {
               return iterator.writeArrayElement(valueToWrite, Integer.parseInt(lastKey), newElement);
            } catch (NumberFormatException var6) {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private URI removeFragment(URI oldURI) {
      return this.replaceFragment(oldURI, (String)null);
   }

   private URI replaceFragment(URI oldURI, String fragment) {
      try {
         return oldURI != null ? new URI(oldURI.getScheme(), oldURI.getSchemeSpecificPart(), fragment) : new URI((String)null, (String)null, fragment);
      } catch (URISyntaxException e) {
         e.printStackTrace();
         return null;
      }
   }
}
