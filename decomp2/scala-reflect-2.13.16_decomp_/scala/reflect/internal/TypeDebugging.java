package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Product;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.util.PropertiesTrait;

@ScalaSignature(
   bytes = "\u0006\u0005\rmb!\u0003(P!\u0003\r\tAVB\u001b\u0011\u0015Y\u0006\u0001\"\u0001]\u000f\u0015\u0001\u0007\u0001#\u0001b\r\u0015\u0019\u0007\u0001#\u0001e\u0011\u0015\u00018\u0001\"\u0001r\u0011\u0015\u00118\u0001\"\u0001t\u0011\u0015Y8\u0001\"\u0001}\u0011\u001d\t)a\u0001C\u0001\u0003\u000fAq!a\u0006\u0004\t\u0003\tI\u0002C\u0004\u0002*\r!\t!a\u000b\t\u000f\u0005=2\u0001\"\u0001\u00022\u001d9\u0011Q\u0007\u0001\t\u0002\u0005]baBA\u001d\u0001!\u0005\u00111\b\u0005\u0007a2!\tA!\u0017\t\u000f\tmC\u0002\"\u0003\u0003^!9!1\r\u0007\u0005\u0002\t\u0015\u0004b\u0002B>\u0019\u0011\u0005!Q\u0010\u0005\b\u0005\u0003cA\u0011\u0001BB\u0011\u001d\u00119\t\u0004C\u0001\u0005\u0013CqA!&\r\t\u0003\u00119jB\u0004\u0003,2A\tA!,\u0007\u000f\tEF\u0002#\u0001\u00034\"1\u0001/\u0006C\u0001\u0005kCqAa.\u0016\t\u0003\u0011I\fC\u0004\u0003TV!\tA!6\t\u000f\tmW\u0003\"\u0001\u0003^\"9!1T\u000b\u0005\u0002\t-\bb\u0002By+\u0011\u0005!1\u001f\u0005\b\u0005s,B\u0011\u0001B~\u0011\u001d\u0019Y!\u0006C\u0001\u0007\u001bAqaa\u0006\r\t\u0013\u0019I\u0002C\u0004\u0004 1!\ta!\t\t\u000f\r\u0015\u0002\u0001\"\u0001\u0004(!911\u0006\u0001\u0005\u0002\r5\u0002bBB\u0010\u0001\u0011\u00051\u0011G\u0004\b\u0003\u0007z\u0005\u0012AA#\r\u0019qu\n#\u0001\u0002H!1\u0001\u000f\nC\u0001\u0003\u0013:q!a\u0013%\u0011\u0003\tiEB\u0004\u0002R\u0011B\t!a\u0015\t\rA<C\u0011AAt\r\u0019\tIoJ\u0002\u0002l\"q\u00111_\u0015\u0005\u0002\u0003\u0015)Q1A\u0005\n\u0005U\bbCA|S\t\u0015\t\u0011)A\u0005\u0003\u007fBa\u0001]\u0015\u0005\u0002\u0005e\bb\u0002B\u0001S\u0011\u0005!1\u0001\u0005\b\u0005\u000bIC\u0011\u0001B\u0002\u0011\u001d\u00119!\u000bC\u0001\u0003kDqA!\u0003*\t\u0003\u0011\u0019\u0001C\u0005\u0003\f%\n\t\u0011\"\u0011\u0003\u000e!I!QC\u0015\u0002\u0002\u0013\u0005#qC\u0004\n\u0005G9\u0013\u0011!E\u0001\u0005K1\u0011\"!;(\u0003\u0003E\tAa\n\t\rA$D\u0011\u0001B\u0015\u0011\u001d\u0011Y\u0003\u000eC\u0003\u0005[AqAa\r5\t\u000b\u0011)\u0004C\u0004\u0003:Q\")Aa\u000f\t\u000f\t}B\u0007\"\u0002\u0003B!I!Q\t\u001b\u0002\u0002\u0013\u0015!q\t\u0005\n\u0005\u0017\"\u0014\u0011!C\u0003\u0005\u001bB\u0011Ba\t(\u0003\u0003%\u0019A!\u0016\u0007\u0013\u0005EC\u0005%A\u0002\u0002\u0005]\u0003\"B.>\t\u0003a\u0006\"CA2{\t\u0007K\u0011BA3\u0011\u001d\t9'\u0010C\u0005\u0003SBq!a&>\t\u0013\tI\nC\u0004\u0002 v\"\t!!)\t\u000f\u0005\u0015V\b\"\u0001\u0002(\"9\u00111V\u001f\u0005\u0002\u00055\u0006bBAY{\u0011\u0005\u00111\u0017\u0005\b\u0003okD\u0011AA]\u0011\u001d\ti,\u0010C\u0001\u0003\u007fCq!a1>\t\u0003\t)\rC\u0004\u0002Jv\"\t!a3\t\u000f\u0005=W\b\"\u0001\u0002R\"9\u0011Q[\u001f\u0005\u0002\u0005]\u0007bBAn{\u0011\u0005\u0011Q\u001c\u0005\b\u0003ClD\u0011AAr\u00055!\u0016\u0010]3EK\n,xmZ5oO*\u0011\u0001+U\u0001\tS:$XM\u001d8bY*\u0011!kU\u0001\be\u00164G.Z2u\u0015\u0005!\u0016!B:dC2\f7\u0001A\n\u0003\u0001]\u0003\"\u0001W-\u000e\u0003MK!AW*\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tQ\f\u0005\u0002Y=&\u0011ql\u0015\u0002\u0005+:LG/A\u0004o_B\u0013\u0018N\u001c;\u0011\u0005\t\u001cQ\"\u0001\u0001\u0003\u000f9|\u0007K]5oiN\u00191aV3\u0011\ta3\u0007.\\\u0005\u0003ON\u0013\u0011BR;oGRLwN\\\u0019\u0011\u0005\tL\u0017B\u00016l\u0005\u0011!&/Z3\n\u00051|%!\u0002+sK\u0016\u001c\bC\u0001-o\u0013\ty7KA\u0004C_>dW-\u00198\u0002\rqJg.\u001b;?)\u0005\t\u0017!D:lSB\u001c6-\u00197b\u001d\u0006lW\r\u0006\u0002ni\")Q/\u0002a\u0001m\u0006!a.Y7f!\t\u0011w/\u0003\u0002ys\n!a*Y7f\u0013\tQxJA\u0003OC6,7/A\u0006tW&\u0004(+\u001a4Ue\u0016,GCA7~\u0011\u0015qh\u00011\u0001\u0000\u0003\u0005!\bc\u00012\u0002\u0002%\u0019\u00111A6\u0003\u000fI+g\r\u0016:fK\u000691o[5q'flGcA7\u0002\n!9\u00111B\u0004A\u0002\u00055\u0011aA:z[B\u0019!-a\u0004\n\t\u0005E\u00111\u0003\u0002\u0007'fl'm\u001c7\n\u0007\u0005UqJA\u0004Ts6\u0014w\u000e\\:\u0002\u0011M\\\u0017\u000e\u001d+za\u0016$2!\\A\u000e\u0011\u001d\ti\u0002\u0003a\u0001\u0003?\t1\u0001\u001e9f!\r\u0011\u0017\u0011E\u0005\u0005\u0003G\t)C\u0001\u0003UsB,\u0017bAA\u0014\u001f\n)A+\u001f9fg\u0006!1o[5q)\ri\u0017Q\u0006\u0005\u0006}&\u0001\r\u0001[\u0001\u0006CB\u0004H.\u001f\u000b\u0004[\u0006M\u0002\"\u0002@\u000b\u0001\u0004A\u0017!\u0003;za\u0016$UMY;h!\t\u0011GBA\u0005usB,G)\u001a2vON!AbVA\u001f!\r\ty$\u0010\b\u0004\u0003\u0003\u001aS\"A(\u0002\u001bQK\b/\u001a#fEV<w-\u001b8h!\r\t\t\u0005J\n\u0003I]#\"!!\u0012\u0002\u0013\u0005s7/[\"pY>\u0014\bcAA(O5\tAEA\u0005B]NL7i\u001c7peN!qeVA+!\r\ty%P\n\u0005{]\u000bI\u0006\u0005\u0003\u0002\\\u0005\u0005TBAA/\u0015\r\tyfU\u0001\u0003S>LA!!\u0015\u0002^\u0005A1m\u001c7peN|5.F\u0001n\u0003\u001dIgnQ8m_J$b!a\u001b\u0002|\u0005M\u0005\u0003BA7\u0003oj!!a\u001c\u000b\t\u0005E\u00141O\u0001\u0005Y\u0006twM\u0003\u0002\u0002v\u0005!!.\u0019<b\u0013\u0011\tI(a\u001c\u0003\rM#(/\u001b8h\u0011\u001d\ti\b\u0011a\u0001\u0003\u007f\n\u0011a\u001d\t\u0005\u0003\u0003\u000byI\u0004\u0003\u0002\u0004\u0006-\u0005cAAC'6\u0011\u0011q\u0011\u0006\u0004\u0003\u0013+\u0016A\u0002\u001fs_>$h(C\u0002\u0002\u000eN\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA=\u0003#S1!!$T\u0011\u001d\t)\n\u0011a\u0001\u0003\u007f\nQaY8m_J\fa!\u001b8C_2$GCBA6\u00037\u000bi\nC\u0004\u0002~\u0005\u0003\r!a \t\u000f\u0005U\u0015\t1\u0001\u0002\u0000\u0005Q\u0011N\u001c'jO\"$(+\u001a3\u0015\t\u0005-\u00141\u0015\u0005\b\u0003{\u0012\u0005\u0019AA@\u0003-Ig\u000eT5hQR\u0014E.^3\u0015\t\u0005-\u0014\u0011\u0016\u0005\b\u0003{\u001a\u0005\u0019AA@\u00031Ig\u000eT5hQR<%/Z3o)\u0011\tY'a,\t\u000f\u0005uD\t1\u0001\u0002\u0000\u0005i\u0011N\u001c'jO\"$\u0018,\u001a7m_^$B!a \u00026\"9\u0011QP#A\u0002\u0005}\u0014AD5o\u0019&<\u0007\u000e^'bO\u0016tG/\u0019\u000b\u0005\u0003W\nY\fC\u0004\u0002~\u0019\u0003\r!a \u0002\u0017%tG*[4ii\u000eK\u0018M\u001c\u000b\u0005\u0003\u007f\n\t\rC\u0004\u0002~\u001d\u0003\r!a \u0002\u000f%twI]3f]R!\u0011qPAd\u0011\u001d\ti\b\u0013a\u0001\u0003\u007f\nQ!\u001b8SK\u0012$B!a \u0002N\"9\u0011QP%A\u0002\u0005}\u0014AB5o\u00052,X\r\u0006\u0003\u0002\u0000\u0005M\u0007bBA?\u0015\u0002\u0007\u0011qP\u0001\u0007S:\u001c\u00150\u00198\u0015\t\u0005}\u0014\u0011\u001c\u0005\b\u0003{Z\u0005\u0019AA@\u0003%Ig.T1hK:$\u0018\r\u0006\u0003\u0002l\u0005}\u0007bBA?\u0019\u0002\u0007\u0011qP\u0001\u000be\u0016\u001cX\r^\"pY>\u0014H\u0003BA@\u0003KDq!! N\u0001\u0004\ty\b\u0006\u0002\u0002N\tq1\u000b\u001e:j]\u001e\u001cu\u000e\\8s\u001fB\u001c8cA\u0015\u0002nB\u0019\u0001,a<\n\u0007\u0005E8K\u0001\u0004B]f4\u0016\r\\\u0001Ag\u000e\fG.\u0019\u0013sK\u001adWm\u0019;%S:$XM\u001d8bY\u0012\"\u0016\u0010]3EK\n,xmZ5oO\u0012\nen]5D_2|'\u000fJ*ue&twmQ8m_J|\u0005o\u001d\u0013%gV\u0011\u0011qP\u0001Bg\u000e\fG.\u0019\u0013sK\u001adWm\u0019;%S:$XM\u001d8bY\u0012\"\u0016\u0010]3EK\n,xmZ5oO\u0012\nen]5D_2|'\u000fJ*ue&twmQ8m_J|\u0005o\u001d\u0013%g\u0002\"B!a?\u0002\u0000B\u0019\u0011Q`\u0015\u000e\u0003\u001dBq!! -\u0001\u0004\ty(A\u0002sK\u0012,\"!a\u001b\u0002\u000b\u001d\u0014X-\u001a8\u0002\re,G\u000e\\8x\u0003\u0011\u0011G.^3\u0002\u0011!\f7\u000f[\"pI\u0016$\"Aa\u0004\u0011\u0007a\u0013\t\"C\u0002\u0003\u0014M\u00131!\u00138u\u0003\u0019)\u0017/^1mgR\u0019QN!\u0007\t\u0013\tm!'!AA\u0002\tu\u0011a\u0001=%cA\u0019\u0001La\b\n\u0007\t\u00052KA\u0002B]f\fab\u0015;sS:<7i\u001c7pe>\u00038\u000fE\u0002\u0002~R\u001a\"\u0001N,\u0015\u0005\t\u0015\u0012!\u0004:fI\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002l\t=\u0002b\u0002B\u0019m\u0001\u0007\u00111`\u0001\u0006IQD\u0017n]\u0001\u0010OJ,WM\u001c\u0013fqR,gn]5p]R!\u00111\u000eB\u001c\u0011\u001d\u0011\td\u000ea\u0001\u0003w\f\u0001#_3mY><H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005}$Q\b\u0005\b\u0005cA\u0004\u0019AA~\u00039\u0011G.^3%Kb$XM\\:j_:$B!a\u001b\u0003D!9!\u0011G\u001dA\u0002\u0005m\u0018A\u00055bg\"\u001cu\u000eZ3%Kb$XM\\:j_:$BA!\u0004\u0003J!9!\u0011\u0007\u001eA\u0002\u0005m\u0018\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\u0011\u0011yEa\u0015\u0015\u00075\u0014\t\u0006C\u0005\u0003\u001cm\n\t\u00111\u0001\u0003\u001e!9!\u0011G\u001eA\u0002\u0005mH\u0003BA~\u0005/Bq!! =\u0001\u0004\ty\b\u0006\u0002\u00028\u0005!Ao\\0t)\u0011\tyHa\u0018\t\u000f\t\u0005d\u00021\u0001\u0003\u001e\u0005\t\u00010A\u0004qi\ncwnY6\u0015\r\u0005}$q\rB6\u0011\u001d\u0011Ig\u0004a\u0001\u0003\u007f\nQ\u0001\\1cK2DqA!\u001c\u0010\u0001\u0004\u0011y'A\u0003qC&\u00148\u000fE\u0003Y\u0005c\u0012)(C\u0002\u0003tM\u0013!\u0002\u0010:fa\u0016\fG/\u001a3?!\u001dA&qOA@\u0005;I1A!\u001fT\u0005\u0019!V\u000f\u001d7fe\u00051\u0001\u000f\u001e'j]\u0016$B!a \u0003\u0000!9!Q\u000e\tA\u0002\t=\u0014A\u00029u)J,W\r\u0006\u0003\u0002\u0000\t\u0015\u0005\"\u0002@\u0012\u0001\u0004A\u0017a\u00039u)f\u0004X\rU1sC6$B!a \u0003\f\"9!Q\u0012\nA\u0002\t=\u0015A\u0001;e!\r\u0011'\u0011S\u0005\u0004\u0005'['a\u0002+za\u0016$UMZ\u0001\raR$\u0016\u0010]3QCJ\fWn\u001d\u000b\u0005\u0003\u007f\u0012I\nC\u0004\u0003\u001cN\u0001\rA!(\u0002\u000fQ\u0004\u0018M]1ngB1!q\u0014BS\u0005\u001fs1\u0001\u0017BQ\u0013\r\u0011\u0019kU\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u00119K!+\u0003\t1K7\u000f\u001e\u0006\u0004\u0005G\u001b\u0016aA:ueB\u0019!qV\u000b\u000e\u00031\u00111a\u001d;s'\t)r\u000b\u0006\u0002\u0003.\u0006Y\u0001/\u0019:f]RDWm]3t)\u0011\tyHa/\t\u000f\tuv\u00031\u0001\u0003@\u0006\u0011\u0001p\u001d\u0019\u0005\u0005\u0003\u00149\r\u0005\u0004\u0003 \n\u0015&1\u0019\t\u0005\u0005\u000b\u00149\r\u0004\u0001\u0005\u0019\t%'1XA\u0001\u0002\u0003\u0015\tAa3\u0003\u0007}#\u0013'\u0005\u0003\u0003N\nu\u0001c\u0001-\u0003P&\u0019!\u0011[*\u0003\u000f9{G\u000f[5oO\u00061\u0001/\u0019:b[N$B!a \u0003X\"9!1\u001b\rA\u0002\te\u0007C\u0002BP\u0005K\u000bi!\u0001\u0005ce\u0006\u001c7.\u001a;t)\u0011\tyHa8\t\u000f\tu\u0016\u00041\u0001\u0003bB\"!1\u001dBt!\u0019\u0011yJ!*\u0003fB!!Q\u0019Bt\t1\u0011IOa8\u0002\u0002\u0003\u0005)\u0011\u0001Bf\u0005\ryFE\r\u000b\u0005\u0003\u007f\u0012i\u000fC\u0004\u0003\u001cj\u0001\rAa<\u0011\r\t}%QUA\u0010\u0003\u001d\u0001\u0018M]3oiN$B!a \u0003v\"9!q_\u000eA\u0002\t=\u0018A\u00019t\u0003\u0019\u0011XMZ5oKR!\u0011q\u0010B\u007f\u0011\u001d\u0011y\u0010\ba\u0001\u0007\u0003\tA\u0001Z3ggB\u0019!ma\u0001\n\t\r\u00151q\u0001\u0002\u0006'\u000e|\u0007/Z\u0005\u0004\u0007\u0013y%AB*d_B,7/\u0001\u0004c_VtGm\u001d\u000b\u0007\u0003\u007f\u001ayaa\u0005\t\u000f\rEQ\u00041\u0001\u0002 \u0005\u0011An\u001c\u0005\b\u0007+i\u0002\u0019AA\u0010\u0003\tA\u0017.A\u0003eK\n,x\r\u0006\u0003\u0002\u0000\rm\u0001bBB\u000f=\u0001\u0007\u0011qD\u0001\u0003iB\f1\u0002Z3ck\u001e\u001cFO]5oOR!\u0011qPB\u0012\u0011\u001d\u0019ib\ba\u0001\u0003?\t1\u0002]1sC6\u001cFO]5oOR!\u0011qPB\u0015\u0011\u001d\u0019i\u0002\ta\u0001\u0003?\t\u0001\u0003^=qKB\u000b'/Y7t'R\u0014\u0018N\\4\u0015\t\u0005}4q\u0006\u0005\b\u0007;\t\u0003\u0019AA\u0010)\u0011\tyha\r\t\u000f\ru!\u00051\u0001\u0002 A!\u0011\u0011IB\u001c\u0013\r\u0019Id\u0014\u0002\f'fl'm\u001c7UC\ndW\r"
)
public interface TypeDebugging {
   noPrint$ noPrint();

   typeDebug$ typeDebug();

   // $FF: synthetic method
   static String paramString$(final TypeDebugging $this, final Types.Type tp) {
      return $this.paramString(tp);
   }

   default String paramString(final Types.Type tp) {
      return this.typeDebug().str().params(tp.params());
   }

   // $FF: synthetic method
   static String typeParamsString$(final TypeDebugging $this, final Types.Type tp) {
      return $this.typeParamsString(tp);
   }

   default String typeParamsString(final Types.Type tp) {
      TypeDebugging$typeDebug$str$ var10000 = this.typeDebug().str();
      List var10001 = tp.typeParams();
      if (var10001 == null) {
         throw null;
      } else {
         List map_this = var10001;
         Object var12;
         if (map_this == .MODULE$) {
            var12 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_this.head()).defString(), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_rest.head()).defString(), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var12 = map_h;
         }

         Object var7 = null;
         Object var8 = null;
         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         return var10000.brackets((List)var12);
      }
   }

   // $FF: synthetic method
   static String debugString$(final TypeDebugging $this, final Types.Type tp) {
      return $this.debugString(tp);
   }

   default String debugString(final Types.Type tp) {
      typeDebug$ var10000 = this.typeDebug();
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.scala$reflect$internal$TypeDebugging$typeDebug$$debug(tp);
      }
   }

   // $FF: synthetic method
   static String $anonfun$typeParamsString$1(final Symbols.Symbol x$4) {
      return x$4.defString();
   }

   static void $init$(final TypeDebugging $this) {
   }

   public class noPrint$ implements Function1 {
      // $FF: synthetic field
      private final SymbolTable $outer;

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

      public boolean skipScalaName(final Names.Name name) {
         boolean var4;
         label62: {
            Names.TypeName var10000 = this.$outer.tpnme().Any();
            if (var10000 == null) {
               if (name == null) {
                  break label62;
               }
            } else if (var10000.equals(name)) {
               break label62;
            }

            label63: {
               var10000 = this.$outer.tpnme().Nothing();
               if (var10000 == null) {
                  if (name == null) {
                     break label63;
                  }
               } else if (var10000.equals(name)) {
                  break label63;
               }

               label64: {
                  var10000 = this.$outer.tpnme().AnyRef();
                  if (var10000 == null) {
                     if (name == null) {
                        break label64;
                     }
                  } else if (var10000.equals(name)) {
                     break label64;
                  }

                  var4 = false;
                  return var4;
               }

               var4 = true;
               return var4;
            }

            var4 = true;
            return var4;
         }

         var4 = true;
         return var4;
      }

      public boolean skipRefTree(final Trees.RefTree t) {
         boolean var2 = false;
         Trees.Select var3 = null;
         if (t instanceof Trees.Select) {
            var2 = true;
            var3 = (Trees.Select)t;
            Trees.Tree var4 = var3.qualifier();
            Names.Name name = var3.name();
            if (var4 instanceof Trees.Select) {
               Trees.Select var6 = (Trees.Select)var4;
               Trees.Tree var7 = var6.qualifier();
               Names.Name var8 = var6.name();
               if (var7 instanceof Trees.Ident) {
                  label101: {
                     Names.Name var9 = ((Trees.Ident)var7).name();
                     Names.TermName var10000 = this.$outer.nme().ROOTPKG();
                     if (var10000 == null) {
                        if (var9 != null) {
                           break label101;
                        }
                     } else if (!var10000.equals(var9)) {
                        break label101;
                     }

                     var10000 = this.$outer.nme().scala_();
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label101;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label101;
                     }

                     if (this.skipScalaName(name)) {
                        return true;
                     }
                  }
               }
            }
         }

         if (var2) {
            label83: {
               Trees.Tree sel = var3.qualifier();
               Names.Name name = var3.name();
               Symbols.Symbol var21 = sel.symbol();
               Symbols.ModuleSymbol var12 = this.$outer.definitions().ScalaPackage();
               if (var21 == null) {
                  if (var12 != null) {
                     break label83;
                  }
               } else if (!var21.equals(var12)) {
                  break label83;
               }

               if (this.skipScalaName(name)) {
                  return true;
               }
            }
         }

         if (var2) {
            Trees.Tree var13 = var3.qualifier();
            Names.Name var14 = var3.name();
            if (var13 instanceof Trees.Super) {
               Trees.Super var15 = (Trees.Super)var13;
               Trees.Tree var16 = var15.qual();
               Names.TypeName var17 = var15.mix();
               if (var16 instanceof Trees.This) {
                  label106: {
                     Names.TypeName var18 = ((Trees.This)var16).qual();
                     Names.Name var22 = this.$outer.tpnme().EMPTY();
                     if (var22 == null) {
                        if (var18 != null) {
                           break label106;
                        }
                     } else if (!var22.equals(var18)) {
                        break label106;
                     }

                     var22 = this.$outer.tpnme().EMPTY();
                     if (var22 == null) {
                        if (var17 != null) {
                           break label106;
                        }
                     } else if (!var22.equals(var17)) {
                        break label106;
                     }

                     Names.TermName var24 = this.$outer.nme().CONSTRUCTOR();
                     if (var24 == null) {
                        if (var14 == null) {
                           return true;
                        }
                     } else if (var24.equals(var14)) {
                        return true;
                     }
                  }
               }
            }
         }

         if (t instanceof Trees.Ident) {
            Names.Name var19 = ((Trees.Ident)t).name();
            Names.TermName var25 = this.$outer.nme().ROOTPKG();
            if (var25 == null) {
               if (var19 == null) {
                  return true;
               }
            } else if (var25.equals(var19)) {
               return true;
            }
         }

         return this.skipSym(((Trees.SymTree)t).symbol());
      }

      public boolean skipSym(final Symbols.Symbol sym) {
         if (sym == null) {
            return false;
         } else {
            boolean var10000;
            if (this.$outer.definitions().NothingClass().equals(sym)) {
               var10000 = true;
            } else {
               label46: {
                  Symbols.ClassSymbol var2 = this.$outer.definitions().AnyClass();
                  if (var2 != null) {
                     if (var2.equals(sym)) {
                        var10000 = true;
                        break label46;
                     }
                  }

                  var10000 = false;
               }
            }

            if (var10000) {
               return true;
            } else {
               Symbols.ModuleSymbol var3 = this.$outer.definitions().PredefModule();
               if (var3 == null) {
                  if (sym == null) {
                     return true;
                  }
               } else if (var3.equals(sym)) {
                  return true;
               }

               Symbols.ClassSymbol var4 = this.$outer.definitions().ObjectClass();
               if (var4 == null) {
                  if (sym == null) {
                     return true;
                  }
               } else if (var4.equals(sym)) {
                  return true;
               }

               return sym.hasPackageFlag();
            }
         }
      }

      public boolean skipType(final Types.Type tpe) {
         return tpe == null || this.skipSym(tpe.typeSymbolDirect());
      }

      public boolean skip(final Trees.Tree t) {
         while(true) {
            boolean var2 = false;
            Trees.Block var3 = null;
            if (this.$outer.EmptyTree().equals(t)) {
               return true;
            }

            if (t instanceof Trees.PackageDef) {
               return true;
            }

            if (t instanceof Trees.RefTree) {
               Trees.RefTree var4 = (Trees.RefTree)t;
               return this.skipRefTree(var4);
            }

            if (t instanceof Trees.TypeBoundsTree) {
               Trees.TypeBoundsTree var5 = (Trees.TypeBoundsTree)t;
               Trees.Tree lo = var5.lo();
               Trees.Tree hi = var5.hi();
               if (!this.skip(lo)) {
                  return false;
               }

               t = hi;
            } else {
               if (t instanceof Trees.Block) {
                  var2 = true;
                  var3 = (Trees.Block)t;
                  List var8 = var3.stats();
                  Trees.Tree expr = var3.expr();
                  if (.MODULE$.equals(var8)) {
                     t = expr;
                     continue;
                  }
               }

               if (t instanceof Trees.Apply) {
                  Trees.Apply var10 = (Trees.Apply)t;
                  Trees.Tree fn = var10.fun();
                  List var12 = var10.args();
                  if (.MODULE$.equals(var12)) {
                     t = fn;
                     continue;
                  }
               }

               if (var2) {
                  List var13 = var3.stats();
                  Trees.Tree expr = var3.expr();
                  if (var13 instanceof scala.collection.immutable..colon.colon) {
                     scala.collection.immutable..colon.colon var15 = (scala.collection.immutable..colon.colon)var13;
                     Trees.Tree stmt = (Trees.Tree)var15.head();
                     List var17 = var15.next$access$1();
                     if (.MODULE$.equals(var17)) {
                        if (this.skip(stmt)) {
                           t = expr;
                           continue;
                        }

                        return false;
                     }
                  }
               }

               Trees.Tree rhs;
               label107: {
                  if (t instanceof Trees.DefDef) {
                     label101: {
                        Trees.DefDef var18 = (Trees.DefDef)t;
                        Names.TermName var19 = var18.name();
                        List var20 = var18.tparams();
                        List var21 = var18.vparamss();
                        rhs = var18.rhs();
                        Names.TermName var10000 = this.$outer.nme().CONSTRUCTOR();
                        if (var10000 == null) {
                           if (var19 != null) {
                              break label101;
                           }
                        } else if (!var10000.equals(var19)) {
                           break label101;
                        }

                        if (.MODULE$.equals(var20)) {
                           List var26 = package$.MODULE$.ListOfNil();
                           if (var26 == null) {
                              if (var21 == null) {
                                 break label107;
                              }
                           } else if (var26.equals(var21)) {
                              break label107;
                           }
                        }
                     }
                  }

                  if (t instanceof Trees.Literal) {
                     Constants.Constant var23 = ((Trees.Literal)t).value();
                     if (var23 != null) {
                        Object var24 = var23.value();
                        BoxedUnit var27 = BoxedUnit.UNIT;
                        if (var27 == null) {
                           if (var24 == null) {
                              return true;
                           }
                        } else if (var27.equals(var24)) {
                           return true;
                        }
                     }
                  }

                  if (t instanceof Trees.TypeTree) {
                     Trees.TypeTree var25 = (Trees.TypeTree)t;
                     return this.skipType(var25.tpe());
                  }

                  return this.skipSym(t.symbol());
               }

               t = rhs;
            }
         }
      }

      public boolean apply(final Trees.Tree t) {
         return this.skip(t);
      }

      public noPrint$() {
         if (TypeDebugging.this == null) {
            throw null;
         } else {
            this.$outer = TypeDebugging.this;
            super();
         }
      }
   }

   public class typeDebug$ implements AnsiColor {
      private volatile TypeDebugging$typeDebug$str$ str$module;
      private boolean scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk;
      // $FF: synthetic field
      private final SymbolTable $outer;

      public String inLightRed(final String s) {
         return TypeDebugging.AnsiColor.super.inLightRed(s);
      }

      public String inLightBlue(final String s) {
         return TypeDebugging.AnsiColor.super.inLightBlue(s);
      }

      public String inLightGreen(final String s) {
         return TypeDebugging.AnsiColor.super.inLightGreen(s);
      }

      public String inLightYellow(final String s) {
         return TypeDebugging.AnsiColor.super.inLightYellow(s);
      }

      public String inLightMagenta(final String s) {
         return TypeDebugging.AnsiColor.super.inLightMagenta(s);
      }

      public String inLightCyan(final String s) {
         return TypeDebugging.AnsiColor.super.inLightCyan(s);
      }

      public String inGreen(final String s) {
         return TypeDebugging.AnsiColor.super.inGreen(s);
      }

      public String inRed(final String s) {
         return TypeDebugging.AnsiColor.super.inRed(s);
      }

      public String inBlue(final String s) {
         return TypeDebugging.AnsiColor.super.inBlue(s);
      }

      public String inCyan(final String s) {
         return TypeDebugging.AnsiColor.super.inCyan(s);
      }

      public String inMagenta(final String s) {
         return TypeDebugging.AnsiColor.super.inMagenta(s);
      }

      public String resetColor(final String s) {
         return TypeDebugging.AnsiColor.super.resetColor(s);
      }

      public TypeDebugging$typeDebug$str$ str() {
         if (this.str$module == null) {
            this.str$lzycompute$1();
         }

         return this.str$module;
      }

      public boolean scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk() {
         return this.scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk;
      }

      public final void scala$reflect$internal$TypeDebugging$AnsiColor$_setter_$scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk_$eq(final boolean x$1) {
         this.scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk = x$1;
      }

      private String to_s(final Object x) {
         if (x instanceof Trees.Tree && ((Trees.Tree)x).scala$reflect$internal$Trees$Tree$$$outer() == this.$outer ? true : x instanceof Types.Type && ((Types.Type)x).scala$reflect$internal$Types$Type$$$outer() == this.$outer) {
            return String.valueOf(x);
         } else if (x instanceof IterableOnce) {
            Iterator var10000 = ((IterableOnce)x).iterator();
            String mkString_sep = ", ";
            if (var10000 == null) {
               throw null;
            } else {
               return var10000.mkString("", mkString_sep, "");
            }
         } else {
            return x instanceof Product ? ((Product)x).productIterator().mkString("(", ", ", ")") : String.valueOf(x);
         }
      }

      public String ptBlock(final String label, final Seq pairs) {
         if (pairs.isEmpty()) {
            return (new StringBuilder(3)).append(label).append("{ }").toString();
         } else {
            int width = BoxesRunTime.unboxToInt(((IterableOnceOps)pairs.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$ptBlock$1(x$1)))).max(scala.math.Ordering.Int..MODULE$));
            String fmt = (new StringBuilder(6)).append("%-").append(width + 1).append("s %s").toString();
            return ((Seq)pairs.map((x0$1) -> {
               if (x0$1 != null) {
                  String k = (String)x0$1._1();
                  Object v = x0$1._2();
                  return scala.collection.StringOps..MODULE$.format$extension(fmt, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{k, this.to_s(v)}));
               } else {
                  throw new MatchError((Object)null);
               }
            })).mkString((new StringBuilder(5)).append(label).append(" {\n  ").toString(), "\n  ", "\n}");
         }
      }

      public String ptLine(final Seq pairs) {
         IterableOnceOps var10000 = (IterableOnceOps)((IterableOps)((IterableOps)pairs.map((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               Object v = x0$1._2();
               return new Tuple2(k, this.to_s(v));
            } else {
               throw new MatchError((Object)null);
            }
         })).filterNot((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$ptLine$2(x0$2)))).map((x0$3) -> {
            if (x0$3 != null) {
               String var1 = (String)x0$3._1();
               String v = (String)x0$3._2();
               if ("".equals(var1)) {
                  return v;
               }
            }

            if (x0$3 != null) {
               String k = (String)x0$3._1();
               String v = (String)x0$3._2();
               return (new StringBuilder(1)).append(k).append("=").append(v).toString();
            } else {
               throw new MatchError((Object)null);
            }
         });
         String mkString_sep = ", ";
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.mkString("", mkString_sep, "");
         }
      }

      public String ptTree(final Trees.Tree t) {
         boolean var2 = false;
         Trees.ClassDef var3 = null;
         if (t instanceof Trees.PackageDef) {
            Trees.RefTree pid = ((Trees.PackageDef)t).pid();
            return (new StringBuilder(8)).append("package ").append(pid).toString();
         } else if (t instanceof Trees.ModuleDef) {
            Names.TermName name = ((Trees.ModuleDef)t).name();
            return (new StringBuilder(7)).append("object ").append(name).toString();
         } else if (t instanceof Trees.DefDef) {
            Trees.DefDef var6 = (Trees.DefDef)t;
            Names.TermName name = var6.name();
            List tparams = var6.tparams();
            return (new StringBuilder(4)).append("def ").append(name).append(this.ptTypeParams(tparams)).toString();
         } else {
            if (t instanceof Trees.ClassDef) {
               var2 = true;
               var3 = (Trees.ClassDef)t;
               Names.TypeName name = var3.name();
               List var10 = var3.tparams();
               if (.MODULE$.equals(var10) && t.symbol() != null && t.symbol().isModuleClass()) {
                  return (new StringBuilder(13)).append("module class ").append(name).toString();
               }
            }

            if (var2) {
               Names.TypeName name = var3.name();
               List tparams = var3.tparams();
               return (new StringBuilder(6)).append("class ").append(name).append(this.ptTypeParams(tparams)).toString();
            } else if (t instanceof Trees.TypeDef) {
               Trees.TypeDef var13 = (Trees.TypeDef)t;
               return this.ptTypeParam(var13);
            } else if (t instanceof Trees.TypeBoundsTree) {
               Trees.TypeBoundsTree var14 = (Trees.TypeBoundsTree)t;
               Trees.Tree lo = var14.lo();
               Trees.Tree hi = var14.hi();
               noPrint$ var10000 = this.$outer.noPrint();
               if (var10000 == null) {
                  throw null;
               } else {
                  String lo_s = var10000.skip(lo) ? "" : (new StringBuilder(4)).append(" >: ").append(this.ptTree(lo)).toString();
                  var10000 = this.$outer.noPrint();
                  if (var10000 == null) {
                     throw null;
                  } else {
                     String hi_s = var10000.skip(hi) ? "" : (new StringBuilder(4)).append(" <: ").append(this.ptTree(hi)).toString();
                     return (new StringBuilder(0)).append(lo_s).append(hi_s).toString();
                  }
               }
            } else {
               return t.symbol() != null && t.symbol() != this.$outer.NoSymbol() ? String.valueOf(t.symbol().rawInfo().safeToString()) : this.to_s(t);
            }
         }
      }

      public String ptTypeParam(final Trees.TypeDef td) {
         if (td != null) {
            Names.TypeName name = td.name();
            List tparams = td.tparams();
            Trees.Tree rhs = td.rhs();
            return (new StringBuilder(0)).append(name.toString()).append(this.ptTypeParams(tparams)).append(this.ptTree(rhs)).toString();
         } else {
            throw new MatchError((Object)null);
         }
      }

      public String ptTypeParams(final List tparams) {
         TypeDebugging$typeDebug$str$ var10000 = this.str();
         if (tparams == null) {
            throw null;
         } else {
            Object var10001;
            if (tparams == .MODULE$) {
               var10001 = .MODULE$;
            } else {
               Trees.TypeDef var6 = (Trees.TypeDef)tparams.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.ptTypeParam(var6), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)tparams.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var6 = (Trees.TypeDef)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.ptTypeParam(var6), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10001 = map_h;
            }

            Object var7 = null;
            Object var8 = null;
            Object var9 = null;
            Object var10 = null;
            return var10000.brackets((List)var10001);
         }
      }

      public String scala$reflect$internal$TypeDebugging$typeDebug$$debug(final Types.Type tp) {
         if (tp instanceof Types.TypeRef) {
            Types.TypeRef var2 = (Types.TypeRef)tp;
            Types.Type pre = var2.pre();
            Symbols.Symbol sym = var2.sym();
            List args = var2.args();
            return (new StringBuilder(2)).append(this.scala$reflect$internal$TypeDebugging$typeDebug$$debug(pre)).append(".").append(sym.nameString()).append(".").append(this.str().tparams(args)).toString();
         } else if (tp instanceof Types.ThisType) {
            Symbols.Symbol sym = ((Types.ThisType)tp).sym();
            return (new StringBuilder(5)).append(sym.nameString()).append(".this").toString();
         } else if (tp instanceof Types.SingleType) {
            Types.SingleType var7 = (Types.SingleType)tp;
            Types.Type pre = var7.pre();
            Symbols.Symbol sym = var7.sym();
            return (new StringBuilder(6)).append(this.scala$reflect$internal$TypeDebugging$typeDebug$$debug(pre)).append(".").append(sym.nameString()).append(".type").toString();
         } else if (tp instanceof Types.RefinedType) {
            Types.RefinedType var10 = (Types.RefinedType)tp;
            List ps = var10.parents();
            Scopes.Scope decls = var10.decls();
            return (new StringBuilder(1)).append(this.str().parents(ps)).append(" ").append(this.str().refine(decls)).toString();
         } else if (tp instanceof Types.ClassInfoType) {
            Types.ClassInfoType var13 = (Types.ClassInfoType)tp;
            List ps = var13.parents();
            Scopes.Scope decls = var13.decls();
            Symbols.Symbol clazz = var13.typeSymbol();
            return (new StringBuilder(8)).append("class ").append(clazz.nameString()).append(" ").append(this.str().parents(ps)).append(" ").append(this.str().refine(decls)).toString();
         } else if (tp instanceof Types.PolyType) {
            Types.PolyType var17 = (Types.PolyType)tp;
            List tparams = var17.typeParams();
            Types.Type result = var17.resultType();
            return (new StringBuilder(0)).append(this.str().brackets(tparams)).append(this.scala$reflect$internal$TypeDebugging$typeDebug$$debug(result)).toString();
         } else if (tp instanceof Types.TypeBounds) {
            Types.TypeBounds var20 = (Types.TypeBounds)tp;
            Types.Type lo = var20.lo();
            Types.Type hi = var20.hi();
            return this.str().bounds(lo, hi);
         } else if (tp instanceof Types.TypeVar) {
            return String.valueOf((Types.TypeVar)tp);
         } else if (tp instanceof Types.ExistentialType) {
            Types.ExistentialType var23 = (Types.ExistentialType)tp;
            List tparams = var23.quantified();
            Types.Type qtpe = var23.underlying();
            return (new StringBuilder(9)).append("forSome ").append(this.str().brackets(tparams)).append(" ").append(this.scala$reflect$internal$TypeDebugging$typeDebug$$debug(qtpe)).toString();
         } else {
            return (new StringBuilder(2)).append("?").append(package$.MODULE$.shortClassOfInstance(tp)).append("?").toString();
         }
      }

      public String debugString(final Types.Type tp) {
         return this.scala$reflect$internal$TypeDebugging$typeDebug$$debug(tp);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$TypeDebugging$typeDebug$$$outer() {
         return this.$outer;
      }

      private final void str$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.str$module == null) {
               this.str$module = new TypeDebugging$typeDebug$str$(this);
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      // $FF: synthetic method
      public static final int $anonfun$ptBlock$1(final Tuple2 x$1) {
         return ((String)x$1._1()).length();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$ptLine$2(final Tuple2 x0$2) {
         if (x0$2 != null) {
            String var10000 = (String)x0$2._2();
            String var1 = "";
            if (var10000 != null) {
               if (var10000.equals(var1)) {
                  return true;
               }
            }

            return false;
         } else {
            throw new MatchError((Object)null);
         }
      }

      // $FF: synthetic method
      public static final String $anonfun$ptTypeParams$1(final typeDebug$ $this, final Trees.TypeDef td) {
         return $this.ptTypeParam(td);
      }

      public typeDebug$() {
         if (TypeDebugging.this == null) {
            throw null;
         } else {
            this.$outer = TypeDebugging.this;
            super();
            TypeDebugging.AnsiColor.$init$(this);
            Statics.releaseFence();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class AnsiColor$ implements AnsiColor {
      public static final AnsiColor$ MODULE$ = new AnsiColor$();
      private static boolean scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk;

      static {
         AnsiColor$ var10000 = MODULE$;
         TypeDebugging.AnsiColor.$init$(MODULE$);
      }

      public String inLightRed(final String s) {
         return TypeDebugging.AnsiColor.super.inLightRed(s);
      }

      public String inLightBlue(final String s) {
         return TypeDebugging.AnsiColor.super.inLightBlue(s);
      }

      public String inLightGreen(final String s) {
         return TypeDebugging.AnsiColor.super.inLightGreen(s);
      }

      public String inLightYellow(final String s) {
         return TypeDebugging.AnsiColor.super.inLightYellow(s);
      }

      public String inLightMagenta(final String s) {
         return TypeDebugging.AnsiColor.super.inLightMagenta(s);
      }

      public String inLightCyan(final String s) {
         return TypeDebugging.AnsiColor.super.inLightCyan(s);
      }

      public String inGreen(final String s) {
         return TypeDebugging.AnsiColor.super.inGreen(s);
      }

      public String inRed(final String s) {
         return TypeDebugging.AnsiColor.super.inRed(s);
      }

      public String inBlue(final String s) {
         return TypeDebugging.AnsiColor.super.inBlue(s);
      }

      public String inCyan(final String s) {
         return TypeDebugging.AnsiColor.super.inCyan(s);
      }

      public String inMagenta(final String s) {
         return TypeDebugging.AnsiColor.super.inMagenta(s);
      }

      public String resetColor(final String s) {
         return TypeDebugging.AnsiColor.super.resetColor(s);
      }

      public boolean scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk() {
         return scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk;
      }

      public final void scala$reflect$internal$TypeDebugging$AnsiColor$_setter_$scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk_$eq(final boolean x$1) {
         scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk = x$1;
      }

      public String StringColorOps(final String s) {
         return s;
      }
   }

   public interface AnsiColor extends scala.io.AnsiColor {
      void scala$reflect$internal$TypeDebugging$AnsiColor$_setter_$scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk_$eq(final boolean x$1);

      boolean scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk();

      private String inColor(final String s, final String color) {
         if (this.scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk()) {
            String var3 = "";
            if (s == null) {
               return (new StringBuilder(4)).append(color).append(s).append("\u001b[0m").toString();
            }

            if (!s.equals(var3)) {
               return (new StringBuilder(4)).append(color).append(s).append("\u001b[0m").toString();
            }
         }

         return s;
      }

      private String inBold(final String s, final String color) {
         if (this.scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk()) {
            String var3 = "";
            if (s == null) {
               return (new StringBuilder(8)).append(color).append("\u001b[1m").append(s).append("\u001b[0m").toString();
            }

            if (!s.equals(var3)) {
               return (new StringBuilder(8)).append(color).append("\u001b[1m").append(s).append("\u001b[0m").toString();
            }
         }

         return s;
      }

      default String inLightRed(final String s) {
         return this.inColor(s, "\u001b[31m");
      }

      default String inLightBlue(final String s) {
         return this.inColor(s, "\u001b[34m");
      }

      default String inLightGreen(final String s) {
         return this.inColor(s, "\u001b[32m");
      }

      default String inLightYellow(final String s) {
         return this.inColor(s, "\u001b[33m");
      }

      default String inLightMagenta(final String s) {
         return this.inColor(s, "\u001b[35m");
      }

      default String inLightCyan(final String s) {
         return this.inColor(s, "\u001b[36m");
      }

      default String inGreen(final String s) {
         return this.inBold(s, "\u001b[32m");
      }

      default String inRed(final String s) {
         return this.inBold(s, "\u001b[31m");
      }

      default String inBlue(final String s) {
         return this.inBold(s, "\u001b[34m");
      }

      default String inCyan(final String s) {
         return this.inBold(s, "\u001b[36m");
      }

      default String inMagenta(final String s) {
         return this.inBold(s, "\u001b[35m");
      }

      default String resetColor(final String s) {
         return this.scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk() ? (new StringBuilder(4)).append(s).append("\u001b[0m").toString() : s;
      }

      static void $init$(final AnsiColor $this) {
         $this.scala$reflect$internal$TypeDebugging$AnsiColor$_setter_$scala$reflect$internal$TypeDebugging$AnsiColor$$colorsOk_$eq(PropertiesTrait.coloredOutputEnabled$(scala.util.Properties..MODULE$));
      }
   }
}
