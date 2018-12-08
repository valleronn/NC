package com.nikitin.valeriy.collections;

/**
 * Collection time evaluation application
 */
public class Main {
    private static final int NUMBER_OF_OBJECTS = 1000000;
    private static final int INDEX = NUMBER_OF_OBJECTS / 2;

    public static void main(String[] args) {
        Data data = new Data();
        Utils utils = new Utils(NUMBER_OF_OBJECTS);

        //adding elements to ArrayList
        long t1 = System.currentTimeMillis();
        utils.addElementsToCollection(CollectionType.ARRAY_LIST);
        long t2 = System.currentTimeMillis();
        String addToArrayList = String.valueOf(t2 - t1);
        data.setArrayAdd(addToArrayList);

        //adding elements to LinkedList
        long t3 = System.currentTimeMillis();
        utils.addElementsToCollection(CollectionType.LINKED_LIST);
        long t4 = System.currentTimeMillis();
        String addToLinkedList = String.valueOf(t4 - t3);
        data.setLinkedAdd(addToLinkedList);

        //adding elements to HashSet
        long t5 = System.currentTimeMillis();
        utils.addElementsToCollection(CollectionType.HASH_SET);
        long t6 = System.currentTimeMillis();
        String addToHashSet = String.valueOf(t6 - t5);
        data.setHashSetAdd(addToHashSet);

        //adding elements to TreeSet
        long t7 = System.currentTimeMillis();
        utils.addElementsToCollection(CollectionType.TREE_SET);
        long t8 = System.currentTimeMillis();
        String addToTreeSet = String.valueOf(t8 - t7);
        data.setTreeSetAdd(addToTreeSet);

        //adding elements to HashMap
        long t9 = System.currentTimeMillis();
        utils.addElementsToCollection(CollectionType.HASH_MAP);
        long t10 = System.currentTimeMillis();
        String addToHashMap = String.valueOf(t10 - t9);
        data.setHashMapAdd(addToHashMap);

        //adding elements to TreeMap
        long t11 = System.currentTimeMillis();
        utils.addElementsToCollection(CollectionType.TREE_MAP);
        long t12 = System.currentTimeMillis();
        String addToTreeMap = String.valueOf(t12 - t11);
        data.setTreeMapAdd(addToTreeMap);

        //getting element from ArrayList
        long t13 = System.currentTimeMillis();
        utils.getElementsFromCollection(CollectionType.ARRAY_LIST, INDEX);
        long t14 = System.currentTimeMillis();
        String getFromArrayList = String.valueOf(t14 - t13);
        data.setArrayGetElement(getFromArrayList);

        //getting element from LinkedList
        long t15 = System.currentTimeMillis();
        utils.getElementsFromCollection(CollectionType.LINKED_LIST, INDEX);
        long t16 = System.currentTimeMillis();
        String getFromLinkedList = String.valueOf(t16 - t15);
        data.setLinkedGetElement(getFromLinkedList);

        //getting element from HashSet
        long t17 = System.currentTimeMillis();
        utils.getElementsFromCollection(CollectionType.HASH_SET, INDEX);
        long t18 = System.currentTimeMillis();
        String getFromHashSet = String.valueOf(t18 - t17);
        data.setHashSetGetElement(getFromHashSet);

        //getting element from TreeSet
        long t19 = System.currentTimeMillis();
        utils.getElementsFromCollection(CollectionType.TREE_SET, INDEX);
        long t20 = System.currentTimeMillis();
        String getFromTreeSet = String.valueOf(t20 - t19);
        data.setTreeSetGetElement(getFromTreeSet);

        //getting element from HashMap
        long t21 = System.currentTimeMillis();
        utils.getElementsFromCollection(CollectionType.HASH_MAP, INDEX);
        long t22 = System.currentTimeMillis();
        String getFromHashMap = String.valueOf(t22 - t21);
        data.setHashMapGetElement(getFromHashMap);

        //getting element from TreeMap
        long t23 = System.currentTimeMillis();
        utils.getElementsFromCollection(CollectionType.TREE_MAP, INDEX);
        long t24 = System.currentTimeMillis();
        String getFromTreeMap = String.valueOf(t24 - t23);
        data.setTreeMapGetElement(getFromTreeMap);

        //removing elements from ArrayList
        long t25 = System.currentTimeMillis();
        utils.removeElementsFromCollection(CollectionType.ARRAY_LIST);
        long t26 = System.currentTimeMillis();
        String removeFromArrayList = String.valueOf(t26 - t25);
        data.setArrayRemove(removeFromArrayList);

        //removing elements from LinkedList
        long t27 = System.currentTimeMillis();
        utils.removeElementsFromCollection(CollectionType.LINKED_LIST);
        long t28 = System.currentTimeMillis();
        String removeFromLinkedList = String.valueOf(t28 - t27);
        data.setLinkedRemove(removeFromLinkedList);

        //removing elements from HashSet
        long t29 = System.currentTimeMillis();
        utils.removeElementsFromCollection(CollectionType.HASH_SET);
        long t30 = System.currentTimeMillis();
        String removeFromHashSet = String.valueOf(t30 - t29);
        data.setHashSetRemove(removeFromHashSet);

        //removing elements from TreeSet
        long t31 = System.currentTimeMillis();
        utils.removeElementsFromCollection(CollectionType.TREE_SET);
        long t32 = System.currentTimeMillis();
        String removeFromTreeSet = String.valueOf(t32 - t31);
        data.setTreeSetRemove(removeFromTreeSet);

        //removing elements from HashMap
        long t33 = System.currentTimeMillis();
        utils.removeElementsFromCollection(CollectionType.HASH_MAP);
        long t34 = System.currentTimeMillis();
        String removeFromHashMap = String.valueOf(t34 - t33);
        data.setHashMapRemove(removeFromHashMap);

        //removing elements from TreeMap
        long t35 = System.currentTimeMillis();
        utils.removeElementsFromCollection(CollectionType.TREE_MAP);
        long t36 = System.currentTimeMillis();
        String removeFromTreeMap = String.valueOf(t36 - t35);
        data.setTreeMapRemove(removeFromTreeMap);

        data.printTable();

    }
}
