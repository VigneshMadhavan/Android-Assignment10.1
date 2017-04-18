package com.vigneshtraining.assignment101;




import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.support.v7.widget.AppCompatImageButton;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewAnimationUtils;
import android.view.ViewGroup;


import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by vimadhavan on 4/15/2017.
 */

public class MyAlert extends DialogFragment implements View.OnClickListener,Animator.AnimatorListener {

    private ImageButton okBtn;
    private TextView title,msg;
    private Communicator communicator;
    public static final String TAG="MyAlert";
    private View vi;


    @Override
    public void onStart() {
        /*if(getDialog()==null){
            return;
        }else {
            getDialog().getWindow().setWindowAnimations(R.style.DialogAnimation);
        }*/


        super.onStart();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vi=view;
        view.post(new Runnable() {
            @Override
            public void run() {
                doCircularReveal(view);
            }
        });
        //
    }

    private void doCircularReveal(View view) {

        // get the center for the clipping circle
        int centerX = (view.getLeft() + view.getRight()) / 2;
        int centerY = (view.getTop() + view.getBottom()) / 2;

        int startRadius = 0;
        // get the final radius for the clipping circle
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        view.setVisibility(View.VISIBLE);

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view,centerX, centerY, startRadius, endRadius);

        anim.setDuration(1000);
        // make the view invisible when the animation is done



        anim.start();
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setCancelable(false);

        View view=inflater.inflate(R.layout.aler_dialog,null);




        title=(TextView) view.findViewById(R.id.alertTitle);
        msg=(TextView) view.findViewById(R.id.alertMsg);
        okBtn=(ImageButton) view.findViewById(R.id.okBtn);
        okBtn.setOnClickListener(MyAlert.this);



        return view;
    }

    private void doExitReveal(View view){
        int centerX = (view.getLeft() + view.getRight()) / 2;
        int centerY = (view.getTop() + view.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int initialRadius = view.getWidth();

        // create the animation (the final radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view,
                        centerX, centerY, initialRadius, 0);
        anim.setDuration(1000);
        anim.setInterpolator(new BounceInterpolator());
        anim.addListener(MyAlert.this);
        anim.start();
    }



    @Override
    public void onClick(View v) {
        communicator= (Communicator) getContext();
        communicator.onConfirmed();
        okBtn.setVisibility(View.INVISIBLE);
        doExitReveal(vi);
        //dismiss();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        setCancelable(true);

        dismiss();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    interface Communicator {
        public void onConfirmed();
    }
}
