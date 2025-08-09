package org.jline.builtins;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NfaMatcher {
   private final String regexp;
   private final BiFunction matcher;
   private volatile State start;

   public NfaMatcher(String regexp, BiFunction matcher) {
      this.regexp = regexp;
      this.matcher = matcher;
   }

   public void compile() {
      if (this.start == null) {
         this.start = toNfa(toPostFix(this.regexp));
      }

   }

   public boolean match(List args) {
      Set<State> clist = new HashSet();
      this.compile();
      this.addState(clist, this.start);

      for(Object arg : args) {
         Set<State> nlist = new HashSet();
         clist.stream().filter((s) -> !Objects.equals("++MATCH++", s.c) && !Objects.equals("++SPLIT++", s.c)).filter((s) -> (Boolean)this.matcher.apply(arg, s.c)).forEach((s) -> this.addState(nlist, s.out));
         clist = nlist;
      }

      return clist.stream().anyMatch((s) -> Objects.equals("++MATCH++", s.c));
   }

   public Set matchPartial(List args) {
      Set<State> clist = new HashSet();
      this.compile();
      this.addState(clist, this.start);

      for(Object arg : args) {
         Set<State> nlist = new HashSet();
         clist.stream().filter((s) -> !Objects.equals("++MATCH++", s.c) && !Objects.equals("++SPLIT++", s.c)).filter((s) -> (Boolean)this.matcher.apply(arg, s.c)).forEach((s) -> this.addState(nlist, s.out));
         clist = nlist;
      }

      return (Set)clist.stream().filter((s) -> !Objects.equals("++MATCH++", s.c) && !Objects.equals("++SPLIT++", s.c)).map((s) -> s.c).collect(Collectors.toSet());
   }

   void addState(Set l, State s) {
      if (s != null && l.add(s) && Objects.equals("++SPLIT++", s.c)) {
         this.addState(l, s.out);
         this.addState(l, s.out1);
      }

   }

   static State toNfa(List postfix) {
      Deque<Frag> stack = new ArrayDeque();

      for(String p : postfix) {
         switch (p) {
            case ".":
               Frag e2 = (Frag)stack.pollLast();
               Frag e1 = (Frag)stack.pollLast();
               e1.patch(e2.start);
               stack.offerLast(new Frag(e1.start, e2.out));
               break;
            case "|":
               Frag e2 = (Frag)stack.pollLast();
               Frag e1 = (Frag)stack.pollLast();
               State s = new State("++SPLIT++", e1.start, e2.start);
               stack.offerLast(new Frag(s, e1.out, e2.out));
               break;
            case "?":
               Frag e = (Frag)stack.pollLast();
               State s = new State("++SPLIT++", e.start, (State)null);
               List var10004 = e.out;
               Objects.requireNonNull(s);
               stack.offerLast(new Frag(s, var10004, s::setOut1));
               break;
            case "*":
               Frag e = (Frag)stack.pollLast();
               State s = new State("++SPLIT++", e.start, (State)null);
               e.patch(s);
               Objects.requireNonNull(s);
               stack.offerLast(new Frag(s, s::setOut1));
               break;
            case "+":
               Frag e = (Frag)stack.pollLast();
               State s = new State("++SPLIT++", e.start, (State)null);
               e.patch(s);
               State var10003 = e.start;
               Objects.requireNonNull(s);
               stack.offerLast(new Frag(var10003, s::setOut1));
               break;
            default:
               State s = new State(p, (State)null, (State)null);
               Objects.requireNonNull(s);
               stack.offerLast(new Frag(s, s::setOut));
         }
      }

      Frag e = (Frag)stack.pollLast();
      if (!stack.isEmpty()) {
         throw new IllegalStateException("Wrong postfix expression, " + stack.size() + " elements remaining");
      } else {
         e.patch(new State("++MATCH++", (State)null, (State)null));
         return e.start;
      }
   }

   static List toPostFix(String regexp) {
      List<String> postfix = new ArrayList();
      int s = -1;
      int natom = 0;
      int nalt = 0;
      Deque<Integer> natoms = new ArrayDeque();
      Deque<Integer> nalts = new ArrayDeque();

      label100:
      for(int i = 0; i < regexp.length(); ++i) {
         char c = regexp.charAt(i);
         if (Character.isJavaIdentifierPart(c)) {
            if (s < 0) {
               s = i;
            }
         } else {
            if (s >= 0) {
               if (natom > 1) {
                  --natom;
                  postfix.add(".");
               }

               postfix.add(regexp.substring(s, i));
               ++natom;
               s = -1;
            }

            if (!Character.isWhitespace(c)) {
               switch (c) {
                  case '(':
                     if (natom > 1) {
                        --natom;
                        postfix.add(".");
                     }

                     nalts.offerLast(nalt);
                     natoms.offerLast(natom);
                     nalt = 0;
                     natom = 0;
                     break;
                  case ')':
                     if (nalts.isEmpty() || natom == 0) {
                        throw new IllegalStateException("unexpected '" + c + "' at pos " + i);
                     }

                     while(true) {
                        --natom;
                        if (natom <= 0) {
                           while(nalt > 0) {
                              postfix.add("|");
                              --nalt;
                           }

                           nalt = (Integer)nalts.pollLast();
                           natom = (Integer)natoms.pollLast();
                           ++natom;
                           continue label100;
                        }

                        postfix.add(".");
                     }
                  case '*':
                  case '+':
                  case '?':
                     if (natom == 0) {
                        throw new IllegalStateException("unexpected '" + c + "' at pos " + i);
                     }

                     postfix.add(String.valueOf(c));
                     break;
                  case '|':
                     if (natom == 0) {
                        throw new IllegalStateException("unexpected '" + c + "' at pos " + i);
                     }

                     while(true) {
                        --natom;
                        if (natom <= 0) {
                           ++nalt;
                           continue label100;
                        }

                        postfix.add(".");
                     }
                  default:
                     throw new IllegalStateException("unexpected '" + c + "' at pos " + i);
               }
            }
         }
      }

      if (s >= 0) {
         if (natom > 1) {
            --natom;
            postfix.add(".");
         }

         postfix.add(regexp.substring(s));
         ++natom;
      }

      while(true) {
         --natom;
         if (natom <= 0) {
            while(nalt > 0) {
               postfix.add("|");
               --nalt;
            }

            return postfix;
         }

         postfix.add(".");
      }
   }

   static class State {
      static final String Match = "++MATCH++";
      static final String Split = "++SPLIT++";
      final String c;
      State out;
      State out1;

      public State(String c, State out, State out1) {
         this.c = c;
         this.out = out;
         this.out1 = out1;
      }

      public void setOut(State out) {
         this.out = out;
      }

      public void setOut1(State out1) {
         this.out1 = out1;
      }
   }

   private static class Frag {
      final State start;
      final List out = new ArrayList();

      public Frag(State start, Collection l) {
         this.start = start;
         this.out.addAll(l);
      }

      public Frag(State start, Collection l1, Collection l2) {
         this.start = start;
         this.out.addAll(l1);
         this.out.addAll(l2);
      }

      public Frag(State start, Consumer c) {
         this.start = start;
         this.out.add(c);
      }

      public Frag(State start, Collection l, Consumer c) {
         this.start = start;
         this.out.addAll(l);
         this.out.add(c);
      }

      public void patch(State s) {
         this.out.forEach((c) -> c.accept(s));
      }
   }
}
