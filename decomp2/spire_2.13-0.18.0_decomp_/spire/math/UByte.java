package spire.math;

import algebra.ring.CommutativeRig;
import scala.math.BigInt;
import scala.math.ScalaNumericAnyConversions;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r-w!B4i\u0011\u0003ig!B8i\u0011\u0003\u0001\b\"\u0002>\u0002\t\u0003Y\b\"\u0002?\u0002\t\u000bi\bB\u0002?\u0002\t\u000b\u0011\u0019\u0006C\u0004\u0003Z\u0005!)!!<\t\u000f\tu\u0013\u0001\"\u0002\u0002n\"9!\u0011M\u0001\u0005\u0006\t\r\u0004b\u0002B5\u0003\u0011\u0015!1\u000e\u0005\b\u0005_\nAQ\u0001B9\u0011\u001d\u0011)(\u0001C\u0003\u0005oBqAa\u001f\u0002\t\u000b\u0011i\bC\u0004\u0003\u0002\u0006!)Aa!\t\u000f\t\u001d\u0015\u0001\"\u0002\u0003\n\"9!QR\u0001\u0005\u0006\t=\u0005b\u0002BJ\u0003\u0011\u0015!Q\u0013\u0005\b\u00053\u000bAQ\u0001BN\u0011\u001d\u0011y*\u0001C\u0003\u0005CCqA!*\u0002\t\u000b\u00119\u000bC\u0004\u0003,\u0006!)A!,\t\u000f\tE\u0016\u0001\"\u0002\u00034\"9!qW\u0001\u0005\u0006\te\u0006b\u0002B_\u0003\u0011\u0015!q\u0018\u0005\b\u0005\u0007\fAQ\u0001Bc\u0011\u001d\u0011I-\u0001C\u0003\u0005\u0017DqAa4\u0002\t\u000b\u0011\t\u000eC\u0004\u0003V\u0006!)Aa6\t\u000f\tm\u0017\u0001\"\u0002\u0003^\"9!\u0011]\u0001\u0005\u0006\t\r\bb\u0002Bt\u0003\u0011\u0015!\u0011\u001e\u0005\b\u0005c\fAQ\u0001Bz\u0011\u001d\u0011Y0\u0001C\u0003\u0005{Dqa!\u0002\u0002\t\u000b\u00199\u0001C\u0004\u0004\u0010\u0005!)a!\u0005\t\u000f\re\u0011\u0001\"\u0002\u0004\u001c!911E\u0001\u0005\u0006\r\u0015\u0002bBB\u0017\u0003\u0011\u00151q\u0006\u0005\b\u0007o\tAQAB\u001d\u0011\u001d\u0019i$\u0001C\u0003\u0007\u007fAqaa\u0012\u0002\t\u000b\u0019I\u0005C\u0004\u0004R\u0005!)aa\u0015\t\u000f\rm\u0013\u0001\"\u0002\u0004^!91QM\u0001\u0005\u0006\r\u001d\u0004bBB8\u0003\u0011\u00151\u0011\u000f\u0005\b\u0007k\nAQAB<\u0011\u001d\u0019y(\u0001C\u0003\u0007\u0003Cqa!#\u0002\t\u000b\u0019Y\tC\u0004\u0004\u0014\u0006!)a!&\t\u000f\ru\u0015\u0001\"\u0002\u0004 \"91qU\u0001\u0005\u0006\r%\u0006bBBY\u0003\u0011\u001511\u0017\u0005\n\u0007w\u000b\u0011\u0011!C\u0003\u0007{C\u0011b!1\u0002\u0003\u0003%)aa1\u0007\t=D'a \u0005\u000b\u0003#)$Q1A\u0005\u0002\u0005M\u0001BCA\u000ek\t\u0005\t\u0015!\u0003\u0002\u0016!1!0\u000eC\u0001\u0003;Aq!!\t6\t\u0003\n\u0019\u0002C\u0004\u0002$U\"\t%!\n\t\u000f\u00055R\u0007\"\u0011\u00020!9\u0011qG\u001b\u0005B\u0005e\u0002bBA!k\u0011\u0005\u00131\t\u0005\b\u0003\u0017*D\u0011IA'\u0011\u001d\t)&\u000eC!\u0003/Bq!a\u00186\t\u0003\t\t\u0007C\u0004\u0002|U\"\t%a\u0005\t\u000f\u0005uT\u0007\"\u0011\u00020!9\u0011qP\u001b\u0005B\u0005e\u0002bBAAk\u0011\u0005\u00131\t\u0005\b\u0003\u0007+D\u0011IA'\u0011\u001d\t))\u000eC!\u0003/Bq!a\"6\t\u0003\nI\tC\u0004\u0002\u0012V\"\t!a%\t\u000f\u0005mU\u0007\"\u0011\u0002\n\"9\u0011QT\u001b\u0005B\u0005%\u0005bBAPk\u0011\u0005\u0013\u0011\u0012\u0005\b\u0003C+D\u0011IAE\u0011\u001d\t\u0019+\u000eC\u0001\u0003\u0013Cq!!*6\t\u0003\n9\u000bC\u0004\u0002:V\"\t!a/\t\u000f\u0005\u0005W\u0007\"\u0001\u0002D\"9\u0011qY\u001b\u0005\u0002\u0005%\u0007bBAgk\u0011\u0005\u0011q\u001a\u0005\b\u0003',D\u0011AAk\u0011\u001d\tI.\u000eC\u0001\u00037Dq!a86\t\u0003\t\t\u000fC\u0004\u0002fV\"\t!a:\t\u000f\u0005-X\u0007\"\u0001\u0002n\"9\u0011q^\u001b\u0005\u0002\u0005E\bbBA{k\u0011\u0005\u0011q\u001f\u0005\b\u0003w,D\u0011AA\u007f\u0011\u001d\u0011\t!\u000eC\u0001\u0005\u0007AqAa\u00026\t\u0003\u0011I\u0001C\u0004\u0003\u000eU\"\t!!<\t\u000f\t=Q\u0007\"\u0001\u0003\u0012!9!qC\u001b\u0005\u0002\te\u0001b\u0002B\u000fk\u0011\u0005!q\u0004\u0005\b\u0005G)D\u0011\u0001B\u0013\u0011\u001d\u0011I#\u000eC\u0001\u0005WAqAa\f6\t\u0003\u0011\t\u0004C\u0004\u00036U\"\tAa\u000e\t\u0013\tmR'!A\u0005B\tu\u0002\"\u0003B k\u0005\u0005I\u0011\tB!\u0003\u0015)&)\u001f;f\u0015\tI'.\u0001\u0003nCRD'\"A6\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011a.A\u0007\u0002Q\n)QKQ=uKN\u0019\u0011!]<\u0011\u0005I,X\"A:\u000b\u0003Q\fQa]2bY\u0006L!A^:\u0003\r\u0005s\u0017PU3g!\tq\u00070\u0003\u0002zQ\nqQKQ=uK&s7\u000f^1oG\u0016\u001c\u0018A\u0002\u001fj]&$h\bF\u0001n\u0003\u0015\t\u0007\u000f\u001d7z)\rq(q\t\t\u0003]V\u001aR!NA\u0001\u0003\u000f\u00012A]A\u0002\u0013\r\t)a\u001d\u0002\u0007\u0003:Lh+\u00197\u0011\t\u0005%\u0011QB\u0007\u0003\u0003\u0017Q!![:\n\t\u0005=\u00111\u0002\u0002\u001b'\u000e\fG.\u0019(v[\u0016\u0014\u0018nY!os\u000e{gN^3sg&|gn]\u0001\u0007g&<g.\u001a3\u0016\u0005\u0005U\u0001c\u0001:\u0002\u0018%\u0019\u0011\u0011D:\u0003\t\tKH/Z\u0001\bg&<g.\u001a3!)\rq\u0018q\u0004\u0005\b\u0003#A\u0004\u0019AA\u000b\u0003\u0019!xNQ=uK\u00061Ao\\\"iCJ,\"!a\n\u0011\u0007I\fI#C\u0002\u0002,M\u0014Aa\u00115be\u00069Ao\\*i_J$XCAA\u0019!\r\u0011\u00181G\u0005\u0004\u0003k\u0019(!B*i_J$\u0018!\u0002;p\u0013:$XCAA\u001e!\r\u0011\u0018QH\u0005\u0004\u0003\u007f\u0019(aA%oi\u00061Ao\u001c'p]\u001e,\"!!\u0012\u0011\u0007I\f9%C\u0002\u0002JM\u0014A\u0001T8oO\u00069Ao\u001c$m_\u0006$XCAA(!\r\u0011\u0018\u0011K\u0005\u0004\u0003'\u001a(!\u0002$m_\u0006$\u0018\u0001\u0003;p\t>,(\r\\3\u0016\u0005\u0005e\u0003c\u0001:\u0002\\%\u0019\u0011QL:\u0003\r\u0011{WO\u00197f\u0003!!xNQ5h\u0013:$XCAA2!\u0011\t)'!\u001e\u000f\t\u0005\u001d\u0014\u0011\u000f\b\u0005\u0003S\ny'\u0004\u0002\u0002l)\u0019\u0011Q\u000e7\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0018bAA:g\u00069\u0001/Y2lC\u001e,\u0017\u0002BA<\u0003s\u0012aAQ5h\u0013:$(bAA:g\u0006I!-\u001f;f-\u0006dW/Z\u0001\u000bg\"|'\u000f\u001e,bYV,\u0017\u0001C5oiZ\u000bG.^3\u0002\u00131|gn\u001a,bYV,\u0017A\u00034m_\u0006$h+\u00197vK\u0006YAm\\;cY\u00164\u0016\r\\;f\u0003\u001dI7o\u00165pY\u0016,\"!a#\u0011\u0007I\fi)C\u0002\u0002\u0010N\u0014qAQ8pY\u0016\fg.\u0001\u0006v]\u0012,'\u000f\\=j]\u001e,\"!!&\u0011\u0007I\f9*C\u0002\u0002\u001aN\u00141!\u00118z\u0003-I7OV1mS\u0012\u0014\u0015\u0010^3\u0002\u0019%\u001ch+\u00197jINCwN\u001d;\u0002\u0017%\u001ch+\u00197jI\u000eC\u0017M]\u0001\u000bSN4\u0016\r\\5e\u0013:$\u0018aC5t-\u0006d\u0017\u000e\u001a'p]\u001e\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003S\u0003B!a+\u00024:!\u0011QVAX!\r\tIg]\u0005\u0004\u0003c\u001b\u0018A\u0002)sK\u0012,g-\u0003\u0003\u00026\u0006]&AB*ue&twMC\u0002\u00022N\fa\u0001J3rI\u0015\fH\u0003BAF\u0003{Ca!a0P\u0001\u0004q\u0018\u0001\u0002;iCR\f\u0001\u0002\n2b]\u001e$S-\u001d\u000b\u0005\u0003\u0017\u000b)\r\u0003\u0004\u0002@B\u0003\rA`\u0001\nI\u0015\fH%Z9%KF$B!a#\u0002L\"1\u0011qX)A\u0002y\f1\u0002J3rI\t\fgn\u001a\u0013fcR!\u00111RAi\u0011\u0019\tyL\u0015a\u0001}\u0006AA\u0005\\3tg\u0012*\u0017\u000f\u0006\u0003\u0002\f\u0006]\u0007BBA`'\u0002\u0007a0A\u0003%Y\u0016\u001c8\u000f\u0006\u0003\u0002\f\u0006u\u0007BBA`)\u0002\u0007a0A\u0006%OJ,\u0017\r^3sI\u0015\fH\u0003BAF\u0003GDa!a0V\u0001\u0004q\u0018\u0001\u0003\u0013he\u0016\fG/\u001a:\u0015\t\u0005-\u0015\u0011\u001e\u0005\u0007\u0003\u007f3\u0006\u0019\u0001@\u0002\u0019Ut\u0017M]=`I5Lg.^:\u0016\u0003y\fQ\u0001\n9mkN$2A`Az\u0011\u0019\ty\f\u0017a\u0001}\u00061A%\\5okN$2A`A}\u0011\u0019\ty,\u0017a\u0001}\u00061A\u0005^5nKN$2A`A\u0000\u0011\u0019\tyL\u0017a\u0001}\u0006!A\u0005Z5w)\rq(Q\u0001\u0005\u0007\u0003\u007f[\u0006\u0019\u0001@\u0002\u0011\u0011\u0002XM]2f]R$2A B\u0006\u0011\u0019\ty\f\u0018a\u0001}\u0006aQO\\1ss~#C/\u001b7eK\u0006QA\u0005\\3tg\u0012bWm]:\u0015\u0007y\u0014\u0019\u0002C\u0004\u0003\u0016y\u0003\r!a\u000f\u0002\u000bMD\u0017N\u001a;\u0002!\u0011:'/Z1uKJ$sM]3bi\u0016\u0014Hc\u0001@\u0003\u001c!9!QC0A\u0002\u0005m\u0012\u0001\u0007\u0013he\u0016\fG/\u001a:%OJ,\u0017\r^3sI\u001d\u0014X-\u0019;feR\u0019aP!\t\t\u000f\tU\u0001\r1\u0001\u0002<\u0005!A%Y7q)\rq(q\u0005\u0005\u0007\u0003\u007f\u000b\u0007\u0019\u0001@\u0002\t\u0011\u0012\u0017M\u001d\u000b\u0004}\n5\u0002BBA`E\u0002\u0007a0A\u0002%kB$2A B\u001a\u0011\u0019\tyl\u0019a\u0001}\u0006aA\u0005^5nKN$C/[7fgR\u0019aP!\u000f\t\r\u0005}F\r1\u0001\u007f\u0003!A\u0017m\u001d5D_\u0012,GCAA\u001e\u0003\u0019)\u0017/^1mgR!\u00111\u0012B\"\u0011%\u0011)EZA\u0001\u0002\u0004\t)*A\u0002yIEBqA!\u0013\u0004\u0001\u0004\t)\"A\u0001oQ\r\u0019!Q\n\t\u0004e\n=\u0013b\u0001B)g\n1\u0011N\u001c7j]\u0016$2A B+\u0011\u001d\u0011I\u0005\u0002a\u0001\u0003wA3\u0001\u0002B'\u0003!i\u0015N\u001c,bYV,\u0007fA\u0003\u0003N\u0005AQ*\u0019=WC2,X\rK\u0002\u0007\u0005\u001b\n\u0001\u0003^8CsR,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005U!Q\r\u0005\u0007\u0005O:\u0001\u0019\u0001@\u0002\u000b\u0011\"\b.[:\u0002!Q|7\t[1sI\u0015DH/\u001a8tS>tG\u0003BA\u0014\u0005[BaAa\u001a\t\u0001\u0004q\u0018!\u0005;p'\"|'\u000f\u001e\u0013fqR,gn]5p]R!\u0011\u0011\u0007B:\u0011\u0019\u00119'\u0003a\u0001}\u0006yAo\\%oi\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002<\te\u0004B\u0002B4\u0015\u0001\u0007a0\u0001\tu_2{gn\u001a\u0013fqR,gn]5p]R!\u0011Q\tB@\u0011\u0019\u00119g\u0003a\u0001}\u0006\tBo\u001c$m_\u0006$H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005=#Q\u0011\u0005\u0007\u0005Ob\u0001\u0019\u0001@\u0002%Q|Gi\\;cY\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u00033\u0012Y\t\u0003\u0004\u0003h5\u0001\rA`\u0001\u0013i>\u0014\u0015nZ%oi\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002d\tE\u0005B\u0002B4\u001d\u0001\u0007a0A\ncsR,g+\u00197vK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002\u0016\t]\u0005B\u0002B4\u001f\u0001\u0007a0\u0001\u000btQ>\u0014HOV1mk\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003c\u0011i\n\u0003\u0004\u0003hA\u0001\rA`\u0001\u0013S:$h+\u00197vK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002<\t\r\u0006B\u0002B4#\u0001\u0007a0A\nm_:<g+\u00197vK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002F\t%\u0006B\u0002B4%\u0001\u0007a0\u0001\u000bgY>\fGOV1mk\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003\u001f\u0012y\u000b\u0003\u0004\u0003hM\u0001\rA`\u0001\u0016I>,(\r\\3WC2,X\rJ3yi\u0016t7/[8o)\u0011\tIF!.\t\r\t\u001dD\u00031\u0001\u007f\u0003EI7o\u00165pY\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003\u0017\u0013Y\f\u0003\u0004\u0003hU\u0001\rA`\u0001\u0015k:$WM\u001d7zS:<G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005U%\u0011\u0019\u0005\u0007\u0005O2\u0002\u0019\u0001@\u0002+%\u001ch+\u00197jI\nKH/\u001a\u0013fqR,gn]5p]R!\u00111\u0012Bd\u0011\u0019\u00119g\u0006a\u0001}\u00061\u0012n\u001d,bY&$7\u000b[8si\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002\f\n5\u0007B\u0002B41\u0001\u0007a0A\u000bjgZ\u000bG.\u001b3DQ\u0006\u0014H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005-%1\u001b\u0005\u0007\u0005OJ\u0002\u0019\u0001@\u0002)%\u001ch+\u00197jI&sG\u000fJ3yi\u0016t7/[8o)\u0011\tYI!7\t\r\t\u001d$\u00041\u0001\u007f\u0003UI7OV1mS\u0012duN\\4%Kb$XM\\:j_:$B!a#\u0003`\"1!qM\u000eA\u0002y\f!\u0003^8TiJLgn\u001a\u0013fqR,gn]5p]R!\u0011q\u0015Bs\u0011\u0019\u00119\u0007\ba\u0001}\u0006\u0001B%Z9%KF$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005W\u0014y\u000f\u0006\u0003\u0002\f\n5\bBBA`;\u0001\u0007a\u0010\u0003\u0004\u0003hu\u0001\rA`\u0001\u0013I\t\fgn\u001a\u0013fc\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003v\neH\u0003BAF\u0005oDa!a0\u001f\u0001\u0004q\bB\u0002B4=\u0001\u0007a0A\n%KF$S-\u001d\u0013fc\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003\u0000\u000e\rA\u0003BAF\u0007\u0003Aa!a0 \u0001\u0004q\bB\u0002B4?\u0001\u0007a0A\u000b%KF$#-\u00198hI\u0015\fH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\r%1Q\u0002\u000b\u0005\u0003\u0017\u001bY\u0001\u0003\u0004\u0002@\u0002\u0002\rA \u0005\u0007\u0005O\u0002\u0003\u0019\u0001@\u0002%\u0011bWm]:%KF$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007'\u00199\u0002\u0006\u0003\u0002\f\u000eU\u0001BBA`C\u0001\u0007a\u0010\u0003\u0004\u0003h\u0005\u0002\rA`\u0001\u0010I1,7o\u001d\u0013fqR,gn]5p]R!1QDB\u0011)\u0011\tYia\b\t\r\u0005}&\u00051\u0001\u007f\u0011\u0019\u00119G\ta\u0001}\u0006)Be\u001a:fCR,'\u000fJ3rI\u0015DH/\u001a8tS>tG\u0003BB\u0014\u0007W!B!a#\u0004*!1\u0011qX\u0012A\u0002yDaAa\u001a$\u0001\u0004q\u0018A\u0005\u0013he\u0016\fG/\u001a:%Kb$XM\\:j_:$Ba!\r\u00046Q!\u00111RB\u001a\u0011\u0019\ty\f\na\u0001}\"1!q\r\u0013A\u0002y\fa#\u001e8bef|F%\\5okN$S\r\u001f;f]NLwN\u001c\u000b\u0004}\u000em\u0002B\u0002B4K\u0001\u0007a0A\b%a2,8\u000fJ3yi\u0016t7/[8o)\u0011\u0019\te!\u0012\u0015\u0007y\u001c\u0019\u0005\u0003\u0004\u0002@\u001a\u0002\rA \u0005\u0007\u0005O2\u0003\u0019\u0001@\u0002!\u0011j\u0017N\\;tI\u0015DH/\u001a8tS>tG\u0003BB&\u0007\u001f\"2A`B'\u0011\u0019\tyl\na\u0001}\"1!qM\u0014A\u0002y\f\u0001\u0003\n;j[\u0016\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\rU3\u0011\f\u000b\u0004}\u000e]\u0003BBA`Q\u0001\u0007a\u0010\u0003\u0004\u0003h!\u0002\rA`\u0001\u000fI\u0011Lg\u000fJ3yi\u0016t7/[8o)\u0011\u0019yfa\u0019\u0015\u0007y\u001c\t\u0007\u0003\u0004\u0002@&\u0002\rA \u0005\u0007\u0005OJ\u0003\u0019\u0001@\u0002%\u0011\u0002XM]2f]R$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007S\u001ai\u0007F\u0002\u007f\u0007WBa!a0+\u0001\u0004q\bB\u0002B4U\u0001\u0007a0\u0001\fv]\u0006\u0014\u0018p\u0018\u0013uS2$W\rJ3yi\u0016t7/[8o)\rq81\u000f\u0005\u0007\u0005OZ\u0003\u0019\u0001@\u0002)\u0011bWm]:%Y\u0016\u001c8\u000fJ3yi\u0016t7/[8o)\u0011\u0019Ih! \u0015\u0007y\u001cY\bC\u0004\u0003\u00161\u0002\r!a\u000f\t\r\t\u001dD\u00061\u0001\u007f\u0003i!sM]3bi\u0016\u0014He\u001a:fCR,'\u000fJ3yi\u0016t7/[8o)\u0011\u0019\u0019ia\"\u0015\u0007y\u001c)\tC\u0004\u0003\u00165\u0002\r!a\u000f\t\r\t\u001dT\u00061\u0001\u007f\u0003\t\"sM]3bi\u0016\u0014He\u001a:fCR,'\u000fJ4sK\u0006$XM\u001d\u0013fqR,gn]5p]R!1QRBI)\rq8q\u0012\u0005\b\u0005+q\u0003\u0019AA\u001e\u0011\u0019\u00119G\fa\u0001}\u0006qA%Y7qI\u0015DH/\u001a8tS>tG\u0003BBL\u00077#2A`BM\u0011\u0019\tyl\fa\u0001}\"1!qM\u0018A\u0002y\fa\u0002\n2be\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004\"\u000e\u0015Fc\u0001@\u0004$\"1\u0011q\u0018\u0019A\u0002yDaAa\u001a1\u0001\u0004q\u0018!\u0004\u0013va\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004,\u000e=Fc\u0001@\u0004.\"1\u0011qX\u0019A\u0002yDaAa\u001a2\u0001\u0004q\u0018A\u0006\u0013uS6,7\u000f\n;j[\u0016\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\rU6\u0011\u0018\u000b\u0004}\u000e]\u0006BBA`e\u0001\u0007a\u0010\u0003\u0004\u0003hI\u0002\rA`\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003>\r}\u0006B\u0002B4g\u0001\u0007a0\u0001\tfcV\fGn\u001d\u0013fqR,gn]5p]R!1QYBe)\u0011\tYia2\t\u0013\t\u0015C'!AA\u0002\u0005U\u0005B\u0002B4i\u0001\u0007a\u0010"
)
public final class UByte implements ScalaNumericAnyConversions {
   private final byte signed;

   public static boolean equals$extension(final byte $this, final Object x$1) {
      return UByte$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final byte $this) {
      return UByte$.MODULE$.hashCode$extension($this);
   }

   public static byte $times$times$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$times$times$extension($this, that);
   }

   public static byte $up$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$up$extension($this, that);
   }

   public static byte $bar$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$bar$extension($this, that);
   }

   public static byte $amp$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$amp$extension($this, that);
   }

   public static byte $greater$greater$greater$extension(final byte $this, final int shift) {
      return UByte$.MODULE$.$greater$greater$greater$extension($this, shift);
   }

   public static byte $greater$greater$extension(final byte $this, final int shift) {
      return UByte$.MODULE$.$greater$greater$extension($this, shift);
   }

   public static byte $less$less$extension(final byte $this, final int shift) {
      return UByte$.MODULE$.$less$less$extension($this, shift);
   }

   public static byte unary_$tilde$extension(final byte $this) {
      return UByte$.MODULE$.unary_$tilde$extension($this);
   }

   public static byte $percent$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$percent$extension($this, that);
   }

   public static byte $div$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$div$extension($this, that);
   }

   public static byte $times$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$times$extension($this, that);
   }

   public static byte $minus$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$minus$extension($this, that);
   }

   public static byte $plus$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$plus$extension($this, that);
   }

   public static byte unary_$minus$extension(final byte $this) {
      return UByte$.MODULE$.unary_$minus$extension($this);
   }

   public static boolean $greater$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$greater$extension($this, that);
   }

   public static boolean $greater$eq$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$greater$eq$extension($this, that);
   }

   public static boolean $less$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$less$extension($this, that);
   }

   public static boolean $less$eq$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$less$eq$extension($this, that);
   }

   public static boolean $eq$bang$eq$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$eq$bang$eq$extension($this, that);
   }

   public static boolean $eq$eq$eq$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$eq$eq$eq$extension($this, that);
   }

   public static boolean $bang$eq$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$bang$eq$extension($this, that);
   }

   public static boolean $eq$eq$extension(final byte $this, final byte that) {
      return UByte$.MODULE$.$eq$eq$extension($this, that);
   }

   public static String toString$extension(final byte $this) {
      return UByte$.MODULE$.toString$extension($this);
   }

   public static boolean isValidLong$extension(final byte $this) {
      return UByte$.MODULE$.isValidLong$extension($this);
   }

   public static boolean isValidInt$extension(final byte $this) {
      return UByte$.MODULE$.isValidInt$extension($this);
   }

   public static boolean isValidChar$extension(final byte $this) {
      return UByte$.MODULE$.isValidChar$extension($this);
   }

   public static boolean isValidShort$extension(final byte $this) {
      return UByte$.MODULE$.isValidShort$extension($this);
   }

   public static boolean isValidByte$extension(final byte $this) {
      return UByte$.MODULE$.isValidByte$extension($this);
   }

   public static Object underlying$extension(final byte $this) {
      return UByte$.MODULE$.underlying$extension($this);
   }

   public static boolean isWhole$extension(final byte $this) {
      return UByte$.MODULE$.isWhole$extension($this);
   }

   public static double doubleValue$extension(final byte $this) {
      return UByte$.MODULE$.doubleValue$extension($this);
   }

   public static float floatValue$extension(final byte $this) {
      return UByte$.MODULE$.floatValue$extension($this);
   }

   public static long longValue$extension(final byte $this) {
      return UByte$.MODULE$.longValue$extension($this);
   }

   public static int intValue$extension(final byte $this) {
      return UByte$.MODULE$.intValue$extension($this);
   }

   public static short shortValue$extension(final byte $this) {
      return UByte$.MODULE$.shortValue$extension($this);
   }

   public static byte byteValue$extension(final byte $this) {
      return UByte$.MODULE$.byteValue$extension($this);
   }

   public static BigInt toBigInt$extension(final byte $this) {
      return UByte$.MODULE$.toBigInt$extension($this);
   }

   public static double toDouble$extension(final byte $this) {
      return UByte$.MODULE$.toDouble$extension($this);
   }

   public static float toFloat$extension(final byte $this) {
      return UByte$.MODULE$.toFloat$extension($this);
   }

   public static long toLong$extension(final byte $this) {
      return UByte$.MODULE$.toLong$extension($this);
   }

   public static int toInt$extension(final byte $this) {
      return UByte$.MODULE$.toInt$extension($this);
   }

   public static short toShort$extension(final byte $this) {
      return UByte$.MODULE$.toShort$extension($this);
   }

   public static char toChar$extension(final byte $this) {
      return UByte$.MODULE$.toChar$extension($this);
   }

   public static byte toByte$extension(final byte $this) {
      return UByte$.MODULE$.toByte$extension($this);
   }

   public static byte MaxValue() {
      return UByte$.MODULE$.MaxValue();
   }

   public static byte MinValue() {
      return UByte$.MODULE$.MinValue();
   }

   public static byte apply(final int n) {
      return UByte$.MODULE$.apply(n);
   }

   public static byte apply(final byte n) {
      return UByte$.MODULE$.apply(n);
   }

   public static NumberTag UByteTag() {
      return UByte$.MODULE$.UByteTag();
   }

   public static BitString UByteBitString() {
      return UByte$.MODULE$.UByteBitString();
   }

   public static CommutativeRig UByteAlgebra() {
      return UByte$.MODULE$.UByteAlgebra();
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public byte signed() {
      return this.signed;
   }

   public byte toByte() {
      return UByte$.MODULE$.toByte$extension(this.signed());
   }

   public char toChar() {
      return UByte$.MODULE$.toChar$extension(this.signed());
   }

   public short toShort() {
      return UByte$.MODULE$.toShort$extension(this.signed());
   }

   public int toInt() {
      return UByte$.MODULE$.toInt$extension(this.signed());
   }

   public long toLong() {
      return UByte$.MODULE$.toLong$extension(this.signed());
   }

   public float toFloat() {
      return UByte$.MODULE$.toFloat$extension(this.signed());
   }

   public double toDouble() {
      return UByte$.MODULE$.toDouble$extension(this.signed());
   }

   public BigInt toBigInt() {
      return UByte$.MODULE$.toBigInt$extension(this.signed());
   }

   public byte byteValue() {
      return UByte$.MODULE$.byteValue$extension(this.signed());
   }

   public short shortValue() {
      return UByte$.MODULE$.shortValue$extension(this.signed());
   }

   public int intValue() {
      return UByte$.MODULE$.intValue$extension(this.signed());
   }

   public long longValue() {
      return UByte$.MODULE$.longValue$extension(this.signed());
   }

   public float floatValue() {
      return UByte$.MODULE$.floatValue$extension(this.signed());
   }

   public double doubleValue() {
      return UByte$.MODULE$.doubleValue$extension(this.signed());
   }

   public boolean isWhole() {
      return UByte$.MODULE$.isWhole$extension(this.signed());
   }

   public Object underlying() {
      return UByte$.MODULE$.underlying$extension(this.signed());
   }

   public boolean isValidByte() {
      return UByte$.MODULE$.isValidByte$extension(this.signed());
   }

   public boolean isValidShort() {
      return UByte$.MODULE$.isValidShort$extension(this.signed());
   }

   public boolean isValidChar() {
      return UByte$.MODULE$.isValidChar$extension(this.signed());
   }

   public boolean isValidInt() {
      return UByte$.MODULE$.isValidInt$extension(this.signed());
   }

   public boolean isValidLong() {
      return UByte$.MODULE$.isValidLong$extension(this.signed());
   }

   public String toString() {
      return UByte$.MODULE$.toString$extension(this.signed());
   }

   public boolean $eq$eq(final byte that) {
      return UByte$.MODULE$.$eq$eq$extension(this.signed(), that);
   }

   public boolean $bang$eq(final byte that) {
      return UByte$.MODULE$.$bang$eq$extension(this.signed(), that);
   }

   public boolean $eq$eq$eq(final byte that) {
      return UByte$.MODULE$.$eq$eq$eq$extension(this.signed(), that);
   }

   public boolean $eq$bang$eq(final byte that) {
      return UByte$.MODULE$.$eq$bang$eq$extension(this.signed(), that);
   }

   public boolean $less$eq(final byte that) {
      return UByte$.MODULE$.$less$eq$extension(this.signed(), that);
   }

   public boolean $less(final byte that) {
      return UByte$.MODULE$.$less$extension(this.signed(), that);
   }

   public boolean $greater$eq(final byte that) {
      return UByte$.MODULE$.$greater$eq$extension(this.signed(), that);
   }

   public boolean $greater(final byte that) {
      return UByte$.MODULE$.$greater$extension(this.signed(), that);
   }

   public byte unary_$minus() {
      return UByte$.MODULE$.unary_$minus$extension(this.signed());
   }

   public byte $plus(final byte that) {
      return UByte$.MODULE$.$plus$extension(this.signed(), that);
   }

   public byte $minus(final byte that) {
      return UByte$.MODULE$.$minus$extension(this.signed(), that);
   }

   public byte $times(final byte that) {
      return UByte$.MODULE$.$times$extension(this.signed(), that);
   }

   public byte $div(final byte that) {
      return UByte$.MODULE$.$div$extension(this.signed(), that);
   }

   public byte $percent(final byte that) {
      return UByte$.MODULE$.$percent$extension(this.signed(), that);
   }

   public byte unary_$tilde() {
      return UByte$.MODULE$.unary_$tilde$extension(this.signed());
   }

   public byte $less$less(final int shift) {
      return UByte$.MODULE$.$less$less$extension(this.signed(), shift);
   }

   public byte $greater$greater(final int shift) {
      return UByte$.MODULE$.$greater$greater$extension(this.signed(), shift);
   }

   public byte $greater$greater$greater(final int shift) {
      return UByte$.MODULE$.$greater$greater$greater$extension(this.signed(), shift);
   }

   public byte $amp(final byte that) {
      return UByte$.MODULE$.$amp$extension(this.signed(), that);
   }

   public byte $bar(final byte that) {
      return UByte$.MODULE$.$bar$extension(this.signed(), that);
   }

   public byte $up(final byte that) {
      return UByte$.MODULE$.$up$extension(this.signed(), that);
   }

   public byte $times$times(final byte that) {
      return UByte$.MODULE$.$times$times$extension(this.signed(), that);
   }

   public int hashCode() {
      return UByte$.MODULE$.hashCode$extension(this.signed());
   }

   public boolean equals(final Object x$1) {
      return UByte$.MODULE$.equals$extension(this.signed(), x$1);
   }

   public UByte(final byte signed) {
      this.signed = signed;
      ScalaNumericAnyConversions.$init$(this);
   }
}
