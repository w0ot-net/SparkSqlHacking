package org.apache.spark.mllib.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.Utils.;
import scala.Function1;
import scala.collection.Iterable;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015a\u0001\u0002\u0011\"\u00011B\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0011)A\u0005\u0003\")Q\t\u0001C\u0001\r\"9!\n\u0001a\u0001\n\u0013Y\u0005bB(\u0001\u0001\u0004%I\u0001\u0015\u0005\u0007-\u0002\u0001\u000b\u0015\u0002'\t\u000f]\u0003\u0001\u0019!C\u00051\"9\u0011\r\u0001a\u0001\n\u0013\u0011\u0007B\u00023\u0001A\u0003&\u0011\fC\u0003F\u0001\u0011\u0005Q\rC\u0003p\u0001\u0011\u0005\u0001\u000fC\u0003x\u0001\u0011\u0005\u0001\u0010C\u0003|\u0001\u0011\u0005A\u0010C\u0004\u0002\b\u0001!I!!\u0003\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u0011q\b\u0001\u0005\n\u0005\u0005\u0003bBA\t\u0001\u0011\u0005\u0011\u0011\r\u0005\b\u0003#\u0001A\u0011AA?\u0011\u001d\t\t\u0002\u0001C\u0001\u0003O;q!!5\"\u0011\u0003\t\u0019N\u0002\u0004!C!\u0005\u0011Q\u001b\u0005\u0007\u000bV!\t!!9\t\u0013\u0005\rXC1A\u0005\u0002UA\u0006bBAs+\u0001\u0006I!\u0017\u0005\n\u0003O,\"\u0019!C\u0001+aCq!!;\u0016A\u0003%\u0011\fC\u0005\u0002lV\u0011\r\u0011\"\u0001&\u0001\"9\u0011Q^\u000b!\u0002\u0013\t\u0005\u0002CAx+\u0011\u0005Q#!=\t\u0011\u0005UX\u0003\"\u0001&\u0003oD\u0011\"a?\u0016\u0003\u0003%I!!@\u0003\u0013!\u000b7\u000f[5oOR3%B\u0001\u0012$\u0003\u001d1W-\u0019;ve\u0016T!\u0001J\u0013\u0002\u000b5dG.\u001b2\u000b\u0005\u0019:\u0013!B:qCJ\\'B\u0001\u0015*\u0003\u0019\t\u0007/Y2iK*\t!&A\u0002pe\u001e\u001c\u0001aE\u0002\u0001[M\u0002\"AL\u0019\u000e\u0003=R\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\u0012a!\u00118z%\u00164\u0007C\u0001\u001b=\u001d\t)$H\u0004\u00027s5\tqG\u0003\u00029W\u00051AH]8pizJ\u0011\u0001M\u0005\u0003w=\nq\u0001]1dW\u0006<W-\u0003\u0002>}\ta1+\u001a:jC2L'0\u00192mK*\u00111hL\u0001\f]Vlg)Z1ukJ,7/F\u0001B!\tq#)\u0003\u0002D_\t\u0019\u0011J\u001c;\u0002\u00199,XNR3biV\u0014Xm\u001d\u0011\u0002\rqJg.\u001b;?)\t9\u0015\n\u0005\u0002I\u00015\t\u0011\u0005C\u0003@\u0007\u0001\u0007\u0011)\u0001\u0004cS:\f'/_\u000b\u0002\u0019B\u0011a&T\u0005\u0003\u001d>\u0012qAQ8pY\u0016\fg.\u0001\u0006cS:\f'/_0%KF$\"!\u0015+\u0011\u00059\u0012\u0016BA*0\u0005\u0011)f.\u001b;\t\u000fU+\u0011\u0011!a\u0001\u0019\u0006\u0019\u0001\u0010J\u0019\u0002\u000f\tLg.\u0019:zA\u0005i\u0001.Y:i\u00032<wN]5uQ6,\u0012!\u0017\t\u00035zs!a\u0017/\u0011\u0005Yz\u0013BA/0\u0003\u0019\u0001&/\u001a3fM&\u0011q\f\u0019\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005u{\u0013!\u00055bg\"\fEnZ8sSRDWn\u0018\u0013fcR\u0011\u0011k\u0019\u0005\b+\"\t\t\u00111\u0001Z\u00039A\u0017m\u001d5BY\u001e|'/\u001b;i[\u0002\"\u0012a\u0012\u0015\u0004\u0015\u001dl\u0007C\u00015l\u001b\u0005I'B\u00016&\u0003)\tgN\\8uCRLwN\\\u0005\u0003Y&\u0014QaU5oG\u0016\f\u0013A\\\u0001\u0006c9\nd\u0006M\u0001\ng\u0016$()\u001b8bef$\"!\u001d:\u000e\u0003\u0001AQa]\u0006A\u00021\u000bQA^1mk\u0016D3aC4vC\u00051\u0018!\u0002\u001a/a9\u0002\u0014\u0001E:fi\"\u000b7\u000f[!mO>\u0014\u0018\u000e\u001e5n)\t\t\u0018\u0010C\u0003t\u0019\u0001\u0007\u0011\fK\u0002\rOV\fq!\u001b8eKb|e\r\u0006\u0002B{\")a0\u0004a\u0001\u007f\u0006!A/\u001a:n!\rq\u0013\u0011A\u0005\u0004\u0003\u0007y#aA!os\"\u001aQbZ7\u0002\u001f\u001d,G\u000fS1tQ\u001a+hn\u0019;j_:,\"!a\u0003\u0011\u000b9\nia`!\n\u0007\u0005=qFA\u0005Gk:\u001cG/[8oc\u0005IAO]1og\u001a|'/\u001c\u000b\u0005\u0003+\t\t\u0003\u0005\u0003\u0002\u0018\u0005uQBAA\r\u0015\r\tYbI\u0001\u0007Y&t\u0017\r\\4\n\t\u0005}\u0011\u0011\u0004\u0002\u0007-\u0016\u001cGo\u001c:\t\u000f\u0005\rr\u00021\u0001\u0002&\u0005AAm\\2v[\u0016tG\u000f\r\u0003\u0002(\u0005E\u0002#\u0002\u001b\u0002*\u00055\u0012bAA\u0016}\tA\u0011\n^3sC\ndW\r\u0005\u0003\u00020\u0005EB\u0002\u0001\u0003\r\u0003g\t\t#!A\u0001\u0002\u000b\u0005\u0011Q\u0007\u0002\u0004?\u0012\n\u0014cAA\u001c\u007fB\u0019a&!\u000f\n\u0007\u0005mrFA\u0004O_RD\u0017N\\4)\u0007=9W.A\u0007ue\u0006t7OZ8s[&k\u0007\u000f\u001c\u000b\u0005\u0003\u0007\n)\u0006E\u00035\u0003\u000b\nI%C\u0002\u0002Hy\u00121aU3r!\u0019q\u00131J!\u0002P%\u0019\u0011QJ\u0018\u0003\rQ+\b\u000f\\33!\rq\u0013\u0011K\u0005\u0004\u0003'z#A\u0002#pk\ndW\rC\u0004\u0002$A\u0001\r!a\u00161\t\u0005e\u0013Q\f\t\u0006i\u0005%\u00121\f\t\u0005\u0003_\ti\u0006\u0002\u0007\u0002`\u0005U\u0013\u0011!A\u0001\u0006\u0003\t)DA\u0002`II\"B!!\u0006\u0002d!9\u00111E\tA\u0002\u0005\u0015\u0004\u0007BA4\u0003o\u0002b!!\u001b\u0002t\u0005UTBAA6\u0015\u0011\ti'a\u001c\u0002\t1\fgn\u001a\u0006\u0003\u0003c\nAA[1wC&!\u00111FA6!\u0011\ty#a\u001e\u0005\u0019\u0005e\u00141MA\u0001\u0002\u0003\u0015\t!!\u000e\u0003\u0007}#3\u0007K\u0002\u0012O6,B!a \u0002\u0016R!\u0011\u0011QAG!\u0019\t\u0019)!#\u0002\u00165\u0011\u0011Q\u0011\u0006\u0004\u0003\u000f+\u0013a\u0001:eI&!\u00111RAC\u0005\r\u0011F\t\u0012\u0005\b\u0003\u001f\u0013\u0002\u0019AAI\u0003\u001d!\u0017\r^1tKR\u0004b!a!\u0002\n\u0006M\u0005\u0003BA\u0018\u0003+#q!a&\u0013\u0005\u0004\tIJA\u0001E#\u0011\t9$a'1\t\u0005u\u0015\u0011\u0015\t\u0006i\u0005%\u0012q\u0014\t\u0005\u0003_\t\t\u000b\u0002\u0007\u0002$\u0006U\u0015\u0011!A\u0001\u0006\u0003\t)DA\u0002`IQB3AE4n+\u0011\tI+a0\u0015\t\u0005-\u0016\u0011\u0018\t\u0007\u0003[\u000b),!\u0006\u000e\u0005\u0005=&\u0002BA9\u0003cS1!a-&\u0003\r\t\u0007/[\u0005\u0005\u0003o\u000byKA\u0004KCZ\f'\u000b\u0012#\t\u000f\u0005=5\u00031\u0001\u0002<B1\u0011QVA[\u0003{\u0003B!a\f\u0002@\u00129\u0011qS\nC\u0002\u0005\u0005\u0017\u0003BA\u001c\u0003\u0007\u0004D!!2\u0002JB1\u0011\u0011NA:\u0003\u000f\u0004B!a\f\u0002J\u0012a\u00111ZA`\u0003\u0003\u0005\tQ!\u0001\u00026\t\u0019q\fJ\u001b)\u0007M9W\u000eK\u0002\u0001O6\f\u0011\u0002S1tQ&tw\r\u0016$\u0011\u0005!+2\u0003B\u000b.\u0003/\u0004B!!7\u0002`6\u0011\u00111\u001c\u0006\u0005\u0003;\fy'\u0001\u0002j_&\u0019Q(a7\u0015\u0005\u0005M\u0017A\u0002(bi&4X-A\u0004OCRLg/\u001a\u0011\u0002\u000f5+(/\\;sg\u0005AQ*\u001e:nkJ\u001c\u0004%\u0001\u0003tK\u0016$\u0017!B:fK\u0012\u0004\u0013A\u00038bi&4X\rS1tQR\u0019\u0011)a=\t\u000byl\u0002\u0019A@\u0002\u00175,(/\\;sg!\u000b7\u000f\u001b\u000b\u0004\u0003\u0006e\b\"\u0002@\u001f\u0001\u0004y\u0018\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u0000!\u0011\tIG!\u0001\n\t\t\r\u00111\u000e\u0002\u0007\u001f\nTWm\u0019;"
)
public class HashingTF implements Serializable {
   private final int numFeatures;
   private boolean binary;
   private String hashAlgorithm;

   public int numFeatures() {
      return this.numFeatures;
   }

   private boolean binary() {
      return this.binary;
   }

   private void binary_$eq(final boolean x$1) {
      this.binary = x$1;
   }

   private String hashAlgorithm() {
      return this.hashAlgorithm;
   }

   private void hashAlgorithm_$eq(final String x$1) {
      this.hashAlgorithm = x$1;
   }

   public HashingTF setBinary(final boolean value) {
      this.binary_$eq(value);
      return this;
   }

   public HashingTF setHashAlgorithm(final String value) {
      this.hashAlgorithm_$eq(value);
      return this;
   }

   public int indexOf(final Object term) {
      return .MODULE$.nonNegativeMod(BoxesRunTime.unboxToInt(this.getHashFunction().apply(term)), this.numFeatures());
   }

   private Function1 getHashFunction() {
      String var2 = this.hashAlgorithm();
      String var10000 = HashingTF$.MODULE$.Murmur3();
      if (var10000 == null) {
         if (var2 == null) {
            return (term) -> BoxesRunTime.boxToInteger($anonfun$getHashFunction$1(term));
         }
      } else if (var10000.equals(var2)) {
         return (term) -> BoxesRunTime.boxToInteger($anonfun$getHashFunction$1(term));
      }

      var10000 = HashingTF$.MODULE$.Native();
      if (var10000 == null) {
         if (var2 == null) {
            return (term) -> BoxesRunTime.boxToInteger($anonfun$getHashFunction$2(term));
         }
      } else if (var10000.equals(var2)) {
         return (term) -> BoxesRunTime.boxToInteger($anonfun$getHashFunction$2(term));
      }

      throw new IllegalArgumentException("HashingTF does not recognize hash algorithm " + this.hashAlgorithm());
   }

   public Vector transform(final Iterable document) {
      Seq seq = this.transformImpl(document);
      return Vectors$.MODULE$.sparse(this.numFeatures(), seq);
   }

   private Seq transformImpl(final Iterable document) {
      HashMap termFrequencies = scala.collection.mutable.HashMap..MODULE$.empty();
      Function1 setTF = this.binary() ? (i) -> (double)1.0F : (i) -> BoxesRunTime.unboxToDouble(termFrequencies.getOrElse(BoxesRunTime.boxToInteger(i), (JFunction0.mcD.sp)() -> (double)0.0F)) + (double)1.0F;
      Function1 hashFunc = this.getHashFunction();
      document.foreach((term) -> {
         int i = .MODULE$.nonNegativeMod(BoxesRunTime.unboxToInt(hashFunc.apply(term)), this.numFeatures());
         return termFrequencies.put(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToDouble(setTF.apply$mcDI$sp(i)));
      });
      return termFrequencies.toSeq();
   }

   public Vector transform(final java.lang.Iterable document) {
      return this.transform(scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(document).asScala());
   }

   public RDD transform(final RDD dataset) {
      return dataset.map((document) -> this.transform(document), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public JavaRDD transform(final JavaRDD dataset) {
      return dataset.rdd().map((document) -> this.transform(document), scala.reflect.ClassTag..MODULE$.apply(Vector.class)).toJavaRDD();
   }

   // $FF: synthetic method
   public static final int $anonfun$getHashFunction$1(final Object term) {
      return HashingTF$.MODULE$.murmur3Hash(term);
   }

   // $FF: synthetic method
   public static final int $anonfun$getHashFunction$2(final Object term) {
      return HashingTF$.MODULE$.nativeHash(term);
   }

   public HashingTF(final int numFeatures) {
      this.numFeatures = numFeatures;
      this.binary = false;
      this.hashAlgorithm = HashingTF$.MODULE$.Murmur3();
   }

   public HashingTF() {
      this(1048576);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
