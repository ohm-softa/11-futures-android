package ohm.softa.a11.openmensa;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import ohm.softa.a11.openmensa.model.Canteen;
import ohm.softa.a11.openmensa.model.Meal;
import ohm.softa.a11.openmensa.model.State;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Peter Kurfer
 */

public interface OpenMensaAPI {

    /**
     * Retrieve the first page of all canteens
     * includes the response wrapper to be able to extract required headers for further pagination handling
     * @return first page of canteens wrapped in a response object
     */
    @GET("canteens")
    CompletableFuture<Response<List<Canteen>>> getCanteens();

    /**
     * Retrieve any page of all canteens
     * does not include the response wrapper because the required information should be retrieved before this method is used
     * @param pageNumber index of the page to retrieve
     * @return List of canteens as async future
     */
    @GET("canteens")
    CompletableFuture<List<Canteen>> getCanteens(@Query("page")int pageNumber);

    /**
     * Get the state of a canteen specified by its id at the specified date
     * @param canteenId id of the canteen
     * @param date date for which the state should be looked up
     * @return state of the mensa - may be closed or !closed
     */
    @GET("canteens/{canteenId}/days/{date}")
    CompletableFuture<State> getCanteenState(@Path("canteenId") int canteenId, @Path("date") String date);

    /**
     * Retrieve the meals for specified date served at canteen specified by its id
     * @param canteenId id of the canteen
     * @param date date for which the meals should be retrieved
     * @return List of meals wrapped as async future
     */
    @GET("canteens/{canteenId}/days/{date}/meals")
    CompletableFuture<List<Meal>> getMeals(@Path("canteenId") int canteenId, @Path("date") String date);

}
