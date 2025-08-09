package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import org.apache.logging.log4j.util.StringBuilderFormattable;

public class SimpleMessage implements Message, StringBuilderFormattable, CharSequence {
   private static final long serialVersionUID = -8398002534962715992L;
   private String message;
   private transient CharSequence charSequence;

   public SimpleMessage() {
      this((String)null);
   }

   public SimpleMessage(final String message) {
      this.message = message;
      this.charSequence = message;
   }

   public SimpleMessage(final CharSequence charSequence) {
      this.charSequence = charSequence;
   }

   public String getFormattedMessage() {
      return this.message = this.message == null ? String.valueOf(this.charSequence) : this.message;
   }

   public void formatTo(final StringBuilder buffer) {
      buffer.append((CharSequence)(this.message != null ? this.message : this.charSequence));
   }

   public String getFormat() {
      return this.message;
   }

   public Object[] getParameters() {
      return null;
   }

   public boolean equals(final Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof SimpleMessage)) {
         return false;
      } else {
         SimpleMessage that = (SimpleMessage)o;
         return Objects.equals(this.charSequence, that.charSequence) || Objects.equals(this.getFormattedMessage(), that.getFormattedMessage());
      }
   }

   public int hashCode() {
      return this.charSequence != null ? this.charSequence.hashCode() : 0;
   }

   public String toString() {
      return this.getFormattedMessage();
   }

   public Throwable getThrowable() {
      return null;
   }

   public int length() {
      return this.charSequence == null ? 0 : this.charSequence.length();
   }

   public char charAt(final int index) {
      return this.charSequence.charAt(index);
   }

   public CharSequence subSequence(final int start, final int end) {
      return this.charSequence.subSequence(start, end);
   }

   private void writeObject(final ObjectOutputStream out) throws IOException {
      this.getFormattedMessage();
      out.defaultWriteObject();
   }

   private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.charSequence = this.message;
   }
}
