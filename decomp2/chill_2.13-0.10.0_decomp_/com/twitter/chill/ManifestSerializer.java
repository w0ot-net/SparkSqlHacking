package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u4A!\u0003\u0006\u0001#!)A\u0007\u0001C\u0001k!9q\u0007\u0001b\u0001\n\u0003A\u0004B\u0002$\u0001A\u0003%\u0011\bC\u0004H\u0001\t\u0007I\u0011\u0001%\t\rE\u0003\u0001\u0015!\u0003J\u0011\u0015)\u0006\u0001\"\u0003W\u0011\u0015Y\u0007\u0001\"\u0001m\u0011\u0015\u0001\b\u0001\"\u0001r\u0005Ii\u0015M\\5gKN$8+\u001a:jC2L'0\u001a:\u000b\u0005-a\u0011!B2iS2d'BA\u0007\u000f\u0003\u001d!x/\u001b;uKJT\u0011aD\u0001\u0004G>l7\u0001A\u000b\u0003%)\u001a\"\u0001A\n\u0011\u0007QA2D\u0004\u0002\u0016-5\t!\"\u0003\u0002\u0018\u0015\u00059\u0001/Y2lC\u001e,\u0017BA\r\u001b\u0005-Y5+\u001a:jC2L'0\u001a:\u000b\u0005]Q\u0001c\u0001\u000f&Q9\u0011Qd\t\t\u0003=\u0005j\u0011a\b\u0006\u0003AA\ta\u0001\u0010:p_Rt$\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n\u0013A\u0002)sK\u0012,g-\u0003\u0002'O\tAQ*\u00198jM\u0016\u001cHO\u0003\u0002%CA\u0011\u0011F\u000b\u0007\u0001\t\u0015Y\u0003A1\u0001-\u0005\u0005!\u0016CA\u00172!\tqs&D\u0001\"\u0013\t\u0001\u0014EA\u0004O_RD\u0017N\\4\u0011\u00059\u0012\u0014BA\u001a\"\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Y\u00022!\u0006\u0001)\u0003)\u0019\u0018N\\4mKR|gn]\u000b\u0002sA\u0019!HP!\u000f\u0005mjdB\u0001\u0010=\u0013\u0005\u0011\u0013BA\f\"\u0013\ty\u0004I\u0001\u0006J]\u0012,\u00070\u001a3TKFT!aF\u00111\u0005\t#\u0005c\u0001\u000f&\u0007B\u0011\u0011\u0006\u0012\u0003\n\u000b\u000e\t\t\u0011!A\u0003\u00021\u00121a\u0018\u00132\u0003-\u0019\u0018N\\4mKR|gn\u001d\u0011\u0002\u001dMLgn\u001a7fi>tGk\\%eqV\t\u0011\n\u0005\u0003\u001d\u00152\u0013\u0016BA&(\u0005\ri\u0015\r\u001d\u0019\u0003\u001b>\u00032\u0001H\u0013O!\tIs\nB\u0005Q\u000b\u0005\u0005\t\u0011!B\u0001Y\t\u0019q\f\n\u001a\u0002\u001fMLgn\u001a7fi>tGk\\%eq\u0002\u0002\"AL*\n\u0005Q\u000b#aA%oi\u0006iqO]5uK&sG/\u001a:oC2$Ba\u0016.`IB\u0011a\u0006W\u0005\u00033\u0006\u0012A!\u00168ji\")1L\u0002a\u00019\u0006!1n]3s!\t!R,\u0003\u0002_5\t!1J]=p\u0011\u0015\u0001g\u00011\u0001b\u0003\ryW\u000f\u001e\t\u0003)\tL!a\u0019\u000e\u0003\r=+H\u000f];u\u0011\u0015)g\u00011\u0001g\u0003\ry'M\u001b\u0019\u0003O&\u00042\u0001H\u0013i!\tI\u0013\u000eB\u0005kI\u0006\u0005\t\u0011!B\u0001Y\t\u0019q\fJ\u001a\u0002\u000b]\u0014\u0018\u000e^3\u0015\t]kgn\u001c\u0005\u00067\u001e\u0001\r\u0001\u0018\u0005\u0006A\u001e\u0001\r!\u0019\u0005\u0006K\u001e\u0001\raG\u0001\u0005e\u0016\fG\r\u0006\u0003\u001ceND\b\"B.\t\u0001\u0004a\u0006\"\u0002;\t\u0001\u0004)\u0018AA5o!\t!b/\u0003\u0002x5\t)\u0011J\u001c9vi\")\u0011\u0010\u0003a\u0001u\u0006\u00191\r\\:\u0011\u0007qY8$\u0003\u0002}O\t)1\t\\1tg\u0002"
)
public class ManifestSerializer extends Serializer {
   private final IndexedSeq singletons;
   private final Map singletonToIdx;

   public IndexedSeq singletons() {
      return this.singletons;
   }

   public Map singletonToIdx() {
      return this.singletonToIdx;
   }

   private void writeInternal(final Kryo kser, final Output out, final Manifest obj) {
      Option idxOpt = this.singletonToIdx().get(obj);
      if (idxOpt.isDefined()) {
         out.writeInt(BoxesRunTime.unboxToInt(idxOpt.get()) + 1, true);
      } else {
         out.writeInt(0, true);
         kser.writeObject(out, obj.runtimeClass());
         List targs = obj.typeArguments();
         out.writeInt(targs.size(), true);
         out.flush();
         targs.foreach((x$1) -> {
            $anonfun$writeInternal$1(this, kser, out, x$1);
            return BoxedUnit.UNIT;
         });
      }

   }

   public void write(final Kryo kser, final Output out, final Manifest obj) {
      this.writeInternal(kser, out, obj);
   }

   public Manifest read(final Kryo kser, final Input in, final Class cls) {
      int sidx = in.readInt(true);
      Manifest var10000;
      if (sidx == 0) {
         Class clazz = (Class)kser.readObject(in, Class.class);
         int targsCnt = in.readInt(true);
         if (targsCnt == 0) {
            var10000 = .MODULE$.Manifest().classType(clazz);
         } else {
            IndexedSeq typeArgs = scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), targsCnt).map((x$2) -> $anonfun$read$1(this, kser, in, BoxesRunTime.unboxToInt(x$2)));
            var10000 = .MODULE$.Manifest().classType(clazz, (Manifest)typeArgs.head(), (Seq)typeArgs.tail());
         }
      } else {
         var10000 = (Manifest)this.singletons().apply(sidx - 1);
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$writeInternal$1(final ManifestSerializer $this, final Kryo kser$1, final Output out$1, final Manifest x$1) {
      $this.writeInternal(kser$1, out$1, x$1);
   }

   // $FF: synthetic method
   public static final Manifest $anonfun$read$1(final ManifestSerializer $this, final Kryo kser$2, final Input in$1, final int x$2) {
      return $this.read(kser$2, in$1, (Class)null);
   }

   public ManifestSerializer() {
      this.singletons = (IndexedSeq)scala.package..MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Manifest[]{.MODULE$.Manifest().Any(), .MODULE$.Manifest().AnyVal(), .MODULE$.Manifest().Boolean(), .MODULE$.Manifest().Byte(), .MODULE$.Manifest().Char(), .MODULE$.Manifest().Double(), .MODULE$.Manifest().Float(), .MODULE$.Manifest().Int(), .MODULE$.Manifest().Long(), .MODULE$.Manifest().Nothing(), .MODULE$.Manifest().Null(), .MODULE$.Manifest().Object(), .MODULE$.Manifest().Short(), .MODULE$.Manifest().Unit()})));
      this.singletonToIdx = ((IterableOnceOps)this.singletons().zipWithIndex()).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
