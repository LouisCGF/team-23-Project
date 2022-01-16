package com.example.team_23_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class BuildingViewActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    private StreetViewPanorama streetViewPanoramaMain;
    private final LatLng entrance = new LatLng(54.9736636,-1.6250803);
    private final LatLng roomG003 = new LatLng(54.9735334,-1.625157);
    private final LatLng lectureRoom1006 = new LatLng(54.9734782,-1.6253067);
    private final LatLng room2015 = new LatLng(54.9736193,-1.6254572);
    private final LatLng room2022 = new LatLng(54.9739011,-1.625651);
    private final LatLng room3015 = new LatLng(54.9736453,-1.6255025);
    private final LatLng room3018 = new LatLng(54.9739374,-1.6258639);
    private final LatLng room4005 = new LatLng(54.973371,-1.62552);
    private final LatLng groundFloorLifts = new LatLng(54.9735882,-1.6249931);
    private final LatLng firstFloorCorridor = new LatLng(54.9736158,-1.6246716);
    private final LatLng secondFloorCorridor = new LatLng(54.9735999,-1.6252767);
    private final LatLng thirdFloorCorridor = new LatLng(54.9735473,-1.6253395);
    private final LatLng fourthFloorCorridor = new LatLng(54.9734825,-1.6255577);
    private final LatLng outsideLectureRoom = new LatLng(54.973548,-1.6254336);
    
    private FloatingActionButton groundFloorButton;
    private FloatingActionButton entranceButton;
    private FloatingActionButton g003Button;
    private FloatingActionButton groundFloorLiftsButton;
    private FloatingActionButton firstFloorButton;
    private FloatingActionButton lectureTheatreButton;
    private FloatingActionButton firstFloorCorridorButton;
    private FloatingActionButton secondFloorButton;
    private FloatingActionButton secondFloorCorridorButton;
    private FloatingActionButton room2015Button;
    private FloatingActionButton room2022Button;
    private FloatingActionButton thirdFloorButton;
    private FloatingActionButton thirdFloorCorridorButton;
    private FloatingActionButton room3015Button;
    private FloatingActionButton room3018Button;
    private FloatingActionButton fourthFloorButton;
    private FloatingActionButton fourthFloorCorridorButton;
    private FloatingActionButton room4005Button;

    private TextView entranceTxt;
    private TextView g003Txt;
    private TextView groundFloorLiftsTxt;
    private TextView lectureTheatreTxt;
    private TextView firstFloorCorridorTxt;
    private TextView secondFloorCorridorTxt;
    private TextView room2015Txt;
    private TextView room2022Txt;
    private TextView thirdFloorCorridorTxt;
    private TextView room3015Txt;
    private TextView room3018Txt;
    private TextView fourthFloorCorridorTxt;
    private TextView room4005Txt;

    private boolean groundFloorClicked = false;
    private boolean firstFloorClicked = false;
    private boolean secondFloorClicked = false;
    private boolean thirdFloorClicked = false;
    private boolean fourthFloorClicked = false;

    private LatLng currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_view);
        SupportStreetViewPanoramaFragment streetViewPanoramaFragment = (SupportStreetViewPanoramaFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMapsStreetView);

        assert streetViewPanoramaFragment != null;
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        currentPosition = entrance;
        
        // -- Ground Floor Buttons --
        groundFloorButton = findViewById(R.id.groundFloorButton);
        entranceButton = findViewById(R.id.entranceButton);
        g003Button = findViewById(R.id.g003Button);
        groundFloorLiftsButton = findViewById(R.id.groundFloorLiftsButton);

        // -- Ground Floor Button Text --
        entranceTxt = findViewById(R.id.entranceTxt);
        g003Txt = findViewById(R.id.g003Txt);
        groundFloorLiftsTxt = findViewById(R.id.groundFloorLiftsTxt);

        // -- First Floor Buttons --
        firstFloorButton = findViewById(R.id.firstFloorButton);
        lectureTheatreButton = findViewById(R.id.lectureTheatreButton);
        firstFloorCorridorButton = findViewById(R.id.firstFloorCorridorButton);

        // -- First Floor Button Text
        lectureTheatreTxt = findViewById(R.id.lectureTheatreTxt);
        firstFloorCorridorTxt = findViewById(R.id.firstFloorCorridorTxt);

        // -- Second Floor Buttons --
        secondFloorButton = findViewById(R.id.secondFloorButton);
        secondFloorCorridorButton = findViewById(R.id.secondFloorCorridorButton);
        room2015Button = findViewById(R.id.room2015Button);
        room2022Button = findViewById(R.id.room2022Button);

        // -- Second Floor Button Text --
        secondFloorCorridorTxt = findViewById(R.id.secondFloorCorridorTxt);
        room2015Txt = findViewById(R.id.room2015Txt);
        room2022Txt = findViewById(R.id.room2022Txt);

        // -- Third Floor Buttons --
        thirdFloorButton = findViewById(R.id.thirdFloorButton);
        thirdFloorCorridorButton = findViewById(R.id.thirdFloorCorridorButton);
        room3015Button = findViewById(R.id.room3015Button);
        room3018Button = findViewById(R.id.room3018Button);

        // -- Third Floor Button Text --
        thirdFloorCorridorTxt = findViewById(R.id.thirdFloorCorridorTxt);
        room3015Txt = findViewById(R.id.room3015Txt);
        room3018Txt = findViewById(R.id.room3018Txt);

        // -- Fourth Floor Buttons --
        fourthFloorButton = findViewById(R.id.fourthFloorButton);
        fourthFloorCorridorButton = findViewById(R.id.fourthFloorCorridorButton);
        room4005Button = findViewById(R.id.room4005Button);

        // -- Fourth Floor Button Text --
        fourthFloorCorridorTxt = findViewById(R.id.fourthFloorCorridorTxt);
        room4005Txt = findViewById(R.id.room4005Txt);

        fourthFloorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fourthFloorClicked){
                    fourthFloorCorridorButton.setVisibility(View.VISIBLE);
                    room4005Button.setVisibility(View.VISIBLE);
                    fourthFloorCorridorTxt.setVisibility(View.VISIBLE);
                    room4005Button.setVisibility(View.VISIBLE);

                    fourthFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_open_anim));
                    fourthFloorCorridorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room4005Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    fourthFloorCorridorTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room4005Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));

                } else{
                    fourthFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_close_anim));
                    fourthFloorCorridorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room4005Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    fourthFloorCorridorTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room4005Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));

                    fourthFloorCorridorButton.setVisibility(View.INVISIBLE);
                    room4005Button.setVisibility(View.INVISIBLE);
                    fourthFloorCorridorTxt.setVisibility(View.INVISIBLE);
                    room4005Txt.setVisibility(View.INVISIBLE);
                }

                fourthFloorClicked = !fourthFloorClicked;
            }
        });

        thirdFloorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!thirdFloorClicked){
                    thirdFloorCorridorButton.setVisibility(View.VISIBLE);
                    room3015Button.setVisibility(View.VISIBLE);
                    room3018Button.setVisibility(View.VISIBLE);
                    thirdFloorCorridorTxt.setVisibility(View.VISIBLE);
                    room3015Button.setVisibility(View.VISIBLE);
                    room3018Button.setVisibility(View.VISIBLE);

                    thirdFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_open_anim));
                    thirdFloorCorridorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room3015Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room3018Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    thirdFloorCorridorTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room3015Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room3018Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                } else{
                    thirdFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_close_anim));
                    thirdFloorCorridorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room3015Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room3018Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    thirdFloorCorridorTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room3015Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room3018Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));

                    thirdFloorCorridorButton.setVisibility(View.INVISIBLE);
                    room3015Button.setVisibility(View.INVISIBLE);
                    room3018Button.setVisibility(View.INVISIBLE);
                    thirdFloorCorridorTxt.setVisibility(View.INVISIBLE);
                    room3015Txt.setVisibility(View.INVISIBLE);
                    room3018Txt.setVisibility(View.INVISIBLE);

                }

                thirdFloorClicked = !thirdFloorClicked;
            }
        });

        secondFloorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!secondFloorClicked){
                    secondFloorCorridorButton.setVisibility(View.VISIBLE);
                    room2015Button.setVisibility(View.VISIBLE);
                    room2022Button.setVisibility(View.VISIBLE);
                    secondFloorCorridorTxt.setVisibility(View.VISIBLE);
                    room2015Txt.setVisibility(View.VISIBLE);
                    room2022Txt.setVisibility(View.VISIBLE);

                    secondFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_open_anim));
                    secondFloorCorridorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room2015Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room2022Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    secondFloorCorridorTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room2015Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    room2022Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));

                } else{
                    secondFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_close_anim));
                    secondFloorCorridorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room2015Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room2022Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    secondFloorCorridorTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room2015Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    room2022Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));

                    secondFloorCorridorButton.setVisibility(View.INVISIBLE);
                    room2015Button.setVisibility(View.INVISIBLE);
                    room2022Button.setVisibility(View.INVISIBLE);
                    secondFloorCorridorTxt.setVisibility(View.INVISIBLE);
                    room2015Txt.setVisibility(View.INVISIBLE);
                    room2022Txt.setVisibility(View.INVISIBLE);
                }

                secondFloorClicked = !secondFloorClicked;
            }
        });

        groundFloorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!groundFloorClicked){

                    entranceButton.setVisibility(View.VISIBLE);
                    g003Button.setVisibility(View.VISIBLE);
                    groundFloorLiftsButton.setVisibility(View.VISIBLE);
                    entranceTxt.setVisibility(View.VISIBLE);
                    g003Txt.setVisibility(View.VISIBLE);
                    groundFloorLiftsTxt.setVisibility(View.VISIBLE);

                    groundFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_open_anim));
                    entranceButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    g003Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    groundFloorLiftsButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    entranceTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    g003Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    groundFloorLiftsTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));

                } else{

                    groundFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_close_anim));
                    entranceButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    g003Button.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    groundFloorLiftsButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    entranceTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    g003Txt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    groundFloorLiftsTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));

                    entranceButton.setVisibility(View.INVISIBLE);
                    g003Button.setVisibility(View.INVISIBLE);
                    groundFloorLiftsButton.setVisibility(View.INVISIBLE);
                    entranceTxt.setVisibility(View.INVISIBLE);
                    g003Txt.setVisibility(View.INVISIBLE);
                    groundFloorLiftsTxt.setVisibility(View.INVISIBLE);

                }

                groundFloorClicked = !groundFloorClicked;
            }
        });

        firstFloorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstFloorClicked){
                    lectureTheatreButton.setVisibility(View.VISIBLE);
                    firstFloorCorridorButton.setVisibility(View.VISIBLE);
                    lectureTheatreTxt.setVisibility(View.VISIBLE);
                    firstFloorCorridorTxt.setVisibility(View.VISIBLE);

                    firstFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_open_anim));
                    lectureTheatreButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    firstFloorCorridorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    lectureTheatreTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));
                    firstFloorCorridorTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.from_bottom_anim));

                } else{
                    firstFloorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.rotate_close_anim));
                    lectureTheatreButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    firstFloorCorridorButton.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    lectureTheatreTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));
                    firstFloorCorridorTxt.setAnimation(AnimationUtils.loadAnimation(BuildingViewActivity.this, R.anim.to_bottom_anim));

                    lectureTheatreButton.setVisibility(View.INVISIBLE);
                    firstFloorCorridorButton.setVisibility(View.INVISIBLE);
                    lectureTheatreTxt.setVisibility(View.INVISIBLE);
                    firstFloorCorridorTxt.setVisibility(View.INVISIBLE);
                }

                firstFloorClicked = !firstFloorClicked;
            }
        });

        // -- GROUND FLOOR --
        entranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = entrance;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        g003Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = roomG003;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        groundFloorLiftsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = groundFloorLifts;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        // -- FIRST FLOOR --

        lectureTheatreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = lectureRoom1006;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        firstFloorCorridorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = firstFloorCorridor;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        // -- SECOND FLOOR --

        secondFloorCorridorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = secondFloorCorridor;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        room2015Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = room2015;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        room2022Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = room2022;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        // -- THIRD FLOOR --

        thirdFloorCorridorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = thirdFloorCorridor;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        room3015Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = room3015;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

       room3018Button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               currentPosition = room3018;
               onStreetViewPanoramaReady(streetViewPanoramaMain);
           }
       });

       // -- FOURTH FLOOR --

        fourthFloorCorridorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = fourthFloorCorridor;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });

        room4005Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = room4005;
                onStreetViewPanoramaReady(streetViewPanoramaMain);
            }
        });



    }


    @Override
    public void onStreetViewPanoramaReady(@NonNull StreetViewPanorama streetViewPanorama) {
        streetViewPanoramaMain = streetViewPanorama;

        streetViewPanoramaMain.setPosition(currentPosition, 1);

        streetViewPanoramaMain.setStreetNamesEnabled(false);
        streetViewPanoramaMain.setPanningGesturesEnabled(true);
        streetViewPanoramaMain.setZoomGesturesEnabled(true);
        streetViewPanoramaMain.setUserNavigationEnabled(true);
        streetViewPanoramaMain.animateTo(
                new StreetViewPanoramaCamera.Builder().orientation(new StreetViewPanoramaOrientation(20, 20))
                        .zoom(streetViewPanoramaMain.getPanoramaCamera().zoom).build(), 2000
        );
        streetViewPanoramaMain.setOnStreetViewPanoramaChangeListener(panoramaChangeListener);

    }

    private final StreetViewPanorama.OnStreetViewPanoramaChangeListener panoramaChangeListener = new StreetViewPanorama.OnStreetViewPanoramaChangeListener() {
        @Override
        public void onStreetViewPanoramaChange(@NonNull StreetViewPanoramaLocation streetViewPanoramaLocation) {
            Toast.makeText(BuildingViewActivity.this, "Location Updated", Toast.LENGTH_SHORT).show();
        }

    };
}