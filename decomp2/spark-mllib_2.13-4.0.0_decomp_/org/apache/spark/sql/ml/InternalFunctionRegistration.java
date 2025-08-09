package org.apache.spark.sql.ml;

import org.apache.spark.sql.SparkSessionExtensions;
import org.apache.spark.sql.SparkSessionExtensionsProvider;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A<Qa\u0004\t\t\u0002m1Q!\b\t\t\u0002yAQ!J\u0001\u0005\u0002\u0019BQaJ\u0001\u0005\u0002!BQ\u0001L\u0001\u0005\n5BQaP\u0001\u0005\n\u0001Cq!X\u0001C\u0002\u0013%a\f\u0003\u0004`\u0003\u0001\u0006I\u0001\u000f\u0005\bA\u0006\u0011\r\u0011\"\u0003_\u0011\u0019\t\u0017\u0001)A\u0005q!9!-\u0001b\u0001\n\u0013q\u0006BB2\u0002A\u0003%\u0001H\u0002\u0003\u001e!\u0001!\u0007\"B\u0013\r\t\u0003I\u0007\"B\u0014\r\t\u0003Z\u0017\u0001H%oi\u0016\u0014h.\u00197Gk:\u001cG/[8o%\u0016<\u0017n\u001d;sCRLwN\u001c\u0006\u0003#I\t!!\u001c7\u000b\u0005M!\u0012aA:rY*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005q\tQ\"\u0001\t\u00039%sG/\u001a:oC24UO\\2uS>t'+Z4jgR\u0014\u0018\r^5p]N\u0011\u0011a\b\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005Y\u0012!B1qa2LH#A\u0015\u0011\u0005\u0001R\u0013BA\u0016\"\u0005\u0011)f.\u001b;\u0002\u0013%tgo\\6f+\u00124Gc\u0001\u00187{A\u0011q\u0006N\u0007\u0002a)\u0011\u0011GM\u0001\fKb\u0004(/Z:tS>t7O\u0003\u00024%\u0005A1-\u0019;bYf\u001cH/\u0003\u00026a\tQQ\t\u001f9sKN\u001c\u0018n\u001c8\t\u000b]\"\u0001\u0019\u0001\u001d\u0002\u0007U$g\r\u0005\u0002:w5\t!H\u0003\u00022%%\u0011AH\u000f\u0002\u0014+N,'\u000fR3gS:,GMR;oGRLwN\u001c\u0005\u0006}\u0011\u0001\rAL\u0001\u0002K\u0006\u0001\"/Z4jgR,'OR;oGRLwN\u001c\u000b\u0003\u0003N#\"!\u000b\"\t\u000b\r+\u0001\u0019\u0001#\u0002\u000f\t,\u0018\u000e\u001c3feB!\u0001%R$/\u0013\t1\u0015EA\u0005Gk:\u001cG/[8ocA\u0019\u0001\n\u0015\u0018\u000f\u0005%seB\u0001&N\u001b\u0005Y%B\u0001'\u001b\u0003\u0019a$o\\8u}%\t!%\u0003\u0002PC\u00059\u0001/Y2lC\u001e,\u0017BA)S\u0005\r\u0019V-\u001d\u0006\u0003\u001f\u0006BQ\u0001V\u0003A\u0002U\u000bAA\\1nKB\u0011aK\u0017\b\u0003/b\u0003\"AS\u0011\n\u0005e\u000b\u0013A\u0002)sK\u0012,g-\u0003\u0002\\9\n11\u000b\u001e:j]\u001eT!!W\u0011\u0002!Y,7\r^8s)>\f%O]1z+\u00124W#\u0001\u001d\u0002#Y,7\r^8s)>\f%O]1z+\u00124\u0007%A\u000bwK\u000e$xN\u001d+p\u0003J\u0014\u0018-\u001f$m_\u0006$X\u000b\u001a4\u0002-Y,7\r^8s)>\f%O]1z\r2|\u0017\r^+eM\u0002\n\u0001#\u0019:sCf$vNV3di>\u0014X\u000b\u001a4\u0002#\u0005\u0014(/Y=U_Z+7\r^8s+\u00124\u0007eE\u0002\r?\u0015\u0004\"AZ4\u000e\u0003II!\u0001\u001b\n\u0003=M\u0003\u0018M]6TKN\u001c\u0018n\u001c8FqR,gn]5p]N\u0004&o\u001c<jI\u0016\u0014H#\u00016\u0011\u0005qaACA\u0015m\u0011\u0015qd\u00021\u0001n!\t1g.\u0003\u0002p%\t12\u000b]1sWN+7o]5p]\u0016CH/\u001a8tS>t7\u000f"
)
public class InternalFunctionRegistration implements SparkSessionExtensionsProvider {
   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
   }

   public void apply(final SparkSessionExtensions e) {
      InternalFunctionRegistration$.MODULE$.apply();
   }

   public InternalFunctionRegistration() {
      Function1.$init$(this);
   }
}
