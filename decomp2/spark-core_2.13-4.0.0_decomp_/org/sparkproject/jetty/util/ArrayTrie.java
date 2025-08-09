package org.sparkproject.jetty.util;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class ArrayTrie extends AbstractTrie {
   public static int MAX_CAPACITY = 65535;
   private static final int ROW_SIZE = 48;
   private static final int BIG_ROW_INSENSITIVE = 22;
   private static final int BIG_ROW_SENSITIVE = 48;
   private static final int X = Integer.MIN_VALUE;
   private static final int[] LOOKUP_INSENSITIVE = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, 43, 44, 45, 46, 47, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 37, 38, 39, 40, 41, 42, -12, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -13, -14, -15, -16, 36, -17, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -18, -19, -20, -21, Integer.MIN_VALUE};
   private static final int[] LOOKUP_SENSITIVE = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, 43, 44, 45, 46, 47, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 37, 38, 39, 40, 41, 42, -12, -22, -23, -24, -25, -26, -27, -28, -29, -30, -31, -32, -33, -34, -35, -36, -37, -38, -39, -40, -41, -42, -43, -44, -45, -46, -47, -13, -14, -15, -16, 36, -17, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -18, -19, -20, -21, Integer.MIN_VALUE};
   private final char[] _table;
   private final int[] _lookup;
   private final Node[] _node;
   private final int _bigRowSize;
   private char _rows;

   public static ArrayTrie from(int capacity, boolean caseSensitive, Map contents) {
      if (capacity < 0) {
         return null;
      } else if (capacity > MAX_CAPACITY) {
         return null;
      } else {
         ArrayTrie<V> trie = new ArrayTrie(caseSensitive, capacity);
         return contents != null && !trie.putAll(contents) ? null : trie;
      }
   }

   ArrayTrie(int capacity) {
      this(false, capacity);
   }

   ArrayTrie(boolean caseSensitive, int capacity) {
      super(caseSensitive);
      this._bigRowSize = caseSensitive ? 48 : 22;
      if (capacity > MAX_CAPACITY) {
         throw new IllegalArgumentException("Capacity " + capacity + " > " + MAX_CAPACITY);
      } else {
         this._lookup = !caseSensitive ? LOOKUP_INSENSITIVE : LOOKUP_SENSITIVE;
         this._table = new char[capacity * 48];
         this._node = new Node[capacity];
      }
   }

   public void clear() {
      this._rows = 0;
      Arrays.fill(this._table, '\u0000');
      Arrays.fill(this._node, (Object)null);
   }

   public boolean put(String key, Object value) {
      int row = 0;
      int limit = key.length();

      for(int i = 0; i < limit; ++i) {
         char c = key.charAt(i);
         int column = c > 127 ? Integer.MIN_VALUE : this._lookup[c];
         if (column >= 0) {
            int idx = row * 48 + column;
            row = this._table[idx];
            if (row == 0) {
               if (this._rows == this._node.length - 1) {
                  return false;
               }

               row = this._table[idx] = ++this._rows;
            }
         } else if (column != Integer.MIN_VALUE) {
            int idx = -column;
            Node<V> node = this._node[row];
            if (node == null) {
               node = this._node[row] = new Node();
            }

            char[] big = node._bigRow;
            row = big != null && idx < big.length ? big[idx] : 0;
            if (row == 0) {
               if (this._rows == this._node.length - 1) {
                  return false;
               }

               if (big == null) {
                  big = node._bigRow = new char[idx + 1];
               } else if (idx >= big.length) {
                  big = node._bigRow = Arrays.copyOf(big, idx + 1);
               }

               row = big[idx] = ++this._rows;
            }
         } else {
            int last = row;
            row = 0;
            Node<V> node = this._node[last];
            if (node != null) {
               char[] big = node._bigRow;
               if (big != null) {
                  for(int idx = this._bigRowSize; idx < big.length; idx += 2) {
                     if (big[idx] == c) {
                        row = big[idx + 1];
                        break;
                     }
                  }
               }
            }

            if (row == 0) {
               if (this._rows == this._node.length - 1) {
                  return false;
               }

               if (node == null) {
                  node = this._node[last] = new Node();
               }

               char[] big = node._bigRow;
               if (big == null) {
                  big = node._bigRow = new char[this._bigRowSize + 2];
               } else {
                  big = node._bigRow = Arrays.copyOf(big, Math.max(big.length, this._bigRowSize) + 2);
               }

               big[big.length - 2] = c;
               row = big[big.length - 1] = ++this._rows;
            }
         }
      }

      Node<V> node = this._node[row];
      if (node == null) {
         node = this._node[row] = new Node();
      }

      node._key = key;
      node._value = value;
      return true;
   }

   private int lookup(int row, char c) {
      if (c < 128) {
         int column = this._lookup[c];
         if (column != Integer.MIN_VALUE) {
            if (column >= 0) {
               int idx = row * 48 + column;
               row = this._table[idx];
            } else {
               Node<V> node = this._node[row];
               char[] big = node == null ? null : this._node[row]._bigRow;
               int idx = -column;
               if (big == null || idx >= big.length) {
                  return -1;
               }

               row = big[idx];
            }

            return row == 0 ? -1 : row;
         }
      }

      Node<V> node = this._node[row];
      char[] big = node == null ? null : node._bigRow;
      if (big != null) {
         for(int i = this._bigRowSize; i < big.length; i += 2) {
            if (big[i] == c) {
               return big[i + 1];
            }
         }
      }

      return -1;
   }

   public Object get(String s, int offset, int len) {
      int row = 0;

      for(int i = 0; i < len; ++i) {
         char c = s.charAt(offset + i);
         row = this.lookup(row, c);
         if (row < 0) {
            return null;
         }
      }

      Node<V> node = this._node[row];
      return node == null ? null : node._value;
   }

   public Object get(ByteBuffer b, int offset, int len) {
      int row = 0;

      for(int i = 0; i < len; ++i) {
         byte c = b.get(offset + i);
         row = this.lookup(row, (char)(c & 255));
         if (row < 0) {
            return null;
         }
      }

      Node<V> node = this._node[row];
      return node == null ? null : node._value;
   }

   public Object getBest(byte[] b, int offset, int len) {
      return this.getBest(0, (byte[])b, offset, len);
   }

   public Object getBest(ByteBuffer b, int offset, int len) {
      return b.hasArray() ? this.getBest(0, (byte[])b.array(), b.arrayOffset() + b.position() + offset, len) : this.getBest(0, (ByteBuffer)b, offset, len);
   }

   public Object getBest(String s, int offset, int len) {
      return this.getBest(0, (String)s, offset, len);
   }

   private Object getBest(int row, String s, int offset, int len) {
      int pos = offset;

      for(int i = 0; i < len; ++i) {
         char c = s.charAt(pos++);
         int next = this.lookup(row, c);
         if (next < 0) {
            break;
         }

         Node<V> node = this._node[row];
         if (node != null && node._key != null) {
            V best = (V)this.getBest(next, s, offset + i + 1, len - i - 1);
            if (best != null) {
               return best;
            }

            return node._value;
         }

         row = next;
      }

      Node<V> node = this._node[row];
      return node == null ? null : node._value;
   }

   private Object getBest(int row, byte[] b, int offset, int len) {
      for(int i = 0; i < len; ++i) {
         byte c = b[offset + i];
         int next = this.lookup(row, (char)(c & 255));
         if (next < 0) {
            break;
         }

         Node<V> node = this._node[row];
         if (node != null && node._key != null) {
            V best = (V)this.getBest(next, b, offset + i + 1, len - i - 1);
            if (best != null) {
               return best;
            }

            return node._value;
         }

         row = next;
      }

      Node<V> node = this._node[row];
      return node == null ? null : node._value;
   }

   private Object getBest(int row, ByteBuffer b, int offset, int len) {
      int pos = b.position() + offset;

      for(int i = 0; i < len; ++i) {
         if (pos >= b.limit()) {
            return null;
         }

         byte c = b.get(pos++);
         int next = this.lookup(row, (char)(c & 255));
         if (next < 0) {
            break;
         }

         Node<V> node = this._node[row];
         if (node != null && node._key != null) {
            V best = (V)this.getBest(next, b, offset + i + 1, len - i - 1);
            if (best != null) {
               return best;
            }

            return node._value;
         }

         row = next;
      }

      Node<V> node = this._node[row];
      return node == null ? null : node._value;
   }

   public String toString() {
      String var10000 = Integer.toHexString(this.hashCode());
      return "AT@" + var10000 + "{cs=" + this.isCaseSensitive() + ";c=" + this._table.length / 48 + ";" + (String)Arrays.stream(this._node).filter((n) -> n != null && n._key != null).map(Node::toString).collect(Collectors.joining(",")) + "}";
   }

   public Set keySet() {
      return (Set)Arrays.stream(this._node).filter(Objects::nonNull).map((n) -> n._key).filter(Objects::nonNull).collect(Collectors.toSet());
   }

   public int size() {
      return this.keySet().size();
   }

   public boolean isEmpty() {
      return this.keySet().isEmpty();
   }

   public void dumpStdErr() {
      System.err.print("row:");

      for(int c = 0; c < 48; ++c) {
         for(int i = 0; i < 127; ++i) {
            if (this._lookup[i] == c) {
               System.err.printf("  %s", (char)i);
               break;
            }
         }
      }

      System.err.println();
      System.err.print("big:");

      for(int c = 0; c < this._bigRowSize; ++c) {
         for(int i = 0; i < 127; ++i) {
            if (-this._lookup[i] == c) {
               System.err.printf("  %s", (char)i);
               break;
            }
         }
      }

      System.err.println();

      for(int row = 0; row <= this._rows; ++row) {
         System.err.printf("%3x:", row);

         for(int c = 0; c < 48; ++c) {
            char ch = this._table[row * 48 + c];
            if (ch == 0) {
               System.err.print("  .");
            } else {
               System.err.printf("%3x", Integer.valueOf(ch));
            }
         }

         Node<V> node = this._node[row];
         if (node == null) {
            System.err.println();
         } else {
            System.err.printf(" : %s%n", node);
            char[] bigRow = node._bigRow;
            if (bigRow != null) {
               System.err.print("   :");

               for(int c = 0; c < Math.min(this._bigRowSize, bigRow.length); ++c) {
                  char ch = bigRow[c];
                  if (ch == 0) {
                     System.err.print("  _");
                  } else {
                     System.err.printf("%3x", Integer.valueOf(ch));
                  }
               }

               for(int c = this._bigRowSize; c < bigRow.length; c += 2) {
                  System.err.printf(" %s>%x", bigRow[c], Integer.valueOf(bigRow[c + 1]));
               }

               System.err.println();
            }
         }
      }

      System.err.println();
   }

   private static class Node {
      String _key;
      Object _value;
      char[] _bigRow;

      public String toString() {
         String var10000 = this._key;
         return var10000 + "=" + String.valueOf(this._value);
      }
   }
}
