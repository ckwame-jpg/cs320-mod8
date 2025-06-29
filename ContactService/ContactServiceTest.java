import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
        contact = new Contact("001", "John", "Doe", "1234567890", "456 Park Ave");
        service.addContact(contact);
    }

    @Test
    public void testAddDuplicateContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(new Contact("001", "Jane", "Smith", "0987654321", "789 Elm St"));
        });
    }

    @Test
    public void testDeleteContact() {
        service.deleteContact("001");
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("001"));
    }

    @Test
    public void testUpdateFields() {
        service.updateFirstName("001", "Mike");
        service.updateLastName("001", "Jones");
        service.updatePhone("001", "1112223333");
        service.updateAddress("001", "321 Oak St");

        Contact updated = service.getContact("001");
        assertEquals("Mike", updated.getFirstName());
        assertEquals("Jones", updated.getLastName());
        assertEquals("1112223333", updated.getPhone());
        assertEquals("321 Oak St", updated.getAddress());
    }
}