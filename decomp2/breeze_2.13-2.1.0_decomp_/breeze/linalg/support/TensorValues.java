package breeze.linalg.support;

import breeze.linalg.QuasiTensor;
import breeze.linalg.TensorLike;
import scala.;
import scala.Function1;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001\u0002\u000b\u0016\u0001qA\u0001\u0002\n\u0001\u0003\u0006\u0004%I!\n\u0005\tc\u0001\u0011\t\u0011)A\u0005M!A!\u0007\u0001B\u0001B\u0003%1\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u00038\u0011!i\u0004A!A!\u0002\u0017q\u0004\"\u0002%\u0001\t\u0003I\u0005\"B)\u0001\t\u0003\u0011\u0006\"\u0002,\u0001\t\u00039\u0006\"\u00020\u0001\t\u0003y\u0006\"\u00026\u0001\t\u0003Z\u0007\"B<\u0001\t\u0003B\b\"B>\u0001\t\u0003a\bbBA\u0011\u0001\u0011\u0005\u00111\u0005\u0005\b\u0003O\u0001A\u0011AA\u0015\u000f%\ti#FA\u0001\u0012\u0003\tyC\u0002\u0005\u0015+\u0005\u0005\t\u0012AA\u0019\u0011\u0019A\u0005\u0003\"\u0001\u00024!I\u0011Q\u0007\t\u0012\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003+\u0002\u0012\u0013!C\u0001\u0003/\u0012A\u0002V3og>\u0014h+\u00197vKNT!AF\f\u0002\u000fM,\b\u000f]8si*\u0011\u0001$G\u0001\u0007Y&t\u0017\r\\4\u000b\u0003i\taA\u0019:fKj,7\u0001A\u000b\u0005;\u0019[\u0004f\u0005\u0002\u0001=A\u0011qDI\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1\u0011I\\=SK\u001a\fa\u0001^3og>\u0014X#\u0001\u0014\u0011\u0005\u001dBC\u0002\u0001\u0003\u0007S\u0001!)\u0019\u0001\u0016\u0003\tQC\u0017n]\t\u0003W9\u0002\"a\b\u0017\n\u00055\u0002#a\u0002(pi\"Lgn\u001a\t\u0003?=J!\u0001\r\u0011\u0003\u0007\u0005s\u00170A\u0004uK:\u001cxN\u001d\u0011\u0002\r\u0005\u001cG/\u001b<f!\tyB'\u0003\u00026A\t9!i\\8mK\u0006t\u0017!\u00014\u0011\t}A$hM\u0005\u0003s\u0001\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0005\u001dZD!\u0002\u001f\u0001\u0005\u0004Q#!\u0001,\u0002\u0005\u00154\b\u0003B\u0010@M\u0005K!\u0001\u0011\u0011\u0003!\u0011bWm]:%G>dwN\u001c\u0013mKN\u001c\b\u0003\u0002\"D\u000bjj\u0011aF\u0005\u0003\t^\u0011a\u0001V3og>\u0014\bCA\u0014G\t\u00159\u0005A1\u0001+\u0005\u0005Y\u0015A\u0002\u001fj]&$h\b\u0006\u0003K\u001d>\u0003FCA&N!\u0015a\u0005!\u0012\u001e'\u001b\u0005)\u0002\"B\u001f\u0007\u0001\bq\u0004\"\u0002\u0013\u0007\u0001\u00041\u0003b\u0002\u001a\u0007!\u0003\u0005\ra\r\u0005\bm\u0019\u0001\n\u00111\u00018\u0003\u0011\u0019\u0018N_3\u0016\u0003M\u0003\"a\b+\n\u0005U\u0003#aA%oi\u0006A\u0011\u000e^3sCR|'/F\u0001Y!\rIFLO\u0007\u00025*\u00111\fI\u0001\u000bG>dG.Z2uS>t\u0017BA/[\u0005!IE/\u001a:bi>\u0014\u0018a\u00024pe\u0016\f7\r[\u000b\u0003A\"$\"!\u00193\u0011\u0005}\u0011\u0017BA2!\u0005\u0011)f.\u001b;\t\u000b\u0015L\u0001\u0019\u00014\u0002\u0005\u0019t\u0007\u0003B\u00109u\u001d\u0004\"a\n5\u0005\u000b%L!\u0019\u0001\u0016\u0003\u0003U\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002YB\u0011Q\u000e\u001e\b\u0003]J\u0004\"a\u001c\u0011\u000e\u0003AT!!]\u000e\u0002\rq\u0012xn\u001c;?\u0013\t\u0019\b%\u0001\u0004Qe\u0016$WMZ\u0005\u0003kZ\u0014aa\u0015;sS:<'BA:!\u0003\u0019)\u0017/^1mgR\u00111'\u001f\u0005\u0006u.\u0001\rAL\u0001\u0003aF\n1!\\1q+\u001di\u0018\u0011CA\r\u0003\u0003!2A`A\u000f)\ry\u0018Q\u0001\t\u0004O\u0005\u0005AABA\u0002\u0019\t\u0007!F\u0001\u0003UQ\u0006$\bbBA\u0004\u0019\u0001\u000f\u0011\u0011B\u0001\u0003E\u001a\u0004\u0012\u0002TA\u0006\u0003\u001fQ\u0014qC@\n\u0007\u00055QC\u0001\u0007DC:l\u0015\r\u001d,bYV,7\u000fE\u0002(\u0003#!q!a\u0005\r\u0005\u0004\t)B\u0001\u0002U)F\u0011aE\f\t\u0004O\u0005eAABA\u000e\u0019\t\u0007!FA\u0001P\u0011\u0019)G\u00021\u0001\u0002 A)q\u0004\u000f\u001e\u0002\u0018\u00051Q\r_5tiN$2aMA\u0013\u0011\u00151T\u00021\u00018\u0003\u00191wN]1mYR\u00191'a\u000b\t\u000bYr\u0001\u0019A\u001c\u0002\u0019Q+gn]8s-\u0006dW/Z:\u0011\u00051\u00032C\u0001\t\u001f)\t\ty#A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\t\u0003s\ty%!\u0015\u0002TU\u0011\u00111\b\u0016\u0004g\u0005u2FAA !\u0011\t\t%a\u0013\u000e\u0005\u0005\r#\u0002BA#\u0003\u000f\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%\u0003%\u0001\u0006b]:|G/\u0019;j_:LA!!\u0014\u0002D\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b\u001d\u0013\"\u0019\u0001\u0016\u0005\u000bq\u0012\"\u0019\u0001\u0016\u0005\u000b%\u0012\"\u0019\u0001\u0016\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+!\tI&a\u0018\u0002b\u0005\rTCAA.U\u0011\ti&!\u0010\u0011\t}Adf\r\u0003\u0006\u000fN\u0011\rA\u000b\u0003\u0006yM\u0011\rA\u000b\u0003\u0006SM\u0011\rA\u000b"
)
public class TensorValues {
   private final Object tensor;
   private final boolean active;
   private final Function1 f;
   private final .less.colon.less ev;

   public static Function1 $lessinit$greater$default$3() {
      return TensorValues$.MODULE$.$lessinit$greater$default$3();
   }

   public static boolean $lessinit$greater$default$2() {
      return TensorValues$.MODULE$.$lessinit$greater$default$2();
   }

   private Object tensor() {
      return this.tensor;
   }

   public int size() {
      return ((TensorLike)this.ev.apply(this.tensor())).size();
   }

   public Iterator iterator() {
      return (this.active ? ((QuasiTensor)this.ev.apply(this.tensor())).activeValuesIterator() : ((QuasiTensor)this.ev.apply(this.tensor())).valuesIterator()).filter(this.f);
   }

   public void foreach(final Function1 fn) {
      this.iterator().foreach(fn);
   }

   public String toString() {
      return this.iterator().mkString("TensorValues(", ",", ")");
   }

   public boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof TensorValues) {
         TensorValues var4 = (TensorValues)p1;
         var2 = var4 == this || this.iterator().sameElements(var4.iterator());
      } else {
         var2 = false;
      }

      return var2;
   }

   public Object map(final Function1 fn, final CanMapValues bf) {
      return ((TensorLike)this.ev.apply(this.tensor())).mapValues(fn, bf);
   }

   public boolean exists(final Function1 f) {
      return this.iterator().exists(f);
   }

   public boolean forall(final Function1 f) {
      return this.iterator().forall(f);
   }

   public TensorValues(final Object tensor, final boolean active, final Function1 f, final .less.colon.less ev) {
      this.tensor = tensor;
      this.active = active;
      this.f = f;
      this.ev = ev;
   }
}
