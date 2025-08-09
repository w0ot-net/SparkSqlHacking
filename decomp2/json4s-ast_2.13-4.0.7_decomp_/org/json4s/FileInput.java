package org.json4s;

import java.io.File;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0002\f\u0018\u0001rA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005k!)a\b\u0001C\u0001\u007f!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dI\u0006!!A\u0005\u0002iCqA\u0018\u0001\u0002\u0002\u0013\u0005q\fC\u0004f\u0001\u0005\u0005I\u0011\t4\t\u000f5\u0004\u0011\u0011!C\u0001]\"91\u000fAA\u0001\n\u0003\"\bb\u0002<\u0001\u0003\u0003%\te\u001e\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011\u001dQ\b!!A\u0005Bm<q!`\f\u0002\u0002#\u0005aPB\u0004\u0017/\u0005\u0005\t\u0012A@\t\ry\u0002B\u0011AA\t\u0011\u001dA\b#!A\u0005FeD\u0011\"a\u0005\u0011\u0003\u0003%\t)!\u0006\t\u0013\u0005e\u0001#!A\u0005\u0002\u0006m\u0001\"CA\u0014!\u0005\u0005I\u0011BA\u0015\u0005%1\u0015\u000e\\3J]B,HO\u0003\u0002\u00193\u00051!n]8oiMT\u0011AG\u0001\u0004_J<7\u0001A\n\u0005\u0001u\ts\u0005\u0005\u0002\u001f?5\tq#\u0003\u0002!/\tI!j]8o\u0013:\u0004X\u000f\u001e\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\b!J|G-^2u!\tA\u0003G\u0004\u0002*]9\u0011!&L\u0007\u0002W)\u0011AfG\u0001\u0007yI|w\u000e\u001e \n\u0003\u0011J!aL\u0012\u0002\u000fA\f7m[1hK&\u0011\u0011G\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003_\r\nAAZ5mKV\tQ\u0007\u0005\u00027w5\tqG\u0003\u00029s\u0005\u0011\u0011n\u001c\u0006\u0002u\u0005!!.\u0019<b\u0013\tatG\u0001\u0003GS2,\u0017!\u00024jY\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002A\u0003B\u0011a\u0004\u0001\u0005\u0006g\r\u0001\r!N\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002A\t\"91\u0007\u0002I\u0001\u0002\u0004)\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u000f*\u0012Q\u0007S\u0016\u0002\u0013B\u0011!jT\u0007\u0002\u0017*\u0011A*T\u0001\nk:\u001c\u0007.Z2lK\u0012T!AT\u0012\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Q\u0017\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\u0019\u0006C\u0001+X\u001b\u0005)&B\u0001,:\u0003\u0011a\u0017M\\4\n\u0005a+&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001\\!\t\u0011C,\u0003\u0002^G\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011\u0001m\u0019\t\u0003E\u0005L!AY\u0012\u0003\u0007\u0005s\u0017\u0010C\u0004e\u0011\u0005\u0005\t\u0019A.\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u00059\u0007c\u00015lA6\t\u0011N\u0003\u0002kG\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u00051L'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"a\u001c:\u0011\u0005\t\u0002\u0018BA9$\u0005\u001d\u0011un\u001c7fC:Dq\u0001\u001a\u0006\u0002\u0002\u0003\u0007\u0001-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GCA*v\u0011\u001d!7\"!AA\u0002m\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00027\u0006AAo\\*ue&tw\rF\u0001T\u0003\u0019)\u0017/^1mgR\u0011q\u000e \u0005\bI:\t\t\u00111\u0001a\u0003%1\u0015\u000e\\3J]B,H\u000f\u0005\u0002\u001f!M)\u0001#!\u0001\u0002\u000eA1\u00111AA\u0005k\u0001k!!!\u0002\u000b\u0007\u0005\u001d1%A\u0004sk:$\u0018.\\3\n\t\u0005-\u0011Q\u0001\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004c\u0001\u001c\u0002\u0010%\u0011\u0011g\u000e\u000b\u0002}\u0006)\u0011\r\u001d9msR\u0019\u0001)a\u0006\t\u000bM\u001a\u0002\u0019A\u001b\u0002\u000fUt\u0017\r\u001d9msR!\u0011QDA\u0012!\u0011\u0011\u0013qD\u001b\n\u0007\u0005\u00052E\u0001\u0004PaRLwN\u001c\u0005\t\u0003K!\u0012\u0011!a\u0001\u0001\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005-\u0002c\u0001+\u0002.%\u0019\u0011qF+\u0003\r=\u0013'.Z2u\u0001"
)
public class FileInput extends JsonInput {
   private final File file;

   public static Option unapply(final FileInput x$0) {
      return FileInput$.MODULE$.unapply(x$0);
   }

   public static FileInput apply(final File file) {
      return FileInput$.MODULE$.apply(file);
   }

   public static Function1 andThen(final Function1 g) {
      return FileInput$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return FileInput$.MODULE$.compose(g);
   }

   public File file() {
      return this.file;
   }

   public FileInput copy(final File file) {
      return new FileInput(file);
   }

   public File copy$default$1() {
      return this.file();
   }

   public String productPrefix() {
      return "FileInput";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.file();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof FileInput;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "file";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof FileInput) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     FileInput var4 = (FileInput)x$1;
                     File var10000 = this.file();
                     File var5 = var4.file();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public FileInput(final File file) {
      this.file = file;
   }
}
