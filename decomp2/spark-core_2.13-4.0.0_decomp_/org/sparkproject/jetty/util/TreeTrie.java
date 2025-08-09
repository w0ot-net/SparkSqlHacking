package org.sparkproject.jetty.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TreeTrie extends AbstractTrie {
   private static final int[] LOOKUP_INSENSITIVE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, 27, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, 29, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1};
   private static final int[] LOOKUP_SENSITIVE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, 27, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1};
   private static final int INDEX = 32;
   private final int[] _lookup;
   private final Node _root;

   public static AbstractTrie from(boolean caseSensitive, Map contents) {
      TreeTrie<V> trie = new TreeTrie(caseSensitive);
      return contents != null && !trie.putAll(contents) ? null : trie;
   }

   TreeTrie() {
      this(false);
   }

   TreeTrie(boolean caseSensitive) {
      super(caseSensitive);
      this._lookup = caseSensitive ? LOOKUP_SENSITIVE : LOOKUP_INSENSITIVE;
      this._root = new Node('\u0000');
   }

   public void clear() {
      Arrays.fill(this._root._nextIndex, (Object)null);
      this._root._nextOther.clear();
      this._root._key = null;
      this._root._value = null;
   }

   public boolean put(String s, Object v) {
      Node<V> t = this._root;
      int limit = s.length();

      for(int k = 0; k < limit; ++k) {
         char c = s.charAt(k);
         int index = c < 127 ? this._lookup[c] : -1;
         if (index >= 0) {
            if (t._nextIndex[index] == null) {
               t._nextIndex[index] = new Node(c);
            }

            t = t._nextIndex[index];
         } else {
            Node<V> n = null;

            for(int i = t._nextOther.size(); i-- > 0; n = null) {
               n = (Node)t._nextOther.get(i);
               if (n._c == c) {
                  break;
               }
            }

            if (n == null) {
               n = new Node(c);
               t._nextOther.add(n);
            }

            t = n;
         }
      }

      t._key = v == null ? null : s;
      t._value = v;
      return true;
   }

   public Object get(String s, int offset, int len) {
      Node<V> t = this._root;

      for(int i = 0; i < len; ++i) {
         char c = s.charAt(offset + i);
         int index = c < 127 ? this._lookup[c] : -1;
         if (index >= 0) {
            if (t._nextIndex[index] == null) {
               return null;
            }

            t = t._nextIndex[index];
         } else {
            Node<V> n = null;

            for(int j = t._nextOther.size(); j-- > 0; n = null) {
               n = (Node)t._nextOther.get(j);
               if (n._c == c) {
                  break;
               }
            }

            if (n == null) {
               return null;
            }

            t = n;
         }
      }

      return t._value;
   }

   public Object get(ByteBuffer b, int offset, int len) {
      Node<V> t = this._root;

      for(int i = 0; i < len; ++i) {
         byte c = b.get(offset + i);
         int index = c >= 0 && c < 127 ? this._lookup[c] : -1;
         if (index >= 0) {
            if (t._nextIndex[index] == null) {
               return null;
            }

            t = t._nextIndex[index];
         } else {
            Node<V> n = null;

            for(int j = t._nextOther.size(); j-- > 0; n = null) {
               n = (Node)t._nextOther.get(j);
               if (n._c == c) {
                  break;
               }
            }

            if (n == null) {
               return null;
            }

            t = n;
         }
      }

      return t._value;
   }

   public Object getBest(byte[] b, int offset, int len) {
      return this.getBest(this._root, b, offset, len);
   }

   private Object getBest(Node node, byte[] b, int offset, int len) {
      for(int i = 0; i < len; ++i) {
         byte c = b[offset + i];
         int index = c >= 0 && c < 127 ? this._lookup[c] : -1;
         Node<V> next;
         if (index >= 0) {
            if (node._nextIndex[index] == null) {
               break;
            }

            next = node._nextIndex[index];
         } else {
            Node<V> n = null;

            for(int j = node._nextOther.size(); j-- > 0; n = null) {
               n = (Node)node._nextOther.get(j);
               if (n._c == c) {
                  break;
               }
            }

            if (n == null) {
               break;
            }

            next = n;
         }

         if (node._key != null) {
            V best = (V)this.getBest(next, b, offset + i + 1, len - i - 1);
            if (best != null) {
               return best;
            }
            break;
         }

         node = next;
      }

      return node._value;
   }

   public boolean isEmpty() {
      return this.keySet().isEmpty();
   }

   public int size() {
      return this.keySet().size();
   }

   public Object getBest(String s, int offset, int len) {
      return this.getBest(this._root, s, offset, len);
   }

   private Object getBest(Node node, String s, int offset, int len) {
      for(int i = 0; i < len; ++i) {
         char c = s.charAt(offset + i);
         int index = c < 127 ? this._lookup[c] : -1;
         Node<V> next;
         if (index >= 0) {
            if (node._nextIndex[index] == null) {
               break;
            }

            next = node._nextIndex[index];
         } else {
            Node<V> n = null;

            for(int j = node._nextOther.size(); j-- > 0; n = null) {
               n = (Node)node._nextOther.get(j);
               if (n._c == c) {
                  break;
               }
            }

            if (n == null) {
               break;
            }

            next = n;
         }

         if (node._key != null) {
            V best = (V)this.getBest(next, s, offset + i + 1, len - i - 1);
            if (best != null) {
               return best;
            }
            break;
         }

         node = next;
      }

      return node._value;
   }

   public Object getBest(ByteBuffer b, int offset, int len) {
      return b.hasArray() ? this.getBest(b.array(), b.arrayOffset() + b.position() + offset, len) : this.getBest(this._root, b, offset, len);
   }

   private Object getBest(Node node, ByteBuffer b, int offset, int len) {
      int pos = b.position() + offset;

      for(int i = 0; i < len; ++i) {
         byte c = b.get(pos++);
         int index = c >= 0 && c < 127 ? this._lookup[c] : -1;
         Node<V> next;
         if (index >= 0) {
            if (node._nextIndex[index] == null) {
               break;
            }

            next = node._nextIndex[index];
         } else {
            Node<V> n = null;

            for(int j = node._nextOther.size(); j-- > 0; n = null) {
               n = (Node)node._nextOther.get(j);
               if (n._c == c) {
                  break;
               }
            }

            if (n == null) {
               break;
            }

            next = n;
         }

         if (node._key != null) {
            V best = (V)this.getBest(next, b, offset + i + 1, len - i - 1);
            if (best != null) {
               return best;
            }
            break;
         }

         node = next;
      }

      return node._value;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append("TT@").append(Integer.toHexString(this.hashCode())).append('{');
      buf.append("ci=").append(this.isCaseInsensitive()).append(';');
      toString(buf, this._root, "");
      buf.append('}');
      return buf.toString();
   }

   private static void toString(Appendable out, Node t, String separator) {
      label48:
      while(true) {
         if (t != null) {
            if (t._value != null) {
               try {
                  out.append(separator);
                  separator = ",";
                  out.append(t._key);
                  out.append('=');
                  out.append(t._value.toString());
               } catch (IOException e) {
                  throw new RuntimeException(e);
               }
            }

            int i = 0;

            while(i < 32) {
               Node<V> n = t._nextIndex[i++];
               if (n != null) {
                  if (i == 32 && t._nextOther.size() == 0) {
                     t = n;
                     continue label48;
                  }

                  toString(out, n, separator);
               }
            }

            i = t._nextOther.size();

            while(i-- > 0) {
               if (i == 0) {
                  t = (Node)t._nextOther.get(i);
                  continue label48;
               }

               toString(out, (Node)t._nextOther.get(i), separator);
            }
         }

         return;
      }
   }

   public Set keySet() {
      Set<String> keys = new HashSet();
      keySet(keys, this._root);
      return keys;
   }

   private static void keySet(Set set, Node t) {
      if (t != null) {
         if (t._key != null) {
            set.add(t._key);
         }

         for(int i = 0; i < 32; ++i) {
            if (t._nextIndex[i] != null) {
               keySet(set, t._nextIndex[i]);
            }
         }

         int i = t._nextOther.size();

         while(i-- > 0) {
            keySet(set, (Node)t._nextOther.get(i));
         }
      }

   }

   private static class Node {
      private final Node[] _nextIndex = new Node[32];
      private final List _nextOther = new ArrayList();
      private final char _c;
      private String _key;
      private Object _value;

      private Node(char c) {
         this._c = c;
      }
   }
}
