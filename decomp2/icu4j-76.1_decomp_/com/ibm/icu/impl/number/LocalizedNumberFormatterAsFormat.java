package com.ibm.icu.impl.number;

import com.ibm.icu.impl.FormattedStringBuilder;
import com.ibm.icu.impl.FormattedValueStringBuilderImpl;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.number.LocalizedNumberFormatter;
import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.util.ULocale;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class LocalizedNumberFormatterAsFormat extends Format {
   private static final long serialVersionUID = 1L;
   private final transient LocalizedNumberFormatter formatter;
   private final transient ULocale locale;

   public LocalizedNumberFormatterAsFormat(LocalizedNumberFormatter formatter, ULocale locale) {
      this.formatter = formatter;
      this.locale = locale;
   }

   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
      if (!(obj instanceof Number)) {
         throw new IllegalArgumentException();
      } else {
         DecimalQuantity dq = new DecimalQuantity_DualStorageBCD((Number)obj);
         FormattedStringBuilder string = new FormattedStringBuilder();
         this.formatter.formatImpl(dq, string);
         pos.setBeginIndex(0);
         pos.setEndIndex(0);
         boolean found = FormattedValueStringBuilderImpl.nextFieldPosition(string, pos);
         if (found && toAppendTo.length() != 0) {
            pos.setBeginIndex(pos.getBeginIndex() + toAppendTo.length());
            pos.setEndIndex(pos.getEndIndex() + toAppendTo.length());
         }

         Utility.appendTo(string, toAppendTo);
         return toAppendTo;
      }
   }

   public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
      if (!(obj instanceof Number)) {
         throw new IllegalArgumentException();
      } else {
         return this.formatter.format((Number)obj).toCharacterIterator();
      }
   }

   public Object parseObject(String source, ParsePosition pos) {
      throw new UnsupportedOperationException();
   }

   public LocalizedNumberFormatter getNumberFormatter() {
      return this.formatter;
   }

   public int hashCode() {
      return this.formatter.hashCode();
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null) {
         return false;
      } else {
         return !(other instanceof LocalizedNumberFormatterAsFormat) ? false : this.formatter.equals(((LocalizedNumberFormatterAsFormat)other).getNumberFormatter());
      }
   }

   private Object writeReplace() throws ObjectStreamException {
      Proxy proxy = new Proxy();
      proxy.languageTag = this.locale.toLanguageTag();
      proxy.skeleton = this.formatter.toSkeleton();
      return proxy;
   }

   static class Proxy implements Externalizable {
      private static final long serialVersionUID = 1L;
      String languageTag;
      String skeleton;

      public Proxy() {
      }

      public void writeExternal(ObjectOutput out) throws IOException {
         out.writeByte(0);
         out.writeUTF(this.languageTag);
         out.writeUTF(this.skeleton);
      }

      public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
         in.readByte();
         this.languageTag = in.readUTF();
         this.skeleton = in.readUTF();
      }

      private Object readResolve() throws ObjectStreamException {
         return NumberFormatter.forSkeleton(this.skeleton).locale(ULocale.forLanguageTag(this.languageTag)).toFormat();
      }
   }
}
