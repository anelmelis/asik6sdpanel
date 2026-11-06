import model.DirectoryNode;
import model.FileNode;
import model.Visitor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleDispatchTest {

    @Test
    void shouldCallCorrectVisitMethods() {

        FileNode file = new FileNode("test.txt", 100);
        DirectoryNode directory = new DirectoryNode("Root");

        class TrackingVisitor implements Visitor {
            int fileVisits = 0;
            int directoryVisits = 0;

            @Override
            public void visit(FileNode file) {
                fileVisits++;
            }

            @Override
            public void visit(DirectoryNode directory) {
                directoryVisits++;
            }
        }

        TrackingVisitor visitor = new TrackingVisitor();

        file.accept(visitor);
        directory.accept(visitor);

        assertEquals(1, visitor.fileVisits);
        assertEquals(1, visitor.directoryVisits);
    }
}
