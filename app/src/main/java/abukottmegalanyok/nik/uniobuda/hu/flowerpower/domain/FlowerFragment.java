package abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.R;

/**
 * Created by TC_L on 2014.12.06..
 */
public class FlowerFragment extends Fragment {
    Flower flower;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.flower_fragment, container, false);
        flower = (Flower) view.findViewById(R.id.flower);
        return view;
    }

    //increase the flower's level
    public void SetFlowerLevelUp(){
        flower.LevelUp();
    }

    //decrease the flower's level
    public void SetFlowerLevelDown(){
        flower.LevelDown();
    }

    //refresh the source of the flower
    public void FragmentRefresh(){
        flower.Refresh();
    }

    //return the flower's level
    public int GetFlowerLevel(){
        return flower.getLevel();
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        outState.putInt("flevel", flower.getLevel());
//        outState.putInt("backgroundId", flower.GetBackgroundImageId());
//    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        if(savedInstanceState != null){
//            flower.setLevel(savedInstanceState.getInt("flevel", 0));
//            flower.setImageResource(savedInstanceState.getInt("backgroundId", 0));
//        }
//
//    }
}
