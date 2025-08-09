package javolution.text;

import java.text.ParsePosition;
import javolution.context.ObjectFactory;
import javolution.lang.Reusable;

public class Cursor extends ParsePosition implements Reusable {
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      protected Object create() {
         return new Cursor();
      }
   };

   public Cursor() {
      super(0);
   }

   public static Cursor newInstance() {
      return (Cursor)FACTORY.object();
   }

   public static void recycle(Cursor cursor) {
      FACTORY.recycle(cursor);
   }

   public final int getIndex() {
      return super.getIndex();
   }

   public void setIndex(int i) {
      super.setIndex(i);
   }

   public final boolean atEnd(CharSequence csq) {
      return this.getIndex() >= csq.length();
   }

   public final boolean at(char c, CharSequence csq) {
      int i = this.getIndex();
      return i < csq.length() ? csq.charAt(i) == c : false;
   }

   public final boolean at(CharSet charSet, CharSequence csq) {
      int i = this.getIndex();
      return i < csq.length() ? charSet.contains(csq.charAt(i)) : false;
   }

   public final boolean at(String str, CharSequence csq) {
      int i = this.getIndex();
      int length = csq.length();
      int j = 0;

      while(j < str.length()) {
         if (i >= length || str.charAt(j++) != csq.charAt(i++)) {
            return false;
         }
      }

      return true;
   }

   public final char nextChar(CharSequence csq) {
      int i = this.getIndex();
      this.setIndex(i + 1);
      return csq.charAt(i);
   }

   public final boolean skipAny(char c, CharSequence csq) {
      int i = this.getIndex();

      for(int n = csq.length(); i < n && csq.charAt(i) == c; ++i) {
      }

      if (i == this.getIndex()) {
         return false;
      } else {
         this.setIndex(i);
         return true;
      }
   }

   public final boolean skipAny(CharSet charSet, CharSequence csq) {
      int i = this.getIndex();

      for(int n = csq.length(); i < n && charSet.contains(csq.charAt(i)); ++i) {
      }

      if (i == this.getIndex()) {
         return false;
      } else {
         this.setIndex(i);
         return true;
      }
   }

   public final boolean skip(char c, CharSequence csq) {
      if (this.at(c, csq)) {
         this.increment();
         return true;
      } else {
         return false;
      }
   }

   public final boolean skip(CharSet charSet, CharSequence csq) {
      if (this.at(charSet, csq)) {
         this.increment();
         return true;
      } else {
         return false;
      }
   }

   public final boolean skip(String str, CharSequence csq) {
      if (this.at(str, csq)) {
         this.increment(str.length());
         return true;
      } else {
         return false;
      }
   }

   public final CharSequence nextToken(CharSequence csq, char c) {
      int n = csq.length();

      for(int i = this.getIndex(); i < n; ++i) {
         if (csq.charAt(i) != c) {
            int j = i;

            do {
               ++j;
            } while(j < n && csq.charAt(j) != c);

            this.setIndex(j);
            return csq.subSequence(i, j);
         }
      }

      this.setIndex(n);
      return null;
   }

   public final CharSequence nextToken(CharSequence csq, CharSet charSet) {
      int n = csq.length();

      for(int i = this.getIndex(); i < n; ++i) {
         if (!charSet.contains(csq.charAt(i))) {
            int j = i;

            do {
               ++j;
            } while(j < n && !charSet.contains(csq.charAt(j)));

            this.setIndex(j);
            return csq.subSequence(i, j);
         }
      }

      this.setIndex(n);
      return null;
   }

   public final Cursor increment() {
      return this.increment(1);
   }

   public final Cursor increment(int i) {
      this.setIndex(this.getIndex() + i);
      return this;
   }

   public String toString() {
      return "Index: " + this.getIndex();
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!(obj instanceof Cursor)) {
         return false;
      } else {
         return this.getIndex() == ((Cursor)obj).getIndex();
      }
   }

   public int hashCode() {
      return this.getIndex();
   }

   public void reset() {
      super.setIndex(0);
      super.setErrorIndex(-1);
   }
}
