package com.example.user.smartmuseum.estimote;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.estimote.internal_plugins_api.scanning.Beacon;
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials;
import com.estimote.proximity_sdk.api.ProximityObserver;
import com.estimote.proximity_sdk.api.ProximityObserverBuilder;
import com.estimote.proximity_sdk.api.ProximityZone;
import com.estimote.proximity_sdk.api.ProximityZoneBuilder;
import com.estimote.proximity_sdk.api.ProximityZoneContext;
import com.example.user.smartmuseum.GalleryActivity;
import com.example.user.smartmuseum.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import es.dmoral.toasty.Toasty;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class ProximityContentManager  {
    private Beacon beacon ;
    private Context context;
    private ProximityContentAdapter proximityContentAdapter;
    private EstimoteCloudCredentials cloudCredentials;
    private ProximityObserver.Handler proximityObserverHandler;

    public ProximityContentManager(Context context,Beacon beacon, ProximityContentAdapter proximityContentAdapter, EstimoteCloudCredentials cloudCredentials) {
        this.context = context;
        this.proximityContentAdapter = proximityContentAdapter;
        this.cloudCredentials = cloudCredentials;
        this.beacon = beacon;
    }

    public ProximityContentManager(Search search, ProximityContentAdapter proximityContentAdapter, EstimoteCloudCredentials cloudCredentials) {

    }

    public interface BeaconCallback{
        public void onBeaconFound(Beacon beacon);
    }
    BeaconCallback callback = new BeaconCallback() {
        @Override
        public void onBeaconFound(Beacon beacon) {
            context.startActivity(new Intent(context,GalleryActivity.class));
        }
    };
    public void start() {

        ProximityObserver proximityObserver = new ProximityObserverBuilder(context, cloudCredentials)
                .onError(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        Log.e("app", "proximity observer error: " + throwable);
                        return null;
                    }

                })

                .withBalancedPowerMode()
                .build();

        ProximityZone zone = new ProximityZoneBuilder()
                .forTag("vladoos")
                .inCustomRange(3.0)
                .onContextChange(new Function1<Set<? extends ProximityZoneContext>, Unit>() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public Unit invoke(Set<? extends ProximityZoneContext> contexts) {

                        List<ProximityContent> nearbyContent = new ArrayList<>(contexts.size());

                        for (ProximityZoneContext proximityContext : contexts) {
                            String title = proximityContext.getAttachments().get("500kg-ggwp-gmail-com-s-pro-bl1/title");
                            if (title == null) {
                                title = "unknown";
                            }
                            Toasty.success(context,"In range",Toast.LENGTH_LONG,true).show();
                            String subtitle = Utils.getShortIdentifier(proximityContext.getDeviceId());

                            nearbyContent.add(new ProximityContent(title, subtitle));
                            if (subtitle.equals("f70973c44bf2e4d4cf0e832e04cf9f3b")){
                            }
                        }

                        proximityContentAdapter.setNearbyContent((List<ProximityContent>) callback);
                        proximityContentAdapter.notifyDataSetChanged();
                        return null;
                    }
                })
                .build();

        proximityObserverHandler = proximityObserver.startObserving(zone);
    }


    public void stop() {
        proximityObserverHandler.stop();
    }
}
