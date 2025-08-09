package cats.kernel.compat;

import scala.Function2;
import scala.Option;
import scala.annotation.Annotation;
import scala.annotation.StaticAnnotation;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055rAB\n\u0015\u0011\u0003A\"D\u0002\u0004\u001d)!\u0005\u0001$\b\u0005\u0006I\u0005!\tA\n\u0004\u0005O\u0005\u0001\u0001\u0006C\u0003%\u0007\u0011\u0005!G\u0002\u00036\u0003\r1\u0004\u0002D\u001e\u0006\t\u0003\u0005)Q!b\u0001\n\u0013a\u0004\"\u0003+\u0006\u0005\u000b\u0005\t\u0015!\u0003>\u0011\u0015!S\u0001\"\u0001V\u0011\u0015IV\u0001\"\u0001[\u0011\u001d\u0019W!!A\u0005B\u0011Dq\u0001[\u0003\u0002\u0002\u0013\u0005\u0013nB\u0004p\u0003\u0005\u0005\t\u0012\u00019\u0007\u000fU\n\u0011\u0011!E\u0001c\")A%\u0004C\u0001e\")1/\u0004C\u0003i\"Aq0DA\u0001\n\u000b\t\t\u0001C\u0005\u0002\u000e5\t\t\u0011\"\u0002\u0002\u0010!Aq.AA\u0001\n\u0007\ty\"\u0001\u000btG\u0006d\u0017MV3sg&|gn\u00159fG&4\u0017n\u0019\u0006\u0003+Y\taaY8na\u0006$(BA\f\u0019\u0003\u0019YWM\u001d8fY*\t\u0011$\u0001\u0003dCR\u001c\bCA\u000e\u0002\u001b\u0005!\"\u0001F:dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7m\u0005\u0002\u0002=A\u0011qDI\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u00025\t\u00114/\u001e9qe\u0016\u001c8/\u00168vg\u0016$\u0017*\u001c9peR<\u0016M\u001d8j]\u001e4uN]*dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7mE\u0002\u0004S=\u0002\"AK\u0017\u000e\u0003-R!\u0001\f\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002/W\tQ\u0011I\u001c8pi\u0006$\u0018n\u001c8\u0011\u0005)\u0002\u0014BA\u0019,\u0005A\u0019F/\u0019;jG\u0006sgn\u001c;bi&|g\u000eF\u00014!\t!4!D\u0001\u0002\u0005UIG/\u001a:bE2,wJ\\2f\u000bb$XM\\:j_:,\"aN&\u0014\u0005\u0015A\u0004CA\u0010:\u0013\tQ\u0004E\u0001\u0004B]f4\u0016\r\\\u0001BG\u0006$8\u000fJ6fe:,G\u000eJ2p[B\fG\u000fJ:dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7\rJ5uKJ\f'\r\\3P]\u000e,W\t\u001f;f]NLwN\u001c\u0013%S>,\u0012!\u0010\t\u0004}\u0019KeBA E\u001d\t\u00015)D\u0001B\u0015\t\u0011U%\u0001\u0004=e>|GOP\u0005\u0002C%\u0011Q\tI\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0005J\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cWM\u0003\u0002FAA\u0011!j\u0013\u0007\u0001\t\u0015aUA1\u0001N\u0005\u0005\t\u0015C\u0001(R!\tyr*\u0003\u0002QA\t9aj\u001c;iS:<\u0007CA\u0010S\u0013\t\u0019\u0006EA\u0002B]f\f!iY1ug\u0012ZWM\u001d8fY\u0012\u001aw.\u001c9bi\u0012\u001a8-\u00197b-\u0016\u00148/[8o'B,7-\u001b4jG\u0012JG/\u001a:bE2,wJ\\2f\u000bb$XM\\:j_:$C%[8!)\t1v\u000bE\u00025\u000b%CQ\u0001\u0017\u0005A\u0002u\n!![8\u0002\u0019I,G-^2f\u001fB$\u0018n\u001c8\u0015\u0005ms\u0006cA\u0010]\u0013&\u0011Q\f\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u000b}K\u0001\u0019\u00011\u0002\u0003\u0019\u0004RaH1J\u0013&K!A\u0019\u0011\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0015\u0004\"a\b4\n\u0005\u001d\u0004#aA%oi\u00061Q-];bYN$\"A[7\u0011\u0005}Y\u0017B\u00017!\u0005\u001d\u0011un\u001c7fC:DqA\\\u0006\u0002\u0002\u0003\u0007\u0011+A\u0002yIE\nQ#\u001b;fe\u0006\u0014G.Z(oG\u0016,\u0005\u0010^3og&|g\u000e\u0005\u00025\u001bM\u0011QB\b\u000b\u0002a\u00061\"/\u001a3vG\u0016|\u0005\u000f^5p]\u0012*\u0007\u0010^3og&|g.\u0006\u0002vsR\u0011a\u000f \u000b\u0003oj\u00042a\b/y!\tQ\u0015\u0010B\u0003M\u001f\t\u0007Q\nC\u0003`\u001f\u0001\u00071\u0010E\u0003 CbD\b\u0010C\u0003~\u001f\u0001\u0007a0A\u0003%i\"L7\u000fE\u00025\u000ba\f!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]V!\u00111AA\u0006)\r!\u0017Q\u0001\u0005\u0007{B\u0001\r!a\u0002\u0011\tQ*\u0011\u0011\u0002\t\u0004\u0015\u0006-A!\u0002'\u0011\u0005\u0004i\u0015\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o+\u0011\t\t\"!\b\u0015\t\u0005M\u0011q\u0003\u000b\u0004U\u0006U\u0001b\u00028\u0012\u0003\u0003\u0005\r!\u0015\u0005\u0007{F\u0001\r!!\u0007\u0011\tQ*\u00111\u0004\t\u0004\u0015\u0006uA!\u0002'\u0012\u0005\u0004iU\u0003BA\u0011\u0003O!B!a\t\u0002*A!A'BA\u0013!\rQ\u0015q\u0005\u0003\u0006\u0019J\u0011\r!\u0014\u0005\u00071J\u0001\r!a\u000b\u0011\ty2\u0015Q\u0005"
)
public final class scalaVersionSpecific {
   public static IterableOnce iterableOnceExtension(final IterableOnce io) {
      return scalaVersionSpecific$.MODULE$.iterableOnceExtension(io);
   }

   public static class suppressUnusedImportWarningForScalaVersionSpecific extends Annotation implements StaticAnnotation {
   }

   public static final class iterableOnceExtension {
      private final IterableOnce cats$kernel$compat$scalaVersionSpecific$iterableOnceExtension$$io;

      public IterableOnce cats$kernel$compat$scalaVersionSpecific$iterableOnceExtension$$io() {
         return this.cats$kernel$compat$scalaVersionSpecific$iterableOnceExtension$$io;
      }

      public Option reduceOption(final Function2 f) {
         return scalaVersionSpecific.iterableOnceExtension$.MODULE$.reduceOption$extension(this.cats$kernel$compat$scalaVersionSpecific$iterableOnceExtension$$io(), f);
      }

      public int hashCode() {
         return scalaVersionSpecific.iterableOnceExtension$.MODULE$.hashCode$extension(this.cats$kernel$compat$scalaVersionSpecific$iterableOnceExtension$$io());
      }

      public boolean equals(final Object x$1) {
         return scalaVersionSpecific.iterableOnceExtension$.MODULE$.equals$extension(this.cats$kernel$compat$scalaVersionSpecific$iterableOnceExtension$$io(), x$1);
      }

      public iterableOnceExtension(final IterableOnce io) {
         this.cats$kernel$compat$scalaVersionSpecific$iterableOnceExtension$$io = io;
      }
   }

   public static class iterableOnceExtension$ {
      public static final iterableOnceExtension$ MODULE$ = new iterableOnceExtension$();

      public final Option reduceOption$extension(final IterableOnce $this, final Function2 f) {
         return $this.iterator().reduceOption(f);
      }

      public final int hashCode$extension(final IterableOnce $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final IterableOnce $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof iterableOnceExtension) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var7;
         if (var3) {
            label32: {
               label31: {
                  IterableOnce var5 = x$1 == null ? null : ((iterableOnceExtension)x$1).cats$kernel$compat$scalaVersionSpecific$iterableOnceExtension$$io();
                  if ($this == null) {
                     if (var5 == null) {
                        break label31;
                     }
                  } else if ($this.equals(var5)) {
                     break label31;
                  }

                  var7 = false;
                  break label32;
               }

               var7 = true;
            }

            if (var7) {
               var7 = true;
               return var7;
            }
         }

         var7 = false;
         return var7;
      }
   }
}
