import model.FileNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import model.DirectoryNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSystemIntegrityTest {

    @Test
    void shouldRejectNegativeFileSize() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new FileNode("invalid.txt", -100)
        );
    }






    @Test
    void shouldRejectDuplicateChildNames() {

        DirectoryNode root = new DirectoryNode("Root");

        root.addChild(new FileNode("notes.txt", 100));

        assertThrows(
                IllegalArgumentException.class,
                () -> root.addChild(new FileNode("notes.txt", 200))
        );
    }










    @Test
    void shouldRejectDirectoryCycle() {

        DirectoryNode root = new DirectoryNode("Root");
        DirectoryNode documents = new DirectoryNode("Documents");

        root.addChild(documents);

        assertThrows(
                IllegalArgumentException.class,
                () -> documents.addChild(root)
        );

        assertEquals(0, root.getChildren().size() - 1);
        assertEquals(0, documents.getChildren().size());
    }







    @Test
    void shouldRejectMultipleParentOwnership() {

        DirectoryNode first = new DirectoryNode("First");
        DirectoryNode second = new DirectoryNode("Second");

        FileNode sharedFile = new FileNode("shared.txt", 100);

        first.addChild(sharedFile);

        assertThrows(
                IllegalArgumentException.class,
                () -> second.addChild(sharedFile)
        );

        assertEquals(1, first.getChildren().size());
        assertEquals(0, second.getChildren().size());
    }




}
