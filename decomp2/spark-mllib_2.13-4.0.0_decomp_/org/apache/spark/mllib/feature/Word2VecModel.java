package org.apache.spark.mllib.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.JValue;
import org.sparkproject.guava.collect.Ordering;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.math.Ordering.Int.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005g\u0001\u0002\u001f>\u0001!C\u0011\"\u0019\u0001\u0003\u0006\u0004%\t!\u00112\t\u0011E\u0004!\u0011!Q\u0001\n\rD\u0011B\u001d\u0001\u0003\u0006\u0004%\t!Q:\t\u0011i\u0004!\u0011!Q\u0001\nQDaa\u001f\u0001\u0005\u0002\u0005c\b\"CA\u0002\u0001\t\u0007I\u0011BA\u0003\u0011\u001d\t9\u0001\u0001Q\u0001\n9D\u0011\"!\u0003\u0001\u0005\u0004%I!!\u0002\t\u000f\u0005-\u0001\u0001)A\u0005]\"Q\u0011Q\u0002\u0001\t\u0006\u0004%I!a\u0004\t\u0013\u0005M\u0001\u0001#b\u0001\n\u0013\u0019\bBB>\u0001\t\u0013\t)\u0002\u0003\u0004|\u0001\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003s\u0001A\u0011AA\u001e\u0011\u001d\tI\u0006\u0001C\u0001\u00037Bq!a\u001d\u0001\t\u0003\t)\bC\u0004\u0002t\u0001!\t!!#\t\u0011\u0005M\u0004\u0001\"\u0001B\u0003'Cq!!*\u0001\t\u0003\t9kB\u0004\u00022vB\t!a-\u0007\rqj\u0004\u0012AA[\u0011\u0019YX\u0003\"\u0001\u0002L\"9\u0011QZ\u000b\u0005\n\u0005=waBAj+!%\u0011Q\u001b\u0004\b\u00033,\u0002\u0012BAn\u0011\u0019Y\u0018\u0004\"\u0001\u0002^\"I\u0011q\\\rC\u0002\u0013\u0005\u0011\u0011\u001d\u0005\t\u0003[L\u0002\u0015!\u0003\u0002d\"I\u0011q^\rC\u0002\u0013\u0005\u0011\u0011\u001d\u0005\t\u0003cL\u0002\u0015!\u0003\u0002d\u001a1\u00111_\rA\u0003kD!\"a\u001b \u0005+\u0007I\u0011AA\u007f\u0011%\typ\bB\tB\u0003%1\u000eC\u0005\u0002\u000e~\u0011)\u001a!C\u0001g\"I!\u0011A\u0010\u0003\u0012\u0003\u0006I\u0001\u001e\u0005\u0007w~!\tAa\u0001\t\u0013\t5q$!A\u0005\u0002\t=\u0001\"\u0003B\u000b?E\u0005I\u0011\u0001B\f\u0011%\u0011YcHI\u0001\n\u0003\u0011i\u0003C\u0005\u00032}\t\t\u0011\"\u0011\u0002b\"I!1G\u0010\u0002\u0002\u0013\u0005\u0011Q\u0001\u0005\n\u0005ky\u0012\u0011!C\u0001\u0005oA\u0011Ba\u0011 \u0003\u0003%\tE!\u0012\t\u0013\tMs$!A\u0005\u0002\tU\u0003\"\u0003B0?\u0005\u0005I\u0011\tB1\u0011%\u0011)gHA\u0001\n\u0003\u00129\u0007C\u0005\u0003j}\t\t\u0011\"\u0011\u0003l!I!QN\u0010\u0002\u0002\u0013\u0005#qN\u0004\n\u0005gJ\u0012\u0011!E\u0001\u0005k2\u0011\"a=\u001a\u0003\u0003E\tAa\u001e\t\rm\u0014D\u0011\u0001BC\u0011%\u0011IGMA\u0001\n\u000b\u0012Y\u0007C\u0005\u0003\bJ\n\t\u0011\"!\u0003\n\"I!q\u0012\u001a\u0002\u0002\u0013\u0005%\u0011\u0013\u0005\n\u00057\u0013\u0014\u0011!C\u0005\u0005;CqA!*\u001a\t\u0003\u00119\u000bC\u0004\u0002:e!\tA!,\t\u000f\t\u0015V\u0003\"\u0011\u00036\"I!1T\u000b\u0002\u0002\u0013%!Q\u0014\u0002\u000e/>\u0014HM\r,fG6{G-\u001a7\u000b\u0005yz\u0014a\u00024fCR,(/\u001a\u0006\u0003\u0001\u0006\u000bQ!\u001c7mS\nT!AQ\"\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011+\u0015AB1qC\u000eDWMC\u0001G\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0011jT.\u0011\u0005)kU\"A&\u000b\u00031\u000bQa]2bY\u0006L!AT&\u0003\r\u0005s\u0017PU3g!\t\u0001\u0006L\u0004\u0002R-:\u0011!+V\u0007\u0002'*\u0011AkR\u0001\u0007yI|w\u000e\u001e \n\u00031K!aV&\u0002\u000fA\f7m[1hK&\u0011\u0011L\u0017\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003/.\u0003\"\u0001X0\u000e\u0003uS!AX \u0002\tU$\u0018\u000e\\\u0005\u0003Av\u0013\u0001bU1wK\u0006\u0014G.Z\u0001\no>\u0014H-\u00138eKb,\u0012a\u0019\t\u0005I\"\\gN\u0004\u0002fMB\u0011!kS\u0005\u0003O.\u000ba\u0001\u0015:fI\u00164\u0017BA5k\u0005\ri\u0015\r\u001d\u0006\u0003O.\u0003\"\u0001\u001a7\n\u00055T'AB*ue&tw\r\u0005\u0002K_&\u0011\u0001o\u0013\u0002\u0004\u0013:$\u0018AC<pe\u0012Le\u000eZ3yA\u0005Yqo\u001c:e-\u0016\u001cGo\u001c:t+\u0005!\bc\u0001&vo&\u0011ao\u0013\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003\u0015bL!!_&\u0003\u000b\u0019cw.\u0019;\u0002\u0019]|'\u000f\u001a,fGR|'o\u001d\u0011\u0002\rqJg.\u001b;?)\u0011ix0!\u0001\u0011\u0005y\u0004Q\"A\u001f\t\u000b\u0005,\u0001\u0019A2\t\u000bI,\u0001\u0019\u0001;\u0002\u00119,XnV8sIN,\u0012A\\\u0001\n]Vlwk\u001c:eg\u0002\n!B^3di>\u00148+\u001b>f\u0003-1Xm\u0019;peNK'0\u001a\u0011\u0002\u0011]|'\u000f\u001a'jgR,\"!!\u0005\u0011\u0007)+8.A\bx_J$g+Z2J]ZtuN]7t)\ri\u0018q\u0003\u0005\b\u00033a\u0001\u0019AA\u000e\u0003\u0015iw\u000eZ3m!\u0015Q\u0015QD2u\u0013\r\tyb\u0013\u0002\u0007)V\u0004H.\u001a\u001a\u0015\u0007u\f\u0019\u0003C\u0004\u0002\u001a5\u0001\r!!\n\u0011\t\u0011D7\u000e\u001e\u0015\u0006\u001b\u0005%\u0012Q\u0007\t\u0005\u0003W\t\t$\u0004\u0002\u0002.)\u0019\u0011qF!\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00024\u00055\"!B*j]\u000e,\u0017EAA\u001c\u0003\u0015\td&\u000e\u00181\u0003\u0011\u0019\u0018M^3\u0015\r\u0005u\u00121IA(!\rQ\u0015qH\u0005\u0004\u0003\u0003Z%\u0001B+oSRDq!!\u0012\u000f\u0001\u0004\t9%\u0001\u0002tGB!\u0011\u0011JA&\u001b\u0005\t\u0015bAA'\u0003\na1\u000b]1sW\u000e{g\u000e^3yi\"1\u0011\u0011\u000b\bA\u0002-\fA\u0001]1uQ\"*a\"!\u000b\u0002V\u0005\u0012\u0011qK\u0001\u0006c9\"d\u0006M\u0001\niJ\fgn\u001d4pe6$B!!\u0018\u0002jA!\u0011qLA3\u001b\t\t\tGC\u0002\u0002d}\na\u0001\\5oC2<\u0017\u0002BA4\u0003C\u0012aAV3di>\u0014\bBBA6\u001f\u0001\u00071.\u0001\u0003x_J$\u0007&B\b\u0002*\u0005=\u0014EAA9\u0003\u0015\td&\r\u00181\u000311\u0017N\u001c3Ts:|g._7t)\u0019\t9(!!\u0002\u0004B!!*^A=!\u0019Q\u0015QD6\u0002|A\u0019!*! \n\u0007\u0005}4J\u0001\u0004E_V\u0014G.\u001a\u0005\u0007\u0003W\u0002\u0002\u0019A6\t\r\u0005\u0015\u0005\u00031\u0001o\u0003\rqW/\u001c\u0015\u0006!\u0005%\u0012q\u000e\u000b\u0007\u0003o\nY)a$\t\u000f\u00055\u0015\u00031\u0001\u0002^\u00051a/Z2u_JDa!!\"\u0012\u0001\u0004q\u0007&B\t\u0002*\u0005=D\u0003CA<\u0003+\u000bI*a'\t\u000f\u00055%\u00031\u0001\u0002\u0018B!!*^A>\u0011\u0019\t)I\u0005a\u0001]\"9\u0011Q\u0014\nA\u0002\u0005}\u0015aB<pe\u0012|\u0005\u000f\u001e\t\u0005\u0015\u0006\u00056.C\u0002\u0002$.\u0013aa\u00149uS>t\u0017AC4fiZ+7\r^8sgV\u0011\u0011Q\u0005\u0015\u0006'\u0005%\u00121V\u0011\u0003\u0003[\u000bQ!\r\u00183]ABS\u0001AA\u0015\u0003_\nQbV8sIJ2VmY'pI\u0016d\u0007C\u0001@\u0016'\u0019)\u0012*a.\u0002>B!A,!/~\u0013\r\tY,\u0018\u0002\u0007\u0019>\fG-\u001a:\u0011\t\u0005}\u0016\u0011Z\u0007\u0003\u0003\u0003TA!a1\u0002F\u0006\u0011\u0011n\u001c\u0006\u0003\u0003\u000f\fAA[1wC&\u0019\u0011,!1\u0015\u0005\u0005M\u0016a\u00042vS2$gI]8n-\u0016\u001cW*\u00199\u0015\t\u0005m\u0011\u0011\u001b\u0005\b\u000339\u0002\u0019AA\u0013\u00031\u0019\u0016M^3M_\u0006$g+M01!\r\t9.G\u0007\u0002+\ta1+\u0019<f\u0019>\fGMV\u0019`aM\u0011\u0011$\u0013\u000b\u0003\u0003+\f\u0011CZ8s[\u0006$h+\u001a:tS>tg+M01+\t\t\u0019\u000f\u0005\u0003\u0002f\u0006-XBAAt\u0015\u0011\tI/!2\u0002\t1\fgnZ\u0005\u0004[\u0006\u001d\u0018A\u00054pe6\fGOV3sg&|gNV\u0019`a\u0001\nQb\u00197bgNt\u0015-\\3Wc}\u0003\u0014AD2mCN\u001ch*Y7f-Fz\u0006\u0007\t\u0002\u0005\t\u0006$\u0018mE\u0003 \u0013\u0006]x\nE\u0002K\u0003sL1!a?L\u0005\u001d\u0001&o\u001c3vGR,\u0012a[\u0001\u0006o>\u0014H\rI\u0001\bm\u0016\u001cGo\u001c:!)\u0019\u0011)A!\u0003\u0003\fA\u0019!qA\u0010\u000e\u0003eAa!a\u001b%\u0001\u0004Y\u0007BBAGI\u0001\u0007A/\u0001\u0003d_BLHC\u0002B\u0003\u0005#\u0011\u0019\u0002\u0003\u0005\u0002l\u0015\u0002\n\u00111\u0001l\u0011!\ti)\nI\u0001\u0002\u0004!\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u00053Q3a\u001bB\u000eW\t\u0011i\u0002\u0005\u0003\u0003 \t\u001dRB\u0001B\u0011\u0015\u0011\u0011\u0019C!\n\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0018\u0017&!!\u0011\u0006B\u0011\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011yCK\u0002u\u00057\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&D\u0018\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0005s\u0011y\u0004E\u0002K\u0005wI1A!\u0010L\u0005\r\te.\u001f\u0005\t\u0005\u0003R\u0013\u0011!a\u0001]\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"Aa\u0012\u0011\r\t%#q\nB\u001d\u001b\t\u0011YEC\u0002\u0003N-\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011\tFa\u0013\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005/\u0012i\u0006E\u0002K\u00053J1Aa\u0017L\u0005\u001d\u0011un\u001c7fC:D\u0011B!\u0011-\u0003\u0003\u0005\rA!\u000f\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003G\u0014\u0019\u0007\u0003\u0005\u0003B5\n\t\u00111\u0001o\u0003!A\u0017m\u001d5D_\u0012,G#\u00018\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a9\u0002\r\u0015\fX/\u00197t)\u0011\u00119F!\u001d\t\u0013\t\u0005\u0003'!AA\u0002\te\u0012\u0001\u0002#bi\u0006\u00042Aa\u00023'\u0015\u0011$\u0011PA_!!\u0011YH!!li\n\u0015QB\u0001B?\u0015\r\u0011yhS\u0001\beVtG/[7f\u0013\u0011\u0011\u0019I! \u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0006\u0002\u0003v\u0005)\u0011\r\u001d9msR1!Q\u0001BF\u0005\u001bCa!a\u001b6\u0001\u0004Y\u0007BBAGk\u0001\u0007A/A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\tM%q\u0013\t\u0006\u0015\u0006\u0005&Q\u0013\t\u0006\u0015\u0006u1\u000e\u001e\u0005\n\u000533\u0014\u0011!a\u0001\u0005\u000b\t1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011y\n\u0005\u0003\u0002f\n\u0005\u0016\u0002\u0002BR\u0003O\u0014aa\u00142kK\u000e$\u0018\u0001\u00027pC\u0012$R! BU\u0005WCq!!\u00129\u0001\u0004\t9\u0005\u0003\u0004\u0002Ra\u0002\ra\u001b\u000b\t\u0003{\u0011yK!-\u00034\"9\u0011QI\u001dA\u0002\u0005\u001d\u0003BBA)s\u0001\u00071\u000eC\u0004\u0002\u001ae\u0002\r!!\n\u0015\u000bu\u00149L!/\t\u000f\u0005\u0015#\b1\u0001\u0002H!1\u0011\u0011\u000b\u001eA\u0002-DSAOA\u0015\u0003+BS!FA\u0015\u0003+BS\u0001FA\u0015\u0003+\u0002"
)
public class Word2VecModel implements Serializable, Saveable {
   private String[] wordList;
   private float[] wordVecInvNorms;
   private final Map wordIndex;
   private final float[] wordVectors;
   private final int numWords;
   private final int vectorSize;
   private volatile byte bitmap$0;

   public static Word2VecModel load(final SparkContext sc, final String path) {
      return Word2VecModel$.MODULE$.load(sc, path);
   }

   public Map wordIndex() {
      return this.wordIndex;
   }

   public float[] wordVectors() {
      return this.wordVectors;
   }

   private int numWords() {
      return this.numWords;
   }

   private int vectorSize() {
      return this.vectorSize;
   }

   private String[] wordList$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.wordList = (String[])((IterableOnce)this.wordIndex().toSeq().sortBy((x$8) -> BoxesRunTime.boxToInteger($anonfun$wordList$1(x$8)), .MODULE$)).iterator().map((x$9) -> (String)x$9._1()).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.wordList;
   }

   private String[] wordList() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.wordList$lzycompute() : this.wordList;
   }

   private float[] wordVecInvNorms$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            int size = this.vectorSize();
            this.wordVecInvNorms = (float[])scala.Array..MODULE$.tabulate(this.numWords(), (JFunction1.mcFI.sp)(i) -> {
               float norm = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().snrm2(size, this.wordVectors(), i * size, 1);
               return norm != (float)0 ? (float)1 / norm : 0.0F;
            }, scala.reflect.ClassTag..MODULE$.Float());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.wordVecInvNorms;
   }

   private float[] wordVecInvNorms() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.wordVecInvNorms$lzycompute() : this.wordVecInvNorms;
   }

   public void save(final SparkContext sc, final String path) {
      Word2VecModel.SaveLoadV1_0$.MODULE$.save(sc, path, this.getVectors());
   }

   public Vector transform(final String word) {
      Option var3 = this.wordIndex().get(word);
      if (!(var3 instanceof Some var4)) {
         if (scala.None..MODULE$.equals(var3)) {
            throw new IllegalStateException(word + " not in vocabulary");
         } else {
            throw new MatchError(var3);
         }
      } else {
         int index = BoxesRunTime.unboxToInt(var4.value());
         int size = this.vectorSize();
         int offset = index * size;
         double[] array = (double[])scala.Array..MODULE$.ofDim(size, scala.reflect.ClassTag..MODULE$.Double());

         for(int i = 0; i < size; ++i) {
            array[i] = (double)this.wordVectors()[offset + i];
         }

         return Vectors$.MODULE$.dense(array);
      }
   }

   public Tuple2[] findSynonyms(final String word, final int num) {
      Vector vector = this.transform(word);
      return this.findSynonyms(vector.toArray(), num, new Some(word));
   }

   public Tuple2[] findSynonyms(final Vector vector, final int num) {
      return this.findSynonyms(vector.toArray(), num, scala.None..MODULE$);
   }

   public Tuple2[] findSynonyms(final double[] vector, final int num, final Option wordOpt) {
      scala.Predef..MODULE$.require(num > 0, () -> "Number of similar words should > 0");
      int localVectorSize = this.vectorSize();
      float[] floatVec = (float[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(vector), (JFunction1.mcFD.sp)(x$10) -> (float)x$10, scala.reflect.ClassTag..MODULE$.Float());
      float vecNorm = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().snrm2(localVectorSize, floatVec, 1);
      String[] localWordList = this.wordList();
      int localNumWords = this.numWords();
      if (vecNorm == (float)0) {
         return (Tuple2[])scala.package..MODULE$.Iterator().tabulate(num + 1, (ix) -> $anonfun$findSynonyms$3(localWordList, BoxesRunTime.unboxToInt(ix))).filterNot((t) -> BoxesRunTime.boxToBoolean($anonfun$findSynonyms$4(wordOpt, t))).take(num).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      } else {
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().sscal(localVectorSize, (float)1 / vecNorm, floatVec, 0, 1);
         float[] cosineVec = (float[])scala.Array..MODULE$.ofDim(localNumWords, scala.reflect.ClassTag..MODULE$.Float());
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().sgemv("T", localVectorSize, localNumWords, 1.0F, this.wordVectors(), localVectorSize, floatVec, 1, 0.0F, cosineVec, 1);
         float[] localWordVecInvNorms = this.wordVecInvNorms();

         for(int i = 0; i < cosineVec.length; ++i) {
            cosineVec[i] *= localWordVecInvNorms[i];
         }

         Ordering idxOrd = new Ordering(cosineVec) {
            private final float[] cosineVec$1;

            public int compare(final int left, final int right) {
               return scala.package..MODULE$.Ordering().apply(scala.math.Ordering.DeprecatedFloatOrdering..MODULE$).compare(BoxesRunTime.boxToFloat(this.cosineVec$1[left]), BoxesRunTime.boxToFloat(this.cosineVec$1[right]));
            }

            public {
               this.cosineVec$1 = cosineVec$1;
            }
         };
         return (Tuple2[])scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(idxOrd.greatestOf(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(scala.package..MODULE$.Iterator().range(0, localNumWords)).asJava(), num + 1).iterator()).asScala().map((ix) -> $anonfun$findSynonyms$5(localWordList, cosineVec, BoxesRunTime.unboxToInt(ix))).filterNot((t) -> BoxesRunTime.boxToBoolean($anonfun$findSynonyms$6(wordOpt, t))).take(num).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      }
   }

   public Map getVectors() {
      return (Map)this.wordIndex().map((x0$1) -> {
         if (x0$1 != null) {
            String word = (String)x0$1._1();
            int ind = x0$1._2$mcI$sp();
            return new Tuple2(word, scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.floatArrayOps(this.wordVectors()), this.vectorSize() * ind, this.vectorSize() * ind + this.vectorSize()));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   // $FF: synthetic method
   public static final int $anonfun$wordList$1(final Tuple2 x$8) {
      return x$8._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$findSynonyms$3(final String[] localWordList$1, final int i) {
      return new Tuple2(localWordList$1[i], BoxesRunTime.boxToDouble((double)0.0F));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findSynonyms$4(final Option wordOpt$1, final Tuple2 t) {
      return wordOpt$1.contains(t._1());
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$findSynonyms$5(final String[] localWordList$1, final float[] cosineVec$1, final int i) {
      return new Tuple2(localWordList$1[i], BoxesRunTime.boxToDouble((double)cosineVec$1[i]));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findSynonyms$6(final Option wordOpt$1, final Tuple2 t) {
      return wordOpt$1.contains(t._1());
   }

   public Word2VecModel(final Map wordIndex, final float[] wordVectors) {
      this.wordIndex = wordIndex;
      this.wordVectors = wordVectors;
      this.numWords = wordIndex.size();
      this.vectorSize = wordVectors.length / this.numWords();
   }

   private Word2VecModel(final Tuple2 model) {
      this((Map)model._1(), (float[])model._2());
   }

   public Word2VecModel(final Map model) {
      this(Word2VecModel$.MODULE$.org$apache$spark$mllib$feature$Word2VecModel$$buildFromVecMap(model));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String formatVersionV1_0 = "1.0";
      private static final String classNameV1_0 = "org.apache.spark.mllib.feature.Word2VecModel";

      public String formatVersionV1_0() {
         return formatVersionV1_0;
      }

      public String classNameV1_0() {
         return classNameV1_0;
      }

      public Word2VecModel load(final SparkContext sc, final String path) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataFrame = spark.read().parquet(Loader$.MODULE$.dataPath(path));
         Loader$ var10000 = Loader$.MODULE$;
         StructType var10001 = dataFrame.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.feature.Word2VecModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
         Row[] dataArray = (Row[])dataFrame.select("word", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"vector"}))).collect();
         Map word2VecMap = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])dataArray), (i) -> new Tuple2(i.getString(0), i.getSeq(1).toArray(scala.reflect.ClassTag..MODULE$.Float())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
         return new Word2VecModel(word2VecMap);
      }

      public void save(final SparkContext sc, final String path, final Map model) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         int vectorSize = ((float[])model.values().head()).length;
         int numWords = model.size();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.classNameV1_0()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.formatVersionV1_0()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("vectorSize"), BoxesRunTime.boxToInteger(vectorSize)), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numWords"), BoxesRunTime.boxToInteger(numWords)), (x) -> $anonfun$save$5(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$2() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).write().text(Loader$.MODULE$.metadataPath(path));
         long bufferSize = org.apache.spark.util.Utils..MODULE$.byteStringAsBytes(spark.conf().get(org.apache.spark.internal.config.Kryo..MODULE$.KRYO_SERIALIZER_MAX_BUFFER_SIZE().key(), "64m"));
         long approxSize = (4L * (long)vectorSize + 15L) * (long)numWords;
         int nPartitions = (int)(approxSize / bufferSize + 1L);
         Seq dataArray = (Seq)model.toSeq().map((x0$1) -> {
            if (x0$1 != null) {
               String w = (String)x0$1._1();
               float[] v = (float[])x0$1._2();
               return new Word2VecModel$SaveLoadV1_0$Data(w, v);
            } else {
               throw new MatchError(x0$1);
            }
         });
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.feature.Word2VecModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(dataArray, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).repartition(nPartitions).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$5(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      public SaveLoadV1_0$() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
