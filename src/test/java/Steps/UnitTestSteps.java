package Steps;

import Pages.UnitTestPage;


public class UnitTestSteps {


    UnitTestPage api;


    public void sendRequestAccessToken()
    {
        api.sendTokenRequest();
    }

    public String getaccessToken()
    {
        return api.returnAccesstoken();
    }

    public void postPartDetailsRequest(String itemNumber)
    {
        api.sendPartDetailsRequest(itemNumber);
    }

    public void validatePartDetails(String Name)
    {
        api.validatePartDetails(Name);
    }

    public void createNewPart(String itemNo, String name)
    {
        //api.createPartApi(itemNo,name);
    }

    public void deletePart()
    {
       // api.deletePartapi();
    }
}
