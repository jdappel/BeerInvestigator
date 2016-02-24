package com.jdappel.beerinvestigator;

import android.app.Application;

import com.jdappel.beerinvestigator.rest.BreweryDBServiceModule;
import com.jdappel.beerinvestigator.ui.beers.BeerModule;

import dagger.ObjectGraph;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BeerApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new BreweryDBServiceModule(),new BeerModule());
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
