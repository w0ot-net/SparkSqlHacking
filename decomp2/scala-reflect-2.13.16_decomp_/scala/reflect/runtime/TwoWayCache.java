package scala.reflect.runtime;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import scala.Function0;
import scala.Option;
import scala.Option.;
import scala.collection.mutable.WeakHashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000554Qa\u0003\u0007\u0001\u0019IAQ\u0001\u0007\u0001\u0005\u0002iAaa\u000b\u0001!\u0002\u0013a\u0003B\u0002 \u0001A\u0003%q\bC\u0003B\u0001\u0011\u0005!iB\u0003K\u0001!%1JB\u0003N\u0001!%a\nC\u0003\u0019\r\u0011\u0005q\nC\u0003Q\r\u0011\u0005\u0011\u000bC\u0003^\u0001\u0011\u0005a\fC\u0003h\u0001\u0011\u0005\u0001NA\u0006Uo><\u0016-_\"bG\",'BA\u0007\u000f\u0003\u001d\u0011XO\u001c;j[\u0016T!a\u0004\t\u0002\u000fI,g\r\\3di*\t\u0011#A\u0003tG\u0006d\u0017-F\u0002\u0014?%\u001a\"\u0001\u0001\u000b\u0011\u0005U1R\"\u0001\t\n\u0005]\u0001\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005Y\u0002\u0003\u0002\u000f\u0001;!j\u0011\u0001\u0004\t\u0003=}a\u0001\u0001B\u0003!\u0001\t\u0007\u0011EA\u0001K#\t\u0011S\u0005\u0005\u0002\u0016G%\u0011A\u0005\u0005\u0002\b\u001d>$\b.\u001b8h!\t)b%\u0003\u0002(!\t\u0019\u0011I\\=\u0011\u0005yIC!\u0002\u0016\u0001\u0005\u0004\t#!A*\u0002\u0015Q|7kY1mC6\u000b\u0007\u000f\u0005\u0003.eu!T\"\u0001\u0018\u000b\u0005=\u0002\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0003cA\t!bY8mY\u0016\u001cG/[8o\u0013\t\u0019dFA\u0006XK\u0006\\\u0007*Y:i\u001b\u0006\u0004\bcA\u001b=Q5\taG\u0003\u00028q\u0005\u0019!/\u001a4\u000b\u0005eR\u0014\u0001\u00027b]\u001eT\u0011aO\u0001\u0005U\u00064\u0018-\u0003\u0002>m\tiq+Z1l%\u00164WM]3oG\u0016\f\u0011\u0002^8KCZ\fW*\u00199\u0011\t5\u0012\u0004\u0006\u0011\t\u0004kqj\u0012!B3oi\u0016\u0014HcA\"G\u0011B\u0011Q\u0003R\u0005\u0003\u000bB\u0011A!\u00168ji\")q\t\u0002a\u0001;\u0005\t!\u000eC\u0003J\t\u0001\u0007\u0001&A\u0001t\u0003\u001d\u0019v.\\3SK\u001a\u0004\"\u0001\u0014\u0004\u000e\u0003\u0001\u0011qaU8nKJ+gm\u0005\u0002\u0007)Q\t1*A\u0004v]\u0006\u0004\b\u000f\\=\u0016\u0005I;FCA*Z!\r)BKV\u0005\u0003+B\u0011aa\u00149uS>t\u0007C\u0001\u0010X\t\u0015A\u0006B1\u0001\"\u0005\u0005!\u0006\"\u0002.\t\u0001\u0004Y\u0016AB8qiJ+g\rE\u0002\u0016)r\u00032!\u000e\u001fW\u0003\u001d!xnU2bY\u0006$\"aX3\u0015\u0005!\u0002\u0007BB1\n\t\u0003\u0007!-\u0001\u0003c_\u0012L\bcA\u000bdQ%\u0011A\r\u0005\u0002\ty\tLh.Y7f}!)a-\u0003a\u0001;\u0005\u00191.Z=\u0002\rQ|'*\u0019<b)\tIG\u000e\u0006\u0002\u001eU\"1\u0011M\u0003CA\u0002-\u00042!F2\u001e\u0011\u00151'\u00021\u0001)\u0001"
)
public class TwoWayCache {
   private volatile SomeRef$ SomeRef$module;
   private final WeakHashMap toScalaMap = new WeakHashMap();
   private final WeakHashMap toJavaMap = new WeakHashMap();

   private SomeRef$ SomeRef() {
      if (this.SomeRef$module == null) {
         this.SomeRef$lzycompute$1();
      }

      return this.SomeRef$module;
   }

   public synchronized void enter(final Object j, final Object s) {
      this.toScalaMap.update(j, new WeakReference(s));
      this.toJavaMap.update(s, new WeakReference(j));
   }

   public synchronized Object toScala(final Object key, final Function0 body) {
      Option var3 = this.toScalaMap.get(key);
      if (var3 != null) {
         Option var4 = this.SomeRef().unapply(var3);
         if (!var4.isEmpty()) {
            return var4.get();
         }
      }

      Object result = body.apply();
      this.enter(key, result);
      return result;
   }

   public synchronized Object toJava(final Object key, final Function0 body) {
      Option var3 = this.toJavaMap.get(key);
      if (var3 != null) {
         Option var4 = this.SomeRef().unapply(var3);
         if (!var4.isEmpty()) {
            return var4.get();
         }
      }

      Object result = body.apply();
      this.enter(result, key);
      return result;
   }

   private final void SomeRef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SomeRef$module == null) {
            this.SomeRef$module = new SomeRef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private class SomeRef$ {
      public Option unapply(final Option optRef) {
         if (optRef == null) {
            throw null;
         } else {
            return (Option)(optRef.isDefined() ? .MODULE$.apply(((Reference)optRef.get()).get()) : scala.None..MODULE$);
         }
      }

      public SomeRef$() {
      }
   }
}
