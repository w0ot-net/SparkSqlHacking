package org.apache.spark.mllib.api.python;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.IObjectPickler;
import net.razorvine.pickle.Pickler;
import net.razorvine.pickle.Unpickler;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.python.SerDeUtil;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005efA\u0002\r\u001a\u0003\u0003yR\u0005C\u0003-\u0001\u0011\u0005a\u0006C\u00042\u0001\t\u0007i\u0011\u0001\u001a\t\u000by\u0002a\u0011A \u0007\r\r\u0003\u0011\u0011A\u0010E\u0011!YFAaA!\u0002\u0017a\u0006\"\u0002\u0017\u0005\t\u0003i\u0007b\u0002:\u0005\u0005\u0004%Ia\u001d\u0005\u0007w\u0012\u0001\u000b\u0011\u0002;\t\u000fq$!\u0019!C\u0005{\"9\u0011\u0011\u0001\u0003!\u0002\u0013q\b\u0002CA\u0002\t\t\u0007I\u0011B?\t\u000f\u0005\u0015A\u0001)A\u0005}\"1\u0011q\u0001\u0003\u0005\u0002}Ba!\u0015\u0003\u0005\u0002\u0005%\u0001\u0002CA\u0015\t\u0011\u0005\u0011$a\u000b\t\u000f\u0005mB\u0001\"\u0005\u0002>!A\u0011Q\n\u0003\u0007\u0002e\ty\u0005C\u0004\u0002X\u0001!\t!!\u0017\t\u000f\u0005u\u0003\u0001\"\u0001\u0002`!9\u0011Q\r\u0001\u0005\u0002\u0005\u001d\u0004bBAD\u0001\u0011\u0005\u0011\u0011\u0012\u0005\b\u0003#\u0003A\u0011AAJ\u0011\u001d\t9\u000b\u0001C\u0001\u0003S\u0013\u0011bU3s\t\u0016\u0014\u0015m]3\u000b\u0005iY\u0012A\u00029zi\"|gN\u0003\u0002\u001d;\u0005\u0019\u0011\r]5\u000b\u0005yy\u0012!B7mY&\u0014'B\u0001\u0011\"\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00113%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002I\u0005\u0019qN]4\u0014\u0005\u00011\u0003CA\u0014+\u001b\u0005A#\"A\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005-B#AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005y\u0003C\u0001\u0019\u0001\u001b\u0005I\u0012a\u0004)Z'B\u000b%kS0Q\u0003\u000e[\u0015iR#\u0016\u0003M\u0002\"\u0001N\u001e\u000f\u0005UJ\u0004C\u0001\u001c)\u001b\u00059$B\u0001\u001d.\u0003\u0019a$o\\8u}%\u0011!\bK\u0001\u0007!J,G-\u001a4\n\u0005qj$AB*ue&twM\u0003\u0002;Q\u0005Q\u0011N\\5uS\u0006d\u0017N_3\u0015\u0003\u0001\u0003\"aJ!\n\u0005\tC#\u0001B+oSR\u00141BQ1tKBK7m\u001b7feV\u0011Q\tZ\n\u0005\t\u0019s\u0005\f\u0005\u0002H\u00196\t\u0001J\u0003\u0002J\u0015\u0006!A.\u00198h\u0015\u0005Y\u0015\u0001\u00026bm\u0006L!!\u0014%\u0003\r=\u0013'.Z2u!\tye+D\u0001Q\u0015\t\t&+\u0001\u0004qS\u000e\\G.\u001a\u0006\u0003'R\u000b\u0011B]1{_J4\u0018N\\3\u000b\u0003U\u000b1A\\3u\u0013\t9\u0006K\u0001\bJ\u001f\nTWm\u0019;QS\u000e\\G.\u001a:\u0011\u0005=K\u0016B\u0001.Q\u0005IIuJ\u00196fGR\u001cuN\\:ueV\u001cGo\u001c:\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002^A\nl\u0011A\u0018\u0006\u0003?\"\nqA]3gY\u0016\u001cG/\u0003\u0002b=\nA1\t\\1tgR\u000bw\r\u0005\u0002dI2\u0001A!B3\u0005\u0005\u00041'!\u0001+\u0012\u0005\u001dT\u0007CA\u0014i\u0013\tI\u0007FA\u0004O_RD\u0017N\\4\u0011\u0005\u001dZ\u0017B\u00017)\u0005\r\te.\u001f\u000b\u0002]R\u0011q.\u001d\t\u0004a\u0012\u0011W\"\u0001\u0001\t\u000bm3\u00019\u0001/\u0002\u0007\rd7/F\u0001ua\t)\u0018\u0010E\u0002HmbL!a\u001e%\u0003\u000b\rc\u0017m]:\u0011\u0005\rLH!\u0003>\t\u0003\u0003\u0005\tQ!\u0001g\u0005\ryF%M\u0001\u0005G2\u001c\b%\u0001\u0004n_\u0012,H.Z\u000b\u0002}B\u0011qi`\u0005\u0003y!\u000bq!\\8ek2,\u0007%\u0001\u0003oC6,\u0017!\u00028b[\u0016\u0004\u0013\u0001\u0003:fO&\u001cH/\u001a:\u0015\u000f\u0001\u000bY!a\u0004\u0002 !1\u0011Q\u0002\bA\u0002\u0019\u000b1a\u001c2k\u0011\u001d\t\tB\u0004a\u0001\u0003'\t1a\\;u!\u0011\t)\"a\u0007\u000e\u0005\u0005]!bAA\r\u0015\u0006\u0011\u0011n\\\u0005\u0005\u0003;\t9B\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000eC\u0004\u0002\"9\u0001\r!a\t\u0002\u000fAL7m\u001b7feB\u0019q*!\n\n\u0007\u0005\u001d\u0002KA\u0004QS\u000e\\G.\u001a:\u0002\u0017M\fg/Z(cU\u0016\u001cGo\u001d\u000b\b\u0001\u00065\u0012qFA\u0019\u0011\u001d\t\tb\u0004a\u0001\u0003'Aq!!\t\u0010\u0001\u0004\t\u0019\u0003C\u0004\u00024=\u0001\r!!\u000e\u0002\u000f=\u0014'.Z2ugB!q%a\u000ek\u0013\r\tI\u0004\u000b\u0002\u000byI,\u0007/Z1uK\u0012t\u0014\u0001C4fi\nKH/Z:\u0015\t\u0005}\u00121\n\t\u0006O\u0005\u0005\u0013QI\u0005\u0004\u0003\u0007B#!B!se\u0006L\bcA\u0014\u0002H%\u0019\u0011\u0011\n\u0015\u0003\t\tKH/\u001a\u0005\u0007\u0003\u001b\u0001\u0002\u0019\u0001$\u0002\u0013M\fg/Z*uCR,Gc\u0002!\u0002R\u0005M\u0013Q\u000b\u0005\u0007\u0003\u001b\t\u0002\u0019\u0001$\t\u000f\u0005E\u0011\u00031\u0001\u0002\u0014!9\u0011\u0011E\tA\u0002\u0005\r\u0012!\u00023v[B\u001cH\u0003BA \u00037Ba!!\u0004\u0013\u0001\u00041\u0013!\u00027pC\u0012\u001cHc\u0001\u0014\u0002b!9\u00111M\nA\u0002\u0005}\u0012!\u00022zi\u0016\u001c\u0018AC1t)V\u0004H.\u001a*E\tR!\u0011\u0011NAA!\u0019\tY'!\u001d\u0002v5\u0011\u0011Q\u000e\u0006\u0004\u0003_z\u0012a\u0001:eI&!\u00111OA7\u0005\r\u0011F\t\u0012\t\bO\u0005]\u00141PA>\u0013\r\tI\b\u000b\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007\u001d\ni(C\u0002\u0002\u0000!\u00121!\u00138u\u0011\u001d\ty\u0007\u0006a\u0001\u0003\u0007\u0003b!a\u001b\u0002r\u0005\u0015\u0005\u0003B\u0014\u0002B)\fQB\u001a:p[R+\b\u000f\\33%\u0012#E\u0003BAB\u0003\u0017Cq!a\u001c\u0016\u0001\u0004\ti\t\u0005\u0004\u0002l\u0005E\u0014q\u0012\t\u0006O\u0005]$N[\u0001\rU\u00064\u0018\rV8QsRDwN\u001c\u000b\u0005\u0003+\u000b\t\u000b\u0005\u0004\u0002\u0018\u0006u\u0015qH\u0007\u0003\u00033S1aSAN\u0015\tar$\u0003\u0003\u0002 \u0006e%a\u0002&bm\u0006\u0014F\t\u0012\u0005\b\u0003G3\u0002\u0019AAS\u0003\u0011Q'\u000b\u0012#\u0011\u000b\u0005]\u0015Q\u00146\u0002\u0019ALH\u000f[8o)>T\u0015M^1\u0015\r\u0005\u0015\u00161VAX\u0011\u001d\tik\u0006a\u0001\u0003+\u000bQ\u0001]=S\t\u0012Cq!!-\u0018\u0001\u0004\t\u0019,A\u0004cCR\u001c\u0007.\u001a3\u0011\u0007\u001d\n),C\u0002\u00028\"\u0012qAQ8pY\u0016\fg\u000e"
)
public abstract class SerDeBase {
   public abstract String PYSPARK_PACKAGE();

   public abstract void initialize();

   public byte[] dumps(final Object obj) {
      return .MODULE$.isArray(obj, 1) ? (new Pickler(true, false)).dumps(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(obj).toImmutableArraySeq()).asJava()) : (new Pickler(true, false)).dumps(obj);
   }

   public Object loads(final byte[] bytes) {
      return (new Unpickler()).loads(bytes);
   }

   public RDD asTupleRDD(final RDD rdd) {
      return rdd.map((x) -> new Tuple2.mcII.sp(BoxesRunTime.unboxToInt(x[0]), BoxesRunTime.unboxToInt(x[1])), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public RDD fromTuple2RDD(final RDD rdd) {
      return rdd.map((x) -> new Object[]{x._1(), x._2()}, scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Object.class)));
   }

   public JavaRDD javaToPython(final JavaRDD jRDD) {
      return org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(jRDD.rdd().mapPartitions((iter) -> {
         this.initialize();
         return new SerDeUtil.AutoBatchedPickler(iter);
      }, jRDD.rdd().mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Byte.TYPE))), scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Byte.TYPE)));
   }

   public JavaRDD pythonToJava(final JavaRDD pyRDD, final boolean batched) {
      return pyRDD.rdd().mapPartitions((iter) -> {
         this.initialize();
         Unpickler unpickle = new Unpickler();
         return iter.flatMap((row) -> {
            Object obj = unpickle.loads(row);
            if (batched) {
               if (obj instanceof ArrayList) {
                  ArrayList var6 = (ArrayList)obj;
                  return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var6).asScala();
               } else if (.MODULE$.isArray(obj, 1)) {
                  return scala.Predef..MODULE$.genericWrapArray(obj);
               } else {
                  throw new MatchError(obj);
               }
            } else {
               return new scala.collection.immutable..colon.colon(obj, scala.collection.immutable.Nil..MODULE$);
            }
         });
      }, pyRDD.rdd().mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Object.class)).toJavaRDD();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public abstract class BasePickler implements IObjectPickler, IObjectConstructor {
      private final Class cls;
      private final String module;
      private final String name;
      // $FF: synthetic field
      public final SerDeBase $outer;

      private Class cls() {
         return this.cls;
      }

      private String module() {
         return this.module;
      }

      private String name() {
         return this.name;
      }

      public void register() {
         Pickler.registerCustomPickler(this.getClass(), this);
         Pickler.registerCustomPickler(this.cls(), this);
         Unpickler.registerConstructor(this.module(), this.name(), this);
      }

      public void pickle(final Object obj, final OutputStream out, final Pickler pickler) {
         label14: {
            if (obj == null) {
               if (this == null) {
                  break label14;
               }
            } else if (obj.equals(this)) {
               break label14;
            }

            pickler.save(this);
            this.saveState(obj, out, pickler);
            out.write(82);
            return;
         }

         out.write(99);
         out.write((this.module() + "\n" + this.name() + "\n").getBytes(StandardCharsets.UTF_8));
      }

      public void saveObjects(final OutputStream out, final Pickler pickler, final Seq objects) {
         if (objects.length() == 0 || objects.length() > 3) {
            out.write(40);
         }

         objects.foreach((x$1) -> {
            $anonfun$saveObjects$1(pickler, x$1);
            return BoxedUnit.UNIT;
         });
         int var5 = objects.length();
         short var10000;
         switch (var5) {
            case 1 -> var10000 = 133;
            case 2 -> var10000 = 134;
            case 3 -> var10000 = 135;
            default -> var10000 = 116;
         }

         short code = var10000;
         out.write(code);
      }

      public byte[] getBytes(final Object obj) {
         return obj.getClass().isArray() ? (byte[])obj : ((String)obj).getBytes(StandardCharsets.ISO_8859_1);
      }

      public abstract void saveState(final Object obj, final OutputStream out, final Pickler pickler);

      // $FF: synthetic method
      public SerDeBase org$apache$spark$mllib$api$python$SerDeBase$BasePickler$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$saveObjects$1(final Pickler pickler$1, final Object x$1) {
         pickler$1.save(x$1);
      }

      public BasePickler(final ClassTag evidence$1) {
         if (SerDeBase.this == null) {
            throw null;
         } else {
            this.$outer = SerDeBase.this;
            super();
            this.cls = ((ClassTag)scala.Predef..MODULE$.implicitly(evidence$1)).runtimeClass();
            String var10001 = SerDeBase.this.PYSPARK_PACKAGE();
            this.module = var10001 + "." + scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(this.cls().getName()), '.')[4];
            this.name = this.cls().getSimpleName();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
