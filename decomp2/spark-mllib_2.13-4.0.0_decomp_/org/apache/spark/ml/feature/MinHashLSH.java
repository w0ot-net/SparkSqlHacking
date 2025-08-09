package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001B\n\u0015\u0001}A\u0001b\f\u0001\u0003\u0006\u0004%\t\u0005\r\u0005\t}\u0001\u0011\t\u0011)A\u0005c!)q\b\u0001C\u0001\u0001\")1\t\u0001C!\t\")\u0011\u000b\u0001C!%\")Q\u000b\u0001C!-\")q\b\u0001C\u0001;\")q\f\u0001C\u0001A\"1a\r\u0001C)-\u001dDQa\u001b\u0001\u0005B1DQ\u0001\u001f\u0001\u0005Be<q!!\u0002\u0015\u0011\u0003\t9A\u0002\u0004\u0014)!\u0005\u0011\u0011\u0002\u0005\u0007\u007f5!\t!!\f\t\u0015\u0005=RB1A\u0005\u0002Y\t\t\u0004C\u0004\u000245\u0001\u000b\u0011\u0002-\t\u000f\u0005UR\u0002\"\u0011\u00028!I\u0011qH\u0007\u0002\u0002\u0013%\u0011\u0011\t\u0002\u000b\u001b&t\u0007*Y:i\u0019NC%BA\u000b\u0017\u0003\u001d1W-\u0019;ve\u0016T!a\u0006\r\u0002\u00055d'BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001I\u0014\u0011\u0007\u0005\u0012C%D\u0001\u0015\u0013\t\u0019CCA\u0002M'\"\u0003\"!I\u0013\n\u0005\u0019\"\"aD'j]\"\u000b7\u000f\u001b'T\u00116{G-\u001a7\u0011\u0005!jS\"A\u0015\u000b\u0005)Z\u0013AB:iCJ,GM\u0003\u0002--\u0005)\u0001/\u0019:b[&\u0011a&\u000b\u0002\b\u0011\u0006\u001c8+Z3e\u0003\r)\u0018\u000eZ\u000b\u0002cA\u0011!g\u000f\b\u0003ge\u0002\"\u0001N\u001c\u000e\u0003UR!A\u000e\u0010\u0002\rq\u0012xn\u001c;?\u0015\u0005A\u0014!B:dC2\f\u0017B\u0001\u001e8\u0003\u0019\u0001&/\u001a3fM&\u0011A(\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005i:\u0014\u0001B;jI\u0002\na\u0001P5oSRtDCA!C!\t\t\u0003\u0001C\u00030\u0007\u0001\u0007\u0011'A\u0006tKRLe\u000e];u\u0007>dGCA#G\u001b\u0005\u0001\u0001\"B$\u0005\u0001\u0004\t\u0014!\u0002<bYV,\u0007f\u0001\u0003J\u001fB\u0011!*T\u0007\u0002\u0017*\u0011A\nG\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001(L\u0005\u0015\u0019\u0016N\\2fC\u0005\u0001\u0016!\u0002\u001a/c9\u0002\u0014\u0001D:fi>+H\u000f];u\u0007>dGCA#T\u0011\u00159U\u00011\u00012Q\r)\u0011jT\u0001\u0011g\u0016$h*^7ICNDG+\u00192mKN$\"!R,\t\u000b\u001d3\u0001\u0019\u0001-\u0011\u0005eSV\"A\u001c\n\u0005m;$aA%oi\"\u001aa!S(\u0015\u0003\u0005C3aB%P\u0003\u001d\u0019X\r^*fK\u0012$\"!R1\t\u000b\u001dC\u0001\u0019\u00012\u0011\u0005e\u001b\u0017B\u000138\u0005\u0011auN\\4)\u0007!Iu*A\tde\u0016\fG/\u001a*bo2\u001b\u0006*T8eK2$\"\u0001\n5\t\u000b%L\u0001\u0019\u0001-\u0002\u0011%t\u0007/\u001e;ES6D3!C%P\u0003=!(/\u00198tM>\u0014XnU2iK6\fGCA7v!\tq7/D\u0001p\u0015\t\u0001\u0018/A\u0003usB,7O\u0003\u0002s1\u0005\u00191/\u001d7\n\u0005Q|'AC*ueV\u001cG\u000fV=qK\")aO\u0003a\u0001[\u000611o\u00195f[\u0006D3AC%P\u0003\u0011\u0019w\u000e]=\u0015\u0005\u0015S\b\"B>\f\u0001\u0004a\u0018!B3yiJ\f\u0007CA?\u007f\u001b\u0005Y\u0013BA@,\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\bfA\u0006J\u001f\"\u001a\u0001!S(\u0002\u00155Kg\u000eS1tQ2\u001b\u0006\n\u0005\u0002\"\u001bM9Q\"a\u0003\u0002\u0012\u0005u\u0001cA-\u0002\u000e%\u0019\u0011qB\u001c\u0003\r\u0005s\u0017PU3g!\u0015\t\u0019\"!\u0007B\u001b\t\t)BC\u0002\u0002\u0018Y\tA!\u001e;jY&!\u00111DA\u000b\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!a\b\u0002*5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#\u0001\u0002j_*\u0011\u0011qE\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002,\u0005\u0005\"\u0001D*fe&\fG.\u001b>bE2,GCAA\u0004\u0003)A\u0015i\u0015%`!JKU*R\u000b\u00021\u0006Y\u0001*Q*I?B\u0013\u0016*T#!\u0003\u0011aw.\u00193\u0015\u0007\u0005\u000bI\u0004\u0003\u0004\u0002<E\u0001\r!M\u0001\u0005a\u0006$\b\u000eK\u0002\u0012\u0013>\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0011\u0011\t\u0005\u0015\u00131J\u0007\u0003\u0003\u000fRA!!\u0013\u0002&\u0005!A.\u00198h\u0013\u0011\ti%a\u0012\u0003\r=\u0013'.Z2uQ\ri\u0011j\u0014\u0015\u0004\u0019%{\u0005"
)
public class MinHashLSH extends LSH implements HasSeed {
   private final String uid;
   private LongParam seed;

   public static MinHashLSH load(final String path) {
      return MinHashLSH$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MinHashLSH$.MODULE$.read();
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public MinHashLSH setInputCol(final String value) {
      return (MinHashLSH)super.setInputCol(value);
   }

   public MinHashLSH setOutputCol(final String value) {
      return (MinHashLSH)super.setOutputCol(value);
   }

   public MinHashLSH setNumHashTables(final int value) {
      return (MinHashLSH)super.setNumHashTables(value);
   }

   public MinHashLSH setSeed(final long value) {
      return (MinHashLSH)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public MinHashLSHModel createRawLSHModel(final int inputDim) {
      .MODULE$.require(inputDim <= MinHashLSH$.MODULE$.HASH_PRIME(), () -> "The input vector dimension " + inputDim + " exceeds the threshold " + MinHashLSH$.MODULE$.HASH_PRIME() + ".");
      Random rand = new Random(BoxesRunTime.unboxToLong(this.$(this.seed())));
      Tuple2[] randCoefs = (Tuple2[])scala.Array..MODULE$.fill(BoxesRunTime.unboxToInt(this.$(this.numHashTables())), () -> new Tuple2.mcII.sp(1 + rand.nextInt(MinHashLSH$.MODULE$.HASH_PRIME() - 1), rand.nextInt(MinHashLSH$.MODULE$.HASH_PRIME() - 1)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return new MinHashLSHModel(this.uid(), randCoefs);
   }

   public StructType transformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      return this.validateAndTransformSchema(schema);
   }

   public MinHashLSH copy(final ParamMap extra) {
      return (MinHashLSH)this.defaultCopy(extra);
   }

   public MinHashLSH(final String uid) {
      this.uid = uid;
      HasSeed.$init$(this);
      Statics.releaseFence();
   }

   public MinHashLSH() {
      this(Identifiable$.MODULE$.randomUID("mh-lsh"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
