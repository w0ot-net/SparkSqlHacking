package org.json4s.scalap.scalasig;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tea\u0001B\u0016-\u0001VB\u0001b\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t#\u0002\u0011\t\u0012)A\u0005\u001b\"A!\u000b\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005[\u0001\tE\t\u0015!\u0003U\u0011!Y\u0006A!f\u0001\n\u0003a\u0006\u0002C1\u0001\u0005#\u0005\u000b\u0011B/\t\u0011\t\u0004!Q3A\u0005\u0002\rD\u0001\u0002\u001b\u0001\u0003\u0012\u0003\u0006I\u0001\u001a\u0005\u0006S\u0002!\tA\u001b\u0005\u0006a\u0002!\t!\u001d\u0005\u0006k\u0002!\t!\u001d\u0005\u0006m\u0002!\ta\u001e\u0005\u0006w\u0002!\ta\u001e\u0005\u0006y\u0002!\t! \u0005\b\u0003\u0017\u0001A\u0011AA\u0007\u0011\u001d\t\u0019\u0002\u0001C\u0001\u0003+Aq!!\u0007\u0001\t\u0003\tY\u0002C\u0005\u00028\u0001\u0011\r\u0011\"\u0001\u0002:!A\u0011\u0011\n\u0001!\u0002\u0013\tY\u0004C\u0004\u0002L\u0001!\t!!\u0014\t\u000f\u0005\u0005\u0004\u0001\"\u0001\u0002d!I\u0011\u0011\u000e\u0001\u0002\u0002\u0013\u0005\u00111\u000e\u0005\n\u0003k\u0002\u0011\u0013!C\u0001\u0003oB\u0011\"a#\u0001#\u0003%\t!!$\t\u0013\u0005E\u0005!%A\u0005\u0002\u0005M\u0005\"CAL\u0001E\u0005I\u0011AAM\u0011%\ti\nAA\u0001\n\u0003\nI\u0004\u0003\u0005\u0002 \u0002\t\t\u0011\"\u0001r\u0011%\t\t\u000bAA\u0001\n\u0003\t\u0019\u000bC\u0005\u0002*\u0002\t\t\u0011\"\u0011\u0002,\"I\u0011Q\u0017\u0001\u0002\u0002\u0013\u0005\u0011q\u0017\u0005\n\u0003\u0003\u0004\u0011\u0011!C!\u0003\u0007D\u0011\"a2\u0001\u0003\u0003%\t%!3\t\u0013\u0005-\u0007!!A\u0005B\u00055\u0007\"CAh\u0001\u0005\u0005I\u0011IAi\u000f%\t)\u000eLA\u0001\u0012\u0003\t9N\u0002\u0005,Y\u0005\u0005\t\u0012AAm\u0011\u0019IW\u0005\"\u0001\u0002r\"I\u00111Z\u0013\u0002\u0002\u0013\u0015\u0013Q\u001a\u0005\n\u0003g,\u0013\u0011!CA\u0003kD\u0011\"a@&\u0003\u0003%\tI!\u0001\t\u0013\t=Q%!A\u0005\n\tE!!C\"mCN\u001ch)\u001b7f\u0015\tic&\u0001\u0005tG\u0006d\u0017m]5h\u0015\ty\u0003'\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u0003cI\naA[:p]R\u001a(\"A\u001a\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u00011Dh\u0010\t\u0003oij\u0011\u0001\u000f\u0006\u0002s\u0005)1oY1mC&\u00111\b\u000f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]j\u0014B\u0001 9\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u0011%\u000f\u0005\u00053eB\u0001\"F\u001b\u0005\u0019%B\u0001#5\u0003\u0019a$o\\8u}%\t\u0011(\u0003\u0002Hq\u00059\u0001/Y2lC\u001e,\u0017BA%K\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t9\u0005(\u0001\u0004iK\u0006$WM]\u000b\u0002\u001bB\u0011ajT\u0007\u0002Y%\u0011\u0001\u000b\f\u0002\u0010\u00072\f7o\u001d$jY\u0016DU-\u00193fe\u00069\u0001.Z1eKJ\u0004\u0013A\u00024jK2$7/F\u0001U!\r\u0001UkV\u0005\u0003-*\u00131aU3r!\tq\u0005,\u0003\u0002ZY\t)a)[3mI\u00069a-[3mIN\u0004\u0013aB7fi\"|Gm]\u000b\u0002;B\u0019\u0001)\u00160\u0011\u00059{\u0016B\u00011-\u0005\u0019iU\r\u001e5pI\u0006AQ.\u001a;i_\u0012\u001c\b%\u0001\u0006biR\u0014\u0018NY;uKN,\u0012\u0001\u001a\t\u0004\u0001V+\u0007C\u0001(g\u0013\t9GFA\u0005BiR\u0014\u0018NY;uK\u0006Y\u0011\r\u001e;sS\n,H/Z:!\u0003\u0019a\u0014N\\5u}Q)1\u000e\\7o_B\u0011a\n\u0001\u0005\u0006\u0017&\u0001\r!\u0014\u0005\u0006%&\u0001\r\u0001\u0016\u0005\u00067&\u0001\r!\u0018\u0005\u0006E&\u0001\r\u0001Z\u0001\r[\u0006TwN\u001d,feNLwN\\\u000b\u0002eB\u0011qg]\u0005\u0003ib\u00121!\u00138u\u00031i\u0017N\\8s-\u0016\u00148/[8o\u0003%\u0019G.Y:t\u001d\u0006lW-F\u0001y!\t9\u00140\u0003\u0002{q\t\u0019\u0011I\\=\u0002\u0015M,\b/\u001a:DY\u0006\u001c8/\u0001\u0006j]R,'OZ1dKN,\u0012A \t\u0005\u007f\u0006%\u00010\u0004\u0002\u0002\u0002)!\u00111AA\u0003\u0003%IW.\\;uC\ndWMC\u0002\u0002\ba\n!bY8mY\u0016\u001cG/[8o\u0013\r1\u0016\u0011A\u0001\tG>t7\u000f^1oiR\u0019\u00010a\u0004\t\r\u0005Eq\u00021\u0001s\u0003\u0015Ig\u000eZ3y\u0003=\u0019wN\\:uC:$xK]1qa\u0016$Gc\u0001=\u0002\u0018!1\u0011\u0011\u0003\tA\u0002I\f\u0011\"\u0019;ue&\u0014W\u000f^3\u0015\t\u0005u\u00111\u0005\t\u0005o\u0005}Q-C\u0002\u0002\"a\u0012aa\u00149uS>t\u0007bBA\u0013#\u0001\u0007\u0011qE\u0001\u0005]\u0006lW\r\u0005\u0003\u0002*\u0005Eb\u0002BA\u0016\u0003[\u0001\"A\u0011\u001d\n\u0007\u0005=\u0002(\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003g\t)D\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003_A\u0014a\u0007*V\u001dRKU*R0W\u0013NK%\tT#`\u0003:su\nV!U\u0013>s5+\u0006\u0002\u0002<A!\u0011QHA$\u001b\t\tyD\u0003\u0003\u0002B\u0005\r\u0013\u0001\u00027b]\u001eT!!!\u0012\u0002\t)\fg/Y\u0005\u0005\u0003g\ty$\u0001\u000fS+:#\u0016*T#`-&\u001b\u0016J\u0011'F?\u0006sej\u0014+B)&{ej\u0015\u0011\u0002\u0017\u0005tgn\u001c;bi&|gn]\u000b\u0003\u0003\u001f\u0002RaNA\u0010\u0003#\u0002B\u0001Q+\u0002TA!\u0011QKA.\u001d\rq\u0015qK\u0005\u0004\u00033b\u0013aD\"mCN\u001ch)\u001b7f!\u0006\u00148/\u001a:\n\t\u0005u\u0013q\f\u0002\u000b\u0003:tw\u000e^1uS>t'bAA-Y\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u0015\t\u0005\u0015\u0014q\r\t\u0006o\u0005}\u00111\u000b\u0005\b\u0003K)\u0002\u0019AA\u0014\u0003\u0011\u0019w\u000e]=\u0015\u0013-\fi'a\u001c\u0002r\u0005M\u0004bB&\u0017!\u0003\u0005\r!\u0014\u0005\b%Z\u0001\n\u00111\u0001U\u0011\u001dYf\u0003%AA\u0002uCqA\u0019\f\u0011\u0002\u0003\u0007A-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005e$fA'\u0002|-\u0012\u0011Q\u0010\t\u0005\u0003\u007f\n9)\u0004\u0002\u0002\u0002*!\u00111QAC\u0003%)hn\u00195fG.,GMC\u0002\u0002baJA!!#\u0002\u0002\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u0012\u0016\u0004)\u0006m\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003+S3!XA>\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\"!a'+\u0007\u0011\fY(A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rA\u0018Q\u0015\u0005\t\u0003Ok\u0012\u0011!a\u0001e\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!,\u0011\u000b\u0005=\u0016\u0011\u0017=\u000e\u0005\u0005\u0015\u0011\u0002BAZ\u0003\u000b\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011\u0011XA`!\r9\u00141X\u0005\u0004\u0003{C$a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003O{\u0012\u0011!a\u0001q\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\tY$!2\t\u0011\u0005\u001d\u0006%!AA\u0002I\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002e\u0006AAo\\*ue&tw\r\u0006\u0002\u0002<\u00051Q-];bYN$B!!/\u0002T\"A\u0011qU\u0012\u0002\u0002\u0003\u0007\u00010A\u0005DY\u0006\u001c8OR5mKB\u0011a*J\n\u0006K\u0005m\u0017q\u001d\t\n\u0003;\f\u0019/\u0014+^I.l!!a8\u000b\u0007\u0005\u0005\b(A\u0004sk:$\u0018.\\3\n\t\u0005\u0015\u0018q\u001c\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:$\u0004\u0003BAu\u0003_l!!a;\u000b\t\u00055\u00181I\u0001\u0003S>L1!SAv)\t\t9.A\u0003baBd\u0017\u0010F\u0005l\u0003o\fI0a?\u0002~\")1\n\u000ba\u0001\u001b\")!\u000b\u000ba\u0001)\")1\f\u000ba\u0001;\")!\r\u000ba\u0001I\u00069QO\\1qa2LH\u0003\u0002B\u0002\u0005\u0017\u0001RaNA\u0010\u0005\u000b\u0001ra\u000eB\u0004\u001bRkF-C\u0002\u0003\na\u0012a\u0001V;qY\u0016$\u0004\u0002\u0003B\u0007S\u0005\u0005\t\u0019A6\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u0014A!\u0011Q\bB\u000b\u0013\u0011\u00119\"a\u0010\u0003\r=\u0013'.Z2u\u0001"
)
public class ClassFile implements Product, Serializable {
   private final ClassFileHeader header;
   private final Seq fields;
   private final Seq methods;
   private final Seq attributes;
   private final String RUNTIME_VISIBLE_ANNOTATIONS;

   public static Option unapply(final ClassFile x$0) {
      return ClassFile$.MODULE$.unapply(x$0);
   }

   public static ClassFile apply(final ClassFileHeader header, final Seq fields, final Seq methods, final Seq attributes) {
      return ClassFile$.MODULE$.apply(header, fields, methods, attributes);
   }

   public static Function1 tupled() {
      return ClassFile$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ClassFile$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ClassFileHeader header() {
      return this.header;
   }

   public Seq fields() {
      return this.fields;
   }

   public Seq methods() {
      return this.methods;
   }

   public Seq attributes() {
      return this.attributes;
   }

   public int majorVersion() {
      return this.header().major();
   }

   public int minorVersion() {
      return this.header().minor();
   }

   public Object className() {
      return this.constant(this.header().classIndex());
   }

   public Object superClass() {
      return this.constant(this.header().superClassIndex());
   }

   public Seq interfaces() {
      return (Seq)this.header().interfaces().map((index) -> $anonfun$interfaces$2(this, BoxesRunTime.unboxToInt(index)));
   }

   public Object constant(final int index) {
      Object var3 = this.header().constants().apply(index);
      Object var2;
      if (var3 instanceof StringBytesPair) {
         StringBytesPair var4 = (StringBytesPair)var3;
         String str = var4.string();
         var2 = str;
      } else {
         var2 = var3;
      }

      return var2;
   }

   public Object constantWrapped(final int index) {
      return this.header().constants().apply(index);
   }

   public Option attribute(final String name) {
      return this.attributes().find((attrib) -> BoxesRunTime.boxToBoolean($anonfun$attribute$3(this, name, attrib)));
   }

   public String RUNTIME_VISIBLE_ANNOTATIONS() {
      return this.RUNTIME_VISIBLE_ANNOTATIONS;
   }

   public Option annotations() {
      return this.attributes().find((attr) -> BoxesRunTime.boxToBoolean($anonfun$annotations$2(this, attr))).map((attr) -> ClassFileParser$.MODULE$.parseAnnotations(attr.byteCode()));
   }

   public Option annotation(final String name) {
      return this.annotations().flatMap((seq) -> seq.find((annot) -> BoxesRunTime.boxToBoolean($anonfun$annotation$4(this, name, annot))));
   }

   public ClassFile copy(final ClassFileHeader header, final Seq fields, final Seq methods, final Seq attributes) {
      return new ClassFile(header, fields, methods, attributes);
   }

   public ClassFileHeader copy$default$1() {
      return this.header();
   }

   public Seq copy$default$2() {
      return this.fields();
   }

   public Seq copy$default$3() {
      return this.methods();
   }

   public Seq copy$default$4() {
      return this.attributes();
   }

   public String productPrefix() {
      return "ClassFile";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.header();
            break;
         case 1:
            var10000 = this.fields();
            break;
         case 2:
            var10000 = this.methods();
            break;
         case 3:
            var10000 = this.attributes();
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
      return x$1 instanceof ClassFile;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "header";
            break;
         case 1:
            var10000 = "fields";
            break;
         case 2:
            var10000 = "methods";
            break;
         case 3:
            var10000 = "attributes";
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
      boolean var13;
      if (this != x$1) {
         label81: {
            boolean var2;
            if (x$1 instanceof ClassFile) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label63: {
                  label72: {
                     ClassFile var4 = (ClassFile)x$1;
                     ClassFileHeader var10000 = this.header();
                     ClassFileHeader var5 = var4.header();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label72;
                     }

                     Seq var9 = this.fields();
                     Seq var6 = var4.fields();
                     if (var9 == null) {
                        if (var6 != null) {
                           break label72;
                        }
                     } else if (!var9.equals(var6)) {
                        break label72;
                     }

                     var9 = this.methods();
                     Seq var7 = var4.methods();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label72;
                        }
                     } else if (!var9.equals(var7)) {
                        break label72;
                     }

                     var9 = this.attributes();
                     Seq var8 = var4.attributes();
                     if (var9 == null) {
                        if (var8 != null) {
                           break label72;
                        }
                     } else if (!var9.equals(var8)) {
                        break label72;
                     }

                     if (var4.canEqual(this)) {
                        var13 = true;
                        break label63;
                     }
                  }

                  var13 = false;
               }

               if (var13) {
                  break label81;
               }
            }

            var13 = false;
            return var13;
         }
      }

      var13 = true;
      return var13;
   }

   // $FF: synthetic method
   public static final Object $anonfun$interfaces$2(final ClassFile $this, final int index) {
      return $this.constant(index);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$attribute$3(final ClassFile $this, final String name$2, final Attribute attrib) {
      boolean var4;
      label23: {
         Object var10000 = $this.constant(attrib.nameIndex());
         if (var10000 == null) {
            if (name$2 == null) {
               break label23;
            }
         } else if (var10000.equals(name$2)) {
            break label23;
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$annotations$2(final ClassFile $this, final Attribute attr) {
      boolean var3;
      label23: {
         Object var10000 = $this.constant(attr.nameIndex());
         String var2 = $this.RUNTIME_VISIBLE_ANNOTATIONS();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$annotation$4(final ClassFile $this, final String name$3, final ClassFileParser.Annotation annot) {
      boolean var4;
      label23: {
         Object var10000 = $this.constant(annot.typeIndex());
         if (var10000 == null) {
            if (name$3 == null) {
               break label23;
            }
         } else if (var10000.equals(name$3)) {
            break label23;
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   public ClassFile(final ClassFileHeader header, final Seq fields, final Seq methods, final Seq attributes) {
      this.header = header;
      this.fields = fields;
      this.methods = methods;
      this.attributes = attributes;
      Product.$init$(this);
      this.RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
