package breeze.linalg;

public final class View$ {
   public static final View$ MODULE$ = new View$();

   public View viewPreferenceFromBoolean(final boolean b) {
      return (View)(b ? View.Require$.MODULE$ : View.Copy$.MODULE$);
   }

   private View$() {
   }
}
