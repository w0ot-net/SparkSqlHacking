package org.json4s.scalap.scalasig;

import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593Q\u0001C\u0005\u0002\u0002IAQa\u0006\u0001\u0005\u0002aAQA\u0007\u0001\u0007\u0002mAQa\b\u0001\u0005\u0002\u0001BQa\n\u0001\u0005\u0002!BQA\u000e\u0001\u0005\u0002]BQa\u0010\u0001\u0005\u0002\u0001C\u0001\"\u0013\u0001\t\u0006\u0004%\tA\u0013\u0002\u0011'fl'm\u001c7J]\u001a|7+_7c_2T!AC\u0006\u0002\u0011M\u001c\u0017\r\\1tS\u001eT!\u0001D\u0007\u0002\rM\u001c\u0017\r\\1q\u0015\tqq\"\u0001\u0004kg>tGg\u001d\u0006\u0002!\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\u0005\t\u0003)Ui\u0011!C\u0005\u0003-%\u0011abU2bY\u0006\u001c\u0016nZ*z[\n|G.\u0001\u0004=S:LGO\u0010\u000b\u00023A\u0011A\u0003A\u0001\u000bgfl'm\u001c7J]\u001a|W#\u0001\u000f\u0011\u0005Qi\u0012B\u0001\u0010\n\u0005)\u0019\u00160\u001c2pY&sgm\\\u0001\u0006K:$(/_\u000b\u0002CA\u0011!%\n\t\u0003)\rJ!\u0001J\u0005\u0003\u0011M\u001b\u0017\r\\1TS\u001eL!AJ\u0012\u0003\u000b\u0015sGO]=\u0002\t9\fW.Z\u000b\u0002SA\u0011!f\r\b\u0003WE\u0002\"\u0001L\u0018\u000e\u00035R!AL\t\u0002\rq\u0012xn\u001c;?\u0015\u0005\u0001\u0014!B:dC2\f\u0017B\u0001\u001a0\u0003\u0019\u0001&/\u001a3fM&\u0011A'\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Iz\u0013A\u00029be\u0016tG/F\u00019!\rI$\bP\u0007\u0002_%\u00111h\f\u0002\u0005'>lW\r\u0005\u0002\u0015{%\u0011a(\u0003\u0002\u0007'fl'm\u001c7\u0002\u000f!\f7O\u00127bOR\u0011\u0011\t\u0012\t\u0003s\tK!aQ\u0018\u0003\u000f\t{w\u000e\\3b]\")QI\u0002a\u0001\r\u0006!a\r\\1h!\tIt)\u0003\u0002I_\t!Aj\u001c8h\u0003!IgNZ8UsB,W#A&\u0011\u0005Qa\u0015BA'\n\u0005\u0011!\u0016\u0010]3"
)
public abstract class SymbolInfoSymbol extends ScalaSigSymbol {
   private Type infoType;
   private volatile boolean bitmap$0;

   public abstract SymbolInfo symbolInfo();

   public ScalaSig.Entry entry() {
      return this.symbolInfo().entry();
   }

   public String name() {
      return this.symbolInfo().name();
   }

   public Some parent() {
      return new Some(this.symbolInfo().owner());
   }

   public boolean hasFlag(final long flag) {
      return ((long)this.symbolInfo().flags() & flag) != 0L;
   }

   private Type infoType$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.infoType = (Type)this.applyRule(ScalaSigEntryParsers$.MODULE$.parseEntry(ScalaSigEntryParsers$.MODULE$.typeEntry(), this.symbolInfo().info()));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.infoType;
   }

   public Type infoType() {
      return !this.bitmap$0 ? this.infoType$lzycompute() : this.infoType;
   }
}
