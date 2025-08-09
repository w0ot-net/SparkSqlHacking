package javolution.xml.stream;

import java.util.Collection;
import java.util.Map;
import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.util.FastCollection;

final class EntitiesImpl implements Reusable {
   private int _maxLength = 1;
   private Map _entitiesMapping;
   private CharArray _tmp = new CharArray();

   public int getMaxLength() {
      return this._maxLength;
   }

   public int replaceEntity(char[] buffer, int start, int length) throws XMLStreamException {
      if (buffer[start + 1] == '#') {
         char c = buffer[start + 2];
         int base = c == 'x' ? 16 : 10;
         int i = c == 'x' ? 3 : 2;

         int charValue;
         for(charValue = 0; i < length - 1; ++i) {
            c = buffer[start + i];
            charValue *= base;
            charValue += c <= '9' ? c - 48 : (c <= 'Z' ? c - 65 : c - 97);
         }

         buffer[start] = (char)charValue;
         return 1;
      } else if (buffer[start + 1] == 'l' && buffer[start + 2] == 't' && buffer[start + 3] == ';') {
         buffer[start] = '<';
         return 1;
      } else if (buffer[start + 1] == 'g' && buffer[start + 2] == 't' && buffer[start + 3] == ';') {
         buffer[start] = '>';
         return 1;
      } else if (buffer[start + 1] == 'a' && buffer[start + 2] == 'p' && buffer[start + 3] == 'o' && buffer[start + 4] == 's' && buffer[start + 5] == ';') {
         buffer[start] = '\'';
         return 1;
      } else if (buffer[start + 1] == 'q' && buffer[start + 2] == 'u' && buffer[start + 3] == 'o' && buffer[start + 4] == 't' && buffer[start + 5] == ';') {
         buffer[start] = '"';
         return 1;
      } else if (buffer[start + 1] == 'a' && buffer[start + 2] == 'm' && buffer[start + 3] == 'p' && buffer[start + 4] == ';') {
         buffer[start] = '&';
         return 1;
      } else {
         this._tmp.setArray(buffer, start + 1, length - 2);
         CharSequence replacementText = this._entitiesMapping != null ? (CharSequence)this._entitiesMapping.get(this._tmp) : null;
         if (replacementText == null) {
            throw new XMLStreamException("Entity " + this._tmp + " not recognized");
         } else {
            int replacementTextLength = replacementText.length();

            for(int i = 0; i < replacementTextLength; ++i) {
               buffer[start + i] = replacementText.charAt(i);
            }

            return replacementTextLength;
         }
      }
   }

   public void setEntitiesMapping(Map entityToReplacementText) {
      Collection values = entityToReplacementText.values();
      if (values instanceof FastCollection) {
         FastCollection fc = (FastCollection)values;
         FastCollection.Record r = fc.head();
         FastCollection.Record t = fc.tail();

         while((r = r.getNext()) != t) {
            CharSequence value = (CharSequence)fc.valueOf(r);
            if (this._maxLength < value.length()) {
               this._maxLength = value.length();
            }
         }
      } else {
         for(CharSequence value : values) {
            if (this._maxLength < value.length()) {
               this._maxLength = value.length();
            }
         }
      }

      this._entitiesMapping = entityToReplacementText;
   }

   public Map getEntitiesMapping() {
      return this._entitiesMapping;
   }

   public void reset() {
      this._maxLength = 1;
      this._entitiesMapping = null;
   }
}
