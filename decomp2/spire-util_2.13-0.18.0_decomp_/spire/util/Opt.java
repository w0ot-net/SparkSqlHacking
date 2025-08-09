package spire.util;

import cats.kernel.Eq;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\rUx!B\u001c9\u0011\u0003id!B 9\u0011\u0003\u0001\u0005\"B$\u0002\t\u0003A\u0005\"B%\u0002\t\u0003Q\u0005bBA~\u0003\u0011\u0005\u0011Q \u0005\b\u0005\u000f\tA\u0011\u0001B\u0005\u0011\u001d\u00119\"\u0001C\u0002\u00053AqA!\u000f\u0002\t\u000b\u0011Y\u0004C\u0004\u0003J\u0005!)Aa\u0013\t\u000f\t]\u0013\u0001\"\u0002\u0003Z!9!QM\u0001\u0005\u0006\t\u001d\u0004b\u0002B:\u0003\u0011\u0015!Q\u000f\u0005\b\u0005\u0003\u000bAQ\u0001BB\u0011\u001d\u0011)*\u0001C\u0003\u0005/CqAa,\u0002\t\u000b\u0011\t\fC\u0004\u0003J\u0006!)Aa3\t\u000f\t\u001d\u0018\u0001\"\u0002\u0003j\"91\u0011A\u0001\u0005\u0006\r\r\u0001bBB\r\u0003\u0011\u001511\u0004\u0005\b\u0007S\tAQAB\u0016\u0011\u001d\u0019I$\u0001C\u0003\u0007wAqa!\u0015\u0002\t\u000b\u0019\u0019\u0006C\u0004\u0004f\u0005!)aa\u001a\t\u000f\re\u0014\u0001\"\u0002\u0004|!91\u0011S\u0001\u0005\u0006\rM\u0005bBBQ\u0003\u0011\u001511\u0015\u0005\b\u0007w\u000bAQAB_\u0011%\u0019).AA\u0001\n\u000b\u00199\u000eC\u0005\u0004d\u0006\t\t\u0011\"\u0002\u0004f\u001a!q\b\u000f\u0002N\u0011!\u0011VD!b\u0001\n\u0003\u0019\u0006\u0002C0\u001e\u0005\u0003\u0005\u000b\u0011\u0002+\t\u000b\u001dkB\u0011\u00011\t\u000b\rlB\u0011\u00013\t\u000b!lB\u0011\u00013\t\u000b%lB\u0011\u00013\t\u000b)lB\u0011A*\t\u000b-lB\u0011\t7\t\u000balB\u0011A=\t\r}lB\u0011AA\u0001\u0011\u001d\t\t\"\bC\u0001\u0003'Aq!!\t\u001e\t\u0003\t\u0019\u0003C\u0004\u0002<u!\t!!\u0010\t\u000f\u00055S\u0004\"\u0001\u0002P!9\u0011\u0011L\u000f\u0005\u0002\u0005m\u0003bBA2;\u0011\u0005\u0011Q\r\u0005\b\u0003sjB\u0011AA>\u0011\u001d\tI)\bC\u0001\u0003\u0017Cq!!%\u001e\t\u0003\t\u0019\nC\u0004\u0002\u0018v!\t!!'\t\u000f\u00055V\u0004\"\u0001\u00020\"9\u0011qW\u000f\u0005\u0002\u0005e\u0006bBAh;\u0011\u0005\u0011\u0011\u001b\u0005\n\u0003Cl\u0012\u0011!C!\u0003GD\u0011\"a;\u001e\u0003\u0003%\t%!<\u0002\u0007=\u0003HO\u0003\u0002:u\u0005!Q\u000f^5m\u0015\u0005Y\u0014!B:qSJ,7\u0001\u0001\t\u0003}\u0005i\u0011\u0001\u000f\u0002\u0004\u001fB$8CA\u0001B!\t\u0011U)D\u0001D\u0015\u0005!\u0015!B:dC2\f\u0017B\u0001$D\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!P\u0001\u0006CB\u0004H._\u000b\u0004\u0017\u0006UHc\u0001'\u0002xB!a(HAz+\tqek\u0005\u0002\u001e\u001fB\u0011!\tU\u0005\u0003#\u000e\u0013a!\u00118z-\u0006d\u0017a\u0001:fMV\tA\u000b\u0005\u0002V-2\u0001AAB,\u001e\t\u000b\u0007\u0001LA\u0001B#\tIF\f\u0005\u0002C5&\u00111l\u0011\u0002\b\u001d>$\b.\u001b8h!\t\u0011U,\u0003\u0002_\u0007\n\u0019\u0011I\\=\u0002\tI,g\r\t\u000b\u0003C\n\u00042AP\u000fU\u0011\u0015\u0011\u0006\u00051\u0001U\u0003%I7\u000fR3gS:,G-F\u0001f!\t\u0011e-\u0003\u0002h\u0007\n9!i\\8mK\u0006t\u0017\u0001\u00038p]\u0016k\u0007\u000f^=\u0002\u000f%\u001cX)\u001c9us\u0006\u0019q-\u001a;\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u001c\t\u0003]Vt!a\\:\u0011\u0005A\u001cU\"A9\u000b\u0005Id\u0014A\u0002\u001fs_>$h(\u0003\u0002u\u0007\u00061\u0001K]3eK\u001aL!A^<\u0003\rM#(/\u001b8h\u0015\t!8)\u0001\u0004gS2$XM\u001d\u000b\u0003CjDQa\u001f\u0014A\u0002q\f\u0011A\u001a\t\u0005\u0005v$V-\u0003\u0002\u007f\u0007\nIa)\u001e8di&|g.M\u0001\u0004[\u0006\u0004X\u0003BA\u0002\u0003\u0013!B!!\u0002\u0002\u000eA!a(HA\u0004!\r)\u0016\u0011\u0002\u0003\u0007\u0003\u00179#\u0019\u0001-\u0003\u0003\tCaa_\u0014A\u0002\u0005=\u0001#\u0002\"~)\u0006\u001d\u0011a\u00024mCRl\u0015\r]\u000b\u0005\u0003+\tY\u0002\u0006\u0003\u0002\u0018\u0005u\u0001\u0003\u0002 \u001e\u00033\u00012!VA\u000e\t\u0019\tY\u0001\u000bb\u00011\"11\u0010\u000ba\u0001\u0003?\u0001RAQ?U\u0003/\tAAZ8mIV!\u0011QEA\u0016)\u0011\t9#!\r\u0015\t\u0005%\u0012Q\u0006\t\u0004+\u0006-BABA\u0006S\t\u0007\u0001\f\u0003\u0004|S\u0001\u0007\u0011q\u0006\t\u0006\u0005v$\u0016\u0011\u0006\u0005\t\u0003gIC\u00111\u0001\u00026\u0005\t!\rE\u0003C\u0003o\tI#C\u0002\u0002:\r\u0013\u0001\u0002\u00102z]\u0006lWMP\u0001\nO\u0016$xJ]#mg\u0016,B!a\u0010\u0002DQ!\u0011\u0011IA$!\r)\u00161\t\u0003\b\u0003\u0017Q#\u0019AA##\t!F\f\u0003\u0005\u0002J)\"\t\u0019AA&\u0003\u001d!WMZ1vYR\u0004RAQA\u001c\u0003\u0003\nQbZ3u\u001fJ,En]3GCN$X\u0003BA)\u0003+\"B!a\u0015\u0002XA\u0019Q+!\u0016\u0005\u000f\u0005-1F1\u0001\u0002F!9\u0011\u0011J\u0016A\u0002\u0005M\u0013\u0001\u0003;p\u001fB$\u0018n\u001c8\u0016\u0005\u0005u\u0003\u0003\u0002\"\u0002`QK1!!\u0019D\u0005\u0019y\u0005\u000f^5p]\u00061Ao\u001c'jgR,\"!a\u001a\u0011\u000b\u0005%\u00141\u000f+\u000f\t\u0005-\u0014q\u000e\b\u0004a\u00065\u0014\"\u0001#\n\u0007\u0005E4)A\u0004qC\u000e\\\u0017mZ3\n\t\u0005U\u0014q\u000f\u0002\u0005\u0019&\u001cHOC\u0002\u0002r\r\u000b\u0001bY8oi\u0006Lgn]\u000b\u0005\u0003{\n)\tF\u0002f\u0003\u007fBq!!!/\u0001\u0004\t\u0019)\u0001\u0003fY\u0016l\u0007cA+\u0002\u0006\u00129\u0011q\u0011\u0018C\u0002\u0005\u0015#AA!2\u0003\u0019)\u00070[:ugR\u0019Q-!$\t\r\u0005=u\u00061\u0001}\u0003\u0005\u0001\u0018A\u00024pe\u0006dG\u000eF\u0002f\u0003+Ca!a$1\u0001\u0004a\u0018a\u00024pe\u0016\f7\r[\u000b\u0005\u00037\u000bI\u000b\u0006\u0003\u0002\u001e\u0006\r\u0006c\u0001\"\u0002 &\u0019\u0011\u0011U\"\u0003\tUs\u0017\u000e\u001e\u0005\u0007wF\u0002\r!!*\u0011\u000b\tkH+a*\u0011\u0007U\u000bI\u000b\u0002\u0004\u0002,F\u0012\r\u0001\u0017\u0002\u0002+\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u00022B)\u0011\u0011NAZ)&!\u0011QWA<\u0005!IE/\u001a:bi>\u0014\u0018a\u0002;p%&<\u0007\u000e^\u000b\u0005\u0003w\u000b)\r\u0006\u0003\u0002>\u0006%\u0007cBA5\u0003\u007f\u000b\u0019\rV\u0005\u0005\u0003\u0003\f9H\u0001\u0004FSRDWM\u001d\t\u0004+\u0006\u0015GABAdg\t\u0007\u0001LA\u0001Y\u0011!\tYm\rCA\u0002\u00055\u0017\u0001\u00027fMR\u0004RAQA\u001c\u0003\u0007\fa\u0001^8MK\u001a$X\u0003BAj\u00033$B!!6\u0002\\B9\u0011\u0011NA`)\u0006]\u0007cA+\u0002Z\u00121\u0011q\u0019\u001bC\u0002aC\u0001\"!85\t\u0003\u0007\u0011q\\\u0001\u0006e&<\u0007\u000e\u001e\t\u0006\u0005\u0006]\u0012q[\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011Q\u001d\t\u0004\u0005\u0006\u001d\u0018bAAu\u0007\n\u0019\u0011J\u001c;\u0002\r\u0015\fX/\u00197t)\r)\u0017q\u001e\u0005\t\u0003c4\u0014\u0011!a\u00019\u0006\u0019\u0001\u0010J\u0019\u0011\u0007U\u000b)\u0010B\u0003X\u0007\t\u0007\u0001\fC\u0004\u0002z\u000e\u0001\r!a=\u0002\u0003\u0005\fQ!Z7qif,B!a@\u0003\u0006U\u0011!\u0011\u0001\t\u0005}u\u0011\u0019\u0001E\u0002V\u0005\u000b!Qa\u0016\u0003C\u0002a\u000bq!\u001e8baBd\u00170\u0006\u0003\u0003\f\tEA\u0003\u0002B\u0007\u0005'\u0001BAP\u000f\u0003\u0010A\u0019QK!\u0005\u0005\u000b]+!\u0019\u0001-\t\u000f\tUQ\u00011\u0001\u0003\u000e\u0005\ta.A\u0003Fc>\u0003H/\u0006\u0003\u0003\u001c\tEB\u0003\u0002B\u000f\u0005g\u0001bAa\b\u0003*\t5RB\u0001B\u0011\u0015\u0011\u0011\u0019C!\n\u0002\r-,'O\\3m\u0015\t\u00119#\u0001\u0003dCR\u001c\u0018\u0002\u0002B\u0016\u0005C\u0011!!R9\u0011\tyj\"q\u0006\t\u0004+\nEB!B,\u0007\u0005\u0004A\u0006b\u0002B\u001b\r\u0001\u000f!qG\u0001\u0003KZ\u0004bAa\b\u0003*\t=\u0012aE5t\t\u00164\u0017N\\3eI\u0015DH/\u001a8tS>tW\u0003\u0002B\u001f\u0005\u000f\"2!\u001aB \u0011\u001d\u0011\te\u0002a\u0001\u0005\u0007\nQ\u0001\n;iSN\u0004BAP\u000f\u0003FA\u0019QKa\u0012\u0005\u000b];!\u0019\u0001-\u0002%9|g.R7qif$S\r\u001f;f]NLwN\\\u000b\u0005\u0005\u001b\u0012)\u0006F\u0002f\u0005\u001fBqA!\u0011\t\u0001\u0004\u0011\t\u0006\u0005\u0003?;\tM\u0003cA+\u0003V\u0011)q\u000b\u0003b\u00011\u0006\t\u0012n]#naRLH%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\tm#1\r\u000b\u0004K\nu\u0003b\u0002B!\u0013\u0001\u0007!q\f\t\u0005}u\u0011\t\u0007E\u0002V\u0005G\"QaV\u0005C\u0002a\u000bQbZ3uI\u0015DH/\u001a8tS>tW\u0003\u0002B5\u0005[\"BAa\u001b\u0003pA\u0019QK!\u001c\u0005\u000b]S!\u0019\u0001-\t\u000f\t\u0005#\u00021\u0001\u0003rA!a(\bB6\u0003I!xn\u0015;sS:<G%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\t]$q\u0010\u000b\u0004Y\ne\u0004b\u0002B!\u0017\u0001\u0007!1\u0010\t\u0005}u\u0011i\bE\u0002V\u0005\u007f\"QaV\u0006C\u0002a\u000b\u0001CZ5mi\u0016\u0014H%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\t\u0015%Q\u0012\u000b\u0005\u0005\u000f\u0013\u0019\n\u0006\u0003\u0003\n\n=\u0005\u0003\u0002 \u001e\u0005\u0017\u00032!\u0016BG\t\u00159FB1\u0001Y\u0011\u0019YH\u00021\u0001\u0003\u0012B)!) BFK\"9!\u0011\t\u0007A\u0002\t%\u0015!D7ba\u0012*\u0007\u0010^3og&|g.\u0006\u0004\u0003\u001a\n\u0005&\u0011\u0016\u000b\u0005\u00057\u0013Y\u000b\u0006\u0003\u0003\u001e\n\r\u0006\u0003\u0002 \u001e\u0005?\u00032!\u0016BQ\t\u0019\tY!\u0004b\u00011\"110\u0004a\u0001\u0005K\u0003bAQ?\u0003(\n}\u0005cA+\u0003*\u0012)q+\u0004b\u00011\"9!\u0011I\u0007A\u0002\t5\u0006\u0003\u0002 \u001e\u0005O\u000b\u0011C\u001a7bi6\u000b\u0007\u000fJ3yi\u0016t7/[8o+\u0019\u0011\u0019La/\u0003DR!!Q\u0017Bc)\u0011\u00119L!0\u0011\tyj\"\u0011\u0018\t\u0004+\nmFABA\u0006\u001d\t\u0007\u0001\f\u0003\u0004|\u001d\u0001\u0007!q\u0018\t\u0007\u0005v\u0014\tMa.\u0011\u0007U\u0013\u0019\rB\u0003X\u001d\t\u0007\u0001\fC\u0004\u0003B9\u0001\rAa2\u0011\tyj\"\u0011Y\u0001\u000fM>dG\rJ3yi\u0016t7/[8o+\u0019\u0011iM!6\u0003^R!!q\u001aBr)\u0011\u0011\tNa8\u0015\t\tM'q\u001b\t\u0004+\nUGABA\u0006\u001f\t\u0007\u0001\f\u0003\u0004|\u001f\u0001\u0007!\u0011\u001c\t\u0007\u0005v\u0014YNa5\u0011\u0007U\u0013i\u000eB\u0003X\u001f\t\u0007\u0001\f\u0003\u0005\u00024=!\t\u0019\u0001Bq!\u0015\u0011\u0015q\u0007Bj\u0011\u001d\u0011\te\u0004a\u0001\u0005K\u0004BAP\u000f\u0003\\\u0006\u0019r-\u001a;Pe\u0016c7/\u001a\u0013fqR,gn]5p]V1!1\u001eBy\u0005o$BA!<\u0003~R!!q\u001eB}!\r)&\u0011\u001f\u0003\b\u0003\u0017\u0001\"\u0019\u0001Bz#\r\u0011)\u0010\u0018\t\u0004+\n]H!B,\u0011\u0005\u0004A\u0006\u0002CA%!\u0011\u0005\rAa?\u0011\u000b\t\u000b9Da<\t\u000f\t\u0005\u0003\u00031\u0001\u0003\u0000B!a(\bB{\u0003]9W\r^(s\u000b2\u001cXMR1ti\u0012*\u0007\u0010^3og&|g.\u0006\u0004\u0004\u0006\r-1\u0011\u0003\u000b\u0005\u0007\u000f\u0019)\u0002\u0006\u0003\u0004\n\rM\u0001cA+\u0004\f\u00119\u00111B\tC\u0002\r5\u0011cAB\b9B\u0019Qk!\u0005\u0005\u000b]\u000b\"\u0019\u0001-\t\u000f\u0005%\u0013\u00031\u0001\u0004\n!9!\u0011I\tA\u0002\r]\u0001\u0003\u0002 \u001e\u0007\u001f\t!\u0003^8PaRLwN\u001c\u0013fqR,gn]5p]V!1QDB\u0012)\u0011\u0019yb!\n\u0011\u000b\t\u000byf!\t\u0011\u0007U\u001b\u0019\u0003B\u0003X%\t\u0007\u0001\fC\u0004\u0003BI\u0001\raa\n\u0011\tyj2\u0011E\u0001\u0011i>d\u0015n\u001d;%Kb$XM\\:j_:,Ba!\f\u00044Q!1qFB\u001b!\u0019\tI'a\u001d\u00042A\u0019Qka\r\u0005\u000b]\u001b\"\u0019\u0001-\t\u000f\t\u00053\u00031\u0001\u00048A!a(HB\u0019\u0003I\u0019wN\u001c;bS:\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0016\r\ru2QIB&)\u0011\u0019yd!\u0014\u0015\u0007\u0015\u001c\t\u0005C\u0004\u0002\u0002R\u0001\raa\u0011\u0011\u0007U\u001b)\u0005B\u0004\u0002\bR\u0011\raa\u0012\u0012\u0007\r%C\fE\u0002V\u0007\u0017\"Qa\u0016\u000bC\u0002aCqA!\u0011\u0015\u0001\u0004\u0019y\u0005\u0005\u0003?;\r%\u0013\u0001E3ySN$8\u000fJ3yi\u0016t7/[8o+\u0011\u0019)fa\u0018\u0015\t\r]3\u0011\r\u000b\u0004K\u000ee\u0003bBAH+\u0001\u000711\f\t\u0006\u0005v\u001ci&\u001a\t\u0004+\u000e}C!B,\u0016\u0005\u0004A\u0006b\u0002B!+\u0001\u000711\r\t\u0005}u\u0019i&\u0001\tg_J\fG\u000e\u001c\u0013fqR,gn]5p]V!1\u0011NB:)\u0011\u0019Yg!\u001e\u0015\u0007\u0015\u001ci\u0007C\u0004\u0002\u0010Z\u0001\raa\u001c\u0011\u000b\tk8\u0011O3\u0011\u0007U\u001b\u0019\bB\u0003X-\t\u0007\u0001\fC\u0004\u0003BY\u0001\raa\u001e\u0011\tyj2\u0011O\u0001\u0012M>\u0014X-Y2iI\u0015DH/\u001a8tS>tWCBB?\u0007\u0017\u001b9\t\u0006\u0003\u0004\u0000\r5E\u0003BAO\u0007\u0003Caa_\fA\u0002\r\r\u0005C\u0002\"~\u0007\u000b\u001bI\tE\u0002V\u0007\u000f#QaV\fC\u0002a\u00032!VBF\t\u0019\tYk\u0006b\u00011\"9!\u0011I\fA\u0002\r=\u0005\u0003\u0002 \u001e\u0007\u000b\u000b!#\u001b;fe\u0006$xN\u001d\u0013fqR,gn]5p]V!1QSBN)\u0011\u00199j!(\u0011\r\u0005%\u00141WBM!\r)61\u0014\u0003\u0006/b\u0011\r\u0001\u0017\u0005\b\u0005\u0003B\u0002\u0019ABP!\u0011qTd!'\u0002#Q|'+[4ii\u0012*\u0007\u0010^3og&|g.\u0006\u0004\u0004&\u000e56\u0011\u0017\u000b\u0005\u0007O\u001b9\f\u0006\u0003\u0004*\u000eM\u0006\u0003CA5\u0003\u007f\u001bYka,\u0011\u0007U\u001bi\u000b\u0002\u0004\u0002Hf\u0011\r\u0001\u0017\t\u0004+\u000eEF!B,\u001a\u0005\u0004A\u0006\u0002CAf3\u0011\u0005\ra!.\u0011\u000b\t\u000b9da+\t\u000f\t\u0005\u0013\u00041\u0001\u0004:B!a(HBX\u0003A!x\u000eT3gi\u0012*\u0007\u0010^3og&|g.\u0006\u0004\u0004@\u000e-7q\u0019\u000b\u0005\u0007\u0003\u001c\t\u000e\u0006\u0003\u0004D\u000e5\u0007\u0003CA5\u0003\u007f\u001b)m!3\u0011\u0007U\u001b9\rB\u0003X5\t\u0007\u0001\fE\u0002V\u0007\u0017$a!a2\u001b\u0005\u0004A\u0006\u0002CAo5\u0011\u0005\raa4\u0011\u000b\t\u000b9d!3\t\u000f\t\u0005#\u00041\u0001\u0004TB!a(HBc\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\re7\u0011\u001d\u000b\u0005\u0003G\u001cY\u000eC\u0004\u0003Bm\u0001\ra!8\u0011\tyj2q\u001c\t\u0004+\u000e\u0005H!B,\u001c\u0005\u0004A\u0016\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o+\u0011\u00199oa=\u0015\t\r%8Q\u001e\u000b\u0004K\u000e-\b\u0002CAy9\u0005\u0005\t\u0019\u0001/\t\u000f\t\u0005C\u00041\u0001\u0004pB!a(HBy!\r)61\u001f\u0003\u0006/r\u0011\r\u0001\u0017"
)
public final class Opt {
   private final Object ref;

   public static boolean equals$extension(final Object $this, final Object x$1) {
      return Opt$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final Object $this) {
      return Opt$.MODULE$.hashCode$extension($this);
   }

   public static Either toLeft$extension(final Object $this, final Function0 right) {
      return Opt$.MODULE$.toLeft$extension($this, right);
   }

   public static Either toRight$extension(final Object $this, final Function0 left) {
      return Opt$.MODULE$.toRight$extension($this, left);
   }

   public static Iterator iterator$extension(final Object $this) {
      return Opt$.MODULE$.iterator$extension($this);
   }

   public static void foreach$extension(final Object $this, final Function1 f) {
      Opt$.MODULE$.foreach$extension($this, f);
   }

   public static boolean forall$extension(final Object $this, final Function1 p) {
      return Opt$.MODULE$.forall$extension($this, p);
   }

   public static boolean exists$extension(final Object $this, final Function1 p) {
      return Opt$.MODULE$.exists$extension($this, p);
   }

   public static boolean contains$extension(final Object $this, final Object elem) {
      return Opt$.MODULE$.contains$extension($this, elem);
   }

   public static List toList$extension(final Object $this) {
      return Opt$.MODULE$.toList$extension($this);
   }

   public static Option toOption$extension(final Object $this) {
      return Opt$.MODULE$.toOption$extension($this);
   }

   public static Object getOrElseFast$extension(final Object $this, final Object default) {
      return Opt$.MODULE$.getOrElseFast$extension($this, default);
   }

   public static Object getOrElse$extension(final Object $this, final Function0 default) {
      return Opt$.MODULE$.getOrElse$extension($this, default);
   }

   public static Object fold$extension(final Object $this, final Function0 b, final Function1 f) {
      return Opt$.MODULE$.fold$extension($this, b, f);
   }

   public static Object flatMap$extension(final Object $this, final Function1 f) {
      return Opt$.MODULE$.flatMap$extension($this, f);
   }

   public static Object map$extension(final Object $this, final Function1 f) {
      return Opt$.MODULE$.map$extension($this, f);
   }

   public static Object filter$extension(final Object $this, final Function1 f) {
      return Opt$.MODULE$.filter$extension($this, f);
   }

   public static String toString$extension(final Object $this) {
      return Opt$.MODULE$.toString$extension($this);
   }

   public static Object get$extension(final Object $this) {
      return Opt$.MODULE$.get$extension($this);
   }

   public static boolean isEmpty$extension(final Object $this) {
      return Opt$.MODULE$.isEmpty$extension($this);
   }

   public static boolean nonEmpty$extension(final Object $this) {
      return Opt$.MODULE$.nonEmpty$extension($this);
   }

   public static boolean isDefined$extension(final Object $this) {
      return Opt$.MODULE$.isDefined$extension($this);
   }

   public static Eq EqOpt(final Eq ev) {
      return Opt$.MODULE$.EqOpt(ev);
   }

   public static Object unapply(final Object n) {
      return Opt$.MODULE$.unapply(n);
   }

   public static Object empty() {
      return Opt$.MODULE$.empty();
   }

   public static Object apply(final Object a) {
      return Opt$.MODULE$.apply(a);
   }

   public Object ref() {
      return this.ref;
   }

   public boolean isDefined() {
      return Opt$.MODULE$.isDefined$extension(this.ref());
   }

   public boolean nonEmpty() {
      return Opt$.MODULE$.nonEmpty$extension(this.ref());
   }

   public boolean isEmpty() {
      return Opt$.MODULE$.isEmpty$extension(this.ref());
   }

   public Object get() {
      return Opt$.MODULE$.get$extension(this.ref());
   }

   public String toString() {
      return Opt$.MODULE$.toString$extension(this.ref());
   }

   public Object filter(final Function1 f) {
      return Opt$.MODULE$.filter$extension(this.ref(), f);
   }

   public Object map(final Function1 f) {
      return Opt$.MODULE$.map$extension(this.ref(), f);
   }

   public Object flatMap(final Function1 f) {
      return Opt$.MODULE$.flatMap$extension(this.ref(), f);
   }

   public Object fold(final Function0 b, final Function1 f) {
      return Opt$.MODULE$.fold$extension(this.ref(), b, f);
   }

   public Object getOrElse(final Function0 default) {
      return Opt$.MODULE$.getOrElse$extension(this.ref(), default);
   }

   public Object getOrElseFast(final Object default) {
      return Opt$.MODULE$.getOrElseFast$extension(this.ref(), default);
   }

   public Option toOption() {
      return Opt$.MODULE$.toOption$extension(this.ref());
   }

   public List toList() {
      return Opt$.MODULE$.toList$extension(this.ref());
   }

   public boolean contains(final Object elem) {
      return Opt$.MODULE$.contains$extension(this.ref(), elem);
   }

   public boolean exists(final Function1 p) {
      return Opt$.MODULE$.exists$extension(this.ref(), p);
   }

   public boolean forall(final Function1 p) {
      return Opt$.MODULE$.forall$extension(this.ref(), p);
   }

   public void foreach(final Function1 f) {
      Opt$.MODULE$.foreach$extension(this.ref(), f);
   }

   public Iterator iterator() {
      return Opt$.MODULE$.iterator$extension(this.ref());
   }

   public Either toRight(final Function0 left) {
      return Opt$.MODULE$.toRight$extension(this.ref(), left);
   }

   public Either toLeft(final Function0 right) {
      return Opt$.MODULE$.toLeft$extension(this.ref(), right);
   }

   public int hashCode() {
      return Opt$.MODULE$.hashCode$extension(this.ref());
   }

   public boolean equals(final Object x$1) {
      return Opt$.MODULE$.equals$extension(this.ref(), x$1);
   }

   public Opt(final Object ref) {
      this.ref = ref;
   }
}
