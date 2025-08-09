package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.ReferenceResolver;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mx!B\u0012%\u0011\u0003Yc!B\u0017%\u0011\u0003q\u0003\"B\u001f\u0002\t\u0003q\u0004bB \u0002\u0005\u0004%I\u0001\u0011\u0005\u0007\t\u0006\u0001\u000b\u0011B!\t\u000f\u0015\u000b!\u0019!C\u0005\u0001\"1a)\u0001Q\u0001\n\u0005CQaR\u0001\u0005\u0002!C\u0011\"a<\u0002\u0003\u0003%I!!=\u0007\t5\"\u0003a\u0013\u0005\u0006{%!\tA\u0018\u0005\bW&\u0001\r\u0011\"\u0003m\u0011\u001da\u0018\u00021A\u0005\nuDq!a\u0002\nA\u0003&Q\u000eC\u0005\u0002\n%\u0011\r\u0011\"\u0003\u0002\f!A\u0011\u0011F\u0005!\u0002\u0013\ti\u0001C\u0005\u00024%\u0011\r\u0011\"\u0003\u00026!A\u0011QH\u0005!\u0002\u0013\t9\u0004C\u0004\u0002B%!\t!a\u0011\t\u000f\u0005\u0015\u0013\u0002\"\u0001\u0002H!9\u0011\u0011J\u0005\u0005\u0002\u0005-\u0003BB-\n\t#\t\t\u0006C\u0004\u0002Z%!\t!a\u0017\t\u000f\u0005u\u0013\u0002\"\u0003\u0002\\!9\u0011qL\u0005\u0005\n\u0005\u0005\u0004bBA:\u0013\u0011%\u0011Q\u000f\u0005\b\u0003{JA\u0011IA@\u0011\u001d\tY)\u0003C\u0005\u0003\u001bCq!a%\n\t#\t)\nC\u0004\u0002\"&!\t\"a)\t\u000f\u0005\u0005\u0016\u0002\"\u0005\u0002(\"9\u0011QV\u0005\u0005\n\u0005=\u0006bBA[\u0013\u0011\u0005\u0013q\u0017\u0005\b\u0003wKA\u0011AA_\u0011\u001d\t9.\u0003C\u0001\u00033\fA\"\u0012=uKJt\u0017\r\\5{KJT!!\n\u0014\u0002\u000b\rD\u0017\u000e\u001c7\u000b\u0005\u001dB\u0013a\u0002;xSR$XM\u001d\u0006\u0002S\u0005\u00191m\\7\u0004\u0001A\u0011A&A\u0007\u0002I\taQ\t\u001f;fe:\fG.\u001b>feN\u0019\u0011aL\u001b\u0011\u0005A\u001aT\"A\u0019\u000b\u0003I\nQa]2bY\u0006L!\u0001N\u0019\u0003\r\u0005s\u0017PU3g!\t14(D\u00018\u0015\tA\u0014(\u0001\u0002j_*\t!(\u0001\u0003kCZ\f\u0017B\u0001\u001f8\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t1&\u0001\u0003L%f{U#A!\u0011\u0005A\u0012\u0015BA\"2\u0005\rIe\u000e^\u0001\u0006\u0017JKv\nI\u0001\u0005\u0015\u00063\u0016)A\u0003K\u0003Z\u000b\u0005%A\u0003baBd\u00170F\u0002J\u0003S$2ASAv!\u0011a\u0013\"a:\u0016\u00051\u00137\u0003B\u0005N'Z\u0003\"AT)\u000e\u0003=S!\u0001U\u001d\u0002\t1\fgnZ\u0005\u0003%>\u0013aa\u00142kK\u000e$\bC\u0001\u001cU\u0013\t)vG\u0001\bFqR,'O\\1mSj\f'\r\\3\u0011\u0005]cV\"\u0001-\u000b\u0005eS\u0016\u0001B6ss>T!a\u0017\u0015\u0002!\u0015\u001cx\u000e^3sS\u000e\u001cxN\u001a;xCJ,\u0017BA/Y\u0005AY%/_8TKJL\u0017\r\\5{C\ndW\rF\u0001`!\ra\u0013\u0002\u0019\t\u0003C\nd\u0001\u0001B\u0003d\u0013\t\u0007AMA\u0001U#\t)\u0007\u000e\u0005\u00021M&\u0011q-\r\u0002\b\u001d>$\b.\u001b8h!\t\u0001\u0014.\u0003\u0002kc\t\u0019\u0011I\\=\u0002\t%$X-\\\u000b\u0002[B!aN^0z\u001d\tyGO\u0004\u0002qg6\t\u0011O\u0003\u0002sU\u00051AH]8pizJ\u0011AM\u0005\u0003kF\nq\u0001]1dW\u0006<W-\u0003\u0002xq\n1Q)\u001b;iKJT!!^\u0019\u0011\u0007AR\b-\u0003\u0002|c\t1q\n\u001d;j_:\f\u0001\"\u001b;f[~#S-\u001d\u000b\u0004}\u0006\r\u0001C\u0001\u0019\u0000\u0013\r\t\t!\r\u0002\u0005+:LG\u000f\u0003\u0005\u0002\u00061\t\t\u00111\u0001n\u0003\rAH%M\u0001\u0006SR,W\u000eI\u0001\rI>,7OS1wC^{'o[\u000b\u0003\u0003\u001b\u0001b!a\u0004\u0002\u001e\u0005\u0005RBAA\t\u0015\u0011\t\u0019\"!\u0006\u0002\r\u0005$x.\\5d\u0015\u0011\t9\"!\u0007\u0002\u0015\r|gnY;se\u0016tGOC\u0002\u0002\u001ce\nA!\u001e;jY&!\u0011qDA\t\u0005=\tEo\\7jGJ+g-\u001a:f]\u000e,\u0007\u0003\u0002\u0019{\u0003G\u00012\u0001MA\u0013\u0013\r\t9#\r\u0002\b\u0005>|G.Z1o\u00035!w.Z:KCZ\fwk\u001c:lA!\u001aq\"!\f\u0011\u0007A\ny#C\u0002\u00022E\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u000fQ,7\u000f^5oOV\u0011\u0011q\u0007\t\u0005\u0003\u001f\tI$\u0003\u0003\u0002<\u0005E!!D!u_6L7MQ8pY\u0016\fg.\u0001\u0005uKN$\u0018N\\4!Q\r\t\u0012QF\u0001\nO\u0016$x\n\u001d;j_:,\u0012!_\u0001\u0004O\u0016$X#\u00011\u0002\u0007M,G\u000fF\u0002\u007f\u0003\u001bBa!a\u0014\u0015\u0001\u0004\u0001\u0017AA5u+\t\t\u0019\u0006E\u0002-\u0003+J1!a\u0016%\u0005AY%/_8J]N$\u0018M\u001c;jCR|'/A\u0005kCZ\fwk\u001c:lgV\u0011\u00111E\u0001\u000faJ|'-\u001a&bm\u0006<vN]6t\u0003-\u0019\u0018MZ3U_\nKH/Z:\u0015\t\u0005\r\u0014\u0011\u000f\t\u0005ai\f)\u0007E\u00031\u0003O\nY'C\u0002\u0002jE\u0012Q!\u0011:sCf\u00042\u0001MA7\u0013\r\ty'\r\u0002\u0005\u0005f$X\r\u0003\u0004Z1\u0001\u0007\u00111K\u0001\nMJ|WNQ=uKN$R!_A<\u0003wBq!!\u001f\u001a\u0001\u0004\t)'A\u0001c\u0011\u0019I\u0016\u00041\u0001\u0002T\u0005a!/Z1e\u000bb$XM\u001d8bYR\u0019a0!!\t\u000f\u0005\r%\u00041\u0001\u0002\u0006\u0006\u0011\u0011N\u001c\t\u0004m\u0005\u001d\u0015bAAEo\tYqJ\u00196fGRLe\u000e];u\u0003Ei\u0017-\u001f2f%\u0016\fGMS1wC.\u0013\u0018p\u001c\u000b\u0006}\u0006=\u0015\u0011\u0013\u0005\b\u0003\u0007[\u0002\u0019AAC\u0011\u0019I6\u00041\u0001\u0002T\u0005IqO]5uK*\u000bg/\u0019\u000b\u0005\u0003G\t9\nC\u0004\u0002\u001ar\u0001\r!a'\u0002\u0007=,H\u000fE\u00027\u0003;K1!a(8\u00051y%M[3di>+H\u000f];u\u0003%9(/\u001b;f\u0017JLx\u000e\u0006\u0003\u0002$\u0005\u0015\u0006bBAM;\u0001\u0007\u00111\u0014\u000b\u0007\u0003G\tI+a+\t\u000f\u0005ee\u00041\u0001\u0002\u001c\"1\u0011L\ba\u0001\u0003'\n!#\\1zE\u0016<&/\u001b;f\u0015\u00064\u0018m\u0013:z_R)a0!-\u00024\"9\u0011\u0011T\u0010A\u0002\u0005m\u0005BB- \u0001\u0004\t\u0019&A\u0007xe&$X-\u0012=uKJt\u0017\r\u001c\u000b\u0004}\u0006e\u0006bBAMA\u0001\u0007\u00111T\u0001\u0006oJLG/\u001a\u000b\u0006}\u0006}\u0016Q\u001a\u0005\u00073\u0006\u0002\r!!1\u0011\t\u0005\r\u0017q\u0019\b\u0004Y\u0005\u0015\u0017BA;%\u0013\u0011\tI-a3\u0003\t-\u0013\u0018p\u001c\u0006\u0003k\u0012Bq!a4\"\u0001\u0004\t\t.\u0001\u0004pkR\u0004X\u000f\u001e\t\u0005\u0003\u0007\f\u0019.\u0003\u0003\u0002V\u0006-'AB(viB,H/\u0001\u0003sK\u0006$G#\u0002@\u0002\\\u0006u\u0007BB-#\u0001\u0004\t\t\rC\u0004\u0002`\n\u0002\r!!9\u0002\u000b%t\u0007/\u001e;\u0011\t\u0005\r\u00171]\u0005\u0005\u0003K\fYMA\u0003J]B,H\u000fE\u0002b\u0003S$QaY\u0004C\u0002\u0011Dq!!<\b\u0001\u0004\t9/A\u0001u\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005i\u0005"
)
public class Externalizer implements Externalizable, KryoSerializable {
   private Either item;
   private final transient AtomicReference doesJavaWork;
   private final transient AtomicBoolean testing;

   public static Externalizer apply(final Object t) {
      return Externalizer$.MODULE$.apply(t);
   }

   private Either item() {
      return this.item;
   }

   private void item_$eq(final Either x$1) {
      this.item = x$1;
   }

   private AtomicReference doesJavaWork() {
      return this.doesJavaWork;
   }

   private AtomicBoolean testing() {
      return this.testing;
   }

   public Option getOption() {
      Either var2 = this.item();
      Option var1;
      if (var2 instanceof Left) {
         Left var3 = (Left)var2;
         Externalizer e = (Externalizer)var3.value();
         var1 = e.getOption();
      } else {
         if (!(var2 instanceof Right)) {
            throw new MatchError(var2);
         }

         Right var5 = (Right)var2;
         Option i = (Option)var5.value();
         var1 = i;
      }

      return var1;
   }

   public Object get() {
      return this.getOption().get();
   }

   public void set(final Object it) {
      Either var3 = this.item();
      if (var3 instanceof Left) {
         Left var4 = (Left)var3;
         Externalizer e = (Externalizer)var4.value();
         e.set(it);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         if (!(var3 instanceof Right)) {
            throw new MatchError(var3);
         }

         Right var6 = (Right)var3;
         Option x = (Option)var6.value();
         .MODULE$.assert(x.isEmpty(), () -> "Tried to call .set on an already constructed Externalizer");
         this.item_$eq(scala.package..MODULE$.Right().apply(new Some(it)));
         BoxedUnit var8 = BoxedUnit.UNIT;
      }

   }

   public KryoInstantiator kryo() {
      return (new ScalaKryoInstantiator()).setReferences(true);
   }

   public boolean javaWorks() {
      Option var2 = (Option)this.doesJavaWork().get();
      boolean var1;
      if (var2 instanceof Some) {
         Some var3 = (Some)var2;
         boolean v = BoxesRunTime.unboxToBoolean(var3.value());
         var1 = v;
      } else {
         if (!scala.None..MODULE$.equals(var2)) {
            throw new MatchError(var2);
         }

         var1 = this.probeJavaWorks();
      }

      return var1;
   }

   private boolean probeJavaWorks() {
      if (!this.testing().compareAndSet(false, true)) {
         return true;
      } else {
         boolean var10000;
         try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.getOption());
            byte[] bytes = baos.toByteArray();
            ByteArrayInputStream testInput = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(testInput);
            ois.readObject();
            this.doesJavaWork().set(new Some(BoxesRunTime.boxToBoolean(true)));
            var10000 = true;
         } catch (Throwable var10) {
            scala.Option..MODULE$.apply(System.getenv().get("CHILL_EXTERNALIZER_DEBUG")).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$probeJavaWorks$1(x$1))).foreach((x$2) -> {
               $anonfun$probeJavaWorks$2(var10, x$2);
               return BoxedUnit.UNIT;
            });
            this.doesJavaWork().set(new Some(BoxesRunTime.boxToBoolean(false)));
            var10000 = false;
         } finally {
            this.testing().set(false);
         }

         return var10000;
      }
   }

   private Option safeToBytes(final KryoInstantiator kryo) {
      Object var10000;
      try {
         KryoPool kpool = KryoPool.withByteArrayOutputStream(1, kryo);
         byte[] bytes = kpool.toBytesWithClass(this.getOption());
         var10000 = new Some(bytes);
      } catch (Throwable var5) {
         scala.Option..MODULE$.apply(System.getenv().get("CHILL_EXTERNALIZER_DEBUG")).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$safeToBytes$1(x$3))).foreach((x$4) -> {
            $anonfun$safeToBytes$2(var5, x$4);
            return BoxedUnit.UNIT;
         });
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   private Option fromBytes(final byte[] b, final KryoInstantiator kryo) {
      return (Option)KryoPool.withByteArrayOutputStream(1, kryo).fromBytes(b);
   }

   public void readExternal(final ObjectInput in) {
      this.maybeReadJavaKryo(in, this.kryo());
   }

   private void maybeReadJavaKryo(final ObjectInput in, final KryoInstantiator kryo) {
      int var4 = in.read();
      if (Externalizer$.MODULE$.com$twitter$chill$Externalizer$$JAVA() == var4) {
         this.item_$eq(scala.package..MODULE$.Right().apply((Option)in.readObject()));
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else {
         if (Externalizer$.MODULE$.com$twitter$chill$Externalizer$$KRYO() != var4) {
            throw new MatchError(BoxesRunTime.boxToInteger(var4));
         }

         int sz = in.readInt();
         byte[] buf = new byte[sz];
         in.readFully(buf);
         this.item_$eq(scala.package..MODULE$.Right().apply(this.fromBytes(buf, kryo)));
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

   }

   public boolean writeJava(final ObjectOutput out) {
      boolean var10000;
      if (this.javaWorks()) {
         out.write(Externalizer$.MODULE$.com$twitter$chill$Externalizer$$JAVA());
         out.writeObject(this.getOption());
         if (true) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   public boolean writeKryo(final ObjectOutput out) {
      return this.writeKryo(out, this.kryo());
   }

   public boolean writeKryo(final ObjectOutput out, final KryoInstantiator kryo) {
      return BoxesRunTime.unboxToBoolean(this.safeToBytes(kryo).map((bytes) -> BoxesRunTime.boxToBoolean($anonfun$writeKryo$1(out, bytes))).getOrElse((JFunction0.mcZ.sp)() -> false));
   }

   private void maybeWriteJavaKryo(final ObjectOutput out, final KryoInstantiator kryo) {
      if (!this.writeJava(out) && !this.writeKryo(out, kryo)) {
         Object inner = this.get();
         throw scala.sys.package..MODULE$.error(scala.collection.StringOps..MODULE$.format$extension(.MODULE$.augmentString("Neither Java nor Kryo works for class: %s instance: %s\nexport CHILL_EXTERNALIZER_DEBUG=true to see both stack traces"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{inner.getClass(), inner})));
      } else {
         boolean var10000 = true;
      }
   }

   public void writeExternal(final ObjectOutput out) {
      this.maybeWriteJavaKryo(out, this.kryo());
   }

   public void write(final Kryo kryo, final Output output) {
      ReferenceResolver resolver = kryo.getReferenceResolver();
      int var4 = resolver.getWrittenId(this.item());
      switch (var4) {
         case -1:
            output.writeInt(-1);
            resolver.addWrittenObject(this.item());
            ObjectOutputStream oStream = new ObjectOutputStream(output);
            this.maybeWriteJavaKryo(oStream, package$.MODULE$.toInstantiator(() -> kryo));
            oStream.flush();
            break;
         default:
            output.writeInt(var4);
      }

   }

   public void read(final Kryo kryo, final Input input) {
      this.doesJavaWork().set(scala.None..MODULE$);
      this.testing().set(false);
      int state = input.readInt();
      ReferenceResolver resolver = kryo.getReferenceResolver();
      switch (state) {
         case -1:
            int objId = resolver.nextReadId(this.getClass());
            resolver.setReadObject(objId, this);
            this.maybeReadJavaKryo(new ObjectInputStream(input), package$.MODULE$.toInstantiator(() -> kryo));
            break;
         default:
            Externalizer z = (Externalizer)resolver.getReadObject(this.getClass(), state);
            if (z != this) {
               this.item_$eq(scala.package..MODULE$.Left().apply(z));
            }
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$probeJavaWorks$1(final String x$1) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(.MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final void $anonfun$probeJavaWorks$2(final Throwable t$1, final String x$2) {
      t$1.printStackTrace();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$safeToBytes$1(final String x$3) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(.MODULE$.augmentString(x$3));
   }

   // $FF: synthetic method
   public static final void $anonfun$safeToBytes$2(final Throwable t$2, final String x$4) {
      t$2.printStackTrace();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$writeKryo$1(final ObjectOutput out$1, final byte[] bytes) {
      out$1.write(Externalizer$.MODULE$.com$twitter$chill$Externalizer$$KRYO());
      out$1.writeInt(scala.collection.ArrayOps..MODULE$.size$extension(.MODULE$.byteArrayOps(bytes)));
      out$1.write(bytes);
      return true;
   }

   public Externalizer() {
      this.item = scala.package..MODULE$.Right().apply(scala.None..MODULE$);
      this.doesJavaWork = new AtomicReference(scala.None..MODULE$);
      this.testing = new AtomicBoolean(false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
