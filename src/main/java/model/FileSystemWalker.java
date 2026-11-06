package model;
public class FileSystemWalker {

    public void walk(Element root, Visitor visitor) {
        visitor.reset();
        walkRecursive(root, visitor, "", 0);
    }

    private void walkRecursive(Element element,
                               Visitor visitor,
                               String path,
                               int depth) {

        element.accept(new Visitor() {

            @Override
            public void visit(FileNode file) {
                String filePath = path.isEmpty()
                        ? file.getName()
                        : path;

                visitor.setContext(filePath, depth);
                visitor.visit(file);
            }

            @Override
            public void visit(DirectoryNode directory) {
                visitor.setContext(path, depth);
                visitor.visit(directory);

                for (Element child : directory.getChildren()) {
                    String childPath = path.isEmpty()
                            ? child.getName()
                            : path + "/" + child.getName();

                    walkRecursive(child, visitor, childPath, depth + 1);
                }
            }
        });
    }
}

