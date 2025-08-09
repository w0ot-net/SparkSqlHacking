package com.twitter.chill;

import com.esotericsoftware.kryo.ClassResolver;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.ReferenceResolver;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.DefaultClassResolver;
import com.esotericsoftware.kryo.util.MapReferenceResolver;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Modifier;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.strategy.InstantiatorStrategy;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005e\u0001\u0002\n\u0014\u0001iA\u0001b\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tY\u0001\u0011\t\u0011)A\u0005[!)\u0001\u0007\u0001C\u0001c!)\u0001\u0007\u0001C\u0001k!Aa\u0007\u0001EC\u0002\u0013\u0005q\u0007C\u0004B\u0001\u0001\u0007I\u0011\u0003\"\t\u000f=\u0003\u0001\u0019!C\t!\"1a\u000b\u0001Q!\n\rCqa\u0016\u0001C\u0002\u0013\u0005\u0001\f\u0003\u0004r\u0001\u0001\u0006I!\u0017\u0005\u0006s\u0002!\tA\u001f\u0005\b\u0003\u0017\u0001A\u0011IA\u0007\u0011\u001d\tI\u0003\u0001C\u0001\u0003WAq!!\u000f\u0001\t\u0003\tY\u0004C\u0004\u0002L\u0001!\t%!\u0014\t\u000f\u0005M\u0003\u0001\"\u0011\u0002V!A\u0011q\u000e\u0001!\n\u0013\t\tH\u0001\u0005Lef|')Y:f\u0015\t!R#A\u0003dQ&dGN\u0003\u0002\u0017/\u00059Ao^5ui\u0016\u0014(\"\u0001\r\u0002\u0007\r|Wn\u0001\u0001\u0014\u0005\u0001Y\u0002C\u0001\u000f!\u001d\tib$D\u0001\u0014\u0013\ty2#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0005\u0012#\u0001B&ss>T!aH\n\u0002\u001b\rd\u0017m]:SKN|GN^3s!\t)#&D\u0001'\u0015\t9\u0003&\u0001\u0003lef|'BA\u0015\u0018\u0003A)7o\u001c;fe&\u001c7o\u001c4uo\u0006\u0014X-\u0003\u0002,M\ti1\t\\1tgJ+7o\u001c7wKJ\f\u0011C]3gKJ,gnY3SKN|GN^3s!\t)c&\u0003\u00020M\t\t\"+\u001a4fe\u0016t7-\u001a*fg>dg/\u001a:\u0002\rqJg.\u001b;?)\r\u00114\u0007\u000e\t\u0003;\u0001AQaI\u0002A\u0002\u0011BQ\u0001L\u0002A\u00025\"\u0012AM\u0001\u0007_\nT7+\u001a:\u0016\u0003a\u00022!H\u001d<\u0013\tQ4C\u0001\tPE*,7\r^*fe&\fG.\u001b>feB\u0011AhP\u0007\u0002{)\ta(A\u0003tG\u0006d\u0017-\u0003\u0002A{\t1\u0011I\\=SK\u001a\f\u0001b\u001d;sCR,w-_\u000b\u0002\u0007B\u0019A\b\u0012$\n\u0005\u0015k$AB(qi&|g\u000e\u0005\u0002H\u001b6\t\u0001J\u0003\u0002B\u0013*\u0011!jS\u0001\n_\nTWM\\3tSNT\u0011\u0001T\u0001\u0004_J<\u0017B\u0001(I\u0005QIen\u001d;b]RL\u0017\r^8s'R\u0014\u0018\r^3hs\u0006a1\u000f\u001e:bi\u0016<\u0017p\u0018\u0013fcR\u0011\u0011\u000b\u0016\t\u0003yIK!aU\u001f\u0003\tUs\u0017\u000e\u001e\u0005\b+\u001e\t\t\u00111\u0001D\u0003\rAH%M\u0001\ngR\u0014\u0018\r^3hs\u0002\n\u0011BZ;oGRLwN\\:\u0016\u0003e\u00032AW1e\u001d\tY\u0006M\u0004\u0002]?6\tQL\u0003\u0002_3\u00051AH]8pizJ\u0011AP\u0005\u0003?uJ!AY2\u0003\u0011%#XM]1cY\u0016T!aH\u001f1\u0005\u0015|\u0007c\u00014k[:\u0011q\r\u001b\t\u00039vJ!![\u001f\u0002\rA\u0013X\rZ3g\u0013\tYGNA\u0003DY\u0006\u001c8O\u0003\u0002j{A\u0011an\u001c\u0007\u0001\t%\u0001(\"!A\u0001\u0002\u000b\u0005!OA\u0002`IE\n!BZ;oGRLwN\\:!#\t\u0019h\u000f\u0005\u0002=i&\u0011Q/\u0010\u0002\b\u001d>$\b.\u001b8h!\tat/\u0003\u0002y{\t\u0019\u0011I\\=\u0002\t%\u001chI\u001c\u000b\u0003wz\u0004\"\u0001\u0010?\n\u0005ul$a\u0002\"p_2,\u0017M\u001c\u0005\u0007\u007f.\u0001\r!!\u0001\u0002\u000b-d\u0017m]:1\t\u0005\r\u0011q\u0001\t\u0005M*\f)\u0001E\u0002o\u0003\u000f!!\"!\u0003\u007f\u0003\u0003\u0005\tQ!\u0001s\u0005\ryFEM\u0001\u0015]\u0016<H)\u001a4bk2$8+\u001a:jC2L'0\u001a:\u0015\t\u0005=\u0011Q\u0004\u0019\u0005\u0003#\tI\u0002E\u0003\u001d\u0003'\t9\"C\u0002\u0002\u0016\t\u00121bS*fe&\fG.\u001b>feB\u0019a.!\u0007\u0005\u0015\u0005mA\"!A\u0001\u0002\u000b\u0005!OA\u0002`IQBaa \u0007A\u0002\u0005}\u0001\u0007BA\u0011\u0003K\u0001BA\u001a6\u0002$A\u0019a.!\n\u0005\u0017\u0005\u001d\u0012QDA\u0001\u0002\u0003\u0015\tA\u001d\u0002\u0004?\u0012\u001a\u0014aC5t'&tw\r\\3u_:$2a_A\u0017\u0011\u0019yX\u00021\u0001\u00020A\"\u0011\u0011GA\u001b!\u00111'.a\r\u0011\u00079\f)\u0004B\u0006\u00028\u00055\u0012\u0011!A\u0001\u0006\u0003\u0011(aA0%k\u0005YAO]=TiJ\fG/Z4z)\r1\u0015Q\b\u0005\b\u0003\u007fq\u0001\u0019AA!\u0003\r\u0019Gn\u001d\u0019\u0005\u0003\u0007\n9\u0005\u0005\u0003gU\u0006\u0015\u0003c\u00018\u0002H\u0011Y\u0011\u0011JA\u001f\u0003\u0003\u0005\tQ!\u0001s\u0005\ryFEN\u0001\u0018g\u0016$\u0018J\\:uC:$\u0018.\u0019;peN#(/\u0019;fOf$2!UA(\u0011\u0019\t\tf\u0004a\u0001\r\u0006\u00111\u000f^\u0001\u0010]\u0016<\u0018J\\:uC:$\u0018.\u0019;peR!\u0011qKA2!\u0015\tI&a\u0018<\u001b\t\tYFC\u0002\u0002^%\u000bA\"\u001b8ti\u0006tG/[1u_JLA!!\u0019\u0002\\\t\u0011rJ\u00196fGRLen\u001d;b]RL\u0017\r^8s\u0011\u001d\ty\u0004\u0005a\u0001\u0003K\u0002D!a\u001a\u0002lA!aM[A5!\rq\u00171\u000e\u0003\f\u0003[\n\u0019'!A\u0001\u0002\u000b\u0005!OA\u0002`I]\nAC\\3x)f\u0004X\rZ%ogR\fg\u000e^5bi>\u0014X\u0003BA:\u0003s\"B!!\u001e\u0002~A1\u0011\u0011LA0\u0003o\u00022A\\A=\t\u0019\tY(\u0005b\u0001e\n\tA\u000bC\u0004\u0002@E\u0001\r!a \u0011\t\u0019T\u0017q\u000f"
)
public class KryoBase extends Kryo {
   private ObjectSerializer objSer;
   private Option strategy;
   private final Iterable functions;
   private volatile boolean bitmap$0;

   private ObjectSerializer objSer$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.objSer = new ObjectSerializer();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.objSer;
   }

   public ObjectSerializer objSer() {
      return !this.bitmap$0 ? this.objSer$lzycompute() : this.objSer;
   }

   public Option strategy() {
      return this.strategy;
   }

   public void strategy_$eq(final Option x$1) {
      this.strategy = x$1;
   }

   public Iterable functions() {
      return this.functions;
   }

   public boolean isFn(final Class klass) {
      return this.functions().find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$isFn$1(klass, x$1))).isDefined();
   }

   public Serializer newDefaultSerializer(final Class klass) {
      Object var10000;
      if (this.isSingleton(klass)) {
         var10000 = this.objSer();
      } else {
         Serializer var3 = super.newDefaultSerializer(klass);
         Object var2;
         if (var3 instanceof FieldSerializer) {
            FieldSerializer var4 = (FieldSerializer)var3;
            var4.setIgnoreSyntheticFields(false);
            var2 = var4;
         } else {
            if (var3 == null) {
               throw new MatchError(var3);
            }

            var2 = var3;
         }

         var10000 = var2;
      }

      return (Serializer)var10000;
   }

   public boolean isSingleton(final Class klass) {
      return .MODULE$.last$extension(scala.Predef..MODULE$.augmentString(klass.getName())) == '$' && this.objSer().accepts(klass);
   }

   public InstantiatorStrategy tryStrategy(final Class cls) {
      return (InstantiatorStrategy)this.strategy().getOrElse(() -> {
         String name = cls.getName();
         if (cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
            throw new KryoException((new StringBuilder(51)).append("Class cannot be created (non-static member class): ").append(name).toString());
         } else {
            throw new KryoException((new StringBuilder(54)).append("Class cannot be created (missing no-arg constructor): ").append(name).toString());
         }
      });
   }

   public void setInstantiatorStrategy(final InstantiatorStrategy st) {
      super.setInstantiatorStrategy(st);
      this.strategy_$eq(new Some(st));
   }

   public ObjectInstantiator newInstantiator(final Class cls) {
      return this.newTypedInstantiator(cls);
   }

   private ObjectInstantiator newTypedInstantiator(final Class cls) {
      return Instantiators$.MODULE$.newOrElse(cls, (IterableOnce)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Function1[]{(x$2) -> Instantiators$.MODULE$.reflectAsm(x$2), (x$3) -> Instantiators$.MODULE$.normalJava(x$3)}))), () -> this.tryStrategy(cls).newInstantiatorOf(cls));
   }

   // $FF: synthetic method
   public static final Class $anonfun$functions$1(final int idx) {
      return Class.forName((new StringBuilder(14)).append("scala.Function").append(Integer.toString(idx)).toString(), true, Thread.currentThread().getContextClassLoader());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isFn$1(final Class klass$1, final Class x$1) {
      return x$1.isAssignableFrom(klass$1);
   }

   public KryoBase(final ClassResolver classResolver, final ReferenceResolver referenceResolver) {
      super(classResolver, referenceResolver);
      this.strategy = scala.None..MODULE$;
      this.functions = scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), 22).map((idx) -> $anonfun$functions$1(BoxesRunTime.unboxToInt(idx)));
   }

   public KryoBase() {
      this(new DefaultClassResolver(), new MapReferenceResolver());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
