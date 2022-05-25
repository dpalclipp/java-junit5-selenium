package Tests;

import Pages.UnitTestPage;
import com.idera.xray.junit.customjunitxml.XrayTestReporter;
import com.idera.xray.junit.customjunitxml.XrayTestReporterParameterResolver;
import com.idera.xray.junit.customjunitxml.annotations.Requirement;
import com.idera.xray.junit.customjunitxml.annotations.XrayTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(XrayTestReporterParameterResolver.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Feature("CLIPP-1254:Testing - manual reporting from Serenity to Xray")
@Story("CLIPP-1255: Manual integration - Dev and Unit testing story_create part")
@Requirement("CLIPP-1255")
@Issue("CLIPP-1255")

public class TestUnitTestingPOC {

    UnitTestPage apiOperation;

    @Test
    @Order(1)
    @XrayTest(summary = "Create access token and Validate Part Details ", description = "User first Creates an access token request then Validates a some details of an existing Part")
    @Requirement("CLIPP-1255")
    @Description("This test is about the part details validation using odata")
    public void user_creates_token_and_Validate_Part_Details(XrayTestReporter xrayReporter) {
        try {
            apiOperation.sendTokenRequest();
            xrayReporter.addComment("Token Should be generated. Token No: "+apiOperation.accessToken);
            apiOperation.sendPartDetailsRequest("Test28");
            apiOperation.validatePartDetails("Test8Name");

        }catch (Exception e){e.printStackTrace();}
    }

    @Test
    @Order(2)
    @XrayTest(summary = "Create, validate and Delete part", description = "User first Creates a new Part, validates the response and then delete the newly created Part")
    @Requirement("CLIPP-1255")
    public void create_newPart_Validate_and_delete()
    {
        apiOperation.createPartApi("Test30","Test30Name");
        apiOperation.validatePartDetails("Test30Name");
        apiOperation.deletePartapi();
    }

}