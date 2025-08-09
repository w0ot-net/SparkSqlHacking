package scala.util.parsing.input;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t<QAE\n\t\u0002q1QAH\n\t\u0002}AQ\u0001J\u0001\u0005\u0002\u0015BqAJ\u0001C\u0002\u0013\u0015q\u0005\u0003\u0004+\u0003\u0001\u0006i\u0001\u000b\u0005\u0006W\u0005!\t\u0001\f\u0004\u0005=M\u0001b\u0006\u0003\u00053\r\t\u0005\t\u0015!\u00034\u0011%IdA!A!\u0002\u0013QT\b\u0003\u0005@\r\t\u0005\t\u0015!\u0003;\u0011!\u0001eA!A!\u0002\u0013Q\u0004\"\u0002\u0013\u0007\t\u0013\t\u0005\"\u0002\u0013\u0007\t\u00031\u0005\"\u0002&\u0007\t\u0003Z\u0005\"\u0002'\u0007\t\u0013i\u0005\"\u0002(\u0007\t\u0003z\u0005\"\u0002*\u0007\t\u0003\u001a\u0006bC,\u0007!\u0003\r\t\u0011!C\u0005\u001bv\nAb\u0015;sK\u0006l'+Z1eKJT!\u0001F\u000b\u0002\u000b%t\u0007/\u001e;\u000b\u0005Y9\u0012a\u00029beNLgn\u001a\u0006\u00031e\tA!\u001e;jY*\t!$A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005u\tQ\"A\n\u0003\u0019M#(/Z1n%\u0016\fG-\u001a:\u0014\u0005\u0005\u0001\u0003CA\u0011#\u001b\u0005I\u0012BA\u0012\u001a\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012\u0001H\u0001\u0006\u000b>47\t[\u000b\u0002Q=\t\u0011\u0006H\u0001\u001b\u0003\u0019)uNZ\"iA\u0005)\u0011\r\u001d9msR\u0011Q\u0006\u0017\t\u0003;\u0019\u0019\"AB\u0018\u0011\u0005u\u0001\u0014BA\u0019\u0014\u00059\u0001\u0016mZ3e'\u0016\f(+Z1eKJ\f1a]3r!\riBGN\u0005\u0003kM\u0011\u0001\u0002U1hK\u0012\u001cV-\u001d\t\u0003C]J!\u0001O\r\u0003\t\rC\u0017M]\u0001\u0004_\u001a4\u0007CA\u0011<\u0013\ta\u0014DA\u0002J]RL!A\u0010\u0019\u0002\r=4gm]3u\u0003\u0011ag.^7\u0002\u00119,\u0007\u0010^#pYB\"R!\f\"D\t\u0016CQAM\u0006A\u0002MBQ!O\u0006A\u0002iBQaP\u0006A\u0002iBQ\u0001Q\u0006A\u0002i\"B!L$I\u0013\")!\u0007\u0004a\u0001g!)\u0011\b\u0004a\u0001u!)q\b\u0004a\u0001u\u0005!!/Z:u+\u0005i\u0013a\u00028fqR,u\u000e\\\u000b\u0002u\u0005!AM]8q)\ti\u0003\u000bC\u0003R\u001f\u0001\u0007!(A\u0001o\u0003\r\u0001xn]\u000b\u0002)B\u0011Q$V\u0005\u0003-N\u0011\u0001\u0002U8tSRLwN\\\u0001\rgV\u0004XM\u001d\u0013pM\u001a\u001cX\r\u001e\u0005\u00063\u0016\u0001\rAW\u0001\u0003S:\u0004\"a\u00171\u000e\u0003qS!!\u00180\u0002\u0005%|'\"A0\u0002\t)\fg/Y\u0005\u0003Cr\u0013aAU3bI\u0016\u0014\b"
)
public class StreamReader extends PagedSeqReader {
   public final PagedSeq scala$util$parsing$input$StreamReader$$seq;
   public final int scala$util$parsing$input$StreamReader$$lnum;
   private final int nextEol0;

   public static StreamReader apply(final java.io.Reader in) {
      return StreamReader$.MODULE$.apply(in);
   }

   public static char EofCh() {
      return StreamReader$.MODULE$.EofCh();
   }

   // $FF: synthetic method
   public int scala$util$parsing$input$StreamReader$$super$offset() {
      return super.offset();
   }

   public StreamReader rest() {
      if (!this.scala$util$parsing$input$StreamReader$$seq.isDefinedAt(super.offset())) {
         return this;
      } else {
         return BoxesRunTime.unboxToChar(this.scala$util$parsing$input$StreamReader$$seq.apply(super.offset())) == '\n' ? new StreamReader(this.scala$util$parsing$input$StreamReader$$seq.slice(super.offset() + 1), 0, this.scala$util$parsing$input$StreamReader$$lnum + 1, -1) : new StreamReader(this.scala$util$parsing$input$StreamReader$$seq, super.offset() + 1, this.scala$util$parsing$input$StreamReader$$lnum, this.nextEol0);
      }
   }

   public int scala$util$parsing$input$StreamReader$$nextEol() {
      if (this.nextEol0 != -1) {
         return this.nextEol0;
      } else {
         int i;
         for(i = super.offset(); this.scala$util$parsing$input$StreamReader$$seq.isDefinedAt(i) && BoxesRunTime.unboxToChar(this.scala$util$parsing$input$StreamReader$$seq.apply(i)) != '\n' && BoxesRunTime.unboxToChar(this.scala$util$parsing$input$StreamReader$$seq.apply(i)) != 26; ++i) {
         }

         return i;
      }
   }

   public StreamReader drop(final int n) {
      while(true) {
         int eolPos = this.scala$util$parsing$input$StreamReader$$nextEol();
         if (eolPos >= super.offset() + n || !this.scala$util$parsing$input$StreamReader$$seq.isDefinedAt(eolPos)) {
            return new StreamReader(this.scala$util$parsing$input$StreamReader$$seq, super.offset() + n, this.scala$util$parsing$input$StreamReader$$lnum, eolPos);
         }

         StreamReader var10000 = new StreamReader(this.scala$util$parsing$input$StreamReader$$seq.slice(eolPos + 1), 0, this.scala$util$parsing$input$StreamReader$$lnum + 1, -1);
         n = super.offset() + n - (eolPos + 1);
         this = var10000;
      }
   }

   public Position pos() {
      return new Position() {
         // $FF: synthetic field
         private final StreamReader $outer;

         public String toString() {
            return Position.toString$(this);
         }

         public String longString() {
            return Position.longString$(this);
         }

         public boolean $less(final Position that) {
            return Position.$less$(this, that);
         }

         public boolean equals(final Object other) {
            return Position.equals$(this, other);
         }

         public int line() {
            return this.$outer.scala$util$parsing$input$StreamReader$$lnum;
         }

         public int column() {
            return this.$outer.scala$util$parsing$input$StreamReader$$super$offset() + 1;
         }

         public String lineContents() {
            return this.$outer.scala$util$parsing$input$StreamReader$$seq.slice(0, this.$outer.scala$util$parsing$input$StreamReader$$nextEol()).toString();
         }

         public {
            if (StreamReader.this == null) {
               throw null;
            } else {
               this.$outer = StreamReader.this;
               Position.$init$(this);
            }
         }
      };
   }

   private StreamReader(final PagedSeq seq, final int off, final int lnum, final int nextEol0) {
      super(seq, off);
      this.scala$util$parsing$input$StreamReader$$seq = seq;
      this.scala$util$parsing$input$StreamReader$$lnum = lnum;
      this.nextEol0 = nextEol0;
   }

   public StreamReader(final PagedSeq seq, final int off, final int lnum) {
      this(seq, off, lnum, -1);
   }
}
