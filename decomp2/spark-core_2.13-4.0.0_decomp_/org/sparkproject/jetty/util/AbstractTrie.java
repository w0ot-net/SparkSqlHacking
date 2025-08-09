package org.sparkproject.jetty.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

abstract class AbstractTrie implements Index.Mutable {
   final boolean _caseSensitive;

   protected AbstractTrie(boolean caseSensitive) {
      this._caseSensitive = caseSensitive;
   }

   public boolean isCaseInsensitive() {
      return !this._caseSensitive;
   }

   public boolean isCaseSensitive() {
      return this._caseSensitive;
   }

   public boolean put(Object v) {
      return this.put(v.toString(), v);
   }

   public Object remove(String s) {
      V o = (V)this.get(s);
      this.put(s, (Object)null);
      return o;
   }

   public Object get(String s) {
      return this.get(s, 0, s.length());
   }

   public Object get(ByteBuffer b) {
      return this.get(b, 0, b.remaining());
   }

   public Object getBest(String s) {
      return this.getBest(s, 0, s.length());
   }

   public Object getBest(byte[] b, int offset, int len) {
      return this.getBest(new String(b, offset, len, StandardCharsets.ISO_8859_1));
   }

   protected static int requiredCapacity(Set keys, boolean caseSensitive) {
      List<String> list = (List<String>)(caseSensitive ? new ArrayList(keys) : (List)keys.stream().map(String::toLowerCase).collect(Collectors.toList()));
      Collections.sort(list);
      return 1 + requiredCapacity(list, 0, list.size(), 0);
   }

   private static int requiredCapacity(List keys, int offset, int length, int index) {
      int required = 0;

      while(true) {
         Character nodeChar = null;

         for(int i = 0; i < length; ++i) {
            String k = (String)keys.get(offset + i);
            if (k.length() > index) {
               char c = k.charAt(index);
               if (nodeChar == null || c != nodeChar) {
                  ++required;
                  if (nodeChar != null) {
                     required += requiredCapacity(keys, offset, i, index + 1);
                  }

                  nodeChar = c;
                  offset += i;
                  length -= i;
                  i = 0;
               }
            }
         }

         if (nodeChar == null) {
            return required;
         }

         ++index;
      }
   }

   protected boolean putAll(Map contents) {
      for(Map.Entry entry : contents.entrySet()) {
         if (!this.put((String)entry.getKey(), entry.getValue())) {
            return false;
         }
      }

      return true;
   }
}
