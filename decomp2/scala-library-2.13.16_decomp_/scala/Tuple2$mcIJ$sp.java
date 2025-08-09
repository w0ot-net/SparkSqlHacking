package scala;

public final class Tuple2$mcIJ$sp extends Tuple2 implements Product2$mcIJ$sp {
   public final int _1$mcI$sp;
   public final long _2$mcJ$sp;

   public int _1$mcI$sp() {
      return this._1$mcI$sp;
   }

   public int _1() {
      return this._1$mcI$sp();
   }

   public long _2$mcJ$sp() {
      return this._2$mcJ$sp;
   }

   public long _2() {
      return this._2$mcJ$sp();
   }

   public Tuple2 swap() {
      return this.swap$mcIJ$sp();
   }

   public Tuple2 swap$mcIJ$sp() {
      return new Tuple2$mcJI$sp(this._2$mcJ$sp(), this._1$mcI$sp());
   }

   public int copy$default$1() {
      return this._1$mcI$sp();
   }

   public int copy$default$1$mcI$sp() {
      return this._1$mcI$sp();
   }

   public long copy$default$2() {
      return this._2$mcJ$sp();
   }

   public long copy$default$2$mcJ$sp() {
      return this._2$mcJ$sp();
   }

   public boolean specInstance$() {
      return true;
   }

   public Tuple2$mcIJ$sp(final int _1$mcI$sp, final long _2$mcJ$sp) {
      super((Object)null, (Object)null);
      this._1$mcI$sp = _1$mcI$sp;
      this._2$mcJ$sp = _2$mcJ$sp;
   }
}
