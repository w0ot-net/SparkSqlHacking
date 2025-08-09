package scala;

public final class Tuple2$mcII$sp extends Tuple2 implements Product2$mcII$sp {
   public final int _1$mcI$sp;
   public final int _2$mcI$sp;

   public int _1$mcI$sp() {
      return this._1$mcI$sp;
   }

   public int _1() {
      return this._1$mcI$sp();
   }

   public int _2$mcI$sp() {
      return this._2$mcI$sp;
   }

   public int _2() {
      return this._2$mcI$sp();
   }

   public Tuple2 swap() {
      return this.swap$mcII$sp();
   }

   public Tuple2 swap$mcII$sp() {
      return new Tuple2$mcII$sp(this._2$mcI$sp(), this._1$mcI$sp());
   }

   public int copy$default$1() {
      return this._1$mcI$sp();
   }

   public int copy$default$1$mcI$sp() {
      return this._1$mcI$sp();
   }

   public int copy$default$2() {
      return this._2$mcI$sp();
   }

   public int copy$default$2$mcI$sp() {
      return this._2$mcI$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public Tuple2$mcII$sp(final int _1$mcI$sp, final int _2$mcI$sp) {
      super((Object)null, (Object)null);
      this._1$mcI$sp = _1$mcI$sp;
      this._2$mcI$sp = _2$mcI$sp;
   }
}
