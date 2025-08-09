package scala.xml;

public final class Equality$ {
   public static final Equality$ MODULE$ = new Equality$();

   public Object asRef(final Object x) {
      return x;
   }

   public boolean compareBlithely(final Object x1, final String x2) {
      if (x1 instanceof Atom) {
         boolean var11;
         label53: {
            Atom var5 = (Atom)x1;
            Object var10 = var5.data();
            if (var10 == null) {
               if (x2 == null) {
                  break label53;
               }
            } else if (var10.equals(x2)) {
               break label53;
            }

            var11 = false;
            return var11;
         }

         var11 = true;
         return var11;
      } else if (!(x1 instanceof NodeSeq)) {
         return false;
      } else {
         boolean var9;
         label55: {
            NodeSeq var7 = (NodeSeq)x1;
            String var10000 = var7.text();
            if (var10000 == null) {
               if (x2 == null) {
                  break label55;
               }
            } else if (var10000.equals(x2)) {
               break label55;
            }

            var9 = false;
            return var9;
         }

         var9 = true;
         return var9;
      }
   }

   public boolean compareBlithely(final Object x1, final Node x2) {
      if (x1 instanceof NodeSeq) {
         NodeSeq var5 = (NodeSeq)x1;
         if (var5.length() == 1) {
            boolean var10000;
            label31: {
               Node var6 = var5.apply(0);
               if (x2 == null) {
                  if (var6 == null) {
                     break label31;
                  }
               } else if (x2.equals(var6)) {
                  break label31;
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      return false;
   }

   public boolean compareBlithely(final Object x1, final Object x2) {
      if (x1 != null && x2 != null) {
         if (x2 instanceof String) {
            String var5 = (String)x2;
            return this.compareBlithely(x1, var5);
         } else if (x2 instanceof Node) {
            Node var6 = (Node)x2;
            return this.compareBlithely(x1, var6);
         } else {
            return false;
         }
      } else {
         return x1 == null && x2 == null;
      }
   }

   private Equality$() {
   }
}
