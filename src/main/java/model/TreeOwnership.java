package model;
import java.util.IdentityHashMap;
import java.util.Map;

final class TreeOwnership {

    private static final Map<Element, DirectoryNode> parents =
            new IdentityHashMap<>();

    private TreeOwnership() {
    }

    static void attach(DirectoryNode parent, Element child) {
        if (parents.containsKey(child)) {
            throw new IllegalArgumentException(
                    "Element already has a parent");
        }

        DirectoryNode current = parent;

        while (current != null) {
            if (current == child) {
                throw new IllegalArgumentException(
                        "Cycle detected");
            }
            current = parents.get(current);
        }
        parents.put(child, parent);
    }
}
