package uk.co.jackdh.funfacts.API;

import retrofit.Callback;
import retrofit.http.*;
import uk.co.jackdh.funfacts.model.Fact;

/**
 * Created by jack on 11/10/2016.
 */

public interface FactAPI {
    @GET("/jokes/random")      //here is the other url part.best way is to start using /
    public void getFeed(Callback<Fact> response);     //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO


}
