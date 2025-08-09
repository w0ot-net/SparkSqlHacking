package io.netty.handler.codec.http;

import io.netty.util.AsciiString;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;

public class HttpMethod implements Comparable {
   public static final HttpMethod OPTIONS = new HttpMethod("OPTIONS");
   public static final HttpMethod GET = new HttpMethod("GET");
   public static final HttpMethod HEAD = new HttpMethod("HEAD");
   public static final HttpMethod POST = new HttpMethod("POST");
   public static final HttpMethod PUT = new HttpMethod("PUT");
   public static final HttpMethod PATCH = new HttpMethod("PATCH");
   public static final HttpMethod DELETE = new HttpMethod("DELETE");
   public static final HttpMethod TRACE = new HttpMethod("TRACE");
   public static final HttpMethod CONNECT = new HttpMethod("CONNECT");
   private static final EnumNameMap methodMap;
   private final AsciiString name;

   public static HttpMethod valueOf(String name) {
      if (name == GET.name()) {
         return GET;
      } else if (name == POST.name()) {
         return POST;
      } else {
         HttpMethod result = (HttpMethod)methodMap.get(name);
         return result != null ? result : new HttpMethod(name);
      }
   }

   public HttpMethod(String name) {
      name = ObjectUtil.checkNonEmptyAfterTrim(name, "name");
      int index = HttpUtil.validateToken(name);
      if (index != -1) {
         throw new IllegalArgumentException("Illegal character in HTTP Method: 0x" + Integer.toHexString(name.charAt(index)));
      } else {
         this.name = AsciiString.cached(name);
      }
   }

   public String name() {
      return this.name.toString();
   }

   public AsciiString asciiName() {
      return this.name;
   }

   public int hashCode() {
      return this.name().hashCode();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof HttpMethod)) {
         return false;
      } else {
         HttpMethod that = (HttpMethod)o;
         return this.name().equals(that.name());
      }
   }

   public String toString() {
      return this.name.toString();
   }

   public int compareTo(HttpMethod o) {
      return o == this ? 0 : this.name().compareTo(o.name());
   }

   static {
      methodMap = new EnumNameMap(new EnumNameMap.Node[]{new EnumNameMap.Node(OPTIONS.toString(), OPTIONS), new EnumNameMap.Node(GET.toString(), GET), new EnumNameMap.Node(HEAD.toString(), HEAD), new EnumNameMap.Node(POST.toString(), POST), new EnumNameMap.Node(PUT.toString(), PUT), new EnumNameMap.Node(PATCH.toString(), PATCH), new EnumNameMap.Node(DELETE.toString(), DELETE), new EnumNameMap.Node(TRACE.toString(), TRACE), new EnumNameMap.Node(CONNECT.toString(), CONNECT)});
   }

   private static final class EnumNameMap {
      private final Node[] values;
      private final int valuesMask;

      EnumNameMap(Node... nodes) {
         this.values = new Node[MathUtil.findNextPositivePowerOfTwo(nodes.length)];
         this.valuesMask = this.values.length - 1;

         for(Node node : nodes) {
            int i = hashCode(node.key) & this.valuesMask;
            if (this.values[i] != null) {
               throw new IllegalArgumentException("index " + i + " collision between values: [" + this.values[i].key + ", " + node.key + ']');
            }

            this.values[i] = node;
         }

      }

      Object get(String name) {
         Node<T> node = this.values[hashCode(name) & this.valuesMask];
         return node != null && node.key.equals(name) ? node.value : null;
      }

      private static int hashCode(String name) {
         return name.hashCode() >>> 6;
      }

      private static final class Node {
         final String key;
         final Object value;

         Node(String key, Object value) {
            this.key = key;
            this.value = value;
         }
      }
   }
}
