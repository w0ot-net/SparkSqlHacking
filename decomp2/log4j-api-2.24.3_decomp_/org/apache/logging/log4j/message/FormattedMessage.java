package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Locale.Category;

public class FormattedMessage implements Message {
   private static final long serialVersionUID = -665975803997290697L;
   private static final int HASHVAL = 31;
   private String messagePattern;
   private transient Object[] argArray;
   private String[] stringArgs;
   private transient String formattedMessage;
   private final Throwable throwable;
   private Message message;
   private final Locale locale;

   public FormattedMessage(final Locale locale, final String messagePattern, final Object arg) {
      this(locale, messagePattern, (Object[])(new Object[]{arg}), (Throwable)null);
   }

   public FormattedMessage(final Locale locale, final String messagePattern, final Object arg1, final Object arg2) {
      this(locale, messagePattern, arg1, arg2);
   }

   public FormattedMessage(final Locale locale, final String messagePattern, final Object... arguments) {
      this(locale, messagePattern, (Object[])arguments, (Throwable)null);
   }

   public FormattedMessage(final Locale locale, final String messagePattern, final Object[] arguments, final Throwable throwable) {
      this.locale = locale;
      this.messagePattern = messagePattern;
      this.argArray = arguments;
      this.throwable = throwable;
   }

   public FormattedMessage(final String messagePattern, final Object arg) {
      this((String)messagePattern, (Object[])(new Object[]{arg}), (Throwable)null);
   }

   public FormattedMessage(final String messagePattern, final Object arg1, final Object arg2) {
      this(messagePattern, arg1, arg2);
   }

   public FormattedMessage(final String messagePattern, final Object... arguments) {
      this((String)messagePattern, (Object[])arguments, (Throwable)null);
   }

   public FormattedMessage(final String messagePattern, final Object[] arguments, final Throwable throwable) {
      this.locale = Locale.getDefault(Category.FORMAT);
      this.messagePattern = messagePattern;
      this.argArray = arguments;
      this.throwable = throwable;
   }

   public boolean equals(final Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof FormattedMessage)) {
         return false;
      } else {
         FormattedMessage that = (FormattedMessage)o;
         if (this.messagePattern != null) {
            if (!this.messagePattern.equals(that.messagePattern)) {
               return false;
            }
         } else if (that.messagePattern != null) {
            return false;
         }

         return Arrays.equals(this.stringArgs, that.stringArgs);
      }
   }

   public String getFormat() {
      return this.messagePattern;
   }

   public String getFormattedMessage() {
      if (this.formattedMessage == null) {
         if (this.message == null) {
            this.message = this.getMessage(this.messagePattern, this.argArray, this.throwable);
         }

         this.formattedMessage = this.message.getFormattedMessage();
      }

      return this.formattedMessage;
   }

   protected Message getMessage(final String msgPattern, final Object[] args, final Throwable aThrowable) {
      try {
         MessageFormat format = new MessageFormat(msgPattern);
         Format[] formats = format.getFormats();
         if (formats.length > 0) {
            return new MessageFormatMessage(this.locale, msgPattern, args);
         }
      } catch (Exception var6) {
      }

      return (Message)(ParameterFormatter.analyzePattern(msgPattern, 1).placeholderCount <= 0 && msgPattern.indexOf(37) != -1 ? new StringFormattedMessage(this.locale, msgPattern, args) : new ParameterizedMessage(msgPattern, args, aThrowable));
   }

   public Object[] getParameters() {
      return (Object[])(this.argArray != null ? this.argArray : this.stringArgs);
   }

   public Throwable getThrowable() {
      if (this.throwable != null) {
         return this.throwable;
      } else {
         if (this.message == null) {
            this.message = this.getMessage(this.messagePattern, this.argArray, (Throwable)null);
         }

         return this.message.getThrowable();
      }
   }

   public int hashCode() {
      int result = this.messagePattern != null ? this.messagePattern.hashCode() : 0;
      result = 31 * result + (this.stringArgs != null ? Arrays.hashCode(this.stringArgs) : 0);
      return result;
   }

   private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.formattedMessage = in.readUTF();
      this.messagePattern = in.readUTF();
      int length = in.readInt();
      this.stringArgs = new String[length];

      for(int i = 0; i < length; ++i) {
         this.stringArgs[i] = in.readUTF();
      }

   }

   public String toString() {
      return this.getFormattedMessage();
   }

   private void writeObject(final ObjectOutputStream out) throws IOException {
      out.defaultWriteObject();
      this.getFormattedMessage();
      out.writeUTF(this.formattedMessage);
      out.writeUTF(this.messagePattern);
      out.writeInt(this.argArray.length);
      this.stringArgs = new String[this.argArray.length];
      int i = 0;

      for(Object obj : this.argArray) {
         String string = String.valueOf(obj);
         this.stringArgs[i] = string;
         out.writeUTF(string);
         ++i;
      }

   }
}
