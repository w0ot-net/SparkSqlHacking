package javolution.xml.stream;

import java.util.Iterator;
import javax.realtime.MemoryArea;
import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.util.FastList;

final class NamespacesImpl implements Reusable, NamespaceContext {
   static final int NBR_PREDEFINED_NAMESPACES = 3;
   final CharArray _nullNsURI = new CharArray("");
   final CharArray _defaultNsPrefix = new CharArray("");
   final CharArray _xml = new CharArray("xml");
   final CharArray _xmlURI = new CharArray("http://www.w3.org/XML/1998/namespace");
   final CharArray _xmlns = new CharArray("xmlns");
   final CharArray _xmlnsURI = new CharArray("http://www.w3.org/2000/xmlns/");
   private int _nesting = 0;
   CharArray[] _prefixes = new CharArray[16];
   CharArray[] _namespaces;
   boolean[] _prefixesWritten;
   int[] _namespacesCount;
   CharArray _defaultNamespace;
   int _defaultNamespaceIndex;
   private CharArray[] _prefixesTmp;
   private CharArray[] _namespacesTmp;

   public NamespacesImpl() {
      this._namespaces = new CharArray[this._prefixes.length];
      this._prefixesWritten = new boolean[this._prefixes.length];
      this._namespacesCount = new int[16];
      this._defaultNamespace = this._nullNsURI;
      this._prefixesTmp = new CharArray[this._prefixes.length];
      this._namespacesTmp = new CharArray[this._prefixes.length];
      this._prefixes[0] = this._defaultNsPrefix;
      this._namespaces[0] = this._nullNsURI;
      this._prefixes[1] = this._xml;
      this._namespaces[1] = this._xmlURI;
      this._prefixes[2] = this._xmlns;
      this._namespaces[2] = this._xmlnsURI;
      this._namespacesCount[0] = 3;
   }

   public CharArray getNamespaceURI(CharSequence prefix) {
      if (prefix == null) {
         throw new IllegalArgumentException("null prefix not allowed");
      } else {
         return this.getNamespaceURINullAllowed(prefix);
      }
   }

   CharArray getNamespaceURINullAllowed(CharSequence prefix) {
      if (prefix != null && prefix.length() != 0) {
         int count = this._namespacesCount[this._nesting];
         int i = count;

         do {
            --i;
            if (i < 0) {
               return null;
            }
         } while(!this._prefixes[i].equals((Object)prefix));

         return this._namespaces[i];
      } else {
         return this._defaultNamespace;
      }
   }

   public CharArray getPrefix(CharSequence uri) {
      if (uri == null) {
         throw new IllegalArgumentException("null namespace URI not allowed");
      } else {
         return this._defaultNamespace.equals((Object)uri) ? this._defaultNsPrefix : this.getPrefix(uri, this._namespacesCount[this._nesting]);
      }
   }

   CharArray getPrefix(CharSequence uri, int count) {
      int i = count;

      while(true) {
         --i;
         if (i < 0) {
            return null;
         }

         CharArray prefix = this._prefixes[i];
         CharArray namespace = this._namespaces[i];
         if (namespace.equals((Object)uri)) {
            boolean isPrefixOverwritten = false;

            for(int j = i + 1; j < count; ++j) {
               if (prefix.equals(this._prefixes[j])) {
                  isPrefixOverwritten = true;
                  break;
               }
            }

            if (!isPrefixOverwritten) {
               return prefix;
            }
         }
      }
   }

   public Iterator getPrefixes(CharSequence namespaceURI) {
      FastList prefixes = new FastList();
      int i = this._namespacesCount[this._nesting];

      while(true) {
         --i;
         if (i < 0) {
            return prefixes.iterator();
         }

         if (this._namespaces[i].equals((Object)namespaceURI)) {
            prefixes.add(this._prefixes[i]);
         }
      }
   }

   void setPrefix(CharArray prefix, CharArray uri) {
      int index = this._namespacesCount[this._nesting];
      this._prefixes[index] = prefix;
      this._namespaces[index] = uri;
      if (prefix.length() == 0) {
         this._defaultNamespaceIndex = index;
         this._defaultNamespace = uri;
      }

      if (++this._namespacesCount[this._nesting] >= this._prefixes.length) {
         this.resizePrefixStack();
      }

   }

   void setPrefix(CharSequence prefix, CharSequence uri, boolean isWritten) {
      final int index = this._namespacesCount[this._nesting];
      this._prefixesWritten[index] = isWritten;
      final int prefixLength = prefix.length();
      CharArray prefixTmp = this._prefixesTmp[index];
      if (prefixTmp == null || prefixTmp.array().length < prefixLength) {
         MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
            public void run() {
               NamespacesImpl.this._prefixesTmp[index] = (new CharArray()).setArray(new char[prefixLength + 32], 0, 0);
            }
         });
         prefixTmp = this._prefixesTmp[index];
      }

      for(int i = 0; i < prefixLength; ++i) {
         prefixTmp.array()[i] = prefix.charAt(i);
      }

      prefixTmp.setArray(prefixTmp.array(), 0, prefixLength);
      final int uriLength = uri.length();
      CharArray namespaceTmp = this._namespacesTmp[index];
      if (namespaceTmp == null || namespaceTmp.array().length < uriLength) {
         MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
            public void run() {
               NamespacesImpl.this._namespacesTmp[index] = (new CharArray()).setArray(new char[uriLength + 32], 0, 0);
            }
         });
         namespaceTmp = this._namespacesTmp[index];
      }

      for(int i = 0; i < uriLength; ++i) {
         namespaceTmp.array()[i] = uri.charAt(i);
      }

      namespaceTmp.setArray(namespaceTmp.array(), 0, uriLength);
      this.setPrefix(prefixTmp, namespaceTmp);
   }

   void pop() {
      if (this._namespacesCount[--this._nesting] <= this._defaultNamespaceIndex) {
         this.searchDefaultNamespace();
      }

   }

   private void searchDefaultNamespace() {
      int count = this._namespacesCount[this._nesting];
      int i = count;

      do {
         --i;
         if (i < 0) {
            throw new Error("Cannot find default namespace");
         }
      } while(this._prefixes[i].length() != 0);

      this._defaultNamespaceIndex = i;
   }

   void push() {
      ++this._nesting;
      if (this._nesting >= this._namespacesCount.length) {
         this.resizeNamespacesCount();
      }

      this._namespacesCount[this._nesting] = this._namespacesCount[this._nesting - 1];
   }

   public void reset() {
      this._defaultNamespace = this._nullNsURI;
      this._defaultNamespaceIndex = 0;
      this._namespacesCount[0] = 3;
      this._nesting = 0;
   }

   private void resizeNamespacesCount() {
      MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
         public void run() {
            int oldLength = NamespacesImpl.this._namespacesCount.length;
            int newLength = oldLength * 2;
            int[] tmp = new int[newLength];
            System.arraycopy(NamespacesImpl.this._namespacesCount, 0, tmp, 0, oldLength);
            NamespacesImpl.this._namespacesCount = tmp;
         }
      });
   }

   private void resizePrefixStack() {
      MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
         public void run() {
            int oldLength = NamespacesImpl.this._prefixes.length;
            int newLength = oldLength * 2;
            CharArray[] tmp0 = new CharArray[newLength];
            System.arraycopy(NamespacesImpl.this._prefixes, 0, tmp0, 0, oldLength);
            NamespacesImpl.this._prefixes = tmp0;
            CharArray[] tmp1 = new CharArray[newLength];
            System.arraycopy(NamespacesImpl.this._namespaces, 0, tmp1, 0, oldLength);
            NamespacesImpl.this._namespaces = tmp1;
            boolean[] tmp2 = new boolean[newLength];
            System.arraycopy(NamespacesImpl.this._prefixesWritten, 0, tmp2, 0, oldLength);
            NamespacesImpl.this._prefixesWritten = tmp2;
            CharArray[] tmp3 = new CharArray[newLength];
            System.arraycopy(NamespacesImpl.this._prefixesTmp, 0, tmp3, 0, oldLength);
            NamespacesImpl.this._prefixesTmp = tmp3;
            CharArray[] tmp4 = new CharArray[newLength];
            System.arraycopy(NamespacesImpl.this._namespacesTmp, 0, tmp4, 0, oldLength);
            NamespacesImpl.this._namespacesTmp = tmp4;
         }
      });
   }
}
