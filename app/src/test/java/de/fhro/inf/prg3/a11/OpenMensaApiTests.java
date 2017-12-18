package de.fhro.inf.prg3.a11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.fhro.inf.prg3.a11.openmensa.OpenMensaAPI;
import de.fhro.inf.prg3.a11.openmensa.OpenMensaAPIService;
import de.fhro.inf.prg3.a11.openmensa.model.Canteen;
import de.fhro.inf.prg3.a11.openmensa.model.PageInfo;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 */

public class OpenMensaApiTests {

    private final OpenMensaAPI openMensaAPI = OpenMensaAPIService.getInstance().getOpenMensaAPI();

    @Test()
    void testGetMensaState() throws ExecutionException, InterruptedException {
        Response<List<Canteen>> canteensResponse = openMensaAPI.getCanteens().get();

        assertNotNull(canteensResponse);
        assertNotNull(canteensResponse.body());
        for (Canteen c : canteensResponse.body()) {
            System.out.println(c.getName());
        }
    }

    @Test
    void testExtractPageInfo() throws ExecutionException, InterruptedException {
        Response<List<Canteen>> canteensResponse = openMensaAPI.getCanteens().get();

        PageInfo pageInfo = PageInfo.extractFromResponse(canteensResponse);

        assertNotNull(pageInfo);
        assertEquals(canteensResponse.body().size(), pageInfo.getItemCountPerPage());
        assertTrue(pageInfo.getTotalCountOfItems() > 0);
        assertTrue(pageInfo.getTotalCountOfPages() > 0);
        assertTrue(pageInfo.getCurrentPageIndex() > 0);
    }
}
