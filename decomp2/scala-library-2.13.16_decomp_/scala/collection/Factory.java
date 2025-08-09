package scala.collection;

import java.io.Serializable;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uaa\u0002\n\u0014!\u0003\r\n\u0001\u0007\u0005\u0006=\u00011\ta\b\u0005\u0006c\u00011\tAM\u0004\u0006sMA\tA\u000f\u0004\u0006%MA\ta\u000f\u0005\u0006\u007f\u0011!\t\u0001\u0011\u0005\b\u0003\u0012\u0011\r\u0011b\u0001C\u0011\u0019\u0011F\u0001)A\u0005\u0007\u001a!1\u000b\u0002\u0003U\u0011\u0015y\u0004\u0002\"\u0001]\u0011\u0015q\u0002\u0002\"\u0001`\u0011\u0015\t\u0004\u0002\"\u0001c\u0011\u0015QG\u0001b\u0001l\r\u0011YH\u0001\u0002?\t\u0015\u0005\u0015QBaA!\u0002\u0017\t9\u0001\u0003\u0004@\u001b\u0011\u0005\u0011\u0011\u0002\u0005\u0007=5!\t!!\u0005\t\rEjA\u0011AA\f\u0005\u001d1\u0015m\u0019;pefT!\u0001F\u000b\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0007\u0001)2!G\u0018#'\t\u0001!\u0004\u0005\u0002\u001c95\tQ#\u0003\u0002\u001e+\t\u0019\u0011I\\=\u0002\u0019\u0019\u0014x.\\*qK\u000eLg-[2\u0015\u0005\u0001B\u0003CA\u0011#\u0019\u0001!aa\t\u0001\u0005\u0006\u0004!#!A\"\u0012\u0005\u0015R\u0002CA\u000e'\u0013\t9SCA\u0004O_RD\u0017N\\4\t\u000b%\n\u0001\u0019\u0001\u0016\u0002\u0005%$\bcA\u0016-]5\t1#\u0003\u0002.'\ta\u0011\n^3sC\ndWm\u00148dKB\u0011\u0011e\f\u0003\u0007a\u0001A)\u0019\u0001\u0013\u0003\u0003\u0005\u000b!B\\3x\u0005VLG\u000eZ3s+\u0005\u0019\u0004\u0003\u0002\u001b8]\u0001j\u0011!\u000e\u0006\u0003mM\tq!\\;uC\ndW-\u0003\u00029k\t9!)^5mI\u0016\u0014\u0018a\u0002$bGR|'/\u001f\t\u0003W\u0011\u0019\"\u0001\u0002\u001f\u0011\u0005mi\u0014B\u0001 \u0016\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012AO\u0001\u000egR\u0014\u0018N\\4GC\u000e$xN]=\u0016\u0003\r\u0003Ba\u000b\u0001E\u000fB\u00111$R\u0005\u0003\rV\u0011Aa\u00115beB\u0011\u0001j\u0014\b\u0003\u00136\u0003\"AS\u000b\u000e\u0003-S!\u0001T\f\u0002\rq\u0012xn\u001c;?\u0013\tqU#\u0001\u0004Qe\u0016$WMZ\u0005\u0003!F\u0013aa\u0015;sS:<'B\u0001(\u0016\u00039\u0019HO]5oO\u001a\u000b7\r^8ss\u0002\u0012Qb\u0015;sS:<g)Y2u_JL8\u0003\u0002\u0005=\u0007V\u0003\"AV-\u000f\u0005m9\u0016B\u0001-\u0016\u0003\u001d\u0001\u0018mY6bO\u0016L!AW.\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005a+B#A/\u0011\u0005yCQ\"\u0001\u0003\u0015\u0005\u001d\u0003\u0007\"B\u0015\u000b\u0001\u0004\t\u0007cA\u0016-\tV\t1\r\u0005\u00035o\u0011;\u0005\u0006\u0002\u0005fQ&\u0004\"a\u00074\n\u0005\u001d,\"\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0011\u0001D1se\u0006Lh)Y2u_JLXC\u00017p)\ti7\u000f\u0005\u0003,\u00019\u0004\bCA\u0011p\t\u0015\u0001DB1\u0001%!\rY\u0012O\\\u0005\u0003eV\u0011Q!\u0011:sCfDq\u0001\u001e\u0007\u0002\u0002\u0003\u000fQ/\u0001\u0006fm&$WM\\2fIE\u00022A^=o\u001b\u00059(B\u0001=\u0016\u0003\u001d\u0011XM\u001a7fGRL!A_<\u0003\u0011\rc\u0017m]:UC\u001e\u0014A\"\u0011:sCf4\u0015m\u0019;pef,2!`A\u0001'\u0011iAH`+\u0011\u000b-\u0002q0a\u0001\u0011\u0007\u0005\n\t\u0001B\u00031\u001b\t\u0007A\u0005E\u0002\u001cc~\f!\"\u001a<jI\u0016t7-\u001a\u00133!\r1\u0018p \u000b\u0003\u0003\u0017!B!!\u0004\u0002\u0010A\u0019a,D@\t\u000f\u0005\u0015q\u0002q\u0001\u0002\bQ!\u00111AA\n\u0011\u0019I\u0003\u00031\u0001\u0002\u0016A\u00191\u0006L@\u0016\u0005\u0005e\u0001#\u0002\u001b8\u007f\u0006\r\u0001\u0006B\u0007fQ&\u0004"
)
public interface Factory {
   static Factory arrayFactory(final ClassTag evidence$1) {
      Factory$ var10000 = Factory$.MODULE$;
      return new ArrayFactory(evidence$1);
   }

   static Factory stringFactory() {
      return Factory$.MODULE$.stringFactory();
   }

   Object fromSpecific(final IterableOnce it);

   Builder newBuilder();

   private static class StringFactory implements Factory, Serializable {
      private static final long serialVersionUID = 3L;

      public String fromSpecific(final IterableOnce it) {
         scala.math.package$ var10002 = scala.math.package$.MODULE$;
         StringBuilder b = new StringBuilder(Math.max(0, it.knownSize()));
         Growable.addAll$(b, it);
         return b.result();
      }

      public Builder newBuilder() {
         return new StringBuilder();
      }

      public StringFactory() {
      }
   }

   private static class ArrayFactory implements Factory, Serializable {
      private static final long serialVersionUID = 3L;
      private final ClassTag evidence$2;

      public Object fromSpecific(final IterableOnce it) {
         Builder b = this.newBuilder();
         b.sizeHint(it, 0);
         b.addAll(it);
         return b.result();
      }

      public Builder newBuilder() {
         ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
         ClassTag make_evidence$1 = this.evidence$2;
         Class var2 = make_evidence$1.runtimeClass();
         Class var3 = Byte.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofByte();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofByte();
         }

         var3 = Short.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofShort();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofShort();
         }

         var3 = Character.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofChar();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofChar();
         }

         var3 = Integer.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofInt();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofInt();
         }

         var3 = Long.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofLong();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofLong();
         }

         var3 = Float.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofFloat();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofFloat();
         }

         var3 = Double.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofDouble();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofDouble();
         }

         var3 = Boolean.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofBoolean();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofBoolean();
         }

         var3 = Void.TYPE;
         if (var3 == null) {
            if (var2 == null) {
               return new ArrayBuilder.ofUnit();
            }
         } else if (var3.equals(var2)) {
            return new ArrayBuilder.ofUnit();
         }

         return new ArrayBuilder.ofRef(make_evidence$1);
      }

      public ArrayFactory(final ClassTag evidence$2) {
         this.evidence$2 = evidence$2;
      }
   }
}
