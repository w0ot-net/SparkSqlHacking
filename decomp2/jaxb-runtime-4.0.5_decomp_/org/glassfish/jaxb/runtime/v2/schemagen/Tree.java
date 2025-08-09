package org.glassfish.jaxb.runtime.v2.schemagen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.ContentModelContainer;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.Occurs;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.Particle;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.TypeDefParticle;

abstract class Tree {
   Tree makeOptional(boolean really) {
      return (Tree)(really ? new Optional(this) : this);
   }

   Tree makeRepeated(boolean really) {
      return (Tree)(really ? new Repeated(this) : this);
   }

   static Tree makeGroup(GroupKind kind, List children) {
      if (children.size() == 1) {
         return (Tree)children.get(0);
      } else {
         List<Tree> normalizedChildren = new ArrayList(children.size());

         for(Tree t : children) {
            if (t instanceof Group) {
               Group g = (Group)t;
               if (g.kind == kind) {
                  normalizedChildren.addAll(Arrays.asList(g.children));
                  continue;
               }
            }

            normalizedChildren.add(t);
         }

         return new Group(kind, (Tree[])normalizedChildren.toArray(new Tree[0]));
      }
   }

   abstract boolean isNullable();

   boolean canBeTopLevel() {
      return false;
   }

   protected abstract void write(ContentModelContainer var1, boolean var2, boolean var3);

   protected void write(TypeDefParticle ct) {
      if (this.canBeTopLevel()) {
         this.write((ContentModelContainer)ct._cast(ContentModelContainer.class), false, false);
      } else {
         (new Group(GroupKind.SEQUENCE, new Tree[]{this})).write(ct);
      }

   }

   protected final void writeOccurs(Occurs o, boolean isOptional, boolean repeated) {
      if (isOptional) {
         o.minOccurs(0);
      }

      if (repeated) {
         o.maxOccurs("unbounded");
      }

   }

   abstract static class Term extends Tree {
      boolean isNullable() {
         return false;
      }
   }

   private static final class Optional extends Tree {
      private final Tree body;

      private Optional(Tree body) {
         this.body = body;
      }

      boolean isNullable() {
         return true;
      }

      Tree makeOptional(boolean really) {
         return this;
      }

      protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
         this.body.write(parent, true, repeated);
      }
   }

   private static final class Repeated extends Tree {
      private final Tree body;

      private Repeated(Tree body) {
         this.body = body;
      }

      boolean isNullable() {
         return this.body.isNullable();
      }

      Tree makeRepeated(boolean really) {
         return this;
      }

      protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
         this.body.write(parent, isOptional, true);
      }
   }

   private static final class Group extends Tree {
      private final GroupKind kind;
      private final Tree[] children;

      private Group(GroupKind kind, Tree... children) {
         this.kind = kind;
         this.children = children;
      }

      boolean canBeTopLevel() {
         return true;
      }

      boolean isNullable() {
         if (this.kind == GroupKind.CHOICE) {
            for(Tree t : this.children) {
               if (t.isNullable()) {
                  return true;
               }
            }

            return false;
         } else {
            for(Tree t : this.children) {
               if (!t.isNullable()) {
                  return false;
               }
            }

            return true;
         }
      }

      protected void write(ContentModelContainer parent, boolean isOptional, boolean repeated) {
         Particle c = this.kind.write(parent);
         this.writeOccurs(c, isOptional, repeated);

         for(Tree child : this.children) {
            child.write(c, false, false);
         }

      }
   }
}
