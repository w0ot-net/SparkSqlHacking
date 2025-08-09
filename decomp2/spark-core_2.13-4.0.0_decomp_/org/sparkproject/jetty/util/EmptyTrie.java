package org.sparkproject.jetty.util;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class EmptyTrie extends AbstractTrie {
   private static final EmptyTrie SENSITIVE = new EmptyTrie(true);
   private static final EmptyTrie INSENSITIVE = new EmptyTrie(false);

   private EmptyTrie(boolean caseSensitive) {
      super(caseSensitive);
   }

   public static EmptyTrie instance(boolean caseSensitive) {
      return caseSensitive ? SENSITIVE : INSENSITIVE;
   }

   public void clear() {
   }

   public Object get(String s) {
      return null;
   }

   public Object get(ByteBuffer b) {
      return null;
   }

   public Object get(String s, int offset, int len) {
      return null;
   }

   public Object get(ByteBuffer b, int offset, int len) {
      return null;
   }

   public Object getBest(String s) {
      return null;
   }

   public Object getBest(byte[] b, int offset, int len) {
      return null;
   }

   public Object getBest(ByteBuffer b) {
      return null;
   }

   public Object getBest(byte[] b) {
      return null;
   }

   public Object getBest(String s, int offset, int len) {
      return null;
   }

   public Object getBest(ByteBuffer b, int offset, int len) {
      return null;
   }

   public boolean isEmpty() {
      return true;
   }

   public Set keySet() {
      return Collections.emptySet();
   }

   public boolean put(Object v) {
      Objects.requireNonNull(v);
      return false;
   }

   public boolean put(String s, Object v) {
      Objects.requireNonNull(s);
      return false;
   }

   public int size() {
      return 0;
   }

   protected boolean putAll(Map contents) {
      return false;
   }
}
