package org.apache.spark.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.sql.Dataset;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f\u0001B\r\u001b\u0001\u000eBQA\u0010\u0001\u0005\u0002}B\u0011\u0002\u0014\u0001A\u0002\u0003\u0007I\u0011A'\t\u0013E\u0003\u0001\u0019!a\u0001\n\u0003\u0011\u0006\"\u0003-\u0001\u0001\u0004\u0005\t\u0015)\u0003O\u0011%1\u0007\u00011AA\u0002\u0013\u0005q\rC\u0005y\u0001\u0001\u0007\t\u0019!C\u0001s\"I!\u000f\u0001a\u0001\u0002\u0003\u0006K\u0001\u001b\u0005\t\u007f\u0002\t\t\u0011\"\u0001\u0002\u0002!I\u0011q\u0002\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0003\u0005\n\u0003G\u0001\u0011\u0011!C\u0001\u0003KA\u0011\"!\f\u0001\u0003\u0003%\t!a\f\t\u0013\u0005M\u0002!!A\u0005B\u0005U\u0002\"CA\"\u0001\u0005\u0005I\u0011AA#\u0011%\ty\u0005AA\u0001\n\u0003\n\t\u0006C\u0005\u0002V\u0001\t\t\u0011\"\u0011\u0002X!I\u0011\u0011\f\u0001\u0002\u0002\u0013\u0005\u00131\f\u0005\n\u0003;\u0002\u0011\u0011!C!\u0003?:\u0011\"a\u001c\u001b\u0003\u0003E\t!!\u001d\u0007\u0011eQ\u0012\u0011!E\u0001\u0003gBaAP\n\u0005\u0002\u0005}\u0004\"CA-'\u0005\u0005IQIA.\u0011%\t\tiEA\u0001\n\u0003\u000b\u0019\tC\u0005\u0002\u0012N\t\t\u0011\"!\u0002\u0014\"I\u0011QU\n\u0002\u0002\u0013%\u0011q\u0015\u0002\t\r&$8\u000b^1si*\u00111\u0004H\u0001\u0003[2T!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001+\t!3iE\u0003\u0001K-z#\u0007\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsE\u0001\u0004B]f\u0014VM\u001a\t\u0003Y5j\u0011AG\u0005\u0003]i\u0011q!\u0014'Fm\u0016tG\u000f\u0005\u0002'a%\u0011\u0011g\n\u0002\b!J|G-^2u!\t\u00194H\u0004\u00025s9\u0011Q\u0007O\u0007\u0002m)\u0011qGI\u0001\u0007yI|w\u000e\u001e \n\u0003!J!AO\u0014\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003u\u001d\na\u0001P5oSRtD#\u0001!\u0011\u00071\u0002\u0011\t\u0005\u0002C\u00072\u0001A!\u0002#\u0001\u0005\u0004)%!A'\u0012\u0005\u0019K\u0005C\u0001\u0014H\u0013\tAuEA\u0004O_RD\u0017N\\4\u0011\u00071R\u0015)\u0003\u0002L5\t)Qj\u001c3fY\u0006IQm\u001d;j[\u0006$xN]\u000b\u0002\u001dB\u0019AfT!\n\u0005AS\"!C#ti&l\u0017\r^8s\u00035)7\u000f^5nCR|'o\u0018\u0013fcR\u00111K\u0016\t\u0003MQK!!V\u0014\u0003\tUs\u0017\u000e\u001e\u0005\b/\u000e\t\t\u00111\u0001O\u0003\rAH%M\u0001\u000bKN$\u0018.\\1u_J\u0004\u0003F\u0001\u0003[!\tYF-D\u0001]\u0015\tif,\u0001\u0006b]:|G/\u0019;j_:T!a\u00181\u0002\u000f)\f7m[:p]*\u0011\u0011MY\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011aY\u0001\u0004G>l\u0017BA3]\u0005)Q5o\u001c8JO:|'/Z\u0001\bI\u0006$\u0018m]3u+\u0005A\u0007GA5q!\rQWn\\\u0007\u0002W*\u0011A\u000eH\u0001\u0004gFd\u0017B\u00018l\u0005\u001d!\u0015\r^1tKR\u0004\"A\u00119\u0005\u0013E<\u0011\u0011!A\u0001\u0006\u0003!(aA0%g\u0005AA-\u0019;bg\u0016$\b\u0005\u000b\u0002\b5F\u0011a)\u001e\t\u0003MYL!a^\u0014\u0003\u0007\u0005s\u00170A\u0006eCR\f7/\u001a;`I\u0015\fHCA*{\u0011\u001d9f!!AA\u0002m\u0004$\u0001 @\u0011\u0007)lW\u0010\u0005\u0002C}\u0012I\u0011O_A\u0001\u0002\u0003\u0015\t\u0001^\u0001\u0005G>\u0004\u00180\u0006\u0003\u0002\u0004\u0005%ACAA\u0003!\u0011a\u0003!a\u0002\u0011\u0007\t\u000bI\u0001\u0002\u0004E\u0011\t\u0007\u00111B\t\u0004\r\u00065\u0001\u0003\u0002\u0017K\u0003\u000f\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\n!\u0011\t)\"a\b\u000e\u0005\u0005]!\u0002BA\r\u00037\tA\u0001\\1oO*\u0011\u0011QD\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\"\u0005]!AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002(A\u0019a%!\u000b\n\u0007\u0005-rEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002v\u0003cA\u0001bV\u0006\u0002\u0002\u0003\u0007\u0011qE\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0007\t\u0006\u0003s\ty$^\u0007\u0003\u0003wQ1!!\u0010(\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0003\nYD\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA$\u0003\u001b\u00022AJA%\u0013\r\tYe\n\u0002\b\u0005>|G.Z1o\u0011\u001d9V\"!AA\u0002U\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111CA*\u0011!9f\"!AA\u0002\u0005\u001d\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u001d\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005M\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0002H\u0005\u0005\u0004bB,\u0012\u0003\u0003\u0005\r!\u001e\u0015\u0004\u0001\u0005\u0015\u0004\u0003BA4\u0003Wj!!!\u001b\u000b\u0005uc\u0012\u0002BA7\u0003S\u0012\u0001\"\u0012<pYZLgnZ\u0001\t\r&$8\u000b^1siB\u0011AfE\n\u0005'\u0015\n)\b\u0005\u0003\u0002x\u0005uTBAA=\u0015\u0011\tY(a\u0007\u0002\u0005%|\u0017b\u0001\u001f\u0002zQ\u0011\u0011\u0011O\u0001\u0006CB\u0004H._\u000b\u0005\u0003\u000b\u000bY\t\u0006\u0002\u0002\bB!A\u0006AAE!\r\u0011\u00151\u0012\u0003\u0007\tZ\u0011\r!!$\u0012\u0007\u0019\u000by\t\u0005\u0003-\u0015\u0006%\u0015aB;oCB\u0004H._\u000b\u0005\u0003+\u000by\n\u0006\u0003\u0002H\u0005]\u0005\"CAM/\u0005\u0005\t\u0019AAN\u0003\rAH\u0005\r\t\u0005Y\u0001\ti\nE\u0002C\u0003?#a\u0001R\fC\u0002\u0005\u0005\u0016c\u0001$\u0002$B!AFSAO\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u000b\u0005\u0003\u0002\u0016\u0005-\u0016\u0002BAW\u0003/\u0011aa\u00142kK\u000e$\b"
)
public class FitStart implements MLEvent, Product, Serializable {
   @JsonIgnore
   private Estimator estimator;
   @JsonIgnore
   private Dataset dataset;

   public static boolean unapply(final FitStart x$0) {
      return FitStart$.MODULE$.unapply(x$0);
   }

   public static FitStart apply() {
      return FitStart$.MODULE$.apply();
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

   public Dataset dataset() {
      return this.dataset;
   }

   public void dataset_$eq(final Dataset x$1) {
      this.dataset = x$1;
   }

   public FitStart copy() {
      return new FitStart();
   }

   public String productPrefix() {
      return "FitStart";
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
      return x$1 instanceof FitStart;
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
      return x$1 instanceof FitStart && ((FitStart)x$1).canEqual(this);
   }

   public FitStart() {
      SparkListenerEvent.$init$(this);
      MLEvent.$init$(this);
      Product.$init$(this);
   }
}
