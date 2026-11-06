import model.DirectoryNode;
import model.FileNode;
import model.FileSystemWalker;
import org.junit.jupiter.api.Test;
import visitor.SearchVisitor;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchVisitorTest {

    @Test
    void shouldFilterByNameAndMinimumSize() {

        DirectoryNode root = new DirectoryNode("Root");
        DirectoryNode docs = new DirectoryNode("Docs");

        root.addChild(new FileNode("small.txt", 50));
        root.addChild(new FileNode("large.txt", 500));
        root.addChild(new FileNode("image.png", 800));
        root.addChild(docs);

        docs.addChild(new FileNode("report.txt", 300));

        SearchVisitor visitor = new SearchVisitor(".txt", 200);

        FileSystemWalker walker = new FileSystemWalker();
        walker.walk(root, visitor);

        assertEquals(
                List.of("Docs/report.txt", "large.txt"),
                visitor.getMatches()
        );
    }












    @Test
    void shouldReturnEmptyListWhenNoFilesMatch() {

        DirectoryNode root = new DirectoryNode("Root");

        root.addChild(new FileNode("image.jpg", 500));
        root.addChild(new FileNode("notes.txt", 100));

        SearchVisitor visitor = new SearchVisitor(".pdf", 200);

        FileSystemWalker walker = new FileSystemWalker();
        walker.walk(root, visitor);

        assertEquals(List.of(), visitor.getMatches());
    }











    @Test
    void shouldResetSearchResultsBetweenRuns() {

        DirectoryNode firstRoot = new DirectoryNode("FirstRoot");
        firstRoot.addChild(new FileNode("first.txt", 300));

        DirectoryNode secondRoot = new DirectoryNode("SecondRoot");
        secondRoot.addChild(new FileNode("second.txt", 500));

        SearchVisitor visitor = new SearchVisitor(".txt", 200);
        FileSystemWalker walker = new FileSystemWalker();

        walker.walk(firstRoot, visitor);

        assertEquals(
                List.of("first.txt"),
                visitor.getMatches()
        );

        walker.walk(secondRoot, visitor);

        assertEquals(
                List.of("second.txt"),
                visitor.getMatches()
        );
    }


}
