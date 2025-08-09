package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.reflect.Method;
import org.apache.spark.internal.Logging;
import scala.MatchError;
import scala.StringContext;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001B\u0006\r\tUAQa\u000e\u0001\u0005\u0002aBQa\u000f\u0001\u0005BqBQa\u0015\u0001\u0005BQ;QA\u001d\u0007\t\nM4Qa\u0003\u0007\t\nQDQaN\u0003\u0005\u0002yD\u0001b`\u0003C\u0002\u0013\u0005\u0011\u0011\u0001\u0005\t\u0003\u001f)\u0001\u0015!\u0003\u0002\u0004!I\u0011QE\u0003C\u0002\u0013%\u0011q\u0005\u0005\t\u0003w)\u0001\u0015!\u0003\u0002*\ti\"*\u0019<b\u0013R,'/\u00192mK^\u0013\u0018\r\u001d9feN+'/[1mSj,'O\u0003\u0002\u000e\u001d\u0005Q1/\u001a:jC2L'0\u001a:\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001-A\u0019qC\b\u0011\u000e\u0003aQ!!\u0007\u000e\u0002\t-\u0014\u0018p\u001c\u0006\u00037q\t\u0001#Z:pi\u0016\u0014\u0018nY:pMR<\u0018M]3\u000b\u0003u\t1aY8n\u0013\ty\u0002D\u0001\u0006TKJL\u0017\r\\5{KJ\u0004$!I\u0016\u0011\u0007\t:\u0013&D\u0001$\u0015\t!S%\u0001\u0003mC:<'\"\u0001\u0014\u0002\t)\fg/Y\u0005\u0003Q\r\u0012\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u0003U-b\u0001\u0001B\u0005-\u0001\u0005\u0005\t\u0011!B\u0001[\t!q\fJ\u00191#\tqC\u0007\u0005\u00020e5\t\u0001GC\u00012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0004GA\u0004O_RD\u0017N\\4\u0011\u0005=*\u0014B\u0001\u001c1\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003e\u0002\"A\u000f\u0001\u000e\u00031\tQa\u001e:ji\u0016$B!\u0010!E\u0019B\u0011qFP\u0005\u0003\u007fA\u0012A!\u00168ji\")\u0011D\u0001a\u0001\u0003B\u0011qCQ\u0005\u0003\u0007b\u0011Aa\u0013:z_\")QI\u0001a\u0001\r\u0006\u0019q.\u001e;\u0011\u0005\u001dSU\"\u0001%\u000b\u0005%C\u0012AA5p\u0013\tY\u0005J\u0001\u0004PkR\u0004X\u000f\u001e\u0005\u0006\u001b\n\u0001\rAT\u0001\u0004_\nT\u0007GA(R!\r\u0011s\u0005\u0015\t\u0003UE#\u0011B\u0015'\u0002\u0002\u0003\u0005)\u0011A\u0017\u0003\t}#\u0013'M\u0001\u0005e\u0016\fG\r\u0006\u0003V5n\u0003\u0007G\u0001,Y!\r\u0011se\u0016\t\u0003Ua#\u0011\"W\u0002\u0002\u0002\u0003\u0005)\u0011A\u0017\u0003\t}#\u0013g\r\u0005\u00063\r\u0001\r!\u0011\u0005\u00069\u000e\u0001\r!X\u0001\u0003S:\u0004\"a\u00120\n\u0005}C%!B%oaV$\b\"B1\u0004\u0001\u0004\u0011\u0017aA2muB\u00191M[7\u000f\u0005\u0011D\u0007CA31\u001b\u00051'BA4\u0015\u0003\u0019a$o\\8u}%\u0011\u0011\u000eM\u0001\u0007!J,G-\u001a4\n\u0005-d'!B\"mCN\u001c(BA51a\tq\u0007\u000fE\u0002#O=\u0004\"A\u000b9\u0005\u0013E\u0004\u0017\u0011!A\u0001\u0006\u0003i#\u0001B0%cI\nQDS1wC&#XM]1cY\u0016<&/\u00199qKJ\u001cVM]5bY&TXM\u001d\t\u0003u\u0015\u00192!B;y!\tyc/\u0003\u0002xa\t1\u0011I\\=SK\u001a\u0004\"!\u001f?\u000e\u0003iT!a\u001f\b\u0002\u0011%tG/\u001a:oC2L!! >\u0003\u000f1{wmZ5oOR\t1/\u0001\u0007xe\u0006\u0004\b/\u001a:DY\u0006\u001c8/\u0006\u0002\u0002\u0004A\"\u0011QAA\u0006!\u0015\u0011\u0013qAA\u0005\u0013\tY7\u0005E\u0002+\u0003\u0017!1\"!\u0004\t\u0003\u0003\u0005\tQ!\u0001\u0002\u0012\t\tA+A\u0007xe\u0006\u0004\b/\u001a:DY\u0006\u001c8\u000fI\t\u0004]\u0005M\u0001CBA\u000b\u00037\ty\"\u0004\u0002\u0002\u0018)\u0019\u0011\u0011D\u0013\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003;\t9B\u0001\u0003MSN$\bcA\u0018\u0002\"%\u0019\u00111\u0005\u0019\u0003\u0007%sG/A\nv]\u0012,'\u000f\\=j]\u001elU\r\u001e5pI>\u0003H/\u0006\u0002\u0002*A)q&a\u000b\u00020%\u0019\u0011Q\u0006\u0019\u0003\r=\u0003H/[8o!\u0011\t\t$a\u000e\u000e\u0005\u0005M\"bAA\u001bG\u00059!/\u001a4mK\u000e$\u0018\u0002BA\u001d\u0003g\u0011a!T3uQ>$\u0017\u0001F;oI\u0016\u0014H._5oO6+G\u000f[8e\u001fB$\b\u0005"
)
public class JavaIterableWrapperSerializer extends com.esotericsoftware.kryo.Serializer {
   public static Class wrapperClass() {
      return JavaIterableWrapperSerializer$.MODULE$.wrapperClass();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return JavaIterableWrapperSerializer$.MODULE$.LogStringContext(sc);
   }

   public void write(final Kryo kryo, final Output out, final Iterable obj) {
      label16: {
         Class var10000 = obj.getClass();
         Class var4 = JavaIterableWrapperSerializer$.MODULE$.wrapperClass();
         if (var10000 == null) {
            if (var4 != null) {
               break label16;
            }
         } else if (!var10000.equals(var4)) {
            break label16;
         }

         if (JavaIterableWrapperSerializer$.MODULE$.org$apache$spark$serializer$JavaIterableWrapperSerializer$$underlyingMethodOpt().isDefined()) {
            kryo.writeClassAndObject(out, ((Method)JavaIterableWrapperSerializer$.MODULE$.org$apache$spark$serializer$JavaIterableWrapperSerializer$$underlyingMethodOpt().get()).invoke(obj));
            return;
         }
      }

      kryo.writeClassAndObject(out, obj);
   }

   public Iterable read(final Kryo kryo, final Input in, final Class clz) {
      Object var5 = kryo.readClassAndObject(in);
      if (var5 instanceof scala.collection.Iterable var6) {
         return .MODULE$.IterableHasAsJava(var6).asJava();
      } else if (var5 instanceof Iterable var7) {
         return var7;
      } else {
         throw new MatchError(var5);
      }
   }
}
