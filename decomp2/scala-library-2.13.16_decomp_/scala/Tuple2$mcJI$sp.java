package scala;

public final class Tuple2$mcJI$sp extends Tuple2 implements Product2$mcJI$sp {
   public final long _1$mcJ$sp;
   public final int _2$mcI$sp;

   public long _1$mcJ$sp() {
      return this._1$mcJ$sp;
   }

   public long _1() {
      return this._1$mcJ$sp();
   }

   public int _2$mcI$sp() {
      return this._2$mcI$sp;
   }

   public int _2() {
      return this._2$mcI$sp();
   }

   public Tuple2 swap() {
      return this.swap$mcJI$sp();
   }

   public Tuple2 swap$mcJI$sp() {
      return new Tuple2$mcIJ$sp(this._2$mcI$sp(), this._1$mcJ$sp());
   }

   public long copy$default$1() {
      return this._1$mcJ$sp();
   }

   public long copy$default$1$mcJ$sp() {
      return this._1$mcJ$sp();
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

   public Tuple2$mcJI$sp(final long _1$mcJ$sp, final int _2$mcI$sp) {
      super((Object)null, (Object)null);
      this._1$mcJ$sp = _1$mcJ$sp;
      this._2$mcI$sp = _2$mcI$sp;
   }
}
