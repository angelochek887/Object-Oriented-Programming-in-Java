package ua.edu.sumdu.j2se.pr9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    @Test
    void shouldThrowObjectNotFoundExceptionWhenDeletingNonExistingObject() {
        Store store = new Store();
        KeypadPhone phone = new KeypadPhone("Nokia", "3310", 1, 45.0, true);

        assertThrows(ObjectNotFoundException.class, () -> {
            store.delete(phone);
        });
    }

    @Test
    void shouldThrowInvalidFieldValueExceptionWhenSettingNegativePrice() {
        KeypadPhone phone = new KeypadPhone("Nokia", "3310", 1, 45.0, true);

        assertThrows(InvalidFieldValueException.class, () -> {
            phone.setPrice(-100.0);
        });
    }
    
    @Test
    void shouldThrowObjectNotFoundExceptionWhenUpdatingNonExistingObject() {
        Store store = new Store();
        SmartPhone existingPhone = new SmartPhone("Apple", "iPhone", 128, 999.0, 12);
        SmartPhone newPhone = new SmartPhone("Apple", "iPhone", 256, 1099.0, 12);
        
        assertThrows(ObjectNotFoundException.class, () -> {
            store.update(existingPhone, newPhone);
        });
    }
}