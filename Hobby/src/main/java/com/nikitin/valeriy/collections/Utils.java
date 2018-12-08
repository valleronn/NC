package com.nikitin.valeriy.collections;

import java.util.*;

/**
 * Stores collections
 * and methods to process them
 */
public class Utils {
    private Data data;
    private ArrayList<User> arrayList;
    private LinkedList<User> linkedList;
    private HashSet<User> hashSet;
    private TreeSet<User> treeSet;
    private HashMap<Integer, User> hashMap;
    private TreeMap<Integer, User> treeMap;

    public Utils(int numberOfObjects) {
        data = new Data();
        data.generateObjects(numberOfObjects);
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
        hashSet = new HashSet<>();
        treeSet = new TreeSet<>();
        hashMap = new HashMap<>();
        treeMap = new TreeMap<>();
    }

    /**
     * Adds elements to the collections
     * @param collectionType Type of a collection
     */
    public void addElementsToCollection(CollectionType collectionType) {
        for (int i = 0; i < data.getUsers().length; i++) {
            if (collectionType == CollectionType.ARRAY_LIST) {
                arrayList.add(data.getUsers()[i]);
            } else if (collectionType == CollectionType.LINKED_LIST) {
                linkedList.add(data.getUsers()[i]);
            } else if (collectionType == CollectionType.HASH_SET) {
                hashSet.add(data.getUsers()[i]);
            } else if (collectionType == CollectionType.TREE_SET) {
                treeSet.add(data.getUsers()[i]);
            } else if (collectionType == CollectionType.HASH_MAP) {
                hashMap.put(i, data.getUsers()[i]);
            } else if (collectionType == CollectionType.TREE_MAP) {
                treeMap.put(i, data.getUsers()[i]);
            }
        }
    }

    /**
     * Gets elements from the collections
     * @param collectionType Type of a collection
     * @param index Index to get the element by
     */
    public void getElementsFromCollection(CollectionType collectionType, int index) {
        if (collectionType == CollectionType.ARRAY_LIST) {
            arrayList.get(index);
        } else if (collectionType == CollectionType.LINKED_LIST) {
            linkedList.get(index);
        } else if (collectionType == CollectionType.HASH_SET) {
            //hashSet = null;
        } else if (collectionType == CollectionType.TREE_SET) {
            //treeSet = null;
        } else if (collectionType == CollectionType.HASH_MAP) {
            hashMap.get(index);
        } else if (collectionType == CollectionType.TREE_MAP) {
            treeMap.get(index);
        }
    }

    /**
     * Removes elements from the collections
     * @param collectionType Type of a collection
     */
    public void removeElementsFromCollection(CollectionType collectionType) {
        for (int i = 0; i < data.getUsers().length; i++) {
            if (collectionType == CollectionType.ARRAY_LIST) {
                arrayList.remove(data.getUsers()[i]);
            } else if (collectionType == CollectionType.LINKED_LIST) {
                linkedList.remove(data.getUsers()[i]);
            } else if (collectionType == CollectionType.HASH_SET) {
                hashSet.remove(data.getUsers()[i]);
            } else if (collectionType == CollectionType.TREE_SET) {
                treeSet.remove(data.getUsers()[i]);
            } else if (collectionType == CollectionType.HASH_MAP) {
                hashMap.remove(i);
            } else if (collectionType == CollectionType.TREE_MAP) {
                treeMap.remove(i);
            }
        }

    }
}
