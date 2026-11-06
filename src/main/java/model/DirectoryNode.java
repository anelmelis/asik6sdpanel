package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryNode implements Element {

    private final String name;
    private final List<Element> children = new ArrayList<>();

    public DirectoryNode(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Directory name cannot be empty");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Element child) {
        if (child == null) {
            throw new IllegalArgumentException("Child cannot be null");
        }
        for (Element existing : children) {
            if (existing.getName().equals(child.getName())) {
                throw new IllegalArgumentException("Duplicate child name");
            }
        }

        if (child == this) {
            throw new IllegalArgumentException(
                    "A directory cannot contain itself");
        }

        if (children.contains(child)) {
            throw new IllegalArgumentException(
                    "Element already exists in this directory");
        }
        TreeOwnership.attach(this, child);
        children.add(child);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
