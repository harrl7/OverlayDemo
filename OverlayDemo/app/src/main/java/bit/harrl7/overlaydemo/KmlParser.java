package bit.harrl7.overlaydemo;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by tsgar on 28/09/2016.
 */

public class KmlParser {

    //Handle all conversions of kml data onto map display
    //Called by PathMapperActivity
    protected Context context;
    private GoogleMap gMap;

    public KmlParser(GoogleMap gMap, Context context)
    {
        this.gMap = gMap;
        this.context = context;
    }

    public void RenderKmlPaths()
    {
        retrieveFileFromResources();
    }

    private void retrieveFileFromResources(){
        try{
            KmlLayer kmlLayer = new KmlLayer(gMap, R.raw.new_path, context); //R.raw.filename = the kml data file.
            kmlLayer.addLayerToMap(); //Adds the layer to the map
        }
        catch (IOException e){
            Log.e("IO KML exception::  ", e.getMessage());
            e.printStackTrace();
        }
        catch (XmlPullParserException e){
            Log.e("XML/KML exception:  ", e.getMessage());
            e.printStackTrace();
        }
    }


}
