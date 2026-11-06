package visitor;
import model.DirectoryNode;
import model.FileNode;
import model.Visitor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchVisitor implements Visitor {

    private final String filenameCriterion;
    private final long minimumSize;
    private final List<String> matches = new ArrayList<>();

    private String currentPath = "";

    public SearchVisitor(String filenameCriterion, long minimumSize) {
        if (filenameCriterion == null) {
            throw new IllegalArgumentException(
                    "Filename criterion cannot be null");
        }

        if (minimumSize < 0) {
            throw new IllegalArgumentException(
                    "Minimum size cannot be negative");
        }

        this.filenameCriterion = filenameCriterion;
        this.minimumSize = minimumSize;
    }


    @Override
    public void setContext(String path, int depth) {
        this.currentPath = path;
    }


    @Override
    public void visit(FileNode file) {
        boolean nameMatches =
                file.getName().contains(filenameCriterion);

        boolean sizeMatches =
                file.getSize() >= minimumSize;

        if (nameMatches && sizeMatches) {
            matches.add(currentPath);
        }
    }

    @Override
    public void visit(DirectoryNode directory) {
    }

    public List<String> getMatches() {
        List<String> sorted = new ArrayList<>(matches);
        Collections.sort(sorted);
        return Collections.unmodifiableList(sorted);
    }

    @Override
    public void reset() {
        matches.clear();
        currentPath = "";
    }

}
