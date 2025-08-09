package org.glassfish.jersey.internal.guava;

public final class MoreObjects {
   private MoreObjects() {
   }

   public static ToStringHelper toStringHelper(Object self) {
      return new ToStringHelper(simpleName(self.getClass()));
   }

   private static String simpleName(Class clazz) {
      String name = clazz.getName();
      name = name.replaceAll("\\$[0-9]+", "\\$");
      int start = name.lastIndexOf(36);
      if (start == -1) {
         start = name.lastIndexOf(46);
      }

      return name.substring(start + 1);
   }

   public static final class ToStringHelper {
      private final String className;
      private final ValueHolder holderHead;
      private ValueHolder holderTail;
      private final boolean omitNullValues;

      private ToStringHelper(String className) {
         this.holderHead = new ValueHolder();
         this.holderTail = this.holderHead;
         this.omitNullValues = false;
         this.className = (String)Preconditions.checkNotNull(className);
      }

      public ToStringHelper add(String name, Object value) {
         return this.addHolder(name, value);
      }

      public ToStringHelper add(String name, int value) {
         return this.addHolder(name, String.valueOf(value));
      }

      public ToStringHelper add(String name, long value) {
         return this.addHolder(name, String.valueOf(value));
      }

      public String toString() {
         String nextSeparator = "";
         StringBuilder builder = (new StringBuilder(32)).append(this.className).append('{');

         for(ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
            builder.append(nextSeparator);
            nextSeparator = ", ";
            if (valueHolder.name != null) {
               builder.append(valueHolder.name).append('=');
            }

            builder.append(valueHolder.value);
         }

         return builder.append('}').toString();
      }

      private ValueHolder addHolder() {
         ValueHolder valueHolder = new ValueHolder();
         this.holderTail = this.holderTail.next = valueHolder;
         return valueHolder;
      }

      private ToStringHelper addHolder(String name, Object value) {
         ValueHolder valueHolder = this.addHolder();
         valueHolder.value = value;
         valueHolder.name = (String)Preconditions.checkNotNull(name);
         return this;
      }

      private static final class ValueHolder {
         String name;
         Object value;
         ValueHolder next;

         private ValueHolder() {
         }
      }
   }
}
