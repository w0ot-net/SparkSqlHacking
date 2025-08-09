package org.apache.spark.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import org.apache.spark.internal.LogEntry$;
import org.apache.spark.internal.LogKeys;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ua\u0001\u0003\u0006\f!\u0003\r\t!D\n\t\u000b\u0001\u0002A\u0011\u0001\u0012\t\u000b\u0019\u0002A\u0011A\u0014\t\u000be\u0002A\u0011\u0001\u001e\t\u000bM\u0003A\u0011\u0001+\t\u000b\u0001\u0004A\u0011A1\t\u000b-\u0004A\u0011\u00017\b\u0011\u0005\u001d1\u0002#\u0001\u000e\u0003\u00131qAC\u0006\t\u00025\ti\u0001C\u0004\u0002\u0012!!\t!a\u0005\u0003\u001fM\u0003\u0018M]6FeJ|'/\u0016;jYNT!\u0001D\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sON\u0019\u0001\u0001\u0006\u000e\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g!\tYb$D\u0001\u001d\u0015\tiR\"\u0001\u0005j]R,'O\\1m\u0013\tyBDA\u0004M_\u001e<\u0017N\\4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012a\t\t\u0003+\u0011J!!\n\f\u0003\tUs\u0017\u000e^\u0001\u0011iJLxJ]%P\u000bb\u001cW\r\u001d;j_:,\"\u0001K\u0016\u0015\u0005%\"\u0004C\u0001\u0016,\u0019\u0001!Q\u0001\f\u0002C\u00025\u0012\u0011\u0001V\t\u0003]E\u0002\"!F\u0018\n\u0005A2\"a\u0002(pi\"Lgn\u001a\t\u0003+IJ!a\r\f\u0003\u0007\u0005s\u0017\u0010\u0003\u00046\u0005\u0011\u0005\rAN\u0001\u0006E2|7m\u001b\t\u0004+]J\u0013B\u0001\u001d\u0017\u0005!a$-\u001f8b[\u0016t\u0014a\u0004;ss^KG\u000f\u001b*fg>,(oY3\u0016\u0007m*e\b\u0006\u0002=!R\u0011Qh\u0010\t\u0003Uy\"Q\u0001L\u0002C\u00025BQ\u0001Q\u0002A\u0002\u0005\u000b\u0011A\u001a\t\u0005+\t#U(\u0003\u0002D-\tIa)\u001e8di&|g.\r\t\u0003U\u0015#QAR\u0002C\u0002\u001d\u0013\u0011AU\t\u0003]!\u0003\"!\u0013(\u000e\u0003)S!a\u0013'\u0002\u0005%|'\"A'\u0002\t)\fg/Y\u0005\u0003\u001f*\u0013\u0011b\u00117pg\u0016\f'\r\\3\t\rE\u001bA\u00111\u0001S\u00039\u0019'/Z1uKJ+7o\\;sG\u0016\u00042!F\u001cE\u0003U!(/_%oSRL\u0017\r\\5{KJ+7o\\;sG\u0016,2!V/Y)\t1f\f\u0006\u0002X3B\u0011!\u0006\u0017\u0003\u0006Y\u0011\u0011\r!\f\u0005\u00065\u0012\u0001\raW\u0001\u000bS:LG/[1mSj,\u0007\u0003B\u000bC9^\u0003\"AK/\u0005\u000b\u0019#!\u0019A$\t\rE#A\u00111\u0001`!\r)r\u0007X\u0001\u0013iJLx+\u001b;i'\u00064WMR5oC2d\u00170\u0006\u0002cKR\u00111-\u001b\u000b\u0003I\u001a\u0004\"AK3\u0005\u000b1*!\u0019A\u0017\t\r\u001d,A\u00111\u0001i\u000311\u0017N\\1mYf\u0014En\\2l!\r)rg\t\u0005\u0007k\u0015!\t\u0019\u00016\u0011\u0007U9D-\u0001\nti\u0006\u001c7\u000e\u0016:bG\u0016$vn\u0015;sS:<GCA7y!\tqWO\u0004\u0002pgB\u0011\u0001OF\u0007\u0002c*\u0011!/I\u0001\u0007yI|w\u000e\u001e \n\u0005Q4\u0012A\u0002)sK\u0012,g-\u0003\u0002wo\n11\u000b\u001e:j]\u001eT!\u0001\u001e\f\t\u000be4\u0001\u0019\u0001>\u0002\u0003Q\u00042a_A\u0001\u001d\tahP\u0004\u0002q{&\tq#\u0003\u0002\u0000-\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0002\u0003\u000b\u0011\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0005}4\u0012aD*qCJ\\WI\u001d:peV#\u0018\u000e\\:\u0011\u0007\u0005-\u0001\"D\u0001\f'\u0011AA#a\u0004\u0011\u0007\u0005-\u0001!\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\u0013\u0001"
)
public interface SparkErrorUtils extends Logging {
   // $FF: synthetic method
   static Object tryOrIOException$(final SparkErrorUtils $this, final Function0 block) {
      return $this.tryOrIOException(block);
   }

   default Object tryOrIOException(final Function0 block) {
      try {
         return block.apply();
      } catch (Throwable var7) {
         if (var7 instanceof IOException var5) {
            this.logError(() -> "Exception encountered", var5);
            throw var5;
         } else if (var7 != null && .MODULE$.apply(var7)) {
            this.logError(() -> "Exception encountered", var7);
            throw new IOException(var7);
         } else {
            throw var7;
         }
      }
   }

   // $FF: synthetic method
   static Object tryWithResource$(final SparkErrorUtils $this, final Function0 createResource, final Function1 f) {
      return $this.tryWithResource(createResource, f);
   }

   default Object tryWithResource(final Function0 createResource, final Function1 f) {
      Closeable resource = (Closeable)createResource.apply();

      Object var10000;
      try {
         var10000 = f.apply(resource);
      } finally {
         resource.close();
      }

      return var10000;
   }

   // $FF: synthetic method
   static Object tryInitializeResource$(final SparkErrorUtils $this, final Function0 createResource, final Function1 initialize) {
      return $this.tryInitializeResource(createResource, initialize);
   }

   default Object tryInitializeResource(final Function0 createResource, final Function1 initialize) {
      Closeable resource = (Closeable)createResource.apply();

      try {
         return initialize.apply(resource);
      } catch (Throwable var5) {
         resource.close();
         throw var5;
      }
   }

   // $FF: synthetic method
   static Object tryWithSafeFinally$(final SparkErrorUtils $this, final Function0 block, final Function0 finallyBlock) {
      return $this.tryWithSafeFinally(block, finallyBlock);
   }

   default Object tryWithSafeFinally(final Function0 block, final Function0 finallyBlock) {
      Throwable originalThrowable = null;
      boolean var17 = false;

      Object var10000;
      try {
         var17 = true;
         var10000 = block.apply();
         var17 = false;
      } catch (Throwable var18) {
         originalThrowable = var18;
         throw var18;
      } finally {
         if (var17) {
            try {
               finallyBlock.apply$mcV$sp();
            } catch (Throwable var20) {
               label113: {
                  if (var20 != null && originalThrowable != null) {
                     if (originalThrowable == null) {
                        if (var20 != null) {
                           break label113;
                        }
                     } else if (!originalThrowable.equals(var20)) {
                        break label113;
                     }
                  }

                  throw var20;
               }

               originalThrowable.addSuppressed(var20);
               this.logWarning(LogEntry$.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Suppressing exception in finally: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MDC[]{new MDC(LogKeys.MESSAGE$.MODULE$, var19.getMessage())}))), var20);
               throw originalThrowable;
            }
         }
      }

      Object var5 = var10000;

      try {
         finallyBlock.apply$mcV$sp();
         return var5;
      } catch (Throwable var19) {
         label102: {
            if (var19 != null && originalThrowable != null) {
               if (originalThrowable == null) {
                  if (var19 != null) {
                     break label102;
                  }
               } else if (!originalThrowable.equals(var19)) {
                  break label102;
               }
            }

            throw var19;
         }

         originalThrowable.addSuppressed(var19);
         this.logWarning(LogEntry$.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Suppressing exception in finally: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MDC[]{new MDC(LogKeys.MESSAGE$.MODULE$, var19.getMessage())}))), var19);
         throw originalThrowable;
      }
   }

   // $FF: synthetic method
   static String stackTraceToString$(final SparkErrorUtils $this, final Throwable t) {
      return $this.stackTraceToString(t);
   }

   default String stackTraceToString(final Throwable t) {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      SparkErrorUtils$.MODULE$.tryWithResource(() -> new PrintWriter(out), (writer) -> {
         $anonfun$stackTraceToString$2(t, writer);
         return BoxedUnit.UNIT;
      });
      return new String(out.toByteArray(), StandardCharsets.UTF_8);
   }

   // $FF: synthetic method
   static void $anonfun$stackTraceToString$2(final Throwable t$1, final PrintWriter writer) {
      t$1.printStackTrace(writer);
      writer.flush();
   }

   static void $init$(final SparkErrorUtils $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
