package org.apache.spark.graphx.lib;

import java.io.Serializable;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%t!B\u0010!\u0011\u0003Yc!B\u0017!\u0011\u0003q\u0003\"B\u001b\u0002\t\u00031d\u0001B\u001c\u0002\u0001aB\u0001\"R\u0002\u0003\u0002\u0004%\tA\u0012\u0005\t\u0015\u000e\u0011\t\u0019!C\u0001\u0017\"A\u0011k\u0001B\u0001B\u0003&q\t\u0003\u0005S\u0007\t\u0005\r\u0011\"\u0001G\u0011!\u00196A!a\u0001\n\u0003!\u0006\u0002\u0003,\u0004\u0005\u0003\u0005\u000b\u0015B$\t\u0011]\u001b!\u00111A\u0005\u0002aC\u0001\u0002X\u0002\u0003\u0002\u0004%\t!\u0018\u0005\t?\u000e\u0011\t\u0011)Q\u00053\"A\u0001m\u0001BA\u0002\u0013\u0005\u0001\f\u0003\u0005b\u0007\t\u0005\r\u0011\"\u0001c\u0011!!7A!A!B\u0013I\u0006\u0002C3\u0004\u0005\u0003\u0007I\u0011\u0001-\t\u0011\u0019\u001c!\u00111A\u0005\u0002\u001dD\u0001\"[\u0002\u0003\u0002\u0003\u0006K!\u0017\u0005\tU\u000e\u0011\t\u0019!C\u00011\"A1n\u0001BA\u0002\u0013\u0005A\u000e\u0003\u0005o\u0007\t\u0005\t\u0015)\u0003Z\u0011!y7A!a\u0001\n\u0003A\u0006\u0002\u00039\u0004\u0005\u0003\u0007I\u0011A9\t\u0011M\u001c!\u0011!Q!\neC\u0001\u0002^\u0002\u0003\u0002\u0004%\t\u0001\u0017\u0005\tk\u000e\u0011\t\u0019!C\u0001m\"A\u0001p\u0001B\u0001B\u0003&\u0011\fC\u00036\u0007\u0011\u0005\u0011\u0010C\u0004\u0002\n\u0005!\t!a\u0003\t\u000f\u0005\u0005\u0013\u0001\"\u0003\u0002D\u0005Y1K\u0016#QYV\u001c\b\u000b\\;t\u0015\t\t#%A\u0002mS\nT!a\t\u0013\u0002\r\u001d\u0014\u0018\r\u001d5y\u0015\t)c%A\u0003ta\u0006\u00148N\u0003\u0002(Q\u00051\u0011\r]1dQ\u0016T\u0011!K\u0001\u0004_J<7\u0001\u0001\t\u0003Y\u0005i\u0011\u0001\t\u0002\f'Z#\u0005\u000b\\;t!2,8o\u0005\u0002\u0002_A\u0011\u0001gM\u0007\u0002c)\t!'A\u0003tG\u0006d\u0017-\u0003\u00025c\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\u0016\u0003\t\r{gNZ\n\u0004\u0007=J\u0004C\u0001\u001eC\u001d\tY\u0004I\u0004\u0002=\u007f5\tQH\u0003\u0002?U\u00051AH]8pizJ\u0011AM\u0005\u0003\u0003F\nq\u0001]1dW\u0006<W-\u0003\u0002D\t\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011)M\u0001\u0005e\u0006t7.F\u0001H!\t\u0001\u0004*\u0003\u0002Jc\t\u0019\u0011J\u001c;\u0002\u0011I\fgn[0%KF$\"\u0001T(\u0011\u0005Aj\u0015B\u0001(2\u0005\u0011)f.\u001b;\t\u000fA+\u0011\u0011!a\u0001\u000f\u0006\u0019\u0001\u0010J\u0019\u0002\u000bI\fgn\u001b\u0011\u0002\u00115\f\u00070\u0013;feN\fA\"\\1y\u0013R,'o]0%KF$\"\u0001T+\t\u000fAC\u0011\u0011!a\u0001\u000f\u0006IQ.\u0019=Ji\u0016\u00148\u000fI\u0001\u0007[&tg+\u00197\u0016\u0003e\u0003\"\u0001\r.\n\u0005m\u000b$A\u0002#pk\ndW-\u0001\u0006nS:4\u0016\r\\0%KF$\"\u0001\u00140\t\u000fA[\u0011\u0011!a\u00013\u00069Q.\u001b8WC2\u0004\u0013AB7bqZ\u000bG.\u0001\u0006nCb4\u0016\r\\0%KF$\"\u0001T2\t\u000fAs\u0011\u0011!a\u00013\u00069Q.\u0019=WC2\u0004\u0013AB4b[6\f\u0017'\u0001\u0006hC6l\u0017-M0%KF$\"\u0001\u00145\t\u000fA\u000b\u0012\u0011!a\u00013\u00069q-Y7nCF\u0002\u0013AB4b[6\f''\u0001\u0006hC6l\u0017MM0%KF$\"\u0001T7\t\u000fA#\u0012\u0011!a\u00013\u00069q-Y7nCJ\u0002\u0013AB4b[6\fg'\u0001\u0006hC6l\u0017MN0%KF$\"\u0001\u0014:\t\u000fA;\u0012\u0011!a\u00013\u00069q-Y7nCZ\u0002\u0013AB4b[6\fw'\u0001\u0006hC6l\u0017mN0%KF$\"\u0001T<\t\u000fAS\u0012\u0011!a\u00013\u00069q-Y7nC^\u0002C#\u0004>}{z|\u0018\u0011AA\u0002\u0003\u000b\t9\u0001\u0005\u0002|\u00075\t\u0011\u0001C\u0003F9\u0001\u0007q\tC\u0003S9\u0001\u0007q\tC\u0003X9\u0001\u0007\u0011\fC\u0003a9\u0001\u0007\u0011\fC\u0003f9\u0001\u0007\u0011\fC\u0003k9\u0001\u0007\u0011\fC\u0003p9\u0001\u0007\u0011\fC\u0003u9\u0001\u0007\u0011,A\u0002sk:$b!!\u0004\u0002(\u0005u\u0002C\u0002\u0019\u0002\u0010\u0005M\u0011,C\u0002\u0002\u0012E\u0012a\u0001V;qY\u0016\u0014\u0004cBA\u000b\u0003/\tY\"W\u0007\u0002E%\u0019\u0011\u0011\u0004\u0012\u0003\u000b\u001d\u0013\u0018\r\u001d5\u0011\u0013A\ni\"!\t\u0002\"eK\u0016bAA\u0010c\t1A+\u001e9mKR\u0002B\u0001MA\u00123&\u0019\u0011QE\u0019\u0003\u000b\u0005\u0013(/Y=\t\u000f\u0005%R\u00041\u0001\u0002,\u0005)Q\rZ4fgB1\u0011QFA\u001a\u0003oi!!a\f\u000b\u0007\u0005EB%A\u0002sI\u0012LA!!\u000e\u00020\t\u0019!\u000b\u0012#\u0011\u000b\u0005U\u0011\u0011H-\n\u0007\u0005m\"E\u0001\u0003FI\u001e,\u0007BBA ;\u0001\u0007!0\u0001\u0003d_:4\u0017aC7bi\u0016\u0014\u0018.\u00197ju\u0016$2\u0001TA#\u0011\u001d\t9E\ba\u0001\u0003\u0013\n\u0011a\u001a\u0019\u0007\u0003\u0017\n\t&!\u001a\u0011\u0011\u0005U\u0011qCA'\u0003G\u0002B!a\u0014\u0002R1\u0001A\u0001DA*\u0003\u000b\n\t\u0011!A\u0003\u0002\u0005U#aA0%cE!\u0011qKA/!\r\u0001\u0014\u0011L\u0005\u0004\u00037\n$a\u0002(pi\"Lgn\u001a\t\u0004a\u0005}\u0013bAA1c\t\u0019\u0011I\\=\u0011\t\u0005=\u0013Q\r\u0003\r\u0003O\n)%!A\u0001\u0002\u000b\u0005\u0011Q\u000b\u0002\u0004?\u0012\u0012\u0004"
)
public final class SVDPlusPlus {
   public static Tuple2 run(final RDD edges, final Conf conf) {
      return SVDPlusPlus$.MODULE$.run(edges, conf);
   }

   public static class Conf implements Serializable {
      private int rank;
      private int maxIters;
      private double minVal;
      private double maxVal;
      private double gamma1;
      private double gamma2;
      private double gamma6;
      private double gamma7;

      public int rank() {
         return this.rank;
      }

      public void rank_$eq(final int x$1) {
         this.rank = x$1;
      }

      public int maxIters() {
         return this.maxIters;
      }

      public void maxIters_$eq(final int x$1) {
         this.maxIters = x$1;
      }

      public double minVal() {
         return this.minVal;
      }

      public void minVal_$eq(final double x$1) {
         this.minVal = x$1;
      }

      public double maxVal() {
         return this.maxVal;
      }

      public void maxVal_$eq(final double x$1) {
         this.maxVal = x$1;
      }

      public double gamma1() {
         return this.gamma1;
      }

      public void gamma1_$eq(final double x$1) {
         this.gamma1 = x$1;
      }

      public double gamma2() {
         return this.gamma2;
      }

      public void gamma2_$eq(final double x$1) {
         this.gamma2 = x$1;
      }

      public double gamma6() {
         return this.gamma6;
      }

      public void gamma6_$eq(final double x$1) {
         this.gamma6 = x$1;
      }

      public double gamma7() {
         return this.gamma7;
      }

      public void gamma7_$eq(final double x$1) {
         this.gamma7 = x$1;
      }

      public Conf(final int rank, final int maxIters, final double minVal, final double maxVal, final double gamma1, final double gamma2, final double gamma6, final double gamma7) {
         this.rank = rank;
         this.maxIters = maxIters;
         this.minVal = minVal;
         this.maxVal = maxVal;
         this.gamma1 = gamma1;
         this.gamma2 = gamma2;
         this.gamma6 = gamma6;
         this.gamma7 = gamma7;
         super();
      }
   }
}
