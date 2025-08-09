package javolution.context;

import java.util.Map;
import javolution.util.FastMap;
import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

public class PersistentContext extends Context {
   private static PersistentContext _PersistentContext = new PersistentContext();
   private final FastMap _idToValue = new FastMap();
   static final XMLFormat PERSISTENT_CONTEXT_XML = new XMLFormat(PersistentContext.class) {
      public void read(XMLFormat.InputElement xml, Object obj) throws XMLStreamException {
         PersistentContext ctx = (PersistentContext)obj;
         ctx.getIdToValue().putAll((FastMap)xml.get("References"));
      }

      public void write(Object obj, XMLFormat.OutputElement xml) throws XMLStreamException {
         PersistentContext ctx = (PersistentContext)obj;
         xml.add(ctx.getIdToValue(), "References");
      }
   };

   public static void setCurrentPersistentContext(PersistentContext ctx) {
      _PersistentContext = ctx;
      synchronized(PersistentContext.Reference.INSTANCES) {
         FastMap.Entry e = PersistentContext.Reference.INSTANCES.head();
         FastMap.Entry end = PersistentContext.Reference.INSTANCES.tail();

         while((e = e.getNext()) != end) {
            Reference reference = (Reference)e.getValue();
            if (ctx._idToValue.containsKey(reference._id)) {
               reference.set(ctx._idToValue.get(reference._id));
            }
         }

      }
   }

   public static PersistentContext getCurrentPersistentContext() {
      return _PersistentContext;
   }

   public Map getIdToValue() {
      return this._idToValue;
   }

   protected void enterAction() {
      throw new UnsupportedOperationException("Cannot enter persistent context (already in)");
   }

   protected void exitAction() {
      throw new UnsupportedOperationException("Cannot exit persistent context (always in)");
   }

   public static class Reference implements javolution.lang.Reference {
      private static final FastMap INSTANCES = new FastMap();
      private final String _id;
      private Object _value;

      public Reference(String id, Object defaultValue) {
         this._id = id;
         this._value = defaultValue;
         synchronized(INSTANCES) {
            if (INSTANCES.containsKey(id)) {
               throw new IllegalArgumentException("Identifier " + id + " already in use");
            }

            INSTANCES.put(id, this);
         }

         if (PersistentContext._PersistentContext._idToValue.containsKey(id)) {
            this.set(PersistentContext._PersistentContext._idToValue.get(id));
         } else {
            PersistentContext._PersistentContext._idToValue.put(id, defaultValue);
         }

      }

      public Object get() {
         return this._value;
      }

      public void set(Object value) {
         this._value = value;
         this.notifyChange();
      }

      public void setMinimum(Object value) {
         synchronized(this) {
            if (value instanceof Comparable) {
               Object prevValue = this.get();
               if (((Comparable)value).compareTo(prevValue) > 0) {
                  this.set(value);
               }
            } else {
               if (!(value instanceof Integer)) {
                  throw new IllegalArgumentException();
               }

               Object prevValue = this.get();
               if ((Integer)value > (Integer)prevValue) {
                  this.set(value);
               }
            }

         }
      }

      public void setMaximum(Object value) {
         synchronized(this) {
            if (value instanceof Comparable) {
               Object prevValue = this.get();
               if (((Comparable)value).compareTo(prevValue) < 0) {
                  this.set(value);
               }
            } else {
               if (!(value instanceof Integer)) {
                  throw new IllegalArgumentException();
               }

               Object prevValue = this.get();
               if ((Integer)value < (Integer)prevValue) {
                  this.set(value);
               }
            }

         }
      }

      public String toString() {
         return String.valueOf(this.get());
      }

      protected void notifyChange() {
      }
   }
}
