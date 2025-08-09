package org.apache.spark.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ee\u0001B\r\u001b\u0001\u000eBQA\u0010\u0001\u0005\u0002}B\u0011\u0002\u0014\u0001A\u0002\u0003\u0007I\u0011A'\t\u0013E\u0003\u0001\u0019!a\u0001\n\u0003\u0011\u0006\"\u0003-\u0001\u0001\u0004\u0005\t\u0015)\u0003O\u0011%1\u0007\u00011AA\u0002\u0013\u0005q\rC\u0005i\u0001\u0001\u0007\t\u0019!C\u0001S\"I1\u000e\u0001a\u0001\u0002\u0003\u0006K!\u0011\u0005\b[\u0002\t\t\u0011\"\u0001o\u0011\u001d)\b!!A\u0005BYD\u0001b \u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0001\u0005\n\u0003\u0013\u0001\u0011\u0011!C\u0001\u0003\u0017A\u0011\"!\u0006\u0001\u0003\u0003%\t%a\u0006\t\u0013\u0005\u0015\u0002!!A\u0005\u0002\u0005\u001d\u0002\"CA\u0019\u0001\u0005\u0005I\u0011IA\u001a\u0011%\t9\u0004AA\u0001\n\u0003\nI\u0004C\u0005\u0002<\u0001\t\t\u0011\"\u0011\u0002>!I\u0011q\b\u0001\u0002\u0002\u0013\u0005\u0013\u0011I\u0004\n\u0003#R\u0012\u0011!E\u0001\u0003'2\u0001\"\u0007\u000e\u0002\u0002#\u0005\u0011Q\u000b\u0005\u0007}M!\t!!\u0019\t\u0013\u0005m2#!A\u0005F\u0005u\u0002\"CA2'\u0005\u0005I\u0011QA3\u0011%\t\u0019hEA\u0001\n\u0003\u000b)\bC\u0005\u0002\bN\t\t\u0011\"\u0003\u0002\n\n1a)\u001b;F]\u0012T!a\u0007\u000f\u0002\u00055d'BA\u000f\u001f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0002%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0005\u0019qN]4\u0004\u0001U\u0011AeQ\n\u0006\u0001\u0015ZsF\r\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00051jS\"\u0001\u000e\n\u00059R\"aB'M\u000bZ,g\u000e\u001e\t\u0003MAJ!!M\u0014\u0003\u000fA\u0013x\u000eZ;diB\u00111g\u000f\b\u0003ier!!\u000e\u001d\u000e\u0003YR!a\u000e\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013B\u0001\u001e(\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005i:\u0013A\u0002\u001fj]&$h\bF\u0001A!\ra\u0003!\u0011\t\u0003\u0005\u000ec\u0001\u0001B\u0003E\u0001\t\u0007QIA\u0001N#\t1\u0015\n\u0005\u0002'\u000f&\u0011\u0001j\n\u0002\b\u001d>$\b.\u001b8h!\ra#*Q\u0005\u0003\u0017j\u0011Q!T8eK2\f\u0011\"Z:uS6\fGo\u001c:\u0016\u00039\u00032\u0001L(B\u0013\t\u0001&DA\u0005FgRLW.\u0019;pe\u0006iQm\u001d;j[\u0006$xN]0%KF$\"a\u0015,\u0011\u0005\u0019\"\u0016BA+(\u0005\u0011)f.\u001b;\t\u000f]\u001b\u0011\u0011!a\u0001\u001d\u0006\u0019\u0001\u0010J\u0019\u0002\u0015\u0015\u001cH/[7bi>\u0014\b\u0005\u000b\u0002\u00055B\u00111\fZ\u0007\u00029*\u0011QLX\u0001\u000bC:tw\u000e^1uS>t'BA0a\u0003\u001dQ\u0017mY6t_:T!!\u00192\u0002\u0013\u0019\f7\u000f^3sq6d'\"A2\u0002\u0007\r|W.\u0003\u0002f9\nQ!j]8o\u0013\u001etwN]3\u0002\u000b5|G-\u001a7\u0016\u0003\u0005\u000b\u0011\"\\8eK2|F%Z9\u0015\u0005MS\u0007bB,\u0007\u0003\u0003\u0005\r!Q\u0001\u0007[>$W\r\u001c\u0011)\u0005\u001dQ\u0016\u0001B2paf,\"a\u001c:\u0015\u0003A\u00042\u0001\f\u0001r!\t\u0011%\u000fB\u0003E\u0011\t\u00071/\u0005\u0002GiB\u0019AFS9\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\bC\u0001=~\u001b\u0005I(B\u0001>|\u0003\u0011a\u0017M\\4\u000b\u0003q\fAA[1wC&\u0011a0\u001f\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\r\u0001c\u0001\u0014\u0002\u0006%\u0019\u0011qA\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u00055\u00111\u0003\t\u0004M\u0005=\u0011bAA\tO\t\u0019\u0011I\\=\t\u0011][\u0011\u0011!a\u0001\u0003\u0007\tq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00033\u0001b!a\u0007\u0002\"\u00055QBAA\u000f\u0015\r\tybJ\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0012\u0003;\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011\u0011FA\u0018!\r1\u00131F\u0005\u0004\u0003[9#a\u0002\"p_2,\u0017M\u001c\u0005\t/6\t\t\u00111\u0001\u0002\u000e\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r9\u0018Q\u0007\u0005\t/:\t\t\u00111\u0001\u0002\u0004\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0004\u0005AAo\\*ue&tw\rF\u0001x\u0003\u0019)\u0017/^1mgR!\u0011\u0011FA\"\u0011!9\u0016#!AA\u0002\u00055\u0001f\u0001\u0001\u0002HA!\u0011\u0011JA'\u001b\t\tYE\u0003\u0002^9%!\u0011qJA&\u0005!)eo\u001c7wS:<\u0017A\u0002$ji\u0016sG\r\u0005\u0002-'M!1#JA,!\u0011\tI&a\u0018\u000e\u0005\u0005m#bAA/w\u0006\u0011\u0011n\\\u0005\u0004y\u0005mCCAA*\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\t9'!\u001c\u0015\u0005\u0005%\u0004\u0003\u0002\u0017\u0001\u0003W\u00022AQA7\t\u0019!eC1\u0001\u0002pE\u0019a)!\u001d\u0011\t1R\u00151N\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\t9(!!\u0015\t\u0005%\u0012\u0011\u0010\u0005\n\u0003w:\u0012\u0011!a\u0001\u0003{\n1\u0001\u001f\u00131!\u0011a\u0003!a \u0011\u0007\t\u000b\t\t\u0002\u0004E/\t\u0007\u00111Q\t\u0004\r\u0006\u0015\u0005\u0003\u0002\u0017K\u0003\u007f\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a#\u0011\u0007a\fi)C\u0002\u0002\u0010f\u0014aa\u00142kK\u000e$\b"
)
public class FitEnd implements MLEvent, Product, Serializable {
   @JsonIgnore
   private Estimator estimator;
   @JsonIgnore
   private Model model;

   public static boolean unapply(final FitEnd x$0) {
      return FitEnd$.MODULE$.unapply(x$0);
   }

   public static FitEnd apply() {
      return FitEnd$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return MLEvent.logEvent$(this);
   }

   public Estimator estimator() {
      return this.estimator;
   }

   public void estimator_$eq(final Estimator x$1) {
      this.estimator = x$1;
   }

   public Model model() {
      return this.model;
   }

   public void model_$eq(final Model x$1) {
      this.model = x$1;
   }

   public FitEnd copy() {
      return new FitEnd();
   }

   public String productPrefix() {
      return "FitEnd";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof FitEnd;
   }

   public String productElementName(final int x$1) {
      return (String)Statics.ioobe(x$1);
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      return x$1 instanceof FitEnd && ((FitEnd)x$1).canEqual(this);
   }

   public FitEnd() {
      SparkListenerEvent.$init$(this);
      MLEvent.$init$(this);
      Product.$init$(this);
   }
}
