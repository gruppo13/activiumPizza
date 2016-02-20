package it.unica.ium.pizzalove;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by perlo on 14/02/16.
 */
public class Main extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient = null;
    private PlaceAutocompleteAdapter mToAdapter;

    private static final int REQUEST_RESOLVE_ERROR = 1001;

    private static final String DIALOG_ERROR = "dialog_error";

    private boolean mResolvingError = false;

    private AutoCompleteTextView mToAutocompleteView;

    // bounds for the entire world
    private static final LatLngBounds BOUNDS = new LatLngBounds(new LatLng(-85, -180), new LatLng(85, 180));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //GoogleApiClient client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        mToAutocompleteView = (AutoCompleteTextView) findViewById(R.id.add_alarm_form_to);

        mToAutocompleteView.setOnItemClickListener(mToAutocompleteClickListener);

        mToAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, BOUNDS, null);

        mToAutocompleteView.setAdapter(mToAdapter);

    }

        private AdapterView.OnItemClickListener mToAutocompleteClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
             read the place ID.
              */
                final AutocompletePrediction item = mToAdapter.getItem(position);
                final String placeId = String.valueOf(item.getPlaceId());
                Log.i("error", "Autocomplete item selected: " + item.getDescription());

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, placeId);
                placeResult.setResultCallback(mToUpdatePlaceDetailsCallback);

//            Toast.makeText(getApplicationContext(), "Clicked: " + item.description, Toast.LENGTH_SHORT).show();
                Log.i("error", "Called getPlaceById to get Place details for " + item.getPlaceId());
            }
        };

        /**
         * Callback for results from a Places Geo Data API query that shows the first place result in
         * the details view on screen.
         */
        private ResultCallback<PlaceBuffer> mToUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(PlaceBuffer places) {
                if (!places.getStatus().isSuccess()) {
                    // Request did not complete successfully
                    Log.e("error", "Place query did not complete. Error: " + places.getStatus().toString());
                    places.release();
                    return;
                }

                places.release();
            }
        };

        protected synchronized void buildGoogleApiClient() {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(Places.GEO_DATA_API)
                    .build();
        }


        @Override
        public void onConnected(Bundle bundle) {
            Log.i("error", "Connection started");
        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.i("error", "Connection suspended");
        }

        @Override
        public void onConnectionFailed(ConnectionResult result) {
            Log.e("error", "onConnectionFailed: ConnectionResult.getErrorCode() = " + result.getErrorCode());

            if (mResolvingError) {
                // Already attempting to resolve an error.
                return;
            } else if (result.hasResolution()) {
                try {
                    mResolvingError = true;
                    result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
                } catch (IntentSender.SendIntentException e) {
                    // There was an error with the resolution intent. Try again.
                    mGoogleApiClient.connect();
                }
            } else {
                // Show dialog using GooglePlayServicesUtil.getErrorDialog()
                showErrorDialog(result.getErrorCode());
                mResolvingError = true;
            }
        }

    /* Creates a dialog for an error message */
        private void showErrorDialog(int errorCode) {
            // Create a fragment for the error dialog
            ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
            // Pass the error that should be displayed
            Bundle args = new Bundle();
            args.putInt(DIALOG_ERROR, errorCode);
            dialogFragment.setArguments(args);
            dialogFragment.show(getSupportFragmentManager(), "errordialog");
        }

    /* Called from ErrorDialogFragment when the dialog is dismissed. */
        public void onDialogDismissed() {
            mResolvingError = false;
        }


    /* A fragment to display an error dialog */
        public static class ErrorDialogFragment extends DialogFragment {
            public ErrorDialogFragment() { }

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                // Get the error code and retrieve the appropriate dialog
                int errorCode = this.getArguments().getInt(DIALOG_ERROR);
                return GooglePlayServicesUtil.getErrorDialog(errorCode,
                        this.getActivity(), REQUEST_RESOLVE_ERROR);
            }

            @Override
            public void onDismiss(DialogInterface dialog) {
//            ((MainActivity)getActivity()).onDialogDismissed();
            }
        }

        @Override
        protected void onStart() {
            super.onStart();
            mGoogleApiClient.connect();
        }

        @Override
        protected void onStop() {
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
            super.onStop();
        }
        /*
        Intent intent = new Intent(Main.this, Scelta.class);
        startActivityForResult(intent, 0);*/
    // });
        /*
        ListView view = (ListView) findViewById(R.id.expListPizzerie);
        LayoutInflater infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view.addHeaderView(infalInflater.inflate(R.layout.list_item, null));


        LayoutInflater infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = (ListView) infalInflater.inflate(R.layout.list_item, null);




        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();*/

    }
