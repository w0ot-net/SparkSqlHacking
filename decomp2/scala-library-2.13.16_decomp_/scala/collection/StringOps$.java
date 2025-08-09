package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.regex.PatternSyntaxException;
import scala.$less$colon$less$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.convert.impl.CharStringStepper;
import scala.collection.convert.impl.CodePointStringStepper;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.HashMap$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.immutable.WrappedString;
import scala.collection.immutable.WrappedString$;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.math.ScalaNumber;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ManifestFactory;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar$;
import scala.runtime.RichInt$;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;
import scala.util.matching.Regex;

public final class StringOps$ {
   public static final StringOps$ MODULE$ = new StringOps$();
   private static final Function1 fallback = (x$1) -> MODULE$.fallback();

   private final int LF() {
      return 10;
   }

   private final int FF() {
      return 12;
   }

   private final int CR() {
      return 13;
   }

   private final int SU() {
      return 26;
   }

   private Function1 fallback() {
      return fallback;
   }

   public final StringView view$extension(final String $this) {
      return new StringView($this);
   }

   public final int size$extension(final String $this) {
      return $this.length();
   }

   public final int knownSize$extension(final String $this) {
      return $this.length();
   }

   public final char apply$extension(final String $this, final int i) {
      return $this.charAt(i);
   }

   public final int sizeCompare$extension(final String $this, final int otherSize) {
      return Integer.compare($this.length(), otherSize);
   }

   public final int lengthCompare$extension(final String $this, final int len) {
      return Integer.compare($this.length(), len);
   }

   public final int sizeIs$extension(final String $this) {
      return $this.length();
   }

   public final int lengthIs$extension(final String $this) {
      return $this.length();
   }

   public final scala.collection.immutable.IndexedSeq map$extension(final String $this, final Function1 f) {
      int len = $this.length();
      Object[] dst = new Object[len];

      for(int i = 0; i < len; ++i) {
         dst[i] = f.apply($this.charAt(i));
      }

      return new ArraySeq.ofRef(dst);
   }

   public final String map$extension(final String $this, final Function1 f) {
      int len = $this.length();
      char[] dst = new char[len];

      for(int i = 0; i < len; ++i) {
         dst[i] = BoxesRunTime.unboxToChar(f.apply($this.charAt(i)));
      }

      return new String(dst);
   }

   public final scala.collection.immutable.IndexedSeq flatMap$extension(final String $this, final Function1 f) {
      int len = $this.length();
      Builder b = scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();

      for(int i = 0; i < len; ++i) {
         b.addAll((IterableOnce)f.apply($this.charAt(i)));
      }

      return (scala.collection.immutable.IndexedSeq)b.result();
   }

   public final String flatMap$extension(final String $this, final Function1 f) {
      int len = $this.length();
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < len; ++i) {
         sb.append((String)f.apply($this.charAt(i)));
      }

      return sb.toString();
   }

   public final String collect$extension(final String $this, final PartialFunction pf) {
      Function1 fallback = this.fallback();
      int i = 0;

      scala.collection.mutable.StringBuilder b;
      for(b = new scala.collection.mutable.StringBuilder(); i < $this.length(); ++i) {
         Object v = pf.applyOrElse($this.charAt(i), fallback);
         if (v != fallback) {
            b.addOne(BoxesRunTime.unboxToChar(v));
         }
      }

      return b.result();
   }

   public final scala.collection.immutable.IndexedSeq collect$extension(final String $this, final PartialFunction pf) {
      Function1 fallback = this.fallback();
      int i = 0;

      Builder b;
      for(b = scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder(); i < $this.length(); ++i) {
         Object v = pf.applyOrElse($this.charAt(i), fallback);
         if (v != fallback) {
            b.addOne(v);
         }
      }

      return (scala.collection.immutable.IndexedSeq)b.result();
   }

   public final scala.collection.immutable.IndexedSeq concat$extension(final String $this, final IterableOnce suffix) {
      Builder b = scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
      int k = suffix.knownSize();
      b.sizeHint($this.length() + (k >= 0 ? k : 16));
      b.addAll(new WrappedString($this));
      b.addAll(suffix);
      return (scala.collection.immutable.IndexedSeq)b.result();
   }

   public final String concat$extension(final String $this, final IterableOnce suffix) {
      int k = suffix.knownSize();
      StringBuilder sb = new StringBuilder($this.length() + (k >= 0 ? k : 16));
      sb.append($this);
      suffix.iterator().foreach((ch) -> $anonfun$concat$1(sb, BoxesRunTime.unboxToChar(ch)));
      return sb.toString();
   }

   public final String concat$extension(final String $this, final String suffix) {
      return (new StringBuilder(0)).append($this).append(suffix).toString();
   }

   public final scala.collection.immutable.IndexedSeq $plus$plus$extension(final String $this, final Iterable suffix) {
      return this.concat$extension($this, (IterableOnce)suffix);
   }

   public final String $plus$plus$extension(final String $this, final IterableOnce suffix) {
      return this.concat$extension($this, suffix);
   }

   public final String $plus$plus$extension(final String $this, final String xs) {
      return (new StringBuilder(0)).append($this).append(xs).toString();
   }

   public final scala.collection.immutable.IndexedSeq padTo$extension(final String $this, final int len, final Object elem) {
      int sLen = $this.length();
      if (sLen >= len) {
         return new WrappedString($this);
      } else {
         Builder b = scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
         b.sizeHint(len);
         b.addAll(new WrappedString($this));

         for(int i = sLen; i < len; ++i) {
            b.addOne(elem);
         }

         return (scala.collection.immutable.IndexedSeq)b.result();
      }
   }

   public final String padTo$extension(final String $this, final int len, final char elem) {
      int sLen = $this.length();
      if (sLen >= len) {
         return $this;
      } else {
         StringBuilder sb = new StringBuilder(len);
         sb.append($this);

         for(int i = sLen; i < len; ++i) {
            sb.append(elem);
         }

         return sb.toString();
      }
   }

   public final scala.collection.immutable.IndexedSeq prepended$extension(final String $this, final Object elem) {
      Builder b = scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
      b.sizeHint($this.length() + 1);
      b.addOne(elem);
      b.addAll(new WrappedString($this));
      return (scala.collection.immutable.IndexedSeq)b.result();
   }

   public final scala.collection.immutable.IndexedSeq $plus$colon$extension(final String $this, final Object elem) {
      return this.prepended$extension($this, elem);
   }

   public final String prepended$extension(final String $this, final char c) {
      return (new StringBuilder($this.length() + 1)).append(c).append($this).toString();
   }

   public final String $plus$colon$extension(final String $this, final char c) {
      return this.prepended$extension($this, c);
   }

   public final scala.collection.immutable.IndexedSeq prependedAll$extension(final String $this, final IterableOnce prefix) {
      Builder b = scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
      int k = prefix.knownSize();
      b.sizeHint($this.length() + (k >= 0 ? k : 16));
      b.addAll(prefix);
      b.addAll(new WrappedString($this));
      return (scala.collection.immutable.IndexedSeq)b.result();
   }

   public final scala.collection.immutable.IndexedSeq $plus$plus$colon$extension(final String $this, final IterableOnce prefix) {
      return this.prependedAll$extension($this, prefix);
   }

   public final String prependedAll$extension(final String $this, final String prefix) {
      return (new StringBuilder(0)).append(prefix).append($this).toString();
   }

   public final String $plus$plus$colon$extension(final String $this, final String prefix) {
      return this.prependedAll$extension($this, prefix);
   }

   public final scala.collection.immutable.IndexedSeq appended$extension(final String $this, final Object elem) {
      Builder b = scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
      b.sizeHint($this.length() + 1);
      b.addAll(new WrappedString($this));
      b.addOne(elem);
      return (scala.collection.immutable.IndexedSeq)b.result();
   }

   public final scala.collection.immutable.IndexedSeq $colon$plus$extension(final String $this, final Object elem) {
      return this.appended$extension($this, elem);
   }

   public final String appended$extension(final String $this, final char c) {
      return (new StringBuilder($this.length() + 1)).append($this).append(c).toString();
   }

   public final String $colon$plus$extension(final String $this, final char c) {
      return this.appended$extension($this, c);
   }

   public final scala.collection.immutable.IndexedSeq appendedAll$extension(final String $this, final IterableOnce suffix) {
      return this.concat$extension($this, suffix);
   }

   public final scala.collection.immutable.IndexedSeq $colon$plus$plus$extension(final String $this, final IterableOnce suffix) {
      return this.concat$extension($this, suffix);
   }

   public final String appendedAll$extension(final String $this, final String suffix) {
      return (new StringBuilder(0)).append($this).append(suffix).toString();
   }

   public final String $colon$plus$plus$extension(final String $this, final String suffix) {
      return (new StringBuilder(0)).append($this).append(suffix).toString();
   }

   public final scala.collection.immutable.IndexedSeq patch$extension(final String $this, final int from, final IterableOnce other, final int replaced) {
      int len = $this.length();
      Builder b = scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
      int k = other.knownSize();
      if (k >= 0) {
         b.sizeHint(len + k - replaced);
      }

      int var12;
      if (from > 0) {
         scala.math.package$ var10000 = scala.math.package$.MODULE$;
         var12 = Math.min(from, len);
      } else {
         var12 = 0;
      }

      int chunk1 = var12;
      if (chunk1 > 0) {
         int slc$1_off = 0;
         b.addAll(new WrappedString($this.substring(slc$1_off, slc$1_off + chunk1)));
      }

      if (b == null) {
         throw null;
      } else {
         b.addAll(other);
         int remaining = len - chunk1 - replaced;
         if (remaining > 0) {
            int slc$1_off = len - remaining;
            b.addAll(new WrappedString($this.substring(slc$1_off, slc$1_off + remaining)));
         }

         return (scala.collection.immutable.IndexedSeq)b.result();
      }
   }

   public final String patch$extension(final String $this, final int from, final IterableOnce other, final int replaced) {
      Iterator var10003 = other.iterator();
      if (var10003 == null) {
         throw null;
      } else {
         IterableOnceOps mkString_this = var10003;
         String mkString_mkString_sep = "";
         String var9 = mkString_this.mkString("", mkString_mkString_sep, "");
         Object var8 = null;
         mkString_this = null;
         return this.patch$extension($this, from, var9, replaced);
      }
   }

   public final String patch$extension(final String $this, final int from, final String other, final int replaced) {
      int len = $this.length();
      StringBuilder sb = new StringBuilder(len + other.length() - replaced);
      int var9;
      if (from > 0) {
         scala.math.package$ var10000 = scala.math.package$.MODULE$;
         var9 = Math.min(from, len);
      } else {
         var9 = 0;
      }

      int chunk1 = var9;
      if (chunk1 > 0) {
         sb.append($this, 0, chunk1);
      }

      sb.append(other);
      int remaining = len - chunk1 - replaced;
      if (remaining > 0) {
         sb.append($this, len - remaining, len);
      }

      return sb.toString();
   }

   public final String updated$extension(final String $this, final int index, final char elem) {
      StringBuilder sb = (new StringBuilder($this.length())).append($this);
      sb.setCharAt(index, elem);
      return sb.toString();
   }

   public final boolean contains$extension(final String $this, final char elem) {
      return $this.indexOf(elem) >= 0;
   }

   public final String mkString$extension(final String $this, final String start, final String sep, final String end) {
      scala.collection.mutable.StringBuilder var10000 = this.addString$extension($this, new scala.collection.mutable.StringBuilder(), start, sep, end);
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.result();
      }
   }

   public final String mkString$extension(final String $this, final String sep) {
      return !sep.isEmpty() && $this.length() >= 2 ? this.mkString$extension($this, "", sep, "") : $this;
   }

   public final String mkString$extension(final String $this) {
      return $this;
   }

   public final scala.collection.mutable.StringBuilder addString$extension(final String $this, final scala.collection.mutable.StringBuilder b) {
      return b.append($this);
   }

   public final scala.collection.mutable.StringBuilder addString$extension(final String $this, final scala.collection.mutable.StringBuilder b, final String sep) {
      return this.addString$extension($this, b, "", sep, "");
   }

   public final scala.collection.mutable.StringBuilder addString$extension(final String $this, final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
      StringBuilder jsb = b.underlying();
      if (start.length() != 0) {
         jsb.append(start);
      }

      int len = $this.length();
      if (len != 0) {
         if (sep.isEmpty()) {
            jsb.append($this);
         } else {
            jsb.ensureCapacity(jsb.length() + len + end.length() + (len - 1) * sep.length());
            jsb.append($this.charAt(0));

            for(int i = 1; i < len; ++i) {
               jsb.append(sep);
               jsb.append($this.charAt(i));
            }
         }
      }

      if (end.length() != 0) {
         jsb.append(end);
      }

      return b;
   }

   public final String slice$extension(final String $this, final int from, final int until) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int max$extension_that = 0;
      scala.math.package$ var8 = scala.math.package$.MODULE$;
      int start = Math.max(from, max$extension_that);
      RichInt$ var9 = RichInt$.MODULE$;
      int min$extension_that = $this.length();
      scala.math.package$ var10 = scala.math.package$.MODULE$;
      int end = Math.min(until, min$extension_that);
      return start >= end ? "" : $this.substring(start, end);
   }

   public final String $times$extension(final String $this, final int n) {
      if (n <= 0) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder($this.length() * n);

         for(int i = 0; i < n; ++i) {
            sb.append($this);
         }

         return sb.toString();
      }
   }

   public final boolean isLineBreak$extension(final String $this, final char c) {
      return c == '\r' || c == '\n';
   }

   public final boolean isLineBreak2$extension(final String $this, final char c0, final char c) {
      return c0 == '\r' && c == '\n';
   }

   public final String stripLineEnd$extension(final String $this) {
      if ($this.isEmpty()) {
         return $this;
      } else {
         int i = $this.length() - 1;
         char last = $this.charAt(i);
         if (last != '\r' && last != '\n') {
            return $this;
         } else {
            if (i > 0) {
               int apply$extension_i = i - 1;
               if ($this.charAt(apply$extension_i) == '\r' && last == '\n') {
                  --i;
               }
            }

            return $this.substring(0, i);
         }
      }
   }

   public final Iterator linesWithSeparators$extension(final String $this) {
      boolean linesSeparated$extension_stripped = false;
      return new AbstractIterator($this, linesSeparated$extension_stripped) {
         public final int scala$collection$StringOps$$anon$$len;
         public int scala$collection$StringOps$$anon$$index;
         private final String $this$2;
         private final boolean stripped$1;

         public boolean hasNext() {
            return this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len;
         }

         public String next() {
            if (this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (String)Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               return this.advance();
            }
         }

         private boolean done() {
            return this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len;
         }

         private String advance() {
            int start;
            for(start = this.scala$collection$StringOps$$anon$$index; this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len; ++this.scala$collection$StringOps$$anon$$index) {
               String var10000 = this.$this$2;
               char isLineBreak$extension_c = this.$this$2.charAt(this.scala$collection$StringOps$$anon$$index);
               if (isLineBreak$extension_c == '\r' || isLineBreak$extension_c == '\n') {
                  break;
               }
            }

            int end = this.scala$collection$StringOps$$anon$$index;
            if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
               int apply$extension_i = this.scala$collection$StringOps$$anon$$index;
               String apply$extension_$this = this.$this$2;
               char var12 = apply$extension_$this.charAt(apply$extension_i);
               Object var10 = null;
               char c = var12;
               ++this.scala$collection$StringOps$$anon$$index;
               if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
                  String var13 = this.$this$2;
                  int apply$extension_ix = this.scala$collection$StringOps$$anon$$index;
                  String apply$extension_$this = this.$this$2;
                  char var14 = apply$extension_$this.charAt(apply$extension_ix);
                  Object var11 = null;
                  char isLineBreak2$extension_c = var14;
                  if (c == '\r' && isLineBreak2$extension_c == '\n') {
                     ++this.scala$collection$StringOps$$anon$$index;
                  }
               }

               if (!this.stripped$1) {
                  end = this.scala$collection$StringOps$$anon$$index;
               }
            }

            return this.$this$2.substring(start, end);
         }

         public {
            this.$this$2 = $this$2;
            this.stripped$1 = stripped$1;
            this.scala$collection$StringOps$$anon$$len = $this$2.length();
            this.scala$collection$StringOps$$anon$$index = 0;
         }
      };
   }

   public final Iterator linesIterator$extension(final String $this) {
      boolean linesSeparated$extension_stripped = true;
      return new AbstractIterator($this, linesSeparated$extension_stripped) {
         public final int scala$collection$StringOps$$anon$$len;
         public int scala$collection$StringOps$$anon$$index;
         private final String $this$2;
         private final boolean stripped$1;

         public boolean hasNext() {
            return this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len;
         }

         public String next() {
            if (this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (String)Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               return this.advance();
            }
         }

         private boolean done() {
            return this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len;
         }

         private String advance() {
            int start;
            for(start = this.scala$collection$StringOps$$anon$$index; this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len; ++this.scala$collection$StringOps$$anon$$index) {
               String var10000 = this.$this$2;
               char isLineBreak$extension_c = this.$this$2.charAt(this.scala$collection$StringOps$$anon$$index);
               if (isLineBreak$extension_c == '\r' || isLineBreak$extension_c == '\n') {
                  break;
               }
            }

            int end = this.scala$collection$StringOps$$anon$$index;
            if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
               int apply$extension_i = this.scala$collection$StringOps$$anon$$index;
               String apply$extension_$this = this.$this$2;
               char var12 = apply$extension_$this.charAt(apply$extension_i);
               Object var10 = null;
               char c = var12;
               ++this.scala$collection$StringOps$$anon$$index;
               if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
                  String var13 = this.$this$2;
                  int apply$extension_ix = this.scala$collection$StringOps$$anon$$index;
                  String apply$extension_$this = this.$this$2;
                  char var14 = apply$extension_$this.charAt(apply$extension_ix);
                  Object var11 = null;
                  char isLineBreak2$extension_c = var14;
                  if (c == '\r' && isLineBreak2$extension_c == '\n') {
                     ++this.scala$collection$StringOps$$anon$$index;
                  }
               }

               if (!this.stripped$1) {
                  end = this.scala$collection$StringOps$$anon$$index;
               }
            }

            return this.$this$2.substring(start, end);
         }

         public {
            this.$this$2 = $this$2;
            this.stripped$1 = stripped$1;
            this.scala$collection$StringOps$$anon$$len = $this$2.length();
            this.scala$collection$StringOps$$anon$$index = 0;
         }
      };
   }

   public final Iterator linesSeparated$extension(final String $this, final boolean stripped) {
      return new AbstractIterator($this, stripped) {
         public final int scala$collection$StringOps$$anon$$len;
         public int scala$collection$StringOps$$anon$$index;
         private final String $this$2;
         private final boolean stripped$1;

         public boolean hasNext() {
            return this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len;
         }

         public String next() {
            if (this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (String)Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               return this.advance();
            }
         }

         private boolean done() {
            return this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len;
         }

         private String advance() {
            int start;
            for(start = this.scala$collection$StringOps$$anon$$index; this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len; ++this.scala$collection$StringOps$$anon$$index) {
               String var10000 = this.$this$2;
               char isLineBreak$extension_c = this.$this$2.charAt(this.scala$collection$StringOps$$anon$$index);
               if (isLineBreak$extension_c == '\r' || isLineBreak$extension_c == '\n') {
                  break;
               }
            }

            int end = this.scala$collection$StringOps$$anon$$index;
            if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
               int apply$extension_i = this.scala$collection$StringOps$$anon$$index;
               String apply$extension_$this = this.$this$2;
               char var12 = apply$extension_$this.charAt(apply$extension_i);
               Object var10 = null;
               char c = var12;
               ++this.scala$collection$StringOps$$anon$$index;
               if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
                  String var13 = this.$this$2;
                  int apply$extension_ix = this.scala$collection$StringOps$$anon$$index;
                  String apply$extension_$this = this.$this$2;
                  char var14 = apply$extension_$this.charAt(apply$extension_ix);
                  Object var11 = null;
                  char isLineBreak2$extension_c = var14;
                  if (c == '\r' && isLineBreak2$extension_c == '\n') {
                     ++this.scala$collection$StringOps$$anon$$index;
                  }
               }

               if (!this.stripped$1) {
                  end = this.scala$collection$StringOps$$anon$$index;
               }
            }

            return this.$this$2.substring(start, end);
         }

         public {
            this.$this$2 = $this$2;
            this.stripped$1 = stripped$1;
            this.scala$collection$StringOps$$anon$$len = $this$2.length();
            this.scala$collection$StringOps$$anon$$index = 0;
         }
      };
   }

   /** @deprecated */
   public final Iterator lines$extension(final String $this) {
      boolean linesIterator$extension_linesSeparated$extension_stripped = true;
      return new AbstractIterator($this, linesIterator$extension_linesSeparated$extension_stripped) {
         public final int scala$collection$StringOps$$anon$$len;
         public int scala$collection$StringOps$$anon$$index;
         private final String $this$2;
         private final boolean stripped$1;

         public boolean hasNext() {
            return this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len;
         }

         public String next() {
            if (this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (String)Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               return this.advance();
            }
         }

         private boolean done() {
            return this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len;
         }

         private String advance() {
            int start;
            for(start = this.scala$collection$StringOps$$anon$$index; this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len; ++this.scala$collection$StringOps$$anon$$index) {
               String var10000 = this.$this$2;
               char isLineBreak$extension_c = this.$this$2.charAt(this.scala$collection$StringOps$$anon$$index);
               if (isLineBreak$extension_c == '\r' || isLineBreak$extension_c == '\n') {
                  break;
               }
            }

            int end = this.scala$collection$StringOps$$anon$$index;
            if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
               int apply$extension_i = this.scala$collection$StringOps$$anon$$index;
               String apply$extension_$this = this.$this$2;
               char var12 = apply$extension_$this.charAt(apply$extension_i);
               Object var10 = null;
               char c = var12;
               ++this.scala$collection$StringOps$$anon$$index;
               if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
                  String var13 = this.$this$2;
                  int apply$extension_ix = this.scala$collection$StringOps$$anon$$index;
                  String apply$extension_$this = this.$this$2;
                  char var14 = apply$extension_$this.charAt(apply$extension_ix);
                  Object var11 = null;
                  char isLineBreak2$extension_c = var14;
                  if (c == '\r' && isLineBreak2$extension_c == '\n') {
                     ++this.scala$collection$StringOps$$anon$$index;
                  }
               }

               if (!this.stripped$1) {
                  end = this.scala$collection$StringOps$$anon$$index;
               }
            }

            return this.$this$2.substring(start, end);
         }

         public {
            this.$this$2 = $this$2;
            this.stripped$1 = stripped$1;
            this.scala$collection$StringOps$$anon$$len = $this$2.length();
            this.scala$collection$StringOps$$anon$$index = 0;
         }
      };
   }

   public final String capitalize$extension(final String $this) {
      if ($this != null && $this.length() != 0) {
         RichChar$ var10000 = RichChar$.MODULE$;
         if (Character.isLowerCase($this.charAt(0))) {
            RichChar$ var10003 = RichChar$.MODULE$;
            return this.updated$extension($this, 0, Character.toUpperCase($this.charAt(0)));
         }
      }

      return $this;
   }

   public final String stripPrefix$extension(final String $this, final String prefix) {
      return $this.startsWith(prefix) ? $this.substring(prefix.length()) : $this;
   }

   public final String stripSuffix$extension(final String $this, final String suffix) {
      return $this.endsWith(suffix) ? $this.substring(0, $this.length() - suffix.length()) : $this;
   }

   /** @deprecated */
   public final String replaceAllLiterally$extension(final String $this, final String literal, final String replacement) {
      return $this.replace(literal, replacement);
   }

   public final String stripMargin$extension(final String $this, final char marginChar) {
      StringBuilder sb = new StringBuilder($this.length());
      boolean linesWithSeparators$extension_linesSeparated$extension_stripped = false;
      (new AbstractIterator($this, linesWithSeparators$extension_linesSeparated$extension_stripped) {
         public final int scala$collection$StringOps$$anon$$len;
         public int scala$collection$StringOps$$anon$$index;
         private final String $this$2;
         private final boolean stripped$1;

         public boolean hasNext() {
            return this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len;
         }

         public String next() {
            if (this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return (String)Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               return this.advance();
            }
         }

         private boolean done() {
            return this.scala$collection$StringOps$$anon$$index >= this.scala$collection$StringOps$$anon$$len;
         }

         private String advance() {
            int start;
            for(start = this.scala$collection$StringOps$$anon$$index; this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len; ++this.scala$collection$StringOps$$anon$$index) {
               String var10000 = this.$this$2;
               char isLineBreak$extension_c = this.$this$2.charAt(this.scala$collection$StringOps$$anon$$index);
               if (isLineBreak$extension_c == '\r' || isLineBreak$extension_c == '\n') {
                  break;
               }
            }

            int end = this.scala$collection$StringOps$$anon$$index;
            if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
               int apply$extension_i = this.scala$collection$StringOps$$anon$$index;
               String apply$extension_$this = this.$this$2;
               char var12 = apply$extension_$this.charAt(apply$extension_i);
               Object var10 = null;
               char c = var12;
               ++this.scala$collection$StringOps$$anon$$index;
               if (this.scala$collection$StringOps$$anon$$index < this.scala$collection$StringOps$$anon$$len) {
                  String var13 = this.$this$2;
                  int apply$extension_ix = this.scala$collection$StringOps$$anon$$index;
                  String apply$extension_$this = this.$this$2;
                  char var14 = apply$extension_$this.charAt(apply$extension_ix);
                  Object var11 = null;
                  char isLineBreak2$extension_c = var14;
                  if (c == '\r' && isLineBreak2$extension_c == '\n') {
                     ++this.scala$collection$StringOps$$anon$$index;
                  }
               }

               if (!this.stripped$1) {
                  end = this.scala$collection$StringOps$$anon$$index;
               }
            }

            return this.$this$2.substring(start, end);
         }

         public {
            this.$this$2 = $this$2;
            this.stripped$1 = stripped$1;
            this.scala$collection$StringOps$$anon$$len = $this$2.length();
            this.scala$collection$StringOps$$anon$$index = 0;
         }
      }).foreach((line) -> {
         int len = line.length();

         int index;
         for(index = 0; index < len && line.charAt(index) <= ' '; ++index) {
         }

         String stripped = index < len && line.charAt(index) == marginChar ? line.substring(index + 1) : line;
         return sb.append(stripped);
      });
      return sb.toString();
   }

   public final String stripMargin$extension(final String $this) {
      return this.stripMargin$extension($this, '|');
   }

   public final String escape$extension(final String $this, final char ch) {
      return (ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9') ? (new StringBuilder(1)).append("\\").append(ch).toString() : Character.toString(ch);
   }

   public final String[] split$extension(final String $this, final char separator) {
      return $this.split(this.escape$extension($this, separator));
   }

   public final String[] split$extension(final String $this, final char[] separators) throws PatternSyntaxException {
      // $FF: Couldn't be decompiled
   }

   public final Regex r$extension(final String $this) {
      return new Regex($this, Nil$.MODULE$);
   }

   /** @deprecated */
   public final Regex r$extension(final String $this, final scala.collection.immutable.Seq groupNames) {
      return new Regex($this, groupNames);
   }

   public final boolean toBoolean$extension(final String $this) {
      return this.toBooleanImpl$extension($this, $this);
   }

   public final Option toBooleanOption$extension(final String $this) {
      StringParsers$ var10000 = StringParsers$.MODULE$;
      if ($this.equalsIgnoreCase("true")) {
         return new Some(true);
      } else {
         return (Option)($this.equalsIgnoreCase("false") ? new Some(false) : None$.MODULE$);
      }
   }

   public final byte toByte$extension(final String $this) {
      return Byte.parseByte($this);
   }

   public final Option toByteOption$extension(final String $this) {
      return StringParsers$.MODULE$.parseByte($this);
   }

   public final short toShort$extension(final String $this) {
      return Short.parseShort($this);
   }

   public final Option toShortOption$extension(final String $this) {
      return StringParsers$.MODULE$.parseShort($this);
   }

   public final int toInt$extension(final String $this) {
      return Integer.parseInt($this);
   }

   public final Option toIntOption$extension(final String $this) {
      return StringParsers$.MODULE$.parseInt($this);
   }

   public final long toLong$extension(final String $this) {
      return Long.parseLong($this);
   }

   public final Option toLongOption$extension(final String $this) {
      return StringParsers$.MODULE$.parseLong($this);
   }

   public final float toFloat$extension(final String $this) {
      return Float.parseFloat($this);
   }

   public final Option toFloatOption$extension(final String $this) {
      return (Option)(StringParsers$.MODULE$.checkFloatFormat($this) ? new Some(Float.parseFloat($this)) : None$.MODULE$);
   }

   public final double toDouble$extension(final String $this) {
      return Double.parseDouble($this);
   }

   public final Option toDoubleOption$extension(final String $this) {
      return (Option)(StringParsers$.MODULE$.checkFloatFormat($this) ? new Some(Double.parseDouble($this)) : None$.MODULE$);
   }

   public final boolean toBooleanImpl$extension(final String $this, final String s) {
      if (s == null) {
         throw new IllegalArgumentException("For input string: \"null\"");
      } else if (s.equalsIgnoreCase("true")) {
         return true;
      } else if (s.equalsIgnoreCase("false")) {
         return false;
      } else {
         throw new IllegalArgumentException((new StringBuilder(20)).append("For input string: \"").append(s).append("\"").toString());
      }
   }

   public final Object toArray$extension(final String $this, final ClassTag tag) {
      ManifestFactory.CharManifest var3 = ClassTag$.MODULE$.Char();
      if (tag != null) {
         if (tag.equals(var3)) {
            return $this.toCharArray();
         }
      }

      return IterableOnceOps.toArray$(new WrappedString($this), tag);
   }

   public final Object unwrapArg$extension(final String $this, final Object arg) {
      return arg instanceof ScalaNumber ? ((ScalaNumber)arg).underlying() : arg;
   }

   public final String format$extension(final String $this, final scala.collection.immutable.Seq args) {
      return String.format($this, ((IterableOnceOps)args.map((arg) -> MODULE$.unwrapArg$extension($this, arg))).toArray(ClassTag$.MODULE$.AnyRef()));
   }

   public final String formatLocal$extension(final String $this, final Locale l, final scala.collection.immutable.Seq args) {
      return String.format(l, $this, ((IterableOnceOps)args.map((arg) -> MODULE$.unwrapArg$extension($this, arg))).toArray(ClassTag$.MODULE$.AnyRef()));
   }

   public final int compare$extension(final String $this, final String that) {
      return $this.compareTo(that);
   }

   public final boolean $less$extension(final String $this, final String that) {
      return $this.compareTo(that) < 0;
   }

   public final boolean $greater$extension(final String $this, final String that) {
      return $this.compareTo(that) > 0;
   }

   public final boolean $less$eq$extension(final String $this, final String that) {
      return $this.compareTo(that) <= 0;
   }

   public final boolean $greater$eq$extension(final String $this, final String that) {
      return $this.compareTo(that) >= 0;
   }

   public final int count$extension(final String $this, final Function1 p) {
      int i = 0;
      int res = 0;

      for(int len = $this.length(); i < len; ++i) {
         if (BoxesRunTime.unboxToBoolean(p.apply($this.charAt(i)))) {
            ++res;
         }
      }

      return res;
   }

   public final void foreach$extension(final String $this, final Function1 f) {
      int len = $this.length();

      for(int i = 0; i < len; ++i) {
         f.apply($this.charAt(i));
      }

   }

   public final boolean forall$extension(final String $this, final Function1 p) {
      int i = 0;

      for(int len = $this.length(); i < len; ++i) {
         if (!BoxesRunTime.unboxToBoolean(p.apply($this.charAt(i)))) {
            return false;
         }
      }

      return true;
   }

   public final Object foldLeft$extension(final String $this, final Object z, final Function2 op) {
      Object v = z;
      int i = 0;

      for(int len = $this.length(); i < len; ++i) {
         v = op.apply(v, $this.charAt(i));
      }

      return v;
   }

   public final Object foldRight$extension(final String $this, final Object z, final Function2 op) {
      Object v = z;

      for(int i = $this.length() - 1; i >= 0; --i) {
         v = op.apply($this.charAt(i), v);
      }

      return v;
   }

   public final Object fold$extension(final String $this, final Object z, final Function2 op) {
      Object foldLeft$extension_v = z;
      int foldLeft$extension_i = 0;

      for(int foldLeft$extension_len = $this.length(); foldLeft$extension_i < foldLeft$extension_len; ++foldLeft$extension_i) {
         foldLeft$extension_v = op.apply(foldLeft$extension_v, $this.charAt(foldLeft$extension_i));
      }

      return foldLeft$extension_v;
   }

   public final char head$extension(final String $this) {
      if ($this.isEmpty()) {
         throw new NoSuchElementException("head of empty String");
      } else {
         return $this.charAt(0);
      }
   }

   public final Option headOption$extension(final String $this) {
      return (Option)($this.isEmpty() ? None$.MODULE$ : new Some($this.charAt(0)));
   }

   public final char last$extension(final String $this) {
      if ($this.isEmpty()) {
         throw new NoSuchElementException("last of empty String");
      } else {
         return $this.charAt($this.length() - 1);
      }
   }

   public final Option lastOption$extension(final String $this) {
      return (Option)($this.isEmpty() ? None$.MODULE$ : new Some($this.charAt($this.length() - 1)));
   }

   public final Range indices$extension(final String $this) {
      Range$ var10000 = scala.package$.MODULE$.Range();
      int apply_end = $this.length();
      byte apply_start = 0;
      if (var10000 == null) {
         throw null;
      } else {
         return new Range.Exclusive(apply_start, apply_end, 1);
      }
   }

   public final Iterator iterator$extension(final String $this) {
      return new StringOps.StringIterator($this);
   }

   public final IntStepper stepper$extension(final String $this) {
      return new CharStringStepper($this, 0, $this.length());
   }

   public final IntStepper charStepper$extension(final String $this) {
      return new CharStringStepper($this, 0, $this.length());
   }

   public final IntStepper codePointStepper$extension(final String $this) {
      return new CodePointStringStepper($this, 0, $this.length());
   }

   public final boolean nonEmpty$extension(final String $this) {
      return !$this.isEmpty();
   }

   public final String reverse$extension(final String $this) {
      return (new StringBuilder($this)).reverse().toString();
   }

   public final Iterator reverseIterator$extension(final String $this) {
      return new StringOps.ReverseIterator($this);
   }

   public final StringOps.WithFilter withFilter$extension(final String $this, final Function1 p) {
      return new StringOps.WithFilter(p, $this);
   }

   public final String tail$extension(final String $this) {
      if ($this.isEmpty()) {
         throw new UnsupportedOperationException("tail of empty String");
      } else {
         return this.slice$extension($this, 1, $this.length());
      }
   }

   public final String init$extension(final String $this) {
      if ($this.isEmpty()) {
         throw new UnsupportedOperationException("init of empty String");
      } else {
         return this.slice$extension($this, 0, $this.length() - 1);
      }
   }

   public final String take$extension(final String $this, final int n) {
      scala.math.package$ var10003 = scala.math.package$.MODULE$;
      int min_y = $this.length();
      return this.slice$extension($this, 0, Math.min(n, min_y));
   }

   public final String drop$extension(final String $this, final int n) {
      scala.math.package$ var10002 = scala.math.package$.MODULE$;
      int min_y = $this.length();
      return this.slice$extension($this, Math.min(n, min_y), $this.length());
   }

   public final String takeRight$extension(final String $this, final int n) {
      int var10002 = $this.length();
      scala.math.package$ var10003 = scala.math.package$.MODULE$;
      int max_y = 0;
      return this.drop$extension($this, var10002 - Math.max(n, max_y));
   }

   public final String dropRight$extension(final String $this, final int n) {
      int var10002 = $this.length();
      scala.math.package$ var10003 = scala.math.package$.MODULE$;
      int max_y = 0;
      return this.take$extension($this, var10002 - Math.max(n, max_y));
   }

   public final Iterator tails$extension(final String $this) {
      Function1 iterateUntilEmpty$extension_f = (x$4) -> MODULE$.tail$extension(x$4);
      Iterator$ var10000 = Iterator$.MODULE$;
      Iterator var4 = (new AbstractIterator($this, iterateUntilEmpty$extension_f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      }).takeWhile((x) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x)));
      Function0 iterateUntilEmpty$extension_$plus$plus_xs = () -> Iterator$.MODULE$.single("");
      if (var4 == null) {
         throw null;
      } else {
         return var4.concat(iterateUntilEmpty$extension_$plus$plus_xs);
      }
   }

   public final Iterator inits$extension(final String $this) {
      Function1 iterateUntilEmpty$extension_f = (x$5) -> MODULE$.init$extension(x$5);
      Iterator$ var10000 = Iterator$.MODULE$;
      Iterator var4 = (new AbstractIterator($this, iterateUntilEmpty$extension_f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      }).takeWhile((x) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x)));
      Function0 iterateUntilEmpty$extension_$plus$plus_xs = () -> Iterator$.MODULE$.single("");
      if (var4 == null) {
         throw null;
      } else {
         return var4.concat(iterateUntilEmpty$extension_$plus$plus_xs);
      }
   }

   public final Iterator iterateUntilEmpty$extension(final String $this, final Function1 f) {
      Iterator$ var10000 = Iterator$.MODULE$;
      Iterator var4 = (new AbstractIterator($this, f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      }).takeWhile((x) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x)));
      Function0 $plus$plus_xs = () -> Iterator$.MODULE$.single("");
      if (var4 == null) {
         throw null;
      } else {
         return var4.concat($plus$plus_xs);
      }
   }

   public final String filter$extension(final String $this, final Function1 pred) {
      int len = $this.length();
      StringBuilder sb = new StringBuilder(len);

      for(int i = 0; i < len; ++i) {
         char x = $this.charAt(i);
         if (BoxesRunTime.unboxToBoolean(pred.apply(x))) {
            sb.append(x);
         }
      }

      if (len == sb.length()) {
         return $this;
      } else {
         return sb.toString();
      }
   }

   public final String filterNot$extension(final String $this, final Function1 pred) {
      int filter$extension_len = $this.length();
      StringBuilder filter$extension_sb = new StringBuilder(filter$extension_len);

      for(int filter$extension_i = 0; filter$extension_i < filter$extension_len; ++filter$extension_i) {
         char filter$extension_x = $this.charAt(filter$extension_i);
         if (!BoxesRunTime.unboxToBoolean(pred.apply(filter$extension_x))) {
            filter$extension_sb.append(filter$extension_x);
         }
      }

      if (filter$extension_len == filter$extension_sb.length()) {
         return $this;
      } else {
         return filter$extension_sb.toString();
      }
   }

   public final int copyToArray$extension(final String $this, final char[] xs) {
      return this.copyToArray$extension($this, xs, 0, Integer.MAX_VALUE);
   }

   public final int copyToArray$extension(final String $this, final char[] xs, final int start) {
      return this.copyToArray$extension($this, xs, start, Integer.MAX_VALUE);
   }

   public final int copyToArray$extension(final String $this, final char[] xs, final int start, final int len) {
      IterableOnce$ var10000 = IterableOnce$.MODULE$;
      int var8 = $this.length();
      int elemsToCopyToArray_destLen = xs.length;
      int elemsToCopyToArray_srcLen = var8;
      scala.math.package$ var9 = scala.math.package$.MODULE$;
      var9 = scala.math.package$.MODULE$;
      var9 = scala.math.package$.MODULE$;
      int copied = Math.max(Math.min(Math.min(len, elemsToCopyToArray_srcLen), elemsToCopyToArray_destLen - start), 0);
      if (copied > 0) {
         $this.getChars(0, copied, xs, start);
      }

      return copied;
   }

   public final int indexWhere$extension(final String $this, final Function1 p, final int from) {
      int len = $this.length();

      for(int i = from; i < len; ++i) {
         if (BoxesRunTime.unboxToBoolean(p.apply($this.charAt(i)))) {
            return i;
         }
      }

      return -1;
   }

   public final int indexWhere$default$2$extension(final String $this) {
      return 0;
   }

   public final int lastIndexWhere$extension(final String $this, final Function1 p, final int end) {
      int len = $this.length();
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int min_y = len - 1;

      for(int i = Math.min(end, min_y); i >= 0; --i) {
         if (BoxesRunTime.unboxToBoolean(p.apply($this.charAt(i)))) {
            return i;
         }
      }

      return -1;
   }

   public final int lastIndexWhere$default$2$extension(final String $this) {
      return Integer.MAX_VALUE;
   }

   public final boolean exists$extension(final String $this, final Function1 p) {
      int indexWhere$extension_from = 0;
      int indexWhere$extension_len = $this.length();
      int indexWhere$extension_i = indexWhere$extension_from;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         if (BoxesRunTime.unboxToBoolean(p.apply($this.charAt(indexWhere$extension_i)))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      return var10000 != -1;
   }

   public final Option find$extension(final String $this, final Function1 p) {
      int indexWhere$extension_from = 0;
      int indexWhere$extension_len = $this.length();
      int indexWhere$extension_i = indexWhere$extension_from;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         if (BoxesRunTime.unboxToBoolean(p.apply($this.charAt(indexWhere$extension_i)))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int var3 = var10000;
      switch (var3) {
         case -1:
            return None$.MODULE$;
         default:
            return new Some($this.charAt(var3));
      }
   }

   public final String dropWhile$extension(final String $this, final Function1 p) {
      int indexWhere$extension_from = 0;
      int indexWhere$extension_len = $this.length();
      int indexWhere$extension_i = indexWhere$extension_from;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         char indexWhere$extension_boxToCharacter_c = $this.charAt(indexWhere$extension_i);
         if (!BoxesRunTime.unboxToBoolean(p.apply(indexWhere$extension_boxToCharacter_c))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int var3 = var10000;
      switch (var3) {
         case -1:
            return "";
         default:
            return $this.substring(var3);
      }
   }

   public final String takeWhile$extension(final String $this, final Function1 p) {
      int indexWhere$extension_from = 0;
      int indexWhere$extension_len = $this.length();
      int indexWhere$extension_i = indexWhere$extension_from;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         char indexWhere$extension_boxToCharacter_c = $this.charAt(indexWhere$extension_i);
         if (!BoxesRunTime.unboxToBoolean(p.apply(indexWhere$extension_boxToCharacter_c))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int var3 = var10000;
      switch (var3) {
         case -1:
            return $this;
         default:
            return $this.substring(0, var3);
      }
   }

   public final Tuple2 splitAt$extension(final String $this, final int n) {
      return new Tuple2(this.take$extension($this, n), this.drop$extension($this, n));
   }

   public final Tuple2 span$extension(final String $this, final Function1 p) {
      int indexWhere$extension_from = 0;
      int indexWhere$extension_len = $this.length();
      int indexWhere$extension_i = indexWhere$extension_from;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         char indexWhere$extension_boxToCharacter_c = $this.charAt(indexWhere$extension_i);
         if (!BoxesRunTime.unboxToBoolean(p.apply(indexWhere$extension_boxToCharacter_c))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int var3 = var10000;
      switch (var3) {
         case -1:
            return new Tuple2($this, "");
         default:
            return new Tuple2($this.substring(0, var3), $this.substring(var3));
      }
   }

   public final Iterator grouped$extension(final String $this, final int size) {
      return new StringOps.GroupedIterator($this, size);
   }

   public final Tuple2 partition$extension(final String $this, final Function1 p) {
      StringBuilder res1 = new StringBuilder();
      StringBuilder res2 = new StringBuilder();
      int i = 0;

      for(int len = $this.length(); i < len; ++i) {
         char x = $this.charAt(i);
         (BoxesRunTime.unboxToBoolean(p.apply(x)) ? res1 : res2).append(x);
      }

      return new Tuple2(res1.toString(), res2.toString());
   }

   public final Tuple2 partitionMap$extension(final String $this, final Function1 f) {
      StringBuilder res1 = new StringBuilder();
      StringBuilder res2 = new StringBuilder();
      int i = 0;

      for(int len = $this.length(); i < len; ++i) {
         Either var7 = (Either)f.apply($this.charAt(i));
         if (var7 instanceof Left) {
            char c = BoxesRunTime.unboxToChar(((Left)var7).value());
            res1.append(c);
         } else {
            if (!(var7 instanceof Right)) {
               throw new MatchError(var7);
            }

            char c = BoxesRunTime.unboxToChar(((Right)var7).value());
            res2.append(c);
         }
      }

      return new Tuple2(res1.toString(), res2.toString());
   }

   public final LazyZip2 lazyZip$extension(final String $this, final Iterable that) {
      return new LazyZip2($this, new WrappedString($this), that);
   }

   public final String diff$extension(final String $this, final Seq that) {
      WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
      WrappedString$ var3 = WrappedString$.MODULE$;
      return ((WrappedString)SeqOps.diff$(new WrappedString($this), that)).scala$collection$immutable$WrappedString$$self();
   }

   public final String intersect$extension(final String $this, final Seq that) {
      WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
      WrappedString$ var3 = WrappedString$.MODULE$;
      return ((WrappedString)SeqOps.intersect$(new WrappedString($this), that)).scala$collection$immutable$WrappedString$$self();
   }

   public final String distinct$extension(final String $this) {
      WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
      WrappedString$ var2 = WrappedString$.MODULE$;
      return ((WrappedString)SeqOps.distinct$(new WrappedString($this))).scala$collection$immutable$WrappedString$$self();
   }

   public final String distinctBy$extension(final String $this, final Function1 f) {
      WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
      WrappedString$ var7 = WrappedString$.MODULE$;
      AbstractSeq distinctBy_this = new WrappedString($this);
      IterableOnce fromSpecific_coll = new View.DistinctBy(distinctBy_this, f);
      WrappedString var8 = WrappedString$.MODULE$.fromSpecific(fromSpecific_coll);
      fromSpecific_coll = null;
      distinctBy_this = null;
      return var8.scala$collection$immutable$WrappedString$$self();
   }

   public final String sorted$extension(final String $this, final Ordering ord) {
      WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
      WrappedString$ var3 = WrappedString$.MODULE$;
      return ((WrappedString)SeqOps.sorted$(new WrappedString($this), ord)).scala$collection$immutable$WrappedString$$self();
   }

   public final String sortWith$extension(final String $this, final Function2 lt) {
      WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
      WrappedString$ var3 = WrappedString$.MODULE$;
      WrappedString var4 = new WrappedString($this);
      if (scala.package$.MODULE$.Ordering() == null) {
         throw null;
      } else {
         return ((WrappedString)var4.sorted(new Ordering(lt) {
            private final Function2 cmp$2;

            public Some tryCompare(final Object x, final Object y) {
               return Ordering.tryCompare$(this, x, y);
            }

            public boolean equiv(final Object x, final Object y) {
               return Ordering.equiv$(this, x, y);
            }

            public Object max(final Object x, final Object y) {
               return Ordering.max$(this, x, y);
            }

            public Object min(final Object x, final Object y) {
               return Ordering.min$(this, x, y);
            }

            public Ordering reverse() {
               return Ordering.reverse$(this);
            }

            public boolean isReverseOf(final Ordering other) {
               return Ordering.isReverseOf$(this, other);
            }

            public Ordering on(final Function1 f) {
               return Ordering.on$(this, f);
            }

            public Ordering orElse(final Ordering other) {
               return Ordering.orElse$(this, other);
            }

            public Ordering orElseBy(final Function1 f, final Ordering ord) {
               return Ordering.orElseBy$(this, f, ord);
            }

            public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
               return Ordering.mkOrderingOps$(this, lhs);
            }

            public int compare(final Object x, final Object y) {
               if (BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y))) {
                  return -1;
               } else {
                  return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x)) ? 1 : 0;
               }
            }

            public boolean lt(final Object x, final Object y) {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
            }

            public boolean gt(final Object x, final Object y) {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
            }

            public boolean gteq(final Object x, final Object y) {
               return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
            }

            public boolean lteq(final Object x, final Object y) {
               return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
            }

            public {
               this.cmp$2 = cmp$2;
            }
         })).scala$collection$immutable$WrappedString$$self();
      }
   }

   public final String sortBy$extension(final String $this, final Function1 f, final Ordering ord) {
      WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
      WrappedString$ var4 = WrappedString$.MODULE$;
      return ((WrappedString)(new WrappedString($this)).sorted(ord.on(f))).scala$collection$immutable$WrappedString$$self();
   }

   public final scala.collection.immutable.Map groupBy$extension(final String $this, final Function1 f) {
      AbstractIterable groupBy_this = new WrappedString($this);
      scala.collection.mutable.Map groupBy_m = (scala.collection.mutable.Map)scala.collection.mutable.Map$.MODULE$.empty();
      Iterator groupBy_it = IndexedSeqOps.iterator$(groupBy_this);

      while(groupBy_it.hasNext()) {
         Object groupBy_elem = groupBy_it.next();
         Object groupBy_key = f.apply(groupBy_elem);
         Builder groupBy_bldr = (Builder)groupBy_m.getOrElseUpdate(groupBy_key, IterableOps::$anonfun$groupBy$1);
         if (groupBy_bldr == null) {
            throw null;
         }

         groupBy_bldr.addOne(groupBy_elem);
      }

      HashMap groupBy_result = HashMap$.MODULE$.empty();

      Object groupBy_k;
      Builder groupBy_v;
      for(Iterator groupBy_mapIt = groupBy_m.iterator(); groupBy_mapIt.hasNext(); groupBy_result = groupBy_result.updated(groupBy_k, groupBy_v.result())) {
         Tuple2 var11 = (Tuple2)groupBy_mapIt.next();
         if (var11 == null) {
            throw new MatchError((Object)null);
         }

         groupBy_k = var11._1();
         groupBy_v = (Builder)var11._2();
      }

      HashMap var10000 = groupBy_result;
      Object var15 = null;
      Object var16 = null;
      Object var17 = null;
      Object var18 = null;
      Object var19 = null;
      groupBy_result = null;
      Object var21 = null;
      Object var22 = null;
      groupBy_k = null;
      groupBy_v = null;
      groupBy_this = null;
      return var10000.view().mapValues((x$6) -> {
         WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
         WrappedString$ var1 = WrappedString$.MODULE$;
         return x$6.scala$collection$immutable$WrappedString$$self();
      }).toMap($less$colon$less$.MODULE$.refl());
   }

   public final Iterator sliding$extension(final String $this, final int size, final int step) {
      return IterableOps.sliding$(new WrappedString($this), size, step).map((x$7) -> {
         WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
         WrappedString$ var1 = WrappedString$.MODULE$;
         return x$7.scala$collection$immutable$WrappedString$$self();
      });
   }

   public final int sliding$default$2$extension(final String $this) {
      return 1;
   }

   public final Iterator combinations$extension(final String $this, final int n) {
      return SeqOps.combinations$(new WrappedString($this), n).map((x$8) -> {
         WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
         WrappedString$ var1 = WrappedString$.MODULE$;
         return x$8.scala$collection$immutable$WrappedString$$self();
      });
   }

   public final Iterator permutations$extension(final String $this) {
      return SeqOps.permutations$(new WrappedString($this)).map((x$9) -> {
         WrappedString.UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
         WrappedString$ var1 = WrappedString$.MODULE$;
         return x$9.scala$collection$immutable$WrappedString$$self();
      });
   }

   public final int hashCode$extension(final String $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final String $this, final Object x$1) {
      if (x$1 instanceof StringOps) {
         String var3 = x$1 == null ? null : ((StringOps)x$1).scala$collection$StringOps$$s();
         if ($this == null) {
            if (var3 == null) {
               return true;
            }
         } else if ($this.equals(var3)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final StringBuilder $anonfun$concat$1(final StringBuilder sb$1, final char ch) {
      return sb$1.append(ch);
   }

   private static final WrappedString slc$1(final int off, final int length, final String $this$1) {
      return new WrappedString($this$1.substring(off, off + length));
   }

   // $FF: synthetic method
   public static final String $anonfun$split$1(final String $this$3, final String x$2, final char x$3) {
      return (new StringBuilder(0)).append(x$2).append(MODULE$.escape$extension($this$3, x$3)).toString();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterateUntilEmpty$1(final String x) {
      return !x.isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterNot$1(final Function1 pred$1, final char c) {
      return !BoxesRunTime.unboxToBoolean(pred$1.apply(c));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$dropWhile$1(final Function1 p$1, final char c) {
      return !BoxesRunTime.unboxToBoolean(p$1.apply(c));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$takeWhile$1(final Function1 p$2, final char c) {
      return !BoxesRunTime.unboxToBoolean(p$2.apply(c));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$span$1(final Function1 p$3, final char c) {
      return !BoxesRunTime.unboxToBoolean(p$3.apply(c));
   }

   private StringOps$() {
   }

   // $FF: synthetic method
   public static final String $anonfun$split$1$adapted(final String $this$3, final String x$2, final Object x$3) {
      return $anonfun$split$1($this$3, x$2, BoxesRunTime.unboxToChar(x$3));
   }

   // $FF: synthetic method
   public static final Object $anonfun$filterNot$1$adapted(final Function1 pred$1, final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$filterNot$1(pred$1, BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$dropWhile$1$adapted(final Function1 p$1, final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$dropWhile$1(p$1, BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$takeWhile$1$adapted(final Function1 p$2, final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$takeWhile$1(p$2, BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$span$1$adapted(final Function1 p$3, final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$span$1(p$3, BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
