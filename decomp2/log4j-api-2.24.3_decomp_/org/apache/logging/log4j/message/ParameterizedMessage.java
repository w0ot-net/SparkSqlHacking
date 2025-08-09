package org.apache.logging.log4j.message;

import com.google.errorprone.annotations.InlineMe;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringBuilders;
import org.apache.logging.log4j.util.internal.SerializationUtil;

public class ParameterizedMessage implements Message, StringBuilderFormattable {
   public static final String RECURSION_PREFIX = "[...";
   public static final String RECURSION_SUFFIX = "...]";
   public static final String ERROR_PREFIX = "[!!!";
   public static final String ERROR_SEPARATOR = "=>";
   public static final String ERROR_MSG_SEPARATOR = ":";
   public static final String ERROR_SUFFIX = "!!!]";
   private static final long serialVersionUID = -665975803997290697L;
   private static final ThreadLocal FORMAT_BUFFER_HOLDER_REF;
   private final String pattern;
   private transient Object[] args;
   private final transient Throwable throwable;
   private final ParameterFormatter.MessagePatternAnalysis patternAnalysis;
   private String formattedMessage;

   /** @deprecated */
   @Deprecated
   @InlineMe(
      replacement = "this(pattern, Arrays.stream(args).toArray(Object[]::new), throwable)",
      imports = {"java.util.Arrays"}
   )
   public ParameterizedMessage(final String pattern, final String[] args, final Throwable throwable) {
      this(pattern, Arrays.stream(args).toArray((x$0) -> new Object[x$0]), throwable);
   }

   public ParameterizedMessage(final String pattern, final Object[] args, final Throwable throwable) {
      this.args = args;
      this.pattern = pattern;
      this.patternAnalysis = ParameterFormatter.analyzePattern(pattern, args != null ? args.length : 0);
      this.throwable = determineThrowable(throwable, this.args, this.patternAnalysis);
   }

   private static Throwable determineThrowable(final Throwable throwable, final Object[] args, final ParameterFormatter.MessagePatternAnalysis analysis) {
      if (throwable != null) {
         return throwable;
      } else {
         if (args != null && args.length > analysis.placeholderCount) {
            Object lastArg = args[args.length - 1];
            if (lastArg instanceof Throwable) {
               return (Throwable)lastArg;
            }
         }

         return null;
      }
   }

   public ParameterizedMessage(final String pattern, final Object... args) {
      this(pattern, (Object[])args, (Throwable)null);
   }

   public ParameterizedMessage(final String pattern, final Object arg) {
      this(pattern, arg);
   }

   public ParameterizedMessage(final String pattern, final Object arg0, final Object arg1) {
      this(pattern, arg0, arg1);
   }

   public String getFormat() {
      return this.pattern;
   }

   public Object[] getParameters() {
      return this.args;
   }

   public Throwable getThrowable() {
      return this.throwable;
   }

   public String getFormattedMessage() {
      if (this.formattedMessage == null) {
         FormatBufferHolder bufferHolder;
         if (FORMAT_BUFFER_HOLDER_REF != null && !(bufferHolder = (FormatBufferHolder)FORMAT_BUFFER_HOLDER_REF.get()).used) {
            bufferHolder.used = true;
            StringBuilder buffer = bufferHolder.buffer;

            try {
               this.formatTo(buffer);
               this.formattedMessage = buffer.toString();
            } finally {
               StringBuilders.trimToMaxSize(buffer, Constants.MAX_REUSABLE_MESSAGE_SIZE);
               buffer.setLength(0);
               bufferHolder.used = false;
            }
         } else {
            StringBuilder buffer = new StringBuilder(Constants.MAX_REUSABLE_MESSAGE_SIZE);
            this.formatTo(buffer);
            this.formattedMessage = buffer.toString();
         }
      }

      return this.formattedMessage;
   }

   public void formatTo(final StringBuilder buffer) {
      if (this.formattedMessage != null) {
         buffer.append(this.formattedMessage);
      } else {
         int argCount = this.args != null ? this.args.length : 0;
         ParameterFormatter.formatMessage(buffer, this.pattern, this.args, argCount, this.patternAnalysis);
      }

   }

   public static String format(final String pattern, final Object[] args) {
      int argCount = args != null ? args.length : 0;
      return ParameterFormatter.format(pattern, args, argCount);
   }

   public boolean equals(final Object object) {
      if (this == object) {
         return true;
      } else if (!(object instanceof ParameterizedMessage)) {
         return false;
      } else {
         ParameterizedMessage that = (ParameterizedMessage)object;
         return Objects.equals(this.pattern, that.pattern) && Arrays.equals(this.args, that.args);
      }
   }

   public int hashCode() {
      int result = this.pattern != null ? this.pattern.hashCode() : 0;
      result = 31 * result + (this.args != null ? Arrays.hashCode(this.args) : 0);
      return result;
   }

   public static int countArgumentPlaceholders(final String pattern) {
      return pattern == null ? 0 : ParameterFormatter.analyzePattern(pattern, -1).placeholderCount;
   }

   public static String deepToString(final Object o) {
      return ParameterFormatter.deepToString(o);
   }

   public static String identityToString(final Object obj) {
      return ParameterFormatter.identityToString(obj);
   }

   public String toString() {
      return "ParameterizedMessage[messagePattern=" + this.pattern + ", argCount=" + this.args.length + ", throwableProvided=" + (this.throwable != null) + ']';
   }

   private void writeObject(final ObjectOutputStream out) throws IOException {
      out.defaultWriteObject();
      out.writeInt(this.args.length);

      for(Object arg : this.args) {
         Serializable serializableArg = (Serializable)(arg instanceof Serializable ? (Serializable)arg : String.valueOf(arg));
         SerializationUtil.writeWrappedObject(serializableArg, out);
      }

   }

   private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
      SerializationUtil.assertFiltered(in);
      in.defaultReadObject();
      int argCount = in.readInt();
      this.args = new Object[argCount];

      for(int argIndex = 0; argIndex < this.args.length; ++argIndex) {
         this.args[argIndex] = SerializationUtil.readWrappedObject(in);
      }

   }

   static {
      FORMAT_BUFFER_HOLDER_REF = Constants.ENABLE_THREADLOCALS ? ThreadLocal.withInitial(() -> new FormatBufferHolder()) : null;
   }

   private static final class FormatBufferHolder {
      private final StringBuilder buffer;
      private boolean used;

      private FormatBufferHolder() {
         this.buffer = new StringBuilder(Constants.MAX_REUSABLE_MESSAGE_SIZE);
         this.used = false;
      }
   }
}
