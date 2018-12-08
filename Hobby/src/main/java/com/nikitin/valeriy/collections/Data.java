package com.nikitin.valeriy.collections;

/**
 * Data class that generate objects
 * and prints info to the console
 */
public class Data {
    private User[] users;
    private static final String ARRAY_LIST = "ArrayList ";
    private static final String LINKED_LIST = "LinkedList";
    private static final String HASH_SET = "HashSet   ";
    private static final String TREE_SET = "TreeSet   ";
    private static final String HASH_MAP = "HashMap   ";
    private static final String TREE_MAP = "TreeMap   ";
    private static final String ADD = "Add ";
    private static final String REMOVE = "Remove";
    private static final String GET_ELEMENT = "GetElement";
    private String arrayAdd;
    private String arrayRemove;
    private String arrayGetElement;
    private String linkedAdd;
    private String linkedRemove;
    private String linkedGetElement;
    private String hashSetAdd;
    private String hashSetRemove;
    private String hashSetGetElement;
    private String treeSetAdd;
    private String treeSetRemove;
    private String treeSetGetElement;
    private String hashMapAdd;
    private String hashMapRemove;
    private String hashMapGetElement;
    private String treeMapAdd;
    private String treeMapRemove;
    private String treeMapGetElement;

    /**
     * Generates specified amount of User objects
     * @param count Number of objects to create
     */
    public void generateObjects(int count) {
        users = new User[count];
        for (int i = 0; i < count; i++) {
            users[i] = new User(i, "User " + i);
        }
    }

    /**
     * Prints table with Add, Remove, GetElement
     * according to different collection
     */
    public void printTable() {
        String[][] result = new String[7][4];
        result[0][0] = "          ";
        result[0][1] = ADD;
        result[0][2] = REMOVE;
        result[0][3] = GET_ELEMENT;
        result[1][0] = ARRAY_LIST;
        result[1][1] = arrayAdd;
        result[1][2] = arrayRemove;
        result[1][3] = arrayGetElement;
        result[2][0] = LINKED_LIST;
        result[2][1] = linkedAdd;
        result[2][2] = linkedRemove;
        result[2][3] = linkedGetElement;
        result[3][0] = HASH_SET;
        result[3][1] = hashSetAdd;
        result[3][2] = hashSetRemove;
        result[3][3] = hashSetGetElement;
        result[4][0] = TREE_SET;
        result[4][1] = treeSetAdd;
        result[4][2] = treeSetRemove;
        result[4][3] = treeSetGetElement;
        result[5][0] = HASH_MAP;
        result[5][1] = hashMapAdd;
        result[5][2] = hashMapRemove;
        result[5][3] = hashMapGetElement;
        result[6][0] = TREE_MAP;
        result[6][1] = treeMapAdd;
        result[6][2] = treeMapRemove;
        result[6][3] = treeMapGetElement;


        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (j == result[i].length - 1) {
                    System.out.println(result[i][j]);
                } else {
                    System.out.print(result[i][j] + "   ");
                }
            }
        }
    }

    public User[] getUsers() {
        return users;
    }

    public String getArrayAdd() {
        return arrayAdd;
    }

    public void setArrayAdd(String arrayAdd) {
        this.arrayAdd = arrayAdd;
    }

    public String getArrayRemove() {
        return arrayRemove;
    }

    public void setArrayRemove(String arrayRemove) {
        this.arrayRemove = arrayRemove;
    }

    public String getArrayGetElement() {
        return arrayGetElement;
    }

    public void setArrayGetElement(String arrayGetElement) {
        this.arrayGetElement = arrayGetElement;
    }

    public String getLinkedAdd() {
        return linkedAdd;
    }

    public void setLinkedAdd(String linkedAdd) {
        this.linkedAdd = linkedAdd;
    }

    public String getLinkedRemove() {
        return linkedRemove;
    }

    public void setLinkedRemove(String linkedRemove) {
        this.linkedRemove = linkedRemove;
    }

    public String getLinkedGetElement() {
        return linkedGetElement;
    }

    public void setLinkedGetElement(String linkedGetElement) {
        this.linkedGetElement = linkedGetElement;
    }

    public String getHashSetAdd() {
        return hashSetAdd;
    }

    public void setHashSetAdd(String hashSetAdd) {
        this.hashSetAdd = hashSetAdd;
    }

    public String getHashSetRemove() {
        return hashSetRemove;
    }

    public void setHashSetRemove(String hashSetRemove) {
        this.hashSetRemove = hashSetRemove;
    }

    public String getHashSetGetElement() {
        return hashSetGetElement;
    }

    public void setHashSetGetElement(String hashSetGetElement) {
        this.hashSetGetElement = hashSetGetElement;
    }

    public String getTreeSetAdd() {
        return treeSetAdd;
    }

    public void setTreeSetAdd(String treeSetAdd) {
        this.treeSetAdd = treeSetAdd;
    }

    public String getTreeSetRemove() {
        return treeSetRemove;
    }

    public void setTreeSetRemove(String treeSetRemove) {
        this.treeSetRemove = treeSetRemove;
    }

    public String getTreeSetGetElement() {
        return treeSetGetElement;
    }

    public void setTreeSetGetElement(String treeSetGetElement) {
        this.treeSetGetElement = treeSetGetElement;
    }

    public String getHashMapAdd() {
        return hashMapAdd;
    }

    public void setHashMapAdd(String hashMapAdd) {
        this.hashMapAdd = hashMapAdd;
    }

    public String getHashMapRemove() {
        return hashMapRemove;
    }

    public void setHashMapRemove(String hashMapRemove) {
        this.hashMapRemove = hashMapRemove;
    }

    public String getHashMapGetElement() {
        return hashMapGetElement;
    }

    public void setHashMapGetElement(String hashMapGetElement) {
        this.hashMapGetElement = hashMapGetElement;
    }

    public String getTreeMapAdd() {
        return treeMapAdd;
    }

    public void setTreeMapAdd(String treeMapAdd) {
        this.treeMapAdd = treeMapAdd;
    }

    public String getTreeMapRemove() {
        return treeMapRemove;
    }

    public void setTreeMapRemove(String treeMapRemove) {
        this.treeMapRemove = treeMapRemove;
    }

    public String getTreeMapGetElement() {
        return treeMapGetElement;
    }

    public void setTreeMapGetElement(String treeMapGetElement) {
        this.treeMapGetElement = treeMapGetElement;
    }
}
