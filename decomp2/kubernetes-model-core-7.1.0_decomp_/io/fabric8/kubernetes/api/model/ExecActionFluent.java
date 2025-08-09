package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ExecActionFluent extends BaseFluent {
   private List command = new ArrayList();
   private Map additionalProperties;

   public ExecActionFluent() {
   }

   public ExecActionFluent(ExecAction instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ExecAction instance) {
      instance = instance != null ? instance : new ExecAction();
      if (instance != null) {
         this.withCommand(instance.getCommand());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ExecActionFluent addToCommand(int index, String item) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      this.command.add(index, item);
      return this;
   }

   public ExecActionFluent setToCommand(int index, String item) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      this.command.set(index, item);
      return this;
   }

   public ExecActionFluent addToCommand(String... items) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      for(String item : items) {
         this.command.add(item);
      }

      return this;
   }

   public ExecActionFluent addAllToCommand(Collection items) {
      if (this.command == null) {
         this.command = new ArrayList();
      }

      for(String item : items) {
         this.command.add(item);
      }

      return this;
   }

   public ExecActionFluent removeFromCommand(String... items) {
      if (this.command == null) {
         return this;
      } else {
         for(String item : items) {
            this.command.remove(item);
         }

         return this;
      }
   }

   public ExecActionFluent removeAllFromCommand(Collection items) {
      if (this.command == null) {
         return this;
      } else {
         for(String item : items) {
            this.command.remove(item);
         }

         return this;
      }
   }

   public List getCommand() {
      return this.command;
   }

   public String getCommand(int index) {
      return (String)this.command.get(index);
   }

   public String getFirstCommand() {
      return (String)this.command.get(0);
   }

   public String getLastCommand() {
      return (String)this.command.get(this.command.size() - 1);
   }

   public String getMatchingCommand(Predicate predicate) {
      for(String item : this.command) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCommand(Predicate predicate) {
      for(String item : this.command) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ExecActionFluent withCommand(List command) {
      if (command != null) {
         this.command = new ArrayList();

         for(String item : command) {
            this.addToCommand(item);
         }
      } else {
         this.command = null;
      }

      return this;
   }

   public ExecActionFluent withCommand(String... command) {
      if (this.command != null) {
         this.command.clear();
         this._visitables.remove("command");
      }

      if (command != null) {
         for(String item : command) {
            this.addToCommand(item);
         }
      }

      return this;
   }

   public boolean hasCommand() {
      return this.command != null && !this.command.isEmpty();
   }

   public ExecActionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ExecActionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ExecActionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ExecActionFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public ExecActionFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            ExecActionFluent that = (ExecActionFluent)o;
            if (!Objects.equals(this.command, that.command)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.command, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.command != null && !this.command.isEmpty()) {
         sb.append("command:");
         sb.append(this.command + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
