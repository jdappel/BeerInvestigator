package com.jdappel.beerinvestigator.rest;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

import io.reactivex.Observable;

public class BreweryDBApiIT {

    private BreweryDBApi service;

    @Before
    public void setup() {
        service = BreweryDBService.INSTANCE.createBreweryDBService("948fd17f5f5d6560f06d6533f18af582");
    }

    @Test
    public void testSearch() throws Exception {
        Observable<BreweryDBResponse<Beer>> beers = service.getBeers("w");
        Style style = new Style("90", "Malty sweetness is dominant but should not be cloying. Malt character is more reminiscent of fresh and lightly toasted Munich- style malt, more so than caramel or toffee malt character. Some elements of caramel and toffee can be evident and contribute to complexity, but the predominant malt character is an expression of toasted barley malt. Doppelbocks are full bodied and deep amber to dark brown in color. Astringency from roast malts is absent. Alcoholic strength is high, and hop rates increase with gravity. Hop bitterness and flavor should be low and hop aroma absent. Fruity esters are commonly perceived but at low to moderate levels. Diacetyl should be absent", 6.5f, 8.0f);
        Beer expected = new Beer("Uu2ExM",
                "A big malty beer with caramel & toffee flavors that has a clean & smooth finish. \r\n\r\nIngredients: Imported German barley & wheat malt, Northern Brewer & Vanguard Hops.", "Goosinator Smoked Doppelbock 2007", 7.7f, style);
        beers.map(item -> item.getData()).forEach(System.out::println);
        assertTrue(beers.map(item -> item.getData().contains(expected)).blockingFirst());
    }
}