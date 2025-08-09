package org.apache.spark.util.random;

import java.io.Serializable;
import scala.Option;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b!\u0002\r\u001a\u0001e\u0019\u0003\u0002C\u001c\u0001\u0005\u0003\u0007I\u0011\u0001\u001d\t\u0011q\u0002!\u00111A\u0005\u0002uB\u0001b\u0011\u0001\u0003\u0002\u0003\u0006K!\u000f\u0005\t\t\u0002\u0011\t\u0019!C\u0001q!AQ\t\u0001BA\u0002\u0013\u0005a\t\u0003\u0005I\u0001\t\u0005\t\u0015)\u0003:\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u001dy\u0005A1A\u0005\u0002ACa\u0001\u0018\u0001!\u0002\u0013\t\u0006bB/\u0001\u0001\u0004%\tA\u0018\u0005\b?\u0002\u0001\r\u0011\"\u0001a\u0011\u0019\u0011\u0007\u0001)Q\u00053\"91\r\u0001a\u0001\n\u0003q\u0006b\u00023\u0001\u0001\u0004%\t!\u001a\u0005\u0007O\u0002\u0001\u000b\u0015B-\t\u000b!\u0004A\u0011A5\t\u000b5\u0004A\u0011\u00018\b\u0011QL\u0012\u0011!E\u00013U4\u0001\u0002G\r\u0002\u0002#\u0005\u0011D\u001e\u0005\u0006\u0013N!\tA \u0005\t\u007fN\t\n\u0011\"\u0001\u0002\u0002!I\u0011qC\n\u0012\u0002\u0013\u0005\u0011\u0011\u0001\u0005\n\u00033\u0019\u0012\u0011!C\u0005\u00037\u0011\u0001#Q2dKB$\u0018M\\2f%\u0016\u001cX\u000f\u001c;\u000b\u0005iY\u0012A\u0002:b]\u0012|WN\u0003\u0002\u001d;\u0005!Q\u000f^5m\u0015\tqr$A\u0003ta\u0006\u00148N\u0003\u0002!C\u00051\u0011\r]1dQ\u0016T\u0011AI\u0001\u0004_J<7c\u0001\u0001%UA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t1\u0011I\\=SK\u001a\u0004\"a\u000b\u001b\u000f\u00051\u0012dBA\u00172\u001b\u0005q#BA\u00181\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0014\n\u0005M2\u0013a\u00029bG.\fw-Z\u0005\u0003kY\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!a\r\u0014\u0002\u00119,X.\u0013;f[N,\u0012!\u000f\t\u0003KiJ!a\u000f\u0014\u0003\t1{gnZ\u0001\r]Vl\u0017\n^3ng~#S-\u001d\u000b\u0003}\u0005\u0003\"!J \n\u0005\u00013#\u0001B+oSRDqA\u0011\u0002\u0002\u0002\u0003\u0007\u0011(A\u0002yIE\n\u0011B\\;n\u0013R,Wn\u001d\u0011\u0002\u00179,X.Q2dKB$X\rZ\u0001\u0010]Vl\u0017iY2faR,Gm\u0018\u0013fcR\u0011ah\u0012\u0005\b\u0005\u0016\t\t\u00111\u0001:\u00031qW/\\!dG\u0016\u0004H/\u001a3!\u0003\u0019a\u0014N\\5u}Q\u00191*\u0014(\u0011\u00051\u0003Q\"A\r\t\u000f]:\u0001\u0013!a\u0001s!9Ai\u0002I\u0001\u0002\u0004I\u0014\u0001C<bSRd\u0015n\u001d;\u0016\u0003E\u00032AU,Z\u001b\u0005\u0019&B\u0001+V\u0003\u001diW\u000f^1cY\u0016T!A\u0016\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002Y'\nY\u0011I\u001d:bs\n+hMZ3s!\t)#,\u0003\u0002\\M\t1Ai\\;cY\u0016\f\u0011b^1ji2K7\u000f\u001e\u0011\u0002\u0017\u0005\u001c7-\u001a9u\u0005>,h\u000eZ\u000b\u00023\u0006y\u0011mY2faR\u0014u.\u001e8e?\u0012*\u0017\u000f\u0006\u0002?C\"9!iCA\u0001\u0002\u0004I\u0016\u0001D1dG\u0016\u0004HOQ8v]\u0012\u0004\u0013!D<bSRd\u0015n\u001d;C_VtG-A\txC&$H*[:u\u0005>,h\u000eZ0%KF$\"A\u00104\t\u000f\ts\u0011\u0011!a\u00013\u0006qq/Y5u\u0019&\u001cHOQ8v]\u0012\u0004\u0013AD1sK\n{WO\u001c3t\u000b6\u0004H/_\u000b\u0002UB\u0011Qe[\u0005\u0003Y\u001a\u0012qAQ8pY\u0016\fg.A\u0003nKJ<W\r\u0006\u0002?_\")\u0001/\u0005a\u0001c\u0006)q\u000e\u001e5feB\u0019QE]&\n\u0005M4#AB(qi&|g.\u0001\tBG\u000e,\u0007\u000f^1oG\u0016\u0014Vm];miB\u0011AjE\n\u0004'\u0011:\bC\u0001=~\u001b\u0005I(B\u0001>|\u0003\tIwNC\u0001}\u0003\u0011Q\u0017M^1\n\u0005UJH#A;\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019AK\u0002:\u0003\u000bY#!a\u0002\u0011\t\u0005%\u00111C\u0007\u0003\u0003\u0017QA!!\u0004\u0002\u0010\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003#1\u0013AC1o]>$\u0018\r^5p]&!\u0011QCA\u0006\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0001\u0003BA\u0010\u0003Ki!!!\t\u000b\u0007\u0005\r20\u0001\u0003mC:<\u0017\u0002BA\u0014\u0003C\u0011aa\u00142kK\u000e$\b"
)
public class AcceptanceResult implements Serializable {
   private long numItems;
   private long numAccepted;
   private final ArrayBuffer waitList;
   private double acceptBound;
   private double waitListBound;

   public static long $lessinit$greater$default$2() {
      return AcceptanceResult$.MODULE$.$lessinit$greater$default$2();
   }

   public static long $lessinit$greater$default$1() {
      return AcceptanceResult$.MODULE$.$lessinit$greater$default$1();
   }

   public long numItems() {
      return this.numItems;
   }

   public void numItems_$eq(final long x$1) {
      this.numItems = x$1;
   }

   public long numAccepted() {
      return this.numAccepted;
   }

   public void numAccepted_$eq(final long x$1) {
      this.numAccepted = x$1;
   }

   public ArrayBuffer waitList() {
      return this.waitList;
   }

   public double acceptBound() {
      return this.acceptBound;
   }

   public void acceptBound_$eq(final double x$1) {
      this.acceptBound = x$1;
   }

   public double waitListBound() {
      return this.waitListBound;
   }

   public void waitListBound_$eq(final double x$1) {
      this.waitListBound = x$1;
   }

   public boolean areBoundsEmpty() {
      return Double.isNaN(this.acceptBound()) || Double.isNaN(this.waitListBound());
   }

   public void merge(final Option other) {
      if (other.isDefined()) {
         this.waitList().$plus$plus$eq(((AcceptanceResult)other.get()).waitList());
         this.numAccepted_$eq(this.numAccepted() + ((AcceptanceResult)other.get()).numAccepted());
         this.numItems_$eq(this.numItems() + ((AcceptanceResult)other.get()).numItems());
      }
   }

   public AcceptanceResult(final long numItems, final long numAccepted) {
      this.numItems = numItems;
      this.numAccepted = numAccepted;
      super();
      this.waitList = new ArrayBuffer();
      this.acceptBound = Double.NaN;
      this.waitListBound = Double.NaN;
   }
}
