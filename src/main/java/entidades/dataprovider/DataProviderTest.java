package entidades.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderTest {

    @DataProvider(name="providerNameJob")
    public static  Object[][] providerNameJob(){
        return new Object[][]{
                {"Aline", "QA"},
                {"Ana", "Professora"},
                {"Jaqueline", "Dentista"},
        };
    }

}
