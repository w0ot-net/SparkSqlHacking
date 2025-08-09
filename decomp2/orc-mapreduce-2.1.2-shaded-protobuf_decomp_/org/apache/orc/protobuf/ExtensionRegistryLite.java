package org.apache.orc.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite {
   private static volatile boolean eagerlyParseMessageSets = false;
   private static boolean doFullRuntimeInheritanceCheck = true;
   static final String EXTENSION_CLASS_NAME = "org.apache.orc.protobuf.Extension";
   private static volatile ExtensionRegistryLite emptyRegistry;
   static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
   private final Map extensionsByNumber;

   public static boolean isEagerlyParseMessageSets() {
      return eagerlyParseMessageSets;
   }

   public static void setEagerlyParseMessageSets(boolean isEagerlyParse) {
      eagerlyParseMessageSets = isEagerlyParse;
   }

   public static ExtensionRegistryLite newInstance() {
      return doFullRuntimeInheritanceCheck ? ExtensionRegistryFactory.create() : new ExtensionRegistryLite();
   }

   public static ExtensionRegistryLite getEmptyRegistry() {
      if (!doFullRuntimeInheritanceCheck) {
         return EMPTY_REGISTRY_LITE;
      } else {
         ExtensionRegistryLite result = emptyRegistry;
         if (result == null) {
            synchronized(ExtensionRegistryLite.class) {
               result = emptyRegistry;
               if (result == null) {
                  result = emptyRegistry = ExtensionRegistryFactory.createEmpty();
               }
            }
         }

         return result;
      }
   }

   public ExtensionRegistryLite getUnmodifiable() {
      return new ExtensionRegistryLite(this);
   }

   public GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(final MessageLite containingTypeDefaultInstance, final int fieldNumber) {
      return (GeneratedMessageLite.GeneratedExtension)this.extensionsByNumber.get(new ObjectIntPair(containingTypeDefaultInstance, fieldNumber));
   }

   public final void add(final GeneratedMessageLite.GeneratedExtension extension) {
      this.extensionsByNumber.put(new ObjectIntPair(extension.getContainingTypeDefaultInstance(), extension.getNumber()), extension);
   }

   public final void add(ExtensionLite extension) {
      if (GeneratedMessageLite.GeneratedExtension.class.isAssignableFrom(extension.getClass())) {
         this.add((GeneratedMessageLite.GeneratedExtension)extension);
      }

      if (doFullRuntimeInheritanceCheck && ExtensionRegistryFactory.isFullRegistry(this)) {
         try {
            this.getClass().getMethod("add", ExtensionRegistryLite.ExtensionClassHolder.INSTANCE).invoke(this, extension);
         } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Could not invoke ExtensionRegistry#add for %s", extension), e);
         }
      }

   }

   ExtensionRegistryLite() {
      this.extensionsByNumber = new HashMap();
   }

   ExtensionRegistryLite(ExtensionRegistryLite other) {
      if (other == EMPTY_REGISTRY_LITE) {
         this.extensionsByNumber = Collections.emptyMap();
      } else {
         this.extensionsByNumber = Collections.unmodifiableMap(other.extensionsByNumber);
      }

   }

   ExtensionRegistryLite(boolean empty) {
      this.extensionsByNumber = Collections.emptyMap();
   }

   private static class ExtensionClassHolder {
      static final Class INSTANCE = resolveExtensionClass();

      static Class resolveExtensionClass() {
         try {
            return Class.forName("org.apache.orc.protobuf.Extension");
         } catch (ClassNotFoundException var1) {
            return null;
         }
      }
   }

   private static final class ObjectIntPair {
      private final Object object;
      private final int number;

      ObjectIntPair(final Object object, final int number) {
         this.object = object;
         this.number = number;
      }

      public int hashCode() {
         return System.identityHashCode(this.object) * '\uffff' + this.number;
      }

      public boolean equals(final Object obj) {
         if (!(obj instanceof ObjectIntPair)) {
            return false;
         } else {
            ObjectIntPair other = (ObjectIntPair)obj;
            return this.object == other.object && this.number == other.number;
         }
      }
   }
}
