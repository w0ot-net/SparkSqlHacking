package org.apache.zookeeper.common;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathTrie {
   private static final Logger LOG = LoggerFactory.getLogger(PathTrie.class);
   private final TrieNode rootNode;
   private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
   private final Lock readLock;
   private final Lock writeLock;

   public PathTrie() {
      this.readLock = this.lock.readLock();
      this.writeLock = this.lock.writeLock();
      this.rootNode = new TrieNode((TrieNode)null, "/");
   }

   public void addPath(String path) {
      Objects.requireNonNull(path, "Path cannot be null");
      if (path.length() == 0) {
         throw new IllegalArgumentException("Invalid path: " + path);
      } else {
         String[] pathComponents = split(path);
         this.writeLock.lock();

         try {
            TrieNode parent = this.rootNode;

            for(String part : pathComponents) {
               TrieNode child = parent.getChild(part);
               if (child == null) {
                  child = new TrieNode(parent, part);
                  parent.addChild(part, child);
               }

               parent = child;
            }

            parent.setProperty(true);
         } finally {
            this.writeLock.unlock();
         }

      }
   }

   public void deletePath(String path) {
      Objects.requireNonNull(path, "Path cannot be null");
      if (path.length() == 0) {
         throw new IllegalArgumentException("Invalid path: " + path);
      } else {
         String[] pathComponents = split(path);
         this.writeLock.lock();

         try {
            TrieNode parent = this.rootNode;

            for(String part : pathComponents) {
               if (parent.getChild(part) == null) {
                  return;
               }

               parent = parent.getChild(part);
               LOG.debug("{}", parent);
            }

            TrieNode realParent = parent.getParent();
            realParent.deleteChild(parent.getValue());
         } finally {
            this.writeLock.unlock();
         }
      }
   }

   public boolean existsNode(String path) {
      Objects.requireNonNull(path, "Path cannot be null");
      if (path.length() == 0) {
         throw new IllegalArgumentException("Invalid path: " + path);
      } else {
         String[] pathComponents = split(path);
         this.readLock.lock();

         try {
            TrieNode parent = this.rootNode;

            for(String part : pathComponents) {
               if (parent.getChild(part) == null) {
                  boolean var8 = false;
                  return var8;
               }

               parent = parent.getChild(part);
               LOG.debug("{}", parent);
            }

            return true;
         } finally {
            this.readLock.unlock();
         }
      }
   }

   public String findMaxPrefix(String path) {
      Objects.requireNonNull(path, "Path cannot be null");
      String[] pathComponents = split(path);
      this.readLock.lock();

      String var12;
      try {
         TrieNode parent = this.rootNode;
         TrieNode deepestPropertyNode = null;

         for(String element : pathComponents) {
            parent = parent.getChild(element);
            if (parent == null) {
               LOG.debug("{}", element);
               break;
            }

            if (parent.hasProperty()) {
               deepestPropertyNode = parent;
            }
         }

         if (deepestPropertyNode != null) {
            Deque<String> treePath = new ArrayDeque();

            for(TrieNode node = deepestPropertyNode; node != this.rootNode; node = node.parent) {
               treePath.offerFirst(node.getValue());
            }

            String var15 = "/" + String.join("/", treePath);
            return var15;
         }

         var12 = "/";
      } finally {
         this.readLock.unlock();
      }

      return var12;
   }

   public void clear() {
      this.writeLock.lock();

      try {
         this.rootNode.getChildren().clear();
      } finally {
         this.writeLock.unlock();
      }

   }

   private static String[] split(String path) {
      return (String[])Stream.of(path.split("/")).filter((t) -> !t.trim().isEmpty()).toArray((x$0) -> new String[x$0]);
   }

   static class TrieNode {
      final String value;
      final Map children;
      boolean property;
      TrieNode parent;

      private TrieNode(TrieNode parent, String value) {
         this.value = value;
         this.parent = parent;
         this.property = false;
         this.children = new HashMap(4);
      }

      TrieNode getParent() {
         return this.parent;
      }

      void setParent(TrieNode parent) {
         this.parent = parent;
      }

      void setProperty(boolean prop) {
         this.property = prop;
      }

      boolean hasProperty() {
         return this.property;
      }

      public String getValue() {
         return this.value;
      }

      void addChild(String childName, TrieNode node) {
         this.children.putIfAbsent(childName, node);
      }

      void deleteChild(String childName) {
         this.children.computeIfPresent(childName, (key, childNode) -> {
            childNode.setProperty(false);
            if (childNode.isLeafNode()) {
               childNode.setParent((TrieNode)null);
               return null;
            } else {
               return childNode;
            }
         });
      }

      TrieNode getChild(String childName) {
         return (TrieNode)this.children.get(childName);
      }

      Collection getChildren() {
         return this.children.keySet();
      }

      boolean isLeafNode() {
         return this.children.isEmpty();
      }

      public String toString() {
         return "TrieNode [name=" + this.value + ", property=" + this.property + ", children=" + this.children.keySet() + "]";
      }
   }
}
