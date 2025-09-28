import javax.swing.JTextField;

import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.prop_base.UpdateProperty;
import com.prop_base.ValidationsHelper;

public class RentalPropertyTests 
{
    @Test
    void SearchProperty_ReturnsProperty()
    {
        String expectedAccount = "ACC-847706";
        String expectedAddress = "123 Test";
        double expectedRent = 1500.00;
        String expectedAgent = "Smith Mbele";

        JSONObject result = ValidationsHelper.searchPropertyByAccount(expectedAccount);
        assertNotNull(result, "Property should be found");
    //-Verifies that all fields match
        assertEquals(expectedAccount, result.getString("Account Number"), "Account Number mismatch");
        assertEquals(expectedAddress, result.getString("Property Address"), "Property Address mismatch");
        assertEquals(expectedRent, result.getDouble("Monthly Rent"), "Monthly Rent mismatch");
        assertEquals(expectedAgent, result.getString("Agent"), "Agent Name mismatch");
    }

    @Test
    void UpdateProperty_ReturnsSuccess() 
    {
        String[] updatedData = {"ACC-847706", "123 Test", "1500.90", "Mpho Mbele"};

        UpdateProperty updater = new UpdateProperty();
        boolean updated = updater.updatePropertyDirect(0, updatedData);

        assertTrue(updated, "Property should be updated");

        JSONObject result = ValidationsHelper.searchPropertyByAccount("ACC-795785");
        assertNotNull(result, "Property should be found after update");
        assertEquals("123 Rent >1500 Streey", result.getString("Property Address"));
        assertEquals("1500.90 ", result.getString("Monthly Rent"));
        assertEquals("Smith Mbele", result.getString("Agent"));
    }

    @Test
    void DeleteProperty_ReturnsSuccess() 
    {
        String[] testData = {"ACC-123456", "123 Test", "1500.00", "Smith Mbele"};
        UpdateProperty updater = new UpdateProperty();
        boolean added = updater.updatePropertyDirect(0, testData);  // reuse your direct updater
        assertTrue(added, "Setup failed: property not added");

        boolean deleted = ValidationsHelper.deletePropertyByAccount("ACC-123456");

        assertTrue(deleted, "Property should be deleted");
        JSONObject result = ValidationsHelper.searchPropertyByAccount("ACC-123456");
        assertNull(result, "Property should not exist after deletion");
    }

    @Test
    void PropertyAmountValidation_AmountsIsValid() 
    {
        JTextField field = new JTextField("2000");

        boolean result = ValidationsHelper.PropertyAmountValidation(
            field,
            "Monthly Rent",
            true 
        );
        assertTrue(result, "Validation should pass for a valid amount >= 1500");
    }

    @Test
    void PropertyAmountValidation_AmountTooLow() 
    {
    JTextField field = new JTextField("1000");                                                      //-Amount below R1500 is invalid

    boolean result = ValidationsHelper.PropertyAmountValidation(
            field,
            "Monthly Rent",
            true
        );
        assertFalse(result, "Validation should fail for amount < 1500");
    }
}
