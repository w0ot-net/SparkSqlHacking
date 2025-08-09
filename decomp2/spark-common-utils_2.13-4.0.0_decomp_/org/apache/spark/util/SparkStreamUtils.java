package org.apache.spark.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\r4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0005\u0005\u00063\u0001!\ta\u0007\u0005\u0006?\u0001!\t\u0001\t\u0005\bu\u0001\t\n\u0011\"\u0001<\u0011\u001d1\u0005!%A\u0005\u0002mBQa\u0012\u0001\u0005\u0002!;a\u0001\u0018\u0006\t\u00021ifAB\u0005\u000b\u0011\u0003aq\fC\u0003b\u000f\u0011\u0005!M\u0001\tTa\u0006\u00148n\u0015;sK\u0006lW\u000b^5mg*\u00111\u0002D\u0001\u0005kRLGN\u0003\u0002\u000e\u001d\u0005)1\u000f]1sW*\u0011q\u0002E\u0001\u0007CB\f7\r[3\u000b\u0003E\t1a\u001c:h'\t\u00011\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tA\u0004\u0005\u0002\u0015;%\u0011a$\u0006\u0002\u0005+:LG/\u0001\u0006d_BL8\u000b\u001e:fC6$R!\t\u0013/ga\u0002\"\u0001\u0006\u0012\n\u0005\r*\"\u0001\u0002'p]\u001eDQ!\n\u0002A\u0002\u0019\n!!\u001b8\u0011\u0005\u001dbS\"\u0001\u0015\u000b\u0005%R\u0013AA5p\u0015\u0005Y\u0013\u0001\u00026bm\u0006L!!\f\u0015\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\u0006_\t\u0001\r\u0001M\u0001\u0004_V$\bCA\u00142\u0013\t\u0011\u0004F\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000eC\u00045\u0005A\u0005\t\u0019A\u001b\u0002\u0019\rdwn]3TiJ,\u0017-\\:\u0011\u0005Q1\u0014BA\u001c\u0016\u0005\u001d\u0011un\u001c7fC:Dq!\u000f\u0002\u0011\u0002\u0003\u0007Q'A\tue\u0006t7OZ3s)>,e.\u00192mK\u0012\fAcY8qsN#(/Z1nI\u0011,g-Y;mi\u0012\u001aT#\u0001\u001f+\u0005Uj4&\u0001 \u0011\u0005}\"U\"\u0001!\u000b\u0005\u0005\u0013\u0015!C;oG\",7m[3e\u0015\t\u0019U#\u0001\u0006b]:|G/\u0019;j_:L!!\u0012!\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u000bd_BL8\u000b\u001e:fC6$C-\u001a4bk2$H\u0005N\u0001\u0012G>\u0004\u0018PR5mKN#(/Z1n\u001d&{E#\u0002\u000fJ'bS\u0006\"\u0002&\u0006\u0001\u0004Y\u0015!B5oaV$\bC\u0001'R\u001b\u0005i%B\u0001(P\u0003!\u0019\u0007.\u00198oK2\u001c(B\u0001)+\u0003\rq\u0017n\\\u0005\u0003%6\u00131BR5mK\u000eC\u0017M\u001c8fY\")A+\u0002a\u0001+\u00061q.\u001e;qkR\u0004\"\u0001\u0014,\n\u0005]k%aE,sSR\f'\r\\3CsR,7\t[1o]\u0016d\u0007\"B-\u0006\u0001\u0004\t\u0013!D:uCJ$\bk\\:ji&|g\u000eC\u0003\\\u000b\u0001\u0007\u0011%A\u0006csR,7\u000fV8D_BL\u0018\u0001E*qCJ\\7\u000b\u001e:fC6,F/\u001b7t!\tqv!D\u0001\u000b'\r91\u0003\u0019\t\u0003=\u0002\ta\u0001P5oSRtD#A/"
)
public interface SparkStreamUtils {
   // $FF: synthetic method
   static long copyStream$(final SparkStreamUtils $this, final InputStream in, final OutputStream out, final boolean closeStreams, final boolean transferToEnabled) {
      return $this.copyStream(in, out, closeStreams, transferToEnabled);
   }

   default long copyStream(final InputStream in, final OutputStream out, final boolean closeStreams, final boolean transferToEnabled) {
      return BoxesRunTime.unboxToLong(SparkErrorUtils$.MODULE$.tryWithSafeFinally((JFunction0.mcJ.sp)() -> {
         Tuple2 var6 = new Tuple2(in, out);
         if (var6 != null) {
            InputStream input = (InputStream)var6._1();
            OutputStream output = (OutputStream)var6._2();
            if (input instanceof FileInputStream) {
               FileInputStream var9 = (FileInputStream)input;
               if (output instanceof FileOutputStream) {
                  FileOutputStream var10 = (FileOutputStream)output;
                  if (transferToEnabled) {
                     FileChannel inChannel = var9.getChannel();
                     FileChannel outChannel = var10.getChannel();
                     long size = inChannel.size();
                     this.copyFileStreamNIO(inChannel, outChannel, 0L, size);
                     return size;
                  }
               }
            }
         }

         if (var6 != null) {
            InputStream input = (InputStream)var6._1();
            OutputStream output = (OutputStream)var6._2();
            long count = 0L;
            byte[] buf = new byte[8192];
            int n = 0;

            while(n != -1) {
               n = input.read(buf);
               if (n != -1) {
                  output.write(buf, 0, n);
                  count += (long)n;
               }
            }

            return count;
         } else {
            throw new MatchError(var6);
         }
      }, (JFunction0.mcV.sp)() -> {
         if (closeStreams) {
            try {
               in.close();
            } finally {
               out.close();
            }

         }
      }));
   }

   // $FF: synthetic method
   static boolean copyStream$default$3$(final SparkStreamUtils $this) {
      return $this.copyStream$default$3();
   }

   default boolean copyStream$default$3() {
      return false;
   }

   // $FF: synthetic method
   static boolean copyStream$default$4$(final SparkStreamUtils $this) {
      return $this.copyStream$default$4();
   }

   default boolean copyStream$default$4() {
      return false;
   }

   // $FF: synthetic method
   static void copyFileStreamNIO$(final SparkStreamUtils $this, final FileChannel input, final WritableByteChannel output, final long startPosition, final long bytesToCopy) {
      $this.copyFileStreamNIO(input, output, startPosition, bytesToCopy);
   }

   default void copyFileStreamNIO(final FileChannel input, final WritableByteChannel output, final long startPosition, final long bytesToCopy) {
      Object var10000;
      if (output instanceof FileChannel var10) {
         var10000 = new Some(new Tuple2(BoxesRunTime.boxToLong(var10.position()), var10));
      } else {
         var10000 = .MODULE$;
      }

      Option outputInitialState = (Option)var10000;

      LongRef count;
      for(count = LongRef.create(0L); count.elem < bytesToCopy; count.elem += input.transferTo(count.elem + startPosition, bytesToCopy - count.elem, output)) {
      }

      scala.Predef..MODULE$.assert(count.elem == bytesToCopy, () -> "request to copy " + bytesToCopy + " bytes, but actually copied " + count.elem + " bytes.");
      outputInitialState.foreach((x0$1) -> {
         $anonfun$copyFileStreamNIO$2(bytesToCopy, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static void $anonfun$copyFileStreamNIO$2(final long bytesToCopy$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long initialPos = x0$1._1$mcJ$sp();
         FileChannel outputFileChannel = (FileChannel)x0$1._2();
         long finalPos = outputFileChannel.position();
         long expectedPos = initialPos + bytesToCopy$1;
         scala.Predef..MODULE$.assert(finalPos == expectedPos, () -> scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n           |Current position " + finalPos + " do not equal to expected position " + expectedPos + "\n           |after transferTo, please check your kernel version to see if it is 2.6.32,\n           |this is a kernel bug which will lead to unexpected behavior when using transferTo.\n           |You can set spark.file.transferTo = false to disable this NIO feature.\n         ")));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   static void $init$(final SparkStreamUtils $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
